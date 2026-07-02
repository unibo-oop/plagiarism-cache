package tmw.model.objects;

import tmw.common.Dim2D;
import tmw.common.P2d;

/**
 * Class to represent a trigger high like the whole room.
 */
public abstract class VerticalTrigger extends Trigger {

    /**
     * This value consider that height of this vertical trigger
     * will be multiplied by 0.08. So 12.5 is value that allows to 
     * get correct height such as fieldsize height.
     */
    private static final double VERTICAL = 12.5;

    /**
     * @param x         The coordinate of the x axis where the trigger is positioned
     * @param fieldSize The dimension of the field
     */
    public VerticalTrigger(final int x, final Dim2D fieldSize) {
        super(new P2d(x, 0), new Dim2D(fieldSize.getWidth(), fieldSize.getHeight() * VERTICAL));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDefaultDimension(final Dim2D dimension) {
        super.resetDefaultDimension(new Dim2D(dimension.getWidth() * VERTICAL, dimension.getHeight() * VERTICAL));
    }
}

