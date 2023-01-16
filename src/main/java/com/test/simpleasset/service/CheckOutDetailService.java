package com.test.simpleasset.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.simpleasset.dao.CheckOutDetailDao;
import com.test.simpleasset.dto.checkoutdetail.CheckOutDetailDataDto;
import com.test.simpleasset.dto.checkoutdetail.CheckOutDetailDataResDto;
import com.test.simpleasset.dto.checkoutdetail.CheckOutDetailsDto;
import com.test.simpleasset.model.CheckOutDetail;

@Service
public class CheckOutDetailService {

	@Autowired
	private CheckOutDetailDao checkOutDetailDao;

	public CheckOutDetailsDto getAllById(final Long selectedId) {
		final List<CheckOutDetail> checkOutDetails = checkOutDetailDao.getAllById(selectedId);
		final List<CheckOutDetailDataDto> dataDtos = new ArrayList<>();
		checkOutDetails.stream().forEach(checkOutDetail -> {			
			final CheckOutDetailDataDto checkOutDetailDataDto = new CheckOutDetailDataDto();
			checkOutDetailDataDto.setTrxCode(checkOutDetail.getCheckOut().getTrxCode());
			checkOutDetailDataDto.setAssetName(checkOutDetail.getAsset().getAssetName());
			checkOutDetailDataDto.setReturnDate(checkOutDetail.getReturnDate());
			checkOutDetailDataDto.setVersion(checkOutDetail.getVersion());
			checkOutDetailDataDto.setCheckOutDetailId(checkOutDetail.getId());
			dataDtos.add(checkOutDetailDataDto);
		});
		final CheckOutDetailsDto checkOutDetailsDto = new CheckOutDetailsDto();
		checkOutDetailsDto.setData(dataDtos);

		return checkOutDetailsDto;
	}

	public CheckOutDetailsDto getAllNotCheckIn() {
		final List<CheckOutDetail> checkOutDetails = checkOutDetailDao.getAllNotCheckIn();
		final List<CheckOutDetailDataDto> dataDtos = new ArrayList<>();
		checkOutDetails.stream().forEach(checkOutDetail -> {	
			final CheckOutDetailDataDto checkOutDetailDataDto = new CheckOutDetailDataDto();
			checkOutDetailDataDto.setTrxCode(checkOutDetail.getCheckOut().getTrxCode());
			checkOutDetailDataDto.setAssetName(checkOutDetail.getAsset().getAssetName());
			checkOutDetailDataDto.setReturnDate(checkOutDetail.getReturnDate());
			checkOutDetailDataDto.setVersion(checkOutDetail.getVersion());
			checkOutDetailDataDto.setCheckOutDetailId(checkOutDetail.getId());
			dataDtos.add(checkOutDetailDataDto);
		});		
		
		final CheckOutDetailsDto checkOutDetailsDto = new CheckOutDetailsDto();
		checkOutDetailsDto.setData(dataDtos);

		return checkOutDetailsDto;
	}

	public CheckOutDetailDataResDto getById(Long id) {
		final Optional<CheckOutDetail> checkOutDetailOptional = checkOutDetailDao.getById(id);
		final CheckOutDetailDataDto checkOutDetailDataDto = new CheckOutDetailDataDto();
		if (checkOutDetailOptional.isPresent()) {
			final CheckOutDetail checkOutDetail = checkOutDetailOptional.get();
			checkOutDetailDataDto.setTrxCode(checkOutDetail.getCheckOut().getTrxCode());
			checkOutDetailDataDto.setAssetName(checkOutDetail.getAsset().getAssetName());
			checkOutDetailDataDto.setReturnDate(checkOutDetail.getReturnDate());
			checkOutDetailDataDto.setVersion(checkOutDetail.getVersion());
			checkOutDetailDataDto.setCheckOutDetailId(checkOutDetail.getId());
		}
		final CheckOutDetailDataResDto checkOutDetailDataResDto = new CheckOutDetailDataResDto();
		checkOutDetailDataResDto.setData(checkOutDetailDataDto);
		return checkOutDetailDataResDto;
	}

	public CheckOutDetailsDto getAllNotCheckInById(final Long id) {
		final List<CheckOutDetail> checkOutDetails = checkOutDetailDao.getAllNotCheckInById(id);
		final List<CheckOutDetailDataDto> dataDtos = new ArrayList<>();
		checkOutDetails.stream().forEach(checkOutDetail -> {	
			final CheckOutDetailDataDto checkOutDetailDataDto = new CheckOutDetailDataDto();
			checkOutDetailDataDto.setTrxCode(checkOutDetail.getCheckOut().getTrxCode());
			checkOutDetailDataDto.setAssetName(checkOutDetail.getAsset().getAssetName());
			checkOutDetailDataDto.setReturnDate(checkOutDetail.getReturnDate());
			checkOutDetailDataDto.setVersion(checkOutDetail.getVersion());
			checkOutDetailDataDto.setCheckOutDetailId(checkOutDetail.getId());
			dataDtos.add(checkOutDetailDataDto);
		});		
		final CheckOutDetailsDto checkOutDetailsDto = new CheckOutDetailsDto();
		checkOutDetailsDto.setData(dataDtos);

		return checkOutDetailsDto;
	}

}
