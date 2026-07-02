package tmw.model.objects;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.common.Rec2D;

/**
 * This Class is the base for every object in the game and contains information
 * about the position of an object and its boundary. This Class cannot be
 * instantiated because it represents only a skeleton for the game's object and
 * it's used to make easier the implementation of the other objects.
 * 
 */
public abstract class BaseGameObject implements GameObject {

    private P2d pos;
    private Dim2D dimension;

    /**
     * Construct the base for an object from the two base informations of an object,
     * position and dimension.
     * 
     * @param pos       - the {@link P2d} which represents the position of the
     *                  object
     * @param dimension - the {@link Dimension2D} which represents the dimensions of
     *                  the object
     */
    protected BaseGameObject(final P2d pos, final Dim2D dimension) {
        this.pos = pos;
        this.dimension = dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public P2d getCurrentPos() {
        return new P2d(pos.getX(), pos.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPos(final P2d position) {
        this.pos = new P2d(position.getX(), position.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rec2D getBoundary() {
        return new Rec2D(this.pos, this.dimension.getWidth(), this.dimension.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public P2d getCentralPosition() {
        final P2d upperLeftCorner = new P2d(this.getBoundary().getMinX(), this.getBoundary().getMinY());
        final P2d lowerRightCorner = new P2d(this.getBoundary().getMaxX(), this.getBoundary().getMaxY());
        return new P2d((upperLeftCorner.getX() + lowerRightCorner.getX()) / 2,
                (upperLeftCorner.getY() + lowerRightCorner.getY()) / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dim2D getDimension() {
        return dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDimension(final Dim2D dimension) {
        this.dimension = dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersect(final GameObject object) {
        return this.getBoundary().intersects(object.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void resetDefaultDimension(Dim2D dimension);

}
