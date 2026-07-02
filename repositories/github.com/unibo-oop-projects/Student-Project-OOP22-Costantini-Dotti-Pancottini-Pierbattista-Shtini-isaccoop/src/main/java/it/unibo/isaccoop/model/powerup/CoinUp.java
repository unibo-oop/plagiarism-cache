package it.unibo.isaccoop.model.powerup;

import it.unibo.isaccoop.graphics.GraphicsComponent;
import it.unibo.isaccoop.graphics.factory.PowerUpGraphicsComponentImpl;
import it.unibo.isaccoop.model.player.PlayerStat;

/**
 * Represents the power up coin.
 * */
public final class CoinUp extends PowerUp {

    private static final int COIN_UP = 4;
    private static final int COIN_SUPER_UP = 8;

    /**
     * CoinUp Constructor.
     * */
    public CoinUp() {
        super(new PowerUpGraphicsComponentImpl().getCoinUpGraphicsComponent(false));
    }

    /**
     *  Increase the player's coins.
     *  @param p reference to player.
     * */
    @Override
    public void interact(final PlayerStat p) {
        if (super.isSuperItem()) {
            p.setCoin(p.getCoin() + COIN_SUPER_UP);
        } else {
            p.setCoin(p.getCoin() + COIN_UP);
        }
    }

    @Override
    protected GraphicsComponent updateSuperGraphics(final Boolean isSuper) {
        return new PowerUpGraphicsComponentImpl().getCoinUpGraphicsComponent(isSuper);
    }
}
