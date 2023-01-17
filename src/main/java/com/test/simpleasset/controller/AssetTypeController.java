package com.test.simpleasset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.simpleasset.dto.assettype.AssetTypeDataResDto;
import com.test.simpleasset.dto.assettype.AssetTypesDto;
import com.test.simpleasset.service.AssetTypeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("asset-types")
public class AssetTypeController {
	@Autowired
	private AssetTypeService assetTypeService;

	@GetMapping
	public ResponseEntity<AssetTypesDto> getAllAssetType() {
		final AssetTypesDto result = assetTypeService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AssetTypeDataResDto> getById(@PathVariable("id") final Long id) {
		final AssetTypeDataResDto result = assetTypeService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}