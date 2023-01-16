package com.test.simpleasset.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDao {

	@PersistenceContext
	protected EntityManager em;

}
