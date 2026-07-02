package hollowmen.model.facade;

import java.util.List;

import hollowmen.enumerators.Difficulty;
import hollowmen.model.utils.GameOverException;

/**
 * 
 * @author Giordo
 *
 */
public interface Model {
	public void setup();
	public void goTo(boolean lobby);
	public void update(long deltaTime)throws GameOverException;
	public void moveHero(String move);
	public void heroAction(String action);
	public List<DrawableRoomEntity> getDrawableRoomEntity();
	public List<InformationDealer> getPokedex();
	public List<InformationDealer> getInventory();
	public List<InformationDealer> getShop();
	public void itemEquip(InformationDealer item);
	public void itemUnequip(InformationDealer item);
	public void itemBuy(InformationDealer item);
	public void itemSell(InformationDealer item);
	public void setDifficulty(Difficulty difficulty);
}
