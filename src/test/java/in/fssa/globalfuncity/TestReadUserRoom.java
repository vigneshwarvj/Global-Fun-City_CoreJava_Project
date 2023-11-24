package in.fssa.globalfuncity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ServiceException;
import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.model.UserRoom;
import in.fssa.globalfuncity.service.RoomService;
import in.fssa.globalfuncity.service.UserRoomService;

public class TestReadUserRoom {

	// Without Sysout
	@Test
	public void getBookedRoomsHistoryByUserId() {
		
		UserRoomService userRoomService = new UserRoomService();
		int id = 23;
		
		assertDoesNotThrow(() -> {
			userRoomService.getAllBookedRoomTicketsByUserId(id);
		});
	}	
	
	//With Sysout
	@Test
	public void getBookedRoomsHistory() throws ServiceException, PersistenceException {
		UserRoomService userRoomService = new UserRoomService();
		List<UserRoom> arr = userRoomService.getAllBookedRoomTicketsByUserId(23);
		System.out.print(arr);
	}
	
	@Test
	public void getBookedRoomsByRoomId() throws ServiceException, PersistenceException {
		UserRoomService userRoomService = new UserRoomService();
		List<UserRoom> arr = userRoomService.getAllBookedRoomByRoomId(5);
		System.out.print(arr);
	}
	
	//Get All Users Rooms
	@Test
	public void getAllUsersRooms() throws PersistenceException, ServiceException {
		UserRoomService userRoomService = new UserRoomService();
		Set<UserRoom> arr = userRoomService.getAllUsersRooms();
		System.out.println(arr);
	}
}
