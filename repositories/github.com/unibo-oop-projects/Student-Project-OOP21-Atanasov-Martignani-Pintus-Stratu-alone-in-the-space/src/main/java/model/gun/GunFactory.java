package model.gun;

import com.almasb.fxgl.core.math.Vec2;

import model.bullet.Bullet;
import model.bullet.BulletFactory;
import model.ship.PlayerShip;
import model.ship.Ship;



public class GunFactory {

	public static Gun rifle(Ship spaceship) {
		class Rifle extends AbstractGun {

			public Rifle(int degRange, Ship ship) {
				super(degRange, ship);
			}
			public Bullet shot(Vec2 direction) {
				return BulletFactory.bolt(super.actualShip.getPosition(), super.actualShip.getDirection());

			}

		}
		return new Rifle(10, spaceship);
	}

	public static Gun missile(Ship spaceship) {
		class Missile extends AbstractGun {

			public Missile(int degRange, Ship ship) {
				super(degRange, ship);
			}
			
			 public Bullet shot(Vec2 direction) {
				return BulletFactory.missile(super.actualShip.getPosition(), super.actualShip.getDirection(), super.actualShip.getTarget());

			}
			 

		}
		return new Missile(45, spaceship);

	}

	public static Gun boltGun(Ship spaceship) {
		class BoltGun extends AbstractGun {

			public BoltGun(int degRange, Ship ship) {
				super(degRange, ship);
			}
			

		}
		return new BoltGun(30, spaceship);
	}

	/**
	 * creates and returns the player gun to the ship
	 * @param ship the player
	 * @param damage how much damage the bullet does, has a default value
	 * @param maxSpeed how fast the bullet can traverse the map
	 * @param acceleration how fast the bullet can reach its top speed
	 * @param rotationSpeed if the bullet can curve
	 * @return gun set with the default values
	 */
	public static Gun playerGun(PlayerShip ship, int damage, float maxSpeed, float acceleration, float rotationSpeed) {
		class PlayerGun extends AbstractGun {
			/**
			 * constructor
			 * @param degRange range of the bullets
			 * @param playerShip the player to give it to
			 * @param bulletDamage how much damage the bullet does
			 * @param bulletMaxSpeed top speed of the bullet
			 * @param bulletAcceleration how fast the bullet can get to its top speed
			 * @param bulletRotationSpeed if the bullet can curve
			 */
			public PlayerGun(int degRange, PlayerShip playerShip, int bulletDamage, float bulletMaxSpeed, float bulletAcceleration, float bulletRotationSpeed) {
				super(degRange, playerShip);
				this.bulletDamage = bulletDamage;
				this.bulletMaxSpeed = bulletMaxSpeed;
				this.bulletAcceleration = bulletAcceleration;
				this.bulletRotationSpeed = bulletRotationSpeed;
			}

			//Use predefined values from PlayerShipValues
			private final int bulletDamage;
			private final float bulletMaxSpeed;
			private final float bulletAcceleration;
			private final float bulletRotationSpeed;

			public Bullet shot(Vec2 direction) {
				return BulletFactory.playerBullet(bulletMaxSpeed, bulletAcceleration, bulletRotationSpeed, bulletDamage,
						ship);
			}
		}
		return new PlayerGun(40, ship, damage, maxSpeed, acceleration, rotationSpeed);
	}

}
