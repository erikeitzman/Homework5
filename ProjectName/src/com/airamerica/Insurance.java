package com.airamerica;

import com.airamerica.utils.Haversine;

import unl.cse.assignments.DataConverter;

import com.airamerica.utils.*;

public class Insurance extends Product{
	
	private String name;
	private String ageClass;
	private Double costPerMile;
	public Insurance(String productCode, String name, String ageClass, Double costPerMile) {
		super(productCode);
		this.name = name;
		this.ageClass = ageClass;
		this.costPerMile = costPerMile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAgeClass() {
		return ageClass;
	}
	public void setAgeClass(String ageClass) {
		this.ageClass = ageClass;
	}
	public Double getCostPerMile() {
		return costPerMile;
	}
	public void setCostPerMile(Double costPerMile) {
		this.costPerMile = costPerMile;
	}
	@Override
	public double calcSub(Double quantity, Double misc) {
		return quantity*misc*this.getCostPerMile();
	}
	//Prints the fare information for insurance
	@Override
	public void printFare(Double quantity, Double distance){
		String desc1 = "Insurance "+this.getName()+"("+this.getAgeClass()+")";
		String desc2 = String.format("%12s%.0f%s$%.2f%s%.0f%s","(", quantity, " units @ ", this.getCostPerMile(), " perMile x ",distance," miles)");
		System.out.printf("%-10s %-70s $%11.2f $%11.2f $%11.2f\n", this.getProductCode(), desc1, this.calcSub(quantity, distance), this.calcTax(quantity, distance), this.calcTotal(quantity, distance));
		System.out.println(desc2);
	}
}
