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
import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.model.UserRoom;
import in.fssa.globalfuncity.util.ConnectionUtil;

public class UserRoomDAO {

    //Book a room
    public void bookRoom(int userId, int ticketId, int roomId, String checkInDate, String checkOutDate, String roomName, int noOfNights,int totalPrice ) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			String query = "INSERT INTO users_rooms (user_id, ticket_id, room_booked_id, from_date, to_date, room_name, no_of_nights, total_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, ticketId);
			ps.setInt(3, roomId);
			ps.setString(4, checkInDate);
			ps.setString(5, checkOutDate);
			ps.setString(6, roomName);
			ps.setInt(7, noOfNights);
			ps.setInt(8, totalPrice);
			
			ps.executeUpdate();
			
			System.out.println(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
    }
    
    
    // Get All Rooms History By User id
    public List<UserRoom> getAllRoomsHistoryByUserId(int userId) throws PersistenceException {
    	
    	List<UserRoom> userRooms = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
        	conn = ConnectionUtil.getConnection();
            String query = "SELECT user_id, ticket_id, room_booked_id, room_name, no_of_nights, total_price FROM users_rooms WHERE user_id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	UserRoom userRoom = new UserRoom();
            	userRoom.setUserId(rs.getInt("user_id"));
            	userRoom.setTicketId(rs.getInt("ticket_id"));
            	userRoom.setRoomId(rs.getInt("room_booked_id"));
            	userRoom.setRoomName(rs.getString("room_name"));
            	userRoom.setNoOfNights(rs.getInt("no_of_nights"));
            	userRoom.setTotalPrice(rs.getInt("total_price"));
            	
            	userRooms.add(userRoom);
            }
        } catch (SQLException e) {
        	
            throw new PersistenceException("Error retrieving booked Rooms");
            
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return userRooms;
    }
    
    
    public List<UserRoom> getAllBookedRoomsByRoomId(int roomId) throws PersistenceException {
        List<UserRoom> bookedRooms = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionUtil.getConnection();
            String query = "SELECT user_id, ticket_id, room_booked_id, room_name, no_of_nights, total_price FROM users_rooms WHERE room_booked_id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, roomId);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                UserRoom bookedRoom = new UserRoom();
                bookedRoom.setUserId(rs.getInt("user_id"));
                bookedRoom.setTicketId(rs.getInt("ticket_id"));
                bookedRoom.setRoomId(rs.getInt("room_booked_id"));
                bookedRoom.setRoomName(rs.getString("room_name"));
                bookedRoom.setNoOfNights(rs.getInt("no_of_nights"));
                bookedRoom.setTotalPrice(rs.getInt("total_price"));
                
                bookedRooms.add(bookedRoom);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error retrieving booked rooms by room ID");
        } finally {
            ConnectionUtil.close(conn, ps, rs);
        }
        return bookedRooms;
    }
    
    //List All Users Rooms
    
    public Set<UserRoom> listAllUsersRooms() throws PersistenceException {
    	
    	Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Set<UserRoom> userRooms = new HashSet<>();
        
        try {
        	
        	String query = "SELECT user_room_id, user_id, ticket_id, room_booked_id, from_date, to_date, room_name, no_of_nights, total_price FROM users_rooms;";
        	
//        	String query = "SELECT user_room_id, user_id, ticket_id, room_booked_id, room_name, no_of_nights, total_price FROM users_rooms "
//        			+ "  WHERE CURDATE() NOT BETWEEN from_date AND to_date;";
        	
        	conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	
            	UserRoom userRoom = new UserRoom();
            	userRoom.setUserRoomId(rs.getInt("user_room_id"));
            	userRoom.setUserId(rs.getInt("user_id"));
            	userRoom.setTicketId(rs.getInt("ticket_id"));
            	userRoom.setRoomId(rs.getInt("room_booked_id"));
            	userRoom.setCheckInDate(rs.getString("from_date"));
            	userRoom.setCheckOutDate(rs.getString("to_date"));
            	userRoom.setRoomName(rs.getString("room_name"));
            	userRoom.setNoOfNights(rs.getInt("no_of_nights"));
            	userRoom.setTotalPrice(rs.getInt("total_price"));
            	
            	userRooms.add(userRoom);
            	
            }
            
        }  catch (SQLException e) {
        	
            e.printStackTrace();
            throw new PersistenceException(e);
            
        } catch (RuntimeException er) {
        	
            er.printStackTrace();
            System.out.println(er.getMessage());
            throw new RuntimeException(er);
            
        } finally {
        	
            ConnectionUtil.close(conn, ps, rs);
            
        }
		return userRooms;    	
    }
}
