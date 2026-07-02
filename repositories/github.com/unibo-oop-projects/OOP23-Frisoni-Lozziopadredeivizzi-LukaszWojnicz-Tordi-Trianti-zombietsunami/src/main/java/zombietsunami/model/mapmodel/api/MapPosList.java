package zombietsunami.model.mapmodel.api;

import java.util.List;

import zombietsunami.Pair;

/**
 * This interface allows you to get the positions of every single tile in the
 * map game, and it puts them into a List of Pairs of Integers.
 */
public interface MapPosList {

    /**
     * This method represents the moving camera of the game.
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
    List<Pair<Integer, Integer>> getScreenTilePosition(int worldRow, int worldCol, int titleSize,
            int zombieWorldX, int zombieWorldY, int zombieScreenX,
            int zombieScreenY);

}
