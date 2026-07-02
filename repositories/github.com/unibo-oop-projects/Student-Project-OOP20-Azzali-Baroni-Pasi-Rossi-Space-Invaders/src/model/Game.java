package model;

import java.util.List;

/**
 * The Interface Game.
 */
public interface Game {
	
	/**
	 * Update.
	 */
	void update();
	
	/**
	 * Gets the entities.
	 *
	 * @return the entities
	 */
	List<Entity> getEntities();
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	GameStatus getStatus();
	
	/**
	 * Check collision.
	 */
	void checkCollision();
	
	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	int getLevel();
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	int getScore();
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	PlayerImpl getPlayer();
	
	/**
	 * Gets the bullets.
	 *
	 * @return the bullets
	 */
	List<BulletImpl> getBullets();
}
