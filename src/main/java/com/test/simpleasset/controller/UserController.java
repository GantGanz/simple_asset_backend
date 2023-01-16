package com.test.simpleasset.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.simpleasset.dto.user.UserDataDto;
import com.test.simpleasset.dto.user.UserDeleteResDto;
import com.test.simpleasset.dto.user.UserInsertReqDto;
import com.test.simpleasset.dto.user.UserInsertResDto;
import com.test.simpleasset.dto.user.UserUpdatePasswordReqDto;
import com.test.simpleasset.dto.user.UserUpdatePasswordResDto;
import com.test.simpleasset.dto.user.UserUpdateReqDto;
import com.test.simpleasset.dto.user.UserUpdateResDto;
import com.test.simpleasset.dto.user.UsersDto;
import com.test.simpleasset.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<UsersDto> getAllUser() {
		final UsersDto result = userService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
//	Comment the PreAuthorize to create super admin for the first time. Then Uncomment back when it's been created
	@PostMapping
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<UserInsertResDto> insert(@RequestBody @Valid final UserInsertReqDto data){
		final UserInsertResDto insert = userService.insert(data); 
		return new ResponseEntity<>(insert, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UserDataDto> getById(@PathVariable("id") final Long id) {
		final UserDataDto result = userService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('SA')")
	public ResponseEntity<UserDeleteResDto> delete(@PathVariable("id") final Long id) {
		final UserDeleteResDto result = userService.deleteById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<UserUpdateResDto> update(@RequestBody @Valid final UserUpdateReqDto data) {
		final UserUpdateResDto result = userService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PatchMapping("password")
	public ResponseEntity<UserUpdatePasswordResDto> updatePassword(@RequestBody @Valid final UserUpdatePasswordReqDto data) {
		final UserUpdatePasswordResDto result = userService.updatePassword(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}