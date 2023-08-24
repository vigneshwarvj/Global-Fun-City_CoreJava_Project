package in.fssa.globalfuncity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.service.RoomService;

public class TestCreateRoom {

	//With Valid all input
	@Test
    public void testCreateRoomWithValidInput() {
        RoomService roomService = new RoomService();
        Room room = new Room();
        room.setHotelName("Freshworks");
        room.setRoomName("Kelvi");
        room.setNoOfBeds(4);
        room.setPrice(60);
        assertDoesNotThrow(() -> {
            roomService.create(room);
        });
    }
	
	//With Invalid input
	@Test
	public void testCreateRoomWithInvalidInput() {
		RoomService roomService = new RoomService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			roomService.create(null);
		});
		String expectedMessage = "Room object can not be null";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//Hotelname Null
	@Test
	public void testCreateRoomWithHotelNameNull() {
		RoomService roomService = new RoomService();
		Room room = new Room();
		room.setHotelName(null);
		room.setRoomName("OMR");
		room.setNoOfBeds(2);
		room.setPrice(40);

		Exception exception = assertThrows(Exception.class, () -> {
			roomService.create(room);
		});
		String expectedMessage = "Hotel Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//Hotelname Empty
	@Test
	public void testCreateRoomWithHotelNameEmpty() {
		RoomService roomService = new RoomService();
		Room room = new Room();
		room.setHotelName("");
		room.setRoomName("OMR");
		room.setNoOfBeds(2);
		room.setPrice(40);

		Exception exception = assertThrows(Exception.class, () -> {
			roomService.create(room);
		});
		String expectedMessage = "Hotel Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//HotelName Pattern
	@Test
	public void testCreateRoomWithHotelNamePattern() {
		RoomService roomService = new RoomService();
		Room room = new Room();
		room.setHotelName("");
		room.setRoomName("OMR");
		room.setNoOfBeds(2);
		room.setPrice(40);

		Exception exception = assertThrows(Exception.class, () -> {
			roomService.create(room);
		});
		String expectedMessage = "Hotel Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//RoomName Null
	@Test
	public void testCreateRoomWithRoomNameNull() {
		RoomService roomService = new RoomService();
		Room room = new Room();
		room.setHotelName("King");
		room.setRoomName(null);
		room.setNoOfBeds(2);
		room.setPrice(40);

		Exception exception = assertThrows(Exception.class, () -> {
			roomService.create(room);
		});
		String expectedMessage = "Room Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//RoomName Empty
	@Test
	public void testCreateRoomWithRoomNameEmpty() {
		RoomService roomService = new RoomService();
		Room room = new Room();
		room.setHotelName("King");
		room.setRoomName("");
		room.setNoOfBeds(2);
		room.setPrice(40);

		Exception exception = assertThrows(Exception.class, () -> {
			roomService.create(room);
		});
		String expectedMessage = "Room Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//RoomName Pattern
	@Test
	public void testCreateRoomWithRoomNamePattern() {
		RoomService roomService = new RoomService();
		Room room = new Room();
		room.setHotelName("King");
		room.setRoomName("ECR");
		room.setNoOfBeds(2);
		room.setPrice(40);

		Exception exception = assertThrows(Exception.class, () -> {
			roomService.create(room);
		});
		String expectedMessage = "Room Name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//RoomName Already Exists
	@Test
	public void testCreateRoomWithRoomNameExists() {
		RoomService roomService = new RoomService();
		Room room = new Room();
		room.setHotelName("Freshworks");
		room.setRoomName("Kelvi");
		room.setNoOfBeds(2);
		room.setPrice(40);
		
		Exception exception = assertThrows(Exception.class, () -> {
			roomService.create(room);
		});
		String expectedMessage = "Room Name already exists";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
		
	}
	
	//No of beds is 0
	@Test
	public void testCreateRoomWithZeroBed() {
		RoomService roomService = new RoomService();
		Room room = new Room();
		room.setHotelName("King");
		room.setRoomName("OMR");
		room.setNoOfBeds(0);
		room.setPrice(10);
		
		ValidationException exception = assertThrows(ValidationException.class, () -> {
		       roomService.create(room);
		});
	
		String expectedMessage = "Number of beds should be greater than zero";
		String actualMessage = exception.getMessage();
	
		  assertTrue(expectedMessage.equals(actualMessage));
	
	}
	
	//Price
	@Test
	public void testCreateRoomWithZeroPrice() {
		RoomService roomService = new RoomService();
		Room room = new Room();
		room.setHotelName("King");
		room.setRoomName("OMR");
		room.setNoOfBeds(1);
		room.setPrice(0);
		
		ValidationException exception = assertThrows(ValidationException.class, () -> {
		       roomService.create(room);
		});
	
		String expectedMessage = "Price should be a positive integer and should be greater than zero";
		String actualMessage = exception.getMessage();
	
		  assertTrue(expectedMessage.equals(actualMessage));
	
	}

}
