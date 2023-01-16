package com.test.simpleasset.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.AssetType;

@Repository
public class AssetTypeDao extends BaseDao{

	public AssetType insert(final AssetType data) {
		em.persist(data);

		return data;
	}

	public AssetType update(final AssetType data) {
		final AssetType assetTypeUpdated = em.merge(data);

		return assetTypeUpdated;
	}

	public Optional<AssetType> getById(final Long id) {
		final AssetType assetStatus = em.find(AssetType.class, id);

		final Optional<AssetType> assetTypeOptional = Optional.ofNullable(assetStatus);

		return assetTypeOptional;
	}

	public List<AssetType> getAll() {
		final String sql = " SELECT * FROM asset_type";

		@SuppressWarnings("unchecked")
		final List<AssetType> assetTypes = em.createNativeQuery(sql, AssetType.class).getResultList();
		return assetTypes;
	}

	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM asset_type WHERE id = :id ";

		final int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}
}
