package com.test.simpleasset.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.simpleasset.dto.login.LoginReqDto;
import com.test.simpleasset.dto.login.LoginResDto;
import com.test.simpleasset.model.User;
import com.test.simpleasset.service.JwtService;
import com.test.simpleasset.service.UserService;

@RestController
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@Autowired
	private JwtService jwtService;
	
	@PostMapping
	public ResponseEntity<LoginResDto> login(@RequestBody @Valid final LoginReqDto data){
		final Authentication authentication = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
		authenticationManager.authenticate(authentication);
		final Optional<User> user = userService.getByEmail(data.getEmail());
		final Map<String, Object> claims = new HashMap<>();
		final Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY,1);
		claims.put("exp", cal.getTime());
		claims.put("id", user.get().getId());
		claims.put("rc", user.get().getRole().getRoleCode());
		
		LoginResDto loginResDto = new LoginResDto();
		loginResDto.setFullname(user.get().getFullname());
		loginResDto.setEmail(user.get().getEmail());
		loginResDto.setPhotoId(user.get().getFile().getId());
		loginResDto.setId(user.get().getId());
		loginResDto.setRoleCode(user.get().getRole().getRoleCode());
		loginResDto.setToken(jwtService.generateJwt(claims));
		return new ResponseEntity<>(loginResDto, HttpStatus.CREATED);
	}
}