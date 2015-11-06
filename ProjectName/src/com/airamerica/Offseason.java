package com.airamerica;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Offseason extends Standard{
	
	private String seasonStartDate;
	private String seasonEndDate;
	private double rebate;
	
	public Offseason(String productCode, Airport departurecity, Airport arrivalCity, String departureDateTime,
			String arrivalDateTime, String flightNo, String flightClass, String aircraftType,
			String seasonStartDate, String seasonEndDate, double rebate) {
		super(productCode, departurecity, arrivalCity, departureDateTime, arrivalDateTime, flightNo, flightClass,
				aircraftType);
		this.seasonStartDate = seasonStartDate;
		this.seasonEndDate = seasonEndDate;
		this.rebate = rebate;
	}

	public String getSeasonStartDate() {
		return seasonStartDate;
	}

	public void setSeasonStartDate(String seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}

	public String getSeasonEndDate() {
		return seasonEndDate;
	}

	public void setSeasonEndDate(String seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}

	public double getRebate() {
		return rebate;
	}

	public void setRebate(double rebate) {
		this.rebate = rebate;
	}

	@Override
	public double calcFee(Double quantity, Double distance){
		return 30.0;
	}
	
	@Override
	public double calcTax(Double quantity, Double distance) {
		return calcSub(quantity, distance)*.075+quantity*(4+5.6+this.getArrivalCity().getPassengerFacilityFee());
	}

	@Override
	public double calcSub(Double quantity, Double distance) {
		return (20+quantity*(1-this.getRebate())*this.distance()*this.getCostPerMile());
		}
	@Override
	public double calcTotal(Double quantity, Double distance) {
		return this.calcTax(quantity, distance)+this.calcSub(quantity, distance);
	}
	@Override
	public void printFare(Double quantity, Double distance){
		String distanceStr = String.format("%.2f",this.distance());
		String desc1 = "OffseasonTicket("+this.getFlightClass()+") "+this.getDepartureCity().getAirportCode()+" to "+this.getArrivalCity().getAirportCode()+" ("+distanceStr+" miles)"+String.format("%.2f%s",100*this.getRebate(),"% off");
		String desc2 = String.format("%12s%.0f%s$%.2f%s","(", quantity, " units @ ", this.calcSub(1.0, distance), "/unit)");
		System.out.printf("%-10s %-70s $%11.2f $%11.2f $%11.2f\n", this.getProductCode(), desc1, this.calcSub(quantity, distance), this.calcTax(quantity, distance), this.calcSub(quantity, distance)+this.calcTax(quantity, distance));
		System.out.println(desc2);
	}
	public boolean outsideRange(String dateStr){
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date date = null;
		Date startDate = null;
		Date endDate = null;
		try {
			date = format.parse(dateStr);
			startDate = format.parse(getSeasonStartDate());
			endDate = format.parse(getSeasonEndDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(date.before(endDate) && date.after(startDate)){
			return true;
		}else{
			return false;
		}
	}

}
