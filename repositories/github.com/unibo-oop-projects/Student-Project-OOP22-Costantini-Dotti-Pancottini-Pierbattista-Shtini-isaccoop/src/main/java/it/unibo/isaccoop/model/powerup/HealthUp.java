package it.unibo.isaccoop.model.powerup;

import it.unibo.isaccoop.graphics.GraphicsComponent;
import it.unibo.isaccoop.graphics.factory.PowerUpGraphicsComponentImpl;
import it.unibo.isaccoop.model.player.PlayerStat;

/**
 * Represents the power up health.
 * */
public final class HealthUp extends PowerUp {


    private static final int HEALTH_SUPER_UP = 2;
    private static final int HEALTH_UP = 1;

    /**
     * HealthUp Constructor.
     * */
    public HealthUp() {
        super(new PowerUpGraphicsComponentImpl().getHealthUpGraphicsComponent(false));
    }

    /**
     *   Increase the player's health.
     *   @param p reference to player.
     * */
    @Override
    public void interact(final PlayerStat p) {
        if (super.isSuperItem()) {
            p.setMaxHeart(p.getMaxHeart() + HEALTH_SUPER_UP);
        } else {
            p.setMaxHeart(p.getMaxHeart() + HEALTH_UP);
        }
    }

    @Override
    protected GraphicsComponent updateSuperGraphics(final Boolean isSuper) {
        return new PowerUpGraphicsComponentImpl().getHealthUpGraphicsComponent(isSuper);
    }
}
