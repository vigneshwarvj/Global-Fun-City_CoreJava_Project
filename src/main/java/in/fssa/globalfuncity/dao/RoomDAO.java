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

	/**
	 * The RoomDAO class is responsible for managing room-related data in a web application.
	 * It includes methods for creating, updating, listing, and retrieving room information
	 * from a database. The class also provides the ability to deactivate rooms and track
	 * the most recently modified room.
	 */

public class RoomDAO {

    /**
     * Creates a new room entry in the database with the provided room details.
     *
     * @param room The Room object representing the room to be created.
     * @throws PersistenceException If there is an issue with database operations.
     */
	
	//Create Room
	
	public static void create(Room room) throws PersistenceException {

		// Insert room details into the database.
        // Marks the room as active.
		
		Connection conn = null;
	    PreparedStatement ps = null;
        
        try {
        	String query = "INSERT INTO rooms (hotel_name, room_name, no_of_beds, price, room_image, room_amenities) VALUES (?, ?, ?, ?, ?, ?)";
        
        	conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(query);
        	
            ps.setString(1, room.getHotelName());
            ps.setString(2, room.getRoomName());
            ps.setInt(3, room.getNoOfBeds());
            ps.setInt(4, room.getPrice());
            ps.setString(5, room.getRoomImageUrl());
            ps.setString(6, room.getRoomAmenities());
            
            ps.executeUpdate();
		
            System.out.println("Room has been created successfully");
            
        }  catch (SQLException e) {
		
           e.printStackTrace();
           System.out.println(e.getMessage());
           throw new PersistenceException(e);
        
        }  finally {
    	
        	ConnectionUtil.close(conn, ps);
        }
	}
	
	
    /**
     * Updates the details of a room in the database based on the provided Room object.
     *
     * @param roomId      The unique identifier of the room to be updated.
     * @param updateRoom  The Room object containing updated room details.
     * @throws PersistenceException If there is an issue with database operations.
     */
	
	//Update Room
	
	public void update(int roomId, Room updateRoom) throws PersistenceException {

        // Construct and execute an SQL UPDATE statement to modify room details.
		
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

	        if(updateRoom.getRoomImageUrl() != null) {
	        	queryBuilder.append("room_image = ?, ");
	        	values.add(updateRoom.getRoomImageUrl());
	        }
	        
	        if(updateRoom.getRoomAmenities() != null) {
	        	queryBuilder.append("room_amenities = ?, ");
	        	values.add(updateRoom.getRoomAmenities());
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
	        throw new PersistenceException(e);
	            
	        } catch (RuntimeException er) {
	        	
	        er.printStackTrace();
	        System.out.println(er.getMessage());
	        throw new RuntimeException(er);
	            
	        } finally {
	        	
	         ConnectionUtil.close(conn, ps);
	            
	        }
	}

    /**
     * Retrieves a set of all active rooms from the database.
     *
     * @return A Set of Room objects representing available rooms.
     * @throws PersistenceException If there is an issue with database operations.
     */
	
	//List All Rooms
	
	public Set<Room> listAllRooms() throws PersistenceException {
		
        // Retrieve and construct Room objects for all active rooms.
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Set<Room> rooms = new HashSet<>();

        try {
        	
            String query = "SELECT room_id, hotel_name, room_name, no_of_beds, price, room_image, room_amenities FROM rooms WHERE is_active = 1;";
            
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
                room.setRoomImageUrl(rs.getString("room_image"));
                room.setRoomAmenities(rs.getString("room_amenities"));
                
                rooms.add(room);
            }

        } catch (SQLException e) {
        	
            e.printStackTrace();
            throw new PersistenceException(e);
            
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
     * Retrieves a set of rooms with a specific number of beds from the database.
     *
     * @param numberOfBeds The desired number of beds in the rooms.
     * @return A Set of Room objects matching the specified bed count.
     * @throws PersistenceException If there is an issue with database operations.
     */
	
	//List All rooms by No of Beds
	
    public Set<Room> listAllRoomsByNoOfBeds(int numberOfBeds) throws PersistenceException {
    	
        // Retrieve and construct Room objects for rooms with the specified number of beds.
    	
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Set<Room> rooms = new HashSet<>();

        try {
        	
            String query = "SELECT room_id, hotel_name, room_name, no_of_beds, price, room_image, room_amenities FROM rooms WHERE no_of_beds = ?";
            
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
                room.setRoomImageUrl(rs.getString("room_image"));
                room.setRoomAmenities(rs.getString("room_amenities"));                
                rooms.add(room);
            }

        } catch (SQLException e) {
        	
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new PersistenceException(e);
            
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
     * Retrieves room details based on a room's unique identifier (room ID).
     *
     * @param roomId The unique identifier of the room to be retrieved.
     * @return A Room object containing the details of the specified room.
     * @throws PersistenceException If there is an issue with database operations.
     */
    
    //List room by room id
    
    public Room findByRoomId(int roomId) throws PersistenceException {
    	
        // Retrieve and construct a Room object for the specified room.
    	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Room room = null;
		try {
			String query = "SELECT room_id, hotel_name, room_name, no_of_beds, price, room_image, room_amenities, is_active FROM rooms WHERE room_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, roomId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				room = new Room();
				room.setRoomId(rs.getInt("room_id"));
				room.setHotelName(rs.getString("hotel_name"));
				room.setRoomName(rs.getString("room_name"));
				room.setNoOfBeds(rs.getInt("no_of_beds"));
				room.setPrice(rs.getInt("price"));
                room.setRoomImageUrl(rs.getString("room_image"));
                room.setRoomAmenities(rs.getString("room_amenities"));				
				room.setActiveRoom(rs.getBoolean("is_active"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return room;
	}
    
    /**
     * Deactivates a room in the database, marking it as inactive.
     *
     * @param roomId The unique identifier of the room to be deactivated.
     * @throws PersistenceException If there is an issue with database operations.
     */
    
    //Delete room
    
    public void deleteRoom(int roomId) throws PersistenceException {
    	
        // Marks the room as inactive in the database.
    	
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE rooms SET is_active = 0 WHERE room_id = ? AND is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, roomId);
			ps.executeUpdate();
			System.out.println("Room has been deleted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}
    
    
    /**
     * Retrieves the ID of the last room that was created or updated.
     *
     * @return The ID of the most recently modified room.
     * @throws PersistenceException If there is an issue with database operations.
     */
    
	//GetLast Updated Room
	
	public static int getLastUpdatedRoomId() throws PersistenceException {
		
        // Retrieve the ID of the last room that was created or updated.
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int roomId = 0;
		
		try {
			
			String query = "SELECT room_id FROM rooms WHERE is_active = 1 ORDER BY created_at DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				roomId = rs.getInt("room_id");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
			
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return roomId;
	}
	
	// ListOfRoomsForAdmins
	
	public Set<Room> listAllRoomsForAdmins() throws PersistenceException {
		
        // Retrieve and construct Room objects for all active rooms.
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Set<Room> rooms = new HashSet<>();

        try {
        	
            String query = "SELECT room_id, hotel_name, room_name, no_of_beds, price, room_image, room_amenities FROM rooms WHERE is_active = 1 ORDER BY room_id ASC";
            
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
                room.setRoomImageUrl(rs.getString("room_image"));
                room.setRoomAmenities(rs.getString("room_amenities"));
                
                rooms.add(room);
            }

        } catch (SQLException e) {
        	
            e.printStackTrace();
            throw new PersistenceException(e);
            
        } catch (RuntimeException er) {
        	
            er.printStackTrace();
            System.out.println(er.getMessage());
            throw new RuntimeException(er);
            
        } finally {
        	
            ConnectionUtil.close(conn, ps, rs);
            
        }
        return rooms;
    }
	
	
//	public static void updateRoomStatus(int roomId) throws PersistenceException {
//		
//		Connection conn = null;
//		PreparedStatement ps = null;
//		
//		try {
//			String query = "UPDATE rooms SET status = 'booked' WHERE room_id = ? AND is_active = 1";
//			conn = ConnectionUtil.getConnection();
//			ps = conn.prepareStatement(query);
//			ps.setInt(1, roomId);
//			ps.executeUpdate();
//			System.out.println("Room status has been deleted successfully");
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			throw new PersistenceException(e);
//			
//		} finally {
//			ConnectionUtil.close(conn, ps);
//		}
//		
//	}
	
	//List All rooms with status
	/*
	 * public Set<Room> listAllRoomsWithUpdatedStatus() throws PersistenceException
	 * {
	 * 
	 * Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
	 * 
	 * Set<Room> rooms = new HashSet<>();
	 * 
	 * try {
	 * 
	 * String query =
	 * "SELECT room_id, hotel_name, room_name, no_of_beds, price, room_image, room_amenities, status FROM rooms WHERE status = 'not_booked';"
	 * ;
	 * 
	 * conn = ConnectionUtil.getConnection(); ps = conn.prepareStatement(query); rs
	 * = ps.executeQuery();
	 * 
	 * while (rs.next()) { Room room = new Room();
	 * room.setRoomId(rs.getInt("room_id"));
	 * room.setHotelName(rs.getString("hotel_name"));
	 * room.setRoomName(rs.getString("room_name"));
	 * room.setNoOfBeds(rs.getInt("no_of_beds")); room.setPrice(rs.getInt("price"));
	 * room.setRoomImageUrl(rs.getString("room_image"));
	 * room.setRoomAmenities(rs.getString("room_amenities"));
	 * room.setStatus(rs.getString("status"));
	 * 
	 * rooms.add(room); }
	 * 
	 * } catch (SQLException e) {
	 * 
	 * e.printStackTrace(); throw new PersistenceException(e);
	 * 
	 * } catch (RuntimeException er) {
	 * 
	 * er.printStackTrace(); System.out.println(er.getMessage()); throw new
	 * RuntimeException(er);
	 * 
	 * } finally {
	 * 
	 * ConnectionUtil.close(conn, ps, rs);
	 * 
	 * } return rooms; }
	 */
	
	
}

