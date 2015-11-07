package JDBCStuff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DatabaseInfo {

	public static final String url = "jdbc:mysql://cse.unl.edu/eeitzman";
	public static final String username = "eeitzman";
	public static final String password = "letmein";
	private static Logger log = Logger.getLogger(DatabaseInfo.class.getName());
	static public Connection getConnection()

	{

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			log.error("InstantiationException", e);
		} catch (IllegalAccessException e) {
			log.error("IllegalAccessException", e);
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException", e);
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			log.error("SQLException",e);
		}
		return conn;
	}

}
