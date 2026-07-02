package model.powerup;

import controller.PowerUp.Strategy;
import model.ID;
import model.Player;
import model.PlayerImpl;
import utility.Clamp;
import utility.Pair;

/**
 * The Class PPowerUp for the player.
 */
public class PPowerUp extends PowerUpImpl{
	
	/** The Constant STANDARD_HEALTH. */
	private static final int STANDARD_HEALTH = 20;
	
	/** The Constant STANDARD_FIRE_RATE_BOOST. */
	private static final int STANDARD_FIRE_RATE_BOOST = 2;
	
	/** The Constant SHIELD_S. */
	private static final int SHIELD_S = 100;
	
	/** The strategy. */
	private final Strategy strategy;

	/**
	 * Instantiates a new p power up.
	 *
	 * @param position the position of powerUp
	 * @param veloX the velo X of powerUp
	 * @param veloY the velo Y of powerUp
	 * @param id the id of powerUp
	 * @param type the type of powerUp
	 * @param strategy the strategy of powerUp
	 */
	public PPowerUp(final Pair<Integer, Integer> position, final int veloX, final int veloY,final ID id, final PowerUpT type,final Strategy strategy) {
		super(position, veloX, veloY, id, type);
		this.strategy = strategy;
	}


	/**
	 * Reset.
	 */
	@Override
	public void reset() {
		 final Player player = this.getEntityStrategy();
	        if (this.getType().equals(PowerUpT.FIRE_BOOST)) {
	            player.setCoolDown(player.getCoolDown() + this.strategy.multiplyEffect(STANDARD_FIRE_RATE_BOOST));
	         }else if(this.getType().equals(PowerUpT.SHIELD)){
	        	player.setSHield(0);
	        }
	}


	/**
	 * Insert effect.
	 */
	@Override
	protected void InsertEffect() {
		final PlayerImpl player = this.getEntityStrategy();
		if(this.getType().equals(PowerUpT.HEALTH)) {
			player.setHealth(Clamp.clamp(player.getHealth() + this.strategy.multiplyEffect(STANDARD_HEALTH), 0, 100));
        } else if (this.getType().equals(PowerUpT.FIRE_BOOST)) {
            player.setCoolDown(player.getCoolDown() - this.strategy.multiplyEffect(STANDARD_FIRE_RATE_BOOST));
        } else {
        	if(this.isActivated() == false) {
        		player.setSHield(this.strategy.multiplyEffect(SHIELD_S));
        	}else if(player.getShield() == 0) {
        		this.setDead();
        	}
        }
		
	}

	/**
	 * Sets the S.
	 */
	@Override
	protected void setS() {
		this.setPosition(this.getEntityStrategy().getPosition());
		this.setHitbox(this.getEntityStrategy().getHitbox());
	}

	
}
