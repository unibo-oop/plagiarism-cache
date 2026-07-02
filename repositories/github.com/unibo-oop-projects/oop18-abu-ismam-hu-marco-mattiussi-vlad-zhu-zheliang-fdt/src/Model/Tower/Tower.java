package model.tower;

import java.util.ArrayList;

import model.enemy.Enemy;
import model.entity.Entity;
import model.projectile.Projectile;

public interface Tower extends Entity {

	/**
	 * 
	 * @return
	 */
	float getShootTime(); 
	
	/**
	 * 
	 * @return
	 */
	boolean isShooting();
	
	
	/**
	 * 
	 * @return target
	 */
	Enemy getTarget();

	/**
	 * set type of tower
	 * @param type
	 */
	void setType(TowerType type);

	/**
	 * choose type of tower
	 * @return
	 */
	TowerType getType();
	
	/**
	 * filter of enemies
	 * @param entities
	 */
	public void setEnemies(ArrayList<Entity> entities);

	/**
	 * to verify the presence of the target
	 * @return
	 */
	boolean isTargetSet();
	
	/**
	 * 
	 * @return the projectile that the tower has just shot
	 */
	Projectile getProjectile();
	
	
	
	
}
