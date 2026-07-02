package models;


/**
 * CollectableImpl is a class that implements Collectable and its contracts.
 * It generates Collectable entities, each initialized with a random value and a given position.
 */
public class CollectableImpl implements Collectable {

    private Point2D position;
    private COLLECTABLETYPE type;

    /**
     * This is the constructor of the class.
     */
    public CollectableImpl() {
        type = COLLECTABLETYPE.getRandomType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point2D position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPoints() {
        return type.getPoints();
    }

}
