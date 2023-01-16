package com.test.simpleasset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.simpleasset.dto.assetstatus.AssetStatusDataResDto;
import com.test.simpleasset.dto.assetstatus.AssetStatusesDto;
import com.test.simpleasset.service.AssetStatusService;

@RestController
@RequestMapping("asset-statuses")
public class AssetStatusController {
	@Autowired
	private AssetStatusService assetStatusService;

	@GetMapping
	public ResponseEntity<AssetStatusesDto> getAllAssetStatus() {
		final AssetStatusesDto result = assetStatusService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AssetStatusDataResDto> getById(@PathVariable("id") final Long id) {
		final AssetStatusDataResDto result = assetStatusService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}