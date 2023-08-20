package in.fssa.globalfuncity.util;

import in.fssa.globalfuncity.exception.ValidationException;

public class StringUtil {
	
	/**
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */
	
	 public static void rejectIfInvalidString(String input, String inputName) throws ValidationException{
    	 if(input == null || "".equals(input.trim())) {
    		 throw new ValidationException(inputName.concat(" cannot be null or empty"));
    	 }
     }
	 
	 /**
	  * 
	  * @param hotelName
	  * @param string
	  * @throws ValidationException
	  */
	 
    //Rooms
	public static void rejectIfNullOrEmpty(String hotelName, String string) throws ValidationException {
   	 if(hotelName == null || "".equals(hotelName.trim())) {
		 throw new ValidationException(string.concat(" cannot be null or empty"));
	 }
		
	}
}