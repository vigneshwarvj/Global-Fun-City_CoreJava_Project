package in.fssa.globalfuncity.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.util.ConnectionUtil;

public class RoomExists {
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void checkIdExists(int id) throws ValidationException {
		
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			
			String query = "Select * From rooms Where is_active = 1 AND room_id = ?";
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
	
}
