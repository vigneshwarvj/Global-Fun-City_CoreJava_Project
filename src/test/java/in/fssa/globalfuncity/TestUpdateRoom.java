package in.fssa.globalfuncity;

import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.service.RoomService;

public class TestUpdateRoom {

	//Update Room
	@Test
	public void testUpdateRoom() throws ValidationException, PersistenceException {
		
		RoomService roomService = new RoomService();
		Room room = new Room();
		room.setHotelName("Freshworks");
		room.setRoomName("Kelvi");
		room.setNoOfBeds(4);
		room.setPrice(80);
		roomService.updateRoom(1, room);
	}
	
}
