package hollowmen.model;

/**
 * This interface represents an {@code Interactable} {@link RoomEntity} intended as
 * an entity that can do something when the player interact with<br>
 * Example: the TreasureChest will loot something when the player interact with it
 * or the Door will move the player to the next room when he interacts with it
 * @author pigio
 *
 */
public interface Interactable extends RoomEntity{

	/**
	 * 
	 * @return {@code true} if the player can interact with this object, {@code false} otherwise
	 */
	public boolean isInteractAllowed();
	
	/**
	 * This method set the value of {@code isInteractAllowed()}
	 */
	public void setInteractAllowed(boolean isAllowed);
	
	/**
	 * This method define what happen when the player interact with this object
	 * 
	 * @throws IllegalStateException If {@code isInteractAllowed} return {@code false}
	 */
	public void interact() throws IllegalStateException;

}
