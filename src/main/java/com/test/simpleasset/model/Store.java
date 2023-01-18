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
@Table(name = "store", uniqueConstraints = @UniqueConstraint(name = "store_name_code_ck", columnNames = { "store_name",
		"store_code" }))
@Data
@EqualsAndHashCode(callSuper = false)
public class Store extends BaseModel {

	@Column(name = "store_name", nullable = false, length = 50)
	private String storeName;

	@Column(name = "store_code", nullable = false, length = 5, unique = true)
	private String storeCode;

	@ManyToOne
	@JoinColumn(name = "file_id", nullable = false)
	private File file;
}
