package com.test.simpleasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "provider", 
uniqueConstraints = @UniqueConstraint(name = "provider_name_code_ck", columnNames = { "provider_name",
"provider_code" }))
public class Provider extends BaseModel{

	@Column(name = "provider_name", nullable = false, length=50)
	private String providerName;
	
	@Column(name = "provider_code", nullable = false, length=5, unique=true)
	private String providerCode;
	
	@ManyToOne
	@JoinColumn(name = "file_id", nullable = false)
	private File file;
	
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;
	
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderCode() {
		return providerCode;
	}
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	
}
