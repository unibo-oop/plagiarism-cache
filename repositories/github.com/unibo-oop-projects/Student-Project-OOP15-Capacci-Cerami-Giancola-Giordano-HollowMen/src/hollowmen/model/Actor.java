package hollowmen.model;

import java.util.Collection;
import java.util.Map;

/**
 * This interface represents the Character as an Object that can:<br>
 * -be moved in two {@link Direction},<br>
 * -perform action (as jump, attack...),<br>
 * <br>
 * A Character has some {@link Parameter}
 * @author pigio
 *
 */
public interface Actor extends RoomEntity{

	/**
	 * RIGHT, LEFT.
	 * @author pigio
	 *
	 */
	public enum Direction {
		RIGHT,
		LEFT;
		
		@Override
		public String toString(){
			return this.name().toLowerCase();
		}
	}
	
	public enum Action {
		ATTACK,
		JUMP,
		ABILITY1,
		ABILITY2,
		ABILITY3,
		CONSUMABLE,
		INTERACT,
		BACK;
		
		@Override
		public String toString(){
			return this.name().toLowerCase();
		}
	}
	
	/**
	 * This method move the Character in the specified {@link Direction}.
	 * @param d {@link Direction} where the Character will move.
	 * @throws NullPointerException
	 */
	public void move(String d) throws NullPointerException;
	
	//state pattern per le TypeAction
	/**
	 * This method make the {@code Character} do something specified by action.
	 * @param action string represents what action will be perform
	 * @throws IllegalArgumentException If the Actor can't do this Action
	 * @throws IllegalStateException If the Actor can do this Action but not now
	 * @throws NullPointerException
	 */
	public void performAction(String action) throws IllegalArgumentException, IllegalStateException, NullPointerException;
	
	/**
	 * This method give all the {@link Parameter} of the Character
	 * @return {@link Map} associated to the Character<br>
	 * KEY: parameter's Name VALUE: {@link Parameter}
	 */
	public Map<String, Parameter> getParameters();
	
	/**
	 * 
	 * @return {@code true} if this {@code Character} is facing right, {@code false} if facing left
	 */
	public boolean isFacingRight();
	
	/**
	 * This method gives a {@code String} represents the state of this {@code Actor}<br>
	 * ex. "attack", "jump", "stand", "move"
	 * @return {@code String} represents the state of this {@code Actor}
	 */
	public String getState();
	
	/**
	 * This method changes the current state of this {@code Actor} with <b>state</b>
	 * @param state
	 */
	public void setState(String state);
	
	/**
	 * This method gives all the {@code Status} that are affecting this {@code Actor}
	 * @return {@link Collection}<{@link Status}> that are affecting this {@code Actor}
	 */
	public Collection<Information> getStatus();
}
