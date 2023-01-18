package com.test.simpleasset.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "check_out_detail")
@Data
@EqualsAndHashCode(callSuper = false)
public class CheckOutDetail extends BaseModel {

	@Column(name = "return_date")
	private LocalDate returnDate;

	@ManyToOne
	@JoinColumn(name = "asset_id")
	private Asset asset;

	@ManyToOne
	@JoinColumn(name = "check_out_id")
	private CheckOut checkOut;
}