package zombietsunami.model.mapmodel.api;

import java.util.List;

/**
 * This interface allows you to get a List of the tile's elements of the whole
 * game map by putting their file's name into the right index, the same as their
 * assigned value on the map.
 */
public interface TileElement {

    /**
     * @return a List of strings, where the strings are the file's name of the
     *         tiles, putten into their respective index.
     */
    List<String> getTileElement();
}
