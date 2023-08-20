package in.fssa.globalfuncity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.model.Room;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.service.RoomService;
import in.fssa.globalfuncity.service.TicketService;

public class TestCreateTicket {

	//With Valid all input
	@Test
	public void testCreateTicketWithValidInput() {
		TicketService ticketService = new TicketService();
		Ticket ticket = new Ticket();
		ticket.setFromDate("2023-08-20");
		ticket.setToDate("2023-08-21");
		ticket.setNoOfAdult(1);
		ticket.setNoOfChildren(1);
		ticket.setCreatedBy(1);
        assertDoesNotThrow(() -> {
            ticketService.bookTicket(ticket);
        });	
	}
}
