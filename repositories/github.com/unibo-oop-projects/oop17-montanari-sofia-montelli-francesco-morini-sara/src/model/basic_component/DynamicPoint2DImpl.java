package model.basic_component;

import lombok.EqualsAndHashCode;

/**
 * Implementation of a dynamic point.
 * using a MutablePair.
 */
@EqualsAndHashCode(callSuper = true)
public final class DynamicPoint2DImpl extends StaticPoint2DImpl implements DynamicPoint2D { 

    private static final long serialVersionUID = 6172082016898300451L;

    /**
     * @param xCord the initial xCord of the point
     * @param yCord the initial yCord of the point
     * */
    public DynamicPoint2DImpl(final int xCord, final int yCord) {
        super(xCord, yCord);
    }

    @Override
    public void moveTo(final int xCord, final int yCord) {
        setX(xCord);
        setY(yCord);
    }

    @Override
    public void traslate(final int xDelta, final int yDelta) {
        moveTo(getX() + xDelta, getY() + yDelta);
    }
}
