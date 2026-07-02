package model.bounding_box;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Concrete implementation of {@link BoundingBox} representing a rectangular
 * bounding box.
 * <p>
 * The rectangle is defined by its upper-left and bottom-right corners.
 * This class provides collision detection and position updating
 * functionalities.
 */
public class RectBoundingBox implements BoundingBox {

    private Pair<Double, Double> cornerUl;
    private Pair<Double, Double> cornerBR;
    private final Double bboxHeight;
    private final Double bboxWidth;

    /**
     * Constructs a rectangular bounding box given the upper-left and bottom-right
     * corners.
     *
     * @param cornerUl the upper-left corner of the bounding box
     * @param cornerBR the bottom-right corner of the bounding box
     */
    public RectBoundingBox(final Pair<Double, Double> cornerUl, final Pair<Double, Double> cornerBR) {
        this.cornerUl = cornerUl;
        this.cornerBR = cornerBR;
        this.bboxWidth = (this.cornerBR.getLeft() - this.cornerUl.getLeft());
        this.bboxHeight = (this.cornerUl.getRight() - this.cornerBR.getRight());
    }

    /**
     * {@inheritDoc}
     * <p>
     * Checks if this rectangle intersects (collides) with another bounding box.
     * Collision is detected if there is overlap in both X and Y axes.
     */
    @Override
    public boolean isColliding(final BoundingBox otherBBox) {

        final double tux = this.cornerUl.getLeft();
        final double tuy = this.cornerUl.getRight();
        final double tbx = this.cornerBR.getLeft();
        final double tby = this.cornerBR.getRight();

        final double oux = otherBBox.getULcorner().getLeft();
        final double ouy = otherBBox.getULcorner().getRight();
        final double obx = otherBBox.getBRcorner().getLeft();
        final double oby = otherBBox.getBRcorner().getRight();

        boolean overlapY = tuy > oby && tby < ouy;
        boolean overlapX = tux < obx && tbx > oux;

        // System.out.println("overlapX " + overlapX + " overlapY " + overlapY);
        return overlapX && overlapY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getULcorner() {
        return this.cornerUl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getBRcorner() {
        return this.cornerBR;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Updates the bounding box position by repositioning the corners relative to
     * the new position.
     * The width and height remain constant.
     *
     * @param newPos the new position representing the new upper-left corner
     *               reference
     */
    @Override
    public void updateBBox(final Pair<Double, Double> newPos) {
        this.cornerUl = Pair.of(newPos.getLeft(), newPos.getRight() + this.bboxHeight);
        this.cornerBR = Pair.of(newPos.getLeft() + this.bboxWidth, newPos.getRight());
    }
}
