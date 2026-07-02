package it.unibo.isaccoop.model.powerup;

import it.unibo.isaccoop.graphics.GraphicsComponent;
import it.unibo.isaccoop.graphics.factory.PowerUpGraphicsComponentImpl;
import it.unibo.isaccoop.model.player.PlayerStat;

/**
 * Represents the power up tears.
 * */
public final class TearsUp extends PowerUp {

    private static final int TEARS_SUPER_UP = -600;
    private static final int TEARS_UP = -400;

    /**
     * TearsUp Constructor.
     * */
    public TearsUp() {
        super(new PowerUpGraphicsComponentImpl().getTearsUpGraphicsComponent(false));
    }

    /**
     *  Increase the player's speed.
     *  @param p reference to player.
     * */
    @Override
    public void interact(final PlayerStat p) {
        if (super.isSuperItem()) {
            p.setTears(p.getTears() + TEARS_SUPER_UP);
        } else {
            p.setTears(p.getTears() + TEARS_UP);
        }
    }

    @Override
    protected GraphicsComponent updateSuperGraphics(final Boolean isSuper) {
        return new PowerUpGraphicsComponentImpl().getTearsUpGraphicsComponent(isSuper);
    }
}
