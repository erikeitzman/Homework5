package com.airamerica;

import com.airamerica.utils.Haversine;

public class Award extends Standard{

	private Double redemptionFee;
	private Double pointsPerMile;
	
	public Award(String productCode, Airport departurecity, Airport arrivalCity, String departureDateTime,
			String arrivalDateTime, String flightNo, String flightClass, String aircraftType, Double pointsPerMile) {
		super(productCode, departurecity, arrivalCity, departureDateTime, arrivalDateTime, flightNo, flightClass,
				aircraftType);
		this.redemptionFee = (double) 30;
		this.pointsPerMile = pointsPerMile;
	}

	public Double getRedemptionFee() {
		return redemptionFee;
	}

	public void setRedemptionFee(Double redemptionFee) {
		this.redemptionFee = redemptionFee;
	}

	public Double getPointsPerMile() {
		return pointsPerMile;
	}

	public void setPointsPerMile(Double pointsPerMile) {
		this.pointsPerMile = pointsPerMile;
	}
	
	public Double getRewardsCost(){
		return this.getPointsPerMile()*this.distance();
	}
	
	@Override
	public double calcTax(Double quantity, Double distance) {
		return 30*.075+quantity*(4+5.6+this.getArrivalCity().getPassengerFacilityFee());
	}

	@Override
	public double calcSub(Double quantity, Double distance) {
		return 30;
		}
	
	@Override
	public double calcTotal(Double quantity, Double distance) {
		return this.calcTax(quantity, distance)+this.calcSub(quantity, distance);
	}
	//Prints the fare information for an offseason ticket
	@Override
	public void printFare(Double quantity, Double distance){
		String distanceStr = String.format("%.2f",this.distance());
		String desc1 = "AwardsTicket("+this.getFlightClass()+") "+this.getDepartureCity().getAirportCode()+" to "+this.getArrivalCity().getAirportCode()+" ("+distanceStr+" miles)";
		String desc2 = String.format("%12s%.0f%s%.2f%s","(", quantity, " units @ ", this.distance()*this.getPointsPerMile(), " reward miles/unit with $30.0 RedemptionFee)");
		System.out.printf("%-10s %-70s $%11.2f $%11.2f $%11.2f\n", this.getProductCode(), desc1, this.calcSub(quantity, distance), this.calcTax(quantity, distance), this.calcTotal(quantity, distance));
		System.out.println(desc2);
	}
}
