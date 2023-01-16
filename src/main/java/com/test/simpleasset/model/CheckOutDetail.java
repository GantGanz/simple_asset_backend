package com.test.simpleasset.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "check_out_detail")
public class CheckOutDetail extends BaseModel {

	@Column(name = "return_date")
	private LocalDate returnDate;

	@ManyToOne
	@JoinColumn(name = "asset_id")
	private Asset asset;

	@ManyToOne
	@JoinColumn(name = "check_out_id")
	private CheckOut checkOut;

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public CheckOut getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(CheckOut checkOut) {
		this.checkOut = checkOut;
	}
}