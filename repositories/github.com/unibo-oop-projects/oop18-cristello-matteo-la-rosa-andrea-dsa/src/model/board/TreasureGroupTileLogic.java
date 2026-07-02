package model.board;

import java.util.List;

/**
 * This interface is useful to menage the group of fallen treasure.
 */

public interface TreasureGroupTileLogic {
    /**
     * This method create and return a treasure grouped list of tile.
     * 
     * @return a list of tile
     */

    List<Tile> getFallenTreasureGroupedList();

}
