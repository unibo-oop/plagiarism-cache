package it.unibo.pacman.model.mapeditor;

import static it.unibo.pacman.model.utilities.EntityType.BLINKY;
import static it.unibo.pacman.model.utilities.EntityType.CLYDE;
import static it.unibo.pacman.model.utilities.EntityType.EMPTY;
import static it.unibo.pacman.model.utilities.EntityType.INKY;
import static it.unibo.pacman.model.utilities.EntityType.PACMAN;
import static it.unibo.pacman.model.utilities.EntityType.PINKY;
import static it.unibo.pacman.model.utilities.EntityType.WALL;

import it.unibo.pacman.model.utilities.Position;

/**
 * Builder for the fixed grid.
 *
 */
public class MapBuilder {

    private final int rows;
    private final int columns;
    private final int centerX;;
    private final int centerY;
    private final Position pacmanInitialPos;
    private final Position pinkyInitialPos;
    private final Position blinkyInitialPos;
    private final Position inkyInitialPos;
    private final Position clydeInitialPos;

    private final FixedGridImpl grid;

    /**
     * It creates a builder.
     * @param rows the number of rows
     * @param columns the number of columns
     */
    public MapBuilder(final int rows, final int columns) {
        grid = new FixedGridImpl(rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.centerX = (columns - 1) / 2;
        this.centerY = (rows - 1) / 2;
        this.pacmanInitialPos = new Position(this.centerX, this.centerY + 2);
        this.pinkyInitialPos = new Position(this.centerX, this.centerY - 1);
        this.blinkyInitialPos = new Position(this.centerX, this.centerY);
        this.inkyInitialPos = new Position(this.centerX - 1, this.centerY);
        this.clydeInitialPos = new Position(this.centerX + 1, this.centerY);
    }

    /**
     * Add fixed borders and portals to the grid.
     * 
     * @return the MapBuilder
     */
    public MapBuilder addFixedBordersWithPortals() {
        this.addFixedBorders();
        this.addFixedPortals();
        return this;
    }

    //RENDERE PUBLIC METTENDOLI OVUNQUE E NEL "WITHPORTALS" CHIAMARE PRIMA PORTALS
    private void addFixedBorders() {
        for (int y = 1; y < rows - 1; y++) {
            if (y != this.centerY) {
                grid.setFixedEntity(0, y, WALL);
                grid.setFixedEntity(this.columns - 1, y, WALL);
            }
        }
        for (int x = 0; x < this.columns; x++) {
            grid.setFixedEntity(x, 0, WALL);
            grid.setFixedEntity(x, this.rows - 1, WALL);
        }
    }

    private void addFixedPortals() {
        for (int xLeft = 0; xLeft < 3; xLeft++) {
            grid.setFixedEntity(xLeft, this.centerY - 1, WALL);
            grid.setFixedEntity(xLeft, this.centerY, EMPTY);
            grid.setFixedEntity(xLeft, this.centerY + 1, WALL);
        }
        for (int xRight = this.columns - 3; xRight < this.columns; xRight++) {
            grid.setFixedEntity(xRight, this.centerY - 1, WALL);
            grid.setFixedEntity(xRight, this.centerY, EMPTY);
            grid.setFixedEntity(xRight, this.centerY + 1, WALL);
        }
    }

    /**
     * Add a fixed center to the grid.
     * 
     * @return the MapBuilder
     */
    public MapBuilder addFixedCenter() {
        grid.setFixedEntity(this.pacmanInitialPos.getX(), this.pacmanInitialPos.getY(), PACMAN);
        grid.setFixedEntity(this.pinkyInitialPos.getX(), this.pinkyInitialPos.getY(), PINKY);
        grid.setFixedEntity(this.blinkyInitialPos.getX(), this.blinkyInitialPos.getY(), BLINKY);
        grid.setFixedEntity(this.inkyInitialPos.getX(), this.inkyInitialPos.getY(), INKY);
        grid.setFixedEntity(this.clydeInitialPos.getX(), this.clydeInitialPos.getY(), CLYDE);
        for (int y = this.centerY - 1; y <= this.centerY; y++) {
            grid.setFixedEntity(this.centerX - 2, y, WALL);
            grid.setFixedEntity(this.centerX + 2, y, WALL);
        }
        for (int x = this.centerX - 2; x <= this.centerX + 2; x++) {
            grid.setFixedEntity(x, this.centerY + 1, WALL);
        }
        for (int y = this.centerY - 2; y <= this.centerY + 2; y++) {
            for (int x = this.centerX - 3; x <= this.centerX + 3; x++) {
                if (grid.isPositionFixed(x, y)) {
                    // Necessary to set EMPTY as unmodifiable
                    grid.setFixedEntity(x, y, EMPTY);
                }
            }
        }
        return this;
    }

    /**
     * 
     * @return a fixed grid
     */
    public FixedGridImpl getGrid() {
        return grid;
    }

}
