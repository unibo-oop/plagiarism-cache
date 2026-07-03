package levels;

import java.util.List;

import elements.Background;
import elements.Bullet;
import elements.EnemyGun;
import elements.Player;

/**
 * This class represent the Level interface. It includes all the methods 
 * that a Level must have to be played on {@link states.StateGame}.
 * 
 * @author Luca
 *
 */
public interface Level {
	/**
	 * Return the Background of this level.
	 * 
	 * @return a {@link elements.Background} of this level.
	 */
	Background getBackground();

	/**
	 * Return the Player of this level.
	 * 
	 * @return a {@link elements.Player}.
	 */
	Player getPlayer();

	/**
	 * Return the List of Enemies of this level.
	 * 
	 * @return a List of {@link elements.EnemyGun} of this level.
	 */
	List<EnemyGun> getEnemies();

	/**
	 * Return the List of Bullet of this level.
	 * 
	 * @return a List of {@link elements.Bullet} of this level.
	 */
	List<Bullet> getBulletList();

	/**
	 * Reset the Level by restoring the Enemies list, bullet list and the Player.
	 * 
	 * @throws IllegalStateException
	 *             if this method is called before the end of the game.
	 */
	void resetLevel() throws IllegalStateException;

	/**
	 * Populate the List of {@link elements.EnemyGun} of this level.
	 * 
	 * @param num
	 *            a int that is the number of elements who must have the List.
	 * @throws IllegalArgumentException
	 *             if argument num is not permitted.
	 */
	void generateEnemies(final int num) throws IllegalArgumentException;
}
