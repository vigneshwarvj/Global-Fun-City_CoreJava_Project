package in.fssa.globalfuncity.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionUtil {

	/**
	 * 
	 * @return
	 */
	public static Connection getConnection() throws SQLException {

		Connection connection = null;
		
		String url;
		String username; 	
		String password;
		
		url = System.getenv("DATABASE_HOSTNAME");
		username = System.getenv("DATABASE_USERNAME");
		password = System.getenv("DATABASE_PASSWORD");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return connection;
	}
	
	/**
	 * 
	 * @param connection
	 * @param ps
	 */
	
	public static void close(Connection connection, PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param connection
	 * @param ps
	 * @param rs
	 */
	
	public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
