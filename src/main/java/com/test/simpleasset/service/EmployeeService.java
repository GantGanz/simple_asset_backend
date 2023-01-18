package com.test.simpleasset.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.simpleasset.constant.Message;
import com.test.simpleasset.dao.EmployeeDao;
import com.test.simpleasset.dto.employee.EmployeeDataDto;
import com.test.simpleasset.dto.employee.EmployeeDataResDto;
import com.test.simpleasset.dto.employee.EmployeeDeleteReqDto;
import com.test.simpleasset.dto.employee.EmployeeDeleteResDto;
import com.test.simpleasset.dto.employee.EmployeeInsertDataResDto;
import com.test.simpleasset.dto.employee.EmployeeInsertReqDto;
import com.test.simpleasset.dto.employee.EmployeeInsertResDto;
import com.test.simpleasset.dto.employee.EmployeeUpdateReqDto;
import com.test.simpleasset.dto.employee.EmployeeUpdateResDto;
import com.test.simpleasset.dto.employee.EmployeesDto;
import com.test.simpleasset.model.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private PrincipalService principalService;

	public EmployeesDto getAll() {
		final List<Employee> employees = employeeDao.getAll();
		
		final Comparator<Employee> compareByNameThenCode = Comparator
                .comparing(Employee::getEmployeeName)
                .thenComparing(Employee::getEmployeeCode);
		final Stream<Employee> sortedEmployees = employees.stream()
            .sorted(compareByNameThenCode);
		
		final List<EmployeeDataDto> dataDtos = new ArrayList<>();
		sortedEmployees.forEach(employee -> {	
			final EmployeeDataDto employeeDataDto = new EmployeeDataDto();
			employeeDataDto.setEmployeeCode(employee.getEmployeeCode());
			employeeDataDto.setEmployeeName(employee.getEmployeeName());
			employeeDataDto.setEmployeeId(employee.getId());
			employeeDataDto.setVersion(employee.getVersion());
			employeeDataDto.setIsActive(employee.getIsActive());
			dataDtos.add(employeeDataDto);
		});
		final EmployeesDto employeesDto = new EmployeesDto();
		employeesDto.setData(dataDtos);

		return employeesDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public EmployeeInsertResDto insert(final EmployeeInsertReqDto data) {
		final EmployeeInsertResDto employeeInsertResDto = new EmployeeInsertResDto();
		final EmployeeInsertDataResDto employeeInsertDataResDto = new EmployeeInsertDataResDto();
		final Employee employee = new Employee();
		employee.setEmployeeCode(data.getEmployeeCode());
		employee.setEmployeeName(data.getEmployeeName());
		employee.setCreatedBy(principalService.getPrinciple().getId());
		final Employee employeeInsert = employeeDao.insert(employee);
		employeeInsertDataResDto.setId(employeeInsert.getId());
		employeeInsertResDto.setData(employeeInsertDataResDto);
		employeeInsertResDto.setMessage(Message.INSERTED.getMessage());
		return employeeInsertResDto;
	}

	public EmployeeDataResDto getById(final Long id) {
		final Optional<Employee> employeeOptional = employeeDao.getById(id);
		final EmployeeDataDto employeeDataDto = new EmployeeDataDto();
		if (employeeOptional.isPresent()) {
			final Employee employee = employeeOptional.get();
			employeeDataDto.setEmployeeCode(employee.getEmployeeCode());
			employeeDataDto.setEmployeeName(employee.getEmployeeName());
			employeeDataDto.setEmployeeId(employee.getId());
			employeeDataDto.setIsActive(employee.getIsActive());
			employeeDataDto.setVersion(employee.getVersion());
		}
		final EmployeeDataResDto employeeDataResDto = new EmployeeDataResDto();
		employeeDataResDto.setData(employeeDataDto);
		return employeeDataResDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public EmployeeUpdateResDto update(final EmployeeUpdateReqDto data) {
		final Optional<Employee> employeeOptional = employeeDao.getById(data.getEmployeeId());
		final EmployeeUpdateResDto employeeUpdateResDto = new EmployeeUpdateResDto();
		Employee employeeUpdate = null;
		if (employeeOptional.isPresent()) {
			employeeUpdate = employeeOptional.get();
			employeeUpdate.setEmployeeName(data.getEmployeeName());
			employeeUpdate.setIsActive(data.getIsActive());
			employeeUpdate.setUpdatedBy(principalService.getPrinciple().getId());
			employeeUpdate = employeeDao.update(employeeUpdate);
			data.setVersion(employeeUpdate.getVersion());
			employeeUpdateResDto.setData(data);
			employeeUpdateResDto.setMessage(Message.UPDATED.getMessage());
		}
		return employeeUpdateResDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public EmployeeDeleteResDto deleteById(final Long id) {
		final Optional<Employee> employeeOptional = employeeDao.getById(id);
		final EmployeeDeleteResDto employeeDeleteResDto = new EmployeeDeleteResDto();
		if (employeeOptional.isPresent()) {
			final EmployeeDeleteReqDto employeeDeleteReqDto = new EmployeeDeleteReqDto();
			employeeDeleteReqDto.setEmployeeId(id);
			employeeDeleteResDto.setMessage(Message.DELETED.getMessage());
			employeeDao.deleteById(id);
		}
		return employeeDeleteResDto;
	}
}