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

import com.test.simpleasset.dto.store.StoreDataDto;
import com.test.simpleasset.dto.store.StoreDeleteResDto;
import com.test.simpleasset.dto.store.StoreInsertReqDto;
import com.test.simpleasset.dto.store.StoreInsertResDto;
import com.test.simpleasset.dto.store.StoreUpdateReqDto;
import com.test.simpleasset.dto.store.StoreUpdateResDto;
import com.test.simpleasset.dto.store.StoresDto;
import com.test.simpleasset.service.StoreService;

@RestController
@RequestMapping("stores")
public class StoreController {
	@Autowired
	private StoreService storeService;

	@GetMapping
	public ResponseEntity<StoresDto> getAllstore() {
		final StoresDto result = storeService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<StoreInsertResDto> insert(@RequestBody @Valid final StoreInsertReqDto data){
		final StoreInsertResDto insert = storeService.insert(data); 
		return new ResponseEntity<>(insert, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<StoreDataDto> getById(@PathVariable("id") final Long id) {
		final StoreDataDto result = storeService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<StoreDeleteResDto> delete(@PathVariable("id") final Long id) {
		final StoreDeleteResDto result = storeService.deleteById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<StoreUpdateResDto> update(@RequestBody @Valid final StoreUpdateReqDto data) {
		final StoreUpdateResDto result = storeService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}