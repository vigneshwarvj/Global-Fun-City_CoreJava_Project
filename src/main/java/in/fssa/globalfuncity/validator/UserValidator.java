package in.fssa.globalfuncity.validator;

import java.util.regex.Pattern;

import in.fssa.globalfuncity.dao.UserDAO;
import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ServiceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.util.StringUtil;

/**
 * UserValidator class provides methods to validate various attributes of a User object.
 */

public class UserValidator {
	
	//Pattern for the Name, Email, Password.
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";
	private static final String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{8,}";
	
    /**
     * Validates a User object's attributes.
     *
     * @param user The User object to be validated.
     * @throws ValidationException If any validation rule is violated.
     */
	
	//Checking whether user object null or not.
	public static void Validate(User user) throws ValidationException {
		if (user == null) {
			throw new ValidationException("User object can not be null");
		}
		validateFirstName(user.getFirstName());
		validateLastName(user.getLastName());
		validateEmail(user.getEmail());
		validatePassword(user.getPassword());
		validatePhoneNumber(user.getPhoneNumber());
	}
	
    /**
     * Validates the ID of a User.
     * @param name 
     *
     * @param userId The ID of the User.
     * @throws ValidationException If the ID is less than or equal to zero.
     */
	
	//UserID Validation
	public static void validateId(String name, int userId) throws ValidationException{
		if(userId <= 0) {
			throw new ValidationException(name + " cannot be less than or equal to zero");
		}
	}

	/**
	 * Validates an ID to ensure it is not zero or negative.
	 *
	 * @param id The ID to validate.
	 * @throws ValidationException  If the ID is invalid or does not exist.
	 * @throws ServiceException
	 * @throws PersistenceException If an error occurs during data persistence
	 *                              checks.
	 */
	
	public static void validateUserId(int userId) throws ValidationException, ServiceException {
		validateId("User Id",userId);
		UserExists.checkIdExists(userId);
	}
	
    /**
     * Validates the first name of a User.
     *
     * @param firstName The first name of the User.
     * @throws ValidationException If the first name doesn't match the pattern.
     */
	
	//FirstName Validation
	public static void validateFirstName(String firstName) throws ValidationException {
		StringUtil.rejectIfInvalidString(firstName, "First Name");
		if (!Pattern.matches(NAME_PATTERN, firstName)) {
			throw new ValidationException("First Name doesn't match the pattern");
		}
	}

    /**
     * Validates the middle name of a User.
     *
     * @param middleName The middle name of the User.
     * @throws ValidationException If the middle name doesn't match the pattern.
     */
	
//	//MiddleName Validation
//	public static void validateMiddleName(String middleName) throws ValidationException {
//		StringUtil.rejectIfInvalidString(middleName, "Middle Name");
//		if (!Pattern.matches(NAME_PATTERN, middleName)) {
//			throw new ValidationException("Middle Name doesn't match the pattern");
//		}
//		
//	}

    /**
     * Validates the last name of a User.
     *
     * @param lastName The last name of the User.
     * @throws ValidationException If the last name doesn't match the pattern.
     */
	
	//LastName Validation
	public static void validateLastName(String lastName) throws ValidationException {
		StringUtil.rejectIfInvalidString(lastName, "Last Name");
		if (!Pattern.matches(NAME_PATTERN, lastName)) {
			throw new ValidationException("Last Name doesn't match the pattern");
		}
		
	}
	
    /**
     * Validates the phone number of a User.
     *
     * @param phoneNumber The phone number of the User.
     * @throws ValidationException If the phone number doesn't match the pattern.
     */
	
	//PhoneNumber Validation
	public static void validatePhoneNumber(long phoneNumber) throws ValidationException {
	    String phoneNumberStr = String.valueOf(phoneNumber);

	    if (phoneNumberStr.length() != 10) {
	        throw new ValidationException("Phone Number should be in 10 digits without country code.");
	    }

	    if (phoneNumber < 6000000000L || phoneNumber >= 10000000000L) {
	        throw new ValidationException("Phone Number should be in 10 digits without country code.");
	    }
	}
	
    /**
     * Validates the email of a User.
     *
     * @param email The email of the User.
     * @throws ValidationException If the email doesn't match the pattern.
     */
	
	//Email Validation
	public static void validateEmail(String email) throws ValidationException {
		StringUtil.rejectIfInvalidString(email, "Email");
		if (!Pattern.matches(EMAIL_PATTERN, email)) {
			throw new ValidationException("Email doesn't match the pattern");
		}
	}

    /**
     * Validates the password of a User.
     *
     * @param password The password of the User.
     * @throws ValidationException If the password doesn't match the pattern.
     */
	
	//Password Validation
	public static void validatePassword(String password) throws ValidationException {
		StringUtil.rejectIfInvalidString(password, "Password");
		 if (password.length() < 8) {
		        throw new ValidationException("Password should be at least 8 characters long");
		    }

		if (!Pattern.matches(PASSWORD_PATTERN, password)) {
			throw new ValidationException("Password must have at least 8 characters and contain at least one uppercase letter, one lowercase letter, and one special character.");
		}
	}
	
}
