package it.unibo.model.gameobj.impl;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.gameobj.api.AbstractGameObj;
import it.unibo.model.physics.alienphysic.api.AlienPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.score.api.ScoreManager;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * Represents a Coin entity in a two-dimensional game environment which can be
 * collected by the {@link Alien}.
 */
public class CoinImpl extends AbstractGameObj implements Coin {

    private static final int COIN_POINTS = 1;

    /**
     * The score manager which updates the {@link Coin} collected number.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
        justification = "The score manager is shared across the game and "
            + "should be updated by the Coin when collected.")
    private final ScoreManager scoreManager;

    /**
     * Constructs a new CoinImpl.
     *
     * @param height       Coin's height
     * @param width        Coin's width
     * @param position     Coin's position
     * @param scoreManager the score manager to update the Coins collected number
     */
    public CoinImpl(final double height, final double width, final Vector2d position, final ScoreManager scoreManager) {
        super(height, width, position);
        this.scoreManager = scoreManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onHitBy(final Alien alien, final AlienPhysic physic, final Boundary boundary, final GameWorld gameWorld,
            final LaunchedGame launchedGame, final ActiveUpgrades activeUpgrades) {
        physic.hitCoin(this, activeUpgrades, gameWorld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collectCoin(final GameWorld gameWorld, final int multiplier) {
        gameWorld.removeMoney(this);
        this.scoreManager.addCoins(COIN_POINTS * multiplier);
    }
}
