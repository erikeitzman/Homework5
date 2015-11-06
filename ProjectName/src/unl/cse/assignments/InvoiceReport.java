package unl.cse.assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.airamerica.Airport;
import com.airamerica.Customer;
import com.airamerica.Invoice;
import com.airamerica.Passengers;
import com.airamerica.Person;
import com.airamerica.Product;
import com.airamerica.SoldProduct;
import com.airamerica.Standard;

/* Assignment 3,5 and 6 (Project Phase-II,IV and V) */

public class InvoiceReport {
	private static Invoice[] importInvoice(){
		//Initializing variables
		Airport airportArr[] = new Airport[1];
		Person personArr[] = new Person[1];
		Customer customerArr[] = new Customer[1];
		Product productArr[] = new Product[1];
		//Scans in the invoices
		Scanner s = DataConverter.scanFileIn("data/Invoices.dat");
		Invoice invoiceArr[] = new Invoice[Integer.parseInt(s.nextLine())];
		//Gets all the other data arrays
		airportArr = DataConverter.airportToArray();
		personArr = DataConverter.personToArray();
		customerArr = DataConverter.customersToArray(personArr);
		productArr = DataConverter.productsToArray(airportArr);
		//Adds the information to each invoice, line by line
		for (int i = 0; i < invoiceArr.length; i++){
			String line = s.nextLine();
			String array[] = line.split(";");
			String array2[] = array[4].split(",");
			Customer b = DataConverter.findCustomer(customerArr, array[1]);
			Person c = DataConverter.findPerson(personArr, array[2]);
			Invoice a = new Invoice(array[0], b, c, array[3]);
			for (int j = 0; j<array2.length; j++){
				//Adds in all the products as soldproducts
				String array3[] = array2[j].split(":");
				Double quantity = SoldProduct.findQuantity(productArr, array2[j]);
				Double misc = SoldProduct.findMisc(productArr, array2[j]);
				SoldProduct tempProd = new SoldProduct(DataConverter.findTicket(productArr, array3[0]), quantity, misc);
				a.addSoldProduct(tempProd);
				String flightDate = array3[1];
				//Adds in teach customer if the product is a flight
				if(array3.length > 3){
					int passengerCount = Integer.parseInt(array3[2]);
					String extraFlightInfo = null;
					for (int k = 0; k < passengerCount; k++){
						if(k==passengerCount-1 || (array3.length) % 5 == 1){
							extraFlightInfo = array3[8+5*k];
						}
						Passengers tempPass = new Passengers(array3[3+k*5], DataConverter.findPerson(personArr, array3[4+k*5]) , array3[5+k*5], Integer.parseInt(array3[6+k*5]), array3[7+k*5], array3[0], extraFlightInfo, flightDate);
						a.addPassenger(tempPass);

					}
				}
			}
			//Adds the invoices to the array
			invoiceArr[i]=a;
		}
		return invoiceArr;
	}
	//Main method which does everything
	public static void main(String args[]) {
		Invoice[] invoices = importInvoice();
		printSummaryReport(invoices);
		System.out.println("Individual Invoice Detail Reports");
		System.out.println("==================================================");
		//Loops through all the arrays and adds the important details
		for (int i=0; i<invoices.length; i++){
			invoices[i].printFlightInformation();
			invoices[i].printCustomer();
			invoices[i].printProductFares();
		}
		System.out.println("========================================================================================================================");
	}
	//Prints the summary report
	public static void printSummaryReport(Invoice[] invoiceArr){
		Double totalSub=0.0;
		Double totalTax=0.0;
		Double totalFee=0.0;
		Double totalDiscount=0.0;
		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		//Header
		System.out.printf("%-10s %-45s %-25s %12s %12s %12s %12s %12s\n","Invoice", "Customer", "Salesperson", "Subtotal", "Fees", "Taxes", "Discount", "Total");
		//Loops through invoices and adds up numbers
		for(int i=0; i<invoiceArr.length; i++){
			invoiceArr[i].printSummary();
			totalSub += invoiceArr[i].FinalSub();
			totalTax += invoiceArr[i].FinalTax();
			totalFee += invoiceArr[i].Fee();
			totalDiscount += invoiceArr[i].Discount();
		}
		//Prints out the end of the section
		System.out.println("===================================================================================================================================================");
		System.out.printf("%-82s $%11.2f $%11.2f $%11.2f $%11.2f $%11.2f\n\n\n\n", "TOTALS", totalSub, totalFee, totalTax, totalDiscount, totalSub+totalFee+totalTax+totalDiscount);
	}
}