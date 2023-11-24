package in.fssa.globalfuncity.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import in.fssa.globalfuncity.dao.RoomDAO;
import in.fssa.globalfuncity.dao.TicketDAO;
import in.fssa.globalfuncity.dao.UserRoomDAO;
import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ServiceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.model.UserRoom;
import in.fssa.globalfuncity.validator.UserRoomDateValidator;

public class UserRoomService {

	//book a room
	public static void bookRoom(int userId, int ticketId, int roomId, String checkInDate, String checkOutDate, String roomName, int noOfNights,int totalPrice) throws PersistenceException, ValidationException {
		
		UserRoomDAO userRoomDAO = new UserRoomDAO();
		//UserRoomDateValidator.validate(userRoom);

		try {

			userRoomDAO.bookRoom(userId, ticketId, roomId, checkInDate, checkOutDate, roomName, noOfNights, totalPrice);
			 System.out.println("Room has been booked successfully.");
		} catch (PersistenceException e) {
            e.printStackTrace();
            throw new PersistenceException("Failed to book the room: " + e.getMessage());
        }
    }
	
	public List<UserRoom> getAllBookedRoomTicketsByUserId(int userId) throws ServiceException, PersistenceException {
		
		try {
			UserRoomDAO userRoomDAO = new UserRoomDAO();
			return userRoomDAO.getAllRoomsHistoryByUserId(userId);
		} catch (PersistenceException e) {
        	throw new ServiceException("Error occurred while retrieving Rooms History."+ e.getMessage());
        }
	}
	
	public List<UserRoom> getAllBookedRoomByRoomId(int roomId) throws ServiceException, PersistenceException {
		
		try {
			UserRoomDAO userRoomDAO = new UserRoomDAO();
			return userRoomDAO.getAllBookedRoomsByRoomId(roomId);
		} catch (PersistenceException e){
			throw new ServiceException("Error occured while retrieving Booked Rooms By Id");
		}
	}
	
	// List All UsersRooms 
		public static Set<UserRoom> getAllUsersRooms() throws PersistenceException, ServiceException {
			
			UserRoomDAO userRoomDAO = new UserRoomDAO();
			
			Set<UserRoom> usersRoomsList = userRoomDAO.listAllUsersRooms();
			
			//Getting the TicketService for CheckInDate and CheckOutDate
			TicketService ticketService = new TicketService();

			return usersRoomsList;
		}

}
