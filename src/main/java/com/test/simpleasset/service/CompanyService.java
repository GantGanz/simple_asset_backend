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
import com.test.simpleasset.dao.CompanyDao;
import com.test.simpleasset.dao.FileDao;
import com.test.simpleasset.dto.company.CompaniesDto;
import com.test.simpleasset.dto.company.CompanyDataDto;
import com.test.simpleasset.dto.company.CompanyDataResDto;
import com.test.simpleasset.dto.company.CompanyDeleteReqDto;
import com.test.simpleasset.dto.company.CompanyDeleteResDto;
import com.test.simpleasset.dto.company.CompanyInsertDataResDto;
import com.test.simpleasset.dto.company.CompanyInsertReqDto;
import com.test.simpleasset.dto.company.CompanyInsertResDto;
import com.test.simpleasset.dto.company.CompanyUpdateReqDto;
import com.test.simpleasset.dto.company.CompanyUpdateResDto;
import com.test.simpleasset.model.Company;
import com.test.simpleasset.model.File;

@Service
public class CompanyService {
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private PrincipalService principalService;

	public CompaniesDto getAll() {
		final List<Company> companies = companyDao.getAll();
		
		final Comparator<Company> compareByNameThenCode = Comparator
                .comparing(Company::getCompanyName)
                .thenComparing(Company::getCompanyCode);
		final Stream<Company> sortedCompanies = companies.stream()
            .sorted(compareByNameThenCode);

		final List<CompanyDataDto> dataDtos = new ArrayList<>();
		sortedCompanies.forEach(company -> {	
			final CompanyDataDto companyDataDto = new CompanyDataDto();
			companyDataDto.setCompanyCode(company.getCompanyCode());
			companyDataDto.setCompanyName(company.getCompanyName());
			companyDataDto.setFileId(company.getFile().getId());
			companyDataDto.setCompanyId(company.getId());
			companyDataDto.setVersion(company.getVersion());
			companyDataDto.setIsActive(company.getIsActive());
			dataDtos.add(companyDataDto);
		});
		final CompaniesDto companiesDto = new CompaniesDto();
		companiesDto.setData(dataDtos);

		return companiesDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public CompanyInsertResDto insert(final CompanyInsertReqDto data) {
		final CompanyInsertResDto companyInsertResDto = new CompanyInsertResDto();
		final CompanyInsertDataResDto companyInsertDataResDto = new CompanyInsertDataResDto();
		final Company company = new Company();
		company.setCompanyCode(data.getCompanyCode());
		company.setCompanyName(data.getCompanyName());
		company.setCreatedBy(principalService.getPrinciple().getId());
		company.setUpdatedBy(principalService.getPrinciple().getId());

		File file = new File();
		file.setFileCodes(data.getFileCodes());
		file.setExtensions(data.getExtensions());
		file.setCreatedBy(principalService.getPrinciple().getId());
		file.setUpdatedBy(principalService.getPrinciple().getId());
		file = fileDao.insert(file);
		company.setFile(file);

		final Company companyInsert = companyDao.insert(company);
		companyInsertDataResDto.setId(companyInsert.getId());
		companyInsertResDto.setData(companyInsertDataResDto);
		companyInsertResDto.setMessage(Message.INSERTED.getMessage());
		return companyInsertResDto;
	}

	public CompanyDataResDto getById(final Long id) {
		final Optional<Company> companyOptional = companyDao.getById(id);
		final CompanyDataDto companyDataDto = new CompanyDataDto();
		if (companyOptional.isPresent()) {
			final Company company = companyOptional.get();
			companyDataDto.setCompanyCode(company.getCompanyCode());
			companyDataDto.setCompanyName(company.getCompanyName());
			companyDataDto.setFileId(company.getFile().getId());
			companyDataDto.setCompanyId(company.getId());
			companyDataDto.setVersion(company.getVersion());
			companyDataDto.setIsActive(company.getIsActive());
		}
		final CompanyDataResDto companyDataResDto = new CompanyDataResDto();
		companyDataResDto.setData(companyDataDto);
		return companyDataResDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public CompanyUpdateResDto update(final CompanyUpdateReqDto data) {
		final Optional<Company> companyOptional = companyDao.getById(data.getCompanyId());
		final CompanyUpdateResDto companyUpdateResDto = new CompanyUpdateResDto();
		Company companyUpdate = null;
		if (companyOptional.isPresent()) {
			companyUpdate = companyOptional.get();
			companyUpdate.setCompanyName(data.getCompanyName());
			companyUpdate.setUpdatedBy(principalService.getPrinciple().getId());
			companyUpdate.setIsActive(data.getIsActive());

			final long oldFileId = companyUpdate.getFile().getId();
			File file = new File();
			file.setFileCodes(data.getFileCodes());
			file.setExtensions(data.getExtensions());
			file.setCreatedBy(principalService.getPrinciple().getId());
			file.setUpdatedBy(principalService.getPrinciple().getId());
			file = fileDao.insert(file);
			companyUpdate.setFile(file);

			companyUpdate = companyDao.update(companyUpdate);
			fileDao.deleteById(oldFileId);
			data.setVersion(companyUpdate.getVersion());
			companyUpdateResDto.setData(data);
			companyUpdateResDto.setMessage(Message.UPDATED.getMessage());
		}
		return companyUpdateResDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public CompanyDeleteResDto deleteById(final Long id) {
		final Optional<Company> companyOptional = companyDao.getById(id);
		final CompanyDeleteResDto companyDeleteResDto = new CompanyDeleteResDto();
		if (companyOptional.isPresent()) {
			final CompanyDeleteReqDto companyDeleteReqDto = new CompanyDeleteReqDto();
			companyDeleteReqDto.setCompanyId(id);
			companyDeleteResDto.setMessage(Message.DELETED.getMessage());
			companyDao.deleteById(id);
		}
		return companyDeleteResDto;
	}
}
