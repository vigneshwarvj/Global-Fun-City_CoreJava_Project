package in.fssa.globalfuncity.exception;

import java.sql.SQLException;

public class PersistenceException extends Exception {

	public PersistenceException(SQLException e) {
		super(e);
	}

	public PersistenceException(String string) {
		// TODO Auto-generated constructor stub
	}
}