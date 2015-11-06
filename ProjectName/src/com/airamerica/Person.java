package com.airamerica;
/*
/* A partial implementation representing a 
 * Person */
import java.util.ArrayList;
import java.util.List;

public class Person {
//Class for persons	
	
	private String personCode;
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNo;
	private List<String> emails;
	

	//eclipse constructor
	public Person(String personCode, String firstName, String lastName, Address address, String phoneNo) {
		this.personCode = personCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNo = phoneNo;
		this.emails = new ArrayList<String>();
	}
	
	//Eclipse getters and setters
	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public Address getAddress() {
		return this.address;
	}
	
	public List<String> getEmails(){
		return this.emails;
	}
	
	public void setEmails(List<String> emails)
	{
		this.emails = emails;
	}
		
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	// TODO: Add additional methods here
	public void addEmail(String email) {
		this.emails.add(email);
	}
	
}
