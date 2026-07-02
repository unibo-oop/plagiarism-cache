package model.ship;

import model.Entity;
import model.bullet.Bullet;
import model.gun.Gun;

public interface Ship extends Entity {
	
	/**
	 * Generate a bullet with the current direction and position of the ship.
	 * @return new bullet
	 */
	Bullet shot();
	
	/**
	 * destroy the Ship.
	 */
	void destroy();
	
	/**
	 * Set the gun for this ship.
	 * @param gun new Gun
	 */
	void setGun(Gun gun);
	
	/**
	 * decrease the ship current health for the damage specified.
	 * @param damage damage inflicted
	 */
	void strike(int damage);
	
	/**
	 * set the current target of this ship.
	 * @param target new target
	 */
	void setTarget(Ship target);


	/**
	 * get the current target of this ship.
	 * @return target
	 */
	Ship getTarget();
	
	
	
	/**
	 * Drop of this ship when destroyed.
	 * @return item dropped
	 */
	String drop();
	
	/**
	 * check if at least an enemy is in range of this ship and attack cooldown is off.
	 * @param deltaTime tic update
	 * @return true or false
	 */
	Boolean isInRangeOfAttack( long deltaTime);

	/**
	 * getter
	 * @return current health of the ship
	 */
	int getHealth();
}
