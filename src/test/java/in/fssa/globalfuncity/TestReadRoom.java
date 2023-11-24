package in.fssa.globalfuncity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ServiceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.service.RoomService;


public class TestReadRoom {
	
	//Get All Rooms
    @Test
	public void getAllRooms() throws PersistenceException, ServiceException {
		RoomService roomService = new RoomService();
		Set<Room> arr = roomService.getAllRooms("2023-10-05","2023-10-06");
		for(Room room:arr) {
		System.out.println(room);
		}
	}
    
    //Get All Rooms By No Of Beds
    @Test
    public void getAllRoomsByNoOfBeds() throws PersistenceException {
    	RoomService roomService = new RoomService();
    	Set<Room> arr = roomService.getAllRoomsByNoOfBeds(4);
    	System.out.println(arr);
    }
    
	//Get Room By Id
    @Test
    public void testGetRoomByRoomId() {
        assertDoesNotThrow(() -> {
        	RoomService roomService = new RoomService();
            Room arr = RoomService.findByRoomId(2);
            System.out.println(arr);
        });
    }
}
