package tmw.model.item.consumable;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.model.item.AbstractItem;

/**
 * A type of item that, when used, restore a percentage of the character's life.
 * <p>
 * Extension of {@link tmw.model.item.AbstractItem}
 */
public class HealingItem extends AbstractItem {

    private final HealingItemType type;

    /**
     * @param type      The type of the consumable where all his information is
     *                  contained
     * @param pos       A {@link P2d} which represents the position of the item in
     *                  the map
     * @param fieldSize A {@link Dimension2D} which represents the resolution of the
     *                  game
     */
    public HealingItem(final HealingItemType type, final P2d pos, final Dim2D fieldSize) {
        super(type.getName(), type.getDescription(), type.getPoints(), pos, new Dim2D(
                fieldSize.getWidth() * type.getProportion(), fieldSize.getWidth() * type.getProportion()));
        this.type = type;
    }

    /**
     * Method to increase character's life when he uses a consumable.
     */
    @Override
    public void useSpecificItem() {
        this.getMilk().heal((int) Math.round(this.getMilk().getMaxHp() * type.getMultiplier()));
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

