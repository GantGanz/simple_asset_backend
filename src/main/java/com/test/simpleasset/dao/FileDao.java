package com.test.simpleasset.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.File;

@Repository
public class FileDao extends BaseDao{

	public File insert(final File data){
		em.persist(data);

		return data;
	}

	public File update(final File data){
		final File fileUpdated = em.merge(data);

		return fileUpdated;
	}

	public Optional<File> getById(final Long id){
		final File file = em.find(File.class, id);

		final Optional<File> fileOptional = Optional.ofNullable(file);

		return fileOptional;
	}

	public List<File> getAll(){
		final String sql = " SELECT * FROM file";

		@SuppressWarnings("unchecked")
		final List<File> files = em.createNativeQuery(sql, File.class).getResultList();
		return files;
	}

	public boolean deleteById(final Long id){
		final String sql = " DELETE FROM file WHERE id = :id ";

		final int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}
}
