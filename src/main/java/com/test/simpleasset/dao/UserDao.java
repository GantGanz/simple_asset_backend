package com.test.simpleasset.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.simpleasset.model.File;
import com.test.simpleasset.model.Role;
import com.test.simpleasset.model.User;

@Repository
public class UserDao extends BaseDao{


	public User insert(final User data) {
		em.persist(data);

		return data;
	}


	public User update(final User data) {
		final User userUpdated = em.merge(data);

		return userUpdated;
	}


	public Optional<User> getById(final Long id) {
		final User user = em.find(User.class, id);

		final Optional<User> userOptional = Optional.ofNullable(user);
		return userOptional;
	}


	public List<User> getAll() {
		final String sql = " SELECT u.id, u.email, u.pass, u.ver, r.role_name, u.is_active, u.fullname, f.file_codes, f.extensions, f.id fid " 
				+ "	FROM users u "
				+ "	INNER JOIN user_role r ON r.id = u.user_role_id "
				+ "	INNER JOIN file f ON f.id = u.file_id ";

		final List<?> result = em.createNativeQuery(sql).getResultList();

		final List<User> users = new ArrayList<>();

		if (result != null && result.size() > 0) {
			result.forEach(userObj -> {
				final Object[] objArr = (Object[]) userObj;
				User user = new User();
				user.setId(Long.valueOf(objArr[0].toString()));
				user.setEmail(objArr[1].toString());
				user.setPass(objArr[2].toString());
				user.setVersion(Integer.valueOf(objArr[3].toString()));

				final Role role = new Role();
				role.setRoleName(objArr[4].toString());
				user.setRole(role);
				user.setIsActive(Boolean.valueOf(objArr[5].toString()));
				user.setFullname(objArr[6].toString());
				
				final File file = new File();
				file.setFileCodes(objArr[7].toString());
				file.setExtensions(objArr[8].toString());
				file.setId(Long.valueOf(objArr[9].toString()));
				user.setFile(file);
				users.add(user);
			});
		}

		return users;
	}


	public boolean deleteById(final Long id) {
		final String sql = " DELETE FROM users WHERE id = :id ";

		final int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}


	public Optional<User> getByEmail(final String email) {
		final String sql = " SELECT u.id as uid, u.email, u.pass, u.fullname, ur.role_name, ur.role_code, f.id as fid "
				+ "FROM users u "
				+ "INNER JOIN user_role ur ON ur.id = u.user_role_id "
				+ "INNER JOIN file f ON u.file_id = f.id "
				+ "WHERE email = :username ";
		User user = null;
		try {
			final Object userObj = em.createNativeQuery(sql).setParameter("username", email)
					.getSingleResult();

			if (userObj != null) {
				final Object[] objArr = (Object[]) userObj;
				user = new User();
				user.setId(Long.valueOf(objArr[0].toString()));
				user.setEmail(objArr[1].toString());
				user.setPass(objArr[2].toString());
				user.setFullname(objArr[3].toString());

				final Role role = new Role();
				role.setRoleName(objArr[4].toString());
				role.setRoleCode(objArr[5].toString());
				
				final File file = new File();
				file.setId(Long.valueOf(objArr[6].toString()));

				user.setFile(file);
				user.setRole(role);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Optional<User> userOptional = Optional.ofNullable(user);
		return userOptional;
	}

}
