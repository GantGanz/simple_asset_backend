package com.test.simpleasset.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.simpleasset.dto.checkout.CheckOutInsertReqDto;
import com.test.simpleasset.dto.checkout.CheckOutInsertResDto;
import com.test.simpleasset.dto.checkout.CheckOutsDto;
import com.test.simpleasset.dto.checkoutdetail.CheckOutDetailDataResDto;
import com.test.simpleasset.dto.checkoutdetail.CheckOutDetailsDto;
import com.test.simpleasset.service.CheckOutDetailService;
import com.test.simpleasset.service.CheckOutService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("check-outs")
public class CheckOutController {
	@Autowired
	private CheckOutService checkOutService;
	@Autowired
	private CheckOutDetailService checkOutDetailService;
	
	@Operation(summary = "[Member only]")
	@GetMapping
	@PreAuthorize("hasAuthority('NA')")
	public ResponseEntity<CheckOutsDto> getAllCheckout() {
		final CheckOutsDto result = checkOutService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Member only]", description = "Get all check outs which hasn't checked in yet")
	@GetMapping("unchecked")
	@PreAuthorize("hasAuthority('NA')")
	public ResponseEntity<CheckOutsDto> getAllCheckoutUnchecked() {
		final CheckOutsDto result = checkOutService.getAllUnchecked();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Member only]", description = "Delete 2 of the check out targets (ex: if u choose to check out at a location, then delete assetGeneralId and employeeId, vice versa). \n\n"
			+ "You can only check out component assets to general asset ")
	@PostMapping
	@PreAuthorize("hasAuthority('NA')")
	public ResponseEntity<CheckOutInsertResDto> insert(@RequestBody @Valid final CheckOutInsertReqDto data){
		final CheckOutInsertResDto insert = checkOutService.insertCheckOutDetails(data); 
		return new ResponseEntity<>(insert, HttpStatus.CREATED);
	}
	
	@Operation(summary = "[Member only]", description = "Get all check out details by check out id")
	@GetMapping("all-by-id/{id}")
	@PreAuthorize("hasAuthority('NA')")
	public ResponseEntity<CheckOutDetailsDto> getAllById(@PathVariable("id") final Long id) {
		final CheckOutDetailsDto result = checkOutDetailService.getAllById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Member only]", description = "Get all check out details which hasn't checked in yet")
	@GetMapping("all-not-check-in")
	@PreAuthorize("hasAuthority('NA')")
	public ResponseEntity<CheckOutDetailsDto> getAllNotCheckIn() {
		final CheckOutDetailsDto result = checkOutDetailService.getAllNotCheckIn();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Member only]", description = "Get check out detail by id")
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('NA')")
	public ResponseEntity<CheckOutDetailDataResDto> getById(@PathVariable("id") final Long id) {
		final CheckOutDetailDataResDto result = checkOutDetailService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Member only]", description = "Get all check out details by check out id which hasn't checked in yet")
	@GetMapping("all-not-check-in-by-id/{id}")
	@PreAuthorize("hasAuthority('NA')")
	public ResponseEntity<CheckOutDetailsDto> getAllNotCheckInById(@PathVariable("id") final Long id) {
		final CheckOutDetailsDto result = checkOutDetailService.getAllNotCheckInById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}