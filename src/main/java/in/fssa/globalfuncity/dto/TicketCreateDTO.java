package in.fssa.globalfuncity.dto;

import in.fssa.globalfuncity.model.TicketPrices;

public class TicketCreateDTO {

	private int ticketId;
    private String fromDate;
    private String toDate;
    private int noOfAdult;
    private int noOfChildren;
    private int createdBy;
    private TicketPrices adultPrice;
    private TicketPrices childrenPrice;
	
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
	public TicketPrices getAdultPrice() {
		return adultPrice;
	}
	public void setAdultPrice(TicketPrices adultPrice) {
		this.adultPrice = adultPrice;
	}
	public TicketPrices getChildrenPrice() {
		return childrenPrice;
	}
	public void setChildrenPrice(TicketPrices childrenPrice) {
		this.childrenPrice = childrenPrice;
	}
	@Override
	public String toString() {
		return "TicketCreateDTO [ticketId=" + ticketId + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", noOfAdult=" + noOfAdult + ", noOfChildren=" + noOfChildren + ", createdBy=" + createdBy + "]";
	}
}
