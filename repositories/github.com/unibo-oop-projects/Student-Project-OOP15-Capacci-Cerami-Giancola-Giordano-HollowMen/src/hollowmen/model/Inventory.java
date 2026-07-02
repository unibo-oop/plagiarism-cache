package hollowmen.model;

import java.util.Collection;

import hollowmen.utilities.Pair;

/**
 * This interface represents the {@code Inventory} intended as an object that
 * holds {@link Item}<br>
 * {@code Inventory} tracks all {@code Item} that has and has had
 * @author pigio
 *
 */
public interface Inventory {

	/**
	 * This method add the <b>item</b> to the {@code Inventory}
	 * @param item {@link Item} to add
	 */
	public void addItem(Item item);
	
	/**
	 * This method remove the <b>item</b> to the {@code Inventory}
	 * @param item {@link Item} to remove
	 */
	public void removeItem(Item item);
	
	/**
	 * This method gives a specific Item from the {@code Inventory}
	 * @param itemName {@code String}
	 * @return {@link Item}
	 * @throws IllegalArgumentException If <b>itemName</b> isn't in the {@code Inventory}
	 */
	public Item getItem(String itemName) throws IllegalArgumentException;
	
	/**
	 * This method give all the {@code Item} currently stored in the {@code Inventory}
	 * @return {@link Collection}<{@link Item}> currently stored in the {@code Inventory}<br>
	 * NOTE: unmodifiable Collection
	 */
	public Collection<Pair<Item, Integer>> getAllItem();
	
	/**
	 * This method give all the {@code Item} that has had stored in the {@code Inventory}
	 * @return {@link Collection}<{@link Item}> has had stored in the {@code Inventory}<br>
	 * NOTE: unmodifiable Collection
	 */
	public Collection<Item> getAllItemFound();
	
}
