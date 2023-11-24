package in.fssa.globalfuncity.validator;

import java.util.regex.Pattern;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ServiceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.util.StringUtil;

	/**
	 * The `RoomValidator` class provides validation methods to ensure the integrity and correctness
	 * of room-related data in a web application. It includes methods to validate room objects, room IDs,
	 * hotel names, room names, the number of beds, and room prices. Additionally, it checks the formatting
	 * of hotel names and room names against a specified regular expression pattern.
	 */

public class RoomValidator {

    private static final String NAME_PATTERN = "^[A-Za-z]+(\\s?[A-Za-z]+)*$";
    
    /**
     * Validates a `Room` object, ensuring it is not null and that its attributes meet validation criteria.
     *
     * @param room The `Room` object to be validated.
     * @throws ValidationException If the `Room` object is null or any attribute fails validation.
     */
    
    public static void validate(Room room) throws ValidationException {
    	
        // Validates the Room object and its attributes, including hotel name, room name, number of beds, and price.
    	
        if (room == null) {
            throw new ValidationException("Room object can not be null");
        }

        validateHotelName(room.getHotelName());
        validateRoomName(room.getRoomName());
        validateNumberOfBeds(room.getNoOfBeds());
        validatePrice(room.getPrice());
    }
    
    /**
     * Validates a room ID, ensuring it is a positive integer.
     *
     * @param roomId The unique identifier of the room to be validated.
     * @throws ValidationException If the room ID is less than or equal to zero.
     */
    
	//RoomID Validation
    
	public static void validateId(int roomId) throws ValidationException{
		
        // Validates the room ID, ensuring it is greater than zero.
		
		if(roomId <= 0) {
			throw new ValidationException("Id can't be less than or equal to zero");
		}
	}
    
    /**
     * Validates a room ID by calling `checkIdExists` to ensure the ID exists in the database.
     *
     * @param roomId The unique identifier of the room to be validated.
     * @throws ValidationException If there are issues with the room ID or if it doesn't exist in the database.
     * @throws ServiceException If there is a service-related error when checking for room existence.
     */
	
	//Validate room Id
	
	public static void validateRoomId(int roomId) throws ValidationException, ServiceException {
		
        // Validates the room ID and checks its existence in the database using `checkIdExists`.
		
		validateId(roomId);
		RoomExists.checkIdExists(roomId);
	}
	
    /**
     * Validates a hotel name, ensuring it is not null and matches a specified regular expression pattern.
     *
     * @param hotelName The name of the hotel to be validated.
     * @throws ValidationException If the hotel name is null or does not match the pattern.
     */
	
    //HotelName Validation
	
    public static void validateHotelName(String hotelName) throws ValidationException {
    	
        // Validates the hotel name, ensuring it is not null and matches the specified pattern.
    	
        StringUtil.rejectIfNullOrEmpty(hotelName, "Hotel Name");
        if(!Pattern.matches(NAME_PATTERN, hotelName)) {
        	throw new ValidationException("Hotel Name doesn't match the pattern");
        }	
    }

    /**
     * Validates a room name, ensuring it is not null and matches a specified regular expression pattern.
     *
     * @param roomName The name of the room to be validated.
     * @throws ValidationException If the room name is null or does not match the pattern.
     */
    
    //RoomName Validation
    
    public static void validateRoomName(String roomName) throws ValidationException {
    	
        // Validates the room name, ensuring it is not null and matches the specified pattern.
    	
        StringUtil.rejectIfNullOrEmpty(roomName, "Room Name");
        if(!Pattern.matches(NAME_PATTERN, roomName)) {
        	throw new ValidationException("Room Name doesn't match the pattern");        
        }
    }

    /**
     * Validates the number of beds in a room, ensuring it is greater than zero.
     *
     * @param numberOfBeds The number of beds in the room to be validated.
     * @throws ValidationException If the number of beds is less than or equal to zero.
     */
    
    //NumberOfBeds Validation
    
    public static void validateNumberOfBeds(int numberOfBeds) throws ValidationException {
    	
        // Validates the number of beds, ensuring it is greater than zero.
    	
        if (numberOfBeds <= 0) {
            throw new ValidationException("Number of beds should be greater than zero");
        }
    }

    /**
     * Validates the price of a room, ensuring it is a positive integer greater than zero.
     *
     * @param price The price of the room to be validated.
     * @throws ValidationException If the price is less than or equal to zero.
     */
    
    //Price Validation
    
    public static void validatePrice(int price) throws ValidationException {
    	
        // Validates the price, ensuring it is a positive integer greater than zero.
    	
        if (price <= 0) {
            throw new ValidationException("Price should be a positive integer and should be greater than zero");
        }
    }
}

