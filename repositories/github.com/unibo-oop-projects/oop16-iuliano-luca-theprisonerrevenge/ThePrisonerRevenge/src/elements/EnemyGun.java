package elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Enemy is a DECORATION of {@link Character}. This Class represent the enemy
 * Character of the game. It will run left and right in loop on map until the
 * {@link Player} is out of range, when the player enter in range, the Enemy will become
 * offensive and it will start firing.
 * 
 * @author Luca
 */
public class EnemyGun extends Character {
	private static final int STEP_FIRE = 25;
	private static final int FIRE_BULLET_INDEX = 4;
	private static final int STEP_PATROL = 1;
	private static final int BULLET_DAMAGE = 20; 				
	private static final int BULLET_SPEED = 4;
	private static final int MIN_RANGE_PATROL = 200;
	private static final int RANGE_PATROL = 100;
	private static final int MIN_RANGE_ATTACK = 140;
	private static final int RANGE_ATTACK = 80;

	private Random random;
	private int minPatrol;
	private int maxPatrol;
	private int waitPatrol;
	private boolean isPatrol;
	private int min;
	private int max;
	private int attackRange;
	private final Player player;
	private final Background background;

	/**
	 * This builder call the Superclass builder, It also get the instances of
	 * {@link Player} and {@link Background}.
	 * 
	 * @see elements.Character#Character(int, int, int, int, Map)
	 * 
	 * @param x
	 *            a int value for X coordinate of Character.
	 * @param y
	 *            a int value for Y coordinate of Character.
	 * @param player
	 *            a {@link Player} instance.
	 * @param background
	 *            a {@link Background} instance.
	 * @param animations
	 *            a Map contains all the possible animations of the Character.
	 * @param bulletList
	 *            a List contains all bullet fired.
	 */
	public EnemyGun(final int x, final int y, final Player player, final Background background,
					Map<String, List<BufferedImage>> animations, final List<Bullet> bulletsList) {
		super(x, y, animations, bulletsList);
		this.player = player;
		this.background = background;
		this.random = new Random();
		this.initPatrolStep();
	}

	/**
	 * Set the Enemy to patrol animation. Initialize the variables to simulate
	 * randomly step in patrol state of Enemy.
	 */
	private void initPatrolStep() {
		this.maxPatrol = random.nextInt(RANGE_PATROL) + MIN_RANGE_PATROL;
		this.attackRange = random.nextInt(RANGE_ATTACK) + MIN_RANGE_ATTACK;
		this.waitPatrol = this.maxPatrol / 2;
		this.min = random.nextInt(this.maxPatrol);
		this.max = maxPatrol;
		this.isPatrol = true;
	}

	/**
	 * Indicates if the Character is dead.
	 * 
	 * @return a boolean that indicate if the Character is dead.
	 */
	public boolean isDead() {
		return this.dead;
	}

	/**
	 * Indicates if the Character is patrol.
	 * 
	 * @return a boolean that indicate if the Character is patrol.
	 */
	public boolean isPatrol() {
		return this.isPatrol;
	}

	/**
	 * Enemy start patrol: the Character will run left and right in loop until
	 * the Player is out of range.
	 */
	public void patrol() {
		if (!this.isPatrol()) {
			this.min = minPatrol;
			this.max = maxPatrol;
			this.isPatrol = true;
		}
		if (this.max == maxPatrol && this.min < maxPatrol) {
			this.turn(false, true);
			if (this.min > maxPatrol - waitPatrol) { // waiting some seconds
				super.setWait();
			} else {
				this.moveX(STEP_PATROL);
			}
			this.min++;
		}
		if (this.min == maxPatrol && this.max > minPatrol) {
			this.turn(true, false);
			if (this.max < minPatrol + waitPatrol) { // waiting some seconds
				super.setWait();
			} else {
				this.moveX(STEP_PATROL);
			}
			this.max--;
		}
		if (this.max == minPatrol && this.min == maxPatrol) {
			this.min = minPatrol;
			this.max = maxPatrol;
		}
	}

	/**
	 * Set Character animation to FIRE only if the Player is not flying, when
	 * the animation is finish, it will be set to WAIT.
	 */
	@Override
	public void setFire() {
		if (!this.isFiring()) {
			this.setAnimation(AnimationEnum.FIRE);
			this.fireBullet = true;
		}
		if (super.fireBullet && super.getIndex() == FIRE_BULLET_INDEX) {
			super.bulletsList.add(new Bullet(this, this.background, this.getX(), super.getY(), true, BULLET_DAMAGE,
					BULLET_SPEED, super.animationsMap));
			super.fireBullet = false;
		}
		if (this.isLastFrame()) {
			this.setWait();
		}
	}

	/**
	 * Check player position for turn Enemy Character orientation on Player
	 * side.
	 * 
	 * @param playerX
	 *            a int represent the X position of the player.
	 */
	public void checkPlayerPosition(final int playerX) {
		if (this.getX() > playerX) {
			super.turn(true, false);
		} else {
			super.turn(false, true);
		}
	}

	/**
	 * Enemy become offensive: the Character will turn on side of player and
	 * start fire.
	 */
	public void thrust() {
		if (this.isPatrol()) {
			this.min = minPatrol;
			this.isPatrol = false;
			this.setWait();
		}
		this.checkPlayerPosition(this.player.getRelativeX());
		if (this.min == STEP_FIRE) {
			this.setFire();
		}
		if (this.min < maxPatrol) {
			this.min++;
		}
		if (this.min == maxPatrol) {
			this.min = minPatrol;
		}
	}

	/**
	 * Set Enemy animation to DEAD, when the animation is finish, Enemy will be
	 * set to dead.
	 */
	@Override
	public void setDie() {
		if (!this.isDying()) {
			this.setAnimation(AnimationEnum.DEAD);
		}
		if (this.isLastFrame()) {
			this.dead = true;
		}
	}

	/**
	 * Manages the logic of movement of Enemy, the selection of the animations
	 * on the base of random values and call the update method to set the
	 * current frame. This method implement all the features of the Enemy
	 * Character artificial intelligence.
	 */
	@Override
	public void update() {
		super.checkHit(this);
		super.update();
		if (!this.isAlive()) {
			this.setDie();
		} else {
			if (this.player.getRelativeX() > super.getX() - this.attackRange
					&& this.player.getRelativeX() < super.getX() + this.attackRange) {
				this.thrust();
				if (super.isFiring()) {
					this.setFire();
				}
			} else {
				this.patrol();
				if (super.isFiring()) {
					this.setWait();
				}
			}
		}
	}

	@Override
	public void print(Graphics2D g) {
		g.drawImage(this.getFrame(this.getIndex()), this.getFrameX(this.background.getX()) + this.getX(), this.getY(),
				this.getFrameWidth(), this.getFrameHeight(), null);
	}

}
