package in.fssa.globalfuncity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.service.UserService;

public class TestReadUser {

	//Find By Email Id
	 @Test
	 @Order(1)
	    void testGetUserByEmailId() {
	        assertDoesNotThrow(() -> {
	            UserService userService = new UserService();
	            User arr = userService.findByEmail("sam1234@gmail.com");
	            System.out.println(arr);
	        });
	    }
	
	 //Find By Id
	    @Test
	    @Order(2)
	    void testGetUserById() {
	        assertDoesNotThrow(() -> {
	            UserService userService = new UserService();
	            User arr = userService.findByUserId(1);
	            System.out.println(arr);
	        });
	    }
	    
	 //Find All Users
	    @Test
	    @Order(3)
	    void testGetAllUsers() {
	    	assertDoesNotThrow(() -> {
	            UserService userService = new UserService();
	            Set<User> arr = userService.getAllUsers();
	           
	            for(User user: arr) {
	            System.out.println(user);
	            }
	    	});
	    }
}
