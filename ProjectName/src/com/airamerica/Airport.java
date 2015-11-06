package com.airamerica;

public class Airport {
	//Class for airports
	private String airportCode;
	private String name;
	private Address address;
	private double latitude;
	private double longitude;
	private double passengerFacilityFee;
	
	//Constructor made by eclipse
	public Airport(String airportCode, String name, Address address, double latitudeDegs, double latitudeMins, double longitudeDegs, double longitudeMins, double passengerFacilityFee){
		this.airportCode = airportCode;
		this.name = name;
		this.address = address;
		//Sets latitude and longitude
		this.latitude = latitudeDegs + latitudeMins/60;
		this.longitude = longitudeDegs + longitudeMins/60;
		this.setPassengerFacilityFee(passengerFacilityFee);
	}
	
	//Eclipse getters and setters
	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public double getLatitudes() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitudes() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getPassengerFacilityFee() {
		return passengerFacilityFee;
	}

	public void setPassengerFacilityFee(double passengerFacilityFee) {
		this.passengerFacilityFee = passengerFacilityFee;
	}
	

}
