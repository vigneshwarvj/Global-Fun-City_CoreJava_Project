package in.fssa.globalfuncity.validator;

import java.util.regex.Pattern;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.util.StringUtil;


public class UserValidator {
	
	//Pattern for the Name, Email, Password.
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";
	private static final String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
	
	/**
	 * 
	 * @param user
	 * @throws ValidationException
	 */
	
	//Checking whether user object null or not.
	public static void Validate(User user) throws ValidationException {
		if (user == null) {
			throw new ValidationException("User object can not be null");
		}
		validateFirstName(user.getFirstName());
		validateMiddleName(user.getMiddleName());
		validateLastName(user.getLastName());
		validateEmail(user.getEmail());
		validatePassword(user.getPassword());
		validatePhoneNumber(user.getPhoneNumber());
	}
	
	/**
	 * 
	 * @param userId
	 * @throws ValidationException
	 */
	
	//UserID Validation
	public static void validateId(int userId) throws ValidationException{
		if(userId <= 0) {
			throw new ValidationException("Id can't be less than or equal to zero");
		}
	}

	/**
	 * 
	 * @param firstName
	 * @throws ValidationException
	 */
	
	//FirstName Validation
	public static void validateFirstName(String firstName) throws ValidationException {
		StringUtil.rejectIfInvalidString(firstName, "First Name");
		if (!Pattern.matches(NAME_PATTERN, firstName)) {
			throw new ValidationException("First Name doesn't match the pattern");
		}
	}

	/**
	 * 
	 * @param middleName
	 * @throws ValidationException
	 */
	
	//MiddleName Validation
	public static void validateMiddleName(String middleName) throws ValidationException {
		StringUtil.rejectIfInvalidString(middleName, "Middle Name");
		if (!Pattern.matches(NAME_PATTERN, middleName)) {
			throw new ValidationException("Middle Name doesn't match the pattern");
		}
		
	}

	/**
	 * 
	 * @param lastName
	 * @throws ValidationException
	 */
	
	//LastName Validation
	public static void validateLastName(String lastName) throws ValidationException {
		StringUtil.rejectIfInvalidString(lastName, "Last Name");
		if (!Pattern.matches(NAME_PATTERN, lastName)) {
			throw new ValidationException("Last Name doesn't match the pattern");
		}
		
	}
	
	/**
	 * 
	 * @param email
	 * @throws ValidationException
	 */
	
	//Email Validation
	public static void validateEmail(String email) throws ValidationException {
		StringUtil.rejectIfInvalidString(email, "Email");
		if (!Pattern.matches(EMAIL_PATTERN, email)) {
			throw new ValidationException("Email doesn't match the pattern");
		}
	}

	/**
	 * 
	 * @param password
	 * @throws ValidationException
	 */
	
	//Password Validation
	public static void validatePassword(String password) throws ValidationException {
		StringUtil.rejectIfInvalidString(password, "Password");
		 if (password.length() != 8) {
		        throw new ValidationException("Password doesn't match the length");
		    }

		if (!Pattern.matches(PASSWORD_PATTERN, password)) {
			throw new ValidationException("Password doesn't match the pattern");
		}
	}

	/**
	 * 
	 * @param phoneNumber
	 * @throws ValidationException
	 */
	
	//PhoneNumber Validation
	public static void validatePhoneNumber(long phoneNumber) throws ValidationException {
	    String phoneNumberStr = String.valueOf(phoneNumber);

	    if (phoneNumberStr.length() != 10) {
	        throw new ValidationException("PhoneNumber doesn't match the length");
	    }

	    if (phoneNumber < 6000000000L || phoneNumber >= 10000000000L) {
	        throw new ValidationException("PhoneNumber doesn't match the pattern");
	    }
	}
	
}
