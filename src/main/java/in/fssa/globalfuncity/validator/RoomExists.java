package in.fssa.globalfuncity.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.util.ConnectionUtil;

	/**
	 * The `RoomExists` class provides utility methods for checking the existence of rooms and room names
	 * in the database. It helps validate room IDs and room names to ensure they are unique and available for use.
	 */

public class RoomExists {
	
    /**
     * Checks if a room with the given ID exists and is marked as active in the database.
     *
     * @param id The unique identifier of the room to be checked.
     * @throws ValidationException If the room ID doesn't exist or is marked as inactive.
     */
	
	public static void checkIdExists(int id) throws ValidationException {
		
        // Queries the database to check if the room ID exists and is active.
        // Throws a ValidationException if the room doesn't exist or is inactive.
		
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			
			String query = "SELECT room_id FROM rooms WHERE is_active = 1 AND room_id = ?";
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
     * Checks if a room with the given room name already exists in the database.
     *
     * @param roomName The name of the room to be checked for existence.
     * @throws ValidationException If a room with the same name already exists.
     */
	
	public static void roomNameExists(String roomName) throws ValidationException {
		
        // Queries the database to check if a room with the same name already exists.
        // Throws a ValidationException if a room with the same name is found.
		
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			
			String query = "SELECT room_name FROM rooms WHERE room_name = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, roomName);
			rs = pre.executeQuery();
			
			if (rs.next()) {
				throw new ValidationException("Room Name already exists");
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
