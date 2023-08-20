package in.fssa.globalfuncity.interfaces;

import in.fssa.globalfuncity.exception.PersistenceException;

public interface TicketInterface<T> extends Base<T> {
	public abstract void create(int id, T object) throws PersistenceException;
}
