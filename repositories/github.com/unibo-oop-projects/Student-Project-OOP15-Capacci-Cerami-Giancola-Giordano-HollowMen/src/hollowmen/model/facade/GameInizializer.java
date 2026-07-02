package hollowmen.model.facade;

import hollowmen.enumerators.Difficulty;
import hollowmen.model.Dungeon;
import hollowmen.model.Enemy;
import hollowmen.model.Hero;
import hollowmen.model.Item;
import hollowmen.model.enemy.EnemyBuilder;
import hollowmen.model.item.ItemBuilder;

/**
 * 
 * @author pigio
 *
 */
public interface GameInizializer {

	public void addItem(Item item);
	
	public void addEnemy(Enemy en);
	
	public void setHero(Hero hero);
	
	public void setDiscoveredFloors(int lastUnlock, int maxFloor);
	
	public void setDifficulty(Difficulty diff);
	
	public Dungeon getDungeon();
	
	public EnemyBuilder getBuilderFor(String enemyName);
	
	public ItemBuilder getItemBuilder();
}
