package in.fssa.globalfuncity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.service.UserService;

public class TestCreateUser {
	
	//With Valid all input
	@Test
    public void testCreateUserWithValidInput() {
        UserService userService = new UserService();
        User newUser = new User();
        newUser.setFirstName("Sam");
        newUser.setMiddleName("Ganesh");
        newUser.setLastName("S");
        newUser.setEmail("samganesh@gmail.com");
        newUser.setPassword("Sam@2300");
        newUser.setPhoneNumber(9923456787L);

        assertDoesNotThrow(() -> {
            userService.create(newUser);
        });
    }

	//With Invalid input
	@Test
	public void testCreateUserWithInvalidInput() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(null);
		});
		String expectedMessage = "User object can not be null";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	//Firstname Null
	@Test
	public void testCreateUserWithFirstNameNull() {
		UserService userService = new UserService();
		User newUser = new User();
        newUser.setFirstName(null);
        newUser.setMiddleName("ganesh");
        newUser.setLastName("S");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "First Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	//FirstName Empty
	@Test
	public void testCreateUserWithFirstNameEmpty() {
		UserService userService = new UserService();
		User newUser = new User();
        newUser.setFirstName("");
        newUser.setMiddleName("ganesh");
        newUser.setLastName("S");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "First Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	//FirstName Pattern
	@Test
	public void testCreateUserWithFirstNamePattern() {
		UserService userService = new UserService();
		User newUser = new User();
        newUser.setFirstName("sam24");
        newUser.setMiddleName("ganesh");
        newUser.setLastName("S");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "First Name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	//Email Null
	@Test
	public void testCreateUserWithEmailNull() {
		UserService userService = new UserService();

		User newUser = new User();
        newUser.setFirstName("Sam");
        newUser.setMiddleName("ganesh");
        newUser.setLastName("S");
		newUser.setEmail(null);
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	//Email Null
	@Test
	public void testCreateUserWithEmailEmpty() {
		UserService userService = new UserService();

		User newUser = new User();
        newUser.setFirstName("Sam");
        newUser.setMiddleName("ganesh");
        newUser.setLastName("S");
		newUser.setEmail("");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//Email Pattern
	@Test
	public void testCreateUserWithEmailPattern() {
		UserService userService = new UserService();

		User newUser = new User();
        newUser.setFirstName("Sam");
        newUser.setMiddleName("ganesh");
        newUser.setLastName("S");
		newUser.setEmail("vv@");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Email doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//Email Already Exists
	@Test
	public void testCreateUserWithEmailExists() {
			UserService userService = new UserService();

			User newUser = new User();
	        newUser.setFirstName("Sam");
	        newUser.setMiddleName("ganesh");
	        newUser.setLastName("S");
			newUser.setEmail("vv@gmail.com");
			newUser.setPassword("Sam@2303");
			newUser.setPhoneNumber(9923456787L);

			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(newUser);
			});
			String expectedMessage = "Email already exists";
			String actualMessage = exception.getMessage();

			assertTrue(expectedMessage.equals(actualMessage));
		
	}

	//Password Null
	@Test
	public void testCreateUserWithPasswordNull() {
		UserService userService = new UserService();
		User newUser = new User();
        newUser.setFirstName("Sam");
        newUser.setMiddleName("ganesh");
        newUser.setLastName("S");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword(null);
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	//Password Empty
	@Test
	public void testCreateUserWithPasswordEmpty() {
		UserService userService = new UserService();
		User newUser = new User();
        newUser.setFirstName("Sam");
        newUser.setMiddleName("ganesh");
        newUser.setLastName("S");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	
	//Password Length
	@Test
	public void testCreateUserWithPasswordLength() {
		UserService userService = new UserService();
		User newUser = new User();
        newUser.setFirstName("Sam");
        newUser.setMiddleName("ganesh");
        newUser.setLastName("S");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Vv23");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Password doesn't match the length";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	//Password Pattern
	@Test
	public void testCreateUserWithPasswordPattern() {
		UserService userService = new UserService();
		User newUser = new User();
        newUser.setFirstName("Sam");
        newUser.setMiddleName("ganesh");
        newUser.setLastName("S");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Vviosf@23");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Password doesn't match the length";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	
	//Phone Number is 0
	@Test
	public void testCreateUserWithAllZeroPhoneNumber() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setFirstName("Sam");
		newUser.setMiddleName("ganesh");
		newUser.setLastName("S");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(0L);
	
		ValidationException exception = assertThrows(ValidationException.class, () -> {
		       userService.create(newUser);
		});
	
		String expectedMessage = "PhoneNumber doesn't match the length";
		String actualMessage = exception.getMessage();
	
		  assertTrue(expectedMessage.equals(actualMessage));
	}
	
    //InValid Phone Number
	@Test
    public void testCreateUserWithInvalidPhoneNumber() {
        UserService userService = new UserService();
        User newUser = new User();
        newUser.setFirstName("Sam");
        newUser.setMiddleName("ganesh");
        newUser.setLastName("S");
        newUser.setEmail("sam@gmail.com");
        newUser.setPassword("Sam@2303");
        newUser.setPhoneNumber(3895673456L);

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            userService.create(newUser);
        });

        String expectedMessage = "PhoneNumber doesn't match the pattern";
        String actualMessage = exception.getMessage();

        assertTrue(expectedMessage.equals(actualMessage));
    }
}
