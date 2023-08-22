package in.fssa.globalfuncity.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.util.ConnectionUtil;

/**
 * The UserExists class provides methods to check the existence of user data in the database.
 * It helps in validating whether a given email or user ID already exists or not.
 */

public class UserExists {

    /**
     * Checks whether a user with the given email already exists in the database.
     *
     * @param email The email to check for existence.
     * @throws ValidationException If the email already exists in the database.
     */
	
	public static void emailExists(String email) throws ValidationException {
		
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			
			String query = "SELECT * FROM users WHERE email = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, email);
			rs = pre.executeQuery();
			
			if (rs.next()) {
				throw new ValidationException("Email already exists");
			} 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
			
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
		
	}
   
    /**
     * Checks whether a user with the given ID exists in the database.
     *
     * @param id The ID to check for existence.
     * @throws ValidationException If the user with the given ID doesn't exist in the database.
     */
	
	public static void checkIdExists(int id) throws ValidationException {
		
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			
			String query = "SELECT * FROM users WHERE is_active = 1 AND user_id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			
			if (!rs.next()) {
				throw new ValidationException("Id doesn't exist");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
			
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
		
	}
	
    /**
     * Checks whether a user with the given email exists in the database.
     * This method also considers the 'is_active' status of the user.
     *
     * @param email The email to check for existence.
     * @throws ValidationException If the email doesn't exist in the database or is not active.
     */
	
	public static void checkEmailExists(String email) throws ValidationException {
		
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			
			String query = "SELECT * FROM users WHERE is_active = 1 AND email = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, email);
			rs = pre.executeQuery();
			
			if (!rs.next()) {
				throw new ValidationException("Email doesn't exist");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
			
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
		
	}
	
}
