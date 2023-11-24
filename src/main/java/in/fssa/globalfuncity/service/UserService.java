package in.fssa.globalfuncity.service;

import java.security.NoSuchAlgorithmException;
import java.util.Set;

import in.fssa.globalfuncity.dao.UserDAO;
import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.exception.ServiceException;
import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.User;
import in.fssa.globalfuncity.util.PasswordUtil;
import in.fssa.globalfuncity.validator.UserExists;
import in.fssa.globalfuncity.validator.UserValidator;

public class UserService {
	
	/**
	 * 
	 * @param newUser
	 * @throws ValidationException
	 * @throws PersistenceException
	 * @return
	 * @throws ServiceException 
	 */
	
	//Create User
	public void createUser(User newUser) throws ValidationException, PersistenceException, ServiceException{
		UserValidator.Validate(newUser);
		UserExists.emailExists(newUser.getEmail());
		UserDAO userDAO = new UserDAO();
		//
//		try {
//			newUser.setPassword(PasswordUtil.encryptPassword(newUser.getPassword()));
//		} catch (NoSuchAlgorithmException e) {
//			throw new ServiceException(e.getMessage());
//		}
		userDAO.create(newUser);
	}

	/**
	 * 
	 * @param id
	 * @param updatedUser
	 * @throws ValidationException
	 * @throws PersistenceException
	 * @return
	 * @throws ServiceException 
	 */
	
	//Update User
	
	public void updateUser(int id, User updatedUser) throws ValidationException, PersistenceException, ServiceException {
		
		try {
		
		UserValidator.validateUserId(id);
		UserExists.checkIdExists(id);
		
		if (updatedUser.getFirstName() != null) {
			UserValidator.validateFirstName(updatedUser.getFirstName());
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
		
		UserDAO userDAO = new UserDAO();
		userDAO.update(id, updatedUser);
		
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while updating user.", e);
		}
	}
	
	//Delete User
	
	public void deleteUser(int userId) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateUserId(userId);
	        UserDAO userDAO = new UserDAO();
	        userDAO.deleteUser(userId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while deleting user.", e);
	    }
	}
	
	//Find By Email Id 
	
	/**
	 * Retrieves a user by their email address.
	 *
	 * @param email The email address of the user to retrieve.
	 * @return The User object corresponding to the given email address.
	 * @throws ValidationException If the provided email address is invalid.
	 * @throws ServiceException   If a service-related error occurs during retrieval.
	 */
	public User findByEmail(String email) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateEmail(email);
	        UserDAO userDAO = new UserDAO();
	        return userDAO.findByEmail(email);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding user by their email.", e);
	    }
	}
	
	//Find by Id
	
	/**
	 * Retrieves a user by their ID.
	 *
	 * @param id The ID of the user to retrieve.
	 * @return The User object corresponding to the given ID.
	 * @throws ValidationException If the provided ID is invalid.
	 * @throws ServiceException   If a service-related error occurs during retrieval.
	 */
	public User findByUserId(int userId) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateUserId(userId);
	        UserDAO userDAO = new UserDAO();
	        return userDAO.findById(userId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding user by their id.", e);
	    }
	}
	
	//Find All Users
	
	/**
	 * Retrieves a set of all users from the database.
	 *
	 * @return A set containing all User objects in the database.
	 * @throws ServiceException If a service-related error occurs during retrieval.
	 */
	public Set<User> getAllUsers() throws ServiceException {
	    try {
	        UserDAO userDAO = new UserDAO();
	        return userDAO.findAllUsers();
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving users.", e);
	    }
	}
}
