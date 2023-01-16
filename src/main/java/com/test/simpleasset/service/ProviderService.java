package com.test.simpleasset.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.simpleasset.constant.Message;
import com.test.simpleasset.dao.FileDao;
import com.test.simpleasset.dao.ProviderDao;
import com.test.simpleasset.dao.StoreDao;
import com.test.simpleasset.dto.provider.ProviderDataDto;
import com.test.simpleasset.dto.provider.ProviderDataResDto;
import com.test.simpleasset.dto.provider.ProviderDeleteReqDto;
import com.test.simpleasset.dto.provider.ProviderDeleteResDto;
import com.test.simpleasset.dto.provider.ProviderInsertDataResDto;
import com.test.simpleasset.dto.provider.ProviderInsertReqDto;
import com.test.simpleasset.dto.provider.ProviderInsertResDto;
import com.test.simpleasset.dto.provider.ProviderUpdateReqDto;
import com.test.simpleasset.dto.provider.ProviderUpdateResDto;
import com.test.simpleasset.dto.provider.ProvidersDto;
import com.test.simpleasset.model.File;
import com.test.simpleasset.model.Provider;
import com.test.simpleasset.model.Store;

@Service
public class ProviderService {
	@Autowired
	private ProviderDao providerDao;
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private PrincipalService principalService;

	public ProvidersDto getAll() {
		final List<Provider> providers = providerDao.getAll();
		final List<ProviderDataDto> dataDtos = new ArrayList<>();
		providers.stream().forEach(provider -> {
			final ProviderDataDto providerDataDto = new ProviderDataDto();
			providerDataDto.setProviderCode(provider.getProviderCode());
			providerDataDto.setProviderName(provider.getProviderName());
			providerDataDto.setStoreName(provider.getStore().getStoreName());
			providerDataDto.setFileId(provider.getFile().getId());
			providerDataDto.setProviderId(provider.getId());
			providerDataDto.setVersion(provider.getVersion());
			providerDataDto.setIsActive(provider.getIsActive());
			dataDtos.add(providerDataDto);
		});

		final ProvidersDto providersDto = new ProvidersDto();
		providersDto.setData(dataDtos);

		return providersDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public ProviderInsertResDto insert(final ProviderInsertReqDto data) {
		final ProviderInsertResDto providerInsertResDto = new ProviderInsertResDto();
		final ProviderInsertDataResDto providerInsertDataResDto = new ProviderInsertDataResDto();
		final Provider provider = new Provider();
		provider.setProviderCode(data.getProviderCode());
		provider.setProviderName(data.getProviderName());
		provider.setCreatedBy(principalService.getPrinciple().getId());

		File file = new File();
		file.setFileCodes(data.getFileCodes());
		file.setExtensions(data.getExtensions());
		file.setCreatedBy(principalService.getPrinciple().getId());
		file = fileDao.insert(file);
		provider.setFile(file);

		final Store store = storeDao.getById(data.getStoreId()).get();
		provider.setStore(store);

		final Provider providerInsert = providerDao.insert(provider);
		providerInsertDataResDto.setId(providerInsert.getId());
		providerInsertResDto.setData(providerInsertDataResDto);
		providerInsertResDto.setMessage(Message.INSERTED.getMessage());
		return providerInsertResDto;
	}

	public ProviderDataResDto getById(final Long id) {
		final Optional<Provider> providerOptional = providerDao.getById(id);
		final ProviderDataDto providerDataDto = new ProviderDataDto();
		if (providerOptional.isPresent()) {
			final Provider provider = providerOptional.get();
			providerDataDto.setProviderCode(provider.getProviderCode());
			providerDataDto.setProviderName(provider.getProviderName());
			providerDataDto.setFileId(provider.getFile().getId());
			providerDataDto.setStoreName(provider.getStore().getStoreName());
			providerDataDto.setIsActive(provider.getIsActive());
			providerDataDto.setProviderId(provider.getId());
			providerDataDto.setVersion(provider.getVersion());
		}
		final ProviderDataResDto providerDataResDto = new ProviderDataResDto();
		providerDataResDto.setData(providerDataDto);
		return providerDataResDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public ProviderUpdateResDto update(final ProviderUpdateReqDto data) {
		final Optional<Provider> providerOptional = providerDao.getById(data.getProviderId());
		final ProviderUpdateResDto providerUpdateResDto = new ProviderUpdateResDto();
		Provider providerUpdate = null;
		if (providerOptional.isPresent()) {
			providerUpdate = providerOptional.get();
			providerUpdate.setProviderName(data.getProviderName());
			providerUpdate.setIsActive(data.getIsActive());

			final Store store = storeDao.getById(data.getStoreId()).get();
			providerUpdate.setStore(store);

			final long oldFileId = providerUpdate.getFile().getId();
			File file = new File();
			file.setFileCodes(data.getFileCodes());
			file.setExtensions(data.getExtensions());
			file.setCreatedBy(principalService.getPrinciple().getId());
			file = fileDao.insert(file);
			providerUpdate.setFile(file);

			providerUpdate.setUpdatedBy(principalService.getPrinciple().getId());
			providerUpdate = providerDao.update(providerUpdate);
			fileDao.deleteById(oldFileId);
			data.setVersion(providerUpdate.getVersion());
			providerUpdateResDto.setData(data);
			providerUpdateResDto.setMessage(Message.UPDATED.getMessage());
		}
		return providerUpdateResDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public ProviderDeleteResDto deleteById(final Long id) {
		final Optional<Provider> providerOptional = providerDao.getById(id);
		final ProviderDeleteResDto providerDeleteResDto = new ProviderDeleteResDto();
		if (providerOptional.isPresent()) {
			final ProviderDeleteReqDto providerDeleteReqDto = new ProviderDeleteReqDto();
			providerDeleteReqDto.setProviderId(id);
			providerDeleteResDto.setMessage(Message.DELETED.getMessage());
			providerDao.deleteById(id);
		}
		return providerDeleteResDto;
	}

}
