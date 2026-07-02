package model.board;
/**
 * Empty Tile Class. 
 *
 */
public class EmptyTile extends TreasureTile implements Tile {
    /**
     * EmptyTileConstructor. 
     */
    public EmptyTile() {
        super(0, TileType.EMPTY.tileType());
    }

}
