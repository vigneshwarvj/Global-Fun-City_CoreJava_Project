package in.fssa.globalfuncity.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.util.ConnectionUtil;

/**
 * The TicketDAO class provides methods for interacting with the database to retrieve and manipulate ticket-related data.
 * It handles operations such as retrieving ticket prices, booking new tickets, and retrieving booking history by user ID.
 */

public class TicketDAO {
	
    /**
     * Retrieves the price for a specific type of ticket from the ticketprices table in the database.
     *
     * @param type The type of ticket for which the price is requested.
     * @return The price of the specified ticket type.
     * @throws PersistenceException If there is an issue with database connectivity or if the input is invalid.
     */
	
	//Getting prices form the ticketprices table
	
	public int getPrice(String type) throws PersistenceException {
		
		//This method retrieves the price for a specific type of ticket from a database table named "ticketprices."
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int price = 0;
        try {
        	String query = "SELECT price FROM ticketprices WHERE type = ?";
            conn = ConnectionUtil.getConnection();
            ps= conn.prepareStatement(query);
            ps.setString(1, type);
            rs = ps.executeQuery();
            
            if(rs.next()) {
            	price = rs.getInt(1);
            }
            else {
            	throw new PersistenceException("Invalid input");
            }            
            
        }catch(SQLException e) {
        	e.printStackTrace();
        	throw new PersistenceException(e);
        }finally {
	        ConnectionUtil.close(conn, ps, rs);
	    }
        return price;
		
	}
	
    /**
     * Books a new ticket in the database with the provided ticket details and associates it with a user.
     *
     * @param ticket  The Ticket object containing ticket details.
     * @param userId  The user ID associated with the booking.
     * @return The generated ID of the newly booked ticket.
     * @throws PersistenceException If there is an issue with database connectivity or if the input is invalid.
     * @throws SQLException        If there is an SQL-related error during the booking process.
     */
	
	//Book a ticket
	
	 public int bookNewTicket(Ticket ticket,int userId) throws PersistenceException, SQLException {
		 
		 //This method is used to book a new ticket in the database with the provided ticket details and associates it with a user.
		 
	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        int generatedId = -1;
	        
	        try {
	            String query = "INSERT INTO tickets (from_date, to_date, no_of_adult, no_of_children, "
	                + "no_of_days, no_of_nights ,created_by, adult_price, children_price, total_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            
	            conn = ConnectionUtil.getConnection();
	            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	            ps.setString(1, ticket.getFromDate());
	            ps.setString(2,ticket.getToDate() );
	            ps.setInt(3, ticket.getNoOfAdult());
	            ps.setInt(4, ticket.getNoOfChildren());
	            
 
	            // Getting the no of days
	            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            LocalDate fromDate = LocalDate.parse(ticket.getFromDate(), dateFormatter);
	            LocalDate toDate = LocalDate.parse(ticket.getToDate(), dateFormatter);
	            
	            int numberOfDays = (int) ChronoUnit.DAYS.between(fromDate, toDate);

	            // Add 1 day to the result
	            numberOfDays++;
	            
	            if(numberOfDays == 0) {
	            	numberOfDays = 1;
	            }
	            
	            int totalAdult = (int) (ticket.getAdultPrice() * ticket.getNoOfAdult());
	            int totalChildren = (int) (ticket.getChildrenPrice() * ticket.getNoOfChildren());
	            
	            int totalForParticipants = (int) totalAdult + totalChildren;
	            
	            int totalPrice = (int) (numberOfDays * totalForParticipants);
	            
	            // Getting no of nights
	            int numberOfNights = (int) ChronoUnit.DAYS.between(fromDate, toDate);
	            
	            if(numberOfNights == 0) {
	            	numberOfNights = 1;
	            }
	         
	            ps.setInt(5, numberOfDays);
	            ps.setInt(6, numberOfNights);
	            ps.setInt(7, userId);
	            ps.setInt(8, ticket.getAdultPrice());
	            ps.setInt(9, ticket.getChildrenPrice());
	            ps.setInt(10, totalPrice);
	          
	            ps.executeUpdate();
	            rs = ps.getGeneratedKeys();
	            
		        if (rs.next()) {
		            generatedId = rs.getInt(1);
		        } else {
		            System.out.println("Ticket is successfully created");
		        }
	            
	            System.out.println("Ticket has been booked successfully");
	        
	        } catch (SQLException e) {
	        	
	            e.printStackTrace();
	            throw new PersistenceException(e.getMessage());
	            
	        } finally {
	        	
	            ConnectionUtil.close(conn, ps);
	        }
	        return generatedId;
	    }


   
	 
	    /**
	     * Retrieves a list of all tickets booked by a specific user based on their user ID.
	     *
	     * @param userId The user ID for which the booking history is requested.
	     * @return A list of Ticket objects representing the booking history of the user.
	     * @throws PersistenceException If there is an issue with database connectivity or data retrieval.
	     */
	 
	//GetAllBookingHistoryByUserId
	 
	    public static List<Ticket> getAllBookedHistoryByUserId(int userId) throws PersistenceException {
	    	
	    	//This method retrieves a list of all tickets booked by a specific user based on their user ID.
	    	
	        List<Ticket> tickets = new ArrayList<>();
	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            conn = ConnectionUtil.getConnection();
	            String query = "SELECT ticket_id, from_date, to_date, no_of_adult, no_of_children, no_of_days, created_by, adult_price, children_price, total_price, created_at FROM tickets WHERE created_by = ?";
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, userId);
	            rs = ps.executeQuery();

	            while (rs.next()) {
	                Ticket ticket = new Ticket();
	                ticket.setTicketId(rs.getInt("ticket_id"));
	                ticket.setFromDate(rs.getString("from_date"));
	                ticket.setToDate(rs.getString("to_date"));
	                ticket.setNoOfAdult(rs.getInt("no_of_adult"));
	                ticket.setNoOfChildren(rs.getInt("no_of_children"));
	                ticket.setNoOfDays(rs.getInt("no_of_days"));
	                ticket.setCreatedBy(rs.getInt("created_by"));
	                ticket.setAdultPrice(rs.getInt("adult_price"));
	                ticket.setChildrenPrice(rs.getInt("children_price"));
	                ticket.setTotalPrice(rs.getInt("total_price"));
	                ticket.setCreatedAt(rs.getTimestamp("created_at"));
	                
	                tickets.add(ticket);
	            }
	        } catch (SQLException e) {
	        	
	            throw new PersistenceException("Error retrieving booked tickets");
	            
	        } finally {
	            ConnectionUtil.close(conn, ps, rs);
	        }

	        return tickets;
	    }

		//Find by Ticket Id
	    public Ticket findByTicketId(int ticketId) throws PersistenceException {
	    	
	    	//This method retrieves a specific ticket by its ticket ID.
	    	
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Ticket ticket = null;
			
			try {
				String query = "SELECT ticket_id, from_date, to_date, no_of_adult, no_of_children, no_of_days, no_of_nights, created_by, adult_price, children_price, total_price FROM tickets WHERE ticket_id = ?";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, ticketId);
				rs = ps.executeQuery();
				if(rs.next()) {
					ticket = new Ticket();
					ticket.setTicketId(rs.getInt("ticket_id"));
					ticket.setFromDate(rs.getString("from_date"));
					ticket.setToDate(rs.getString("to_date"));
					ticket.setNoOfAdult(rs.getInt("no_of_adult"));
					ticket.setNoOfChildren(rs.getInt("no_of_children"));
					ticket.setNoOfDays(rs.getInt("no_of_days"));
					ticket.setNoOfNights(rs.getInt("no_of_nights"));
					ticket.setCreatedBy(rs.getInt("created_by"));
					ticket.setAdultPrice(rs.getInt("adult_price"));
					ticket.setChildrenPrice(rs.getInt("children_price"));
					ticket.setTotalPrice(rs.getInt("total_price"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new PersistenceException(e);
			} finally {
				ConnectionUtil.close(conn, ps, rs);
			}
			return ticket;
	    }
	    
 
	    //Find By No Of Nights by Ticket Id
	    public Ticket findNoOfNightsByTicketId(int ticketId, int noOfNights) throws PersistenceException {
	    	
	    	//This method retrieves a specific ticket by its ticket ID and the number of nights.
	    	
	    	Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Ticket ticket = null;
			
			try {
				String query = "SELECT ticket_id, from_date, to_date, no_of_adult, no_of_children, no_of_days, no_of_nights, created_by, adult_price, children_price, total_price FROM tickets WHERE ticket_id = ? AND no_of_nights = ? ";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, ticketId);
				ps.setInt(2, noOfNights);
				rs = ps.executeQuery();
				if(rs.next()) {
					ticket = new Ticket();
					ticket.setTicketId(rs.getInt("ticket_id"));
					ticket.setFromDate(rs.getString("from_date"));
					ticket.setToDate(rs.getString("to_date"));
					ticket.setNoOfAdult(rs.getInt("no_of_adult"));
					ticket.setNoOfChildren(rs.getInt("no_of_children"));
					ticket.setNoOfDays(rs.getInt("no_of_days"));
					ticket.setNoOfNights(rs.getInt("no_of_nights"));
					ticket.setCreatedBy(rs.getInt("created_by"));
					ticket.setAdultPrice(rs.getInt("adult_price"));
					ticket.setChildrenPrice(rs.getInt("children_price"));
					ticket.setTotalPrice(rs.getInt("total_price"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new PersistenceException(e);
			} finally {
				ConnectionUtil.close(conn, ps, rs);
			}
			return ticket;
	    }
	    
	    //Find StartDate and ToDate By TicketId
	    public Ticket findCheckInDateCheckOutDateByTicketId(int ticketId) throws PersistenceException {
	    	
	    	//This method retrieves the check-in and check-out dates of a specific ticket by its ticket ID.
	    	
	    	Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Ticket ticket = null;
			
			try {
				String query = "SELECT ticket_id, from_date, to_date FROM tickets WHERE ticket_id = ?;";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, ticketId);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					ticket = new Ticket();
					ticket.setTicketId(rs.getInt("ticket_id"));
					ticket.setFromDate(rs.getString("from_date"));
					ticket.setToDate(rs.getString("to_date"));
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new PersistenceException(e);
				
			} finally {
				ConnectionUtil.close(conn, ps, rs);
			}
			return ticket;
	    }
	    
  }