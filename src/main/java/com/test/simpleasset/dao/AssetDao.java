package com.test.simpleasset.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.Asset;

@Repository
public class AssetDao extends BaseDao{

	public Asset insert(final Asset data) {
		em.persist(data);

		return data;
	}

	public Asset update(final Asset data) {
		final Asset assetUpdated = em.merge(data);

		return assetUpdated;
	}

	public Optional<Asset> getById(final Long id) {
		final Asset asset = em.find(Asset.class, id);

		final Optional<Asset> assetOptional = Optional.ofNullable(asset);

		return assetOptional;
	}

	public List<Asset> getAll() {
		final String sql = " SELECT * FROM asset";

		@SuppressWarnings("unchecked")
		final List<Asset> assetStatuses = em.createNativeQuery(sql, Asset.class).getResultList();
		return assetStatuses;
	}

	public List<Asset> getAllDeployable(){
		final String sql = " SELECT * FROM asset a "
				+ "INNER JOIN asset_status ast ON ast.id = a.asset_status_id "
				+ "INNER JOIN asset_type at ON at.id = a.asset_type_id "
				+ "WHERE ast.asset_status_code = 'dep' "
				+ "AND a.is_active = true "
				+ "ORDER BY a.id";

		@SuppressWarnings("unchecked")
		final List<Asset> assets = em.createNativeQuery(sql, Asset.class).getResultList();
		return assets;
	}
	
	public List<Asset> getAllDeployableGeneral(){
		final String sql = " SELECT * FROM asset a "
				+ "INNER JOIN asset_status ast ON ast.id = a.asset_status_id "
				+ "INNER JOIN asset_type at ON at.id = a.asset_type_id "
				+ "WHERE at.asset_type_code = 'gen' AND ast.asset_status_code = 'dep' "
				+ "AND a.is_active = true "+ "ORDER BY a.id";

		@SuppressWarnings("unchecked")
		final List<Asset> assets = em.createNativeQuery(sql, Asset.class).getResultList();
		return assets;
	}
	
	public List<Asset> getAllDeployableComponent(){
		final String sql = " SELECT * FROM asset a "
				+ "INNER JOIN asset_status ast ON ast.id = a.asset_status_id "
				+ "INNER JOIN asset_type at ON at.id = a.asset_type_id "
				+ "WHERE at.asset_type_code = 'com' AND ast.asset_status_code = 'dep' "
				+ "AND a.is_active = true "+ "ORDER BY a.id";

		@SuppressWarnings("unchecked")
		final List<Asset> assets = em.createNativeQuery(sql, Asset.class).getResultList();
		return assets;
	}
	
	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM asset WHERE id = :id ";

		final int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}
}
