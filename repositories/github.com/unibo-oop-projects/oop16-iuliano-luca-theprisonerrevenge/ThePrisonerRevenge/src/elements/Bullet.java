package elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import resources.LoadResources;

/**
 * This Class extend {@link ElementImpl}. It represent the Bullet type, All
 * Bullets deal damage points when an {@link EnemyGun} or the {@link Player} get
 * hit.
 * 
 * @author Luca
 */
public class Bullet extends ElementImpl {

	public static final int BULLET_Y = LoadResources.SP_HEIGHT / 2;
	private static final int TIME_CLOCK = 50;
	private static final int RANGE = 300;
	private static final String BULLET = "Bullet";

	private final Map<String, List<BufferedImage>> animations;
	private final Character character;
	private final Background background;
	private int bulletSpeed;
	private int index;
	private int damage;
	private int startX;
	private boolean isPlayerBullet;
	private long timer; // timer update frame.

	/**
	 * This builder get the instances of the Character who is fire this bullet.
	 * It initialize the coordinates and the damage who will deal.
	 * 
	 * @param character
	 *            a Character instance.
	 * @param background
	 *            a Background instance.
	 * @param x
	 *            a int value for X coordinate of Bullet at start.
	 * @param y
	 *            a int value for Y coordinate of Bullet at start
	 * @param alive
	 *            a boolean represent if this bullet is "alive".
	 * @param damange
	 *            a int value represent the damage deal by this bullet.
	 * @param bulletSpeed
	 *            a int value represent bullet's speed.
	 * @param animations
	 *            a Map contains all the possible animations of the Character.
	 */
	public Bullet(final Character character, final Background background, final int x, final int y, final boolean alive,
			final int damange, final int bulletSpeed, final Map<String, List<BufferedImage>> animations) {
		super(x, y);
		super.turnedSide = character.isTurnedLeft() ? LEFT : RIGHT;
		super.setX(this.setFrameX(x));
		this.startX = super.getX();
		this.character = character;
		this.isPlayerBullet = this.character instanceof Player;
		this.background = background;
		this.animations = animations;
		this.damage = damange;
		this.bulletSpeed = bulletSpeed;
	}

	/**
	 * Return the bullet range.
	 * 
	 * @param side
	 *            a boolean represent the direction of range.
	 * @return a int value that is the range of the bullet on left or right
	 *         direction.
	 */
	public int getRange(final boolean side) {
		return (side == RIGHT) ? this.startX - Character.HITBOX + RANGE : this.startX - Character.HITBOX - RANGE;
	}

	/**
	 * Return if this Bullet is fired by Player.
	 * 
	 * @return a boolean value, true if the bullet is fired by Player otherwise
	 *         false if this bullet is fired by Enemy.
	 */
	public boolean isPlayerBullet() {
		return this.isPlayerBullet;
	}

	/**
	 * Set the Bullet frame X position.
	 * 
	 * @param x
	 *            a int contains position of the Character.
	 * @return a int value that is the correct position of X in base of the
	 *         turned side of Character.
	 */
	public int setFrameX(final int x) {
		if (super.isTurnedLeft()) {
			return x - LoadResources.SP_WIDTH;
		} else {
			return x + LoadResources.SP_WIDTH;
		}
	}

	/**
	 * Get the damage of this bullet.
	 * 
	 * @return a int value as damage point of this bullet.
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Get current animation.
	 * 
	 * @return a List of BufferedImage who contains the frames of current
	 *         Bullet's animation.
	 */
	public List<BufferedImage> getBulletList() {
		return this.animations.get(BULLET);
	}

	/**
	 * Return the index of the frame in the specific animation.
	 * 
	 * @return a int value of index of frame.
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * @return the current frame like BufferedImage.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of bounds.
	 */
	public BufferedImage getFrame(final int index) throws IndexOutOfBoundsException {
		if (index >= 0 && index < this.getBulletList().size()) {
			return this.animations.get(BULLET).get(index);
		} else {
			throw new IndexOutOfBoundsException("ERROR! Index out of bounds.");
		}
	}

	/**
	 * Increment index N times/second, the frequency of this increment it's set
	 * by the static variable TIME_CLOCK. The timer will be reset at every
	 * increment, at the end of the animation list, the index will be reset to.
	 */
	@Override
	public void update() {
		if ((System.nanoTime() - timer) / 1000000 >= TIME_CLOCK) {
			if (index >= 0 && index < this.getBulletList().size() - 1) {
				this.index++;
			} else {
				this.index = 0;
			}
			this.timer = System.nanoTime();
		}
		if (super.isTurnedLeft()) {
			super.setX(super.getX() - bulletSpeed);
		} else {
			super.setX(super.getX() + bulletSpeed);
		}
	}

	@Override
	public void print(Graphics2D g) {
		g.drawImage(this.getFrame(this.index), this.getX() + this.background.getX(), this.getY(), null);
	}

	@Override
	public String toString() {
		return "[ BULLET ]" + "\t[ X: " + this.getX() + " ]\t[ Y: " + this.getY() + " ]\t[ index: " + this.index
				+ " ]\t[ Turned Left: " + this.isTurnedLeft() + " ]\t[ Turned Right: " + this.isTurnedRight() + " ]";
	}
}
