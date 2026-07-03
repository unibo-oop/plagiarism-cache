package game.buffs;

import java.awt.Rectangle;

import game.GameImpl;
import game.ID;
import utilities.Pair;

/**
 * A powerUp which affect more than a entity.
 */
public final class GlobalPowerUp extends PowerUpImpl {

    private GameImpl game;

    /**
     * @param id id of this powerUp
     * @param position position of this powerUp
     * @param velocityX x velocity of this powerUp
     * @param velocityY y velocity of this powerUp
     * @param type type of this powerUp
     */
    public GlobalPowerUp(final ID id, final Pair<Integer, Integer> position, final int velocityX, final int velocityY, final PowerUpType type) {
        super(id, position, velocityX, velocityY, type);
    }

    @Override
    protected void applyEffect() {
        if (this.getType().equals(PowerUpType.NUKE)) {
            this.game.getEnemies().get().forEach(e -> e.setDead());
        } else {
            this.game.setFreeze();
        }
    }

    /**
     * @param game the game this powerUps need to be applied to
     */
    public void setGame(final GameImpl game) {
        this.game = game;
    }

    @Override
    public void reset() {
        if (this.getType().equals(PowerUpType.FREEZE)) {
            this.game.setFreeze();
        }
    }

    @Override
    protected void setState() {
        if (this.getTimeLeft() == this.getType().getLifetime()) {
            this.setPosition(new Pair<>(0, 0));
            this.setVelocity(0, 0);
            this.setHitbox(new Rectangle(0, 0, GameImpl.GAMEAREA_WIDTH, GameImpl.GAMEAREA_HEIGHT));
        }
    }
}
