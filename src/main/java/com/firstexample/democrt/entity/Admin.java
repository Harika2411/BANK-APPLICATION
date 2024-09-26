package com.firstexample.democrt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//@Table(name="admin_table")
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
 private String uname;
	@Column(nullable=false)
 private String password;
 public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUname() {
	return uname;
}
public void setUname(String uname) {
	this.uname = uname;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Admin(int id, String uname, String password) {
	super();
	this.id = id;
	this.uname = uname;
	this.password = password;
}
public Admin() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Admin [id=" + id + ", uname=" + uname + ", password=" + password + "]";
}

	
	
	
}
