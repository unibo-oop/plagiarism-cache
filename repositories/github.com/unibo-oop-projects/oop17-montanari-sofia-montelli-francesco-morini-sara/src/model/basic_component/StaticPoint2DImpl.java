package model.basic_component;

import org.apache.commons.lang3.tuple.MutablePair;
import lombok.EqualsAndHashCode;
/**
 * Basic implementation of a generic 2D point.
 * that can't move
 * using apache.commons.lang3.tuple.pair
 */
@EqualsAndHashCode(of = "point")
public class StaticPoint2DImpl implements StaticPoint2D {

    private static final long serialVersionUID = 1L;
    /**
     * The internal representation on a point.
     */
    private final MutablePair<Integer, Integer> point;

    /**
     *  @param xCord the xCord of the point
     *  @param yCord the yCord of the point 
     */
    public StaticPoint2DImpl(final int xCord, final int yCord) {
        point = new MutablePair<>(xCord, yCord);
    }

    @Override
    public final int getX() {
        return point.getLeft();
    }

    @Override
    public final int getY() {
        return point.getRight();
    }

    /**
     * @param xCord the coordinate to set
     */
    protected void setX(final int xCord) {
        point.setLeft(xCord);
    }
    /**
     * @param yCord the coordinate to set
     */
    protected void setY(final int yCord) {
        point.setRight(yCord);
    }

}
