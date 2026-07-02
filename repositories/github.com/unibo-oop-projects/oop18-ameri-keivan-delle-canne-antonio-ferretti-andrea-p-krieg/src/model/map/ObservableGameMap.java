package model.map;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.util.Pair;
import model.objects.GameObject;
import model.objects.structures.Structure;
import model.objects.terrains.Terrain;
import model.objects.unit.Unit;
import util.Coordinates;

/**
 * A game map that can't be modified but can be read.
 */
public interface ObservableGameMap {

    /**
     * 
     * @return the size of the game map
     */
    Pair<Integer, Integer> getMapSize();

    /**
     * @param cords the cords of the case to be checked for the terrain
     * 
     * @return the terrain of the case
     */
    Terrain getTerrain(Coordinates cords);

    /**
     * @param cords the cords of the case to be checked for the structure
     * 
     * @return the structure associated with this terrain if present, Optional.Empty
     *         otherwise
     */
    Optional<Structure> getStructure(Coordinates cords);

    /**
     * @param cords the cords to be checked for the pg
     * 
     * @return the Unit on the case if present
     */
    Optional<Unit> getUnit(Coordinates cords);

    /**
     * 
     * @return the the actual updated state of the game map
     */
    Map<Coordinates, List<GameObject>> toMap();
}
