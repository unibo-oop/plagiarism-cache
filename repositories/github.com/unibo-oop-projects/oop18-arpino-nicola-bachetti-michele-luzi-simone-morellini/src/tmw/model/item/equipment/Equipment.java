package tmw.model.item.equipment;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.model.item.AbstractTemporaryItem;

/**
 * A type of item that, when used, increases the damage inflicted by the
 * character of a percentage for a specific period of time.
 * <p>
 * Extension of {@link tmw.model.item.AbstractTemporaryItem}
 */
public class Equipment extends AbstractTemporaryItem {

    private final EquipmentType type;

    /**
     * @param type      The type of the equipment where all his information is
     *                  contained
     * @param pos       A {@link P2d} which represents the position of the item in
     *                  the map
     * @param fieldSize A {@link Dimension2D} which represents the resolution of the
     *                  game
     */
    public Equipment(final EquipmentType type, final P2d pos, final Dim2D fieldSize) {
        super(type.getName(), type.getDescription(), type.getPoints(), pos, new Dim2D(
                fieldSize.getWidth() * type.getProportion(), fieldSize.getWidth() * type.getProportion()));
        this.type = type;
    }

    @Override
    public final void activeEffect() {
        this.getMilk().setDamage((int) Math.round(this.getMilk().getDefaultDamage() * type.getMultiplier()));
    }

    @Override
    public final void cancelEffect() {
        this.getMilk().setDefaultDamage();
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
}

