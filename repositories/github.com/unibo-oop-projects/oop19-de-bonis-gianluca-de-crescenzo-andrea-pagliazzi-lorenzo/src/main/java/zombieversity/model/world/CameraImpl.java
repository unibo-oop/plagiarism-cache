package zombieversity.model.world;

import javafx.geometry.Point2D;
import zombieversity.model.entities.Entity;

/**
 * Implementation of {@link Camera}.
 */
public class CameraImpl implements Camera {

    private double xOffset;
    private double yOffset;
    private double mapW;
    private double mapH;
    private double cameraW;
    private double cameraH;

    /**
     * Constructor to setup init values for the camera, map sizes represent limits
     * for the camera, camera sizes represent the region of the map effectively user
     * - visible.
     * 
     * @param x       - starting offset
     * @param y       - starting offset
     * @param mapW    - width of the map
     * @param mapH    - height of the map
     * @param cameraW - camera width
     * @param cameraH - camera height
     */
    public CameraImpl(final double x, final double y, final double mapW, final double mapH, final double cameraW,
            final double cameraH) {
        xOffset = x;
        yOffset = y;
        this.mapH = mapH;
        this.mapW = mapW;
        this.cameraW = cameraW;
        this.cameraH = cameraH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void move(final Point2D step) {
        this.xOffset += step.getX();
        this.yOffset += step.getY();
        this.adjust();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void move(final double x, final double y) {
        this.move(new Point2D(x, y));
    }

    private void adjust() {
        xOffset = xOffset + cameraW <= mapW ? xOffset : mapW - cameraW;
        xOffset = xOffset >= 0 ? xOffset : 0;
        yOffset = yOffset + cameraH <= mapH ? yOffset : mapH - cameraH;
        yOffset = yOffset >= 0 ? yOffset : 0;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Point2D getCenter() {
        return new Point2D(mapW / 2, mapH / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void centerOnEntity(final Entity e) {
        final Point2D ePos = e.getPosition();
        this.centerOn(ePos.getX() - e.getWidth() / 2, ePos.getY() - e.getHeight() / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void centerOn(final double x, final double y) {
        xOffset = x - cameraW / 2;
        yOffset = y - cameraH / 2;
        this.adjust();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getOffsetY() {
        return this.yOffset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getOffsetX() {
        return this.xOffset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Point2D getOffset() {
        return new Point2D(this.getOffsetX(), this.getOffsetY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setScale(final double scale) {
        this.mapW *= scale;
        this.mapH *= scale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void resize(final double w, final double h) {
        this.cameraH = h;
        this.cameraW = w;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Point2D midpoint() {
        return this.end().midpoint(this.start());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Point2D start() {
        return this.getOffset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Point2D end() {
        return this.getOffset().add(new Point2D(cameraW, cameraH));
    }
}
