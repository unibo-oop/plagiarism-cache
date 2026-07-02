package model.marker;

/**
 * 
 * A factory of {@link Marker}.
 *
 */
public interface MarkerFactory {

    /**
     * Creates a simple {@link Marker} with the given notice.
     * @param text the marker's notice.
     * @return a common {@link Marker}.
     */
    Marker createCommonMarker(String text);

    /**
     * Creates a special {@link Marker} that notify the last death distance.
     * @return a last death {@link Marker}.
     */
    Marker createLastDeathMarker();

    /**
     * Creates a special {@link Marker} that notify the record distance.
     * @return a record {@link Marker}.
     */
    Marker createRecordMarker();

}
