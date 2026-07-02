package it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.impl.CoinImpl;
import it.unibo.model.gameobj.impl.EliCap;
import it.unibo.model.gameobj.impl.EnemyImpl;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api.FactoryAddOn;

/**
 * Implementation of {@link FactoryAddOn}.
 */
public class FactoryAddOnImpl implements FactoryAddOn {

    /**
     * coin width.
     */
    private final double coinWidth;

    /**
     * coin height.
     */
    private final double coinHeight;

    /**
     * enemy width.
     */
    private final double enemyWidth;

    /**
     * enemy height.
     */
    private final double enemyHeight;

    /**
     * elycap width.
     */
    private final double elycapWidth;

    /**
     * elycap height.
     */
    private final double elycapHeight;

    /**
     * Score manager to update the score when a coin is collected.
     */
    private final ScoreManager scoreManager;

    /**
     * Constructs a FactoryAddOnImpl.
     *
     * @param scoreManager the score manager to use for updating scores when coins
     *                     are collected.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "The score manager is necessary for the"
            + " coin to update the score")
    public FactoryAddOnImpl(final ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
        this.coinWidth = GameObjDimension.COIN_WIDTH;
        this.coinHeight = GameObjDimension.COIN_HEIGHT;
        this.enemyWidth = GameObjDimension.ENEMY_WIDTH;
        this.enemyHeight = GameObjDimension.ENEMY_HEIGHT;
        this.elycapWidth = GameObjDimension.ELYCAP_WIDTH;
        this.elycapHeight = GameObjDimension.ELYCAP_HEIGHT;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coin createCoin(final Vector2d position) {
        return new CoinImpl(coinWidth, coinHeight, position, this.scoreManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enemy createEnemy(final Vector2d position) {
        return new EnemyImpl(enemyWidth, enemyHeight, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Gadget createElycap(final Vector2d position) {
        return new EliCap(elycapWidth, elycapHeight, position);
    }

}
