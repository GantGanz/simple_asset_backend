package com.test.simpleasset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.simpleasset.dto.role.RoleDataResDto;
import com.test.simpleasset.dto.role.RolesDto;
import com.test.simpleasset.service.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("roles")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@Operation(summary = "[Super Admin only]")
	@GetMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<RolesDto> getAllrole() {
		final RolesDto result = roleService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "[Super Admin only]")
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<RoleDataResDto> getById(@PathVariable("id") final Long id) {
		final RoleDataResDto result = roleService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}