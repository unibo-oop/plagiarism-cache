package model;

import java.awt.Rectangle;


import utility.Pair;

/**
 * Class to create the enemies's shots, implements the interface {@link ShotEnemy}.
 *
 */
public class ShotEnemyImpl extends EntityImpl implements ShotEnemy {
	
	/** The Constant MYID. */
	private static final ID MYID = ID.ENEMY_BULLET;
	
	/** The Constant STDSPD. */
	private static final int STDSPD = 3;
	
	/** The Constant HIT. */
	private static final int HIT = GameImpl.ARENA_HEIGHT / 25;
	
	/** The spd. */
	private final Pair<Integer, Integer> spd;
	
	/** The dir. */
	private DirEnemy dir;
	
	/**
	 * Constructor for shot enemy class {@link ShotEnemyImpl}.
     * @param enemyX Integer: Coordinate X of the enemy that called this class.
     * @param enemyY Integer: Coordinate Y of the enemy that called this class.
     * @param dir DirEnemy: Direction of the enemy that called this class.
     */
	public ShotEnemyImpl(final Integer enemyX, final Integer enemyY, final DirEnemy dir) {
		super(new Pair<Integer, Integer>(enemyX, enemyY), 0, 0, MYID);
		this.dir = dir;
		this.spd = new Pair<Integer, Integer>(0, 0);
		whichSpeed();
		setSpeed(spd.getX(), spd.getY());
		setHitbox(new Rectangle(this.getPosition().getX() - (HIT / 2), this.getPosition().getY() - (HIT / 2), HIT, HIT));
	}
	
    /**
     * Method to check the direction of the shot and to set the speed.
     */
	private void whichSpeed() {
		switch (dir) {
		case DOWN: spd.setX(0);
		spd.setY(+STDSPD);
		break;
		case D_R: spd.setX(STDSPD);
		spd.setY(+STDSPD);
		break;
		case D_L: spd.setX(-STDSPD);
		spd.setY(+STDSPD);
		break;
		default:
			break;
		}
	}
	
	/**
	 * Method of {@link model.EntityImpl}.
	 */
	@Override
	public void update() {
		whichSpeed();
		setSpeed(spd.getX(), spd.getY());
		this.setPosition(new Pair<Integer,Integer>(this.getPosition().getX() + spd.getX(), this.getPosition().getY() + spd.getY()));
		setHitbox(new Rectangle(this.getPosition().getX() - (HIT / 2), this.getPosition().getY() - (HIT / 2), HIT, HIT));
		if (this.getPosition().getY() >= GameImpl.ARENA_HEIGHT || this.getPosition().getY() < 0 || this.getPosition().getX() >= GameImpl.ARENA_WIDTH || this.getPosition().getX() < 0) {
			this.setDead();
		}
		
	}
	
	/**
	 * Method of {@link model.EntityImpl}.
	 *
	 * @param entity {@link Entity}
	 */
	@Override
	public void collide(final Entity entity) {
		this.setDead();
	}
	
	/**
	 * Method of {@link ShotEnemy}
	 *
	 * @param dir {@link DirEnemy}
	 */
	@Override
	public void setDir(final DirEnemy dir){
		this.dir = dir;
		
	}

}
