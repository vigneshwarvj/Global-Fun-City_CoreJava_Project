package in.fssa.globalfuncity.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.model.UserRoom;
import in.fssa.globalfuncity.util.StringUtil;

public class UserRoomDateValidator {

	public static void validate(UserRoom userRoom) throws ValidationException, PersistenceException {
        if (userRoom == null) {
            throw new ValidationException("UserRoom object cannot be null");
        }
        
        validateDate(userRoom.getCheckInDate());
        validateDate(userRoom.getCheckOutDate());
    }
	
	
    //Validating the Date
  public static void validateDate(String date) throws ValidationException, PersistenceException { 
		  
  	StringUtil.rejectIfInvalidString(date, "Date");
  	  
  	  DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  	  LocalDate dueDate; 
  	  try { 
  		  dueDate = LocalDate.parse(date, inputFormatter);
  		  }
  	  catch (DateTimeParseException e) { 
  		  throw new ValidationException("Invalid date or Invalid date format (yyyy-MM-dd)"); 
  		  }
  	  
  	  String formattedDate = dueDate.format(inputFormatter); LocalDate currentDate = LocalDate.now();
  	  
  	  if (dueDate.isBefore(currentDate) || dueDate.equals(currentDate) || dueDate.isAfter(currentDate.plusDays(90))) { 
  		  throw new ValidationException("Invalid date or Invalid date format (yyyy-MM-dd)"); 
  		  }
  	  }
	
}
