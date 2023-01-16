package com.test.simpleasset.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.Store;

@Repository
public class StoreDao extends BaseDao{


	public Store insert(final Store data) {
		em.persist(data);

		return data;
	}


	public Store update(final Store data) {
		final Store storeUpdated = em.merge(data);

		return storeUpdated;
	}


	public Optional<Store> getById(final Long id) {
		final Store assetStatus = em.find(Store.class, id);

		final Optional<Store> storeOptional = Optional.ofNullable(assetStatus);

		return storeOptional;
	}


	public List<Store> getAll() {
		final String sql = " SELECT * FROM store";

		@SuppressWarnings("unchecked")
		final List<Store> assetTypes = em.createNativeQuery(sql, Store.class).getResultList();
		return assetTypes;
	}


	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM store WHERE id = :id ";

		final int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}
}
