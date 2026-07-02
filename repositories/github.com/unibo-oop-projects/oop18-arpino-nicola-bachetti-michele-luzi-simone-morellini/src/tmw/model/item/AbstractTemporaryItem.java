package tmw.model.item;

import tmw.common.Dim2D;
import tmw.common.P2d;

/**
 * Abstract class to implements the use of the temporary item using a thread to
 * cancel the effects once the duration is over.
 * <p>
 * Implementation of {@link tmw.model.item.TemporaryItem} <br>
 * Extension of {@link tmw.model.item.AbstractItem}
 */
public abstract class AbstractTemporaryItem extends AbstractItem implements TemporaryItem {

    private static final int SECOND_IN_MILLIS = 1000;

    /**
     * @param name        Name of the item
     * @param description Description of the item
     * @param points      Points that give the item
     * @param pos         A {@link P2d} which represents the position of the item in
     *                    the map
     * @param dimension   A {@link Dimension2D} which represents the dimension of
     *                    the item
     */
    public AbstractTemporaryItem(final String name, final String description, final int points, final P2d pos,
            final Dim2D dimension) {
        super(name, description, points, pos, dimension);
    }

    /**
     * the use of a {@link TemporaryItem} consists in the activation of its effect
     * and, once its duration is over, cancel that effect.
     */
    @Override
    public final void useSpecificItem() {
        activeEffect();
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(getDuration() * SECOND_IN_MILLIS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cancelEffect();
            }
        }.start();
    }

    @Override
    public abstract void activeEffect();

    @Override
    public abstract void cancelEffect();
}

