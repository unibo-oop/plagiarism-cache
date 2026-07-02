package it.unibo.biscia.core;

import it.unibo.biscia.core.Level.LevelManaged;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class LevelImpl implements LevelManaged {

    private int level;
    private final List<List<Cell>> cells;

    private final int cols;
    private final int rows;
    private int cardinal;
    private final Set<Entity.EntityManaged> entities = new HashSet<>();
    private LevelAnalyzer analyzer;
    private InteractionManager interactor;

    /**
     * basic constructor for empty board at min size.
     */
    protected LevelImpl() {
        this(Level.MIN_COLS, Level.MIN_ROWS);
    }

    /**
     * basic constructor for empty board.
     * 
     * @param cols number of columns
     * @param rows number of rows
     */
    protected LevelImpl(final int cols, final int rows) {
        if (cols <= 0 || rows <= 0) {
            throw new IllegalArgumentException();
        }
        this.cols = cols;
        this.rows = rows;
        this.level = 0;
        this.cells = IntStream.range(0, cols).mapToObj(
                c -> IntStream.range(0, rows).mapToObj(r -> (Cell) new CellImpl(c, r)).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    /**
     * basic constructor for empty board.
     * 
     * @param cols     number of columns
     * @param rows     number of rows
     * @param entities to start
     */
    protected LevelImpl(final int cols, final int rows, final List<Entity.EntityManaged> entities) {
        this(cols, rows);
        for (final var entity : entities) {
            this.addEntity(entity);
        }
    }

    @Override
    public final int getCols() {
        return this.cols;
    }

    @Override
    public final int getRows() {
        return this.rows;
    }

    /**
     * if coord exceed max o is minor of zero return a value for loop to other side.
     * 
     * @param coord just coordinate
     * @param max   upper bound (not included)
     * @return
     */
    private int rotateCoord(final int coord, final int max) {
        int c = coord;
        while (c < 0) {
            c = c + max;
        }
        return c % max;
    }

    protected int rotateRow(final int row) {
        return this.rotateCoord(row, this.rows);
    }

    protected int rotateCol(final int col) {
        return this.rotateCoord(col, this.cols);
    }

    @Override
    public int getCardinal() {
        return this.cardinal;
    }

    @Override
    public List<Entity> getEntities() {
        return Collections.unmodifiableList(this.entities.stream().map(e -> (Entity) e).collect(Collectors.toList()));
    }

    @Override
    public List<Entity.EntityManaged> getEntitiesManaged() {
        return Collections.unmodifiableList(this.entities.stream().collect(Collectors.toList()));
    }

    @Override
    public void setCardinal(final int cardinal) {
        this.cardinal = cardinal;
    }

    protected final void setLevel(final int level) {
        this.level = level;
    }

    @Override
    public final void addEntity(final Entity.EntityManaged entity) {
        final Set<Cell> occ = this.getEntities().stream().flatMap(e -> e.getCells().stream())
                .collect(Collectors.toSet());
        entity.getCells().forEach(c -> {
            if (occ.contains(c)) {
                throw new IllegalArgumentException();
            }
        });

        this.entities.add(entity);
    }

    @Override
    public String toString() {
        return "Board [level=" + this.level + ", Cols=" + this.getCols() + ", Rows=" + this.getRows() + " Entities="
                + this.getEntities() + "]";
    }

    @Override
    public final List<Cell> getCells() {
        return Collections.unmodifiableList(this.cells.stream().flatMap(l -> l.stream()).collect(Collectors.toList()));
    }

    @Override
    public Cell getCell(final int col, final int row) {
        return this.cells.get(this.rotateCol(col)).get(this.rotateRow(row));
    }

    @Override
    public final void removeEntity(final Entity.EntityManaged entity) {
        this.entities.remove(entity);
    }

    @Override
    public final Cell getSideCell(final int col, final int row, final Direction direction) {
        return this.getCell(col + direction.getStepCol(), row + direction.getStepRow());
    }

    @Override
    public final Cell getSideCell(final Cell cell, final Direction direction) {
        return this.getSideCell(cell.getCol(), cell.getRow(), direction);
    }

    @Override
    public List<Cell> getArea(final Cell cell, final int width, final int height) {
        final int w = Math.abs(width);
        final int h = Math.abs(height);
        if (w > this.getCols() || h > this.getRows()) {
            throw new IllegalArgumentException();
        }
        return Stream.iterate(cell, c -> this.getSideCell(c, width > 0 ? Direction.RIGHT : Direction.LEFT)).limit(w)
                .flatMap(c -> Stream.iterate(c, r -> this.getSideCell(r, height > 0 ? Direction.DOWN : Direction.UP))
                        .limit(h))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cell> getArea(final Cell cell1, final Cell cell2) {
        return this.getArea(cell1, cell2.getCol() - cell1.getCol() + 1, cell2.getRow() - cell1.getRow() + 1);
    }

    @Override
    public LevelAnalyzer getAnalyzer() {
        if (Objects.isNull(this.analyzer)) {
            return new LevelAnalyzerImpl(this);
        }
        return this.analyzer;
    }

    @Override
    public InteractionManager getInteractionManager() {
        if (Objects.isNull(this.interactor)) {
            return new InteractionManagerlImpl(this);
        }
        return this.interactor;

    }

}
