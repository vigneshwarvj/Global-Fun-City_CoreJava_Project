package in.fssa.globalfuncity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.util.ConnectionUtil;

public class TicketDAO {
	
	//Getting prices form the ticketprices table
	
	public int getPrice(String type) throws PersistenceException {
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
        	throw new PersistenceException(e.getMessage());
        }finally {
	        ConnectionUtil.close(conn, ps, rs);
	    }
        return price;
		
	}
	
	
	//Book a ticket
	 public void bookNewTicket(Ticket ticket) throws PersistenceException, SQLException {
	        Connection conn = null;
	        PreparedStatement ps = null;
	        
	        try {
	            String query = "INSERT INTO tickets (from_date, to_date, no_of_adult, no_of_children, "
	                + "created_by, adult_price, children_price, total_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	            
	            conn = ConnectionUtil.getConnection();
	            ps = conn.prepareStatement(query);
	            
	            ps.setString(1, ticket.getFromDate());
	            ps.setString(2,ticket.getToDate() );
	            ps.setInt(3, ticket.getNoOfAdult());
	            ps.setInt(4, ticket.getNoOfChildren());
	            ps.setInt(5, ticket.getCreatedBy());
	            ps.setInt(6, ticket.getAdultPrice());
	            ps.setInt(7, ticket.getChildrenPrice());
	            int totalPrice = (ticket.getAdultPrice() * ticket.getNoOfAdult()) + (ticket.getChildrenPrice() * ticket.getNoOfChildren()); 
	            ps.setInt(8, totalPrice);
	            ps.executeUpdate();
	            
	            System.out.println("Ticket has been booked successfully");
	        
	        } catch (SQLException e) {
	        	
	            e.printStackTrace();
	            throw new PersistenceException(e.getMessage());
	            
	        } finally {
	        	
	            ConnectionUtil.close(conn, ps);
	        }
	    }


	/**
	 * 
	 * @param userId
	 * @return
	 */
	 
	// GetAllBookingHistoryByUserId
	    public static List<Ticket> getAllBookedHistoryByUserId(int userId) throws PersistenceException {
	    	
	        List<Ticket> tickets = new ArrayList<>();
	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            conn = ConnectionUtil.getConnection();
	            String query = "SELECT ticket_id, from_date, to_date, no_of_adult, no_of_children, created_by, adult_price, children_price, total_price FROM tickets WHERE created_by = ?";
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
	                ticket.setCreatedBy(rs.getInt("created_by"));
	                ticket.setAdultPrice(rs.getInt("adult_price"));
	                ticket.setChildrenPrice(rs.getInt("children_price"));
	                ticket.setTotalPrice(rs.getInt("total_price"));

	                tickets.add(ticket);
	            }
	        } catch (SQLException e) {
	        	
	            throw new PersistenceException("Error retrieving booked tickets");
	            
	        } finally {
	            ConnectionUtil.close(conn, ps, rs);
	        }

	        return tickets;
	    }
  }
