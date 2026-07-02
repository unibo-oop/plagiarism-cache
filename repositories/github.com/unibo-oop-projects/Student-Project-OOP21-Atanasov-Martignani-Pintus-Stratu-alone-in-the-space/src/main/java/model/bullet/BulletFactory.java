package model.bullet;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.Image;
import model.ship.PlayerShip;
import model.ship.Ship;

public class BulletFactory {

	public static Bullet BasicBullet(Vec2 position, Vec2 direction) {

		class BasicBullet extends AbstractBullet {
			public BasicBullet(float maxSpeed, float acceleration, float rotationSpeed, int damage, Vec2 position,
							   Vec2 direction) {
				super(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
			}
		}
		Bullet var = new BasicBullet(100, 100, 10, 10, position, direction);
		var.setSprite(new Image("images/bullet_01.png"));
		return var;
	}

	public static Bullet bolt(Vec2 position, Vec2 direction) {

		class Bolt extends AbstractBullet {

			public Bolt(float maxSpeed, float acceleration, float rotationSpeed, int damage, Vec2 position,
						Vec2 direction) {
				super(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
			}

		}
		Bullet var = new Bolt(300, 100, 10, 3, position, direction);
		var.setSprite(new Image("images/bullet_02.png"));
		return var;
	}

	public static Bullet missile(Vec2 position, Vec2 direction, final Ship enemy) {

		class Missile extends AbstractBullet {

			protected final Ship target;
			private final float researchRange;

			public Missile(float maxSpeed, float acceleration, float rotationSpeed, int damage, float researchRange,
						   Vec2 position, Vec2 direction, Ship enemy) {
				super(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
				this.target = enemy;
				this.researchRange = researchRange;
			}

			@Override
			public void move(long deltaTime) {
				if (target != null && target.isAlive()) {
					double newdeltaTime = ((double) deltaTime) / 1000000000L; // conversion to seconds
					double angle = this.target.getPosition().sub(this.position).angle();
					if (Math.abs(this.speed.angle() - angle) < this.researchRange) {
						this.direction = this.direction.add(
								(new Vec2(1, 0)
										.setFromAngle(angle))
										.mul(newdeltaTime * this.rotationSpeed)
						);
					}
					this.speed = this.speed.add(direction.mul(newdeltaTime * acceleration));
					if (this.speed.length() > maxSpeed) {
						this.speed = this.speed.normalize().mul(this.maxSpeed);
					}
					this.position = this.position.add(speed.mul(newdeltaTime));

				} else {
					super.destroy();
				}
			}
		}
		Bullet var = new Missile(150, 20, 600, 10, 90, position, direction, enemy);
		var.setSprite(new Image("images/missile_01.png"));
		return var;
	}

	/**
	 * PlayerBullets which change value depending on the player gun modifications
	 *
	 * @param maxSpeed      top speed
	 * @param acceleration  how fast it reaches its top speed
	 * @param rotationSpeed if it can curve
	 * @param damage        how much damage it does on impact
	 * @param playerShip    player ship to get position, direction, yaw
	 * @return bullet entity that's been shot
	 */
	public static Bullet playerBullet(float maxSpeed, float acceleration, float rotationSpeed, int damage,
									  PlayerShip playerShip) {
		class PlayerBullet extends AbstractBullet {
			public PlayerBullet(float maxSpeed, float acceleration, float rotationSpeed, int damage,
								PlayerShip playerShip) {
				super(maxSpeed, acceleration, rotationSpeed, damage, playerShip.getPosition().copy(),
						playerShip.getDirection().copy());
				this.yaw = playerShip.getAngle();
				float newX = (float) (Math.cos(Math.toRadians(yaw)));
				float newY = (float) (Math.sin(Math.toRadians(yaw)));
				this.position.addLocal((playerShip.getSprite().getImage().getWidth()-20)*newX, (playerShip.getSprite().getImage().getHeight()-20)*newY);

			}

			private float speed2 = 0;
			private final double yaw;

			public void move(long deltaTime) {
				// TODO Check
				float newSpeed = speed2 + acceleration * 0.01f * ((float) deltaTime) / 1_000_000L;
				this.setSpeed(newSpeed);
				try {
					float newX = (float) (speed2 * Math.cos(Math.toRadians(yaw)));
					float newY = (float) (speed2 * Math.sin(Math.toRadians(yaw)));
					this.direction.set(this.direction.x + newX * 1.01f, this.direction.y + newY * 1.01f);
					this.position.addLocal(newX, newY);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void setSpeed(float newSpeed) {
				if (newSpeed >= maxSpeed)
					this.speed2 = maxSpeed;
				else this.speed2 = Math.max(newSpeed, -maxSpeed);
			}

			public double getAngle() {
				return this.yaw;
			}
		}
		Bullet var = new PlayerBullet(maxSpeed, acceleration, rotationSpeed, damage, playerShip);
		var.setSprite(new Image("images/bullet_00.png"));
		return var;
	}

}
