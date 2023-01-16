package com.test.simpleasset.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "check_in_detail")
public class CheckInDetail extends BaseModel{

	@Column(name = "check_in_time", nullable = false)
	private LocalDateTime checkInTime;
	
	@OneToOne
	@JoinColumn(name = "check_out_detail_id", nullable = false)
	private CheckOutDetail checkOutDetail;
	
	@ManyToOne
	@JoinColumn(name = "asset_status_id", nullable = false)
	private AssetStatus assetStatus;
	
	@ManyToOne
	@JoinColumn(name = "check_in_id", nullable = false)
	private CheckIn checkIn;
	
	@PrePersist
	public void prePersist() {
		this.checkInTime = LocalDateTime.now();
		setCreatedAt(LocalDateTime.now());
		setIsActive(true);
	}
	
	public CheckOutDetail getCheckOutDetail() {
		return checkOutDetail;
	}
	public void setCheckOutDetail(CheckOutDetail checkOutDetail) {
		this.checkOutDetail = checkOutDetail;
	}
	public AssetStatus getAssetStatus() {
		return assetStatus;
	}
	public void setAssetStatus(AssetStatus assetStatus) {
		this.assetStatus = assetStatus;
	}
	public CheckIn getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(CheckIn checkIn) {
		this.checkIn = checkIn;
	}
	public LocalDateTime getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(LocalDateTime checkInTime) {
		this.checkInTime = checkInTime;
	}
	
}