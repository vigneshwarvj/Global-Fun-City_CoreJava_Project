package in.fssa.globalfuncity;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.service.RoomService;


public class TestGetAllRoom {

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
	
	//Get All Rooms
    @Test
	public void getAllRooms() throws PersistenceException {
		RoomService roomService = new RoomService();
		Set<Room> arr = roomService.getAll();
		System.out.println(arr);
	}
    
    //Get All Rooms By No Of Beds
    public void getAllRoomsByNoOfBeds() throws PersistenceException {
    	RoomService roomService = new RoomService();
    	Set<Room> arr = roomService.getAllRoomsByNoOfBeds(4);
    	System.out.println(arr);
    }
}
