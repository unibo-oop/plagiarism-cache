package model.board;


/**
 * Treasure group tile, usefull for game purpose, this rapresent a group of threasure fallen down from players.
 */


public class TreasureGroupTile extends TreasureTile implements Tile {

    /**
     * Constructor for the group of Tile.
     */

    /**
     * @param firstTile the First Tile
     * @param secondTile the Second Tile
     * @param thirdTile the Third Tile
     */
    public TreasureGroupTile(final int firstTile, final int secondTile, final int thirdTile) {

        super(firstTile + secondTile + thirdTile, TileType.TREASUREGROUP.tileType());
    }




}
