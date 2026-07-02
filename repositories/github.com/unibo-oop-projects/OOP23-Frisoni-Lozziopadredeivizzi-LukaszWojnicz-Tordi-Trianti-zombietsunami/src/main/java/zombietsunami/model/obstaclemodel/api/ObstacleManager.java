package zombietsunami.model.obstaclemodel.api;

import java.util.List;

import zombietsunami.Pair;

/**
 * Interface whose purpose is to manage all obstacles.
 */
public interface ObstacleManager {

    /**
     * Removes the "index" breakable from the list.
     * @param index the index of the breakable in the list.
     */
    void removeBreakableFromList(int index);

    /**
     * Removes the "index" bomb from the list.
     * @param index the index of the bomb in the list.
     */
    void removeBombFromList(int index);

    /**
     * Returns the bomb list.
     * @return the bomb list.
     */
    List<Bomb> getBombList();

    /**
     * Returns the breakable list.
     * @return the breakable list.
     */
    List<Breakable> getBreakableList();

    /**
     * Gets the list of bombs from the map.
     * @param bomblist the list of the bombs.
     * @param coords the list of the coordinates of every single bomb.
     * @param strength the zombies strength.
     */
    void fillBombListFromMap(List<Integer> bomblist, List<Pair<Integer, Integer>> coords, Integer strength);

    /**
     * Gets the list of breakables from the map.
     * @param breakablelist the list of the breakables.
     * @param coords the list of the coordinates of every single breakable.
     * @param strength the zombies strength.
     */
    void fillBreakableListFromMap(List<Integer> breakablelist, List<Pair<Integer, Integer>> coords, Integer strength);

    /**
     * Adds a bomb into the bomb list.
     * @param bomb the bomb that needs to be added.
     */
    void addBomb(Bomb bomb);

    /**
     * Adds a breakable into the breakable list.
     * @param breakable the breakable that needs to be added.
     */
    void addBreakable(Breakable breakable);
}
