package model;

import utility.Pair;

import java.util.Random;



/**
 * The Class to create boss enemy {@link BossEnemy} extends the abstract class {@link AbstarctEnemy}.
 */
public class BossEnemy extends AbstractEnemy{
	
	/** The Constant HIT. */
	private static final int HIT = GameImpl.ARENA_HEIGHT / 3;
	
	/** The Constant MYID. */
	private static final ID MYID = ID.BOSS_ENEMY;
	
	/** The Constant SPD. */
	private static final int SPD = 1;
	
	/** The Constant TIMESHOT. */
	private static final int TIMESHOT = 20;
	
	/** The Constant CMOVIM. */
	private static final int CMOVIM = 10;
	
	/** The game. */
	private final GameImpl game;
	
	/** The ran. */
	private final Random ran;
	
	/** The shotgun. */
	private int shotgun;
	
	/** The life. */
	private int life;
	
	/** The count. */
	private int count;
	
	/** The dir. */
	private DirEnemy dir;
	
	/**
	 * Classic constructor for {@link BossEnemy}.
	 *
	 * @param game {@link model.GameImpl}
	 */
	public BossEnemy(final GameImpl game) {
		super(new Pair<Integer,Integer>(0, 0), SPD, SPD, MYID, HIT);
		this.game = game;
		ran = new Random();
	}
	
	/**
	 * Method of {@link EnemyBehaviour}.
	 *
	 * @return {@link AbstractEnemy}.
	 */
	@Override
	public AbstractEnemy createThisEnemy() {
		if(this.isDead()) {
			this.setAlive();
		}
		this.life = 10;
		createEnemy();
		deleteList();
		setHitbox();
		dir = casualMovs();
		return this;
		
	}
	
	/**
	 * Method of {@link EntityImpl}
	 */
	@Override
	public void update() {
		shotgun++;
		if(count == CMOVIM) {
			dir = casualMovs();
			move(dir);
			count = 0;
		} else {
			count++;
			move(dir);
			dir = checkPosition(dir);
			}
		if(checkShotgun(shotgun, TIMESHOT)) {
			enemyShot(dir, game, MYID);
			shotgun = 0;
		}
	}
	
	/**
	 * Method of {@link model.EntityImpl}
	 *
	 * @param entity {@link model.Entity}
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
	
	/**
	 * Casual movs.
	 *
	 * @return the dir enemy
	 */
	@Override
	public DirEnemy casualMovs() {
		if (ran.nextBoolean()) {
			if (ran.nextBoolean()) {
				dir = DirEnemy.RIGHT;
			} else {
				dir = DirEnemy.LEFT;
			}
//			}else if(ran.nextBoolean()) {
//				dir=DirEnemy.DOWN;	
		}
		else {
			dir = DirEnemy.DOWN;
		}
			return dir;
	}
	
}
	

