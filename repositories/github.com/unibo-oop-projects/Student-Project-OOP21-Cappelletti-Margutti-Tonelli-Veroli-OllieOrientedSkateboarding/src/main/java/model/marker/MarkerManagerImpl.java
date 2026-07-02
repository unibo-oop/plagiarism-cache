package model.marker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 
 * Implementation of {@link MarkerManager}.
 *
 */
public class MarkerManagerImpl implements MarkerManager {

    private static final int DISTANCE_BETWEEN_MARKERS = 10;
    private final int lastDeathDistance;
    private final int recordDistance;
    private int numberNextMarker;
    private final MarkerFactory markerFactory;
    private Optional<Marker> lastDeathMarker;
    private Optional<Marker> recordMarker;
    private List<Optional<Marker>> markers;

    /**
     * Creates a new MarkerManagerImpl.
     * @param lastDeathDistance the distance reached in the last run.
     * @param recordDistance the record distance.
     */
    public MarkerManagerImpl(final int lastDeathDistance, final int recordDistance) {
        super();
        this.lastDeathDistance = lastDeathDistance;
        this.recordDistance = recordDistance;
        this.numberNextMarker = 1;
        this.markerFactory = new MarkerFactoryImpl();
        this.lastDeathMarker = Optional.empty();
        this.recordMarker = Optional.empty();
        this.markers = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCommonMarkerToBeCreated(final int distance) {
        final int nextMarkerDist = this.numberNextMarker * DISTANCE_BETWEEN_MARKERS;

        if (distance >= nextMarkerDist && distance % nextMarkerDist >= 0) {
            this.numberNextMarker++;
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkCreateMarker(final int distance) {
        if (this.isCommonMarkerToBeCreated(distance)) {
            this.markers.add(Optional.of(this.markerFactory.createCommonMarker(Integer.toString(this.approximateDistance()))));
        }

        if (this.lastDeathMarker.isEmpty() && distance > 0 && distance >= this.calculateSpawn(this.lastDeathDistance)) {
            this.lastDeathMarker = Optional.of(this.markerFactory.createLastDeathMarker());
            this.markers.add(this.lastDeathMarker);
        }

        if (this.recordMarker.isEmpty() && distance > 0 && distance >= this.calculateSpawn(this.recordDistance)) {
            this.recordMarker = Optional.of(this.markerFactory.createRecordMarker());
            this.markers.add(this.recordMarker);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double difficulty) {
        this.markers.stream()
                .forEach(op -> op.get().update(difficulty));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Optional<Marker>> getMarkers() {
        this.markers = this.markers.stream()
                .filter(op -> !op.get().isOutOfScreen())
                .collect(Collectors.toList());
        return this.markers;
    }

    /**
     * Calculate the distance that common {@link Marker} must notify. 
     * @return the approximate distance.
     */
    private int approximateDistance() {
        return this.numberNextMarker * DISTANCE_BETWEEN_MARKERS;
    }

    /**
     * Calculate the spawn distance that special {@link Marker} must to use. 
     * @param distance the covered distance.
     * @return the spawn distance.
     */
    private int calculateSpawn(final int distance) {
        return distance - DISTANCE_BETWEEN_MARKERS;
    }

}
