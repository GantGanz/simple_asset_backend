package com.test.simpleasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "fullname_email_ck", columnNames = { "fullname",
		"email" }))
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseModel {

	@Column(name = "fullname", nullable = false, length = 50)
	private String fullname;

	@Column(name = "email", nullable = false, length = 30, unique = true)
	private String email;

	@Column(name = "pass", nullable = false)
	private String pass;

	@ManyToOne
	@JoinColumn(name = "user_role_id", nullable = false)
	private Role role;

	@ManyToOne
	@JoinColumn(name = "file_id", nullable = false, unique = true)
	private File file;
}