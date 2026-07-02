package model.powerup;

import java.awt.Rectangle;

import model.GameImpl;
import model.ID;
import utility.Pair;

/**
 * The Class GPowerUp for all the entity.
 */
public class GPowerUp extends PowerUpImpl{
	
	/** The game. */
	private GameImpl game;

	

	/**
	 * Instantiates a new g power up.
	 *
	 * @param position the position of powerUp
	 * @param veloX the velo X of powerUp
	 * @param veloY the velo Y of powerUp
	 * @param id the id of powerUp
	 * @param type the type of powerUp
	 */
	public GPowerUp(Pair<Integer, Integer> position, int veloX, int veloY, ID id, PowerUpT type) {
		super(position, veloX, veloY, id, type);
		
	}

	/**
	 * Insert effect.
	 */
	@Override
	protected void InsertEffect() {
		 this.game.setFreeze();
		
	}

	/**
	 * Reset.
	 */
	@Override
	public void reset() {
		if (this.getType().equals(PowerUpT.FREEZE)) {
            this.game.setFreeze();
        }
		
	}

	/**
	 * Sets the S.
	 */
	@Override
	protected void setS() {
		if (this.getTimeLeft() == this.getType().getLifetime()) {
            this.setPosition(new Pair<>(0, 0));
            this.setSpeed(0, 0);
            this.setHitbox(new Rectangle(0, 0, GameImpl.ARENA_WIDTH, GameImpl.ARENA_HEIGHT));
        }
		
	}
	
    /**
     * Sets the game.
     *
     * @param game the new game
     */
    public void setGame(final GameImpl game) {
        this.game = game;
    }

}
