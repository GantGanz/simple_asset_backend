package com.test.simpleasset.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.simpleasset.constant.Message;
import com.test.simpleasset.dao.AssetDao;
import com.test.simpleasset.dao.AssetStatusDao;
import com.test.simpleasset.dao.CheckInDao;
import com.test.simpleasset.dao.CheckInDetailDao;
import com.test.simpleasset.dao.CheckOutDao;
import com.test.simpleasset.dao.CheckOutDetailDao;
import com.test.simpleasset.dto.checkin.CheckInDataDto;
import com.test.simpleasset.dto.checkin.CheckInInsertDataResDto;
import com.test.simpleasset.dto.checkin.CheckInInsertReqDto;
import com.test.simpleasset.dto.checkin.CheckInInsertResDto;
import com.test.simpleasset.dto.checkin.CheckInsDto;
import com.test.simpleasset.dto.checkindetail.CheckInDetailInsertReqDto;
import com.test.simpleasset.model.Asset;
import com.test.simpleasset.model.AssetStatus;
import com.test.simpleasset.model.CheckIn;
import com.test.simpleasset.model.CheckInDetail;
import com.test.simpleasset.model.CheckOut;
import com.test.simpleasset.model.CheckOutDetail;

@Service
public class CheckInService {

	@Autowired
	private CheckInDao checkInDao;
	@Autowired
	private CheckOutDao checkOutDao;
	@Autowired
	private CheckInDetailDao checkInDetailDao;
	@Autowired
	private CheckOutDetailDao checkOutDetailDao;
	@Autowired
	private AssetDao assetDao;
	@Autowired
	private AssetStatusDao assetStatusDao;
	@Autowired
	private PrincipalService principalService;

	public CheckInsDto getAll() {
		final List<CheckIn> checkIns = checkInDao.getAll();
		
		final Comparator<CheckIn> compareByLastCheckInTime = Comparator
                .comparing(CheckIn::getUpdatedAt)
				.thenComparing(CheckIn::getCreatedAt).reversed();
		final Stream<CheckIn> sortedCheckIns = checkIns.stream()
				.sorted(compareByLastCheckInTime);

		final List<CheckInDataDto> dataDtos = new ArrayList<>();
		sortedCheckIns.forEach(checkIn -> {
			final CheckInDataDto checkInDataDto = new CheckInDataDto();
			checkInDataDto.setTrxCode(checkIn.getCheckOut().getTrxCode());
			checkInDataDto.setCheckOutTime(checkIn.getCheckOut().getTimeCheckOut());
			if (checkIn.getCheckOut().getCheckOutLocation() != null) {
				checkInDataDto.setCheckOutLocation(checkIn.getCheckOut().getCheckOutLocation());
			} else if (checkIn.getCheckOut().getAssetGeneral() != null) {
				checkInDataDto.setAssetGeneralName(checkIn.getCheckOut().getAssetGeneral().getAssetName());
			} else {
				checkInDataDto.setEmployeeName(checkIn.getCheckOut().getEmployee().getEmployeeName());
			}
			checkInDataDto.setCreatedAt(checkIn.getCreatedAt());
			checkInDataDto.setUpdatedAt(checkIn.getUpdatedAt());
			checkInDataDto.setCheckInId(checkIn.getId());
			checkInDataDto.setVersion(checkIn.getVersion());
			dataDtos.add(checkInDataDto);
		});
		final CheckInsDto checkInsDto = new CheckInsDto();
		checkInsDto.setData(dataDtos);

		return checkInsDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public CheckInInsertResDto insertCheckInDetails(final CheckInInsertReqDto checkIn) {
		final CheckInInsertResDto checkInInsertResDto = new CheckInInsertResDto();
		final CheckInInsertDataResDto checkInInsertDataResDto = new CheckInInsertDataResDto();

		CheckIn checkInInsert = new CheckIn();
		final long checkOutDetailId = checkIn.getCheckInDetails().get(0).getCheckOutDetailId();
		final Optional<CheckOutDetail> cod = checkOutDetailDao.getById(checkOutDetailId);
		final long checkOutId = cod.get().getCheckOut().getId();
		final Optional<CheckIn> checkInDaoOptional = checkInDao.checkId(checkOutId);
		final CheckOut checkOut = checkOutDao.getById(checkIn.getCheckOutId()).get();
		checkInInsert.setCheckOut(checkOut);
		if (!checkInDaoOptional.isPresent()) {
			checkInInsert.setCreatedBy(principalService.getPrinciple().getId());
			checkInInsert.setUpdatedBy(principalService.getPrinciple().getId());
			checkInInsert = checkInDao.insert(checkInInsert);
		} else {
			final CheckIn checkInUpdate = checkInDao.getById(checkInDaoOptional.get().getId()).get();
			checkInUpdate.setUpdatedBy(principalService.getPrinciple().getId());
			checkInUpdate.setUpdatedAt(LocalDateTime.now());
			checkInDao.update(checkInUpdate);
			checkInInsert = checkInDaoOptional.get();
		}
		checkInInsertDataResDto.setId(checkInInsert.getId());
		final int checkInDetailsSize = checkIn.getCheckInDetails().size();
		for (int i = 0; i < checkInDetailsSize; i++) {
			final CheckInDetailInsertReqDto checkInDetailInsert = checkIn.getCheckInDetails().get(i);
			final CheckInDetail checkInDetail = new CheckInDetail();

			final AssetStatus assetStatus = assetStatusDao.getById(checkInDetailInsert.getAssetStatusId()).get();
			checkInDetail.setAssetStatus(assetStatus);

			final CheckOutDetail checkOutDetail = checkOutDetailDao.getById(checkInDetailInsert.getCheckOutDetailId())
					.get();
			checkInDetail.setCheckOutDetail(checkOutDetail);
			checkInDetail.setCheckIn(checkInInsert);
			checkInDetail.setCreatedBy(principalService.getPrinciple().getId());
			checkInDetail.setUpdatedBy(principalService.getPrinciple().getId());

			final Long assetId = checkOutDetail.getAsset().getId();
			final Asset asset = assetDao.getById(assetId).get();
			asset.setAssetStatus(assetStatus);
			asset.setIsActive(true);
			asset.setUpdatedBy(principalService.getPrinciple().getId());

			assetDao.update(asset);
			checkInDetailDao.insert(checkInDetail);
		}
		checkInInsertDataResDto.setId(checkInInsert.getId());
		checkInInsertResDto.setData(checkInInsertDataResDto);
		checkInInsertResDto.setMessage(Message.INSERTED.getMessage());
		return checkInInsertResDto;
	}
}
