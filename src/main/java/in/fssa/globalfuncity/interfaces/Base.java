package in.fssa.globalfuncity.interfaces;

import in.fssa.globalfuncity.exception.PersistenceException;

public interface Base<T> {
	
	public abstract void create(T object) throws PersistenceException;
	
}
