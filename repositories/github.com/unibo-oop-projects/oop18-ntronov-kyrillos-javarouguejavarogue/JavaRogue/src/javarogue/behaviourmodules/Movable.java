package javarogue.behaviourmodules;

import javarogue.utility.Direction;
import javarogue.utility.Position;

/**
 * Interface that represents the ability for an object to move in the level.
 */
public interface Movable {
	
	/**
	 * Method that changes the position the object is in.
	 * @param direction the direction the object is facing
	 */
	public void changePosition(Direction direction);
	
	/**
	 * Method that returns the position the object is in
	 * @return the position
	 */
	public Position getPosition();
}
