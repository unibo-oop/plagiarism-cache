package zombietsunami.view.mapview.api;

import java.util.List;

/**
 * Allows you to sets the imgages to their relative tiles.
 */
public interface TileImage {

    /**
     * @param element is the list of element of tiles
     * @return a List where each position of the List (the element's number of the
     *         Tile) has their associated Tile
     */
    List<Tile> getTileImage(List<String> element);
}
