package com.airamerica;
//Class used to record passengers on a flight
public class Passengers {
	private String seatNumber;
	private Person person;
	private String Id;
	private int age;
	private String nationality;
	private String flight;
	private String flightExtraInfo;
	private String flightDate;
	public Passengers(String seatNumber, Person person, String id, int age, String nationality, String flight, String flightExtraInfo, String flightDate) {
		super();
		this.seatNumber = seatNumber;
		this.person = person;
		this.Id = id;
		this.age = age;
		this.nationality = nationality;
		this.flight = flight;
		this.flightExtraInfo = flightExtraInfo;
		this.flightDate = flightDate;
	}
	
	//Eclipse getters and setters
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	public String getFlightExtraInfo() {
		return flightExtraInfo;
	}
	public void setFlightExtraInfo(String flightExtraInfo) {
		this.flightExtraInfo = flightExtraInfo;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	

}
