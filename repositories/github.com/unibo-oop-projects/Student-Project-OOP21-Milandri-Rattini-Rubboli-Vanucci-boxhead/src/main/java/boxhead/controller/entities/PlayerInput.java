package boxhead.controller.entities;

import java.util.Set;

import boxhead.model.entities.utils.Direction;
import javafx.scene.input.KeyCode;

import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.W;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.SPACE;
import static javafx.scene.input.KeyCode.Q;
import static javafx.scene.input.KeyCode.E;

public class PlayerInput {
	
	private PlayerInput() {
	}
	
	/**
	 * Check the inputs saved in a Set to return the Direction
	 * @param Set keys
	 * @return Direction
	 */
	public static Direction calculateDirection(final Set<KeyCode> keys) {
		if (keys.contains(A) && keys.contains(W))
			return Direction.NORTH_WEST;
		if (keys.contains(W) && keys.contains(D))
			return Direction.NORTH_EAST;
		if (keys.contains(D) && keys.contains(S))
			return Direction.SOUTH_EAST;
		if (keys.contains(A) && keys.contains(S))
			return Direction.SOUTH_WEST;
		if (keys.contains(A))
			return Direction.WEST;
		if (keys.contains(W))
			return Direction.NORTH;
		if (keys.contains(S))
			return Direction.SOUTH;
		if (keys.contains(D))
			return Direction.EAST;
		return Direction.NULL;
	}
	
	/**
	 * Check if the Attack button is pressed then return a boolean
	 * @param Set keys
	 * @return boolean true if key is pressed 
	 */
	public static boolean shotInput(final Set<KeyCode> keys) {
		return keys.contains(SPACE);
	}
	
	/**
	 * Check if previous gun key is pressed
	 * @param Set keys
	 * @return boolean true if key is pressed 
	 */
	public static boolean previousGun(final Set<KeyCode> keys) {
		return keys.contains(Q);
	}
	
	/**
	 * Check if next gun key is pressed
	 * @param Set keys
	 * @return boolean true if key is pressed 
	 */
	public static boolean nextGun(final Set<KeyCode> keys) {
		return keys.contains(E);
	}
}