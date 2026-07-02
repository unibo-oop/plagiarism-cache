package model.gamemap;

import java.util.Map;
import javafx.util.Pair;
import model.construction.Construction;

/**
 * Interface of the Game Map.
 */
public interface GameMap {


    /**
     * Getter of the Game Map.
     * @return
     *          the Game Map.
     */
    Map<Pair<Integer, Integer>, Construction> getMap();

    /**
     * Method that allows the placement of a new building in the Game Map.
     * @param build
     *          the new building to add to the Game Map.
     */
    void addBuilding(Construction build);

    /**
     * Method that allows the removal of an existing building from the Game Map.
     * @param build
     *          the building to remove from the Game Map.
     */
    void deleteBuilding(Construction build);

    /**
     * Method that checks if a position of the Game Map is occupied or not.
     * @param position
     *          the position to check.
     * @return
     *          true:   if the position is free.
     *          false:  if the position is occupied.
     */
    boolean isBuilding(Pair<Integer, Integer> position);

    /**
     * Method that return the building present in a cell of the Game Map.
     * @param position
     *          the cell occupied by a building.
     * @return
     *          the building present in the cell.
     */
    Construction getBuilding(Pair<Integer, Integer> position);
}
