package in.fssa.globalfuncity;

import in.fssa.globalfuncity.exception.ServiceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.service.UserService;

public class AppTest {

	public static void main(String[] args) {
		
		UserService u = new UserService();
		
		try {
			System.out.println(u.findByEmail("lskdfjl@gmail.com"));
			
		} catch (ValidationException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
