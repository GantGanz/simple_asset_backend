package com.test.simpleasset.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "asset", uniqueConstraints = @UniqueConstraint(name = "asset_name_serial_number_ck", columnNames = {
		"asset_name", "serial_number" }))
public class Asset extends BaseModel {

	@Column(name = "asset_name", nullable = false, length = 50)
	private String assetName;

	@Column(name = "invoice_number", nullable = false, length = 30)
	private String invoiceNumber;

	@Column(name = "serial_number", nullable = false, length = 30, unique = true)
	private String serialNumber;

	@Column(name = "expired_date")
	private LocalDate expiredDate;

	@ManyToOne
	@JoinColumn(name = "file_id", nullable = false)
	private File file;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@ManyToOne
	@JoinColumn(name = "asset_status_id", nullable = false)
	private AssetStatus assetStatus;

	@ManyToOne
	@JoinColumn(name = "asset_type_id", nullable = false)
	private AssetType assetType;

	@ManyToOne
	@JoinColumn(name = "provider_id", nullable = false)
	private Provider provider;

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public AssetStatus getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(AssetStatus assetStatus) {
		this.assetStatus = assetStatus;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public LocalDate getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
	}

}