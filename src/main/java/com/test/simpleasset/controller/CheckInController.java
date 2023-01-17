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

import com.test.simpleasset.dto.checkin.CheckInInsertReqDto;
import com.test.simpleasset.dto.checkin.CheckInInsertResDto;
import com.test.simpleasset.dto.checkin.CheckInsDto;
import com.test.simpleasset.dto.checkindetail.CheckInDetailsDto;
import com.test.simpleasset.service.CheckInDetailService;
import com.test.simpleasset.service.CheckInService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("check-ins")
public class CheckInController {
	@Autowired
	private CheckInService checkInService;
	@Autowired
	private CheckInDetailService checkInDetailService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('NA')")
	public ResponseEntity<CheckInsDto> getAllCheckIn() {
		final CheckInsDto result = checkInService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("all-by-id/{id}")
	@PreAuthorize("hasAuthority('NA')")
	public ResponseEntity<CheckInDetailsDto> getAllCheckedIn(@PathVariable("id") final Long id) {
		final CheckInDetailsDto result = checkInDetailService.getAllCheckedIn(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('NA')")
	public ResponseEntity<CheckInInsertResDto> insert(@RequestBody @Valid final CheckInInsertReqDto data){
		final CheckInInsertResDto insert = checkInService.insertCheckInDetails(data); 
		return new ResponseEntity<>(insert, HttpStatus.CREATED);
	}
}