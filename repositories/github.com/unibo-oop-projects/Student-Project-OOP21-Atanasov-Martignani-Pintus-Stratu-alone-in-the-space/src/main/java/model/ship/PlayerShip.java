package model.ship;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.bullet.Bullet;
import model.gun.Gun;
import utilities.InputCommands;


public class PlayerShip implements Ship {

	private final Vec2 position;
	private final Vec2 direction;
	private int maxHealth;
	private int health;
	private final int maxSpeed;
	private float speed;
	private float acceleration = 0;
	private final float directionMul = 1.01f;
	private final int rotationSpeed;
	private ImageView sprite;
	private Gun playerGun;
	private float yaw;

	/**
	 * constructor
	 * @param position initial position of the player
	 * @param maxHealth how much HP the ship has
	 * @param maxSpeed how fast the ship goes
	 * @param rotationSpeed how fast the ship turns
	 */
	public PlayerShip(Vec2 position, int maxHealth, int maxSpeed, int rotationSpeed) {
		this.position = new Vec2(position);
		this.maxHealth = maxHealth;
		this.health = this.maxHealth;
		this.speed = 0;
		this.maxSpeed = maxSpeed;
		this.direction = this.position.copy();
		this.rotationSpeed = rotationSpeed;
		//starts looking to the right
		this.yaw = 1;
	}

	/**
	 * shoots a bullet from the gun
	 * @return the bullet shot
	 */
	public Bullet shot() {
		return playerGun.shot(this.direction);
	}

	/**
	 * moves on tic update based on the direction, rotation and speed the ship has, updates position
	 * @param deltaTime time elapsed
	 */
	public void move(long deltaTime) {
		float accelerationAmount = 0.01f;
		long delta = 1_000_000L;
		float newSpeed = speed + acceleration * accelerationAmount * ((float)deltaTime)/ delta;
		this.setSpeed(newSpeed);
		try {
			float newX = (float) (speed * Math.cos(Math.toRadians(yaw)));
			float newY = (float) (speed * Math.sin(Math.toRadians(yaw)));
			this.direction.set(this.direction.x + newX * directionMul, this.direction.y + newY * directionMul);
			this.position.addLocal(newX,newY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * change speed in accordance
	 * @param newSpeed new speed
	 */
	private void setSpeed(float newSpeed) {
		if(newSpeed >= maxSpeed)
			this.speed = maxSpeed;
		else if(newSpeed <= -maxSpeed){
			this.speed = -maxSpeed;
		}
		else
			this.speed = newSpeed;
	}


	/**
	 * slows down the ship gradually (P.S.: Space doesn't have friction for this to happen)
	 */
	public void decaySpeed() {
		float decaySpeedMul = 0.95f;
		this.setSpeed(speed * decaySpeedMul);
	}
	/**
	 * change movement based on the input from player
	 */
	public void thrust(InputCommands input) {
		switch (input) {
			case UP:
				acceleration = 1;
				break;
			case DOWN:
				acceleration = -1;
				break;
			default:
				break;
		}
	}
	/**
	 * when both up and down inputs aren't being held, do not accelerate
	 */
	public void thrustReleased() {
		this.acceleration = 0;
	}

	/**
	 * rotate the ship on its position depending on the inputs
	 *
	 * @param input user input
	 */
	public void rotate(InputCommands input) {
		int direction = -1; //FOR LEFT
		if (input == InputCommands.RIGHT) {
			direction = Math.abs(direction);
		}
		this.yaw = (yaw + rotationSpeed * direction) % 360;
		float newX = (float) (Math.cos(Math.toRadians(yaw)));
		float newY = (float) (Math.sin(Math.toRadians(yaw)));
		this.direction.set(this.position.x + newX * directionMul, this.position.y + newY * directionMul);
	}

	/**
	 * getter
	 * @return angle of the ship
	 */
	@Override
	public double getAngle() {
		return this.yaw;
	}

	/**
	 * getter
	 * @return direction of the ship
	 */
	public Vec2 getDirection() {
		return this.direction;
	}

	/**
	 * not useful for Player ship, potentially reworkable for new weapon type
	 * @param target enemy ship to target
	 */
	public void setTarget(Ship target) {
		//NOT USED
	}

	/**
	 * setter
	 * @param gun Gun for the player ship
	 */
	@Override
	public void setGun(Gun gun) {
		this.playerGun = gun;
	}

	/**
	 * lose health based on the damage inflicted
	 * @param damage inflicted damage amount
	 */
	@Override
	public void strike(int damage) {
		this.health -= damage;
		if (this.health <= 0) {
			this.destroy();
		}
	}

	/**
	 * not useful for player, dedicated to enemy ships, potentially reworkable to drop the player gun
	 * @return null
	 */
	@Override
	public String drop() {
		return null;
	}

	/**
	 * DEFUNCT, not useful for player, potentially re-workable for new gun type
	 * @param deltaTime check every tic
	 * @return if a certain enemy is in range of attack
	 */
	@Override
	public Boolean isInRangeOfAttack( long deltaTime) {
		return null;
	}

	/**
	 * getter
	 * @return current player position
	 */
	public Vec2 getPosition() {
		return position;
	}

	/**
	 * setter
	 * @param newPos set new position of player
	 */
	public void setPosition(Vec2 newPos) {
		this.position.set(newPos);
	}

	/**
	 * destroys the playerShip
	 */
	public void destroy() {
		this.health = 0;
	}

	/**
	 * check if the ship still has health left
	 * @return if player has been destroyed
	 */
	@Override
	public Boolean isAlive() {
		return this.health > 0;
	}

	/**
	 * getter
	 *
	 * @return current health of the player
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * getter
	 * @return current max health of the player
	 */
	public int getMaxHealth() {
		return this.maxHealth;
	}

	/**
	 * setter
	 * @param hitPoints what health amount to set the player at
	 */
	public void setHealth(int hitPoints) {
		this.health = hitPoints;
	}

	/**
	 * heals the player up until max health
	 * @param hitPoints how much hp the player heals
	 */
	public void heal(int hitPoints) {
		this.setHealth(Math.min((this.health + hitPoints), maxHealth));
	}

	/**
	 * getter
	 * @return node-imageview of the player
	 */
	@Override
	public Node getNode() {
		return this.sprite;
	}

	/**
	 * getter
	 * @return imageview of the sprite of the player
	 */
	public ImageView getSprite() {
		return this.sprite;
	}

	/**
	 * setter
	 * @param img new sprite for the player
	 */
	@Override
	public void setSprite(Image img) {
		this.sprite = new ImageView();
		this.sprite.setImage(img);
		this.sprite.setViewOrder(-2);
	}

	/**
	 * setter
	 * @param maxHealth new max health the player can reach
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	 * getter
	 * @return current acceleration, check for no acceleration
	 */
	public float getAcceleration() {
		return this.acceleration;
	}

	/**
	 * DEFUNCT, not useful for the player, potentially re-workable for a new type of gun
	 * @return current enemy target
	 */
	@Override
	public Ship getTarget() {
		return null;

	}

}
