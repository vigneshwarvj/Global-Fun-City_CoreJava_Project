package in.fssa.globalfuncity.service;

import java.sql.SQLException;
import java.util.List;

import in.fssa.globalfuncity.dao.TicketDAO;
import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.validator.TicketValidator;

public class TicketService {
/**
 * 
 * @param ticket
 * @return
 * @throws ValidationException
 * @throws PersistenceException
 * @throws SQLException 
 */
	
	// Book Ticket 
	
	public Ticket bookTicket(Ticket ticket)throws ValidationException, PersistenceException, SQLException {
		TicketValidator.validate(ticket);
		TicketDAO ticketDao = new TicketDAO();
		ticketDao.bookNewTicket(ticket);
		return ticket;
    }

	
/**
 * 
 * @param userId
 * @return
 */
	public List<Ticket> getBookingHistoryByUserId(int userId) {
        return TicketDAO.getBookingHistoryByUserId(userId);
    }


public void create(Ticket ticket) {
	// TODO Auto-generated method stub
	
}
	
}
