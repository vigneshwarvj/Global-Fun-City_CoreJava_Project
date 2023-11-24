package in.fssa.globalfuncity.model;

import java.sql.Timestamp;

public class UserRoomEntity {

	private int userRoomId;
	private int userId;
	private int ticketId;
	private int roomId;
	private String checkInDate;
	private String checkOutDate;
	private String roomName;
	private int noOfNights;
	private int totalPrice;
	private Timestamp createdAt;
	
	public int getUserRoomId() {
		return userRoomId;
	}


	public void setUserRoomId(int userRoomId) {
		this.userRoomId = userRoomId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getTicketId() {
		return ticketId;
	}


	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}


	public int getRoomId() {
		return roomId;
	}


	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	public String getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}


	public String getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getNoOfNights() {
		return noOfNights;
	}


	public void setNoOfNights(int noOfNights) {
		this.noOfNights = noOfNights;
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "UserRoomEntity [userRoomId=" + userRoomId + ", userId=" + userId + ", ticketId=" + ticketId
				+ ", roomId=" + roomId + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", roomName=" + roomName + ", noOfNights=" + noOfNights + ", totalPrice=" + totalPrice
				+ ", createdAt=" + createdAt + "]";
	}
	
}
