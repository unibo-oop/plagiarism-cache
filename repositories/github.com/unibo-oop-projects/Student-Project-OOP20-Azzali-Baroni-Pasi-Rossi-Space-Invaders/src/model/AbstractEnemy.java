package model;

import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

import utility.Pair;

/**
 * The Abstract class implements {@link Enemy} to create entity for both type of enemies. {@link BasicEnemy}, {@link BossEnemy}.
 */
public abstract class AbstractEnemy extends EntityImpl implements EnemyBehaviour {
	
	/** The Constant WIDTH_X. */
	private static final int WIDTH_X = GameImpl.ARENA_WIDTH;
	
	/** The Constant HEIGHT_Y. */
	private static final int HEIGHT_Y = GameImpl.ARENA_HEIGHT;
	
	/** The Constant ENEMY_SPAWN_HEIGHT. */
	private static final int ENEMY_SPAWN_HEIGHT = 115;
	
	/** The model. */
	private final Enemy model;
	
	/** The hit. */
	private final int hit; 
	
	/** The done. */
	private boolean done;

	/**
	 * Constructor for {@link AbstarctEnemy}.
	 *
	 * @param position : Position of the Enemy.
	 * @param speedX : Speed's coordinate X.
	 * @param speedY : Speed's coordinate Y.
	 * @param id : ID of the enemy.
	 * @param hit : size of the Hitbox.
	 */
	public AbstractEnemy(final Pair<Integer, Integer> position, final int speedX, final int speedY, final ID id, final int hit) {
		super(position, speedX, speedY, id);
		this.hit = hit;
		done = false;
		model = new EnemyImpl(hit);
	}

	/**
	 * Method to create the enemy's coordinate X and Y
	 *
	 * @return the abstract enemy
	 */
	protected AbstractEnemy createEnemy() {
		double number;
		do {
			number = ThreadLocalRandom.current().nextDouble(0, WIDTH_X);
		} while ((number > WIDTH_X / 3 && number < (WIDTH_X * 2) / 3) || model.tiedupX((int) number));
		model.addNumber(true, (int) number);
		this.getPosition().setX((int) number);
		do {
			number = ThreadLocalRandom.current().nextDouble(0, HEIGHT_Y);
		} while ((number > HEIGHT_Y / 4 && number < (HEIGHT_Y * 3) / 4) || model.tiedupY((int) number));
		this.getPosition().setY(ENEMY_SPAWN_HEIGHT);
		done = false;
		return this;
	}

	/**
	 * Delete list in {@link EnemyImpl}.
	 */
	protected void deleteList() {
		if (!done) {
			model.deleteList();
			done = true;
		}
	}

	/**
	 * Sets the hitbox.
	 */
	protected void setHitbox() {
		setHitbox(new Rectangle(this.getPosition().getX() - (hit / 2 ), this.getPosition().getY() - (hit / 2), hit, hit));

	}	

	/**
	 * Method to create Enemy's Shot with {@link ShotEnemyImpl }.
	 *
	 * @param dir {@link DirEnemy}
	 * @param game {@link model.GameImpl}.
	 * @param id {@link model.GameImpl}.
	 */
	protected void enemyShot(final DirEnemy dir, final GameImpl game, final ID id) {
		if (id == ID.BOSS_ENEMY) {
			game.getShot().add(new ShotEnemyImpl(this.getPosition().getX(), this.getPosition().getY(), DirEnemy.D_R));
			game.getShot().add(new ShotEnemyImpl(this.getPosition().getX(), this.getPosition().getY(), DirEnemy.D_L));
			game.getShot().add(new ShotEnemyImpl(this.getPosition().getX(), this.getPosition().getY(), DirEnemy.DOWN));
		} else {
			game.getShot().add(new ShotEnemyImpl(this.getPosition().getX(), this.getPosition().getY(), DirEnemy.DOWN));
		}
	}

	/**
	 * Check shotgun.
	 *
	 * @param shotgun variable to shot
	 * @param timeShot time to shot
	 * @return boolean: true if the shot {@link Shot} can be create, false otherwise.
	 */
	protected boolean checkShotgun(final int shotgun, final int timeShot) {
		return shotgun == timeShot ? true : false;
	}

	/**
	 * Method to check direction  of the enemy and move it.
	 *
	 * @param dir {@link DirEnemy}.
	 */
	protected void move(final DirEnemy dir) {
		switch (dir) {
		case DOWN: this.getPosition().setY(this.getPosition().getY() + this.getSpeed().getY());
		break;
		case LEFT: this.getPosition().setX(this.getPosition().getX() - this.getSpeed().getX());
		break;
		case RIGHT: this.getPosition().setX(this.getPosition().getX() + this.getSpeed().getX());
		break;
		default:
			break;
		}
		setHitbox();
	}

	/**
	 * Check position of the enemy.
	 *
	 * @param dir {@link DirEnemy}.
	 * @return DirEnemy {@link DirEnemy}.
	 */
	protected DirEnemy checkPosition(final DirEnemy dir) {
		if (this.getPosition().getX() - hit <= 0) {
			return DirEnemy.RIGHT;
		} else if (this.getPosition().getX() + hit >= GameImpl.ARENA_WIDTH) {
			return DirEnemy.LEFT;
		} 

		setHitbox();
		return dir;

	}
	
	/**
	 * Game over.
	 *
	 * @param enemy the enemy
	 * @return the boolean
	 */
	public Boolean gameOver(AbstractEnemy enemy) {
		if(this.getPosition().getY() >= 900) {
			return true;
		}
		return false;
	}
}

