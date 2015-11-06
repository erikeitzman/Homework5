package com.airamerica;

public class Refreshment extends Product{
	
	private String name;
	private Double cost;
	
	public Refreshment(String productCode, String name, Double cost) {
		super(productCode);
		this.name = name;
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	@Override
	//Checks whether to charge .95 or 1.00 the cost
	public double calcSub(Double quantity, Double distance){
		if (distance == 1.0){
			return this.getCost()*quantity*.95;
		}
		else{
			return this.getCost()*quantity;
		}
	}
	//Prints the fare information
	public void printFare(Double quantity, Double distance){
		String desc1 = String.format("%s (%.0f%s$%.2f%s)", this.getName(), quantity, " units @ ", this.getCost(),"/unit with 5% off");
		System.out.printf("%-10s %-70s $%11.2f $%11.2f $%11.2f\n", this.getProductCode(), desc1, this.calcSub(quantity, distance), this.calcTax(quantity, distance), this.calcTotal(quantity, distance));
	}
	
}
