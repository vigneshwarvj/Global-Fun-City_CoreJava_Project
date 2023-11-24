package in.fssa.globalfuncity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.interfaces.UserInterface;
import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.util.ConnectionUtil;

public class UserDAO implements UserInterface<User>{
	
	
	/**
	 * Retrieves a set of all active users from the database.
	 *
	 * @return A set containing all active User objects in the database.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              retrieval.
	 */
	public Set<User> findAllUsers() throws PersistenceException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Set<User> userList = new HashSet<>();
		
		try {
			String query = "SELECT user_id, first_name, middle_name, last_name, email, password, phone_no, is_active FROM users";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setMiddleName(rs.getString("middle_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("phone_no"));
				user.setActive(rs.getBoolean("is_active"));
				
				userList.add(user);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
			
		} finally {
			
			ConnectionUtil.close(conn, ps, rs);
		}
		return userList;
	}
	
	/**
	 * Retrieves a user from the database based on their user ID.
	 *
	 * @param userId The ID of the user to retrieve.
	 * @return The User object corresponding to the provided user ID.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              retrieval.
	 */
	
	//Find By Id
	
	public User findById(int userId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT user_id, first_name, middle_name, last_name, email, password, phone_no, is_active FROM users WHERE is_active = 1 AND user_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setMiddleName(rs.getString("middle_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("phone_no"));
				user.setActive(rs.getBoolean("is_active"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

	
	//Find By Email Id
	public User findByEmail(String email) throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT user_id, first_name, middle_name, last_name, email, password, phone_no, is_active FROM users WHERE is_active = 1 AND email = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setMiddleName(rs.getString("middle_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("phone_no"));
				user.setActive(rs.getBoolean("is_active"));
				
			} else {
				throw new ValidationException("Invalid Email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}
	
	
	/**
	 * @return
	 */
	
	//Create User
	
	@Override
	public void create(User newUser) throws PersistenceException {
		
		    Connection conn = null;
		    PreparedStatement ps = null;
		    
		    try {
		    	
		        String query = "INSERT INTO users (first_name, middle_name, last_name, email, password, phone_no) VALUES (?,?,?,?,?,?)";
		        
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(query);
		        
		        ps.setString(1, newUser.getFirstName());    
		        ps.setString(2, newUser.getMiddleName());
		        ps.setString(3, newUser.getLastName());
		        ps.setString(4, newUser.getEmail());       
		        ps.setString(5, newUser.getPassword()); 
		        ps.setLong(6, newUser.getPhoneNumber());
		        ps.executeUpdate();
		        
		        System.out.println("User has been created successfully");
		       
		    } catch (SQLException e) {
		    	
		        e.printStackTrace();
		        System.out.println(e.getMessage());
		        throw new PersistenceException(e);
		        
		    } finally {
		    	
		        ConnectionUtil.close(conn, ps);
		    }
	}
	
	/**
	 * @return
	 */
	
	//Update User
	
	@Override
	public void update(int id, User updatedUser) throws PersistenceException {
		
		 Connection conn = null;
         PreparedStatement ps = null;
         
         try {
         
             StringBuilder queryBuilder = new StringBuilder("UPDATE users SET ");
             List<Object> values = new ArrayList<>();
             
             if (updatedUser.getFirstName() != null) {
                 queryBuilder.append("first_name = ?, ");
                 values.add(updatedUser.getFirstName());
             }
             
             if(updatedUser.getLastName() != null) {
             	queryBuilder.append("last_name = ?, ");
             	values.add(updatedUser.getLastName());
             }
             
             if(updatedUser.getMiddleName() != null) {
            	 queryBuilder.append("middle_name = ?, ");
            	 values.add(updatedUser.getMiddleName());
             }
             
             if (updatedUser.getPassword() != null) {
                 queryBuilder.append("password = ?, ");
                 values.add(updatedUser.getPassword());
             }
             
 	        if (updatedUser.getPhoneNumber() != 0) {
	            queryBuilder.append("phone_no = ?, ");
	            values.add(updatedUser.getPhoneNumber());
	        }
            
             queryBuilder.setLength(queryBuilder.length() - 2);
             queryBuilder.append(" WHERE is_active = 1 AND user_id = ?");
             conn = ConnectionUtil.getConnection();
             ps = conn.prepareStatement(queryBuilder.toString());
            
             for (int i = 0; i < values.size(); i++) {
                 ps.setObject(i + 1, values.get(i));
             }
             ps.setInt(values.size() + 1, id);
             ps.executeUpdate();
             
             System.out.println("User has been updated successfully");
        
         } catch (SQLException e) {
            
             e.printStackTrace();
             System.out.println(e.getMessage());
             throw new RuntimeException(e);
        
         } catch (RuntimeException er) {
             
             er.printStackTrace();
             System.out.println(er.getMessage());
             throw new RuntimeException(er);
             
         } finally {
             ConnectionUtil.close(conn, ps);
         }
	}
	
	//Delete User
	public void deleteUser(int userId) throws PersistenceException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			String query = "DELETE FROM users WHERE user_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.executeUpdate();
			System.out.println("User has been deleted successfully");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
			
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}
	
	/**
	 * Retrieves the ID of the last updated non-designer user who is active.
	 *
	 * @return The ID of the last updated non-designer user.
	 * @throws PersistenceException If a database error occurs while retrieving the
	 *                              ID.
	 */
	
	//GetLast Updated User
	
	public static int getLastUpdatedUserId() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int userId = 0;
		
		try {
			
			String query = "SELECT user_id FROM users WHERE is_active = 1 ORDER BY created_at DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				userId = rs.getInt("user_id");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
			
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return userId;
	}
}
