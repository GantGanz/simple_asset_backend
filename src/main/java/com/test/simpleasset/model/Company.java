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
@Table(name = "company", uniqueConstraints = @UniqueConstraint(name = "company_name_code_ck", columnNames = {
		"company_name", "company_code" }))
@Data
@EqualsAndHashCode(callSuper = false)
public class Company extends BaseModel {

	@Column(name = "company_name", nullable = false, length = 50)
	private String companyName;

	@Column(name = "company_code", nullable = false, length = 5, unique = true)
	private String companyCode;

	@ManyToOne
	@JoinColumn(name = "file_id", nullable = false)
	private File file;
}
