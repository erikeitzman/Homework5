package com.airamerica.interfaces;

import java.sql.*;

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

	/**
	 * Method that removes every person record from the database
	 */
	public static void removeAllPersons() { }

	/**
	 * Method to add a person record to the database with the provided data. 
	 */
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
			
			String personQuery = "Insert into Person (Code, FirstName, LastName, AddressCode, PhoneNo) values (?,?,?,?,?);";
			ps = conn.prepareStatement(personQuery);
			ps.setString(1,personCode);
			ps.setString(2,firstName);
			ps.setString(3,lastName);
			ps.setInt(4,addressID);
			ps.setString(5,phoneNo);
			ps.executeUpdate();
			ps.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		try {
//			if (rs != null && !rs.isClosed()){
//				rs.close();
//			}
//			if (ps != null && !ps.isClosed()){
//				ps.close();
//			}
//			if (conn != null && !conn.isClosed()){
//				conn.close();
//			}
//		} catch (SQLException e) {
//			System.out.println("SQLException: ");
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
	}

	/**
	 * Method that removes every airport record from the database
	 */
	public static void removeAllAirports() { }
	
	/**
	 * Method to add a airport record to the database with the provided data. 
	 */
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
			String airportQuery = "Insert into Airport (Code, Name, AddressCode, Latitude, Longitude, PassengerFacilityFee) values (?,?,?,?,?,?,?,?);";
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 */
	public static void addEmail(String personCode, String email) { }
	
	/**
	 * Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() { }

	/**
	 * Method to add a customer record to the database with the provided data. 
	 */
	public static void addCustomer(String customerCode, String customerType, 
			String primaryContactPersonCode, String name, 
			int airlineMiles) {	}

	/**
	 * Removes all product records from the database
	 */
	public static void removeAllProducts() { }

	/**
	 * Adds an standardTicket record to the database with the
	 * provided data.  
	 */
	public static void addStandardTicket(String productCode,String depAirportCode, 
			String arrAirportCode, String depTime, String arrTime, 
			String flightNo, String flightClass, String aircraftType) { }
		
	
	 /** 
	 * Adds an offSeasonTicket record to the database with the
	 * provided data.  
	 */
	public static void addOffSeasonTicket(String productCode, String seasonStartDate, 
			String seasonEndDate, String depAirportCode, String arrAirportCode, 
			String depTime, String arrTime,	String flightNo, String flightClass, 
			String aircraftType, double rebate) { }
	 
	 /** Adds an awardsTicket record to the database with the
	 * provided data.  
	 */
	public static void addAwardsTicket(String productCode,String depAirportCode, 
			String arrAirportCode, String depTime, String arrTime, 
			String flightNo, String flightClass, 
			String aircraftType, double pointsPerMile) { } 
	
	/**
	 * Adds a CheckedBaggage record to the database with the
	 * provided data.  
	 */
	public static void addCheckedBaggage(String productCode, String ticketCode) { }

	/**
	 * Adds a Insurance record to the database with the
	 * provided data.  
	 */
	public static void addInsurance(String productCode, String productName, 
			String ageClass, double costPerMile) {	}
	
	/**
	 * Adds a SpecialAssistance record to the database with the
	 * provided data.  
	 */
	public static void addSpecialAssistance(String productCode, String assistanceType) { }

	/**
	 * Adds a refreshment record to the database with the
	 * provided data.  
	 */
	public static void addRefreshment(String productCode, String name, double cost) { }
	
	/**
	 * Removes all invoice records from the database
	 */
	public static void removeAllInvoices() { }
	
	/**
	 * Adds an invoice record to the database with the given data.  
	 */
	public static void addInvoice(String invoiceCode, String customerCode, 
			String salesPersonCode, String invoiceDate) { }
	
	/**
	 * Adds a particular Ticket (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * additional details
	 */
	public static void addTicketToInvoice(String invoiceCode, String productCode, 
			String travelDate, String ticketNote) { }
	
	/**
	 * Adds a Passenger information to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> 
	 */
	public static void addPassengerInformation(String invoiceCode, String productCode, 
			String personCode, 
			String identity, int age, String nationality, String seat){ }
	
	/**
	 * Adds an Insurance Service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity and associated ticket information
	 */
	public static void addInsuranceToInvoice(String invoiceCode, String productCode, 
			int quantity, String ticketCode) { }

	/**
	 * Adds a CheckedBaggage Service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 */
	public static void addCheckedBaggageToInvoice(String invoiceCode, String productCode, 
			int quantity) { }
		
	/**
	 * Adds a SpecialAssistance Service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 */
	public static void addSpecialAssistanceToInvoice(String invoiceCode, String productCode, 
			String personCode) { }
	
	/**
	 * Adds a Refreshment service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 */
	public static void addRefreshmentToInvoice(String invoiceCode, 
			String productCode, int quantity) { }
}