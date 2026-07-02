package zombietsunami.model.mapmodel.api;

import java.util.List;

import zombietsunami.Pair;

/**
 * This interface loads the map file, whose file type is .txt, and store its
 * data
 * into a List.
 */
public interface GameMap {

    /**
     * @return the list of Integer with the item's number of the map
     */
    List<Integer> getLoadedMapList();

    /**
     * @return the list of Integer with the item's number of the obstacles
     */
    List<Integer> getLoadedObstacleList();

    /**
     * Removes an obstacle from the list.
     * 
     * @param index
     */
    void removeObstacleListItem(int index);

    /**
     * @return the list of Integer with the item's number of the Person
     */
    List<Integer> getLoadedPersonList();

    /**
     * Removes a Person from the list.
     * 
     * @param index index of Person
     */
    void removePersonListItem(int index);

    /**
     * This method calls the method getScreenTilePosition in
     * {@link zombietsunami.model.mapmodel.api.MapPosList}.
     * 
     * @param worldRow      the map's (world) row
     * @param worldCol      the map's (world) col
     * @param titleSize     the size of title
     * @param zombieWorldX  the zombie's X coordinate on the map (world)
     * @param zombieWorldY  the zombie's Y coordinate on the map (world)
     * @param zombieScreenX the zombie's X coordinate on the screen
     * @param zombieScreenY the zombie's Y coordinate on the screen
     * @return a List of Pair of Integers, where the pair are the positions of every
     *         single tile that has to be putten into the graphic.
     */
    List<Pair<Integer, Integer>> getScreenTilePos(int worldRow, int worldCol, int titleSize,
            int zombieWorldX, int zombieWorldY, int zombieScreenX,
            int zombieScreenY);

    /**
     * This method calls the method getTileElement in
     * {@link zombietsunami.model.mapmodel.api.TileElement}.
     * 
     * @return a List of strings, where the strings are the file's name of the
     *         tiles, putten into their respective index.
     */
    List<String> getTileElem();
}
