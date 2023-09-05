package in.fssa.globalfuncity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import in.fssa.globalfuncity.dao.UserDAO;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.service.UserService;

@TestMethodOrder(OrderAnnotation.class)

public class TestDeleteUser {
	
		@Test
		@Order(1)
		void testGetUserByIdLessThanZero() {
			UserService userService = new UserService();
	        UserDAO app = new UserDAO();
			Exception exception = assertThrows(ValidationException.class, () -> {
		            userService.deleteUser(-5);
			});
			String expectedMessage = "User Id cannot be less than or equal to zero";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage, actualMessage);
		}

		 @Test
		    @Order(2)
		    void testDeleteUser() {
		        assertDoesNotThrow(() -> {
		            UserService userService = new UserService();
		            UserDAO userDAO = new UserDAO();
		    	    int user = userDAO.getLastUpdatedUserId();
		            userService.deleteUser(user);
		        });
		    }


		@Test
		@Order(4)
		void testDeleteWithNonExistingId() throws ValidationException {
			UserService userService = new UserService();
			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.deleteUser(5000);
			});
			String expectedMessage = "User Id doesn't exist";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage, actualMessage);
		}
}
