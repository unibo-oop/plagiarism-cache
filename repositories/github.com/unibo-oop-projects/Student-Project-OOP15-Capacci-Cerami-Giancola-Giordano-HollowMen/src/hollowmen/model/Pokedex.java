package hollowmen.model;

import java.util.Collection;

/**
 * This interface represents an object that tracks all the {@link Enemy} met
 * @author pigio
 *
 */
public interface Pokedex {

	/**
	 * This method update the enemy met 
	 * @param r {@link Room}
	 */
	public void checkNewEnemy(Room r);
	
	/**
	 * This method give all the {@code Enemy} met
	 * @return {@link Collection}<{@link Enemy}>
	 */
	public Collection<Enemy> getEnemyMet();
	
}
