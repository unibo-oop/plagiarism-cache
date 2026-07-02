package hollowmen.model.item;

import java.util.Collection;

import hollowmen.model.Information;
import hollowmen.model.Item;
import hollowmen.model.Item.ItemState;
import hollowmen.model.Modifier;

/**
 * This Interface represents an Object that allow anyone outside the model to set up 
 * the foundable {@link Item} into the model.<br>
 * Through the {@code build()} method an {@code Item} with the given properties is stored in {@link ItemPool}
 * @author pigio
 *
 */
public interface ItemBuilder {

	/**
	 * 
	 * @param info {@link Information} with <u>name</u> and description
	 * @return {@code ItemBuilder}
	 */
	public ItemBuilder info(Information info);
	
	/**
	 * 
	 * @param state {@link ItemState} <u>state</u>
	 * @return {@code ItemBuilder}
	 */
	public ItemBuilder state(ItemState state);
	
	/**
	 * 
	 * @param coll {@link Modifier} <u>collection</u> not null and not empty
	 * @return {@code ItemBuilder}
	 */
	public ItemBuilder modifier(Collection<Modifier> coll);
	
	/**
	 * 
	 * @param i <u>value</u> can't be less than one
	 * @return {@code ItemBuilder}
	 */
	public ItemBuilder value(int i);
	
	/**
	 * 
	 * @param i <u>rarity</u> can't be less than one
	 * @return {@code ItemBuilder}
	 */
	public ItemBuilder rarity(int i);
	
	/**
	 * 
	 * @param slot {@link Information} with <u>name</u>
	 * @return {@code ItemBuilder}
	 */
	public ItemBuilder slot(String slot);
	
	/**
	 * 
	 * @param className {@link Information} with <u>name</u>
	 * @return {@code ItemBuilder}
	 */
	public ItemBuilder heroClass(String className);
	
	
	/**
	 * This method will create the constructed {@link Item}
	 * @throws IllegalStateException If any of the underlined previous method 
	 * wasn't called or called passing wrong arguments
	 * @throws NullPointerException If any of the previous method was called with null argument
	 */
	public Item build() throws IllegalStateException, NullPointerException;
}
