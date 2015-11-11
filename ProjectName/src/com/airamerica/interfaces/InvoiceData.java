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
//		
		InvoiceData.addAirport("LNK","Lincoln Municipal","2400 W Adams St","Lincoln","NE","68524","USA",40,50,96,40,0);
		InvoiceData.addAirport("ORD","Chicago O'Hare Domestic","10000 W O'Hare Ave","Chicago","IL","60666","USA",41,50,87,37,4.50);
		InvoiceData.addAirport("MDW","Chicago Midway International","5700 S Cicero Ave","Chicago","IL","60638","USA",41,50,87,37,4.50);
		InvoiceData.addAirport("JFK","John F. Kennedy Domestic","JFK Expy & S Cargo Rd","Jamaica","NY","11430","USA",40,47,73,58,4.50);
		InvoiceData.addAirport("PHX","Phoenix Sky Harbor Domestic","3400 E Sky Harbor Blvd","Phoenix","AZ","85034","USA",33,29,112,4,4.50);
		InvoiceData.addAirport("LAX","Los Angeles Airport","1 World Way", "Los Angeles","CA","90045","USA",34,3,118,15,4.50);
		InvoiceData.addAirport("SJC","San Jose Domestic Airport","1701 Airport Blvd","San Jose","CA","95110","USA",37,20,121,53,4.50);
		InvoiceData.addAirport("ATL","Hartsfield Jackson Atlanta Domestic Airport","6000 N Terminal Pkwy","Atlanta","GA","30320","USA",33,45,84,23,4.50);	
		InvoiceData.addAirport("DEN","Denver Domestic Airport","8500 Pea Blvd","Denver","CO","80249","USA",39,45,105,0,4.50);
		InvoiceData.addAirport("MSP","Minneapolis Saint Paul Domestic Airport","4300 Glumack Drive","St. Paul","MN","55111","USA",44,59,93,14,4.50);
		
		
		InvoiceData.addPerson("944c","Jain","Suresh","142-241-6024","1060 West Addison Street","Chicago","IL","60613","USA");
		InvoiceData.addPerson("306a","Duka","Ilda","402-241-6024","123 N 1st Street","Omaha","NE","68116","USA");
		InvoiceData.addPerson("55bb","O'Brien","Miles","402-251-2114","8753 West 3rd Ave.","Dallas","TX","75001","USA");
		InvoiceData.addPerson("2342","O'Brien","Miles","212-241-6024","123 Friendly Street","Ottawa","ON","K1A 0G9","Canada");
		InvoiceData.addPerson("aef1","Salman","Gordon","122-141-1032","1 Wall Street","New York","NY","10005-0012","USA");
		InvoiceData.addPerson("321f","Deer","Bud","","321 Bronx Street","New York","NY","10004","USA");
		InvoiceData.addPerson("ma12","Sveum","Pale","541-354-3210","1060 West Addison Street","Chicago","IL","60613","USA");
		InvoiceData.addPerson("321nd","Hartnull","Will","323-541-3210","1060 West Addison Street","Chicago","IL","60613","USA");
		InvoiceData.addPerson("nf32a","Compton","Patrick","","1060 West Addison Street","Chicago","IL","60613","USA");
		InvoiceData.addPerson("321na","Samurai","Jon","323-541-3210","301 Front St W"," Toronto"," ON"," M5V 2T6"," Canada");
		InvoiceData.addPerson("231","Cook","Tom","210-124-3210","1 Blue Jays Way"," Toronto"," ON"," M5V 1J1"," Canada");
		InvoiceData.addPerson("6doc","Son","Richard","124-366-3210","Campos El290","Mexico City"," FD",""," Mexico");
		InvoiceData.addPerson("321dr","Baker","C.","122-141-1032","Avery Hall","Lincoln","NE","68503","USA");
		InvoiceData.addPerson("1svndr","McCoy","Sylvester","","126-01 Roosevelt Ave"," Flushing"," NY","11368","USA");
		InvoiceData.addPerson("123lst","McGann","Paul","102-147-3522","1 MetLife Stadium Dr"," East Rutherford"," NJ","07073","USA");
		InvoiceData.addPerson("nwdoc1","Eccleston","Chris","210-124-3210","1 E 161st St"," Bronx"," NY","10451","USA");
		InvoiceData.addPerson("2ndbestd","Tennant","David","320-141-3142","700 E Grand Ave"," Chicago"," IL"," 60611","USA");
		InvoiceData.addPerson("wrddoc","Smith","Matt","141-325-1032","333 W 35th St"," Chicago"," IL","60616","USA");
		InvoiceData.addPerson("bbchar","Ehrmantraut","Kaylee","587-417-1032","800 West 7th Street"," Albuquerque"," NM"," 87105","USA");
		InvoiceData.addPerson("doc05","Davison","Peter","122-141-1032","123 Cabo San Lucas"," Los Cabos"," BCS"," "," Mexico");

		InvoiceData.addCustomer("C001","C","231","Some Consultants",0);
		InvoiceData.addCustomer("C002","V","944c","Thor Associates",12500);
		InvoiceData.addCustomer("C003","G","306a","United Kingdom Postal Service",36000);
		InvoiceData.addCustomer("C004","C","321f","Mrs Julia Odelay",4170000);
		InvoiceData.addCustomer("C005","G","bbchar","Robert Industries",0);
		InvoiceData.addCustomer("C006","C","321dr","University of Nebaska-Omaha",6857441);
		InvoiceData.addCustomer("C007","V","6doc","Miami Club",0);
		InvoiceData.addCustomer("C008","G","ma12","Mr Joshua Smith",101111);
		
		
		InvoiceData.addInsurance("ff23","InterNationalInsurance","0-20",0.04);
		InvoiceData.addStandardTicket("1255","LAX","ORD","09:30","15:55","NA2222","BC","Boeing757");
		InvoiceData.addCheckedBaggage("90fa","1255");
		InvoiceData.addStandardTicket("1238","LNK","ORD","06:00","9:08","NA4889","EC","CRJ900");
		InvoiceData.addSpecialAssistance("xer2","Tricycle");
		InvoiceData.addOffSeasonTicket("1240","2015-10-07","2016-01-29","LNK","ORD","06:00","9:08","NA4889","EC","CRJ900",0.2);
		InvoiceData.addStandardTicket("1241","ORD","LNK","21:15","22:42","NA4871","EC","CRJ900");
		InvoiceData.addInsurance("ff25","Progressive","69-102",0.1);
		InvoiceData.addOffSeasonTicket("1243","2015-10-07","2016-01-29","ORD","LNK","21:15","22:42","NA4871","EP","CRJ900",0.6);
		InvoiceData.addCheckedBaggage("90fb","1241");
		InvoiceData.addStandardTicket("1256","LAX","ORD","09:30","15:55","NA2222","EP","Boeing757");
		InvoiceData.addAwardsTicket("1257","LAX","ORD","10:15","18:13","NA101","EP","Boeing737-900ER",90);
		InvoiceData.addStandardTicket("1258","ORD","LAX","10:50","12:50","NA1555","EC","Boeing737-900ER");
		InvoiceData.addRefreshment("32f4","Indian Buffet",20.00);
		InvoiceData.addAwardsTicket("1260","ORD","PHX","09:09","11:06","N725","EP","Airbus-A319",110);
		InvoiceData.addSpecialAssistance("xer4","AdultBassinet");
		InvoiceData.addStandardTicket("1261","ORD","PHX","09:09","11:06","N725","BC","Airbus-A319");
		InvoiceData.addInsurance("ff24","Non-Progressive","55-68",0.09);
		InvoiceData.addOffSeasonTicket("1262","2015-09-01","2016-10-12","PHX","ORD","14:24","19:56","N2000","BC","Airbus-A320",0.15);
		InvoiceData.addOffSeasonTicket("1263","2015-09-15","2016-12-29","PHX","ORD","07:10","12:42","N488","EC","Airbus-A320",0.25);
		InvoiceData.addInsurance("fg23","Progressive","21-54",0.08);
		InvoiceData.addInsurance("fh23","StateInsurance","21-60",0.085);
		InvoiceData.addCheckedBaggage("90fc","1260");
		InvoiceData.addSpecialAssistance("xer1","PrioritySeating");
		InvoiceData.addRefreshment("31f4","Labbat-Beer-15oz",04.50);
		
		
		
		InvoiceData.addInvoice("INV001", "C003", "1svndr", "2015-04-02");
		InvoiceData.addTicketToInvoice("INV001", "1241", "2015-06-11", "Operated by Bhaarat");
		InvoiceData.addPassengerInformation("INV001", "1241", "321nd", "ayylmao", 35, "Jew", "25");
		InvoiceData.addInsuranceToInvoice("INV001", "ff24", 2, "1241");

		
		
//		InvoiceData.addPerson("123", "john", "smith", "123-456-7890", "fake st", "lincoln", "NE", "68508", "USA");
//		InvoiceData.addPerson("456", "jane", "smith", "123-456-7890", "fake st", "lincoln", "NE", "68508", "USA");
//		InvoiceData.addPerson("789", "Jim", "Johns", "fake number", "Dodge", "Omaha", "NE", "68700", "USA");
//		InvoiceData.addPerson("123123", "James", "Johns", "fake number", "DodgeST", "Omaha", "NE", "68700", "USA");
//		InvoiceData.addAirport("LAX", "Los Angeles", "LA St", "LA", "CA", "12345", "USA", 10, 20, 100, 120, 0);
//		InvoiceData.addAirport("ORD", "Chicago", "Some St", "Some", "fake", "13345", "USA", 20, 30, 300, 120, 0);
//		InvoiceData.addCustomer("C001", "Government", "123", "UNL", 100000);
//		InvoiceData.addInvoice("INV001", "C001", "123", "12-12-2014");
//		InvoiceData.addStandardTicket("1234", "LAX", "ORD", "9:30", "12:30","12456v", "EC", "BOakdb");
//		InvoiceData.addAwardsTicket("2344", "LAX", "ORD", "9:30", "12:40", "2323", "EC", "shitty", 1);
//		InvoiceData.addOffSeasonTicket("48645", "2012-05-05", "2012-06-06", "ORD", "LAX", "10:30", "7:35", "adjio", "BC", "767", 50);
//		InvoiceData.addSpecialAssistance("48615615615", "wheelchair");
//		InvoiceData.addRefreshment("1", "coke", 1.50);
//		InvoiceData.addInsurance("adfinsurance", "ProductName", "20-45", 100);
//		InvoiceData.addTicketToInvoice("INV001", "1234", "01-03-2014", "shut up");
//		InvoiceData.addPassengerInformation("INV001", "1234", "456", "ADFASDF", 45, "Bosnian", "20f");
//		InvoiceData.addEmail("123", "adfasdf@gmail.com");
//		InvoiceData.addEmail("123", "johnsmith@gmail.com");
//		InvoiceData.addEmail("789", "adfoaidfjaoidfjaoseifjweiofj@gmail.com");
//		InvoiceData.addCheckedBaggage("90fa", "1234");
//		InvoiceData.addRefreshmentToInvoice("INV001", "1", 3);
//		InvoiceData.addInsuranceToInvoice("INV001", "adfinsurance", 2, "1234");
//		InvoiceData.addCheckedBaggageToInvoice("INV001", "90fa", 5);
//		InvoiceData.addSpecialAssistanceToInvoice("INV001", "48615615615", "5648189189");
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
			conn.close();
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
			ps.close();
			rs.close();
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
			rs.close();
			conn.close();
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
			conn.close();
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
			rs.close();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
		String airportCodeQuery = "Select Code from Airport where Code like ?;";
		try {
			deleteIfExists("Airport", "Code", airportCode, ps, rs, conn);
			String airportQuery = "Insert into Airport (Code, Name, AddressCode, LatDeg, LatMin, LonDeg, LonMin, PassengerFacilityFee) values (?,?,?,?,?,?,?,?);";
			ps = conn.prepareStatement(airportQuery);
			ps.setString(1, airportCode);
			ps.setString(2, name);
			ps.setInt(3,addressID);
			ps.setDouble(4, latdegs);
			ps.setDouble(5, latmins);
			ps.setDouble(6, londegs);
			ps.setDouble(7, lonmins);
			ps.setDouble(8, passengerFacilityFee);
			ps.executeUpdate();
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}


	/*Adds an email record corresponding person record corresponding to the
	  provided <code>personCode</code> */

	public static void addEmail(String personCode, String email) { 
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int emailID = 0;
		int personID = 0;
		String emailQuery = "Insert into Email (email) values (?);";
		try{
			ps = conn.prepareStatement(emailQuery);
			ps.setString(1, email);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
		emailID = findID("Email", "Email", email, ps,rs,conn);
		personID = findID("Person", "Code", personCode, ps,rs,conn);
		emailQuery = "Insert into PersonEmails (PersonID, EmailID) values (?,?);";
		try{
			ps = conn.prepareStatement(emailQuery);
			ps.setInt(1, personID);
			ps.setInt(2, emailID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
		
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
			conn.close();
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
		int personID = 0;
		personID = findID("Person", "Code", primaryContactPersonCode, ps,rs,conn);
		try {
			deleteIfExists("Customer", "Code", customerCode, ps, rs, conn);
			String customerQuery = "Insert into Customer (Code, Type, PersonID, Name, AirlineMiles) values (?,?,?,?,?);";
			ps = conn.prepareStatement(customerQuery);
			ps.setString(1, customerCode);
			ps.setString(2, customerType);
			ps.setInt(3, personID);
			ps.setString(4, name);
			ps.setInt(5, airlineMiles);
			ps.executeUpdate();
			ps.close();
			conn.close();
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
			conn.close();
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
			conn.close();
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
		int productID = 0;
		addProduct(productCode, "OffSeason", ps , rs ,conn);
		productID = findID("Products", "Code", productCode,ps,rs,conn);
		try {
			deleteIfExists("OffSeason", "Code", productCode, ps, rs, conn);
			
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
			
			
			
			String offSeasonQuery = "Insert into OffSeason (Code, SeasonStartDate, SeasonEndDate, DepartureID, ArrivalID, DepartureDateTime, ArrivalDateTime, FlightNo, FlightClass, AircraftType, Rebate, ProductID) values (?,?,?,?,?,?,?,?,?,?,?,?);";
			ps = conn.prepareStatement(offSeasonQuery);
			ps.setString(1, productCode);
			ps.setString(2, seasonStartDate);
			ps.setString(3, seasonEndDate);
			ps.setInt(4, depAirportID);
			ps.setInt(5, arrAirportID);
			ps.setString(6, depTime);
			ps.setString(7, arrTime);
			ps.setString(8, flightNo);
			ps.setString(9, flightClass);
			ps.setString(10, aircraftType);
			ps.setDouble(11, rebate);
			ps.setDouble(12, productID);
			ps.executeUpdate();
			ps.close();
			conn.close();
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
		int productID = 0;
		addProduct(productCode, "Award", ps , rs, conn);
		productID = findID("Products", "Code", productCode,ps,rs,conn);
		try {
			deleteIfExists("Award", "Code", productCode, ps, rs, conn);
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
			String awardQuery = "Insert into Award (Code, DepartureID, ArrivalID, DepartureDateTime, ArrivalDateTime, FlightNo, FlightClass, AircraftType, PointsPerMile, ProductID) values (?,?,?,?,?,?,?,?,?,?);";
			String productsIDQuery = "select ID from Products where Code = ?;";
			ps = conn.prepareStatement(awardQuery);
			ps.setString(1, productCode);
			ps.setInt(2, depAirportID);
			ps.setInt(3, arrAirportID);
			ps.setString(4, depTime);
			ps.setString(5, arrTime);
			ps.setString(6, flightNo);
			ps.setString(7, flightClass);
			ps.setString(8, aircraftType);
			ps.setDouble(9, pointsPerMile);
			ps.setInt(10, productID);
			ps.executeUpdate();
			ps.close();
			conn.close();
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
		int productID = 0;
		int ticketID = 0;
		productID = findID("Products", "Code", productCode,ps,rs,conn);
		ticketID = findID("Products", "Code",ticketCode,ps,rs,conn);
		try {
			deleteIfExists("CheckedBaggage", "Code", productCode, ps, rs, conn);
			String cBQuery = "Insert into CheckedBaggage (ProductID, Code, TicketID) values (?,?,?);";
			ps = conn.prepareStatement(cBQuery);
			ps.setInt(1, productID);
			ps.setString(2, productCode);
			ps.setInt(3, ticketID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}

	/* Adds a Insurance record to the database with the
	 provided data. */
	public static void addInsurance(String productCode, String productName, 
			String ageClass, double costPerMile) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		addProduct(productCode, "Insurance", ps, rs, conn);
		int productID = 0;
		productID = findID("Products", "Code", productCode,ps,rs,conn);
		try {
			deleteIfExists("Insurance", "Code", productCode, ps, rs, conn);
			String insuranceQuery = "Insert into Insurance (ProductID, Name, CostPerMile, AgeClass, Code) values (?,?,?,?,?);";
			ps = conn.prepareStatement(insuranceQuery);
			ps.setInt(1, productID);
			ps.setString(2, productName);
			ps.setDouble(3, costPerMile);
			ps.setString(4, ageClass);
			ps.setString(5, productCode);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}

	}

	/* Adds a SpecialAssistance record to the database with the
	 provided data. */
	public static void addSpecialAssistance(String productCode, String assistanceType) {

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		addProduct(productCode, "SpecialAssistance", ps, rs, conn);
		int productID = 0;
		productID = findID("Products", "Code", productCode,ps,rs,conn);
		try {
			deleteIfExists("SpecialAssistance", "Code", productCode, ps, rs, conn);
			String specialAssistanceQuery = "Insert into SpecialAssistance (ProductID, TypeOfService, Code) values (?,?,?);";
			ps = conn.prepareStatement(specialAssistanceQuery);
			ps.setInt(1, productID);
			ps.setString(2, assistanceType);
			ps.setString(3, productCode);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}

	// Adds a refreshment record to the database with the provided data. 
	public static void addRefreshment(String productCode, String name, double cost) { 

		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		addProduct(productCode, "Refreshment", ps, rs, conn);
		int productID = 0;
		productID = findID("Products", "Code", productCode,ps,rs,conn);
		try {
			deleteIfExists("Refreshment", "Code", productCode, ps, rs, conn);
			String refreshmentQuery = "Insert into Refreshment (ProductID, Name, Cost, Code) values (?,?,?,?);";
			ps = conn.prepareStatement(refreshmentQuery);
			ps.setInt(1, productID);
			ps.setString(2, name);
			ps.setDouble(3, cost);
			ps.setString(4, productCode);
			ps.executeUpdate();
			ps.close();
			conn.close();
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
			conn.close();
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
			ps = conn.prepareStatement(invoiceQuery);
			ps.setString(1, invoiceCode);
			ps.setInt(2, customerID);
			ps.setInt(3, personID);
			ps.setString(4, invoiceDate);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e1) {
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
		int productID = 0;
		int invoiceID = 0;
		try {
			String ticketQuery = "Insert into Ticket (invoiceID, productID, travelDate, ticketNote) values (?,?,?,?);";
			String invoiceIDQuery = "select ID from Invoice where Code = ?;";
			String productsIDQuery = "select ID from Products where Code = ?;";
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
		}catch (SQLException e1) {
			log.error("SQLException", e1);
		}
		String ticketProdQuery = "Insert into InvoiceProducts (ProductID, Quantity, Misc, InvoiceID) values (?,1,1,?);";
		try{
			ps = conn.prepareStatement(ticketProdQuery);
			ps.setInt(1, productID);
			ps.setInt(2, invoiceID);
			ps.executeUpdate();
			conn.close();
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
		int invoiceID = 0;
		int productID = 0;
		int personID = 0;
		int ticketID = 0;
		int passengerID = 0;
		productID = findID("Products", "Code", productCode, ps, rs, conn);
		personID = findID("Person", "Code", personCode, ps, rs, conn);
		invoiceID = findID("Invoice", "Code", invoiceCode, ps, rs, conn);
		try {
			String ticketInfoQuery = "Select ID from Ticket where ProductID = ? and InvoiceID = ?;";
			ps = conn.prepareStatement(ticketInfoQuery);
			ps.setInt(1, productID);
			ps.setInt(2, invoiceID);
			rs = ps.executeQuery();
			while (rs.next()){
				ticketID = rs.getInt("ID");					
			}
			deleteIfExists("Passenger", "Identity", identity, ps, rs, conn);
			String passengerInformationQuery = "Insert into Passenger (Identity, SeatNumber, Age, Nationality, PersonID, TicketID) values (?,?,?,?,?,?);";
			ps = conn.prepareStatement(passengerInformationQuery);
			ps.setString(1, identity);
			ps.setString(2, seat);
			ps.setInt(3, age);
			ps.setString(4, nationality);
			ps.setInt(5, personID);
			ps.setInt(6, ticketID);
			ps.executeUpdate();
			String passengerInfoQuery = "Select ID from Passenger where PersonID = ? and TicketID = ?;";
			ps = conn.prepareStatement(passengerInfoQuery);
			ps.setInt(1, personID);
			ps.setInt(2, ticketID);
			rs = ps.executeQuery();
			while (rs.next()){
				passengerID = rs.getInt("ID");					
			}
			String invoicePassengerQuery = "Insert into InvoicePassengers (InvoiceID, PassengerID) Values (?,?);";
			ps = conn.prepareStatement(invoicePassengerQuery);
			ps.setInt(1, invoiceID);
			ps.setInt(2, passengerID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}

		
	}

	/* Adds an Insurance Service (corresponding to <code>productCode</code>) to an 
	 invoice corresponding to the provided <code>invoiceCode</code> with the given
	 number of quantity and associated ticket information */
	public static void addInsuranceToInvoice(String invoiceCode, String productCode, 
			int quantity, String ticketCode) { 
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int invoiceID = 0;
		int productID = 0;
		invoiceID = findID("Invoice","Code", invoiceCode,ps,rs,conn);
		productID = findID("Products","Code", productCode,ps,rs,conn);
		String insuranceQuery = "Insert into InvoiceProducts (ProductID, Quantity, Misc, InvoiceID) values (?,?,?,?);";
		try{
			ps = conn.prepareStatement(insuranceQuery);
			ps.setInt(1, productID);
			ps.setInt(2, quantity);
			ps.setString(3, ticketCode);
			ps.setInt(4, invoiceID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
		
	}

	/* Adds a CheckedBaggage Service (corresponding to <code>productCode</code>) to an 
	  invoice corresponding to the provided <code>invoiceCode</code> with the given
	  number of quantity. */
	public static void addCheckedBaggageToInvoice(String invoiceCode, String productCode, 
			int quantity) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int invoiceID = 0;
		int productID = 0;
		invoiceID = findID("Invoice","Code", invoiceCode,ps,rs,conn);
		productID = findID("Products","Code", productCode,ps,rs,conn);
		String cBQuery = "Insert into InvoiceProducts (ProductID, Quantity, InvoiceID) values (?,?,?);";
		try{
			ps = conn.prepareStatement(cBQuery);
			ps.setInt(1, productID);
			ps.setInt(2, quantity);
			ps.setInt(3, invoiceID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
	}

	/* Adds a SpecialAssistance Service (corresponding to <code>productCode</code>) to an 
	  invoice corresponding to the provided <code>invoiceCode</code> with the given
	  number of quantity. */
	public static void addSpecialAssistanceToInvoice(String invoiceCode, String productCode, 
			String personCode) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int invoiceID = 0;
		int productID = 0;
		invoiceID = findID("Invoice","Code", invoiceCode,ps,rs,conn);
		productID = findID("Products","Code", productCode,ps,rs,conn);
		String sAQuery = "Insert into InvoiceProducts (ProductID, Quantity, InvoiceID) values (?,?,?);";
		try{
			ps = conn.prepareStatement(sAQuery);
			ps.setInt(1, productID);
			ps.setInt(2, 1);
			ps.setInt(3, invoiceID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
		
	}

	/* Adds a Refreshment service (corresponding to <code>productCode</code>) to an 
	  invoice corresponding to the provided <code>invoiceCode</code> with the given
	  number of quantity. */
	public static void addRefreshmentToInvoice(String invoiceCode, 
			String productCode, int quantity) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int invoiceID = 0;
		int productID = 0;
		invoiceID = findID("Invoice","Code", invoiceCode,ps,rs,conn);
		productID = findID("Products","Code", productCode,ps,rs,conn);
		String refQuery = "Insert into InvoiceProducts (ProductID, Quantity, InvoiceID) values (?,?,?);";
		try{
			ps = conn.prepareStatement(refQuery);
			ps.setInt(1, productID);
			ps.setInt(2, quantity);
			ps.setInt(3, invoiceID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e1) {
			log.error("SQLException", e1);
		}
		
	}

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
			ps.close();
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
	
	public static String airportCode(int iD){
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String airQuery = "Select Code from Airport where ID = "+iD+";";
		try{
		ps = conn.prepareStatement(airQuery);
		rs = ps.executeQuery();
		while (rs.next()){
			String testStr = rs.getString("Code");
			conn.close();
			return testStr;					
		}
		ps.close();
	} catch (SQLException e1) {
		log.error("SQLException", e1);
	}
	return "LNK";
	}
	public static String TicketCode(int iD){
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String airQuery = "select a.*, b.* from Ticket a join Products b on b.ID = a.ProductID where a.ID ="+iD+";";
		try{
		System.out.println("made it");
		ps = conn.prepareStatement(airQuery);
		rs = ps.executeQuery();
		while (rs.next()){
			String testStr = rs.getString("Code");
			conn.close();
			return testStr;		
		}
		ps.close();
	} catch (SQLException e1) {
		log.error("SQLException", e1);
	}
	return "738";
	}
	
}

