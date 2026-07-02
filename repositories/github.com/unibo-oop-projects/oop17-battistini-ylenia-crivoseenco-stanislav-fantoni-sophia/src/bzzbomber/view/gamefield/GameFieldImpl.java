package bzzbomber.view.gamefield;

import java.awt.Point;
import java.util.Arrays;

/**
 * Represents an implementation of Game field grid 2D of Tiles.
 */
public class GameFieldImpl implements GameField {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final int rows;
    private final int columns;
    private final Tile[][] gameMap;

    /**
     * @param r
     *            Number of rows of this grid.
     * @param c
     *            Number of columns of this grid.
     */
    public GameFieldImpl(final int r, final int c) {
        this.rows = r;
        this.columns = c;
        this.gameMap = new Tile[this.rows][this.columns];
    }

    @Override
    public final void addTile(final Point p, final Tile t) {
        if (p != null) {
            this.gameMap[(int) p.getY()][(int) p.getX()] = t;
        }
    }

    @Override
    public final void removeTile(final Point p) {
        if (p != null) {
            this.gameMap[(int) p.getY()][(int) p.getX()] = null;
        }
    }

    @Override
    public final void cleanMap() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.gameMap[i][j] = null;
            }
        }
    }

    @Override
    public final Tile getTile(final Point p) {
        if (p != null) {
            return this.gameMap[(int) p.getY()][(int) p.getX()];
        }
        return null;
    }

    @Override
    public final Tile[][] getMap() {
        return Arrays.copyOf(this.gameMap, this.gameMap.length);
    }

    @Override
    public final int getRows() {
        return this.rows;
    }

    @Override
    public final int getColumns() {
        return this.columns;
    }

}
