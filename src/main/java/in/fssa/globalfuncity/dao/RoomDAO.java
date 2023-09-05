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
        	
            String query = "SELECT room_id, hotel_name, room_name, no_of_beds, price, room_image, room_amenities  FROM rooms WHERE is_active = 1;";
            
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
    
    //List room by room id
    
    public Room findByRoomId(int roomId) throws PersistenceException {
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
    
    //Delete room;
    public void deleteRoom(int roomId) throws PersistenceException {
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
			ConnectionUtil.close(conn, ps, null);
		}
	}
    
	//GetLast Updated User
	
	public static int getLastUpdatedRoomId() throws PersistenceException {
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
}

