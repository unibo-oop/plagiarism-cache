package tmw.model.item.powerup;

import tmw.common.Dim2D;
import tmw.common.P2d;

/**
 * Class to implements the effect of the white sugar item that increases the
 * frequency of bullets of a percentage for a specific period of time.
 * <p>
 * Extension of the abstract class
 * {@link tmw.model.item.powerup.AbstractPowerUp}
 */
public class WhiteSugarPowerUp extends AbstractPowerUp {

    /**
     * @param pos       A {@link P2d} which represents the position of the item in
     *                  the map
     * @param fieldSize A {@link Dimension2D} which represents the resolution of the
     *                  game
     */
    public WhiteSugarPowerUp(final P2d pos, final Dim2D fieldSize) {
        super(PowerUpType.WHITE_SUGAR, pos,
                new Dim2D(fieldSize.getWidth() * PowerUpType.WHITE_SUGAR.getProportion(),
                        fieldSize.getWidth() * PowerUpType.WHITE_SUGAR.getProportion()));
    }

    /**
     * increases the frequency of bullets of 50% for 30 seconds.
     */
    @Override
    public void activeEffect() {
        this.getMilk().setTimeForReload(
                (int) Math.round(this.getMilk().getDefaultTimeForReload() / PowerUpType.WHITE_SUGAR.getMultiplier()));
    }

    /**
     * sets the frequency of bullets to the default.
     */
    @Override
    public void cancelEffect() {
        this.getMilk().setDefaultTimeForReload();
    }
}

