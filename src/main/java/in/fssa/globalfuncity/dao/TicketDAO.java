package in.fssa.globalfuncity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.model.Ticket;
import in.fssa.globalfuncity.util.ConnectionUtil;
import in.fssa.globalfuncity.model.TicketPrices;

public class TicketDAO {

	/**
	 * 
	 * @param ticketPrices
	 * @throws SQLException 
	 */
	
	//Book a ticket
	
	 public void bookNewTicket(Ticket ticket) throws PersistenceException, SQLException {
	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet adultPriceResultSet = null;
	        ResultSet childrenPriceResultSet = null;
	        
	        try {
	            conn = ConnectionUtil.getConnection();
	            
	            // Fetch adult_price and children_price from ticketprices table
	            String fetchPricesQuery = "SELECT price FROM ticketprices WHERE type = ?";
	            ps = conn.prepareStatement(fetchPricesQuery);
	            
	            ps.setString(1, "adult");
	            adultPriceResultSet = ps.executeQuery();
	            int adultPrice = adultPriceResultSet.next() ? adultPriceResultSet.getInt("price") : 0;
	            
	            ps.setString(1, "children");
	            childrenPriceResultSet = ps.executeQuery();
	            int childrenPrice = childrenPriceResultSet.next() ? childrenPriceResultSet.getInt("price") : 0;
	            
	            // Calculate total_price based on no_of_adult and no_of_children
	            int totalAdultPrice = ticket.getNoOfAdult() * adultPrice;
	            int totalChildrenPrice = ticket.getNoOfChildren() * childrenPrice;
	            int totalTicketPrice = totalAdultPrice + totalChildrenPrice;
	            
	            // Insert ticket information into tickets table
	            String insertTicketQuery = "INSERT INTO tickets (from_date, to_date, no_of_adult, no_of_children, "
	                + "created_by, adult_price, children_price, total_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	            ps = conn.prepareStatement(insertTicketQuery);
	            
	            ps.setString(1, ticket.getFromDate());
	            ps.setString(2,ticket.getToDate() );
	            ps.setInt(3, ticket.getNoOfAdult());
	            ps.setInt(4, ticket.getNoOfChildren());
	            ps.setInt(5, ticket.getCreatedBy());
	            ps.setInt(6, totalAdultPrice);
	            ps.setInt(7, totalChildrenPrice);
	            ps.setInt(8, totalTicketPrice);
	            
	            ps.executeUpdate();
	            
	            System.out.println("Ticket has been booked successfully");
	        
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new PersistenceException(e);
	        } finally {
	            ConnectionUtil.close(conn, ps);
	            if(adultPriceResultSet !=null) {
	            	adultPriceResultSet.close();
	            }
	            if(childrenPriceResultSet != null) {
	            	childrenPriceResultSet.close();
	            }
	        }
	    }


	/**
	 * 
	 * @param userId
	 * @return
	 */
	
	public static List<Ticket> getBookingHistoryByUserId(int userId) {
		return null;
        // Implement the logic to fetch booking history from the database for the given user ID
    }
}
