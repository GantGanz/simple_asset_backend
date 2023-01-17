package com.test.simpleasset.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.simpleasset.constant.Message;
import com.test.simpleasset.dao.AssetDao;
import com.test.simpleasset.dao.CheckOutDao;
import com.test.simpleasset.dao.CheckOutDetailDao;
import com.test.simpleasset.dao.EmployeeDao;
import com.test.simpleasset.dto.checkout.CheckOutDataDto;
import com.test.simpleasset.dto.checkout.CheckOutInsertDataResDto;
import com.test.simpleasset.dto.checkout.CheckOutInsertReqDto;
import com.test.simpleasset.dto.checkout.CheckOutInsertResDto;
import com.test.simpleasset.dto.checkout.CheckOutsDto;
import com.test.simpleasset.dto.checkoutdetail.CheckOutDetailInsertReqDto;
import com.test.simpleasset.model.Asset;
import com.test.simpleasset.model.CheckOut;
import com.test.simpleasset.model.CheckOutDetail;
import com.test.simpleasset.model.Employee;
import com.test.simpleasset.util.RandomNumberGeneratorUtil;

@Service
public class CheckOutService{
	@Autowired
	private CheckOutDao checkOutDao;
	@Autowired
	private CheckOutDetailDao checkOutDetailDao;
	@Autowired
	private AssetDao assetDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private PrincipalService principalService;

	public CheckOutsDto getAll() {
		final List<CheckOut> checkOuts = checkOutDao.getAll();
		final List<CheckOutDataDto> dataDtos = new ArrayList<>();
		checkOuts.stream().forEach(checkOut -> {	
			final CheckOutDataDto checkOutDataDto = new CheckOutDataDto();
			checkOutDataDto.setTrxCode(checkOut.getTrxCode());
			checkOutDataDto.setCheckOutTime(checkOut.getTimeCheckOut());
			if (checkOut.getCheckOutLocation() != null) {
				checkOutDataDto.setCheckOutLocation(checkOut.getCheckOutLocation());
			} else if (checkOut.getAssetGeneral() != null) {
				checkOutDataDto.setAssetGeneralName(checkOut.getAssetGeneral().getAssetName());
			} else {
				checkOutDataDto.setEmployeeName(checkOut.getEmployee().getEmployeeName());
			}
			checkOutDataDto.setCheckOutId(checkOut.getId());
			checkOutDataDto.setVersion(checkOut.getVersion());
			dataDtos.add(checkOutDataDto);
		});
		final CheckOutsDto checkOutsDto = new CheckOutsDto();
		checkOutsDto.setData(dataDtos);

		return checkOutsDto;
	}

	public CheckOutsDto getAllUnchecked() {
		final List<CheckOut> checkOuts = checkOutDao.getAllUnchecked();
		final List<CheckOutDataDto> dataDtos = new ArrayList<>();
		checkOuts.stream().forEach(checkOut -> {	
			final CheckOutDataDto checkOutDataDto = new CheckOutDataDto();
			checkOutDataDto.setTrxCode(checkOut.getTrxCode());
			checkOutDataDto.setCheckOutTime(checkOut.getTimeCheckOut());
			if (checkOut.getCheckOutLocation() != null) {
				checkOutDataDto.setCheckOutLocation(checkOut.getCheckOutLocation());
			} else if (checkOut.getAssetGeneral() != null) {
				checkOutDataDto.setAssetGeneralName(checkOut.getAssetGeneral().getAssetName());
			} else {
				checkOutDataDto.setEmployeeName(checkOut.getEmployee().getEmployeeName());
			}
			checkOutDataDto.setCheckOutId(checkOut.getId());
			checkOutDataDto.setVersion(checkOut.getVersion());
			dataDtos.add(checkOutDataDto);
		});
		final CheckOutsDto checkOutsDto = new CheckOutsDto();
		checkOutsDto.setData(dataDtos);

		return checkOutsDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public CheckOutInsertResDto insertCheckOutDetails(final CheckOutInsertReqDto checkOut) {
		final CheckOutInsertResDto checkOutInsertResDto = new CheckOutInsertResDto();
		final CheckOutInsertDataResDto checkOutInsertDataResDto = new CheckOutInsertDataResDto();

		CheckOut checkOutInsert = new CheckOut();
		if (checkOut.getAssetGeneralId() != null && checkOut.getAssetGeneralId() != 0) {
			final Asset asset = assetDao.getById(checkOut.getAssetGeneralId()).get();
			checkOutInsert.setAssetGeneral(asset);
		} else if (checkOut.getEmployeeId() != null && checkOut.getEmployeeId() != 0) {
			final Employee employee = employeeDao.getById(checkOut.getEmployeeId()).get();
			checkOutInsert.setEmployee(employee);
		} else {
			checkOutInsert.setCheckOutLocation(checkOut.getCheckOutLocation());
		}
		checkOutInsert.setCreatedBy(principalService.getPrinciple().getId());
		final String rngValue = RandomNumberGeneratorUtil
				.givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect();
		checkOutInsert.setTrxCode(rngValue);
		checkOutInsert = checkOutDao.insert(checkOutInsert);

		final int checkOutDetailsSize = checkOut.getCheckOutDetails().size();
		for (int i = 0; i < checkOutDetailsSize; i++) {
			final CheckOutDetailInsertReqDto checkOutDetailInsert = checkOut.getCheckOutDetails().get(i);
			final CheckOutDetail checkOutDetail = new CheckOutDetail();

			final Asset asset = assetDao.getById(checkOutDetailInsert.getAssetId()).get();
			asset.setIsActive(false);
			assetDao.update(asset);
			checkOutDetail.setAsset(asset);

			checkOutDetail.setCheckOut(checkOutInsert);
			checkOutDetail.setReturnDate(checkOutDetailInsert.getReturnDate());
			checkOutDetail.setCreatedBy(principalService.getPrinciple().getId());
			checkOutDetailDao.insert(checkOutDetail);
		}
		checkOutInsertDataResDto.setId(checkOutInsert.getId());
		checkOutInsertResDto.setData(checkOutInsertDataResDto);
		checkOutInsertResDto.setMessage(Message.INSERTED.getMessage());
		return checkOutInsertResDto;
	}
}
