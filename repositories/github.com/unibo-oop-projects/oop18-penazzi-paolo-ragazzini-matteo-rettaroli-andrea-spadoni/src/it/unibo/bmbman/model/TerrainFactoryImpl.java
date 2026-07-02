package it.unibo.bmbman.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import it.unibo.bmbman.model.entities.Block;
import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.entities.Tile;
import it.unibo.bmbman.model.entities.Wall;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;
/**
 * Implements the {@link TerrainFactory}.
 *
 */
public class TerrainFactoryImpl implements TerrainFactory {
    /**
     * The cell dimension.
     */
    public static final int  CELL_DIMENSION = 50;
    /**
     * Number of rows in terrain.
     */
    public static final int TERRAIN_ROWS = 15;
    /**
     * Number of columns in terrain.
     */
    public static final int TERRAIN_COLUMNS = 19;
    /**
     * The player start position.
     */
    public static final Position PLAYER_POSITION = new Position(1 * CELL_DIMENSION * ScreenToolUtils.SCALE, 1 * CELL_DIMENSION * ScreenToolUtils.SCALE);
    /**
     * The door position.
     */
    public static final Position DOOR_POSITION = new Position((TERRAIN_COLUMNS - 2) * CELL_DIMENSION * ScreenToolUtils.SCALE,
            (TERRAIN_ROWS - 2) * CELL_DIMENSION * ScreenToolUtils.SCALE);
    /**
     * Terrain's width.
     */
    public static final int TERRAIN_WIDTH = TERRAIN_COLUMNS * CELL_DIMENSION * ScreenToolUtils.SCALE;
    /**
     * Terrain's height.
     */
    public static final int TERRAIN_HEGHT = TERRAIN_ROWS * CELL_DIMENSION * ScreenToolUtils.SCALE;
    private static final Position PLAYER_POSITION_RIGHT = new Position(2 * CELL_DIMENSION * ScreenToolUtils.SCALE, 1 * CELL_DIMENSION * ScreenToolUtils.SCALE);
    private static final Position PLAYER_POSITION_DOWN = new Position(1 * CELL_DIMENSION * ScreenToolUtils.SCALE, 2 * CELL_DIMENSION * ScreenToolUtils.SCALE);
    private int blocksNumber;
    private List<List<Entity>> terrain;
    private List<Entity> blockList;
    /**
     * {@inheritDoc}
     */
    @Override
    public Terrain create(final int blocksnumber) {
        this.blocksNumber = blocksnumber;
        terrain = new ArrayList<>();
        blockList = new ArrayList<>();
        addTiles();
        addWalls();
        addBlocks();
        return new Terrain() {
            private final List<Position> freePosition = getFreeTiles().stream().map(t -> t.getPosition()).collect(Collectors.toList());
            private final List<Position> blockPowerUpPosition = getBlockPosition().stream().distinct().collect(Collectors.toList());
            @Override
            public List<Entity> getTiles() {
                final List<Entity> tiles = new ArrayList<>();
                terrain.stream()
                .forEach(e -> e.stream()
                        .filter(s -> s.getType() == EntityType.TILE)
                        .forEach(k -> tiles.add(k)));
                return tiles;
            }
            @Override
            public Position getRandomBlockPosition() {
                final int randomIndex = new Random().nextInt(blockPowerUpPosition.size()); 
                final Position pos = new Position(blockPowerUpPosition.get(randomIndex).getX() / ScreenToolUtils.SCALE,
                        blockPowerUpPosition.get(randomIndex).getY() / ScreenToolUtils.SCALE);
                blockPowerUpPosition.remove(randomIndex);
                return pos;
            }
            @Override
            public List<Entity> getFreeTiles() {
                return this.getTiles().stream().filter(i -> !i.getPosition().equals(PLAYER_POSITION) 
                        && !i.getPosition().equals(PLAYER_POSITION_RIGHT)
                        && !i.getPosition().equals(PLAYER_POSITION_DOWN)
                        && !i.getPosition().equals(DOOR_POSITION)
                        && !getBlockPosition().contains(i.getPosition()))
                        .collect(Collectors.toList());
            }
            private List<Position> getBlockPosition() {
                return getBlocks().stream().map(b -> b.getPosition()).collect(Collectors.toList());
            }
            @Override
            public Position getFreeRandomPosition() {
                final int randomIndex = new Random().nextInt(freePosition.size()); 
                final Position pos = new Position(freePosition.get(randomIndex).getX() / ScreenToolUtils.SCALE,
                        freePosition.get(randomIndex).getY() / ScreenToolUtils.SCALE);
                freePosition.remove(randomIndex);
                return pos;
            }
            @Override
            public Entity getEntity(final int x, final int y) {
                return terrain.get(x).get(y);
            }
            @Override
            public List<Entity> getBlocks() {
                return blockList;
            }
        };
    }
    private void addTiles() {
        for (int i = 0; i < TERRAIN_COLUMNS; i++) {
            final List<Entity> col = new ArrayList<>();
            for (int j = 0; j < TERRAIN_ROWS; j++) {
                col.add(new Tile(new Position(i * CELL_DIMENSION, j * CELL_DIMENSION), new Dimension(CELL_DIMENSION, CELL_DIMENSION)));
            }
            this.terrain.add(col);
        }
    }
    private void addWalls() {
        for (int i = 0; i < TERRAIN_COLUMNS; i++) {
            for (int j = 0; j < TERRAIN_ROWS; j++) {
                addBorderWall(i, j, this.terrain.get(i));
            }
        }
        for (int i = 0; i < TERRAIN_COLUMNS; i = i + 2) {
            addWall(terrain.get(i), i);
        }
    }
    private List<Entity> addBorderWall(final int column, final int row, final  List<Entity> entityList) {
        if (row == 0 || column == 0 || column == TERRAIN_COLUMNS - 1 || row == TERRAIN_ROWS - 1) {
            entityList.set(row, new Wall(new Position(column * CELL_DIMENSION, row * CELL_DIMENSION), new Dimension(CELL_DIMENSION, CELL_DIMENSION)));
        }
        return entityList;
    }
    private void addWall(final List<Entity> entityList, final int col) {
        for (int i = 0; i < TERRAIN_ROWS; i = i + 2) {
            entityList.set(i, new Wall(new Position(col * CELL_DIMENSION, i * CELL_DIMENSION), new Dimension(CELL_DIMENSION, CELL_DIMENSION)));
        }
    }

    private void addBlocks() {
        blockList.add(new Block(new Position(PLAYER_POSITION_RIGHT.getX() / ScreenToolUtils.SCALE + CELL_DIMENSION,
                PLAYER_POSITION_RIGHT.getY() / ScreenToolUtils.SCALE), new Dimension(CELL_DIMENSION, CELL_DIMENSION)));
        blockList.add(new Block(new Position(PLAYER_POSITION_DOWN.getX() / ScreenToolUtils.SCALE,
                PLAYER_POSITION_DOWN.getY() / ScreenToolUtils.SCALE + CELL_DIMENSION), new Dimension(CELL_DIMENSION, CELL_DIMENSION)));
        IntStream.iterate(0, i -> i + 1)
        .limit(blocksNumber)
        .forEach((i) -> blockList.add(new Block(new Position(new Random().nextInt(TERRAIN_COLUMNS - 1) * CELL_DIMENSION,
                new Random().nextInt(TERRAIN_ROWS - 1) * CELL_DIMENSION), new Dimension(CELL_DIMENSION, CELL_DIMENSION))));
        checksBlock();
    }
    private void checksBlock() {
        this.blockList = this.blockList.stream()
                .filter(s -> !s.getPosition().equals(PLAYER_POSITION)
                        && !s.getPosition().equals(PLAYER_POSITION_RIGHT)
                        && !s.getPosition().equals(PLAYER_POSITION_DOWN)
                        && !s.getPosition().equals(DOOR_POSITION))
                .collect(Collectors.toList());
        this.blockList = this.blockList.stream().filter(s -> !getWallsPosition().contains(s.getPosition())).collect(Collectors.toList());
    }
    private List<Entity> getWalls() {
        final List<Entity> walls = new ArrayList<>();
        this.terrain.stream()
        .forEach(e -> e.stream()
                .filter(s -> s.getType() == EntityType.WALL)
                .forEach(k -> walls.add(k)));
        return walls;
    }
    private List<Position> getWallsPosition() {
        return getWalls().stream().map(i -> i.getPosition()).collect(Collectors.toList());
    }
}
