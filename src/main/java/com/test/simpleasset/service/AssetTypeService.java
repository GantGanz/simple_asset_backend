package com.test.simpleasset.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.simpleasset.dao.AssetTypeDao;
import com.test.simpleasset.dto.assettype.AssetTypeDataDto;
import com.test.simpleasset.dto.assettype.AssetTypeDataResDto;
import com.test.simpleasset.dto.assettype.AssetTypesDto;
import com.test.simpleasset.model.AssetType;

@Service
public class AssetTypeService {

	@Autowired
	private AssetTypeDao assetTypeDao;

	public AssetTypeDataResDto getById(final Long id) {
		Optional<AssetType> assetTypeOptional = assetTypeDao.getById(id);
		final AssetTypeDataDto assetTypeDataDto = new AssetTypeDataDto();
		if (assetTypeOptional.isPresent()) {
			final AssetType assetType = assetTypeOptional.get();
			assetTypeDataDto.setAssetTypeCode(assetType.getAssetTypeCode());
			assetTypeDataDto.setAssetTypeName(assetType.getAssetTypeName());
			assetTypeDataDto.setAssetTypeId(assetType.getId());
			assetTypeDataDto.setVersion(assetType.getVersion());
		}
		final AssetTypeDataResDto assetTypeDataResDto = new AssetTypeDataResDto();
		assetTypeDataResDto.setData(assetTypeDataDto);
		return assetTypeDataResDto;
	}

	public AssetTypesDto getAll() {
		final List<AssetType> assetTypes = assetTypeDao.getAll();
		final List<AssetTypeDataDto> dataDtos = new ArrayList<>();
		
		final Comparator<AssetType> compareByNameThenCode = Comparator
                .comparing(AssetType::getAssetTypeName)
                .thenComparing(AssetType::getAssetTypeCode);
		final Stream<AssetType> sortedAssetTypes = assetTypes.stream()
            .sorted(compareByNameThenCode);
		
		sortedAssetTypes.forEach(assetType -> {
			final AssetTypeDataDto assetTypeDataDto = new AssetTypeDataDto();
			assetTypeDataDto.setAssetTypeCode(assetType.getAssetTypeCode());
			assetTypeDataDto.setAssetTypeName(assetType.getAssetTypeName());
			assetTypeDataDto.setAssetTypeId(assetType.getId());
			assetTypeDataDto.setVersion(assetType.getVersion());
			dataDtos.add(assetTypeDataDto);
		});
		final AssetTypesDto assetTypeesDto = new AssetTypesDto();
		assetTypeesDto.setData(dataDtos);

		return assetTypeesDto;
	}

}
