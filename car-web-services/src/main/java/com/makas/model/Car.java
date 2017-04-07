package com.makas.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Car {
	
	private int id;
	private String owner;
	private int age;
	private Driver driver;
	
	
	public Car(int id, String owner, int age,Driver driver) {
		super();
		this.id = id;
		this.owner = owner;
		this.age = age;
		this.driver = driver;
	}
	public Car() {
	}

	@XmlElement(name="CarId")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	
}
