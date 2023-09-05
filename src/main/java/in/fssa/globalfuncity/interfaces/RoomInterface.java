package in.fssa.globalfuncity.interfaces;

import java.util.List;

import in.fssa.globalfuncity.exception.PersistenceException;
import in.fssa.globalfuncity.model.Room;

public interface RoomInterface {
	public abstract List<Room> listAllRoomsByNoOfBeds(int id) throws PersistenceException;
}
