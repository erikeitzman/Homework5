package Jdbc.Example;

//STEP 1. Import required packages
import java.sql.*;

public class JdbcStatementExample  {
    // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/eeitzman";

   //  Database credentials
   static final String USER = "eeitzman";
   static final String PASS = "letmein";

   public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;


       try{
           //STEP 2: Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");

           //STEP 3: Open a connection
           System.out.println("Connecting to database...");
           conn = DriverManager.getConnection(DB_URL,USER,PASS);
  
           System.out.println("Connected to database...");

           //STEP 4: Execute a query
           System.out.println("Creating statement...");
           stmt = conn.createStatement();
  
          /** Following sql statements create an Employee table
           * Insert values into the table
           * Read all the rows and attributes from the table
           */
           String sql;
           sql = "DROP TABLE IF EXISTS Employees";
           stmt.executeUpdate(sql);

           sql = "CREATE TABLE Employees " +
                 "(ID INTEGER not NULL, " +
                 " First_Name VARCHAR(255), " + 
                 " Last_Name VARCHAR(255), " + 
                 " Salary DOUBLE, " + 
                 " PRIMARY KEY ( ID ))"; 

           stmt.executeUpdate(sql);

           sql = "INSERT INTO Employees " +
                 "VALUES (111, 'Richard', 'Feynman', 10)";
           stmt.executeUpdate(sql);         
           sql = "INSERT INTO Employees " +
                 "VALUES (112, 'Alan', 'Turing', 5000)";
           stmt.executeUpdate(sql);
           sql = "INSERT INTO Employees " +
                 "VALUES (113, 'Ada', 'Lovelace', 400)";
           stmt.executeUpdate(sql);
           sql = "INSERT INTO Employees " +
                 "VALUES (114, 'Albert', 'Einstein', 20)";
           stmt.executeUpdate(sql);
  
  
  
          //sql = "SELECT * FROM Employees where Last_Name = 'Feynman' OR '1'='1'";
          sql = "SELECT * FROM Employees where Last_Name = 'Feynman'";
          //sql = "SELECT * FROM Employees";
  

 
          ResultSet rs = stmt.executeQuery(sql);
  
          //STEP 5: Extract data from result set
         while(rs.next()){
	       //Retrieve by column name
	       int id  = rs.getInt("ID");
	       String first = rs.getString("First_Name");
	       String last = rs.getString("Last_Name");
	       double salary = rs.getInt("Salary");

	       //Display values
	      System.out.print("ID: " + id);
	      System.out.print(", Salary: " + salary);
	      System.out.print(", First: " + first);
	      System.out.println(", Last: " + last);
	     }
         
         //STEP 6: Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
         }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
         }catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
         }finally{
           //finally block used to close resources
           try{
              if(stmt != null)
                 stmt.close();
           }catch(SQLException se2){
           }// nothing we can do
           try{
              if(conn != null)
                 conn.close();
           }catch(SQLException se){
               se.printStackTrace();
           }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}

