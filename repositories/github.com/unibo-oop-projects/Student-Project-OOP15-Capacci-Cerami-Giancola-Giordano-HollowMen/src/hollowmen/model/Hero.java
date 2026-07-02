package hollowmen.model;

import java.util.Collection;

import hollowmen.utilities.Pair;

/**
 * This interface represents the {@code Hero} intended as the {@link Actor} controlled by the player<br>
 * <br>
 * The {@code Hero} can equip, unequip, sell and buy {@link Item}<br>
 * <br>
 * The {@code Hero} has a {@link Level} that increase his {@link Parameter} and add skill points to the {@link SkillTree}<br>
 * <br>
 * The {@code Hero} has an {@link Inventory} to keep all {@link Item} found during the game<br>
 * <br>
 * The {@code Hero} has a {@link Pokedex} to keep all information about the {@link Enemy} met<br>
 * <br>
 * The {@code Hero} has a {@link HeroClass} that hold the information about his {@link SkillTree},
 * his basic attack range and {@link Parameter}<br>
 * <br>
 * @author pigio
 *
 */
public interface Hero extends Actor{

	/**
	 * This method equip the <b>item</b> adding it's {@link Modifier} to the {@code Hero}'s {@link Parameter} <br>
	 * <br>
	 * the <b>item</b> will replace the {@link Item} already equipped in the same slot
	 * 
	 * @param item {@link Item} to equip
	 * @throws IllegalStateException If the <b>item</b> can't be equipped
	 * @throws IllegalArgumentException If hero haven't the <b>item</b> in the {@link Inventory}
	 * @throws NullPointerException
	 */
	public void equipItem(Item item) throws IllegalStateException, IllegalArgumentException, NullPointerException;
	
	/**
	 * This method unequip the <b>item</b> removing it's {@link Modifier} to the {@code Hero}'s {@link Parameter} <br>
	 * 
	 * @param item {@link Item} to unequip
	 * @throws IllegalStateException if the <b>item</b> isn't equipped
	 * @throws NullPointerException
	 */
	public void unequipItem(Item item) throws IllegalStateException, NullPointerException;

	/**
	 * This method sell the passed <b>item</b> removing it from the {@link Inventory}
	 * and add his sellValue to the {@code Hero}'s gold
	 * @param item {@link Item} to sell
	 * @throws IllegalStateException If the <b>item</b> is unsellable
	 * @throws IllegalArgumentException If the <b>item</b> isn't in the {@code Inventory}
	 * @throws NullPointerException
	 */
	public void sellItem(Item item) throws IllegalStateException, IllegalArgumentException, NullPointerException;
	
	/**
	 * This method buy an <b>item</b> adding it to the {@link Inventory} and subtract his sellValue to the Hero's gold
	 * @param item the {@link Item} to buy
	 * @throws IllegalStateException If the {@code Hero} has less gold than the <b>item</b>'s sellValue
	 * @throws NullPointerException
	 */
	public void buyItem(Item item) throws IllegalStateException, NullPointerException;
	
	/**
	 * @return {@link Pair} X current exp, Y exp to reach for level up
	 */
	public Pair<Integer, Integer> getExp();
	
	/**
	 * This method gives the {@code Hero}'s level
	 * @return {@code int}
	 */
	public int getLevel();
	
	/**
	 * This method give the {@code Hero}'s gold
	 * @return {@code int} that represents the Hero's gold
	 */
	public int getGold();
	
	/**
	 * This method give the {@code Hero}'s {@code Inventory}
	 * @return {@link Inventory}
	 */
	public Inventory getInventory();
	
	/**
	 * This method give the {@code Hero}'s {@code HeroClass}
	 * @return {@link HeroClass}
	 */
	public HeroClass getHeroClass();
	
	/**
	 * This method allow the {@code Hero} to add Exp, Gold and {@code Item} using the passed {@link Lootable} object
	 * @param loot {@link Lootable}
	 * @throws NullPointerException
	 */
	public void pick(Lootable loot) throws NullPointerException;
	
	/**
	 * This method gives a {@code TargetPointSystem} based on the {@code Parameter} of this {@code Hero} 
	 * @return {@link TargetPointSystem}<{@link Parameter}>
	 */
	public TargetPointSystem<Parameter> getUpgradableParameter();
	
	/**
	 * This method gives all the equipped {@code Item}
	 * @return {@link Collection} {@link Item}
	 */
	public Collection<Item> getEquippedItem();
}
