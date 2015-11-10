package com.airamerica.interfaces;

import java.sql.*;

import org.apache.log4j.Logger;

import com.airamerica.Person;

import JDBCStuff.DatabaseInfo;

/* Assignment 5 - (Phase IV) */
/* NOTE: Donot change the package name or any of the method signatures.
 *  
 * There are 23 methods in total, all of which need to be completed as a 
 * bare minimum as part of the assignment.You can add additional methods 
 * for testing if you feel.
 * 
 * It is also recommended that you write a separate program to read
 * from the .dat files and test these methods to insert data into your 
 * database.
 * 
 * Donot forget to change your reports generation classes to read from 
 * your database instead of the .dat files.
 */

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class InvoiceData {
	private static Logger log = Logger.getLogger(InvoiceData.class.getName());
	
	//Testing thing
	public static void main(String args[]) {
		// Comment so i can push
		InvoiceData.addPerson("123", "john", "smith", "123-456-7890", "fake st", "lincoln", "NE", "68508", "USA");
		InvoiceData.addPerson("456", "jane", "smith", "123-456-7890", "fake st", "lincoln", "NE", "68508", "USA");
		InvoiceData.addPerson("789", "Jim", "Johns", "fake number", "Dodge", "Omaha", "NE", "68700", "USA");
		InvoiceData.addPerson("123123", "James", "Johns", "fake number", "DodgeST", "Omaha", "NE", "68700", "USA");
		InvoiceData.addAirport("LAX", "Los Angeles", "LA St", "LA", "CA", "12345", "USA", 10, 20, 100, 120, 0);
		InvoiceData.addCustomer("C001", "Government", "123", "UNL", 100000);
		InvoiceData.addSpecialAssistance("i3re", "wheelchair");
	}

	/**
	 * Method that removes every person record from the database
	 */	
	public static void removeAllPersons() {
	Connection conn = DatabaseInfo.getConnection();
	PreparedStatement ps = null;
	ResultSet rs = null;
	String removeQuery = "Delete from Person;";
	try {
		ps = conn.prepareStatement(removeQuery);
		ps.executeUpdate();
		ps.close();
	} catch (SQLException e1) {
		log.error("SQLException", e1);
	}
	}

	
	 //Method to add a person record to the database with the provided data. 
	 
	public static void addPerson(String personCode, String firstName, String lastName, 
			String phoneNo, String street, String city, String state, 
			String zip, String country) { 
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String addressQuery = "Insert into Address (Street, City, State, Zip, Country) values (?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(addressQuery);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
		String addressIDQuery = "Select ID from Address where Street = ? and City = ? and State = ? and Zip = ? and Country = ?;";
		int addressID = 0;
		Person a = null;
		try {
			ps = conn.prepareStatement(addressIDQuery);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			rs = ps.executeQuery();
			while(rs.next()){
			addressID = rs.getInt("ID");
			}
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
			String personCodeQuery = "Select Code from Person where Code like ?;";
		try {
			ps = conn.prepareStatement(personCodeQuery);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			
			if (rs.next()){
				String removePerson = "Delete from Person where Code = ?;";
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(removePerson);
				ps.setString(1,  personCode);
				ps.executeUpdate();
				ps.close();
			}else{
			ps.close();
			rs.close();
			}
			String personQuery = "Insert into Person (Code, FirstName, LastName, AddressCode, PhoneNo) values (?,?,?,?,?);";
			ps = conn.prepareStatement(personQuery);
			ps.setString(1,personCode);
			ps.setString(2,firstName);
			ps.setString(3,lastName);
			ps.setInt(4,addressID);
			ps.setString(5,phoneNo);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}

	// Method that removes every airport record from the database
	 
	public static void removeAllAirports() {
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String removeQuery = "Delete from Airport;";
		try {
			ps = conn.prepareStatement(removeQuery);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}
	
	
	 // Method to add a airport record to the database with the provided data. 
	 
	public static void addAirport(String airportCode, String name, String street, 
			String city, String state, String zip, String country, 
			int latdegs, int latmins, int londegs, int lonmins, 
			double passengerFacilityFee) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String addressQuery = "Insert into Address (Street, City, State, Zip, Country) values (?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(addressQuery);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
		String addressIDQuery = "Select ID from Address where Street = ? and City = ? and State = ? and Zip = ? and Country = ?;";
		int addressID = 0;
		try {
			ps = conn.prepareStatement(addressIDQuery);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			rs = ps.executeQuery();
			while(rs.next()){
			addressID = rs.getInt("ID");
			}
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
			String airportCodeQuery = "Select Code from Airport where Code like ?;";
		try {
			ps = conn.prepareStatement(airportCodeQuery);
			ps.setString(1, airportCode);
			rs = ps.executeQuery();
			
			if (rs.next()){
				String removePerson = "Delete from Person where Code = ?;";
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(removePerson);
				ps.setString(1,  airportCode);
				ps.executeUpdate();
				ps.close();
			}else{
			ps.close();
			rs.close();
			
			double latitude = latdegs + latmins/60;
			double longitude = londegs + lonmins/60;
			String airportQuery = "Insert into Airport (Code, Name, AddressCode, Latitude, Longitude, PassengerFacilityFee) values (?,?,?,?,?,?);";
			ps = conn.prepareStatement(airportQuery);
			ps.setString(1, airportCode);
			ps.setString(2, name);
			ps.setInt(3,addressID);
			ps.setDouble(4, latitude);
			ps.setDouble(5, longitude);
			ps.setDouble(6, passengerFacilityFee);
			ps.executeUpdate();
			ps.close();
			}
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}
	
	
	  /*Adds an email record corresponding person record corresponding to the
	  provided <code>personCode</code> */
	 
	public static void addEmail(String personCode, String email) { }
	
	
	 //Method that removes every customer record from the database
	 
	public static void removeAllCustomers() { 
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String removeQuery = "Delete from Customer;";
		try {
			ps = conn.prepareStatement(removeQuery);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}

	
	// Method to add a customer record to the database with the provided data. 
	 
	public static void addCustomer(String customerCode, String customerType, 
			String primaryContactPersonCode, String name, 
			int airlineMiles) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String customerCodeQuery = "Select Code from Customer where Code like ?;";
		try {
			ps = conn.prepareStatement(customerCodeQuery);
			ps.setString(1, customerCode);
			rs = ps.executeQuery();
			
			if (rs.next()){
				String removeCustomer = "Delete from Customer where Code = ?;";
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(removeCustomer);
				ps.setString(1,  customerCode);
				ps.executeUpdate();
				ps.close();
			}else{
			ps.close();
			rs.close();
			}
			String customerQuery = "Insert into Customer (Code, Type, PersonCode, Name, AirlineMiles) values (?,?,?,?,?);";
			ps = conn.prepareStatement(customerQuery);
			ps.setString(1, customerCode);
			ps.setString(2, customerType);
			ps.setString(3, primaryContactPersonCode);
			ps.setString(4, name);
			ps.setInt(5, airlineMiles);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}

	
	 //Removes all product records from the database
	 
	public static void removeAllProducts() {
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String removeQuery = "Delete from Products;";
		try {
			ps = conn.prepareStatement(removeQuery);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}

	
	 /*Adds an standardTicket record to the database with the
	 provided data.  */
	public static void addStandardTicket(String productCode,String depAirportCode, 
			String arrAirportCode, String depTime, String arrTime, 
			String flightNo, String flightClass, String aircraftType) { 
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
			String standardCodeQuery = "Select Code from Standard where Code like ?;";
		try {
			ps = conn.prepareStatement(standardCodeQuery);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			
			if (rs.next()){
				String removeStandard = "Delete from Standard where Code = ?;";
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(removeStandard);
				ps.setString(1,  productCode);
				ps.executeUpdate();
				ps.close();
			}else{
			ps.close();
			rs.close();
			}
			String standardQuery = "Insert into Standard (Code, DepartureCity, ArrivalCity, DepartureDateTime, ArrivalDateTime, FlightNo, FlightClass, AircraftType) values (?,?,?,?,?,?,?,?);";
			String productsIDQuery = "select ID from Products where Code = ?;";
			int productID = 0;
			
			try{
				ps = conn.prepareStatement(productsIDQuery);
				ps.setString(1, productCode);
				rs = ps.executeQuery();
				
				while (rs.next()){
					productID = rs.getInt("ID");					
				}
				
			} catch (SQLException e1) {
				log.error("SQLException", e1);
			}
			
			ps = conn.prepareStatement(standardQuery);
			ps.setString(1, productCode);
			ps.setString(2, depAirportCode);
			ps.setString(3, arrAirportCode);
			ps.setString(4, depTime);
			ps.setString(5, arrTime);
			ps.setString(6, flightNo);
			ps.setString(7, flightClass);
			ps.setString(8, aircraftType);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}
		
	
	  
	 /* Adds an offSeasonTicket record to the database with the
	  provided data. */
	public static void addOffSeasonTicket(String productCode, String seasonStartDate, 
			String seasonEndDate, String depAirportCode, String arrAirportCode, 
			String depTime, String arrTime,	String flightNo, String flightClass, 
			String aircraftType, double rebate) {
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
			String offSeasonCodeQuery = "Select Code from OffSeason where Code like ?;";
		try {
			ps = conn.prepareStatement(offSeasonCodeQuery);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			
			if (rs.next()){
				String removeOffSeason = "Delete from OffSeason where Code = ?;";
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(removeOffSeason);
				ps.setString(1,  productCode);
				ps.executeUpdate();
				ps.close();
			}else{
			ps.close();
			rs.close();
			}
			String offSeasonQuery = "Insert into OffSeason (Code, SeasonStartDate, SeasonEndDate, DepartureCity, ArrivalCity, DepartureDateTime, ArrivalDateTime, FlightNo, FlightClass, AircraftType, Rebate) values (?,?,?,?,?,?,?,?,?,?,?);";
			String productsIDQuery = "select ID from Products where Code = ?;";
			int productID = 0;
			
			try{
				ps = conn.prepareStatement(productsIDQuery);
				ps.setString(1, productCode);
				rs = ps.executeQuery();
				
				while (rs.next()){
					productID = rs.getInt("ID");					
				}
				
			} catch (SQLException e1) {
				log.error("SQLException", e1);
			}
			
			ps = conn.prepareStatement(offSeasonQuery);
			ps.setString(1, productCode);
			ps.setString(2, seasonStartDate);
			ps.setString(3, seasonEndDate);
			ps.setString(4, depAirportCode);
			ps.setString(5, arrAirportCode);
			ps.setString(6, depTime);
			ps.setString(7, arrTime);
			ps.setString(8, flightNo);
			ps.setString(9, flightClass);
			ps.setString(10, aircraftType);
			ps.setDouble(11, rebate);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}
	
	 /*Adds an awardsTicket record to the database with the
	 provided data.*/
	public static void addAwardsTicket(String productCode,String depAirportCode, 
			String arrAirportCode, String depTime, String arrTime, 
			String flightNo, String flightClass, 
			String aircraftType, double pointsPerMile) { 
		

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
			String awardCodeQuery = "Select Code from Award where Code like ?;";
		try {
			ps = conn.prepareStatement(awardCodeQuery);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			
			if (rs.next()){
				String removeAward = "Delete from Award where Code = ?;";
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(removeAward);
				ps.setString(1,  productCode);
				ps.executeUpdate();
				ps.close();
			}else{
			ps.close();
			rs.close();
			}
			String awardQuery = "Insert into Award (Code, DepartureCity, ArrivalCity, DepartureDateTime, ArrivalDateTime, FlightNo, FlightClass, AircraftType, PointsPerMile) values (?,?,?,?,?,?,?,?,?);";
			String productsIDQuery = "select ID from Products where Code = ?;";
			int productID = 0;
			
			try{
				ps = conn.prepareStatement(productsIDQuery);
				ps.setString(1, productCode);
				rs = ps.executeQuery();
				
				while (rs.next()){
					productID = rs.getInt("ID");					
				}
				
			} catch (SQLException e1) {
				log.error("SQLException", e1);
			}
			
			ps = conn.prepareStatement(awardQuery);
			ps.setString(1, productCode);
			ps.setString(2, depAirportCode);
			ps.setString(3, arrAirportCode);
			ps.setString(4, depTime);
			ps.setString(5, arrTime);
			ps.setString(6, flightNo);
			ps.setString(7, flightClass);
			ps.setString(8, aircraftType);
			ps.setDouble(9, pointsPerMile);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	} 
	
	/* Adds a CheckedBaggage record to the database with the
	 provided data. */
	public static void addCheckedBaggage(String productCode, String ticketCode) { 
		

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
			String checkedBaggageCodeQuery = "Select Code from CheckedBaggage where Code like ?;";
		try {
			ps = conn.prepareStatement(checkedBaggageCodeQuery);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			
			if (rs.next()){
				String removeCheckedBaggage = "Delete from CheckedBaggage where Code = ?;";
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(removeCheckedBaggage);
				ps.setString(1,  productCode);
				ps.executeUpdate();
				ps.close();
			}else{
			ps.close();
			rs.close();
			}
			String checkedBaggageQuery = "Insert into CheckedBaggage (Code, TicketCode) values (?,?);";
			String productsIDQuery = "select ID from Products where Code = ?;";
			int productID = 0;
			
			try{
				ps = conn.prepareStatement(productsIDQuery);
				ps.setString(1, productCode);
				rs = ps.executeQuery();
				
				while (rs.next()){
					productID = rs.getInt("ID");					
				}
				
			} catch (SQLException e1) {
				log.error("SQLException", e1);
			}
			
			ps = conn.prepareStatement(checkedBaggageQuery);
			ps.setString(1, productCode);
			ps.setString(2, ticketCode);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}

	/* Adds a Insurance record to the database with the
	 provided data. */
	public static void addInsurance(String productCode, String productName, 
			String ageClass, double costPerMile) {
		
	
	}
	
	/* Adds a SpecialAssistance record to the database with the
	 provided data. */
	public static void addSpecialAssistance(String productCode, String assistanceType) {
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String productQuery = "Insert into Products (Code) values (?)";
		try {
			ps = conn.prepareStatement(productQuery);
			ps.setString(1, productCode);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
		String productIDQuery = "Select ID from Products where Code = ?;";
		int productID = 0;
		try {
			ps = conn.prepareStatement(productIDQuery);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			
			while(rs.next()){
			productID = rs.getInt("ID");
			}
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}

		
			String specialAssistanceCodeQuery = "Select ProductID from SpecialAssistance where ProductID = ?;";
		try {
			ps = conn.prepareStatement(specialAssistanceCodeQuery);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			
			if (rs.next()){
				String removeSpecialAssistance = "Delete from SpecialAssistance where ProductID = ?;";
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(removeSpecialAssistance);
				ps.setString(1,  productCode);
				ps.executeUpdate();
				ps.close();
			}else{
			ps.close();
			rs.close();
			}
			String specialAssistanceQuery = "Insert into SpecialAssistance (ProductID, TypeOfService) values (?,?);";
			String productsIDQuery = "select ID from Products where Code = ?;";
			productID = 0;
			
			try{
				ps = conn.prepareStatement(productsIDQuery);
				ps.setString(1, productCode);
				rs = ps.executeQuery();
				
				while (rs.next()){
					productID = rs.getInt("ID");					
				}
				
			} catch (SQLException e1) {
				log.error("SQLException", e1);
			}
			
			ps = conn.prepareStatement(specialAssistanceQuery);
			ps.setString(1, productCode);
			ps.setString(2, assistanceType);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}

	// Adds a refreshment record to the database with the provided data. 
	public static void addRefreshment(String productCode, String name, double cost) { 
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
			String refreshmentCodeQuery = "Select Code from Refreshment where Code like ?;";
		try {
			ps = conn.prepareStatement(refreshmentCodeQuery);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			
			if (rs.next()){
				String removeRefreshment = "Delete from Refreshment where Code = ?;";
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(removeRefreshment);
				ps.setString(1,  productCode);
				ps.executeUpdate();
				ps.close();
			}else{
			ps.close();
			rs.close();
			}
			String refreshmentQuery = "Insert into Refreshment (Code, Name, Cost) values (?,?,?);";
			String productsIDQuery = "select ID from Products where Code = ?;";
			int productID = 0;
			
			try{
				ps = conn.prepareStatement(productsIDQuery);
				ps.setString(1, productCode);
				rs = ps.executeQuery();
				
				while (rs.next()){
					productID = rs.getInt("ID");					
				}
				
			} catch (SQLException e1) {
				log.error("SQLException", e1);
			}
			
			ps = conn.prepareStatement(refreshmentQuery);
			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setDouble(3, cost);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}
	
	// Removes all invoice records from the database
	public static void removeAllInvoices() {
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String removeQuery = "Delete from Invoice;";
		try {
			ps = conn.prepareStatement(removeQuery);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}
	
	// Adds an invoice record to the database with the given data.
	public static void addInvoice(String invoiceCode, String customerCode, 
			String salesPersonCode, String invoiceDate) { 
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
			String invoiceCodeQuery = "Select Code from Invoice where Code like ?;";
		try {
			ps = conn.prepareStatement(invoiceCodeQuery);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			
			if (rs.next()){
				String removeInvoice = "Delete from Invoice where Code = ?;";
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(removeInvoice);
				ps.setString(1,  invoiceCode);
				ps.executeUpdate();
				ps.close();
			}else{
			ps.close();
			rs.close();
			}
			String invoiceQuery = "Insert into Refreshment (Code, CustomerCode, PersonCode, Date) values (?,?,?,?);";
			
			ps = conn.prepareStatement(invoiceQuery);
			ps.setString(1, invoiceCode);
			ps.setString(2, customerCode);
			ps.setString(3, salesPersonCode);
			ps.setString(4, invoiceDate);
			
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}
	
	/* Adds a particular Ticket (corresponding to <code>productCode</code>) to an 
	 invoice corresponding to the provided <code>invoiceCode</code> with the given
	 additional details */
	public static void addTicketToInvoice(String invoiceCode, String productCode, 
			String travelDate, String ticketNote) { }
	
	/* Adds a Passenger information to an 
	  invoice corresponding to the provided <code>invoiceCode</code> */
	public static void addPassengerInformation(String invoiceCode, String productCode, 
			String personCode, 
			String identity, int age, String nationality, String seat){ }
	
	/* Adds an Insurance Service (corresponding to <code>productCode</code>) to an 
	 invoice corresponding to the provided <code>invoiceCode</code> with the given
	 number of quantity and associated ticket information */
	public static void addInsuranceToInvoice(String invoiceCode, String productCode, 
			int quantity, String ticketCode) { }

	/* Adds a CheckedBaggage Service (corresponding to <code>productCode</code>) to an 
	  invoice corresponding to the provided <code>invoiceCode</code> with the given
	  number of quantity. */
	public static void addCheckedBaggageToInvoice(String invoiceCode, String productCode, 
			int quantity) { }
		
	/* Adds a SpecialAssistance Service (corresponding to <code>productCode</code>) to an 
	  invoice corresponding to the provided <code>invoiceCode</code> with the given
	  number of quantity. */
	public static void addSpecialAssistanceToInvoice(String invoiceCode, String productCode, 
			String personCode) { }
	
	/* Adds a Refreshment service (corresponding to <code>productCode</code>) to an 
	  invoice corresponding to the provided <code>invoiceCode</code> with the given
	  number of quantity. */
	public static void addRefreshmentToInvoice(String invoiceCode, 
			String productCode, int quantity) { }
}