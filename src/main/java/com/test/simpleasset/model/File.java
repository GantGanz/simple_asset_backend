package com.test.simpleasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "file")
public class File extends BaseModel {
	
	@Column(name = "file_codes", nullable = false, columnDefinition = "TEXT")
	private String fileCodes;
	
	@Column(name = "extensions", nullable = false, length=4)
	private String extensions;
	
	public String getFileCodes() {
		return fileCodes;
	}
	public void setFileCodes(String fileCodes) {
		this.fileCodes = fileCodes;
	}
	public String getExtensions() {
		return extensions;
	}
	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}

}
