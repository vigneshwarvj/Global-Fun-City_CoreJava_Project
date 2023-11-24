package in.fssa.globalfuncity.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import in.fssa.globalfuncity.dao.RoomDAO;
import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ServiceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.model.UserRoom;
import in.fssa.globalfuncity.validator.RoomExists;
import in.fssa.globalfuncity.validator.RoomValidator;

	/**
	 * The RoomService class is responsible for managing room-related operations and business logic
	 * in a web application. It acts as an intermediary layer between the web interface and the data
	 * persistence layer, providing services for creating, updating, listing, retrieving, and deleting
	 * room information.
	 */

public class RoomService {
	
    /**
     * Creates a new room in the system, validating the room details and checking for the existence
     * of a room with the same name. If the room is valid, it is added to the database.
     *
     * @param room The Room object representing the room to be created.
     * @throws ValidationException If the room details are invalid.
     * @throws PersistenceException If there is an issue with database operations.
     */
	
	// Create Room
	
    public void createRoom(Room room) throws ValidationException, PersistenceException {
    	
        // Validates room details, checks for duplicate room names, and creates the room in the database.
    	
            RoomValidator.validate(room);
            RoomExists.roomNameExists(room.getRoomName());
            RoomDAO roomDAO = new RoomDAO();
            roomDAO.create(room);
    }
    
    /**
     * Updates an existing room's information based on the provided room ID and updated room details.
     * It validates the room ID, checks for the existence of the room, and validates the updated room
     * details before performing the update.
     *
     * @param roomId      The unique identifier of the room to be updated.
     * @param updateRoom  The Room object containing updated room details.
     * @throws ValidationException If there are issues with room ID, room name, number of beds, or price validation.
     * @throws PersistenceException If there is an issue with database operations.
     */
    
    //Update Room
    
    public void updateRoom(int roomId, Room updateRoom) throws ValidationException, PersistenceException {
    	
        // Validates room ID and room details, checks for room existence, and performs the room update.
    	
    	RoomValidator.validateId(roomId);
    	RoomExists.checkIdExists(roomId);
    	
    	if(updateRoom.getRoomName() != null) {
    		RoomValidator.validateRoomName(updateRoom.getRoomName());
    	}
    	
    	if(updateRoom.getNoOfBeds() != 0) {
    		RoomValidator.validateNumberOfBeds(updateRoom.getNoOfBeds());
    	}
    	
    	if(updateRoom.getPrice() != 0) {
    		RoomValidator.validatePrice(updateRoom.getPrice());
    	}
    	
    	RoomDAO roomDAO =  new RoomDAO();
    	roomDAO.update(roomId, updateRoom);
    }
    
    /**
     * Retrieves a set of all available rooms that are not booked for a specified check-in and check-out date range.
     * It filters rooms based on their availability, considering reservations made by users within the provided dates.
     *
     * @param checkIn  The check-in date in the format "yyyy-MM-dd."
     * @param checkOut The check-out date in the format "yyyy-MM-dd."
     * @return A Set of Room objects representing available rooms.
     * @throws PersistenceException If there is an issue with database operations.
     * @throws ServiceException If there is a service-related error.
     */
    
    // Get All rooms
    
    public Set<Room> getAllRooms(String checkIn, String checkOut) throws PersistenceException, ServiceException {
    	
        // Retrieves all available rooms based on check-in and check-out dates, excluding booked rooms.
    	
        RoomDAO roomDAO = new RoomDAO();

        Set<Room> roomList = roomDAO.listAllRooms();
        LocalDate checkInLocal = LocalDate.parse(checkIn);
        LocalDate checkOutLocal = LocalDate.parse(checkOut);

        List<Room> list = new ArrayList<>(roomList);
        UserRoomService userRoomService = new UserRoomService();

        Set<UserRoom> getAllUsersRooms = userRoomService.getAllUsersRooms();

        List<UserRoom> listOfUsersRooms = new ArrayList<>(getAllUsersRooms);

        Set<Room> outputRoomsList = list.stream()
                .filter(room -> !isRoomBooked(room, listOfUsersRooms, checkInLocal, checkOutLocal))
                .collect(Collectors.toSet());

        return outputRoomsList;
    }

    private boolean isRoomBooked(Room room, List<UserRoom> listOfUsersRooms, LocalDate checkInLocal, LocalDate checkOutLocal) {
        for (UserRoom userRoom : listOfUsersRooms) {
            if (userRoom.getRoomId() == room.getRoomId()) {
                LocalDate checkInDate = LocalDate.parse(userRoom.getCheckInDate());
                LocalDate checkOutDate = LocalDate.parse(userRoom.getCheckOutDate());

                if (!checkInDate.isAfter(checkOutLocal) && !checkOutDate.isBefore(checkInLocal)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Retrieves a set of rooms that have a specific number of beds.
     *
     * @param numberOfBeds The desired number of beds in the rooms.
     * @return A Set of Room objects matching the specified bed count.
     * @throws PersistenceException If there is an issue with database operations.
     */
	
	//List All Rooms By No Of Beds
    
	public Set<Room> getAllRoomsByNoOfBeds(int numberOfBeds) throws PersistenceException {
		
	    // Retrieves and prints rooms with a specific number of beds.
		
		RoomDAO roomDAO = new RoomDAO();
		
		Set<Room> roomList = roomDAO.listAllRoomsByNoOfBeds(4);
		
		for(Room room : roomList) {
			System.out.println(room);
		}
		return roomList;
	}
	
    /**
     * Retrieves room details based on a room's unique identifier (room ID).
     *
     * @param roomId The unique identifier of the room to be retrieved.
     * @return A Room object containing the details of the specified room.
     * @throws ValidationException If there are issues with room ID validation.
     * @throws ServiceException If there is a service-related error.
     */
	
	//Get Rooms by room Id
	
	public static Room findByRoomId(int roomId) throws ValidationException, ServiceException {
		
        // Validates room ID and retrieves room details by ID.
		
	    try {
	        RoomValidator.validateRoomId(roomId);
	        RoomDAO roomDAO = new RoomDAO();
	        return roomDAO.findByRoomId(roomId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding room by their id.", e);
	    }
	}
	
    /**
     * Deactivates a room in the system, marking it as inactive and unavailable for booking.
     *
     * @param roomId The unique identifier of the room to be deactivated.
     * @throws ValidationException If there are issues with room ID validation.
     * @throws ServiceException If there is a service-related error.
     */
	
	//Delete Room
	
	public void deleteRoom(int roomId) throws ValidationException, ServiceException {
		
        // Validates room ID and deactivates the room in the database.
		
	    try {
	    	RoomValidator.validateRoomId(roomId);
	    	RoomDAO roomDAO = new RoomDAO();
	    	roomDAO.deleteRoom(roomId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while deleting room.", e);
	    }
	}
	
	//List of Rooms for admin
	public Set<Room> listAllRoomsForAdmin() throws PersistenceException {
			
			RoomDAO roomDAO = new RoomDAO();
			
			Set<Room> roomList = roomDAO.listAllRoomsForAdmins();
			
			for(Room room : roomList) {
				System.out.println(room);
			}
			return roomList;
	}
	
	
//	public void updateRoomStatus(int roomId) throws ServiceException, ValidationException {
//		
//		try {
//	    	RoomValidator.validateRoomId(roomId);
//	    	RoomDAO roomDAO = new RoomDAO();
//	    	roomDAO.updateRoomStatus(roomId);
//	    } catch (PersistenceException e) {
//	        throw new ServiceException("Error occurred while updating room status.", e);
//	    }
//		
//	}
	
	//List All Rooms By Updated Status
//		public static Set<Room> getAllRoomsByUpdatedStatus() throws PersistenceException {
//			
//			RoomDAO roomDAO = new RoomDAO();
//			
//			Set<Room> roomList = roomDAO.listAllRoomsWithUpdatedStatus();
//			
//			for(Room room : roomList) {
//				
//				System.out.println(room);
//			}
//			return roomList;
//		}
	
	
}
