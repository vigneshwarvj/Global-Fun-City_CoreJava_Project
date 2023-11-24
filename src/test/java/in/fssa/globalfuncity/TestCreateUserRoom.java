package in.fssa.globalfuncity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.model.UserRoom;
import in.fssa.globalfuncity.service.UserRoomService;

public class TestCreateUserRoom {

	
	//Book a room
	@Test
	public void testBookRoomWithValidInput() {
		
		UserRoomService userRoomService = new UserRoomService();
		UserRoom userRoom = new UserRoom();
		userRoom.setUserId(1);
		userRoom.setTicketId(1);
		userRoom.setRoomId(4);
		
		assertDoesNotThrow(() -> {
		    userRoomService.bookRoom(userRoom, 1, 85);
		});	
	}
}
