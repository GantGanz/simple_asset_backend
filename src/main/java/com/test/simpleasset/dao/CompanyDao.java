package com.test.simpleasset.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.Company;

@Repository
public class CompanyDao extends BaseDao{


	public Company insert(final Company data) {
		em.persist(data);

		return data;
	}


	public Company update(final Company data) {
		final Company companyUpdated = em.merge(data);

		return companyUpdated;
	}


	public Optional<Company> getById(final Long id) {
		final Company assetStatus = em.find(Company.class, id);

		final Optional<Company> companyOptional = Optional.ofNullable(assetStatus);

		return companyOptional;
	}


	public List<Company> getAll() {
		final String sql = " SELECT * FROM company";

		@SuppressWarnings("unchecked")
		final List<Company> companies = em.createNativeQuery(sql, Company.class).getResultList();
		return companies;
	}


	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM company WHERE id = :id ";

		final int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}
}
