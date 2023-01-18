package com.test.simpleasset.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.simpleasset.constant.Message;
import com.test.simpleasset.dao.AssetDao;
import com.test.simpleasset.dao.AssetStatusDao;
import com.test.simpleasset.dao.AssetTypeDao;
import com.test.simpleasset.dao.CompanyDao;
import com.test.simpleasset.dao.FileDao;
import com.test.simpleasset.dao.ProviderDao;
import com.test.simpleasset.dao.UserDao;
import com.test.simpleasset.dto.asset.AssetDataDto;
import com.test.simpleasset.dto.asset.AssetDataResDto;
import com.test.simpleasset.dto.asset.AssetDeleteReqDto;
import com.test.simpleasset.dto.asset.AssetDeleteResDto;
import com.test.simpleasset.dto.asset.AssetInsertDataResDto;
import com.test.simpleasset.dto.asset.AssetInsertReqDto;
import com.test.simpleasset.dto.asset.AssetInsertResDto;
import com.test.simpleasset.dto.asset.AssetUpdateReqDto;
import com.test.simpleasset.dto.asset.AssetUpdateResDto;
import com.test.simpleasset.dto.asset.AssetsDto;
import com.test.simpleasset.model.Asset;
import com.test.simpleasset.model.AssetStatus;
import com.test.simpleasset.model.AssetType;
import com.test.simpleasset.model.Company;
import com.test.simpleasset.model.File;
import com.test.simpleasset.model.Provider;
import com.test.simpleasset.model.User;
import com.test.simpleasset.pojo.EmailPojo;

@Service
public class AssetService {

	@Autowired
	private AssetDao assetDao;
	@Autowired
	private ProviderDao providerDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private AssetTypeDao assetTypeDao;
	@Autowired
	private AssetStatusDao assetStatusDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PrincipalService principalService;
	@Autowired
	private EmailService emailService;

	@Transactional(rollbackOn = Exception.class)
	public AssetInsertResDto insert(final AssetInsertReqDto data) {
		final AssetInsertResDto assetInsertResDto = new AssetInsertResDto();
		final AssetInsertDataResDto assetInsertDataResDto = new AssetInsertDataResDto();
		final Asset asset = new Asset();
		asset.setAssetName(data.getAssetName());
		asset.setInvoiceNumber(data.getInvoiceNumber());
		asset.setSerialNumber(data.getSerialNumber());
		if (data.getExpiredDate() != null) {
			asset.setExpiredDate(data.getExpiredDate());
		}
		asset.setCreatedBy(principalService.getPrinciple().getId());
		asset.setUpdatedBy(principalService.getPrinciple().getId());

		final AssetStatus assetStatus = assetStatusDao.getById(data.getAssetStatusId()).get();
		asset.setAssetStatus(assetStatus);
		final AssetType assetType = assetTypeDao.getById(data.getAssetTypeId()).get();
		asset.setAssetType(assetType);
		final Provider provider = providerDao.getById(data.getProviderId()).get();
		asset.setProvider(provider);
		final Company company = companyDao.getById(data.getCompanyId()).get();
		asset.setCompany(company);

		File file = new File();
		file.setFileCodes(data.getFileCodes());
		file.setExtensions(data.getExtensions());
		file.setCreatedBy(principalService.getPrinciple().getId());
		file.setUpdatedBy(principalService.getPrinciple().getId());
		file = fileDao.insert(file);
		asset.setFile(file);

		final Asset assetInsert = assetDao.insert(asset);

		final EmailPojo emailPojo = new EmailPojo();
		final Optional<User> superAdmin = userDao.getById(principalService.getPrinciple().getCreatedBy());
		emailPojo.setEmail(superAdmin.get().getEmail());
		emailPojo.setSubject("Asset Telah Dibuat");
		emailPojo.setBody("Asset dengan Nama : " + data.getAssetName() + ", Invoice Number : " + data.getInvoiceNumber()
				+ ", Serial Number : " + data.getSerialNumber() + ", Expired Date : " + data.getExpiredDate()
				+ ", Berhasi Dibuat");
		final Runnable r = () -> emailService.sendMail(emailPojo);
		final Thread thread = new Thread(r);
		thread.start();

		assetInsertDataResDto.setId(assetInsert.getId());
		assetInsertResDto.setData(assetInsertDataResDto);
		assetInsertResDto.setMessage(Message.INSERTED.getMessage());
		return assetInsertResDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public AssetUpdateResDto update(final AssetUpdateReqDto data) {
		final Optional<Asset> assetOptional = assetDao.getById(data.getAssetId());
		final AssetUpdateResDto assetUpdateResDto = new AssetUpdateResDto();
		Asset assetUpdate = null;
		if (assetOptional.isPresent()) {
			assetUpdate = assetOptional.get();
			assetUpdate.setAssetName(data.getAssetName());
			assetUpdate.setInvoiceNumber(data.getInvoiceNumber());
			assetUpdate.setSerialNumber(data.getSerialNumber());
			assetUpdate.setIsActive(data.getIsActive());
			if (data.getExpiredDate() != null) {
				assetUpdate.setExpiredDate(data.getExpiredDate());
			}

			final AssetStatus assetStatus = assetStatusDao.getById(data.getAssetStatusId()).get();
			assetUpdate.setAssetStatus(assetStatus);
			final AssetType assetType = assetTypeDao.getById(data.getAssetTypeId()).get();
			assetUpdate.setAssetType(assetType);
			final Provider provider = providerDao.getById(data.getProviderId()).get();
			assetUpdate.setProvider(provider);
			final Company company = companyDao.getById(data.getCompanyId()).get();
			assetUpdate.setCompany(company);

			final long oldFileId = assetUpdate.getFile().getId();
			File file = new File();
			file.setFileCodes(data.getFileCodes());
			file.setExtensions(data.getExtensions());
			file.setCreatedBy(principalService.getPrinciple().getId());
			file.setUpdatedBy(principalService.getPrinciple().getId());
			file = fileDao.insert(file);
			assetUpdate.setFile(file);

			assetUpdate.setUpdatedBy(principalService.getPrinciple().getId());
			assetUpdate = assetDao.update(assetUpdate);
			fileDao.deleteById(oldFileId);
			data.setVersion(assetUpdate.getVersion());
			assetUpdateResDto.setData(data);
			assetUpdateResDto.setMessage(Message.UPDATED.getMessage());
		}
		return assetUpdateResDto;
	}

	public AssetDataResDto getById(final Long id) {
		Optional<Asset> assetOptional = assetDao.getById(id);
		final AssetDataDto assetDataDto = new AssetDataDto();
		if (assetOptional.isPresent()) {
			final Asset asset = assetOptional.get();
			assetDataDto.setAssetName(asset.getAssetName());
			assetDataDto.setAssetTypeName(asset.getAssetType().getAssetTypeName());
			assetDataDto.setAssetStatusName(asset.getAssetStatus().getAssetStatusName());
			if (asset.getExpiredDate() != null) {
				assetDataDto.setExpiredDate(asset.getExpiredDate());
			}
			assetDataDto.setInvoiceNumber(asset.getInvoiceNumber());
			assetDataDto.setSerialNumber(asset.getSerialNumber());
			assetDataDto.setCompanyName(asset.getCompany().getCompanyName());
			assetDataDto.setFileId(asset.getFile().getId());
			assetDataDto.setIsActive(asset.getIsActive());
			assetDataDto.setAssetId(asset.getId());
			assetDataDto.setVersion(asset.getVersion());
			assetDataDto.setProviderName(asset.getProvider().getProviderName());
		}
		final AssetDataResDto assetDataResDto = new AssetDataResDto();
		assetDataResDto.setData(assetDataDto);
		return assetDataResDto;
	}

	public AssetsDto getAll() {
		final List<Asset> assets = assetDao.getAll();
		final List<AssetDataDto> dataDtos = new ArrayList<>();

		final Comparator<Asset> compareByNameThenSerial = Comparator.comparing(Asset::getAssetName)
				.thenComparing(Asset::getSerialNumber);
		final Stream<Asset> sortedAssets = assets.stream().sorted(compareByNameThenSerial);

		sortedAssets.forEach(asset -> {
			final AssetDataDto assetDataDto = new AssetDataDto();
			assetDataDto.setAssetName(asset.getAssetName());
			assetDataDto.setAssetTypeName(asset.getAssetType().getAssetTypeName());
			assetDataDto.setAssetStatusName(asset.getAssetStatus().getAssetStatusName());
			if (asset.getExpiredDate() != null) {
				assetDataDto.setExpiredDate(asset.getExpiredDate());
			}
			assetDataDto.setInvoiceNumber(asset.getInvoiceNumber());
			assetDataDto.setSerialNumber(asset.getSerialNumber());
			assetDataDto.setCompanyName(asset.getCompany().getCompanyName());
			assetDataDto.setFileId(asset.getFile().getId());
			assetDataDto.setAssetId(asset.getId());
			assetDataDto.setVersion(asset.getVersion());
			assetDataDto.setProviderName(asset.getProvider().getProviderName());
			assetDataDto.setIsActive(asset.getIsActive());
			dataDtos.add(assetDataDto);
		});

		final AssetsDto assetsDto = new AssetsDto();
		assetsDto.setData(dataDtos);

		return assetsDto;
	}

	public AssetsDto findByName(final String name) {
		final List<Asset> assets = assetDao.getAll();
		final List<AssetDataDto> dataDtos = new ArrayList<>();

		final Comparator<Asset> compareByNameThenSerial = Comparator.comparing(Asset::getAssetName)
				.thenComparing(Asset::getSerialNumber);
		final Stream<Asset> sortedAssets = assets.stream().sorted(compareByNameThenSerial)
				.filter(asset -> asset.getAssetName().toLowerCase().contains(name.toLowerCase()));

		sortedAssets.forEach(asset -> {
			final AssetDataDto assetDataDto = new AssetDataDto();
			assetDataDto.setAssetName(asset.getAssetName());
			assetDataDto.setAssetTypeName(asset.getAssetType().getAssetTypeName());
			assetDataDto.setAssetStatusName(asset.getAssetStatus().getAssetStatusName());
			if (asset.getExpiredDate() != null) {
				assetDataDto.setExpiredDate(asset.getExpiredDate());
			}
			assetDataDto.setInvoiceNumber(asset.getInvoiceNumber());
			assetDataDto.setSerialNumber(asset.getSerialNumber());
			assetDataDto.setCompanyName(asset.getCompany().getCompanyName());
			assetDataDto.setFileId(asset.getFile().getId());
			assetDataDto.setAssetId(asset.getId());
			assetDataDto.setVersion(asset.getVersion());
			assetDataDto.setProviderName(asset.getProvider().getProviderName());
			assetDataDto.setIsActive(asset.getIsActive());
			dataDtos.add(assetDataDto);
		});

		final AssetsDto assetsDto = new AssetsDto();
		assetsDto.setData(dataDtos);

		return assetsDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public AssetDeleteResDto deleteById(final Long id) {
		final Optional<Asset> assetOptional = assetDao.getById(id);
		final AssetDeleteResDto assetDeleteResDto = new AssetDeleteResDto();
		if (assetOptional.isPresent()) {
			final AssetDeleteReqDto assetDeleteReqDto = new AssetDeleteReqDto();
			assetDeleteReqDto.setAssetId(id);
			assetDeleteResDto.setMessage(Message.DELETED.getMessage());
			assetDao.deleteById(id);
		}
		return assetDeleteResDto;
	}

	public AssetsDto getAllDeployable() {
		final List<Asset> assets = assetDao.getAllDeployable();
		final List<AssetDataDto> dataDtos = new ArrayList<>();
		assets.stream().forEach(asset -> {
			final AssetDataDto assetDataDto = new AssetDataDto();
			assetDataDto.setAssetName(asset.getAssetName());
			assetDataDto.setAssetTypeName(asset.getAssetType().getAssetTypeName());
			assetDataDto.setAssetStatusName(asset.getAssetStatus().getAssetStatusName());
			if (asset.getExpiredDate() != null) {
				assetDataDto.setExpiredDate(asset.getExpiredDate());
			}
			assetDataDto.setInvoiceNumber(asset.getInvoiceNumber());
			assetDataDto.setSerialNumber(asset.getSerialNumber());
			assetDataDto.setCompanyName(asset.getCompany().getCompanyName());
			assetDataDto.setFileId(asset.getFile().getId());
			assetDataDto.setAssetId(asset.getId());
			assetDataDto.setVersion(asset.getVersion());
			assetDataDto.setProviderName(asset.getProvider().getProviderName());
			dataDtos.add(assetDataDto);
		});
		final AssetsDto assetsDto = new AssetsDto();
		assetsDto.setData(dataDtos);

		return assetsDto;
	}

	public AssetsDto getAllDeployableGeneral() {
		final List<Asset> assets = assetDao.getAllDeployableGeneral();
		final List<AssetDataDto> dataDtos = new ArrayList<>();
		assets.stream().forEach(asset -> {
			final AssetDataDto assetDataDto = new AssetDataDto();
			assetDataDto.setAssetName(asset.getAssetName());
			assetDataDto.setAssetTypeName(asset.getAssetType().getAssetTypeName());
			assetDataDto.setAssetStatusName(asset.getAssetStatus().getAssetStatusName());
			if (asset.getExpiredDate() != null) {
				assetDataDto.setExpiredDate(asset.getExpiredDate());
			}
			assetDataDto.setInvoiceNumber(asset.getInvoiceNumber());
			assetDataDto.setSerialNumber(asset.getSerialNumber());
			assetDataDto.setCompanyName(asset.getCompany().getCompanyName());
			assetDataDto.setFileId(asset.getFile().getId());
			assetDataDto.setAssetId(asset.getId());
			assetDataDto.setVersion(asset.getVersion());
			assetDataDto.setProviderName(asset.getProvider().getProviderName());
			dataDtos.add(assetDataDto);
		});
		final AssetsDto assetsDto = new AssetsDto();
		assetsDto.setData(dataDtos);

		return assetsDto;
	}

	public AssetsDto getAllDeployableComponents() {
		final List<Asset> assets = assetDao.getAllDeployableComponent();
		final List<AssetDataDto> dataDtos = new ArrayList<>();
		assets.stream().forEach(asset -> {
			final AssetDataDto assetDataDto = new AssetDataDto();
			assetDataDto.setAssetName(asset.getAssetName());
			assetDataDto.setAssetTypeName(asset.getAssetType().getAssetTypeName());
			assetDataDto.setAssetStatusName(asset.getAssetStatus().getAssetStatusName());
			if (asset.getExpiredDate() != null) {
				assetDataDto.setExpiredDate(asset.getExpiredDate());
			}
			assetDataDto.setInvoiceNumber(asset.getInvoiceNumber());
			assetDataDto.setSerialNumber(asset.getSerialNumber());
			assetDataDto.setCompanyName(asset.getCompany().getCompanyName());
			assetDataDto.setFileId(asset.getFile().getId());
			assetDataDto.setAssetId(asset.getId());
			assetDataDto.setVersion(asset.getVersion());
			assetDataDto.setProviderName(asset.getProvider().getProviderName());
			dataDtos.add(assetDataDto);
		});
		final AssetsDto assetsDto = new AssetsDto();
		assetsDto.setData(dataDtos);

		return assetsDto;
	}
}
