package tmw.model.item;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.model.entities.MilkEntity;
import tmw.model.objects.BaseGameObject;

/**
 * Abstract class to implements the features and methods common to all types of
 * item.
 * <p>
 * Implementation of {@link tmw.model.item.Item} <br>
 * Extension of {@link BaseGameObject}
 */
public abstract class AbstractItem extends BaseGameObject implements Item {

    private final String name;
    private final String description;
    private final int points;
    private MilkEntity milk;

    /**
     * @param name        Name of the item
     * @param description Description of the item
     * @param points      Points that give the item
     * @param pos         A {@link P2d} which represents the position of the item in
     *                    the map
     * @param dimension   A {@link Dimension2D} which represents the dimension of
     *                    the item
     */
    public AbstractItem(final String name, final String description, final int points, final P2d pos,
            final Dim2D dimension) {
        super(pos, dimension);
        this.name = name;
        this.description = description;
        this.points = points;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final int getPoints() {
        return this.points;
    }

    /**
     * Method to get the model of the milk.
     * 
     * @return the model of the milk
     */
    protected final MilkEntity getMilk() {
        return milk;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void useItem(final MilkEntity milk) {
        this.milk = milk;
        this.useSpecificItem();
    };

    @Override
    public abstract void useSpecificItem();
}

