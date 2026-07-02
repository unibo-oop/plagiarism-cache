package model.marker;

import java.awt.geom.Point2D;
import java.security.SecureRandom;

import javafx.scene.image.Image;

/**
 * 
 * Implementation of {@link MarkerFactory}.
 *
 */
public class MarkerFactoryImpl implements MarkerFactory {

    private static final double MARKER_X = 980;
    private static final double LOW_MARKER_Y = 365;
    private static final double HIGH_MARKER_Y = 40;
    private final SecureRandom random;

    /**
     * Creates a new MarkerFactoryImpl.
     * 
     */
    public MarkerFactoryImpl() {
        super();
        this.random = new SecureRandom();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Marker createCommonMarker(final String text) {
        return this.random.nextInt() % 2 == 0
                ? this.createHighMarker(new Image("RedBalloon.png"), text)
                : this.createLowMarker(new Image("SwagCat.png"), text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Marker createLastDeathMarker() {
        return this.createLowMarkerWithoutText(new Image("Tombstone.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Marker createRecordMarker() {
        return this.createLowMarkerWithoutText(new Image("RecordFlag.png"));
    }

    /**
     * Creates a general {@link Marker}.
     * @param point the marker's coordinates.
     * @param image the marker's image.
     * @param text the marker's notice.
     * @return a general {@link Marker}.
     */
    private Marker createGeneralised(final Point2D.Double point, final Image image, final String text) {
        return new MarkerImpl(point, image, text);
    }

    /**
     * Creates a general {@link Marker} without a text notice.
     * @param point the marker's coordinates.
     * @param image the marker's image.
     * @return a general {@link Marker} without a text notice.
     */
    private Marker createGeneralisedWithoutText(final Point2D.Double point, final Image image) {
        return new MarkerImpl(point, image, "");
    }

    /**
     * Creates a {@link Marker} at the bottom right.
     * @param image the marker's image.
     * @param text the marker's notice.
     * @return a {@link Marker} at the bottom right. 
     */
    private Marker createLowMarker(final Image image, final String text) {
        return this.createGeneralised(new Point2D.Double(MARKER_X, LOW_MARKER_Y), image, text);
    }

    /**
     * Creates a {@link Marker} at the top right.
     * @param image the marker's image.
     * @param text the marker's notice.
     * @return a {@link Marker} at the top right. 
     */
    private Marker createHighMarker(final Image image, final String text) {
        return this.createGeneralised(new Point2D.Double(MARKER_X, HIGH_MARKER_Y), image, text);
    }

    /**
     * Creates a {@link Marker} at the bottom right without a text notice.
     * @param image the marker's image.
     * @return a {@link Marker} at the top right without a text notice.. 
     */
    private Marker createLowMarkerWithoutText(final Image image) {
        return this.createGeneralisedWithoutText(new Point2D.Double(MARKER_X, LOW_MARKER_Y), image);
    }

}
