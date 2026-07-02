package tmw.model.item.powerup;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.model.item.AbstractTemporaryItem;

/**
 * Abstract class to implements the features and methods common to all types of
 * power-up.
 * <p>
 * Extension of {@link tmw.model.item.AbstractTemporaryItem}
 */
public abstract class AbstractPowerUp extends AbstractTemporaryItem {

    private final PowerUpType type;

    /**
     * @param type      The type of the power-up where all his information is
     *                  contained
     * @param pos       A {@link P2d} which represents the position of the item in
     *                  the map
     * @param dimension A {@link Dimension2D} which represents the dimension of the
     *                  item
     */
    public AbstractPowerUp(final PowerUpType type, final P2d pos, final Dim2D dimension) {
        super(type.getName(), type.getDescription(), type.getPoints(), pos, dimension);
        this.type = type;
    }

    @Override
    public final int getDuration() {
        return type.getDuration();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDefaultDimension(final Dim2D dimension) {
        setDimension(new Dim2D(type.getProportion() * dimension.getWidth(),
                type.getProportion() * dimension.getWidth()));
    }

    @Override
    public abstract void activeEffect();

    @Override
    public abstract void cancelEffect();
}
