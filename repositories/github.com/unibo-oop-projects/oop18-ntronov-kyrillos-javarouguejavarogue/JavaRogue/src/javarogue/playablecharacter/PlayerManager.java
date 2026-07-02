package javarogue.playablecharacter;

import javarogue.behaviourmodules.Movable;
import javarogue.utility.Direction;
import javarogue.utility.Position;
/**
 * interface that allows interaction with the character
 */
public interface PlayerManager extends Movable {
	/**
	 * Builds character
	 * @param origin the position from which the character begins
	 * @return the character built.
	 */
	void makeCharacter(Position origin);
	/**
	 * Moves character
	 * @param direction the direction the character has to move
	 */
	void moveCharacter(Direction direction);
	/**
	 * sets the character inside a level
	 * @param level the level
	 */
	void setLevel(javarogue.level.Level level);
	
	@Override
	public Position getPosition();
	/**
	 * the character.
	 * @return the character
	 */
	public PlayableCharacter getCharacter();
}
