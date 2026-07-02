package hollowmen.model.dungeon;

import java.util.Optional;

import hollowmen.model.Item;
import hollowmen.model.Lootable;

/**
 * This class implements {@link Lootable}<br>
 * @author pigio
 *
 */
public class LootableImpl implements Lootable{

	private int gold;

	private int exp;

	private Optional<Item> item;

	/**
	 * 
	 * @param gold
	 * @param exp
	 * @param item can be a <b>null</b>
	 */
	public LootableImpl(int gold, int exp, Item item){
		this.gold = gold;
		this.exp = exp;
		this.item = Optional.ofNullable(item);
	};

	/**
	 * {@inheritDoc Lootable}
	 */
	@Override
	public int getGold() {
		return this.gold;
	}

	/**
	 * {@inheritDoc Lootable}
	 */
	@Override
	public int getExp() {
		return this.exp;
	}

	/**
	 * {@inheritDoc Lootable}
	 */
	@Override
	public Optional<Item> getItem() {
		return this.item;
	}

}
