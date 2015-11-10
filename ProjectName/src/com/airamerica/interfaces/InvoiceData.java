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
 * Do `not forget to change your reports generation classes to read from 
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
		org.apache.log4j.BasicConfigurator.configure();
		// Comment so i can push
		InvoiceData.addPerson("123", "john", "smith", "123-456-7890", "fake st", "lincoln", "NE", "68508", "USA");
		InvoiceData.addPerson("456", "jane", "smith", "123-456-7890", "fake st", "lincoln", "NE", "68508", "USA");
		InvoiceData.addPerson("789", "Jim", "Johns", "fake number", "Dodge", "Omaha", "NE", "68700", "USA");
		InvoiceData.addPerson("123123", "James", "Johns", "fake number", "DodgeST", "Omaha", "NE", "68700", "USA");
		InvoiceData.addAirport("LAX", "Los Angeles", "LA St", "LA", "CA", "12345", "USA", 10, 20, 100, 120, 0);
		InvoiceData.addAirport("ORD", "Chicago", "Some St", "Some", "fake", "13345", "USA", 20, 30, 300, 120, 0);
		InvoiceData.addCustomer("C001", "Government", "123", "UNL", 100000);
		InvoiceData.addInvoice("INV001", "C001", "123", "12-12-2014");
		InvoiceData.addStandardTicket("1234", "LAX", "ORD", "9:30", "12:30","12456v", "EC", "BOakdb");
		InvoiceData.addTicketToInvoice("INV001", "1234", "01-03-2014", "shut up");
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
		try {
			deleteIfExists("Person", "Code", personCode, ps, rs, conn);
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
			deleteIfExists("Airport", "Code", airportCode, ps, rs, conn);
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

		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}


	/*Adds an email record corresponding person record corresponding to the
	  provided <code>personCode</code> */

	public static void addEmail(String personCode, String email) { 
		
	}


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

		try {
			deleteIfExists("Customer", "Code", customerCode, ps, rs, conn);
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
		addProduct(productCode, "Standard", ps, rs, conn);
		try {
			deleteIfExists("Standard", "Code", productCode, ps, rs, conn);
			String standardQuery = "Insert into Standard (DepartureID, ArrivalID, DepartureDateTime, ArrivalDateTime, FlightNo, FlightClass, AircraftType, ProductID, Code) values (?,?,?,?,?,?,?,?,?);";
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

			String depAirportIDQuery = "select ID from Airport where Code = ?;";
			int depAirportID = 0;
			try{
				ps = conn.prepareStatement(depAirportIDQuery);
				ps.setString(1, depAirportCode);
				rs = ps.executeQuery();

				while (rs.next()){
					depAirportID = rs.getInt("ID");					
				}

			} catch (SQLException e1) {
				log.error("SQLException", e1);
			}
			int arrAirportID = 0;
			try{
				ps = conn.prepareStatement(depAirportIDQuery);
				ps.setString(1, arrAirportCode);
				rs = ps.executeQuery();
				while (rs.next()){
					arrAirportID = rs.getInt("ID");					
				}
			} catch (SQLException e1) {
				log.error("SQLException", e1);
			}

			ps = conn.prepareStatement(standardQuery);
			ps.setInt(1, depAirportID);
			ps.setInt(2, arrAirportID);
			ps.setString(3, depTime);
			ps.setString(4, arrTime);
			ps.setString(5, flightNo);
			ps.setString(6, flightClass);
			ps.setString(7, aircraftType);
			ps.setInt(8, productID);
			ps.setString(9, productCode);
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
		addProduct(productCode, "OffSeason", ps , rs ,conn);
		try {
			deleteIfExists("OffSeason", "Code", productCode, ps, rs, conn);
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
		addProduct(productCode, "Award", ps , rs, conn);
		try {
			deleteIfExists("Award", "Code", productCode, ps, rs, conn);
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
		addProduct(productCode, "CheckedBaggage", ps, rs, conn);
		try {
			deleteIfExists("CheckedBaggage", "Code", productCode, ps, rs, conn);
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
		addProduct(productCode, "SpecialAssistance", ps, rs, conn);
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

		try {
			deleteIfExists("SpecialAssistance", "Code", productCode, ps, rs, conn);
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
		addProduct(productCode, "Refreshments", ps, rs, conn);
		try {
			deleteIfExists("Refreshment", "Code", productCode, ps, rs, conn);
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
		int customerID = findID("Customer", "Code", customerCode, ps, rs, conn);
		int personID = findID("Person", "Code", salesPersonCode, ps, rs, conn);
		
		try {
			deleteIfExists("Invoice", "Code", invoiceCode, ps, rs, conn);
			String invoiceQuery = "Insert into Invoice (Code, CustomerID, PersonID, Date) values (?,?,?,?);";
			String invoicePassengerQuery = "Insert into InvoicePassengers(InvoiceID, PassengerID)";
			
			ps = conn.prepareStatement(invoiceQuery);
			ps.setString(1, invoiceCode);
			ps.setInt(2, customerID);
			ps.setInt(3, personID);
			ps.setString(4, invoiceDate);

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
		int invoiceID = findID("Invoice", "Code", invoiceCode, ps , rs, conn);
		try{
			
		}catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}

	/* Adds a particular Ticket (corresponding to <code>productCode</code>) to an 
	 invoice corresponding to the provided <code>invoiceCode</code> with the given
	 additional details */
	public static void addTicketToInvoice(String invoiceCode, String productCode, 
			String travelDate, String ticketNote) {

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//deleteIfExists("Ticket", "Code", invoiceCode, ps, rs, conn);
			String ticketQuery = "Insert into Ticket (invoiceID, productID, travelDate, ticketNote) values (?,?,?,?);";
			String invoiceIDQuery = "select ID from Invoice where Code = ?;";
			String productsIDQuery = "select ID from Products where Code = ?;";
			int invoiceID = 0;
			int productID = 0;

			try{
				ps = conn.prepareStatement(invoiceIDQuery);
				ps.setString(1, invoiceCode);
				rs = ps.executeQuery();

				while (rs.next()){
					invoiceID = rs.getInt("ID");					
				}
			} catch (SQLException e1) {
				log.error("SQLException", e1);
			}
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
			ps = conn.prepareStatement(ticketQuery);
			ps.setInt(1, invoiceID);
			ps.setInt(2, productID);
			ps.setString(3, travelDate);
			ps.setString(4, ticketNote);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}

	}

	/* Adds a Passenger information to an 
	  invoice corresponding to the provided <code>invoiceCode</code> */
	public static void addPassengerInformation(String invoiceCode, String productCode, 
			String personCode, String identity, int age, String nationality, String seat){
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//deleteIfExists("Ticket", "Code", invoiceCode, ps, rs, conn);
			String passengerInformationQuery = "Insert into  (invoiceID, productID, travelDate, ticketNote) values (?,?,?,?);";
			String invoiceIDQuery = "select ID from Invoice where Code = ?;";
			String productsIDQuery = "select ID from Products where Code = ?;";
			int invoiceID = 0;
			int productID = 0;

			try{
				ps = conn.prepareStatement(invoiceIDQuery);
				ps.setString(1, invoiceCode);
				rs = ps.executeQuery();

				while (rs.next()){
					invoiceID = rs.getInt("ID");					
				}
			} catch (SQLException e1) {
				log.error("SQLException", e1);
			}
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
			ps = conn.prepareStatement(ticketQuery);
			ps.setInt(1, invoiceID);
			ps.setInt(2, productID);
			ps.setString(3, travelDate);
			ps.setString(4, ticketNote);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}

		
	}

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

	public static void deleteIfExists(String tableName, String fieldToCheck, String code, PreparedStatement ps, ResultSet rs, Connection conn){
		String query = "Select "+fieldToCheck+" from "+tableName+" where "+fieldToCheck+" = ?;";
		try{
			ps = conn.prepareStatement(query);
			ps.setString(1, code);
			rs = ps.executeQuery();

			if(rs.next()){
				String removeQuery = "Delete from "+tableName+" where "+fieldToCheck+" = ?;";
				rs.close();
				ps.close();
				ps = conn.prepareStatement(removeQuery);
				ps.setString(1, code);
				ps.executeUpdate();
				ps.close();
			}else{
				ps.close();
				rs.close();
			}
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}
	
	public static int findID(String tableName, String fieldToCheck, String code, PreparedStatement ps, ResultSet rs, Connection conn){
		String findQuery = "Select ID from "+tableName+" where "+fieldToCheck+" = ?;";
		try{
			ps = conn.prepareStatement(findQuery);
			ps.setString(1, code);
			rs = ps.executeQuery();
			while (rs.next()){
				return rs.getInt("ID");					
			}
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
		return 0;
	}
	
	public static void addProduct(String code, String type, PreparedStatement ps, ResultSet rs, Connection conn){
		String prodQuery = "Insert into Products (Code, Type) Values (?,?);";
		try{
		deleteIfExists("Products", "Code", code, ps, rs, conn);
		ps = conn.prepareStatement(prodQuery);
		ps.setString(1, code);
		ps.setString(2, type);
		ps.executeUpdate();
		ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}
}

