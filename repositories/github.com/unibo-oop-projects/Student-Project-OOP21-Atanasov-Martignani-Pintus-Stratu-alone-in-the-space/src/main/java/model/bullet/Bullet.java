package model.bullet;

import com.almasb.fxgl.core.math.Vec2;

import model.Entity;


public interface Bullet extends Entity {

	/**
     *	destroy this bullet.
     */
	void destroy();
	
	/**
	 * getter
	 * @return how much damage the bullet does
	 */
	int getDamage();
	
	/**
	 * getter
	 * @return the position of this bullet.
	 */
	Vec2 getPosition();
	
	/**
	 * getter
	 * @return the direction of this bullet.
	 */
	Vec2 getDirection();
}
