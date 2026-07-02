package model;

import java.util.Random;

import utility.Pair;

/**
 * The Class to create a Basic enemy {@link BasicEnemy} extend the abstract class {@link AbstactEnemy}.
 */
public class BasicEnemy extends AbstractEnemy {
	
	/** The Constant HIT. */
	private static final int HIT = GameImpl.ARENA_HEIGHT / 12;
	
	/** The Constant MYID. */
	private static final ID MYID = ID.BASIC_ENEMY;
	
	/** The Constant SPD. */
	private static final int SPD = 1;
	
	/** The Constant TIMESHOT. */
	private static final int TIMESHOT = 150;
	
	/** The Constant CMOVIM. */
	private static final int CMOVIM = 50;
	
	/** The game. */
	private final GameImpl game;
	
	/** The ran. */
	private final Random ran;
	
	/** The shotgun. */
	private int shotgun;
	
	/** The life. */
	private int life = 3;
	
	/** The count. */
	private int count;
	
	/** The done. */
	private boolean done;
	
	/** The dir. */
	private DirEnemy dir;
	
	/**
	 * Constructor for {@link BasicEnemy}.
	 *
	 * @param game the game
	 */
	public BasicEnemy(final GameImpl game) {
		super(new Pair<Integer, Integer> (0, 0), SPD, SPD, MYID, HIT);
		this.game = game;
		this.done = false;
		ran = new Random();
	}
	
	/**
	 * Method of {@link Enemy}.
	 *
	 * @return {@link AbstarctEnemy}.
	 */
	@Override
	public AbstractEnemy createThisEnemy() {
		createEnemy();
		setHitbox();
		dir = casualMovs();
		return this;
		
	}
	
	/**
	 * Method of {@link EnemyBehaviour}.
	 *
	 * @return {@link DirEnemy}.
	 *  */
	@Override
	public DirEnemy casualMovs() {
		if (ran.nextBoolean()) {
			if (ran.nextBoolean()) {
				dir = DirEnemy.RIGHT;
			} else {
				dir = DirEnemy.LEFT;
			}
		}
		else {
			dir = DirEnemy.DOWN;
		}
			return dir;
		
	} 
	
	/**
	 * Method of {@link EnemyBehaviour}.
	 */
	@Override
	public void update() {
		shotgun++;
		if(!done) {
			deleteList();
			done = true;
		}
		if(count == CMOVIM) {
			dir = casualMovs();
			move(dir);
			count = 0;
		} else {
			count++;
			move(dir);
			dir = checkPosition(dir);
			}
		if (checkShotgun(shotgun, TIMESHOT)) {
			enemyShot(dir, game, MYID);
			shotgun = 0;
		}
	}
	
	/**
	 * 	Method of {@link model.EntityImpl}.
	 * @param entity {@link mode.Entity}.
	 */
	@Override
	public void collide (final Entity entity) {
		switch (entity.getID()) {
		case BASIC_ENEMY: case ENEMY_BULLET:
			break;
			default:
				life--;
		}
		if (life == 0) {
			this.setDead();
		}
	}
	
	
}
	 

		
	

