package in.fssa.globalfuncity.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

import com.google.protobuf.ServiceException;

import in.fssa.globalfuncity.dao.TicketDAO;
import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.util.StringUtil;

/**
 * This class contains a set of static methods for validating Ticket objects and related data.
 * It ensures that the data adheres to specific criteria and throws ValidationException when
 * validation fails. Additionally, it handles ticket ID validation and date formatting, providing
 * a comprehensive validation framework for tickets and their associated information.
 */

public class TicketValidator {
	
	/**
     * Validates a Ticket object to ensure it meets specific criteria.
     *
     * @param ticket The Ticket object to be validated.
     * @throws ValidationException If the Ticket object is not valid.
     * @throws PersistenceException If there's an issue related to persistence.
     */
    
    public static void validate(Ticket ticket) throws ValidationException, PersistenceException {
        if (ticket == null) {
            throw new ValidationException("Ticket object cannot be null");
        }
        
        validateDate(ticket.getFromDate());
        validateDate(ticket.getToDate());
        validateNoOfAdult(ticket.getNoOfAdult());
        validateNoOfChildren(ticket.getNoOfChildren());
    }
    
    /**
     * Validates a ticket ID to ensure it's a positive integer.
     *
     * @param ticketId The ticket ID to be validated.
     * @throws ValidationException If the ticket ID is not valid.
     */
    
	//TicketID Validation
	public static void validateId(int ticketId) throws ValidationException{
		if(ticketId <= 0) {
			throw new ValidationException("Id can't be less than or equal to zero");
		}
	}
    
    /**
     * Validates the number of adults to ensure it's greater than zero.
     *
     * @param noOfAdult The number of adults to be validated.
     * @throws ValidationException If the number of adults is not valid.
     */
	
	//No Of Adult Validation
    public static void validateNoOfAdult(int noOfAdult) throws ValidationException {
        if (noOfAdult <= 0) {
            throw new ValidationException("No of Adult should be greater than 0");
        }
    }
    
    /**
     * Validates the number of children to ensure it's a non-negative integer.
     *
     * @param noOfChildren The number of children to be validated.
     * @throws ValidationException If the number of children is not valid.
     */
    
    //No Of Children Validation
    public static void validateNoOfChildren(int noOfChildren) throws ValidationException {
        if (noOfChildren < 0) {
            throw new ValidationException("No of Children should be positive integer");
        }
    }
    
    /**
     * Validates a date string to ensure it's in the format "yyyy-MM-dd" and falls within a certain
     * range (not in the past and not more than 90 days in the future).
     *
     * @param date The date string to be validated.
     * @throws ValidationException If the date string is not valid.
     * @throws PersistenceException If there's an issue related to persistence.
     */
    
    //Date Validation
	
	
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