package com.airamerica.interfaces;
//Interface for products
public interface ProductInterface {

	double calcFee(Double quantity, Double distance);
	double calcTax(Double quantity, Double distance);
	double calcSub(Double quantity, Double distance);
	double calcTotal(Double quantity, Double distance);
	void printFare(Double quantity, Double distance);
}
