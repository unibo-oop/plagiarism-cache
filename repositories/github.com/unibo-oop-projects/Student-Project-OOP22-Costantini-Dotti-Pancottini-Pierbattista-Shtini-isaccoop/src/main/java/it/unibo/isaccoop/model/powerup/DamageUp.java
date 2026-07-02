package it.unibo.isaccoop.model.powerup;

import it.unibo.isaccoop.graphics.GraphicsComponent;
import it.unibo.isaccoop.graphics.factory.PowerUpGraphicsComponentImpl;
import it.unibo.isaccoop.model.player.PlayerStat;

/**
 * Represents the power up damage.
 * */
public final class DamageUp extends PowerUp {

    private static final Double DAMAGE_SUPER_UP = 2.0;
    private static final Double DAMAGE_UP = 1.0;

    /**
     * DamageUp Constructor.
     * */
    public DamageUp() {
        super(new PowerUpGraphicsComponentImpl().getDamageUpGraphicsComponent(false));
    }

    /**
     *  Increase the player's damage.
     *  @param p reference to player.
     * */
    @Override
    public void interact(final PlayerStat p) {
        if (super.isSuperItem()) {
            p.setDamage(p.getDamage() + DAMAGE_SUPER_UP);
        } else {
            p.setDamage(p.getDamage() + DAMAGE_UP);
        }
    }

    @Override
    protected GraphicsComponent updateSuperGraphics(final Boolean isSuper) {
        return new PowerUpGraphicsComponentImpl().getDamageUpGraphicsComponent(isSuper);
    }

}
