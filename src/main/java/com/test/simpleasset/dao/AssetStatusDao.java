package com.test.simpleasset.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.AssetStatus;

@Repository
public class AssetStatusDao extends BaseDao{

	public AssetStatus insert(final AssetStatus data) {
		em.persist(data);

		return data;
	}

	public AssetStatus update(final AssetStatus data) {
		final AssetStatus assetStatusUpdated = em.merge(data);

		return assetStatusUpdated;
	}

	public Optional<AssetStatus> getById(final Long id) {
		final AssetStatus assetStatus = em.find(AssetStatus.class, id);

		final Optional<AssetStatus> assetStatusOptional = Optional.ofNullable(assetStatus);

		return assetStatusOptional;
	}


	public List<AssetStatus> getAll() {
		final String sql = " SELECT * FROM asset_status";

		@SuppressWarnings("unchecked")
		final List<AssetStatus> assetStatuses = em.createNativeQuery(sql, AssetStatus.class).getResultList();
		return assetStatuses;
	}


	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM asset_status WHERE id = :id ";

		final int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}
}
