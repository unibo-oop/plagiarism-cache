package model.powerup;

import java.awt.Rectangle;

import controller.ChronometerEntity;
import controller.GameLoop;
import model.Entity;
import model.ID;
import model.PlayerImpl;
import utility.Pair;

/**
 * The Class PowerUpImpl implements powerUp.
 */
public abstract class PowerUpImpl extends ChronometerEntity implements PowerUp {
	
	/** The Constant WIDTH. */
	public static final int WIDTH = 60;
	
	/** The Constant HEIGHT. */
	public static final int HEIGHT = 60;

	/** The Constant LIFETIME_P. */
	public static final int LIFETIME_P = GameLoop.FPS * 6;

	/** The type. */
	private final PowerUpT type;
	
	/** The is activeted. */
	private boolean isActiveted;
	
	/** The player. */
	private PlayerImpl player;

	

	/**
	 * Instantiates a new power up impl.
	 *
	 * @param position the position
	 * @param veloX the velo X
	 * @param veloY the velo Y
	 * @param id the id
	 * @param type the type
	 */
	public PowerUpImpl(final Pair<Integer, Integer> position, final int veloX,final int veloY, final ID id, final PowerUpT type) {
		super(position, veloX, veloY, id, type.getLifetime());
		this.type = type;
		this.isActiveted = false;
		this.setHitbox(new Rectangle(this.getPosition().getX() - WIDTH / 2, this.getPosition().getY() - HEIGHT / 2, WIDTH, HEIGHT));
		
	}

	/**
	 * Insert strategy.
	 */
	@Override
	public void InsertStrategy() {
		if(this.isActiveted == false || this.getType().isRequiringUpdate()) {
			this.InsertEffect();
			this.isActiveted = true;
		}
		
	}
	
	/**
	 * Insert effect.
	 */
	protected abstract void InsertEffect();


	/**
	 * Reset.
	 */
	@Override
	public abstract void reset();
	


	/**
	 * Checks if is activated.
	 *
	 * @return true, if is activated
	 */
	@Override
	public boolean isActivated() {
		return this.isActiveted;
	}



	/**
	 * Update.
	 */
	@Override
	public void update() {
		if(this.isActiveted == false){
			this.getPosition().setY(this.getPosition().getY() + this.getSpeed().getY());
			this.setHitbox(new Rectangle(this.getPosition().getX() - WIDTH / 2, this.getPosition().getY()- HEIGHT / 2,WIDTH,HEIGHT));
		}else {
			this.setS();
			if(this.isEnded() == false) {
				this.tick();
				this.InsertStrategy();
			}else {
				this.reset();
				this.setDead();
			}
		}
		
	}
	
	/**
	 * Sets the S.
	 * set the position and the hitbox of the powerUp
	 */
	protected abstract void setS();
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public final PowerUpT getType() {
		return this.type;
	}
	

	/**
	 * Collide.
	 *
	 * @param entity the entity
	 */
	@Override
	public void collide(final Entity entity) {
		if(entity instanceof PlayerImpl) {
			this.player = (PlayerImpl)entity;
			this.InsertStrategy();;
			this.setPosition(entity.getPosition());
			this.setHitbox(entity.getHitbox());
		}
			
		
	}
	
	/**
	 * Gets the entity strategy.
	 *
	 * @return the entity strategy
	 */
	public final PlayerImpl getEntityStrategy() {
		return this.player;
	}


}
