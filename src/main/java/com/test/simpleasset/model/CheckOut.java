package com.test.simpleasset.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "check_out")
@Data
@EqualsAndHashCode(callSuper = false)
public class CheckOut extends BaseModel{

	@Column(name = "trxCode", unique = true, nullable = false, length=5)
	private String trxCode;
	
	@Column(name = "time_check_out", nullable = false)
	private LocalDateTime timeCheckOut;
	
	@Column(name = "check_out_location")
	private String checkOutLocation;
	
	@ManyToOne
	@JoinColumn(name = "asset_general_id")
	private Asset assetGeneral;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@PrePersist
	public void prePersist() {
		this.timeCheckOut = LocalDateTime.now();
		setCreatedAt(LocalDateTime.now());
		setIsActive(true);
	}
}