package unl.cse.assignments;

/* Phase-I */
import com.airamerica.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Include imports for XML/JSON libraries if needed
import com.thoughtworks.xstream.XStream;

public class DataConverter {


	public static void main(String args[]) {
		Scanner s = null; 
		s = scanFileIn("data/Airports.dat");
		Airport airportArr[] = new Airport[Integer.parseInt(s.nextLine())];
		//Loops through all the lines of the airport data file and creates airport objects
		for (int i = 0; i < airportArr.length; i++){
			String line = s.nextLine();
			String array[] = line.split(";");
			String array2[] = array[2].split(",");
			String array3[] = array[3].split(",");
			Double latitudeDeg = Double.parseDouble(array3[0]);
			Double latitudeMin = Double.parseDouble(array3[1]);
			Double longitudeDeg = Double.parseDouble(array3[2]);
			Double longitudeMin = Double.parseDouble(array3[3]);
			//Creates and address for the airport
			Address a = new Address(array2[0], array2[1], array2[2], array2[3], array2[4]);
			//Makes the airport
			Airport b = new Airport(array[0], array[1], a, latitudeDeg, latitudeMin, longitudeDeg, longitudeMin, Double.parseDouble(array[4]));
			airportArr[i] = b;
		}
		s = scanFileIn("data/Persons.dat");
		//Loops through all the lines of the person data file and creates person objects
		Person personArr[] = new Person[Integer.parseInt(s.nextLine())];
		for (int i = 0; i < personArr.length; i++){
			String line = s.nextLine();
			String array[] = line.split(";");
			array[1] = array[1].replaceAll("\\s+","");
			String array1[] = array[1].split(",");
			String array2[] = array[2].split(",");
			Address a = new Address(array2[0], array2[1], array2[2], array2[3], array2[4]);
			Person b = new Person(array[0], array1[1], array1[0], a, array[3]);
			//Adds an email if the array has more than 4 members
			if (array.length > 4){
				String array3[] = array[4].split(",");
				for (int j = 0; j < array3.length; j++){
					b.addEmail(array3[j]);
				}
			}
			personArr[i] = b;
		}


		s = scanFileIn("data/Customers.dat");
		//Loops through all the lines of the customer data file and creates customer objects
		Customer customerArr[] = new Customer[Integer.parseInt(s.nextLine())];
		for (int i = 0; i < customerArr.length; i++){
			String line = s.nextLine();
			String array[] = line.split(";");
			Person b =  personArr[0];
			//Finds the person who matches the contact listed for the customer
			for (int j = 0; j < personArr.length; j++){
				if(personArr[j].getPersonCode().equals(array[2])){
					b =  personArr[j];
					j = personArr.length;
				}
			}
			Customer a = new Customer(array[0], array[1], b, array[3]);
			//Sets airline miles if there are more than 4 fields 
			if (array.length > 4){
				a.setAirlineMiles(Integer.parseInt(array[4]));
			}
			customerArr[i] = a;
		}

		s = scanFileIn("data/Products.dat");
		Product productArr[] = new Product[Integer.parseInt(s.nextLine())];
		List<Integer> checkedBagIndex = new ArrayList<Integer>();
		List<String> checkedBagCode = new ArrayList<String>();
		List<String> checkedBagTicketCode = new ArrayList<String>();
		for (int i = 0; i < productArr.length; i++){
			String line = s.nextLine();
			String array[] = line.split(";");
			Airport b = airportArr[0];
			Airport c = airportArr[1];
			//Checks product types
			if (array[1].equals("SC")){
				//If the product is a checked bag we mark the spot in the array for later and record the information
				checkedBagIndex.add(i);
				checkedBagCode.add(array[0]);
				checkedBagTicketCode.add(array[2]);
			}else if(array[1].equals("SI")){
				//If it's not a checked bag the correct object is created
				Insurance a = new Insurance(array[0], array[2], array[3], Double.parseDouble(array[4]));
				productArr[i] = a;
			}else if(array[1].equals("SR")){
				Refreshment a = new Refreshment(array[0], array[2], Double.parseDouble(array[3]));
				productArr[i] = a;
			}else if(array[1].equals("SS")){
				SpecialAssistance a = new SpecialAssistance(array[0], array[2]);
				productArr[i] = a;
			}else if(array[1].equals("TA")){
				//Tickets have to find which city/airport they're connected to
				b = findCity(airportArr, array[2]);
				c = findCity(airportArr, array[3]);
				Award a = new Award(array[0], b, c, array[4], array[5], array[6], array[7], array[8], Double.parseDouble(array[9]));
				productArr[i] = a;
			}else if(array[1].equals("TO")){
				b = findCity(airportArr, array[4]);
				c = findCity(airportArr, array[5]);
				Offseason a = new Offseason(array[0], b, c, array[6], array[7], array[8], array[9], array[10], array[2], array[3], Double.parseDouble(array[11]));
				productArr[i] = a;
			}else{
				b = findCity(airportArr, array[2]);
				c = findCity(airportArr, array[3]);
				Standard a = new Standard(array[0], b, c, array[4], array[5], array[6], array[7], array[8]);
				productArr[i] = a;
			}		
		}
		for (int i=0; i < checkedBagIndex.size(); i++){
			CheckedBaggage a = new CheckedBaggage(checkedBagCode.get(i), findTicket(productArr, checkedBagTicketCode.get(i)));
			productArr[checkedBagIndex.get(i)] = a;
			}
		
		s = scanFileIn("data/Invoices.dat");
		Invoice invoiceArr[] = new Invoice[Integer.parseInt(s.nextLine())];
		//Loops through all the lines of the airport data file and creates airport objects
		for (int i = 0; i < invoiceArr.length; i++){
			int commas = 0;
			String line = s.nextLine();
			String array[] = line.split(";");
			String array2[] = array[4].split(",");
			Customer b =  customerArr[0];
			Person c = personArr[0];
			
			for(int j = 0; j < line.length(); j++) {
			    if(line.charAt(j) == ','){
			    	commas++;
			    }
			}
			for (int j = 0; j<=commas; j++){
				String array3[] = array2[j].split(":");
			}
		}
	}
	



	//Scans in a data file at the specified path
	public static Scanner scanFileIn(String file){
		Scanner sc = null;
		try{
			sc = new Scanner(new File (file));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return sc;
	}

	//Finds the airport object from an array based on the airport code
	public static Airport findCity(Airport airportArr[], String airportCode){
		for (int i = 0; i < airportArr.length; i++){
			if(airportArr[i].getAirportCode().equals(airportCode)){
				return airportArr[i];
			}
		}
		return airportArr[0];
	}
	
	//Finds the ticket/product object from an array based on the ticket code
	public static Product findTicket(Product productArr[], String ticketCode){
		for (int i = 0; i < productArr.length; i++){
			if(productArr[i].getProductCode().equals(ticketCode)){
				return productArr[i];
			}
		}
		return productArr[0];
	}
	public static String findClass(Product productArr[], String productCode){
		for (int i = 0; i < productArr.length; i++){
			if(productArr[i].getProductCode().equals(productCode)){
				return productArr[i].getClass().getName();
			}
		}
		return "asdfasdfasdf";
	}
	
	public static Person findPerson(Person personArr[], String personCode){
		for (int k = 0; k < personArr.length; k++){
			if(personArr[k].getPersonCode().equals(personCode)){
				return personArr[k];
			}
		}
		Person p = personArr[0];
		p.setLastName("ONLINE");
		p.setFirstName("Null");
		return p;
	}
	
	public static Customer findCustomer(Customer customerArr[], String customerCode){
		for (int k = 0; k < customerArr.length; k++){
			if(customerArr[k].getCustomerCode().equals(customerCode)){
				return customerArr[k];
			}
		}
		return customerArr[0];
	}
	
	//Airport Scanner
	public static Airport[] airportToArray(){
		Scanner s = null; 
		s = scanFileIn("data/Airports.dat");
		Airport airportArr[] = new Airport[Integer.parseInt(s.nextLine())];
		//Loops through all the lines of the airport data file and creates airport objects
		for (int i = 0; i < airportArr.length; i++){
			String line = s.nextLine();
			String array[] = line.split(";");
			String array2[] = array[2].split(",");
			String array3[] = array[3].split(",");
			Double latitudeDeg = Double.parseDouble(array3[0]);
			Double latitudeMin = Double.parseDouble(array3[1]);
			Double longitudeDeg = Double.parseDouble(array3[2]);
			Double longitudeMin = Double.parseDouble(array3[3]);
			//Creates and address for the airport
			Address a = new Address(array2[0], array2[1], array2[2], array2[3], array2[4]);
			//Makes the airport
			Airport b = new Airport(array[0], array[1], a, latitudeDeg, latitudeMin, longitudeDeg, longitudeMin, Double.parseDouble(array[4]));
			airportArr[i] = b;
		}
		return airportArr;
	}
	
	public static Person[] personToArray(){
		Scanner s = scanFileIn("data/Persons.dat");
		//Loops through all the lines of the person data file and creates person objects
		Person personArr[] = new Person[Integer.parseInt(s.nextLine())];
		for (int i = 0; i < personArr.length; i++){
			String line = s.nextLine();
			String array[] = line.split(";");
			array[1] = array[1].replaceAll("\\s+","");
			String array1[] = array[1].split(",");
			String array2[] = array[2].split(",");
			Address a = new Address(array2[0], array2[1], array2[2], array2[3], array2[4]);
			Person b = new Person(array[0], array1[1], array1[0], a, array[3]);
			//Adds an email if the array has more than 4 members
			if (array.length > 4){
				String array3[] = array[4].split(",");
				for (int j = 0; j < array3.length; j++){
					b.addEmail(array3[j]);
				}
			}
			personArr[i] = b;
		}
		return personArr;
	}
public static Customer[] customersToArray(Person[] personArr){
	Scanner s = scanFileIn("data/Customers.dat");
	//Loops through all the lines of the customer data file and creates customer objects
	Customer customerArr[] = new Customer[Integer.parseInt(s.nextLine())];
	for (int i = 0; i < customerArr.length; i++){
		String line = s.nextLine();
		String array[] = line.split(";");
		Person b =  personArr[0];
		//Finds the person who matches the contact listed for the customer
		for (int j = 0; j < personArr.length; j++){
			if(personArr[j].getPersonCode().equals(array[2])){
				b =  personArr[j];
				j = personArr.length;
			}
		}
		Customer a = new Customer(array[0], array[1], b, array[3]);
		//Sets airline miles if there are more than 4 fields 
		if (array.length > 4){
			a.setAirlineMiles(Integer.parseInt(array[4]));
		}
		customerArr[i] = a;
	}
	return customerArr;
}

public static Product[] productsToArray(Airport[] airportArr){

	Scanner s = scanFileIn("data/Products.dat");
	Product productArr[] = new Product[Integer.parseInt(s.nextLine())];
	List<Integer> checkedBagIndex = new ArrayList<Integer>();
	List<String> checkedBagCode = new ArrayList<String>();
	List<String> checkedBagTicketCode = new ArrayList<String>();
	for (int i = 0; i < productArr.length; i++){
		String line = s.nextLine();
		String array[] = line.split(";");
		Airport b = airportArr[0];
		Airport c = airportArr[1];
		//Checks product types
		if (array[1].equals("SC")){
			//If the product is a checked bag we mark the spot in the array for later and record the information
			checkedBagIndex.add(i);
			checkedBagCode.add(array[0]);
			checkedBagTicketCode.add(array[2]);
		}else if(array[1].equals("SI")){
			//If it's not a checked bag the correct object is created
			Insurance a = new Insurance(array[0], array[2], array[3], Double.parseDouble(array[4]));
			productArr[i] = a;
		}else if(array[1].equals("SR")){
			Refreshment a = new Refreshment(array[0], array[2], Double.parseDouble(array[3]));
			productArr[i] = a;
		}else if(array[1].equals("SS")){
			SpecialAssistance a = new SpecialAssistance(array[0], array[2]);
			productArr[i] = a;
		}else if(array[1].equals("TA")){
			//Tickets have to find which city/airport they're connected to
			b = findCity(airportArr, array[2]);
			c = findCity(airportArr, array[3]);
			Award a = new Award(array[0], b, c, array[4], array[5], array[6], array[7], array[8], Double.parseDouble(array[9]));
			productArr[i] = a;
		}else if(array[1].equals("TO")){
			b = findCity(airportArr, array[4]);
			c = findCity(airportArr, array[5]);
			Offseason a = new Offseason(array[0], b, c, array[6], array[7], array[8], array[9], array[10], array[2], array[3], Double.parseDouble(array[11]));
			productArr[i] = a;
		}else{
			b = findCity(airportArr, array[2]);
			c = findCity(airportArr, array[3]);
			Standard a = new Standard(array[0], b, c, array[4], array[5], array[6], array[7], array[8]);
			productArr[i] = a;
		}		
	}
	for (int i=0; i < checkedBagIndex.size(); i++){
		CheckedBaggage a = new CheckedBaggage(checkedBagCode.get(i), findTicket(productArr, checkedBagTicketCode.get(i)));
		productArr[checkedBagIndex.get(i)] = a;
		}
	return productArr;
}
}