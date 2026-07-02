package model.marker;

import java.awt.geom.Point2D;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 * 
 * A simple Marker.
 * Implements {@link Marker}.
 *
 */
public class MarkerImpl implements Marker {

    private final Point2D.Double coordinates;
    private final Image image;
    private final Dimension2D dimension;
    private final String text;

    /**
     * Creates a new MarkerImpl.
     * @param coordinates the position of the marker.
     * @param image the image of the marker.
     * @param text the notice of the marker.
     */
    public MarkerImpl(final Point2D.Double coordinates, final Image image, final String text) {
        super();
        this.coordinates = coordinates;
        this.image = image;
        this.dimension = new Dimension2D(this.image.getWidth(), this.image.getHeight());
        this.text = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double velocity) {
        this.coordinates.setLocation(this.coordinates.getX() - velocity, this.coordinates.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOutOfScreen() {
        return this.coordinates.getX() < -this.dimension.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return this.coordinates.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return this.coordinates.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension2D getDimension() {
        return this.dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage() {
        return this.image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return this.text;
    }

}
