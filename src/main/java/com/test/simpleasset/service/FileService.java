package com.test.simpleasset.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.simpleasset.dao.FileDao;
import com.test.simpleasset.model.File;

@Service
public class FileService {
	@Autowired
	private FileDao fileDao;

	public Optional<File> getById(final Long id) {
		return fileDao.getById(id);
	}

}
