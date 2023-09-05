package in.fssa.globalfuncity;

import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ServiceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.service.UserService;


public class TestUpdateUser {

	//UpdateUser
	@Test
	public void testUpdateUser() throws ValidationException, PersistenceException, ServiceException {
		UserService userService = new UserService();
	    User newUser = new User();
		newUser.setFirstName("Vignesh");
		newUser.setMiddleName("VV");
		newUser.setLastName("Vijay");
		newUser.setPassword("Vignes@12");
		newUser.setPhoneNumber(9965283126L);
		userService.updateUser(1, newUser);
	}
}
