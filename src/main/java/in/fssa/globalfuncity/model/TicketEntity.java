package in.fssa.globalfuncity.model;

import java.time.LocalDateTime;

public class TicketEntity {
	
	private int ticketId;
    private String fromDate;
    private String toDate;
    private int noOfAdult;
    private int noOfChildren;
    private int createdBy;
    private int adultPrice;
    private int childrenPrice;
    private int totalPrice;

    public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public int getNoOfAdult() {
		return noOfAdult;
	}
	public void setNoOfAdult(int noOfAdult) {
		this.noOfAdult = noOfAdult;
	}
	public int getNoOfChildren() {
		return noOfChildren;
	}
	public void setNoOfChildren(int noOfChildren) {
		this.noOfChildren = noOfChildren;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getAdultPrice() {
		return adultPrice;
	}
	public void setAdultPrice(int adultPrice) {
		this.adultPrice = adultPrice;
	}
	public int getChildrenPrice() {
		return childrenPrice;
	}
	public void setChildrenPrice(int childrenPrice) {
		this.childrenPrice = childrenPrice;
	}

	
	@Override
	public String toString() {
		return "TicketEntity [ticketId=" + ticketId + ", fromDate=" + fromDate + ", toDate=" + toDate + ", noOfAdult="
				+ noOfAdult + ", noOfChildren=" + noOfChildren + ", createdBy=" + createdBy + ", adultPrice="
				+ adultPrice + ", childrenPrice=" + childrenPrice + ", totalPrice=" + totalPrice + "]";
	}

	 public int compareTo(TicketEntity other) {
			return Integer.compare(this.ticketId, other.getTicketId());
	    }
	
}
