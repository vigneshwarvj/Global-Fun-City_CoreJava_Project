package in.fssa.globalfuncity.model;

public class TicketPrices {

	private int ticketPriceId;
	private String type;
	private int price;
	private boolean isActive = true;
	
	public int getTicketPriceId() {
		return ticketPriceId;
	}
	public void setTicketPriceId(int ticketPriceId) {
		this.ticketPriceId = ticketPriceId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "TicketPrices [ticketPriceId=" + ticketPriceId + ", type=" + type + ", price=" + price + ", isActive="
				+ isActive + "]";
	}

}
