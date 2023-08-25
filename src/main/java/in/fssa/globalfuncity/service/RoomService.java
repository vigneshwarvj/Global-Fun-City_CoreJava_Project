package in.fssa.globalfuncity.service;

import java.util.Set;


import in.fssa.globalfuncity.dao.RoomDAO;
import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.validator.RoomExists;
import in.fssa.globalfuncity.validator.RoomValidator;


public class RoomService {
	
    /**
     * 
     * @param room
     * @throws ValidationException
     * @throws PersistenceException
     */
	
	// Create Room
    public void createRoom(Room room) throws ValidationException, PersistenceException {
            RoomValidator.validate(room);
            RoomExists.roomNameExists(room.getRoomName());
            RoomDAO roomDAO = new RoomDAO();
            roomDAO.create(room);
    }
    
    /**
     * 
     * @param roomId
     * @param updatedRoom
     * @throws ValidationException
     * @throws PersistenceException
     */
    
    //Update Room
    public void updateRoom(int roomId, Room updateRoom) throws ValidationException, PersistenceException {
    	
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
     * 
     * @return
     * @throws PersistenceException
     */
    
    //List All Rooms
	public Set<Room> getAllRooms() throws PersistenceException {
		
		RoomDAO roomDAO = new RoomDAO();
		
		Set<Room> roomList = roomDAO.listAllRooms();
		
		for(Room room : roomList) {
			System.out.println(room);
		}
		return roomList;
	}
	
	/**
	 * 
	 * @param numberOfBeds
	 * @return
	 * @throws PersistenceException
	 */
	
	//List All Rooms By No Of Beds
	public Set<Room> getAllRoomsByNoOfBeds(int numberOfBeds) throws PersistenceException {
		
		RoomDAO roomDAO = new RoomDAO();
		
		Set<Room> roomList = roomDAO.listAllRoomsByNoOfBeds(4);
		
		for(Room room : roomList) {
			System.out.println(room);
		}
		return roomList;
	}
}
