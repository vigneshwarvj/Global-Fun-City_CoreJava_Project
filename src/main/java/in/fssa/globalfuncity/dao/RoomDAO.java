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
import in.fssa.globalfuncity.interfaces.RoomInterface;
import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.util.ConnectionUtil;


public class RoomDAO {

	/**
	 * 
	 * @param object
	 * @throws PersistenceException
	 */
	
	//Create Room
	
	public static void create(Room room) throws PersistenceException {

		Connection conn = null;
	    PreparedStatement ps = null;
        
        try {
        	String query = "INSERT INTO rooms (hotel_name, room_name, no_of_beds, price) VALUES (?, ?, ?, ?)";
        
        	conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(query);
        	
            ps.setString(1, room.getHotelName());
            ps.setString(2, room.getRoomName());
            ps.setInt(3, room.getNoOfBeds());
            ps.setInt(4, room.getPrice());
            
            ps.executeUpdate();
		
            System.out.println("Room has been created successfully");
            
	}  catch (SQLException e) {
		
           e.printStackTrace();
           System.out.println(e.getMessage());
           throw new PersistenceException(e.getMessage());
        
    }  finally {
    	
        	ConnectionUtil.close(conn, ps);
    }
}
	
	
	/**
	 * 
	 * @param id
	 * @param object
	 * @throws PersistenceException
	 */
	
	//Update Room
	
	public void update(int roomId, Room updateRoom) throws PersistenceException {

	        Connection conn = null;
	        PreparedStatement ps = null;

	 try {
	        StringBuilder queryBuilder = new StringBuilder("UPDATE rooms SET ");
	        List<Object> values = new ArrayList<>();

	        if (updateRoom.getHotelName() != null) {
	             queryBuilder.append("hotel_name = ?, ");
	             values.add(updateRoom.getHotelName());
	        }

	        if (updateRoom.getRoomName() != null) {
	             queryBuilder.append("room_name = ?, ");
	             values.add(updateRoom.getRoomName());
	        }

	        if (updateRoom.getNoOfBeds() > 0) {
	             queryBuilder.append("no_of_beds = ?, ");
	             values.add(updateRoom.getNoOfBeds());
	        }

	        if (updateRoom.getPrice() > 0) {
	            queryBuilder.append("price = ?, ");
	            values.add(updateRoom.getPrice());
	        }

	        queryBuilder.setLength(queryBuilder.length() - 2);
	        queryBuilder.append(" WHERE room_id = ?");
	        conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(queryBuilder.toString());

	        for (int i = 0; i < values.size(); i++) {
	                ps.setObject(i + 1, values.get(i));
	        }
	        ps.setInt(values.size() + 1, roomId);
	        ps.executeUpdate();

	            System.out.println("Room information has been updated successfully");

	        } catch (SQLException e) {
	        	
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistenceException(e.getMessage());
	            
	        } catch (RuntimeException er) {
	        	
	        er.printStackTrace();
	        System.out.println(er.getMessage());
	        throw new RuntimeException(er);
	            
	        } finally {
	        	
	         ConnectionUtil.close(conn, ps);
	            
	        }
}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	
	//List All Rooms
	
	public Set<Room> listAllRooms() throws PersistenceException {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Set<Room> rooms = new HashSet<>();

        try {
        	
            String query = "SELECT room_id, hotel_name, room_name, no_of_beds, price FROM rooms";
            
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                room.setRoomId(rs.getInt("room_id"));
                room.setHotelName(rs.getString("hotel_name"));
                room.setRoomName(rs.getString("room_name"));
                room.setNoOfBeds(rs.getInt("no_of_beds"));
                room.setPrice(rs.getInt("price"));
                rooms.add(room);
            }

        } catch (SQLException e) {
        	
            e.printStackTrace();
            throw new PersistenceException(e.getMessage());
            
        } catch (RuntimeException er) {
        	
            er.printStackTrace();
            System.out.println(er.getMessage());
            throw new RuntimeException(er);
            
        } finally {
        	
            ConnectionUtil.close(conn, ps, rs);
            
        }
        return rooms;
    }
		

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	
	//List All rooms by No of Beds
	
    public Set<Room> listAllRoomsByNoOfBeds(int numberOfBeds) throws PersistenceException {
    	
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Set<Room> rooms = new HashSet<>();

        try {
        	
            String query = "SELECT room_id, hotel_name, room_name, no_of_beds, price FROM rooms WHERE no_of_beds = ?";
            
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, numberOfBeds);
            rs = ps.executeQuery();
            

            while (rs.next()) {
            	
                Room room = new Room();
                room.setRoomId(rs.getInt("room_id"));
                room.setHotelName(rs.getString("hotel_name"));
                room.setRoomName(rs.getString("room_name"));
                room.setNoOfBeds(rs.getInt("no_of_beds"));
                room.setPrice(rs.getInt("price"));
                rooms.add(room);
            }

        } catch (SQLException e) {
        	
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new PersistenceException(e.getMessage());
            
        } catch (RuntimeException er) {
        	
            er.printStackTrace();
            System.out.println(er.getMessage());
            throw new RuntimeException(er);
            
        } finally {
        	
            ConnectionUtil.close(conn, ps, rs);
        }
        
        return rooms;
    }	
}

