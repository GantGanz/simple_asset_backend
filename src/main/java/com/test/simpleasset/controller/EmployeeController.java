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

import com.test.simpleasset.dto.employee.EmployeeDataResDto;
import com.test.simpleasset.dto.employee.EmployeeDeleteResDto;
import com.test.simpleasset.dto.employee.EmployeeInsertReqDto;
import com.test.simpleasset.dto.employee.EmployeeInsertResDto;
import com.test.simpleasset.dto.employee.EmployeeUpdateReqDto;
import com.test.simpleasset.dto.employee.EmployeeUpdateResDto;
import com.test.simpleasset.dto.employee.EmployeesDto;
import com.test.simpleasset.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<EmployeesDto> getAllemployee() {
		final EmployeesDto result = employeeService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Super Admin only]")
	@PostMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<EmployeeInsertResDto> insert(@RequestBody @Valid final EmployeeInsertReqDto data){
		final EmployeeInsertResDto insert = employeeService.insert(data); 
		return new ResponseEntity<>(insert, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDataResDto> getById(@PathVariable("id") final Long id) {
		final EmployeeDataResDto result = employeeService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Super Admin only]")
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<EmployeeDeleteResDto> delete(@PathVariable("id") final Long id) {
		final EmployeeDeleteResDto result = employeeService.deleteById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Super Admin only]")
	@PutMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<EmployeeUpdateResDto> update(@RequestBody @Valid final EmployeeUpdateReqDto data) {
		final EmployeeUpdateResDto result = employeeService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}