package hollowmen.model;

import java.util.Collection;

/**
 * This interface represent a {@code Room} intended as a container for {@link RoomEntity}<br>
 * {@code Room}s compose a single {@link Floor} and every {@code Room} has a "parent" {@code Room}
 * and not always "child" {@code Room}
 * 
 * @author pigio
 *
 */
public interface Room {

	/**
	 * This method give the {@code Room} "before" this
	 * @return {@link Room}
	 */
	public Room getParentRoom();
	
	/**
	 * This method populate this {@code Room} with any {@code RoomEntity}
	 */
	public void autoPopulate();
	
	/**
	 * This method give the selected child{@code Room}
	 * @param choice {@code int} counting from left to right (<u>starting from 0</u>)
	 * @return {@link Room}
	 * @throws IllegalArgumentException If <b>choice</b> > childRoomNumber, or negative
	 */
	public Room getChildRoom(int choice) throws IllegalArgumentException;
	
	/**
	 * This method give all the {@code RoomEntity} in this {@code Room}
	 * @return {@link Collection}<{@link RoomEntity}>
	 * NOTE: unmodifiableCollection
	 */
	public Collection<RoomEntity> getAllEntities();
	
	/**
	 * This method give all the {@code Enemy} in this {@code Room}
	 * @return {@link Collection}<{@link Enemy}>
	 * NOTE: unmodifiableCollection
	 */
	public Collection<Enemy> getEnemies();
	
	/**
	 * This method give all the {@code Bullet} in this {@code Room}
	 * @return {@link Collection}<{@link Attack}>
	 * NOTE: unmodifiableCollection
	 */
	public Collection<Attack> getBullets();
	
	/**
	 * This method give all the {@code Interactable} in this {@code Room}
	 * @return {@link Collection}<{@link Interactable}>
	 * NOTE: unmodifiableCollection
	 */
	public Collection<Interactable> getInteractable();
	
	/**
	 * This method give the number of this {@code Room} inside the {@code Floor}
	 * @return {@code int}
	 */
	public int getRoomNumber();
	
	/**
	 * This method adds the given <b>roomEntity</b> to the right Collection
	 * @param roomEntity {@link RoomEntity} to add at this {@code Room}
	 * @throws IllegalArgumentException 
	 */
	public void addEntity(RoomEntity roomEntity);
	
	/**
	 * This method remove the given <b>roomEntity</b> to the right Collection
	 * @param roomEntity {@link RoomEntity} to remove at this {@code Room}
	 * @throws IllegalArgumentException If the given <b>roomEntity</b> isn't present
	 */
	public void removeEntity(RoomEntity roomEntity) throws IllegalArgumentException;
}
