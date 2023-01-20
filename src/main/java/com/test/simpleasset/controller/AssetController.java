package com.test.simpleasset.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.simpleasset.dto.asset.AssetDataResDto;
import com.test.simpleasset.dto.asset.AssetDeleteResDto;
import com.test.simpleasset.dto.asset.AssetInsertReqDto;
import com.test.simpleasset.dto.asset.AssetInsertResDto;
import com.test.simpleasset.dto.asset.AssetUpdateReqDto;
import com.test.simpleasset.dto.asset.AssetUpdateResDto;
import com.test.simpleasset.dto.asset.AssetsDto;
import com.test.simpleasset.service.AssetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("assets")
public class AssetController {
	@Autowired
	private AssetService assetService;

	@GetMapping
	public ResponseEntity<AssetsDto> getAllAsset() {
		final AssetsDto result = assetService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Super Admin only]", 
			description = "You must atleast create a company and a provider first to add an asset. \n\n"
					+ "Creating an asset would send an email to the Super Admin email.")
	@PostMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<AssetInsertResDto> insert(@RequestBody @Valid final AssetInsertReqDto data){
		final AssetInsertResDto insert = assetService.insert(data); 
		return new ResponseEntity<>(insert, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AssetDataResDto> getById(@PathVariable("id") final Long id) {
		final AssetDataResDto result = assetService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity<AssetsDto> findByName(@PathVariable("name") final String name) {
		final AssetsDto result = assetService.findByName(name);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Super Admin only]")
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<AssetDeleteResDto> delete(@PathVariable("id") final Long id) {
		final AssetDeleteResDto result = assetService.deleteById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Super Admin only]")
	@PutMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<AssetUpdateResDto> update(@RequestBody @Valid final AssetUpdateReqDto data) {
		final AssetUpdateResDto result = assetService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("deployables")
	public ResponseEntity<AssetsDto> getAllAssetDeployable() {
		final AssetsDto result = assetService.getAllDeployable();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("deployable-generals")
	public ResponseEntity<AssetsDto> getAllAssetDeployableGeneral() {
		final AssetsDto result = assetService.getAllDeployableGeneral();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("deployable-components")
	public ResponseEntity<AssetsDto> getAllAssetDeployableComponents() {
		final AssetsDto result = assetService.getAllDeployableComponents();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}