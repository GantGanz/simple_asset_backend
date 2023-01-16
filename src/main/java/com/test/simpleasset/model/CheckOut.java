package com.test.simpleasset.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "check_out")
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
	
	public String getCheckOutLocation() {
		return checkOutLocation;
	}
	public void setCheckOutLocation(String checkOutLocation) {
		this.checkOutLocation = checkOutLocation;
	}
	
	public Asset getAssetGeneral() {
		return assetGeneral;
	}
	public void setAssetGeneral(Asset assetGeneral) {
		this.assetGeneral = assetGeneral;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public LocalDateTime getTimeCheckOut() {
		return timeCheckOut;
	}
	public void setTimeCheckOut(LocalDateTime timeCheckOut) {
		this.timeCheckOut = timeCheckOut;
	}
	public String getTrxCode() {
		return trxCode;
	}
	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}
	
}