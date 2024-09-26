package com.firstexample.democrt.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Records {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long tid;
	@Column(nullable=false)
	private int recordtype;
	@Column(nullable=false)
	private double amount;
	@Column(nullable=false)
	private LocalDateTime time;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="accNumber")
	private Customer customer;

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public int getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(int recordtype) {
		this.recordtype = recordtype;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Records(Long tid, int recordtype, double amount, LocalDateTime time, Customer customer) {
		super();
		this.tid = tid;
		this.recordtype = recordtype;
		this.amount = amount;
		this.time = time;
		this.customer = customer;
	}

	public Records() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Record [tid=" + tid + ", recordtype=" + recordtype + ", amount=" + amount + ", time=" + time
				+ ", customer=" + customer + "]";
	}
    

}
