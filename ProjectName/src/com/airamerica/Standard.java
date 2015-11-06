package com.airamerica;

import com.airamerica.utils.Haversine;

public class Standard extends Product {
	private Airport departureCity;
	private Airport arrivalCity;
	private String departureDateTime;
	private String arrivalDateTime;
	private String flightNo;
	private String flightClass;
	private String aircraftType;
	private Double costPerMile;
	
	public Standard(String productCode, Airport departureCity, Airport arrivalCity, String departureDateTime,
			String arrivalDateTime, String flightNo, String flightClass, String aircraftType) {
		super(productCode);
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.departureDateTime = departureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.flightNo = flightNo;
		this.flightClass = flightClass;
		this.aircraftType = aircraftType;
		if (this.flightClass.equals("BC")){
			this.costPerMile = 0.5;
		}else if(this.flightClass.equals("EP")){
			this.costPerMile = 0.2;
		}else{
			this.costPerMile = 0.15;
		}
	}

	public Airport getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(Airport departureCity) {
		this.departureCity = departureCity;
	}

	public Airport getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(Airport arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public String getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public String getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	public Double getCostPerMile() {
		return costPerMile;
	}

	public void setCostPerMile(Double costPerMile) {
		this.costPerMile = costPerMile;
	}
	//Changes departure time to 12 hour AM/PM from 24hr
	public String get24HrDeparture(){
		String[] hourMinute= this.getDepartureDateTime().split(":");
		String ampm = "AM";
		int minute = Integer.parseInt(hourMinute[1]);
		int hour = Integer.parseInt(hourMinute[0]);
		if(hour>12){
			hour = hour-12;
			ampm = "PM";
		}
		if(hour==0){
			hour = 12;
		}
		return String.format("%d:%02d%s",hour,minute,ampm);
	}
	//Changes arrival time to 12 hour AM/PM from 24hr
	public String get24HrArrival(){
		String[] hourMinute= this.getArrivalDateTime().split(":");
		String ampm = "AM";
		int minute = Integer.parseInt(hourMinute[1]);
		int hour = Integer.parseInt(hourMinute[0]);
		if(hour>12){
			hour = hour-12;
			ampm = "PM";
		}
		if(hour==0){
			hour = 12;
		}
		return String.format("%d:%02d%s",hour,minute,ampm);
	}
	
	@Override
	public double distance(){
		return Haversine.getMiles(this.getArrivalCity().getLatitudes(), this.getArrivalCity().getLongitudes(), this.getDepartureCity().getLatitudes(), this.getDepartureCity().getLongitudes());
	}

	@Override
	public double calcTax(Double quantity, Double distance) {
		return this.calcSub(quantity, distance)*.075+quantity*(4+5.6+this.getArrivalCity().getPassengerFacilityFee());
	}

	@Override
	public double calcSub(Double quantity, Double distance) {
		return this.distance()*this.getCostPerMile()*quantity;
		}

	@Override
	public double calcTotal(Double quantity, Double distance) {
		return this.calcTax(quantity, distance)+this.calcSub(quantity, distance);
	}
	//Prints the fare
	@Override
	public void printFare(Double quantity, Double distance){
		String distanceStr = String.format("%.2f",this.distance());
		String desc1 = "StandardTicket("+this.getFlightClass()+") "+this.getDepartureCity().getAirportCode()+" to "+this.getArrivalCity().getAirportCode()+" ("+distanceStr+" miles)";
		String desc2 = String.format("%12s%.0f%s$%.2f%s","(", quantity, " units @ ", this.calcSub(1.0, distance), "/unit)");
		System.out.printf("%-10s %-70s $%11.2f $%11.2f $%11.2f\n", this.getProductCode(), desc1, this.calcSub(quantity, distance), this.calcTax(quantity, distance), this.calcTotal(quantity, distance));
		System.out.println(desc2);
	}
}
