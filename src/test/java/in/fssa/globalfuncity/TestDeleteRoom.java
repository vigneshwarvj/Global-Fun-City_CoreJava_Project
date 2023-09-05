package in.fssa.globalfuncity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.dao.RoomDAO;
import in.fssa.globalfuncity.service.RoomService;

public class TestDeleteRoom {

    //Delete Room:
	 @Test
	 public void testDeleteRoom() {
	        assertDoesNotThrow(() -> {
	        	RoomService roomService = new RoomService();
	            RoomDAO roomDAO = new RoomDAO();
	            int room = roomDAO.getLastUpdatedRoomId();
	            roomService.deleteRoom(room);
	        });
	    }
}
