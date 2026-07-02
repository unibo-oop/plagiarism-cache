package it.unibo.model.tiles.tileset;

import java.util.List;

import it.unibo.model.tiles.Tile;

/**
 * An interface representing a collection of tiles obtained from a TMX file.
 * Implementing classes should provide methods to access and manipulate the TileSet.
 */
public interface TileSet {

    /**
     * Reads the TMX file, parses it, and extracts tiles.
     * 
     * @return A list of Tile objects extracted from the TMX file.
     */
    List<Tile> read();

}
