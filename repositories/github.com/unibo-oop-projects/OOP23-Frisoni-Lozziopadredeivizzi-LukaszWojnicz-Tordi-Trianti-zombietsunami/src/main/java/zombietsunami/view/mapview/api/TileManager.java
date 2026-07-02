package zombietsunami.view.mapview.api;

import java.awt.Graphics2D;
import java.util.List;

import zombietsunami.Pair;
import zombietsunami.view.api.VController;

/**
 * Allows you to manage the game map's tile and to draw them.
 */
public interface TileManager {

    /**
     * method to drow the rispective tile of the game map.
     * 
     * @param g2         graphic 2D to drow the tile
     * @param titleSize  title's size for the game
     * @param element    list of tile's elements
     * @param mapIndex   list of the game map values
     * @param tilePos    list of the tile's positions
     * @param controller the view controller
     */
    void drawMap(Graphics2D g2, int titleSize, List<String> element, List<Integer> mapIndex,
            List<Pair<Integer, Integer>> tilePos, VController controller);

}
