package in.fssa.globalfuncity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.protobuf.ServiceException;

import in.fssa.globalfuncity.dao.TicketDAO;
import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.service.TicketService;

public class TestGetAllTicket {

	//To Getting the Booking History for the user 1. 
	@Test
	public void getBookedHistoryByUserId(){
		
		TicketService ticketService = new TicketService();
		int id = 1;
		
		assertDoesNotThrow(() -> {
			ticketService.getAllBookedTicketsByUserId(id);
		});
	}
	
	//To Getting the Booking History without Booking tickets.
	@Test
	public void getBookedHistoryWithoutBookingTickets() {
		TicketService ticketService = new TicketService();	
		int id = 2;
		
		Exception exception = assertThrows(Exception.class, () -> {
			ticketService.getAllBookedTicketsByUserId(id);
		});
		
		String exceptedMessage = "Error while checking user's bookings";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage, actualMessage);
		
	}
}
