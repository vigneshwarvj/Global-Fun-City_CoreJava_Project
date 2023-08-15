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
import in.fssa.globalfuncity.interfaces.UserInterface;
import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.util.ConnectionUtil;

public class UserDAO implements UserInterface<User>{
	
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
             
             if(updatedUser.getMiddleName() != null) {
             	queryBuilder.append("middle_name = ?, ");
             	values.add(updatedUser.getMiddleName());
             }
             
             if(updatedUser.getLastName() != null) {
             	queryBuilder.append("last_name = ?, ");
             	values.add(updatedUser.getLastName());
             }
             
             if (updatedUser.getPassword() != null) {
                 queryBuilder.append("password = ?, ");
                 values.add(updatedUser.getPassword());
             }
             
             if (updatedUser.getEmail() != null) {
                 queryBuilder.append("email = ?, ");
                 values.add(updatedUser.getEmail());
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
	
}
