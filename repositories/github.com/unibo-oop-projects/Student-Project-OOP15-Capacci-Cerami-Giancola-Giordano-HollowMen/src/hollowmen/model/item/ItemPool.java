package hollowmen.model.item;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import hollowmen.model.Item;
import hollowmen.model.utils.Cloner;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.RandomSelector;

/**
 * This class holds every {@link Item} in the game.<br>
 * Each time anyone needs an {@code Item} it can refers to this class.<br>
 * This class gives always a clone of an {@code Item}<br>
 * This class is a Singleton
 * @author pigio
 *
 */
public class ItemPool {
	
	private Set<Item> itemInGame = new HashSet<>();
	
	private ItemPool(){};
	
	private static class Holder {
		public static ItemPool INSTANCE = new ItemPool();
	}
	
	/**
	 * 
	 * @return {@link ItemPool} unique instance
	 */
	public static ItemPool getInstance() {
		return Holder.INSTANCE;
	}
	
	/**
	 * This method add the <b>item</b> to the {@code ItemPool} and can be found in the game
	 * @param item {@link Item} to add to the game
	 * @throws IllegalArgumentException If the Item exists already in the {@code ItemPool}
	 * @throws NullPointerException
	 */
	public void addItem(Item item) throws IllegalArgumentException, NullPointerException{
		ExceptionThrower.checkNullPointer(item);
		itemInGame.add(item);
	}
	
	/**
	 * This method give a <u>copy</u> of the input {@code Item}'s <b>name</b> if exist
	 * @param name of the {@link Item} asked 
	 * @return a <u>copy</u> of the {@code Item}
	 * @throws IllegalArgumentException If the {@code Item}'s <b>name</b> doesn't exist in the Pool
	 */
	public Item getItem(String name) throws IllegalArgumentException{
		for(Item i : this.itemInGame) {
			if(i.equals(name)) {
				return Cloner.item(i);
			}
		}
		throw new IllegalArgumentException();
	}
	
	
	/**
	 * This method give a <u>copy</u> of a random {@code Item}
	 * @return a <u>copy</u> of an {@link Item} inside the Pool or null if no {@code Item}
	 * was added to the pool
	 */
	public Item getCompletelyRandom() {
		return (Item) RandomSelector.getAnyFrom(itemInGame.toArray());
	}
	
	/**
	 * This method give a <u>copy</u> of a random {@code Item} after apply 
	 * the given <b>func</b> 
	 * @param func {@link Predicate} <{@link Item}>
	 * @return <u>copy</u> of an {@code Item} inside the Item's Pool that meet the <b>func</b>
	 */
	public Item getRandom(Predicate<Item> func) {
		return (Item) RandomSelector.getAnyFrom(itemInGame.stream().filter(func).toArray());
	}
	
}
