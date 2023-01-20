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

import com.test.simpleasset.dto.company.CompaniesDto;
import com.test.simpleasset.dto.company.CompanyDataResDto;
import com.test.simpleasset.dto.company.CompanyDeleteResDto;
import com.test.simpleasset.dto.company.CompanyInsertReqDto;
import com.test.simpleasset.dto.company.CompanyInsertResDto;
import com.test.simpleasset.dto.company.CompanyUpdateReqDto;
import com.test.simpleasset.dto.company.CompanyUpdateResDto;
import com.test.simpleasset.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("companies")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@GetMapping
	public ResponseEntity<CompaniesDto> getAllCompany() {
		final CompaniesDto result = companyService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity<CompaniesDto> findByName(@PathVariable("name") final String name) {
		final CompaniesDto result = companyService.findByName(name);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Super Admin only]")
	@PostMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<CompanyInsertResDto> insert(@RequestBody @Valid final CompanyInsertReqDto data){
		final CompanyInsertResDto insert = companyService.insert(data); 
		return new ResponseEntity<>(insert, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CompanyDataResDto> getById(@PathVariable("id") final Long id) {
		final CompanyDataResDto result = companyService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Super Admin only]")
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<CompanyDeleteResDto> delete(@PathVariable("id") final Long id) {
		final CompanyDeleteResDto result = companyService.deleteById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Super Admin only]")
	@PutMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<CompanyUpdateResDto> update(@RequestBody @Valid final CompanyUpdateReqDto data) {
		final CompanyUpdateResDto result = companyService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}