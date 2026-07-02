package model.bullet;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractBullet implements Bullet {
	private boolean alive;
	protected final float maxSpeed;
	protected final float acceleration;
	protected final float rotationSpeed;
	private final int damage;
	protected Vec2 speed;
	protected Vec2 direction;
	protected Vec2 position;
	private ImageView sprite;
	

	protected AbstractBullet(float maxSpeed, float acceleration, float rotationSpeed, int damage, Vec2 position, Vec2 direction) {

		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
		this.damage = damage;
		this.position = position;
		this.direction = direction;
		this.alive = true;
		this.speed=direction.normalize().mul(maxSpeed);
	}
	
	public void move(long deltaTime) {
		double newDeltaTime = ((double)deltaTime) / 1_000_000_000L; //conversion to seconds
		this.speed = this.speed.add(direction.mul(newDeltaTime * acceleration));
		if (this.speed.length() > maxSpeed) {
			this.speed = this.speed.normalize().mul(this.maxSpeed);
		}
		this.position = this.position.add(speed.mul(newDeltaTime));
	}
	
	@Override
	public double getAngle() {
		return this.direction.angle();
	}

	public void destroy() {
		this.alive = false;
	};

	public int getDamage() {
		return this.damage;
	};


	@Override
	public Vec2 getPosition() {
		return this.position.copy();
	}

	@Override
	public Boolean isAlive() {
		return this.alive;
	}
	@Override
	public Node getNode() {
		return this.sprite;
	}

	@Override
	public Vec2 getDirection() {
		return this.direction.copy();
	}
	@Override
	public void setPosition(Vec2 newPos) {
		 this.position = newPos;
	}
		public void setSprite(Image img) {
			this.sprite = new ImageView();
			this.sprite.setImage(img);
			this.sprite.setViewOrder(-1);
		}
}
