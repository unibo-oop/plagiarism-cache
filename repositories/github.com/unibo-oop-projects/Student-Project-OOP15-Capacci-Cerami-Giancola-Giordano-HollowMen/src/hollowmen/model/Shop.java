package hollowmen.model;

import java.util.Collection;

/**
 * This interface represents the Shop in the game
 * 
 * @author pigio
 *
 */
public interface Shop {
	
	/**
	 * This method give all the buyable {@link Item} from the shop<br>
	 * 
	 * <u>The Item inside the {@link Collection} must have their {@link ItemState} setted to BUYABLE</u> 
	 * 
	 * @return a {@link Collection} of <u>BUYABLE</u> {@link Item}
	 */
	public Collection<Item> getShopItem();
	
}
