package com.airamerica.interfaces;

import java.sql.*;

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
		PreparedStatement ps;
		ResultSet rs;
		
		String PersonQuery = "select a.AlbumID, a.AlbumTitle, b.BandName, a.AlbumYear, a.AlbumNumber from Albums as a Join Bands as b on a.BandID = b.BandID where a.AlbumTitle like ?;";
		Person a = null;
		try {
			ps = conn.prepareStatement(PersonQuery);
			ps.setString(1,personCode);
			ps.setString(2,firstName);
			ps.setString(3,lastName);
			ps.setString(4,phoneNo);
			ps.setString(5,street);
			ps.setString(6,city);
			ps.setString(7,state);
			ps.setString(8,zip);
			ps.setString(9,country);
			rs = ps.executeQuery();
			
			while (rs.next()) {

				String bandName = rs.getString("BandName");
				BandBean bb = new BandBean();
				Band whatever = bb.getBand(bandName);
				a = new Album(rs.getString("AlbumTitle"), rs.getInt("AlbumYear"), whatever, rs.getInt("AlbumNumber"));
				a.setAlbumId(Integer.parseInt(rs.getString("AlbumID")));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return a;
	}

	public List<Album> getAlbums() {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Album> albums = new ArrayList<Album>();

		String getAlbumsQuery = "select AlbumTitle from Albums;";

		try {
			ps = conn.prepareStatement(getAlbumsQuery);
			rs = ps.executeQuery();

			
			while (rs.next()) {
				AlbumBean bb = new AlbumBean();
				Album something = bb.getDetailedAlbum(rs.getString("AlbumTitle"));
				albums.add(something);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return albums;

	}
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
			double passengerFacilityFee) { }
	
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