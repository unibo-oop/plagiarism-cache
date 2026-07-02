package tmw.model.item.powerup;

import tmw.common.Dim2D;
import tmw.common.P2d;

/**
 * Class to implements the effect of the sugar cane item that increases the
 * speed of the character of a percentage for a specific period of time.
 * <p>
 * Extension of the abstract class
 * {@link tmw.model.item.powerup.AbstractPowerUp}
 */
public class SugarCanePowerUp extends AbstractPowerUp {

    /**
     * @param pos       A {@link P2d} which represents the position of the item in
     *                  the map
     * @param fieldSize A {@link Dimension2D} which represents the resolution of the
     *                  game
     */
    public SugarCanePowerUp(final P2d pos, final Dim2D fieldSize) {
        super(PowerUpType.SUGAR_CANE, pos,
                new Dim2D(fieldSize.getWidth() * PowerUpType.SUGAR_CANE.getProportion(),
                        fieldSize.getWidth() * PowerUpType.SUGAR_CANE.getProportion()));
    }

    /**
     * increases character's speed of 50% for 30 seconds.
     */
    @Override
    public void activeEffect() {
        this.getMilk()
                .setSpeed((this.getMilk().getDefaultSpeed() * PowerUpType.SUGAR_CANE.getMultiplier()));

    }

    /**
     * sets character's speed to the default.
     */
    @Override
    public void cancelEffect() {
        this.getMilk().setDefaultSpeed();
    }
}

