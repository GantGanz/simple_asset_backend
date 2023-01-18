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
import com.test.simpleasset.dao.FileDao;
import com.test.simpleasset.dao.StoreDao;
import com.test.simpleasset.dto.store.StoreDataDto;
import com.test.simpleasset.dto.store.StoreDataResDto;
import com.test.simpleasset.dto.store.StoreDeleteReqDto;
import com.test.simpleasset.dto.store.StoreDeleteResDto;
import com.test.simpleasset.dto.store.StoreInsertDataResDto;
import com.test.simpleasset.dto.store.StoreInsertReqDto;
import com.test.simpleasset.dto.store.StoreInsertResDto;
import com.test.simpleasset.dto.store.StoreUpdateReqDto;
import com.test.simpleasset.dto.store.StoreUpdateResDto;
import com.test.simpleasset.dto.store.StoresDto;
import com.test.simpleasset.model.File;
import com.test.simpleasset.model.Store;

@Service
public class StoreService {
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private PrincipalService principalService;

	public StoresDto getAll() {
		final List<Store> stores = storeDao.getAll();
		
		final Comparator<Store> compareByNameThenCode = Comparator
                .comparing(Store::getStoreName)
                .thenComparing(Store::getStoreCode);
		final Stream<Store> sortedStores = stores.stream()
            .sorted(compareByNameThenCode);
		
		final List<StoreDataDto> dataDtos = new ArrayList<>();
		sortedStores.forEach(store -> {	
			final StoreDataDto storeDataDto = new StoreDataDto();
			storeDataDto.setStoreCode(store.getStoreCode());
			storeDataDto.setStoreName(store.getStoreName());
			storeDataDto.setFileId(store.getFile().getId());
			storeDataDto.setStoreId(store.getId());
			storeDataDto.setVersion(store.getVersion());
			storeDataDto.setIsActive(store.getIsActive());
			dataDtos.add(storeDataDto);
		});
		final StoresDto storesDto = new StoresDto();
		storesDto.setData(dataDtos);

		return storesDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public StoreInsertResDto insert(final StoreInsertReqDto data) {
		final StoreInsertResDto storeInsertResDto = new StoreInsertResDto();
		final StoreInsertDataResDto storeInsertDataResDto = new StoreInsertDataResDto();
		final Store store = new Store();
		store.setStoreCode(data.getStoreCode());
		store.setStoreName(data.getStoreName());
		store.setCreatedBy(principalService.getPrinciple().getId());
		store.setUpdatedBy(null);

		File file = new File();
		file.setFileCodes(data.getFileCodes());
		file.setExtensions(data.getExtensions());
		file.setCreatedBy(principalService.getPrinciple().getId());
		file.setUpdatedBy(null);
		file = fileDao.insert(file);
		store.setFile(file);
		final Store storeInsert = storeDao.insert(store);

		storeInsertDataResDto.setId(storeInsert.getId());
		storeInsertResDto.setData(storeInsertDataResDto);
		storeInsertResDto.setMessage(Message.INSERTED.getMessage());
		return storeInsertResDto;
	}

	public StoreDataResDto getById(final Long id) {
		final Optional<Store> storeOptional = storeDao.getById(id);
		final StoreDataDto storeDataDto = new StoreDataDto();
		if (storeOptional.isPresent()) {
			final Store store = storeOptional.get();
			storeDataDto.setStoreCode(store.getStoreCode());
			storeDataDto.setStoreName(store.getStoreName());
			storeDataDto.setFileId(store.getFile().getId());
			storeDataDto.setIsActive(store.getIsActive());
			storeDataDto.setStoreId(store.getId());
			storeDataDto.setVersion(store.getVersion());
		}
		final StoreDataResDto storeDataResDto = new StoreDataResDto();
		storeDataResDto.setData(storeDataDto);
		return storeDataResDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public StoreUpdateResDto update(final StoreUpdateReqDto data) {
		final Optional<Store> storeOptional = storeDao.getById(data.getStoreId());
		final StoreUpdateResDto storeUpdateResDto = new StoreUpdateResDto();
		Store storeUpdate = null;
		if (storeOptional.isPresent()) {
			storeUpdate = storeOptional.get();
			storeUpdate.setStoreName(data.getStoreName());
			storeUpdate.setIsActive(data.getIsActive());

			final long oldFileId = storeUpdate.getFile().getId();
			File file = new File();
			file.setFileCodes(data.getFileCodes());
			file.setExtensions(data.getExtensions());
			file.setCreatedBy(principalService.getPrinciple().getId());
			file = fileDao.insert(file);
			storeUpdate.setFile(file);

			storeUpdate.setUpdatedBy(principalService.getPrinciple().getId());
			storeUpdate = storeDao.update(storeUpdate);
			fileDao.deleteById(oldFileId);
			data.setVersion(storeUpdate.getVersion());
			storeUpdateResDto.setData(data);
			storeUpdateResDto.setMessage(Message.UPDATED.getMessage());
		}
		return storeUpdateResDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public StoreDeleteResDto deleteById(final Long id) {
		final Optional<Store> storeOptional = storeDao.getById(id);
		final StoreDeleteResDto storeDeleteResDto = new StoreDeleteResDto();
		if (storeOptional.isPresent()) {
			final StoreDeleteReqDto storeDeleteReqDto = new StoreDeleteReqDto();
			storeDeleteReqDto.setStoreId(id);
			storeDeleteResDto.setMessage(Message.DELETED.getMessage());
			storeDao.deleteById(id);
		}
		return storeDeleteResDto;
	}
}
