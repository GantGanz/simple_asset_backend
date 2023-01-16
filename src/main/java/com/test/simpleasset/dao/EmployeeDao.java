package com.test.simpleasset.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.Employee;

@Repository
public class EmployeeDao extends BaseDao{


	public Employee insert(final Employee data) {
		em.persist(data);

		return data;
	}


	public Employee update(final Employee data) {
		final Employee employeeUpdated = em.merge(data);

		return employeeUpdated;
	}


	public Optional<Employee> getById(final Long id) {
		Employee assetStatus = em.find(Employee.class, id);

		final Optional<Employee> employeeOptional = Optional.ofNullable(assetStatus);

		return employeeOptional;
	}


	public List<Employee> getAll() {
		final String sql = " SELECT * FROM employee";

		@SuppressWarnings("unchecked")
		final List<Employee> employees = em.createNativeQuery(sql, Employee.class).getResultList();
		return employees;
	}


	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM employee WHERE id = :id ";

		final int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}
}
