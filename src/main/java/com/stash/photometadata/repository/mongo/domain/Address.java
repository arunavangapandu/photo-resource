package com.stash.photometadata.repository.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address {
	
	@Id
	private String id;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	
	//public Address() {}
	
	/*public Address(String street, String city, String state, String zip) {
		//super();
		this.setStreet(street);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
	}*/
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
}
