package com.test.simpleasset.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.Provider;

@Repository
public class ProviderDao extends BaseDao{

	public Provider insert(final Provider data) {
		em.persist(data);

		return data;
	}

	public Provider update(final Provider data) {
		final Provider providerUpdated = em.merge(data);

		return providerUpdated;
	}

	public Optional<Provider> getById(final Long id) {
		final Provider assetStatus = em.find(Provider.class, id);

		final Optional<Provider> providerOptional = Optional.ofNullable(assetStatus);

		return providerOptional;
	}

	public List<Provider> getAll() {
		final String sql = " SELECT * FROM provider "
				+ "INNER JOIN store ON store.id = provider.store_id "
				+ "ORDER BY provider.id ";

		@SuppressWarnings("unchecked")
		final List<Provider> asset_types = em.createNativeQuery(sql, Provider.class).getResultList();
		return asset_types;
	}

	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM provider WHERE id = :id ";

		final int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}
}
