package in.fssa.globalfuncity;

import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.service.UserService;


public class TestGetAllUser {

	//UpdateUser
	@Test
	public void testUpdateUser() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
	    User newUser = new User();
		newUser.setFirstName("Vignesh");
		newUser.setMiddleName("VV");
		newUser.setLastName("Vijay");
		newUser.setEmail("vv@gmail.com");
		newUser.setPassword("Vignes!1");
		newUser.setPhoneNumber(9965283126L);
		userService.update(1, newUser);
	}
}
