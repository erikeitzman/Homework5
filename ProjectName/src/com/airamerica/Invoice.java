package com.airamerica;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.airamerica.utils.StandardUtils;


public class Invoice  {
	private String InvoiceCode;
	private Customer customer;
	private Person person;
	private String invoiceDate;
	private ArrayList<SoldProduct> productList;
	private ArrayList<Passengers> passengerList;

	public Invoice(String invoiceCode, Customer customer, Person person, String invoiceDate) {
		InvoiceCode = invoiceCode;
		this.customer = customer;
		this.person = person;
		this.invoiceDate = invoiceDate;
		this.productList = new ArrayList<SoldProduct>();
		this.passengerList = new ArrayList<Passengers>();
	}
	
	//Prints the flight information section
	public void printFlightInformation(){
		System.out.println("Invoice "+this.getInvoiceCode());
		System.out.println("--------------------------------------------------------------------------------------------------------");
		System.out.println("AIR-AMERICA                                                         PNR");
		System.out.printf("%s: %3s %49s\n","ISSUE DATE", convertDay(this.getInvoiceDate()), StandardUtils.generatePNR());
		System.out.println("--------------------------------------------------------------------------------------------------------");
		System.out.println("FLIGHT INFORMATION");
		System.out.println("Day, Date       Flight     Class   DepartureCity and Time        ArrivalCity and Time      Aircraft");
		String flightDate = null;
		//Goes through all the products and checks if they're flights
		for (int i = 0; i < this.getProductList().size(); i++){
			if(flightBool(i)){
				//If the product is a flight all relevant information is printed
				Standard tempTicket = (Standard) this.getProductList().get(i).getProduct();
				for(int k = 0; k<this.getPassengerList().size(); k++){
					if(tempTicket.getProductCode().equals(this.getPassengerList().get(k).getFlight())){
						flightDate = this.getPassengerList().get(k).getFlightDate();
					}
				}
				String departureInfo = String.format("(%s) %s",tempTicket.getDepartureCity().getAirportCode(),tempTicket.get24HrDeparture());
				String arrivalInfo = String.format("(%s) %s",tempTicket.getArrivalCity().getAirportCode(),tempTicket.get24HrArrival());
				System.out.printf("%-15s %-10s %-6s %-30s %-25s %-10s\n", convertDayWeek(flightDate), tempTicket.getFlightNo(), tempTicket.getFlightClass(), tempTicket.getDepartureCity().getAddress().cityState(), tempTicket.getArrivalCity().getAddress().cityState(), tempTicket.getAircraftType());
				System.out.printf("%-33s %-30s %-35s\n","", departureInfo, arrivalInfo);
				System.out.printf("%15s %-20s %-8s %-6s\n", "", "Traveller", "Age", "SeatNo");
				//Loops through the passengers on the flight (in the invoice) and prints their information
				for(int j = 0; j<this.getPassengerList().size(); j++){
					if(this.getPassengerList().get(j).getFlight().equals(this.getProductList().get(i).getProduct().getProductCode())){
						System.out.printf("%15s %-20s %-8s %-6s\n", "",this.getPassengerList().get(j).getPerson().getLastName()+", "+this.getPassengerList().get(j).getPerson().getFirstName(), this.getPassengerList().get(j).getAge(), this.getPassengerList().get(j).getSeatNumber());
						//If it's the last passenger on a flight the extra flight information is printed
						if(this.getPassengerList().get(j).getFlightExtraInfo() != null && this.getPassengerList().get(j).getFlightExtraInfo().equals("None")==false){
							System.out.println("     *"+this.getPassengerList().get(j).getFlightExtraInfo());
						}
					}
				}
			}
		}
		System.out.println("--------------------------------------------------------------------------------------------------------");
	}
	//Prints information about the customer, pretty straightforward
	public void printCustomer(){
		System.out.println("CUSTOMER INFORMATION");
		System.out.printf("%-2s %s (%s)\n","",this.getCustomer().getName(), this.getCustomer().getCustomerCode());
		System.out.printf("%-2s [%s]\n","",this.getCustomer().getType());
		System.out.printf("%-2s %s\n","",this.getCustomer().getPrimaryContact().getLastName()+", "+this.getCustomer().getPrimaryContact().getFirstName());
		System.out.printf("%-2s %s\n","",this.getCustomer().getPrimaryContact().getAddress().getStreet());
		System.out.printf("%-2s %s %s %s %s\n","",this.getCustomer().getPrimaryContact().getAddress().getCity().trim(), this.getCustomer().getPrimaryContact().getAddress().getState().trim(), this.getCustomer().getPrimaryContact().getAddress().getZip().trim(), this.getCustomer().getPrimaryContact().getAddress().getCountry());
		System.out.println("Salesperson: "+this.getPerson().getLastName()+", "+this.getPerson().getFirstName());
		System.out.println("--------------------------------------------------------------------------------------------------------");
	}
	//Prints the product fare information
	public void printProductFares(){
		//2 below variables used for storing running totals
		Double totalSub = 0.0;
		Double totalTax = 0.0;
		String discountType = "DISCOUNT ( None )";
		System.out.println("FARES AND SERVICES");
		System.out.printf("%-10s %-70s %12s %12s %12s\n", "Code", "Item", "Subtotal", "Tax", "Total");
		//Loops through each product and prints the relevant information
		for (int i = 0; i < this.getProductList().size(); i++){
			Double quantity = this.getProductList().get(i).getQuantity();
			Double misc = this.getProductList().get(i).getMisc();
			//Adds the subtotal and tax to the running total
			totalSub = totalSub+this.getProductList().get(i).getProduct().calcSub(quantity, misc);
			totalTax = totalTax+this.getProductList().get(i).getProduct().calcTax(quantity, misc);
			//Special assistance is out here because our interface didn't end up working correctly with special assistance
			if(this.getProductList().get(i).getProduct().getClass().getName().equals("com.airamerica.SpecialAssistance")){
				SpecialAssistance sa = (SpecialAssistance) this.getProductList().get(i).getProduct();
				String desc1 = String.format("%s[%s, %s] (%s)", "Special Assistance for ", this.getPassengerList().get(0).getPerson().getLastName(), this.getPassengerList().get(0).getPerson().getFirstName(), sa.getTypeOfService());
				System.out.printf("%-10s %-70s $%11.2f $%11.2f $%11.2f\n", sa.getProductCode(), desc1, sa.calcSub(1.0, 1.0), sa.calcTax(1.0, 1.0), sa.calcTotal(1.0, 1.0));
			}else{
				//If not specialassistance the product is printed normally
				this.getProductList().get(i).getProduct().printFare(quantity, misc);
			}
		}
		//Calculates the discount depending on the customer
		if(this.getCustomer().getType().equals("Corporate")){
			discountType = "DISCOUNT ( 12.00% of SUBTOTAL )";
		}else if(this.getCustomer().getType().equals("Government")){
			discountType = "DISCOUNT ( NO TAX )";
		}
		//Prints out the information at the bottom of the fare section
		System.out.printf("%-81s %s\n"," ", "======================================");
		System.out.printf("%-81s $%11.2f $%11.2f $%11.2f\n", "SUB-TOTALS", totalSub, totalTax, totalSub+totalTax);
		System.out.printf("%-107s $%11.2f\n", discountType, this.Discount());
		System.out.printf("%-107s $%11.2f\n", "ADDITIONAL FEE", this.Fee());
		System.out.printf("%-107s $%11.2f\n", "TOTAL", this.Discount()+this.Fee()+totalTax+totalSub);
		System.out.println("--------------------------------------------------------------------------------------------------------");
		System.out.printf("\n\n\n");
	}
		//Prints the invoice summary (used in executive summary report)
	public void printSummary(){
		String name = this.person.getLastName() +", " + this.person.getFirstName();
		String customerName = this.getCustomer().getName()+"["+this.getCustomer().getType()+"]";
		System.out.printf("%-10s %-45s %-25s $%11.2f $%11.2f $%11.2f $%11.2f $%11.2f\n",this.getInvoiceCode(), customerName, name, this.FinalSub(), this.Fee(), this.FinalTax(), this.Discount(), this.FinalSub()+this.Discount()+this.Fee()+this.FinalTax());
	}
	
	//Eclipse getters and setters
	public String getInvoiceCode() {
		return InvoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		InvoiceCode = invoiceCode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public ArrayList<SoldProduct> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<SoldProduct> productList) {
		this.productList = productList;
	}

	public ArrayList<Passengers> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(ArrayList<Passengers> passengerList) {
		this.passengerList = passengerList;
	}
	//Adds passengers
	public void addPassenger(Passengers passenger){
		this.passengerList.add(passenger);
	}
	//Adds soldproducts
	public void addSoldProduct(SoldProduct soldProduct){
		this.productList.add(soldProduct);
	}
	//Checks if the product is a flight or not
	public Boolean flightBool(int i){
			if (this.getProductList().get(i).getProduct().getClass().getName().equals("com.airamerica.Standard") || this.getProductList().get(i).getProduct().getClass().getName().equals("com.airamerica.Offseason") || this.getProductList().get(i).getProduct().getClass().getName().equals("com.airamerica.Award")){
				return true;
			}else{
				return false;
			}
	}
	//Returns the final subtotal
	public Double FinalSub(){
		Double sub = 0.0;
		for (int i=0; i<this.getProductList().size(); i++){
			Double quantity = this.getProductList().get(i).getQuantity();
			Double misc = this.getProductList().get(i).getMisc();
			sub = sub + this.getProductList().get(i).getProduct().calcSub(quantity, misc);
		}
		return sub;
	}
	//Returns the final tax
	public Double FinalTax(){
		Double tax = 0.0;
		for (int i=0; i<this.getProductList().size(); i++){
			Double quantity = this.getProductList().get(i).getQuantity();
			Double misc = this.getProductList().get(i).getMisc();
			tax = tax + this.getProductList().get(i).getProduct().calcTax(quantity, misc);
		}
		return tax;
	}
	//Returns the final discount
	public Double Discount(){
		Double discount = 0.0;
		if(this.getCustomer().getType().equals("Corporate")){
			discount = (this.FinalSub()) * .12*(-1);
		}else if(this.getCustomer().getType().equals("Government")){
			discount = this.FinalTax()*(-1);
		}
		return discount;
	}
	//Returns the final fee
	public Double Fee(){
		Double fee = 0.0;
		if(this.getCustomer().getType().equals("Corporate")){
			fee = 40.0;
		}
		return fee;
	}
	
	//Converts the date to a different format
	public String convertDayWeek(String dateStr){
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date MyDate = null;
		try {
			MyDate = newDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		newDateFormat.applyPattern("EE, dd MMM yy");
		return newDateFormat.format(MyDate);
	}
	//Converts the date to a different format
	public String convertDay(String dateStr){
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date MyDate = null;
		try {
			MyDate = newDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		newDateFormat.applyPattern("MMM dd, yyyy");
		return newDateFormat.format(MyDate);
	}
}