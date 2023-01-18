package com.test.simpleasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "file")
@Data
@EqualsAndHashCode(callSuper = false)
public class File extends BaseModel {
	
	@Column(name = "file_codes", nullable = false, columnDefinition = "TEXT")
	private String fileCodes;
	
	@Column(name = "extensions", nullable = false, length=4)
	private String extensions;
}
