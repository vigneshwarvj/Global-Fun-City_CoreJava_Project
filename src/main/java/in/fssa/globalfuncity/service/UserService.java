package in.fssa.globalfuncity.service;

import in.fssa.globalfuncity.dao.UserDAO;
import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.validator.UserExists;
import in.fssa.globalfuncity.validator.UserValidator;

public class UserService {

	public void create(User newUser) throws ValidationException, PersistenceException{
		UserValidator.Validate(newUser);
		UserExists.emailExists(newUser.getEmail());
		UserDAO userDao = new UserDAO();
		userDao.create(newUser);
	}

	public void update(int id, User updatedUser) throws ValidationException, PersistenceException {
		UserValidator.validateId(id);
		UserExists.checkIdExists(id);
		
		if (updatedUser.getFirstName() != null) {
			UserValidator.validateFirstName(updatedUser.getFirstName());
		}
		
		if (updatedUser.getMiddleName() != null) {
			UserValidator.validateMiddleName(updatedUser.getMiddleName());
		}
		
		if (updatedUser.getLastName() != null) {
			UserValidator.validateLastName(updatedUser.getLastName());
		}
		
		if (updatedUser.getPassword() != null) {
			UserValidator.validatePassword(updatedUser.getPassword());
		}
		if (updatedUser.getPhoneNumber() != 0) {
			UserValidator.validatePhoneNumber(updatedUser.getPhoneNumber());
		}
		
		UserDAO userDao = new UserDAO();
		userDao.update(id, updatedUser);
	}
	
}
