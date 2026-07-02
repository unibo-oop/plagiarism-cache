package model;

import java.awt.Rectangle;

import utility.Clamp;
import utility.Pair;

/**
 * The Class PlayerImpl that manages the creation of the player.
 */
public final class PlayerImpl extends EntityImpl implements Player{

	/** The Constant DAMAGE_MULTIPLIER. */
	private static final int DAMAGE_MULTIPLIER = 2;//10
    
    /** The Constant WIDTH. */
    private static final int WIDTH = GameImpl.ARENA_WIDTH / 20;
    
    /** The Constant HEIGHT. */
    private static final int HEIGHT = WIDTH + WIDTH / 100;
    
    /** The Constant DEFINITLY_NOT_SHOT_COOLDOWN. */
    private static final int DEFINITLY_NOT_SHOT_COOLDOWN = 20;
    
    /** The health. */
    private int health = 100;
    
    /** The shoot CD. */
    private int shootCD = DEFINITLY_NOT_SHOT_COOLDOWN;
    
    /** The shot ready. */
    private int shotReady;
    
    /** The shield. */
    private int shield;
    
    /** The y inizial. */
    private static int Y_INIZIAL = 900;
    
    /** The game impl. */
    private final GameImpl gameImpl;

    
    /**
     * Instantiates a new player impl.
     *
     * @param id the id
     * @param gameImpl the game impl
     */
    public PlayerImpl(final ID id, final GameImpl gameImpl) {
    	super(new Pair<Integer, Integer>(GameImpl.ARENA_WIDTH / 2, Y_INIZIAL), 0, 0, id);
    	this.gameImpl = gameImpl;
    	setHitbox(new Rectangle(this.getPosition().getX(),this.getPosition().getY(),WIDTH,HEIGHT));
    }

	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	@Override
	public int getHealth() {
		return this.health;
	}

	/**
	 * Sets the health.
	 *
	 * @param lifePoints the new health
	 */
	@Override
	public void setHealth(int lifePoints) {
		this.health = lifePoints;
	}

	/**
	 * Gets the cool down.
	 *
	 * @return the cool down
	 */
	@Override
	public int getCoolDown() {
		return this.shootCD;
	}

	/**
	 * Sets the cool down.
	 *
	 * @param coolDown the new cool down
	 */
	@Override
	public void setCoolDown(int coolDown) {
		this.shootCD = coolDown;
	}

	/**
	 * Gets the shield.
	 *
	 * @return the shield
	 */
	@Override
	public int getShield() {
		// TODO Auto-generated method stub
		return this.shield;
	}

	/**
	 * Sets the s hield.
	 *
	 * @param shieldLife the new s hield
	 */
	@Override
	public void setSHield(int shieldLife) {
		this.shield = shieldLife;		
	}

	/**
	 * Gets the shot ready.
	 *
	 * @return the shot ready
	 */
	@Override
	public int getShotReady() {
		// TODO Auto-generated method stub
		return this.shotReady;
	}

	/**
	 * Sets the shot ready.
	 *
	 * @param time the new shot ready
	 */
	@Override
	public void setShotReady(int time) {
		this.shotReady = time;
	}

	/**
	 * Reset position.
	 */
	@Override
	public void resetPosition() {
		this.setPosition(new Pair<Integer, Integer>(GameImpl.ARENA_WIDTH / 2, Y_INIZIAL));
	}

	/**
	 * Update.
	 */
	@Override
	public void update() {
		this.getPosition().setX(this.getPosition().getX() + this.getSpeed().getX());
		this.getPosition().setY(this.getPosition().getY() + this.getSpeed().getY());
		
		this.getPosition().setX(Clamp.clamp(this.getPosition().getX(), WIDTH / 2, GameImpl.ARENA_WIDTH - WIDTH / 2));
        this.getPosition().setY(Clamp.clamp(this.getPosition().getY(), HEIGHT / 2, GameImpl.ARENA_HEIGHT - HEIGHT / 2));

    this.setHitbox(new Rectangle(this.getPosition().getX() - WIDTH / 2, this.getPosition().getY() - HEIGHT / 2, WIDTH, HEIGHT));
	}

	/**
	 * Collide.
	 *
	 * @param entity the entity
	 */
	@Override
	public void collide(Entity entity) {
		if (this.shield == 0) {
			this.health -= DAMAGE_MULTIPLIER * gameImpl.getLevel();
		} else if (this.shield > 0 && this.shield < DAMAGE_MULTIPLIER * gameImpl.getLevel()) {
            final int tempDamageOverShieldValue = DAMAGE_MULTIPLIER * gameImpl.getLevel() - this.shield;
            this.health -= tempDamageOverShieldValue;
            this.shield = 0;
        } else {
            this.shield -= DAMAGE_MULTIPLIER * gameImpl.getLevel();
        }
		
		if (this.health <= 0) {
        this.setDead();
        }
	}
	
}
