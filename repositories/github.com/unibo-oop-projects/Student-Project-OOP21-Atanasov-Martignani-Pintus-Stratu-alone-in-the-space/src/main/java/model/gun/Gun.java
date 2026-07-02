package model.gun;

import com.almasb.fxgl.core.math.Vec2;

import model.bullet.Bullet;
import model.ship.Ship;



public interface Gun {
	/**
	 * shot a bullet in the direction specified.
	 * @param direction vec2 direction of the bullet
	 * @return new bullet shot by the gun
	 */
	Bullet shot(Vec2 direction);
	
	/**
	 * check if there is at least an enemy in that direction, based on the range of the setted gun.
	 * @param shipPos starting position
	 * @param direction in which direction to check
	 * @param enemy ship to check
	 * @return true or false
	 */
	boolean isInRange(Vec2 shipPos, Vec2 direction, Ship enemy);
	
	/**
	 * the actual range of this gun.
	 * @return degrees of range
	 */
	float getDegRange();
}
