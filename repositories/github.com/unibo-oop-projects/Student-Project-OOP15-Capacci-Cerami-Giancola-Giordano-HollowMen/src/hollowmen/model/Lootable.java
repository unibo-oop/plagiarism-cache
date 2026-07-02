package hollowmen.model;

import java.util.Optional;

/**
 * This interface represents something that can be picked by the {@link Hero}<br>
 * It holds information about the Exp, the Gold and the {@link Item} given
 * @author pigio
 *
 */
public interface Lootable {
	
	/**
	 * This method give the value of gold given
	 * @return {@code int} represents the value of gold given
	 */
	public int getGold();
	
	/**
	 * This method give the value of exp given
	 * @return {@code int} represents the value of exp given
	 */
	public int getExp();
	
	/**
	 * This method give the {@code Optional} of an {@code Item}
	 * @return {@link Optional}<{@link Item}>
	 */
	public Optional<Item> getItem();
}
