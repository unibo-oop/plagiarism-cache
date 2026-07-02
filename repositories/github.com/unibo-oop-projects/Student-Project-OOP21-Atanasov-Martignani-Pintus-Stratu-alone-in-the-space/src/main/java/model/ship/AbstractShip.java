package model.ship;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.bullet.Bullet;
import model.gun.Gun;

public abstract class AbstractShip implements Ship {
    private int health;
    private final float maxSpeed;
    private final float acceleration;
    private final float rotationSpeed;
    private long lastAttack = 0;
    private final long attackCooldown;
    private Vec2 speed;
    private Vec2 direction;
    private Vec2 position;
    private Ship target;
    private ImageView sprite;
    private Gun gun;

    public AbstractShip(int health, float maxSpeed, float acceleration, float rotationSpeed, long attackCD,
	    Vec2 newPosition) {

	this.attackCooldown = attackCD * 1000000L;

	this.health = health;
	this.maxSpeed = maxSpeed;
	this.acceleration = acceleration;
	this.rotationSpeed = rotationSpeed;
	this.position = newPosition;
	this.direction = new Vec2(0, 1);
	this.speed = new Vec2(0, 0);
    }

    public void move(long deltaTime) {

	double newdeltaTime = ((double) deltaTime) / 1000000000L; // conversion to seconds
	if (this.target != null) {
	    var angle = (calculateDir());
	    if (Math.abs(this.speed.angle() - angle) > 90 || Math.abs(angle) > this.gun.getDegRange() / 2) {
		this.direction = this.direction
			.add((new Vec2(1, 0).setFromAngle(angle)).mul(newdeltaTime * this.rotationSpeed));
	    }
	}
	this.speed = this.speed.add(direction.mul(newdeltaTime * acceleration));
	if (this.speed.length() > maxSpeed) {
	    this.speed = this.speed.normalize().mul(this.maxSpeed);
	}
	this.position = this.position.add(speed.mul(newdeltaTime));

    }

    public Bullet shot() {
	this.lastAttack -= this.attackCooldown;
	Bullet bullet = gun.shot(this.getDirection());
	bullet.setPosition(this.position.copy());
	return bullet;
    }

    @Override
    public void destroy() {
	this.health = 0;
	this.target = null;
    }

    @Override
    public void setTarget(Ship enemy) {
	this.target = enemy;
	this.target.getPosition().copy();
    }

    @Override
    public Ship getTarget() {
	return this.target;
    }

    @Override
    public double getAngle() {
	return (this.speed.angle() + 90);

    }

    @Override
    public Vec2 getPosition() {
	return position.copy();
    }

    @Override
    public Vec2 getDirection() {
	return this.direction.copy();
    }

    @Override
    public void setPosition(Vec2 newPos) {
	this.position = newPos;
    }

    @Override
    public Boolean isInRangeOfAttack(long deltaTime) throws NullPointerException {

	if (this.lastAttack > this.attackCooldown) {
	    return gun.isInRange(this.position.copy(), this.direction.copy(), this.target);
	} else {
	    this.lastAttack += deltaTime;
	}
	return false;
    }

    @Override
    public void setGun(Gun gun) {
	this.gun = gun;
    }

    public void setSprite(Image img) {
	this.sprite = new ImageView();
	this.sprite.setImage(img);
	this.sprite.setViewOrder(-2);

    }

    @Override
    public Node getNode() {
	return this.sprite;
    }

    @Override
    public String drop() {
	return null;
    }

    @Override
    public void strike(int damage) {
	this.health -= damage;
    }

    @Override
    public Boolean isAlive() {
	if (this.health > 0) {
	    return true;
	}
	{
	    this.destroy();
	}
	return false;
    }

    /**
     * calculate the angle from the actual direction, and ideal direction
     * 
     * @return new direction
     */
    private float calculateDir() {
	return this.target.getPosition().sub(this.position).angle();
    }

    public int getHealth() {
	return this.health;
    }

}
