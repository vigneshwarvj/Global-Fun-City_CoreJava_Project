package in.fssa.globalfuncity.model;

public abstract class RoomEntity implements Comparable<RoomEntity> {

	private int roomId;
    private String hotelName;
    private String roomName;
    private int noOfBeds;
    private int price;
    private String roomImageUrl;
    private String roomAmenities;
    private boolean isActiveRoom = true;
    
    public String getRoomAmenities() {
		return roomAmenities;
	}
	public void setRoomAmenities(String roomAmenities) {
		this.roomAmenities = roomAmenities;
	}
    
    public String getRoomImageUrl() {
		return roomImageUrl;
	}
	public void setRoomImageUrl(String roomImageUrl) {
		this.roomImageUrl = roomImageUrl;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getNoOfBeds() {
		return noOfBeds;
	}
	public void setNoOfBeds(int noOfBeds) {
		this.noOfBeds = noOfBeds;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isActiveRoom() {
		return isActiveRoom;
	}
	public void setActiveRoom(boolean isActiveRoom) {
		this.isActiveRoom = isActiveRoom;
	}
	
	@Override
	public String toString() {
		return "RoomEntity [roomId=" + roomId + ", hotelName=" + hotelName + ", roomName=" + roomName + ", noOfBeds="
				+ noOfBeds + ", price=" + price + ", roomImageUrl=" + roomImageUrl + ", roomAmenities=" + roomAmenities
				+ ", isActiveRoom=" + isActiveRoom + "]";
	}
	
	@Override
	public int compareTo(RoomEntity o) {
		return 0;
	}
}
