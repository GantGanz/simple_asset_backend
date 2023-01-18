package com.test.simpleasset.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "check_in")
@Data
@EqualsAndHashCode(callSuper = false)
public class CheckIn extends BaseModel{

	@OneToOne
	@JoinColumn(name = "check_out_id")
	private CheckOut checkOut;
}