package elements;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Player is a DECORATION of {@link Character}. This Class represent the
 * principal Character of the game. During the game, the player can run along
 * the map without going beyond the limits and eliminating the opponents
 * ({@link EnemyGun}) by firing. In order to dodge enemy bullets the player can
 * fly for a short time.
 * 
 * @author Luca
 */
public class Player extends Character {

	private static final int NAME_X = 20;
	private static final int NAME_Y = 5;
	private static final int LIFEPOINTS_X = 20;
	private static final int LIFEPOINTS_Y = 20;
	private static final int LIFEPOINTS_OFFSET = 2;

	private static final String TEXT_NAME = "Name";
	private static final String TEXT_LIFEPOINTS = "LifePoints";

	private static final int STEP = 2;
	private static final int MIN_Y_STEP = 0;
	private static final int MAX_Y_STEP = 26;
	private static final int STEP_FLY = 2;
	private static final int FIRE_BULLET_INDEX = 12;
	private static final int BULLET_DAMAGE = 100;
	private static final int BULLET_SPEED = 4;

	private final Background background;
	private int minY;
	private int maxY;

	/**
	 * This builder call the Superclass builder and initialize the variables to
	 * manage the "FLY" animation. It also get the instance of
	 * {@link Background}.
	 * 
	 * @see elements.Character#Character(int, int, int, int, Map)
	 * 
	 * @param x
	 *            a int value for X coordinate of Character.
	 * @param y
	 *            a int value for Y coordinate of Character.
	 * @param background
	 *            a {@link Background} instance.
	 * @param animations
	 *            a Map contains all the possible animations of the Character.
	 * @param bulletList
	 *            a List contains all bullet fired.
	 */
	public Player(final int x, final int y, final Background background,
					final Map<String, List<BufferedImage>> animations, final List<Bullet> bulletsList) {
		super(x, y, animations, bulletsList);
		this.background = background;
		this.minY = MIN_Y_STEP;
		this.maxY = MAX_Y_STEP;
	}

	/**
	 * Return the value of X coordinate of Player considering the relative
	 * position.
	 * 
	 * @return a int value of X relative coordinate.
	 */
	public int getRelativeX() {
		return this.getX() + this.background.getWorldLimitLeft();
	}

	/**
	 * Manages the logic of selection of the animations on the base of the keys
	 * pressed and call the update method to set the current frame. If the
	 * Player is not doing an action animation and there's no key pressed, the
	 * current animation will be set on WAIT. The Player can stop fire animation
	 * by flying to dodge the bullets. The animation can have different
	 * timeClock. It also check if the Player can move in base of the world X
	 * limits, if the Player reach the limit, it can't go on.
	 * 
	 * @param keys
	 *            a Set of Integer, contains the value of keys currently
	 *            pressed.
	 */
	public void update(final Set<Integer> keys) {
		super.checkHit(this);
		super.update();
		// RUN animation.
		if (keys.contains(KeyEvent.VK_D) || keys.contains(KeyEvent.VK_A)) {
			super.setTimeClockUpdate(70);
			super.turn(keys.contains(KeyEvent.VK_A), keys.contains(KeyEvent.VK_D));
			if (this.checkWorldLimit()) {
				this.moveX(STEP);
			} else {
				if (!this.isFlying()) {
					this.setWait();
				}
			}
		}
		// FIRE animation.
		if (super.isFiring() || keys.contains(KeyEvent.VK_CONTROL) && !this.isFlying()) {
			super.setTimeClockUpdate(40);
			this.setFire();
			if (super.isWaiting()) {
				keys.remove(KeyEvent.VK_CONTROL);
			}
		}
		// FLY animation.
		if (this.isFlying() || keys.contains(KeyEvent.VK_W)) {
			super.setTimeClockUpdate(50);
			this.setFly();
			if (super.isWaiting()) {
				keys.remove(KeyEvent.VK_CONTROL);
			}
		}
		// reset player animation.
		if (!this.isFlying() && !super.isFiring() && keys.isEmpty()) {
			super.setTimeClockUpdate(70);
			super.setWait();
		}
	}

	/**
	 * Indicates if the Player reach the limit of the world;
	 * 
	 * @return a boolean, return true if the Character didn't reach the world
	 *         limit, otherwise false.
	 */
	private boolean checkWorldLimit() {
		if (this.isTurnedLeft() && this.getRelativeX() > this.background.getWorldLimitLeft()
				|| this.isTurnedRight() && this.getRelativeX() < this.background.getWorldLimitRight()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void print(Graphics2D g) {
		// Print Name and LifePoints bar:
		g.drawImage(this.getResources().getTextImage().get(TEXT_NAME), NAME_X, NAME_Y,
					this.getResources().getTextImage().get(TEXT_NAME).getWidth(),
					this.getResources().getTextImage().get(TEXT_NAME).getHeight(), null);
		
		for (int i = 0; i < this.getLifePoints(); i++) {
			g.drawImage(this.getResources().getTextImage().get(TEXT_LIFEPOINTS), (i * LIFEPOINTS_OFFSET) + LIFEPOINTS_X,
						LIFEPOINTS_Y, this.getResources().getTextImage().get(TEXT_LIFEPOINTS).getWidth(),
						this.getResources().getTextImage().get(TEXT_LIFEPOINTS).getHeight(), null);
		}
		// print Player:
		g.drawImage(this.getFrame(this.getIndex()), this.getFrameX(this.background.getWorldLimitLeft()), this.getY(),
					this.getFrameWidth(), this.getFrameHeight(), null);
	}

	/**
	 * If Character is not firing, move the Character on X axe by adding a value
	 * to current X position, If it's flying, the animation will not change to
	 * RUN, but it can still move left or right direction.
	 * 
	 * @param value
	 *            a int contains the value to add.
	 */
	@Override
	public void moveX(final int value) {
		if (!super.isFiring()) {
			if (!this.isFlying()) {
				super.setAnimation(AnimationEnum.RUN);
			}
			int x = super.isTurnedLeft() ? -1 : 1;
			super.setX(super.getX() + value * x);
			// update background position:
			this.background.moveBackground(value * x);
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
		if (this.fireBullet && super.getIndex() == FIRE_BULLET_INDEX) {
			super.bulletsList.add(new Bullet(this, this.background, this.getRelativeX(), super.getY(), true,
									BULLET_DAMAGE, BULLET_SPEED, super.animationsMap));
			super.fireBullet = false;
		}
		if (this.isLastFrame()) {
			this.setWait();
		}
	}

	/**
	 * Indicates if the Player is flying.
	 * 
	 * @return a boolean that indicate if the Character is flying.
	 */
	private boolean isFlying() {
		return super.getAnimation() == AnimationEnum.FLY;
	}

	/**
	 * Set Character animation to FLY, if the animation is finish, it will be
	 * set to WAIT.
	 */
	private void setFly() {
		super.setAnimation(AnimationEnum.FLY);
		if (this.maxY == MAX_Y_STEP && this.minY < MAX_Y_STEP) {
			this.moveY(-STEP_FLY);
			this.minY++;
		}
		if (this.minY == MAX_Y_STEP && this.maxY > MIN_Y_STEP) {
			this.moveY(STEP_FLY);
			this.maxY--;
		}
		if (this.maxY == MIN_Y_STEP && this.minY == MAX_Y_STEP) {
			this.minY = MIN_Y_STEP;
			this.maxY = MAX_Y_STEP;
			super.setWait();
		}
	}

	@Override
	public String toString() {
		return "[ Element ]\t[ X: " + this.getRelativeX() + " ]\t[ Y: " + this.getY() + " ]";
	}
}