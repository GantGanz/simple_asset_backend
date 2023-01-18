package com.test.simpleasset.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "asset", uniqueConstraints = @UniqueConstraint(name = "asset_name_serial_number_ck", columnNames = {
		"asset_name", "serial_number" }))
@Data
@EqualsAndHashCode(callSuper = false)
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
}