package com.makas.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Driver {
 private int driverId;
 private String name;
 private String country;
 
 
public Driver() {
	super();
}
public Driver(int driverId, String name, String country) {
	super();
	this.driverId = driverId;
	this.name = name;
	this.country = country;
}
public int getDriverId() {
	return driverId;
}
public void setDriverId(int driverId) {
	this.driverId = driverId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
 
 
  
}
