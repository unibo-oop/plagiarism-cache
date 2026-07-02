package model.gun;

import com.almasb.fxgl.core.math.Vec2;

import model.bullet.Bullet;
import model.bullet.BulletFactory;
import model.ship.Ship;

abstract class AbstractGun implements Gun {
	private final int degRange;
	protected final Ship actualShip;

	public AbstractGun(int degRange, Ship ship) {
		this.degRange = degRange;
		this.actualShip = ship;
	}

	public Bullet shot(Vec2 direction) {
		// TODO Auto-generated method stub
		return BulletFactory.BasicBullet(actualShip.getPosition(), direction);

	}

	public boolean isInRange(Vec2 shipPos, Vec2 direction,Ship enemy) {
		// TODO Auto-generated method stub
	    return Math.abs(enemy.getPosition().sub(shipPos).angle()-direction.angle())<this.degRange/2f;

	}

	public float getDegRange() {
		// TODO Auto-generated method stub
		return this.degRange;
	}

}
