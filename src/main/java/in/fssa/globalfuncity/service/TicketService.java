package in.fssa.globalfuncity.service;

import java.sql.SQLException;
import java.util.List;

import in.fssa.globalfuncity.dao.TicketDAO;
import in.fssa.globalfuncity.dao.UserDAO;
import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ServiceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.validator.TicketValidator;
import in.fssa.globalfuncity.validator.UserValidator;

/**
 * The TicketService class serves as a service layer for managing ticket-related operations.
 * It provides methods to book new tickets, retrieve ticket prices, and retrieve booking history by user ID.
 */

public class TicketService {
	
    /**
     * Books a new ticket for a user based on the provided ticket details.
     *
     * @param ticket  The Ticket object containing the details of the ticket to be booked.
     * @param userId  The ID of the user booking the ticket.
     * @throws ValidationException  If the provided ticket data is invalid.
     * @throws PersistenceException If there is an issue with database connectivity during ticket booking.
     * @throws SQLException        If there is an SQL-related error during the ticket booking process.
     */
	
	// Book Ticket 
	
	public int bookTicket(Ticket ticket,int userId)throws ValidationException, PersistenceException, SQLException {
		int trackId = -1;
		TicketValidator.validate(ticket);
		//TicketValidator.validateDateRange(ticket.getFromDate(), ticket.getToDate());
		
		try {
			UserValidator.validateUserId(userId);
			TicketDAO ticketDAO = new TicketDAO();
			int aprice = ticketDAO.getPrice("adult");
			int cprice = ticketDAO.getPrice("children");
			
			ticket.setAdultPrice(aprice);
			ticket.setChildrenPrice(cprice);
			
			trackId = ticketDAO.bookNewTicket(ticket,userId);
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
		};
		return trackId;
    }

	
    /**
     * Retrieves a list of all tickets booked by a specific user based on their user ID.
     *
     * @param userId The user ID for which the booking history is requested.
     * @return A list of Ticket objects representing the booking history of the user.
     * @throws ServiceException  If there is an issue with retrieving ticket data or processing the request.
     * @throws PersistenceException If there is an issue with database connectivity during data retrieval.
     */
	
    public List<Ticket> getAllBookedTicketsByUserId(int userId) throws ServiceException, PersistenceException {
        try {
           TicketDAO ticketDAO = new TicketDAO();
           return ticketDAO.getAllBookedHistoryByUserId(userId);
        } catch (PersistenceException e) {
        	throw new ServiceException("Error occurred while retrieving tickets.", e);
        }
    }
    
    //Find By Ticket Id
    public Ticket findByTicketId(int ticketId) throws ServiceException {
    	try {
    		TicketDAO ticketDAO = new TicketDAO();
    		return ticketDAO.findByTicketId(ticketId);
    	} catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding tickets by their ticketId.", e);
	    }
    }
 
    //Find NO Of Nights BY Ticket Id
    public Ticket findNoOfNightsByTicketId(int ticketId, int noOfNights) throws ServiceException {
    	try {
    		TicketDAO ticketDAO = new TicketDAO();
    		return ticketDAO.findNoOfNightsByTicketId(ticketId, noOfNights);
    	} catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding no of nights by their ticketId.", e);
	    }
    }
    
    //Find CheckInDate CheckOutDate By TicketId 
    public Ticket findByCheckInDateCheckOutDateByTicketId(int ticketId) throws ServiceException {
    	try {
    		TicketDAO ticketDAO = new TicketDAO();
    		return ticketDAO.findCheckInDateCheckOutDateByTicketId(ticketId);
    	} catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding check-in date and check-out date by their ticketId.", e);
	    }
    }
    
}
