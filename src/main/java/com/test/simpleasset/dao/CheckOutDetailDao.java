package com.test.simpleasset.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.Asset;
import com.test.simpleasset.model.CheckOut;
import com.test.simpleasset.model.CheckOutDetail;


@Repository
public class CheckOutDetailDao extends BaseDao{


	public CheckOutDetail insert(final CheckOutDetail data) {
		em.persist(data);

		return data;
	}


	public List<CheckOutDetail> getAllById(final Long selectedId) {
		final String sql = " SELECT cod.id, return_date, asset_name, cod.created_by, cod.created_at, "
				+ "cod.updated_by, cod.updated_at, cod.ver, cod.is_active, co.trx_code "
				+ "FROM check_out_detail cod INNER JOIN asset a ON a.id = cod.asset_id "
				+ "INNER JOIN check_out co ON co.id = cod.check_out_id "
				+ "WHERE check_out_id = :selectedId "
				+ "ORDER BY cod.id ";

		final List<?> result = em.createNativeQuery(sql)
				.setParameter("selectedId", selectedId)
				.getResultList();
		final List<CheckOutDetail> checkOutDetails = new ArrayList<>();

		if (result != null && result.size() > 0) {
			result.forEach(checkInObj -> {
				final Object[] objArr = (Object[]) checkInObj;
				final CheckOutDetail checkOutDetail = new CheckOutDetail();
				checkOutDetail.setId(Long.valueOf(objArr[0].toString()));
				if (objArr[1] != null) {
					checkOutDetail.setReturnDate(Date.valueOf(objArr[1].toString()).toLocalDate());
				}
				final Asset asset = new Asset();
				asset.setAssetName(objArr[2].toString());
				checkOutDetail.setAsset(asset);
				checkOutDetail.setCreatedBy(Long.valueOf(objArr[3].toString()));
				checkOutDetail.setCreatedAt(Timestamp.valueOf(objArr[4].toString()).toLocalDateTime());
				if (objArr[5] != null) {
					checkOutDetail.setUpdatedBy(Long.valueOf(objArr[5].toString()));
				}
				if (objArr[6] != null) {
					checkOutDetail.setUpdatedAt(Timestamp.valueOf(objArr[6].toString()).toLocalDateTime());
				}
				checkOutDetail.setVersion(Integer.valueOf(objArr[7].toString()));
				checkOutDetail.setIsActive(Boolean.valueOf(objArr[8].toString()));
				final CheckOut checkOut = new CheckOut();
				checkOut.setTrxCode(objArr[9].toString());
				checkOutDetail.setCheckOut(checkOut);
				checkOutDetails.add(checkOutDetail);
			});
		}
		return checkOutDetails;
	}


	public List<CheckOutDetail> getAllNotCheckIn() {
		final String sql = " SELECT cod.created_at, a.asset_name, cod.return_date, cod.created_by, cod.id, "
				+ "cod.updated_by, cod.updated_at, cod.ver, cod.is_active, co.trx_code "
				+ "FROM check_in_detail cid RIGHT JOIN check_out_detail cod ON cid.check_out_detail_id = cod.id "
				+ "INNER JOIN asset a ON a.id = cod.asset_id " 
				+ "INNER JOIN check_out co ON co.id = cod.check_out_id "
				+ "WHERE cid.id IS NULL ORDER BY cid.id; ";
		final List<?> result = em.createNativeQuery(sql).getResultList();
		final List<CheckOutDetail> checkOutDetails = new ArrayList<>();

		if (result != null && result.size() > 0) {
			result.forEach(checkInObj -> {
				final Object[] objArr = (Object[]) checkInObj;
				final CheckOutDetail checkOutDetail = new CheckOutDetail();
				checkOutDetail.setCreatedAt(Timestamp.valueOf(objArr[0].toString()).toLocalDateTime());

				final Asset asset = new Asset();
				asset.setAssetName(objArr[1].toString());
				checkOutDetail.setAsset(asset);
				if (objArr[2] != null) {
					checkOutDetail.setReturnDate(Date.valueOf(objArr[2].toString()).toLocalDate());
				}

				checkOutDetail.setCreatedBy(Long.valueOf(objArr[3].toString()));
				checkOutDetail.setId(Long.valueOf(objArr[4].toString()));
				if (objArr[5] != null) {
					checkOutDetail.setUpdatedBy(Long.valueOf(objArr[5].toString()));
				}
				if (objArr[6] != null) {
					checkOutDetail.setUpdatedAt(Timestamp.valueOf(objArr[6].toString()).toLocalDateTime());
				}
				checkOutDetail.setVersion(Integer.valueOf(objArr[7].toString()));
				checkOutDetail.setIsActive(Boolean.valueOf(objArr[8].toString()));

				final CheckOut checkOut = new CheckOut();
				checkOut.setTrxCode(objArr[9].toString());
				checkOutDetail.setCheckOut(checkOut);

				checkOutDetails.add(checkOutDetail);
			});
		}
		return checkOutDetails;
	}


	public Optional<CheckOutDetail> getById(final Long id) {
		final String sql = " SELECT cod.id, return_date, asset_name, cod.created_by, cod.created_at, "
				+ "cod.updated_by, cod.updated_at, cod.ver, cod.is_active, cod.check_out_id, cod.asset_id "
				+ "FROM check_out_detail cod INNER JOIN asset a ON a.id = cod.asset_id " 
				+ "WHERE cod.id = :id "
				+ "ORDER BY cod.id ";
		
		final List<?> result = em.createNativeQuery(sql).setParameter("id", id).getResultList();
		final List<CheckOutDetail> checkOutDetails = new ArrayList<>();
		if (result != null && result.size() > 0) {
			result.forEach(checkOutDetailObj -> {
				final Object[] objArr = (Object[]) checkOutDetailObj;
				final CheckOutDetail checkOutDetail = new CheckOutDetail();
				checkOutDetail.setId(Long.valueOf(objArr[0].toString()));

				if (objArr[1] != null) {
					checkOutDetail.setReturnDate(Date.valueOf(objArr[1].toString()).toLocalDate());
				}
				final Asset asset = new Asset();
				asset.setAssetName(objArr[2].toString());
				asset.setId(Long.valueOf(objArr[10].toString()));
				checkOutDetail.setAsset(asset);
				checkOutDetail.setCreatedBy(Long.valueOf(objArr[3].toString()));
				checkOutDetail.setCreatedAt(Timestamp.valueOf(objArr[4].toString()).toLocalDateTime());
				if (objArr[5] != null) {
					checkOutDetail.setUpdatedBy(Long.valueOf(objArr[5].toString()));
				}
				if (objArr[6] != null) {
					checkOutDetail.setUpdatedAt(Timestamp.valueOf(objArr[6].toString()).toLocalDateTime());
				}
				checkOutDetail.setVersion(Integer.valueOf(objArr[7].toString()));
				checkOutDetail.setIsActive(Boolean.valueOf(objArr[8].toString()));
				
				final CheckOut checkOut = new CheckOut();
				checkOut.setId(Long.valueOf(objArr[9].toString()));
				checkOutDetail.setCheckOut(checkOut);

				checkOutDetails.add(checkOutDetail);
			});
		}
		final CheckOutDetail checkOutDetailFirst = checkOutDetails.get(0);
		final Optional<CheckOutDetail> checkOutDetailOptional = Optional.ofNullable(checkOutDetailFirst);
		return checkOutDetailOptional;
	}


	public List<CheckOutDetail> getAllNotCheckInById(final Long id) {
		final String sql = " SELECT cod.created_at, a.asset_name, cod.return_date, cod.created_by, cod.id, "
				+ "cod.updated_by, cod.updated_at, cod.ver, cod.is_active, cod.check_out_id, co.trx_code "
				+ "FROM check_in_detail cid RIGHT JOIN check_out_detail cod ON cid.check_out_detail_id = cod.id "
				+ "INNER JOIN asset a ON a.id = cod.asset_id " 
				+ "INNER JOIN check_out co ON co.id = cod.check_out_id "
				+ "WHERE cid.id IS NULL AND co.id = :id " 
				+ "ORDER BY cid.id; ";
		final List<?> result = em.createNativeQuery(sql).setParameter("id", id).getResultList();
		final List<CheckOutDetail> checkOutDetails = new ArrayList<>();

		if (result != null && result.size() > 0) {
			result.forEach(checkInObj -> {
				final Object[] objArr = (Object[]) checkInObj;
				final CheckOutDetail checkOutDetail = new CheckOutDetail();
				checkOutDetail.setCreatedAt(Timestamp.valueOf(objArr[0].toString()).toLocalDateTime());

				final Asset asset = new Asset();
				asset.setAssetName(objArr[1].toString());
				checkOutDetail.setAsset(asset);
				if (objArr[2] != null) {
					checkOutDetail.setReturnDate(Date.valueOf(objArr[2].toString()).toLocalDate());
				}
				checkOutDetail.setCreatedBy(Long.valueOf(objArr[3].toString()));
				checkOutDetail.setId(Long.valueOf(objArr[4].toString()));
				if (objArr[5] != null) {
					checkOutDetail.setUpdatedBy(Long.valueOf(objArr[5].toString()));
				}
				if (objArr[6] != null) {
					checkOutDetail.setUpdatedAt(Timestamp.valueOf(objArr[6].toString()).toLocalDateTime());
				}
				checkOutDetail.setVersion(Integer.valueOf(objArr[7].toString()));
				checkOutDetail.setIsActive(Boolean.valueOf(objArr[8].toString()));

				final CheckOut checkOut = new CheckOut();
				checkOut.setId(Long.valueOf(objArr[9].toString()));
				checkOut.setTrxCode(objArr[10].toString());
				checkOutDetail.setCheckOut(checkOut);

				checkOutDetails.add(checkOutDetail);
			});
		}
		return checkOutDetails;
	}
}