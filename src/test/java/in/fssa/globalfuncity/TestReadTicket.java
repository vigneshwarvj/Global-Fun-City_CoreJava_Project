package in.fssa.globalfuncity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.google.protobuf.ServiceException;

import in.fssa.globalfuncity.dao.TicketDAO;
import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.service.TicketService;
import in.fssa.globalfuncity.service.UserService;

public class TestReadTicket {

	//Getting the Booking History for the user 1. 
	@Test
	public void getBookedHistoryByUserId(){
		
		TicketService ticketService = new TicketService();
		int id = 1;
		
		assertDoesNotThrow(() -> {
			ticketService.getAllBookedTicketsByUserId(id);
		});
	}
	
	//To Getting the Booking History without Booking tickets.
//	@Test
//	public void getBookedHistoryWithoutBookingTickets() {
//		TicketService ticketService = new TicketService();	
//		
//		
//		Exception exception = assertThrows(Exception.class, () -> {
//			ticketService.getAllBookedTicketsByUserId(2);
//		});
//		
//		String exceptedMessage = "Error while checking user's bookings";
//		String actualMessage = exception.getMessage();
//		
//		assertEquals(exceptedMessage, actualMessage);
//		
//	}
	
	
	@Test 
	   void testGetTicketByTicketId() {
		assertDoesNotThrow(() -> {
			TicketService ticketService = new TicketService();
			Ticket arr = ticketService.findByTicketId(1);
			System.out.print(arr);
		});
	}
	
	@Test
		void testGetNoOfNightsByTicketId() {
		assertDoesNotThrow(() -> {
			TicketService ticketService = new TicketService();
			Ticket arr = ticketService.findNoOfNightsByTicketId(1, 3);
			System.out.print(arr);
		});
	}
	
	//Find CheckInDate CheckOutDate By TicketId
	@Test 
	   void testGetCheckInDateCheckOutDateByTicketId() {
		assertDoesNotThrow(() -> {
			TicketService ticketService = new TicketService();
			Ticket arr = ticketService.findByCheckInDateCheckOutDateByTicketId(1);
			System.out.print(arr);
		});
	}
	
}
