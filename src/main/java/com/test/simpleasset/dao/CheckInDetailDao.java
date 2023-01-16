package com.test.simpleasset.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.CheckInDetail;

@Repository
public class CheckInDetailDao extends BaseDao{
	
	public CheckInDetail insert(final CheckInDetail data) {
		em.persist(data);

		return data;
	}

	public List<CheckInDetail> getAllCheckedIn(final Long checkInId) {
		final String sql = " SELECT * " 
				+ "FROM check_in_detail cid "
				+ "INNER JOIN check_in ci ON ci.id = cid.check_in_id " 
				+ "WHERE ci.id = :id ";
		
		@SuppressWarnings("unchecked")
		final List<CheckInDetail> checkInDetail = em.createNativeQuery(sql, CheckInDetail.class)
				.setParameter("id", checkInId)
				.getResultList();
		return checkInDetail;
	}
}