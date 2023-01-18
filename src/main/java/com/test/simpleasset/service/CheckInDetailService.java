package com.test.simpleasset.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.simpleasset.dao.CheckInDetailDao;
import com.test.simpleasset.dto.checkindetail.CheckInDetailDataDto;
import com.test.simpleasset.dto.checkindetail.CheckInDetailsDto;
import com.test.simpleasset.model.CheckInDetail;

@Service
public class CheckInDetailService {

	@Autowired
	private CheckInDetailDao checkInDetailDao;

	public CheckInDetailsDto getAllCheckedIn(final Long checkInId) {
		final List<CheckInDetail> checkInDetails = checkInDetailDao.getAllCheckedIn(checkInId);
		final List<CheckInDetailDataDto> dataDtos = new ArrayList<>();
		
		final Comparator<CheckInDetail> compareByCheckInTime = Comparator
                .comparing(CheckInDetail::getCheckInTime).reversed();
		final Stream<CheckInDetail> sortedCheckInDetails = checkInDetails.stream()
            .sorted(compareByCheckInTime);
		
		sortedCheckInDetails.forEach(checkInDetail -> {			
			final CheckInDetailDataDto checkInDetailDataDto = new CheckInDetailDataDto();
			checkInDetailDataDto.setAssetName(checkInDetail.getCheckOutDetail().getAsset().getAssetName());
			checkInDetailDataDto.setAssetStatus(checkInDetail.getAssetStatus().getAssetStatusName());
			checkInDetailDataDto.setCheckInTime(checkInDetail.getCheckInTime());
			checkInDetailDataDto.setTrxCode(checkInDetail.getCheckIn().getCheckOut().getTrxCode());
			checkInDetailDataDto.setCheckInDetailId(checkInDetail.getId());
			checkInDetailDataDto.setVersion(checkInDetail.getVersion());
			dataDtos.add(checkInDetailDataDto);
		});
		final CheckInDetailsDto checkInDetailsDto = new CheckInDetailsDto();
		checkInDetailsDto.setData(dataDtos);
		return checkInDetailsDto;
	}

}
