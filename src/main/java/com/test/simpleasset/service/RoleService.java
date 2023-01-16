package com.test.simpleasset.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.simpleasset.dao.RoleDao;
import com.test.simpleasset.dto.role.RoleDataDto;
import com.test.simpleasset.dto.role.RoleDataResDto;
import com.test.simpleasset.dto.role.RolesDto;
import com.test.simpleasset.model.Role;

@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;

	public RoleDataResDto getById(final Long id) {
		Optional<Role> roleOptional = roleDao.getById(id);
		final RoleDataDto roleDataDto = new RoleDataDto();
		if (roleOptional.isPresent()) {
			final Role role = roleOptional.get();
			roleDataDto.setRoleCode(role.getRoleCode());
			roleDataDto.setRoleName(role.getRoleName());
			roleDataDto.setRoleId(role.getId());
			roleDataDto.setVersion(role.getVersion());
		}
		final RoleDataResDto roleDataResDto = new RoleDataResDto();
		roleDataResDto.setData(roleDataDto);
		return roleDataResDto;
	}

	public RolesDto getAll() {
		final List<Role> roles = roleDao.getAll();
		final List<RoleDataDto> dataDtos = new ArrayList<>();
		roles.stream().forEach(role -> {			
			final RoleDataDto roleDataDto = new RoleDataDto();
			roleDataDto.setRoleCode(role.getRoleCode());
			roleDataDto.setRoleName(role.getRoleName());
			roleDataDto.setRoleId(role.getId());
			roleDataDto.setVersion(role.getVersion());
			dataDtos.add(roleDataDto);
		});
			
		final RolesDto roleesDto = new RolesDto();
		roleesDto.setData(dataDtos);

		return roleesDto;
	}
}
