package in.fssa.globalfuncity.model;

import in.fssa.globalfuncity.model.UserEntity;

public abstract class UserEntity implements Comparable<UserEntity> {

	private int userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String password;
	private long phoneNumber;
	private boolean isActive = true;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", isActive=" + isActive + "]";
	}
	
	 @Override
	    public int compareTo(UserEntity other) {
			return Integer.compare(this.userId, other.getUserId());
	    }
	
	
}
