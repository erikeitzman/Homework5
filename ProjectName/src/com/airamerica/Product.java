package com.airamerica;

import com.airamerica.interfaces.ProductInterface;

public abstract class Product implements ProductInterface{
	private String productCode;

	public Product(String productCode) {
		super();
		this.productCode = productCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public double calcTax(Double quantity, Double distance) {
		return calcSub(quantity, distance)*.04;
	}

	public double calcFee(Double quantity, Double distance) {
		return 0;
	}

	public double calcTotal(Double quantity, Double distance) {
		return calcSub(quantity, distance)+calcTax(quantity, distance);
	}
	
	public double calcSub(Double quantity, Double distance){
		return 0;
	}
	public double distance(){
		return 0;
	}
	//Default printFare (never used)
	public void printFare(Double quantity, Double distance){
		System.out.print("");
	}
}
