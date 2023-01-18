package com.test.simpleasset.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.Asset;
import com.test.simpleasset.model.CheckOut;
import com.test.simpleasset.model.Employee;

@Repository
public class CheckOutDao extends BaseDao{


	public List<CheckOut> getAll() {
		final String sql = " SELECT co.id, asset_general_id, asset_name, employee_id, employee_name, "
				+ "check_out_location, trx_code, time_check_out, co.created_by, co.updated_by, "
				+ "co.ver, co.is_active "
				+ "FROM check_out co LEFT JOIN employee e ON co.employee_id = e.id "
				+ "LEFT JOIN asset a ON co.asset_general_id = a.id ";
		
		final List<?> result = em.createNativeQuery(sql).getResultList();

		final List<CheckOut> checkOuts = new ArrayList<>();

		if (result != null && result.size() > 0) {
			result.forEach(checkOutObj -> {
				final Object[] objArr = (Object[]) checkOutObj;
				final CheckOut checkOut = new CheckOut();
				checkOut.setId(Long.valueOf(objArr[0].toString()));

				if(objArr[1] != null) {
					final Asset asset = new Asset();
					asset.setAssetName(objArr[2].toString());
					checkOut.setAssetGeneral(asset);				
				}
				if(objArr[3] != null) {
					final Employee employee = new Employee();
					employee.setEmployeeName(objArr[4].toString());
					checkOut.setEmployee(employee);				
				}
				if(objArr[5] != null) {
					checkOut.setCheckOutLocation(objArr[5].toString());				
				}
				checkOut.setTrxCode(objArr[6].toString());
				final LocalDateTime dateTime = Timestamp.valueOf(objArr[7].toString()).toLocalDateTime();
				checkOut.setTimeCheckOut(dateTime);
				 
				checkOut.setCreatedBy(Long.valueOf(objArr[8].toString()));
				if(objArr[9] != null) {				
					checkOut.setUpdatedBy(Long.valueOf(objArr[9].toString()));
				}else {
					checkOut.setUpdatedBy(null);
				}
				checkOut.setVersion(Integer.valueOf(objArr[10].toString()));
				checkOut.setIsActive(Boolean.valueOf(objArr[11].toString()));
				checkOuts.add(checkOut);
			});
		}

		return checkOuts;
	}


	public List<CheckOut> getAllUnchecked() {
		final String sql = " SELECT DISTINCT co.id, asset_general_id, asset_name, employee_id, employee_name, "
				+ "check_out_location, trx_code, time_check_out, co.created_by, co.updated_by, "
				+ "co.ver, co.is_active "
				+ "FROM check_out co "
				+ "LEFT JOIN employee e ON co.employee_id = e.id "
				+ "LEFT JOIN asset a ON co.asset_general_id = a.id "
				+ "INNER JOIN check_out_detail cod ON cod.check_out_id = co.id "
				+ "LEFT JOIN check_in_detail cid ON cod.id = cid.check_out_detail_id "
				+ "WHERE cid.id IS NULL "
				+ "ORDER BY co.id "
				+ ";";
		
		final List<?> result = em.createNativeQuery(sql).getResultList();

		final List<CheckOut> checkOuts = new ArrayList<>();

		if (result != null && result.size() > 0) {
			result.forEach(checkOutObj -> {
				final Object[] objArr = (Object[]) checkOutObj;
				final CheckOut checkOut = new CheckOut();
				checkOut.setId(Long.valueOf(objArr[0].toString()));

				if(objArr[1] != null) {
					final Asset asset = new Asset();
					asset.setAssetName(objArr[2].toString());
					checkOut.setAssetGeneral(asset);				
				}
				if(objArr[3] != null) {
					final Employee employee = new Employee();
					employee.setEmployeeName(objArr[4].toString());
					checkOut.setEmployee(employee);				
				}
				if(objArr[5] != null) {
					checkOut.setCheckOutLocation(objArr[5].toString());				
				}
				checkOut.setTrxCode(objArr[6].toString());
				final LocalDateTime dateTime = Timestamp.valueOf(objArr[7].toString()).toLocalDateTime();
				checkOut.setTimeCheckOut(dateTime);
				 
				checkOut.setCreatedBy(Long.valueOf(objArr[8].toString()));
				if(objArr[9] != null) {				
					checkOut.setUpdatedBy(Long.valueOf(objArr[9].toString()));
				}else {
					checkOut.setUpdatedBy(null);
				}
				checkOut.setVersion(Integer.valueOf(objArr[10].toString()));
				checkOut.setIsActive(Boolean.valueOf(objArr[11].toString()));
				checkOuts.add(checkOut);
			});
		}

		return checkOuts;
	}
	

	public CheckOut insert(final CheckOut data) {
		em.persist(data);

		return data;
	}


	public Optional<CheckOut> getById(Long id) {
		final CheckOut checkOut = em.find(CheckOut.class, id);
		em.detach(checkOut);
		final Optional<CheckOut> checkOutOptional = Optional.ofNullable(checkOut);

		return checkOutOptional;
	}
}