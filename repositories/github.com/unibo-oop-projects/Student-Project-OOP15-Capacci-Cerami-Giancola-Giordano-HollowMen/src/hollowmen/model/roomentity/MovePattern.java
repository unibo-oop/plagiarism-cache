package hollowmen.model.roomentity;

/**
 * A simple strategy for let the Enemy choose where move
 * @author pigio
 *
 */
public interface MovePattern {

	/**
	 * This method give a {@code String} represents the direction to move
	 * @return {@code String} represents the direction to move
	 */
	public String getDirection();
	
}
