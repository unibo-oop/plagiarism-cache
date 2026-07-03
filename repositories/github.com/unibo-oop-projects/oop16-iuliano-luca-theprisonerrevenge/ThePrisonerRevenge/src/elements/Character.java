package elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import resources.LoadResources;
import view.ViewImpl;

/**
 * Character extend {@link ElementImpl}. It represent the Character type in this
 * game, It include all the attributes of the Element like the life points or
 * the position coordinates of Character in the map, a list with all the
 * possible animation and the frequency to update the frames.
 * 
 * @author Luca
 */
public abstract class Character extends ElementImpl {

	private static final int LIFE_POINTS = 100;

	private int timeClockUpdate = 70;
	private AnimationEnum animation;
	private long timer;
	private int index;
	private int lifePoints;

	protected static int HITBOX = LoadResources.SP_WIDTH / ViewImpl.SCALE;
	protected final Map<String, List<BufferedImage>> animationsMap;
	protected final List<Bullet> bulletsList;
	protected boolean fireBullet;
	protected boolean dead;


	/**
	 * Build all the attributes of a Character.
	 * 
	 * @param x
	 *            a int value for X coordinate of Character.
	 * @param y
	 *            a int value for Y coordinate of Character.
	 * @param animationsMap
	 *            a Map contains all the possible animations of the Character.
	 * @param bulletList
	 *            a List contains all bullet fired.
	 */
	public Character(final int x, final int y, final Map<String, List<BufferedImage>> animationsMap, final List<Bullet> bulletsList) {
		super(x, y);
		this.lifePoints = LIFE_POINTS;
		this.animationsMap = animationsMap;
		this.bulletsList = bulletsList;
		this.animation = AnimationEnum.WAIT;
		this.fireBullet = false;
		this.dead = false;
		timer = System.nanoTime(); // timer test
	}

	/**
	 * If Character is not firing, turn the Character in the required direction
	 * by calling the turn method of super Class
	 * {@link ElementImpl#turn(boolean, boolean)}
	 */
	@Override
	public void turn(final boolean turnLeft, final boolean turnRight) {
		if (!this.isFiring()) {
			super.turn(turnLeft, turnRight);
		}
	}

	/**
	 * Indicates if the Character is "alive".
	 * 
	 * @return a boolean that indicate if the Character is alive, true if
	 *         lifePoints > 0 otherwise false.
	 */
	public boolean isAlive() {
		return this.lifePoints > ZERO;
	}

	/**
	 * Indicates if the Character is dying.
	 * 
	 * @return a boolean that indicate if the Character is dead.
	 */
	public boolean isDying() {
		return this.getAnimation() == AnimationEnum.DEAD;
	}

	/**
	 * Indicates if the Character is waiting.
	 * 
	 * @return a boolean that indicate if the Character is waiting.
	 */
	public boolean isWaiting() {
		return this.getAnimation() == AnimationEnum.WAIT;
	}

	/**
	 * Indicates if the Character is firing;
	 * 
	 * @return a boolean that indicate if the Character is firing.
	 */
	public boolean isFiring() {
		return this.getAnimation() == AnimationEnum.FIRE;
	}

	/**
	 * Indicates if the Character is running;
	 * 
	 * @return a boolean that indicate if the Character is running.
	 */
	public boolean isRunning() {
		return this.getAnimation() == AnimationEnum.RUN;
	}

	/**
	 * Set Character animation to DEAD.
	 */
	public void setDie() {
		this.setAnimation(AnimationEnum.DEAD);
	}

	/**
	 * Set Character animation to FIRE.
	 */
	public void setFire() {
		this.setAnimation(AnimationEnum.FIRE);
	}

	/**
	 * Set Character animation to WAIT.
	 */
	public void setWait() {
		this.setAnimation(AnimationEnum.WAIT);
	}

	/**
	 * Set Character animation to RUN only if the current animation is not
	 * already set on RUN.
	 */
	public void setRun() {
		this.setAnimation(AnimationEnum.RUN);
	}

	/**
	 * Set LifePoints of this Character after get damage.
	 * 
	 * @param value
	 *            a int contains the value to subtract from life points.
	 */
	public void sufferDamage(final int damange) {
		this.lifePoints = this.lifePoints - damange;
	}

	/**
	 * This method set index to specific value.
	 * 
	 * @param index
	 *            a int contains the value set index.
	 */
	public void setIndex(final int index) {
		if (index >= 0 && index < this.getAnimationList().size() - 1) {
			this.index = index;
		} else {
			this.index = 0;
		}
	}

	/**
	 * Character's Frame X position, if the current Frame is more big of a
	 * single tile, the X coordinate will be set on correct position considering
	 * the Frame width. This method also considers the relative position of X
	 * respect the Frame.
	 * 
	 * @param relativeX
	 *            a int contains the value of the relative X position.
	 * @return a int value of fixed X coordinate.
	 */
	public int getFrameX(final int relativeX) {
		return (this.isTurnedLeft()) ? relativeX + LoadResources.SP_WIDTH : relativeX;
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
	 * If the index of current animation is set on the last frame return true,
	 * otherwise false.
	 * 
	 * @return true if it's the last frame, otherwise false.
	 */
	public boolean isLastFrame() {
		return this.getIndex() == this.getAnimationList().size() - 1;
	}

	/**
	 * Get the current frame as BufferedImage in a specific animation from the
	 * animationsMap only if the index is valid.
	 * 
	 * @return a BufferedImage frame.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of bounds.
	 */
	public BufferedImage getFrame(final int index) throws IndexOutOfBoundsException {
		if (index >= 0 && index < this.getAnimationList().size()) {
			return this.animationsMap.get(this.animation.getAnimation()).get(index);
		} else {
			throw new IndexOutOfBoundsException("ERROR! Index out of bounds.");
		}
	}

	/**
	 * Return current Frame's width, if character is turned left, this value
	 * will be inverted to be mirrored at print fase.
	 * 
	 * @return a int value represent the width of the current Frame.
	 */
	public int getFrameWidth() {
		return (this.isTurnedLeft()) ? this.getFrame(this.getIndex()).getWidth() * -1
				: this.getFrame(this.getIndex()).getWidth();
	}

	/**
	 * Return current Frame's height.
	 * 
	 * @return a int value represent the height of the current Frame.
	 */
	public int getFrameHeight() {
		return this.getFrame(this.getIndex()).getHeight();
	}

	/**
	 * Return a int value of life points of this Character.
	 * 
	 * @return a int value represent the life points.
	 */
	public int getLifePoints() {
		return lifePoints;
	}

	/**
	 * If Character is not firing, move the Character on X axe by adding a value
	 * to current X position.
	 * 
	 * @param value
	 *            a int contains the value to add.
	 */
	public void moveX(final int value) {
		if (!this.isFiring()) {
			this.setRun();
			int x = this.isTurnedLeft() ? -1 : 1;
			this.setX(this.getX() + value * x);
		}
	}

	/**
	 * If Character is not firing, move the Character on Y axe by adding a value
	 * to current Y position.
	 * 
	 * @param value
	 *            a int contains the value to add.
	 */
	public void moveY(final int value) {
		if (!this.isFiring()) {
			this.setY(this.getY() + value);
		}
	}

	/**
	 * Sets current Character's animation in a specific state only if is not
	 * already set on the animation passed in argument.
	 * 
	 * @param animation
	 *            a {@link AnimationEnum} contains the name of current animation
	 *            to set.
	 */
	protected void setAnimation(final AnimationEnum animation) {
		if (this.animation != animation) {
			this.setIndex(0);
		}
		this.animation = animation;
	}

	/**
	 * Get current animation List.
	 * 
	 * @return a List of BufferedImage who contains the frames of current
	 *         Character's animation.
	 */
	public List<BufferedImage> getAnimationList() {
		return this.getAnimation(this.animation);
	}

	/**
	 * Get current animation name.
	 * 
	 * @return a {@link AnimationEnum} with the name of the current Character's
	 *         animation.
	 */
	public AnimationEnum getAnimation() {
		return this.animation;
	}

	/**
	 * Character animations frames.
	 * 
	 * @param animation
	 *            a {@link AnimationEnum} contains the name of the animation
	 *            type; "Run", "Fire" ...
	 * @return a List of BufferedImage who contains the frames of specific type
	 *         of animation.
	 */
	private List<BufferedImage> getAnimation(final AnimationEnum animation) {
		return this.animationsMap.get(animation.getAnimation());
	}

	/**
	 * Set the timeClock frequency to update of the index who manage the frame
	 * step.
	 * 
	 * @param value
	 *            a int contains the value to set.
	 */
	protected void setTimeClockUpdate(final int value) {
		if (this.timeClockUpdate != value) {
			this.timeClockUpdate = value;
		}
	}

	@Override
	public void update() {
		/**
		 * Increment index N times/second, the frequency of this increment it's
		 * set by the timeClockUpdate variable. The timer will be reset at every
		 * increment, at the end of the animation list, the index will be reset
		 * to.
		 */
		if ((System.nanoTime() - this.timer) / 1000000 >= this.timeClockUpdate) {
			if (index >= 0 && index < this.getAnimationList().size() - 1) {
				this.index++;
			} else {
				this.index = 0;
			}
			this.timer = System.nanoTime();
		}
	}

	@Override
	void print(Graphics2D g) {
		g.drawImage(this.getFrame(this.getIndex()), this.getX(), this.getY(), this.getFrameWidth(),
				this.getFrameHeight(), null);
	}

	/**
	 * Check if Characters get hit by a bullet, this method also prevent
	 * "friendly fire": Only if the Bullet fired by the Player hits an Enemy, or
	 * if the Bullet fired by Enemy hits the Player, the current Character will
	 * lose lifePoint and the bullet will be removed. If the current Character
	 * is a Player and it is flying, it cam't be hit. If the Player or Enemy
	 * didn't get hit by the bullet, it will be removed when it will reach the
	 * end of the map.
	 * 
	 * @param character
	 *            the Character who is checking the Hits.
	 */
	public void checkHit(final Character character) {
		final boolean isPlayer = character instanceof Player;
		final int x = isPlayer ? ((Player) character).getRelativeX() : ((EnemyGun) character).getX();

		for (int i = 0; i < this.bulletsList.size(); i++) {
			if ((this.bulletsList.get(i).getX() + HITBOX) > x && (this.bulletsList.get(i).getX()) < (x + HITBOX)) {
				if (!isPlayer && this.bulletsList.get(i).isPlayerBullet() 
					|| isPlayer && !this.bulletsList.get(i).isPlayerBullet()
					&& character.getY() >= this.bulletsList.get(i).getY() - Bullet.BULLET_Y) {				
					
					this.sufferDamage(this.bulletsList.get(i).getDamage());
					this.bulletsList.remove(i);
				}
			} else {
				if (this.bulletsList.get(i).getX() < this.bulletsList.get(i).getRange(Character.LEFT) 
					|| this.bulletsList.get(i).getX() > this.bulletsList.get(i).getRange(Character.RIGHT)) {
					
					this.bulletsList.remove(i);
				}
			}
		}
	}

	/**
	 * Character informations.
	 * 
	 * @return a String with all the principal information about this Character.
	 */
	@Override
	public String toString() {
		return "[ CHARACTER ]" + "\t[ X: " + this.getX() + " ]\t[ Y: " + this.getY() + " ]\t[ lifePoints: "
				+ this.getLifePoints() + " ]\t[ AnimationEnum: " + this.animation.getAnimation() + " ]\t[ index: "
				+ this.index + " ]\t[ Turned Left: " + this.isTurnedLeft() + " ]\t[ Turned Right: "
				+ this.isTurnedRight() + " ]";
	}
}
