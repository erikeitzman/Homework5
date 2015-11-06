package com.airamerica;

import unl.cse.assignments.DataConverter;
//This class records which products were sold with quantity and misc
public class SoldProduct {
	private Product product;
	private Double quantity;
	private Double misc;
	public SoldProduct(Product product, Double quantity, Double misc) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.misc = misc;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getMisc() {
		return misc;
	}
	public void setMisc(Double misc) {
		this.misc = misc;
	}
	//Finds misc from a product array and string. usually ends up being distance
	public static Double findMisc(Product productArr[], String productString){
		String array[] = productString.split(":");
		Double misc = 0.0;
		if(DataConverter.findClass(productArr, array[0]) == "com.airamerica.Insurance"){
			misc = DataConverter.findTicket(productArr, array[2]).distance();
		}else{
			misc = 1.0;
		}
		return misc;
	}
	//Finds the quantity sold
	public static Double findQuantity(Product productArr[], String productString){
		String array[] = productString.split(":");
		Double quantity = 1.0;
		if(DataConverter.findClass(productArr, array[0]) == "com.airamerica.Insurance" || DataConverter.findClass(productArr, array[0]) == "com.airamerica.CheckedBaggage" || DataConverter.findClass(productArr, array[0]) == "com.airamerica.Refreshment"){
			quantity = Double.parseDouble(array[1]);
		}else if(DataConverter.findClass(productArr, array[0]) == "com.airamerica.SpecialAssistance"){
			quantity = 1.0;
		}else{
			quantity = Double.parseDouble(array[2]);
		}
		return quantity;
	}
}
