package in.fssa.globalfuncity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.service.TicketService;


public class TestCreateTicket {

	//With Valid all input
	@Test
	public void testCreateTicketWithValidInput() {
		TicketService ticketService = new TicketService();
		Ticket ticket = new Ticket();
		ticket.setFromDate("2023-08-24");
		ticket.setToDate("2023-08-25");
		ticket.setNoOfAdult(1);
		ticket.setNoOfChildren(1);
		ticket.setCreatedBy(1);
        assertDoesNotThrow(() -> {
            ticketService.bookTicket(ticket);
        });	
	}
	
	//With Invalid input
	@Test
	public void testCreateTicketWithInvalidInput() {
		TicketService ticketService = new TicketService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			ticketService.bookTicket(null);
		});
		String expectedMessage = "Ticket object cannot be null";
		String actualMessage = exception.getMessage();
		
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//With FromDate Null
	@Test
	public void testCreateTicketWithFromDateNull() {
	    TicketService ticketService = new TicketService();
	    Ticket ticket = new Ticket();
	    ticket.setFromDate(null);
	    ticket.setToDate("2023-08-25");
	    ticket.setNoOfAdult(1);
	    ticket.setNoOfChildren(1);
	    ticket.setCreatedBy(1);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        ticketService.bookTicket(ticket);
	    });
	    
	    String expectedMessage = "Date cannot be null or empty";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//With FromDate Empty
	@Test
	public void testCreateTicketWithFromDateEmpty() {
	    TicketService ticketService = new TicketService();
	    Ticket ticket = new Ticket();
	    ticket.setFromDate("");
	    ticket.setToDate("2023-08-25");
	    ticket.setNoOfAdult(1);
	    ticket.setNoOfChildren(1);
	    ticket.setCreatedBy(1);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        ticketService.bookTicket(ticket);
	    });
	    
	    String expectedMessage = "Date cannot be null or empty";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//With FromDate Pattern
	@Test
	public void testCreateTicketWithFromDatePattern() {
	    TicketService ticketService = new TicketService();
	    Ticket ticket = new Ticket();
	    ticket.setFromDate("2023/08/24");
	    ticket.setToDate("2023-08-25");
	    ticket.setNoOfAdult(1);
	    ticket.setNoOfChildren(1);
	    ticket.setCreatedBy(1);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        ticketService.bookTicket(ticket);
	    });
	    
	    String expectedMessage = "Invalid date or Invalid date format (yyyy-MM-dd)";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//With FromDate Past
	@Test
	public void testCreateTicketWithFromDatePast() {
	    TicketService ticketService = new TicketService();
	    Ticket ticket = new Ticket();
	    ticket.setFromDate("2023-08-04");
	    ticket.setToDate("2023-08-25");
	    ticket.setNoOfAdult(1);
	    ticket.setNoOfChildren(1);
	    ticket.setCreatedBy(1);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        ticketService.bookTicket(ticket);
	    });
	    
	    String expectedMessage = "Invalid date or Invalid date format (yyyy-MM-dd)";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}

	//With ToDate Null
	@Test
	public void testCreateTicketWithToDateNull() {
	    TicketService ticketService = new TicketService();
	    Ticket ticket = new Ticket();
	    ticket.setFromDate("2023-08-25");
	    ticket.setToDate(null);
	    ticket.setNoOfAdult(1);
	    ticket.setNoOfChildren(1);
	    ticket.setCreatedBy(1);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        ticketService.bookTicket(ticket);
	    });
	    
	    String expectedMessage = "Date cannot be null or empty";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//With ToDate Empty
	@Test
	public void testCreateTicketWithToDateEmpty() {
	    TicketService ticketService = new TicketService();
	    Ticket ticket = new Ticket();
	    ticket.setFromDate("2023-08-25");
	    ticket.setToDate("");
	    ticket.setNoOfAdult(1);
	    ticket.setNoOfChildren(1);
	    ticket.setCreatedBy(1);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        ticketService.bookTicket(ticket);
	    });
	    
	    String expectedMessage = "Date cannot be null or empty";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//With FromDate Pattern
	@Test
	public void testCreateTicketWithToDatePattern() {
	    TicketService ticketService = new TicketService();
	    Ticket ticket = new Ticket();
	    ticket.setFromDate("2023-08-26");
	    ticket.setToDate("2023/08/24");
	    ticket.setNoOfAdult(1);
	    ticket.setNoOfChildren(1);
	    ticket.setCreatedBy(1);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        ticketService.bookTicket(ticket);
	    });
	    
	    String expectedMessage = "Invalid date or Invalid date format (yyyy-MM-dd)";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//With FromDate Past
	@Test
	public void testCreateTicketWithToDateFuture() {
	    TicketService ticketService = new TicketService();
	    Ticket ticket = new Ticket();
	    ticket.setFromDate("2023-08-04");
	    ticket.setToDate("2023-11-25");
	    ticket.setNoOfAdult(1);
	    ticket.setNoOfChildren(1);
	    ticket.setCreatedBy(1);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        ticketService.bookTicket(ticket);
	    });
	    
	    String expectedMessage = "Invalid date or Invalid date format (yyyy-MM-dd)";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//With No of Adults as 0
	@Test
	public void testCreateTicketWithNoOfAdultAsZero() {
	    TicketService ticketService = new TicketService();
	    Ticket ticket = new Ticket();
	    ticket.setFromDate("2023-08-24");
	    ticket.setToDate("2023-08-25");
	    ticket.setNoOfAdult(0);
	    ticket.setNoOfChildren(1);
	    ticket.setCreatedBy(1);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        ticketService.bookTicket(ticket);
	    });
	    
	    String expectedMessage = "No of Adult should be greater than 0";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//With No of Adults as Negative
	@Test
	public void testCreateTicketWithNoOfAdultAsNegativeInteger() {
	    TicketService ticketService = new TicketService();
	    Ticket ticket = new Ticket();
	    ticket.setFromDate("2023-08-24");
	    ticket.setToDate("2023-08-25");
	    ticket.setNoOfAdult(-1);
	    ticket.setNoOfChildren(1);
	    ticket.setCreatedBy(1);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        ticketService.bookTicket(ticket);
	    });
	    
	    String expectedMessage = "No of Adult should be greater than 0";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//With No Of Children as Negative Integer
	@Test
	public void testCreateTicketWithNoOfChildernAsNegativeInteger() {
	    TicketService ticketService = new TicketService();
	    Ticket ticket = new Ticket();
	    ticket.setFromDate("2023-08-23");
	    ticket.setToDate("2023-08-24");
	    ticket.setNoOfAdult(1);
	    ticket.setNoOfChildren(-1);
	    ticket.setCreatedBy(1);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        ticketService.bookTicket(ticket);
	    });
	    
	    String expectedMessage = "No of Children should be positive integer";
	    String actualMessage = exception.getMessage();

	    assertEquals(expectedMessage,actualMessage);
	}
}
