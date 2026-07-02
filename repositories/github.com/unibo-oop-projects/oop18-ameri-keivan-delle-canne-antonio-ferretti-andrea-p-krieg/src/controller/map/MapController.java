package controller.map;

import java.util.Map;

import javafx.util.Pair;
import util.Coordinates;

/**
 * the controller of the map.
 */
public interface MapController {
    /**
     * this method needs to be called only the first draw since the terrains of the
     * map don't vary.
     * 
     * @return the terrains of the map based on the actual state of the model
     */
    Map<Coordinates, String> getTerrains();

    /**
     * this method is called at each refresh of the view.
     * 
     * @return a grid representing the actual state of the map
     */
    Map<Coordinates, VariableCasePart> getActualMapState();

    /**
     * Handle the hit on a cell done by the User.
     * 
     * @param cords The cords in which the user hit
     */
    void cellHit(Coordinates cords);

    /**
     * @return the size of the map
     */
    Pair<Integer, Integer> getMapSize();
}
