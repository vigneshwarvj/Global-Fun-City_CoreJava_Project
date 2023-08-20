package in.fssa.globalfuncity.validator;

import java.util.regex.Pattern;

import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.util.StringUtil;

public class RoomValidator {

    private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
    
    /**
     * 
     * @param room
     * @throws ValidationException
     */
    public static void validate(Room room) throws ValidationException {
        if (room == null) {
            throw new ValidationException("Room object can not be null");
        }

        validateHotelName(room.getHotelName());
        validateRoomName(room.getRoomName());
        validateNumberOfBeds(room.getNoOfBeds());
        validatePrice(room.getPrice());
    }
    
    /**
     * 
     * @param roomId
     * @throws ValidationException
     */
	//RoomID Validation
	public static void validateId(int roomId) throws ValidationException{
		if(roomId <= 0) {
			throw new ValidationException("Id can't be less than or equal to zero");
		}
	}
    
	/**
	 * 
	 * @param hotelName
	 * @throws ValidationException
	 */
    //HotelName Validation
    public static void validateHotelName(String hotelName) throws ValidationException {
        StringUtil.rejectIfNullOrEmpty(hotelName, "Hotel Name");
        if(!Pattern.matches(NAME_PATTERN, hotelName)) {
        	throw new ValidationException("Hotel Name doesn't match the pattern");
    }	
    }

    /**
     * 
     * @param roomName
     * @throws ValidationException
     */
    //RoomName Validation
    public static void validateRoomName(String roomName) throws ValidationException {
        StringUtil.rejectIfNullOrEmpty(roomName, "Room Name");
        if(!Pattern.matches(NAME_PATTERN, roomName)) {
        	throw new ValidationException("Room Name doesn't match the pattern");        
        }
    }

    /**
     * 
     * @param numberOfBeds
     * @throws ValidationException
     */
    //NumberOfBeds Validation
    public static void validateNumberOfBeds(int numberOfBeds) throws ValidationException {
        if (numberOfBeds <= 0) {
            throw new ValidationException("Number of beds should be greater than zero");
        }
    }

    /**
     * 
     * @param price
     * @throws ValidationException
     */
    //Price Validation
    public static void validatePrice(int price) throws ValidationException {
        if (price <= 0) {
            throw new ValidationException("Price should be a positive integer and should be greater than zero");
        }
    }
}

