package com.test.simpleasset.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.Asset;
import com.test.simpleasset.model.CheckIn;
import com.test.simpleasset.model.CheckOut;
import com.test.simpleasset.model.Employee;

@Repository
public class CheckInDao extends BaseDao {


	public List<CheckIn> getAll() {
		final String sql = " SELECT ci.id, asset_general_id, asset_name, employee_id, employee_name, "
				+ "check_out_location, ci.created_by, ci.created_at, ci.updated_by, ci.updated_at, ci.ver, ci.is_active, "
				+ "co.trx_code, co.time_check_out "
				+ "FROM check_in ci INNER JOIN check_out co ON ci.check_out_id "
				+ "= co.id LEFT JOIN employee e ON co.employee_id = e.id "
				+ " LEFT JOIN asset a ON co.asset_general_id = a.id;";

		final List<?> result = em.createNativeQuery(sql).getResultList();

		final List<CheckIn> checkIns = new ArrayList<>();

		if (result != null && result.size() > 0) {
			result.forEach(checkInObj -> {
				final Object[] objArr = (Object[]) checkInObj;
				final CheckIn checkIn = new CheckIn();
				checkIn.setId(Long.valueOf(objArr[0].toString()));

				final CheckOut checkOut = new CheckOut();
				if (objArr[1] != null) {
					final Asset asset = new Asset();
					asset.setAssetName(objArr[2].toString());
					checkOut.setAssetGeneral(asset);
				}
				if (objArr[3] != null) {
					final Employee employee = new Employee();
					employee.setEmployeeName(objArr[4].toString());
					checkOut.setEmployee(employee);
				}
				if (objArr[5] != null) {
					checkOut.setCheckOutLocation(objArr[5].toString());
				}
				checkOut.setTrxCode(objArr[12].toString());
				checkOut.setTimeCheckOut(Timestamp.valueOf(objArr[13].toString()).toLocalDateTime());
				checkIn.setCheckOut(checkOut);

				checkIn.setCreatedBy(Long.valueOf(objArr[6].toString()));

				checkIn.setCreatedAt(Timestamp.valueOf(objArr[7].toString()).toLocalDateTime());
				if (objArr[8] != null) {
					checkIn.setUpdatedBy(Long.valueOf(objArr[8].toString()));
				}
				if (objArr[9] != null) {
					checkIn.setUpdatedAt(Timestamp.valueOf(objArr[9].toString()).toLocalDateTime());
				}
				checkIn.setVersion(Integer.valueOf(objArr[10].toString()));
				checkIn.setIsActive(Boolean.valueOf(objArr[11].toString()));
				checkIns.add(checkIn);
			});
		}

		return checkIns;
	}


	public CheckIn insert(final CheckIn data) {
		em.persist(data);

		return data;
	}


	public Optional<CheckIn> checkId(Long id) {
		final String sql = " SELECT ci " + "FROM CheckIn ci " + "WHERE ci.checkOut.id = :id ";
		CheckIn checkIn = null;
		try {
			checkIn = em.createQuery(sql, CheckIn.class).setParameter("id", id).getSingleResult();
			em.detach(checkIn);
		} catch (NoResultException nre) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		final Optional<CheckIn> checkInOptional = Optional.ofNullable(checkIn);
		return checkInOptional;
	}


	public CheckIn update(final CheckIn data) {
		final CheckIn checkInUpdated = em.merge(data);

		return checkInUpdated;
	}
	

	public Optional<CheckIn> getById(final Long id) {
		final CheckIn checkIn = em.find(CheckIn.class, id);
		em.detach(checkIn);
		final Optional<CheckIn> checkInOptional = Optional.ofNullable(checkIn);
		return checkInOptional;
	}
}