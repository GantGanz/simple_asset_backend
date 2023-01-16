package com.test.simpleasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "fullname_email_ck", columnNames = { "fullname",
		"email" }))
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}