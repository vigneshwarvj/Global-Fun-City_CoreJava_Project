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

public class TicketValidator {
	
    /**
     * 
     * @param ticket
     * @throws ValidationException
     */
    
    public static void validate(Ticket ticket) throws ValidationException {
        if (ticket == null) {
            throw new ValidationException("Ticket object cannot be null");
        }
        
        validateDate(ticket.getFromDate());
        validateDate(ticket.getToDate());
        validateNoOfAdult(ticket.getNoOfAdult());
        validateNoOfChildren(ticket.getNoOfChildren());
    }
    
    /**
     * 
     * @param ticketId
     * @throws ValidationException
     */
    
	//TicketID Validation
	public static void validateId(int ticketId) throws ValidationException{
		if(ticketId <= 0) {
			throw new ValidationException("Id can't be less than or equal to zero");
		}
	}
    
	/**
	 * 
	 * @param value
	 * @param fieldName
	 * @throws ValidationException
	 */
	
	//No Of Adult Validation
    public static void validateNoOfAdult(int noOfAdult) throws ValidationException {
        if (noOfAdult <= 0) {
            throw new ValidationException("No of Adult should be greater than 0");
        }
    }
    
    //No Of Children Validation
    public static void validateNoOfChildren(int noOfChildren) throws ValidationException {
        if (noOfChildren < 0) {
            throw new ValidationException("No of Children should be positive integer");
        }
    }
    
    /**
     * 
     * @param date
     * @throws ValidationException
     */
    
    //Date Validation
    public static void validateDate(String date) throws ValidationException {
	    StringUtil.rejectIfInvalidString(date, "Date");
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate dueDate;

	    if (date.trim().isEmpty()) {
	        throw new ValidationException("Date cannot be empty");
	    }
	    
	    try {
	        dueDate = LocalDate.parse(date, inputFormatter);
	    } catch (DateTimeParseException e) {
	        throw new ValidationException("Invalid date or Invalid date format (yyyy-MM-dd)");
	    }

	    String formattedDate = dueDate.format(inputFormatter);
	    System.out.println("Formatted Date: " + formattedDate);
	    LocalDate currentDate = LocalDate.now();

	    if (dueDate.isBefore(currentDate) || dueDate.equals(currentDate) || dueDate.isAfter(currentDate.plusDays(90))) {
	        throw new ValidationException("Invalid date or Invalid date format (yyyy-MM-dd)");
	    }
    }
    
    
    //Booking History Validation
//    public boolean hasBookedTicket(int userId) {
//        List<Ticket> bookingHistory = null;
//		try {
//			bookingHistory = TicketDAO.getBookingHistoryByUserId(userId);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        return !bookingHistory.isEmpty();
//    }
    
    
    public boolean hasBookings(int userId) throws ServiceException {
    	
            List<Ticket> bookedTickets = TicketDAO.getAllBookedHistoryByUserId(userId);
            return !bookedTickets.isEmpty();

            TicketDAO ticketDao = new TicketDAO();
            ticketDao.getAllBookedHistoryByUserId(userId);
    }
}



