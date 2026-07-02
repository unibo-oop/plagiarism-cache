package model.world;

import model.caster.CastClass;
import model.caster.CastClassImp;
import model.direction.Direction;
import model.entity.Entity;
import model.entity.EntityTypeNameEnum;
import model.entity.stone.Stone;
import model.square.SquareImp;
import model.entity.cell.Cell;
import model.entity.cell.CellTypeNameEnum;
import model.entity.cell.cellDead.CellDead;
import model.entity.cell.cellDead.CellDeadImpl;
import model.entity.cell.standard.CellStandard;
import utilities.Pair;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class WorldImp implements World {

    /**
     * used to cast classes.
     */
    private static CastClass cast = new CastClassImp();

    /**
     * the map that contains the grid with all the cells.
     */
    private static Map<Pair<Integer, Integer>, SquareImp> map = new HashMap<>();
    /**
     * tracks number of alive cells in all the world.
     */
    private static int counterAliveCells = 0;

    /**
     * tracks number of death cells in all the world.
     */
    private static int counterDeadCells = 0;

    /**
     * size of the world.
     */
    private Dimension size;

    /**
     * start the world, add stones in the height borders and add some cells in random positions.
     * @param height
     * @param width
     */
    public WorldImp(final int height, final int width) {
        this.size = new Dimension(height, width);
         for (int w = 0; w < this.size.width; w++) {
            for (int h = 0; h < this.size.height; h++) {
                SquareImp sq = new SquareImp(Optional.empty());
                WorldImp.map.put(new Pair<>(w, h), sq);
            }
        }
         for (int w = 0; w < this.size.width; w++) {
            this.getSquare(w, this.size.height - 1).setEntity(Optional.of(new Stone(w, this.size.height - 1)));
        }
    }

    @Override
    public final void makeCellDeath(final int x, final int y) {
        final Entity celldead = new CellDeadImpl(x, y);
        this.getSquare(x, y).setEntity(Optional.of(celldead));
        WorldImp.counterDeadCells++;
        WorldImp.counterAliveCells--;
    }

    @Override
    public final void removeCell(final int x, final int y) {
        if (this.isThereAnything(x, y)) {
            if (cast.cellCast(this.getSquare(x, y).getEntity().get()) instanceof CellDead) {
                WorldImp.counterDeadCells--;
            } else {
                WorldImp.counterAliveCells--;
            }
            this.getSquare(x, y).setEntity(Optional.empty());
        } else {
            throw new IllegalStateException("no cell here!");
        }
    }

    @Override
    public final void tryMoveCell(final int x, final int y, final Direction direction) {
        int w = x + direction.movementAlongX();
        int h = y + direction.movementAlongY();
        if (this.getSquare(x, y).getEntity().get() instanceof Cell) {
            if (w >= this.size.width || w < 0) {
                w = Math.abs(Math.abs(w) - this.size.width);
            }
            if (!WorldImp.map.containsKey(new Pair<>(w, h))) {
                return;
            }
            if (!this.isThereAnything(w, h)) {
                    this.getSquare(w, h).setEntity(Optional.of(this.getSquare(x, y).getEntity().get()));
                    cast.cellCast(this.getSquare(w, h).getEntity().get()).setX(w);
                    cast.cellCast(this.getSquare(w, h).getEntity().get()).setY(h);
                    this.getSquare(x, y).setEntity(Optional.empty());
            } 
        }
    }

    @Override
    public final boolean isThereFreeSpaceAround(final int x, final int y) {
        for (int w = x - 1; w <= x + 1; w++) {
            for (int h = y - 1; h <= y + 1; h++) {
                if (w == x && h == y) {
                    continue;
                }
                if (h < 0) {
                    continue;
                }
                int w1 = w;
                if (w1 < 0) {
                    w1 = this.size.width - 1;
                }
                if (w1 >= this.size.width) {
                    w1 = 0;
                }
                if (WorldImp.map.containsKey(new Pair<>(w1, h))) {
                    if (!this.isThereAnything(w1, h)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public final void putChild(final int x, final int y, final CellStandard cell, final Direction direction) {
        if (!this.isThereAnything(x, y)) {
            throw new IllegalStateException("no cell mother");
        }
        int w = x + direction.movementAlongX();
        if (w > this.size.width - 1 || w < 0) {
            w = Math.abs(Math.abs(w) - this.size.width);
        }
        int h = y + direction.movementAlongY();
        if (h >= this.size.height - 1 || h < 0) {
            Pair<Integer, Integer> p = this.spaceAroundCell(x, y);
            if (WorldImp.map.containsKey(p)) {
                    WorldImp.map.get(p).setEntity(Optional.of(cell));
                    WorldImp.counterAliveCells++;
                    cell.setX(p.getX());
                    cell.setY(p.getY());
            }
            return;
        }
        if (!this.isThereAnything(w, h)) {
            this.getSquare(w, h).setEntity(Optional.of(cell));
            cell.setX(w);
            cell.setY(h);
            WorldImp.counterAliveCells++;
        } else {
            Pair<Integer, Integer> p = this.spaceAroundCell(x, y);
            if (WorldImp.map.containsKey(p)) {
                WorldImp.map.get(p).setEntity(Optional.of(cell));
                WorldImp.counterAliveCells++;
                cell.setX(p.getX());
                cell.setY(p.getY());
            }
        }
    }

    @Override
    public final EntityTypeNameEnum getType(final int x, final int y) {
        if (this.isThereAnything(x, y)) {
            return this.getSquare(x, y).getEntity().get().getEntityType();
        }
        throw new IllegalStateException("not an entity!");
    }

    @Override
    public final CellTypeNameEnum getCellType(final int x, final int y) {
        if (this.getType(x, y) == EntityTypeNameEnum.CELL) {
            return cast.cellCast(this.getSquare(x, y).getEntity().get()).getCellTypeName();
        }
            throw new IllegalStateException("not a cell!");
    }

    /** 
     * @param x width
     * @param y height
     * @return the coordinates of the first free square found around the given cell position
     * if it doesn't find anything, returns the given coordinates
     */
    private Pair<Integer, Integer> spaceAroundCell(final int x, final int y) {
        for (int w = x - 1; w <= x + 1; w++) {
            for (int h = y - 1; h <= y + 1; h++) {
                if (w == x && h == y) {
                    continue;
                }
                int w1 = w;
                if (w1 < 0) {
                    w1 = this.size.width - 1;
                } else if (w1 >= this.size.width) {
                    w1 = 0;
                }
                if (h >= this.size.height - 1 || h < 0) {
                    continue;
                }
                if (WorldImp.map.containsKey(new Pair<>(w1, h))) {
                    if (!this.isThereAnything(w1, h)) {
                        return new Pair<>(w1, h);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public final Map<Pair<Integer, Integer>, SquareImp> getMap() {
        return WorldImp.map;
    }


    @Override
    public final int getWorldHeight() {
        return this.size.height;
    }

    @Override
    public final  int getWorldWidth() {
        return this.size.width;
    }

    @Override
    public final SquareImp getSquare(final int x, final int y) throws NoSquareException {
        int w = x;
        if (y < this.size.height && y >= 0) {
            if (x >= this.size.width || x < 0) {
                w = Math.abs(Math.abs(w) - this.size.width);
            }
        return WorldImp.map.get(new Pair<>(w, y));
        }
        throw new NoSquareException("no square in this position!");
    }

    @Override
    public final int getAliveCells() {
        return WorldImp.counterAliveCells;
    }

    @Override
    public final int getDeadCells() {
        return WorldImp.counterDeadCells;
    }

    @Override
    public final void putCell(final int x, final int y, final Entity cell) throws OccupedSquareException {
        if (!this.isThereAnything(x, y)) {
            this.getSquare(x, y).setEntity(Optional.of(cell));
            WorldImp.counterAliveCells++;
        }   else {
            throw new OccupedSquareException("position already taken");
        }
    }

    @Override
    public final boolean isThereAnything(final int x, final int y) {
        if (this.getSquare(x, y).getEntity().isPresent()) {
            return true;
        }
        return false;

    }

}
