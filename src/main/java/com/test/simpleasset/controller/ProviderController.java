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

import com.test.simpleasset.dto.provider.ProviderDataResDto;
import com.test.simpleasset.dto.provider.ProviderDeleteResDto;
import com.test.simpleasset.dto.provider.ProviderInsertReqDto;
import com.test.simpleasset.dto.provider.ProviderInsertResDto;
import com.test.simpleasset.dto.provider.ProviderUpdateReqDto;
import com.test.simpleasset.dto.provider.ProviderUpdateResDto;
import com.test.simpleasset.dto.provider.ProvidersDto;
import com.test.simpleasset.service.ProviderService;

@RestController
@RequestMapping("providers")
public class ProviderController {
	@Autowired
	private ProviderService providerService;

	@GetMapping
	public ResponseEntity<ProvidersDto> getAllprovider() {
		final ProvidersDto result = providerService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<ProviderInsertResDto> insert(@RequestBody @Valid final ProviderInsertReqDto data){
		final ProviderInsertResDto insert = providerService.insert(data); 
		return new ResponseEntity<>(insert, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProviderDataResDto> getById(@PathVariable("id") final Long id) {
		final ProviderDataResDto result = providerService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<ProviderDeleteResDto> delete(@PathVariable("id") final Long id) {
		final ProviderDeleteResDto result = providerService.deleteById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<ProviderUpdateResDto> update(@RequestBody @Valid final ProviderUpdateReqDto data) {
		final ProviderUpdateResDto result = providerService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}