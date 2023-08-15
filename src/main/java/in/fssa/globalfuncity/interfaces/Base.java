package in.fssa.globalfuncity.interfaces;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.model.User;

public interface Base<T> {
	
	public abstract void create(T object) throws PersistenceException;
	
}
