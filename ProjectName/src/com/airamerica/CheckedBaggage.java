package com.airamerica;

public class CheckedBaggage extends Product{
	private Product ticket;

	public CheckedBaggage(String productCode, Product ticket) {
		super(productCode);
		this.ticket = ticket;
	}

	public Product getTicket() {
		return ticket;
	}

	public void setTicket(Product ticket) {
		this.ticket = ticket;
	}
	
	@Override
	public double calcSub(Double quantity, Double distance) {
		return 25+(quantity-1)*35;
	}
	//Prints the fare information for baggage
	@Override
	public void printFare(Double quantity, Double distance){
		String desc1 = String.format("%s%.0f%s", "Baggage (", quantity, " units @ $25.00 for 1st and $35.00 onwards)");
		System.out.printf("%-10s %-70s $%11.2f $%11.2f $%11.2f\n", this.getProductCode(), desc1, this.calcSub(quantity, distance), this.calcTax(quantity, distance), this.calcTotal(quantity, distance));
	}
}
