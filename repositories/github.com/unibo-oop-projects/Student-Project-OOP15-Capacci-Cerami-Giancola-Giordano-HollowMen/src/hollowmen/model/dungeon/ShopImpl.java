package hollowmen.model.dungeon;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import hollowmen.model.Item;
import hollowmen.model.Item.ItemState;
import hollowmen.model.Shop;
import hollowmen.model.item.ItemPool;

/**
 * This class implements {@link Shop}
 * @author pigio
 *
 */
public class ShopImpl implements Shop{

	
	private Collection<Item> sellableItem = new LinkedList<Item>();
	
	/**
	 * This constructor builds the shop using the <b>allItemName</b>
	 * which hold the names of {@code Item}<br><br>
	 * NOTE: This constructor use {@link ItemPool} for retrieve the {@code Item}s
	 * @param allItemName <br>
	 */
	public ShopImpl(Collection<String> allItemName) {
		allItemName.stream()
			.forEach(x -> {
				Item temp = ItemPool.getInstance().getItem(x);
				temp.setState(ItemState.BUYABLE);
				sellableItem.add(temp);
			});
	}
	
	/**
	 * This constructor builds the {@code Shop} using <b>shop</b>'s {@code Item}
	 * @param shop
	 * @param i
	 */
	public ShopImpl(Collection<Item> shop, int i) {
		shop.stream().forEach(x -> x.setState(ItemState.BUYABLE));
		this.sellableItem = shop;
	}

	/**
	 * {@inheritDoc Shop}
	 */
	@Override
	public Collection<Item> getShopItem() {
		return Collections.unmodifiableCollection(this.sellableItem);
	}

}
