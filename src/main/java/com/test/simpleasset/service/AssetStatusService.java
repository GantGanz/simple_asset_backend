package com.test.simpleasset.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.simpleasset.dao.AssetStatusDao;
import com.test.simpleasset.dto.assetstatus.AssetStatusDataDto;
import com.test.simpleasset.dto.assetstatus.AssetStatusDataResDto;
import com.test.simpleasset.dto.assetstatus.AssetStatusesDto;
import com.test.simpleasset.model.AssetStatus;

@Service
public class AssetStatusService {

	@Autowired
	private AssetStatusDao assetStatusDao;

	public AssetStatusDataResDto getById(final Long id) {
		Optional<AssetStatus> assetStatusOptional = assetStatusDao.getById(id);
		final AssetStatusDataDto assetStatusDataDto = new AssetStatusDataDto();
		if (assetStatusOptional.isPresent()) {
			final AssetStatus assetStatus = assetStatusOptional.get();
			assetStatusDataDto.setAssetStatusCode(assetStatus.getAssetStatusCode());
			assetStatusDataDto.setAssetStatusName(assetStatus.getAssetStatusName());
			assetStatusDataDto.setAssetStatusId(assetStatus.getId());
			assetStatusDataDto.setVersion(assetStatus.getVersion());
		}
		final AssetStatusDataResDto assetStatusDataResDto = new AssetStatusDataResDto();
		assetStatusDataResDto.setData(assetStatusDataDto);
		return assetStatusDataResDto;
	}

	public AssetStatusesDto getAll() {
		final List<AssetStatus> assetStatuses = assetStatusDao.getAll();
		final List<AssetStatusDataDto> dataDtos = new ArrayList<>();
		
		final Comparator<AssetStatus> compareByNameThenCode = Comparator
                .comparing(AssetStatus::getAssetStatusName)
                .thenComparing(AssetStatus::getAssetStatusCode);
		final Stream<AssetStatus> sortedAssetStatuses = assetStatuses.stream()
            .sorted(compareByNameThenCode);
		
		sortedAssetStatuses.forEach(assetStatus -> {
			final AssetStatusDataDto assetStatusDataDto = new AssetStatusDataDto();
			assetStatusDataDto.setAssetStatusCode(assetStatus.getAssetStatusCode());
			assetStatusDataDto.setAssetStatusName(assetStatus.getAssetStatusName());
			assetStatusDataDto.setAssetStatusId(assetStatus.getId());
			assetStatusDataDto.setVersion(assetStatus.getVersion());
			dataDtos.add(assetStatusDataDto);
		});
		final AssetStatusesDto assetStatusesDto = new AssetStatusesDto();
		assetStatusesDto.setData(dataDtos);

		return assetStatusesDto;
	}

}
