package model.marker;

import java.util.List;
import java.util.Optional;

/**
 * 
 * Interface that identifies a manager of {@link Marker}.
 *
 */
public interface MarkerManager {

    /**
     * Checks if a common {@link Marker} must to be created.
     * @param distance the covered distance by player. 
     * @return true if {@link Marker} has to be created, false otherwise.
     */
    boolean isCommonMarkerToBeCreated(int distance);

    /**
     * Checks and creates {@link Marker} that must to be created.
     * @param distance the covered distance by player.
     * 
     */
    void checkCreateMarker(int distance);

    /**
     * Updates {@link Marker}s' positions of the given amount.
     * @param difficulty the amount of the movement.
     */
    void update(double difficulty);

    /**
     * Gets the list of Optional of {@link Marker}.
     * @return the list of Optional of {@link Marker}.
     */
    List<Optional<Marker>> getMarkers();

}
