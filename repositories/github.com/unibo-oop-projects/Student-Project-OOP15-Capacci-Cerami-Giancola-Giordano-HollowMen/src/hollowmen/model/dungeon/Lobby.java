package hollowmen.model.dungeon;

import java.util.Collection;
import java.util.Collections;

import hollowmen.model.Attack;
import hollowmen.model.Enemy;
import hollowmen.model.Interactable;
import hollowmen.model.Room;
import hollowmen.model.RoomEntity;
import hollowmen.utilities.ExceptionThrower;

/**
 * This class represents an empty {@link Room}<br>
 * Since {@link RoomImpl} needs {@code Room} in order to correctly use the
 * Compose pattern, the {@link DungeonSingleton} use this class as start point
 * of the hierarchy
 * @author pigio
 *
 */
public class Lobby implements Room{
	
	public Lobby() {}
	
	/**
	 * @throws IllegalStateException always
	 */
	@Override
	public Room getParentRoom() throws IllegalStateException{
		ExceptionThrower.checkIllegalState(this, x -> true);
		return null;
	}

	/**
	 * @throws IllegalStateException always
	 */
	@Override
	public Room getChildRoom(int choice) throws IllegalArgumentException {
		ExceptionThrower.checkIllegalState(this, x -> true);
		return null;
	}

	/**
	 * @return Empty Collection
	 */
	@Override
	public Collection<RoomEntity> getAllEntities() {
		return Collections.emptyList();
	}

	/**
	 * @return Empty Collection
	 */
	@Override
	public Collection<Enemy> getEnemies() {
		return Collections.emptyList();
	}

	/**
	 * @return Empty Collection
	 */
	@Override
	public Collection<Attack> getBullets() {
		return Collections.emptyList();
	}

	/**
	 * @return Empty Collection
	 */
	@Override
	public Collection<Interactable> getInteractable() {
		return Collections.emptyList();
	}

	/**
	 * @return 0
	 */
	@Override
	public int getRoomNumber() {
		return 0;
	}

	/**
	 * Do nothing
	 */
	@Override
	public void addEntity(RoomEntity roomEntity) throws IllegalArgumentException {}

	/**
	 * Do nothing
	 */
	@Override
	public void removeEntity(RoomEntity roomEntity) throws IllegalArgumentException {}

	/**
	 * Do nothing
	 */
	@Override
	public void autoPopulate() {}

}
