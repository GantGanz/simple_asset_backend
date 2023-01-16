package com.test.simpleasset.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.Role;

@Repository
public class RoleDao extends BaseDao{

	public Role insert(final Role data) {
		em.persist(data);

		return data;
	}

	public Role update(final Role data) {
		final Role roleUpdated = em.merge(data);

		return roleUpdated;
	}

	public Optional<Role> getById(final Long id) {
		final Role role = em.find(Role.class, id);

		final Optional<Role> roleOptional = Optional.ofNullable(role);

		return roleOptional;
	}

	public List<Role> getAll() {
		final String sql = " SELECT * FROM user_role";

		@SuppressWarnings("unchecked")
		final List<Role> roles = em.createNativeQuery(sql, Role.class).getResultList();
		return roles;
	}

	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM user_role WHERE id = :id ";

		final int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	public Optional<Role> getCodeId(final String roleCode) {
		final String sql = " SELECT r.id, r.role_name, r.role_code, r.ver FROM user_role r WHERE role_code = :roleCode ";
		Role role = null;
		try {
			final Object roleObj = em.createNativeQuery(sql).setParameter("roleCode", roleCode).getSingleResult();
			if (roleObj != null) {
				Object[] objects = (Object[]) roleObj;
				role = new Role();
				role.setId(Long.valueOf(objects[0].toString()));
				role.setRoleName(objects[1].toString());
				role.setRoleCode(objects[2].toString());
				role.setVersion(Integer.valueOf(objects[3].toString()));
			}
			em.detach(role);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Optional<Role> roleOptional = Optional.ofNullable(role);
		return roleOptional;
	}
}
