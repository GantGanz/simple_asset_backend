package com.test.simpleasset.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "check_in")
public class CheckIn extends BaseModel{

	@OneToOne
	@JoinColumn(name = "check_out_id")
	private CheckOut checkOut;
	
	public CheckOut getCheckOut() {
		return checkOut;
	}	
	public void setCheckOut(CheckOut checkOut) {
		this.checkOut = checkOut;
	}
}