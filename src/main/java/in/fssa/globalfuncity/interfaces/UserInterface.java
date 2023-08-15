package in.fssa.globalfuncity.interfaces;

import in.fssa.globalfuncity.exception.PersistenceException;

public interface UserInterface<T> extends Base<T>{
	public abstract void update(int id, T object) throws PersistenceException;
}
