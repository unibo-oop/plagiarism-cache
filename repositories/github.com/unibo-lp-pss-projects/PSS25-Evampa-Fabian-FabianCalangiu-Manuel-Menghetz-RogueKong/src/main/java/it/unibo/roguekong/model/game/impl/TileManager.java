package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.view.paths.Assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class represents the map manager. His job is to fill the map, return the tile type
 */
public class TileManager {
    private final static int ROWS = 20;
    private final static int COLS = 30;
    private final static int TILE_SIZE = 32;

    private Tile[] tileSet;
    private Tile[] tileSetBackground;
    private final int [][] gameMap;
    private final int [][] backgroundMap;

    /**
     * Create a new TileManager
     * @param mapPath is the map resource path
     * @param backgroundPath is the background map resource path
     */
    public TileManager(String mapPath, String backgroundPath) {
        this.gameMap = new int[ROWS][COLS];
        this.backgroundMap = new int[ROWS][COLS];

        this.fillTileSet();
        this.fillGameBackground(backgroundPath);
        this.fillGameMap(mapPath);
    }

    public Tile[] getTileSet() { return tileSet; }

    public Tile[] getTileSetBackground() { return tileSetBackground; }

    public int[][] getGameMap() { return gameMap; }

    public int[][] getBackgroundMap() { return backgroundMap; }

    public int getRows() { return ROWS; }

    public int getCols() { return COLS; }

    /**
     * Fill the tileSet array with the tile implementations
     */
    private void fillTileSet() {
        // World tiles
        Tile tile0 = new Tile("", TileType.VOID);
        Tile tile1 = new Tile(Assets.BRICK_WALL, true, false, TileType.PLATFORM);
        Tile tile2 = new Tile(Assets.GRASSY_SOIL, true, false, TileType.PLATFORM);
        Tile tile3 = new Tile(Assets.SOIL, true, false, TileType.PLATFORM);
        Tile tile4 = new Tile(Assets.WATER, false, true, TileType.WATER);
        Tile tile5 = new Tile(Assets.WATER_RIPPLES, false, true, TileType.WATER);
        Tile tile6 = new Tile(Assets.LADDER, TileType.LADDER);
        Tile tile7 = new Tile(Assets.TOP_TREE_BUSH, TileType.TREE);
        Tile tile8 = new Tile(Assets.MIDDLE_TREE_BUSH, TileType.TREE);
        Tile tile9 = new Tile(Assets.TREE_LOG, TileType.TREE);
        Tile tile10 = new Tile(Assets.LOWER_ORANGE_PORTAL, TileType.PORTAL);
        Tile tile11 = new Tile(Assets.UPPER_ORANGE_PORTAL, TileType.PORTAL);
        Tile tile12 = new Tile(Assets.LOWER_PURPLE_PORTAL, TileType.PORTAL);
        Tile tile13 = new Tile(Assets.UPPER_PURPLE_PORTAL, TileType.PORTAL);
        Tile tile14 = new Tile(Assets.SPIKES, false, true, TileType.SPIKE);
        Tile tile15 = new Tile(Assets.LAVA, false, true, TileType.LAVA);
        Tile tile16 = new Tile(Assets.LAVA_RIPPLES, false, true, TileType.LAVA);
        Tile tile17 = new Tile(Assets.BRIDGE, true, false, TileType.PLATFORM);
        Tile tile18 = new Tile(Assets.ORANGE_BUSH, TileType.VOID);
        Tile tile19 = new Tile(Assets.SAND, true, false, TileType.PLATFORM);
        Tile tile20 = new Tile(Assets.SANDY_ROCK, true, false, TileType.PLATFORM);
        Tile tile21 = new Tile(Assets.SANDY_SOIL, true, false, TileType.PLATFORM);
        Tile tile22 = new Tile(Assets.CACTUS, false, true, TileType.SPIKE);
        Tile tile23 = new Tile(Assets.SNOW, true, false, TileType.PLATFORM);
        Tile tile24 = new Tile(Assets.SNOWY_SOIL, true, false, TileType.PLATFORM);
        Tile tile25 = new Tile(Assets.ICE_SPIKE_BOTTOM, false, true, TileType.SPIKE);
        Tile tile26 = new Tile(Assets.ICE_SPIKE_TOP, false, true, TileType.SPIKE);
        Tile tile27 = new Tile(Assets.SNOWY_ROCK, true, false, TileType.PLATFORM);

        this.tileSet = new Tile[] { tile0, tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9,
                tile10, tile11, tile12, tile13, tile14, tile15, tile16,  tile17, tile18, tile19, tile20, tile21, tile22, tile23, tile24, tile25, tile26, tile27};

        // Background tiles
        Tile tileBG0 = new Tile(Assets.DARK_CLOUD, TileType.VOID);
        Tile tileBG1 = new Tile(Assets.DARK_FOGGY_SKY, TileType.VOID);
        Tile tileBG2 = new Tile(Assets.DARK_SKY, TileType.VOID);
        Tile tileBG3 = new Tile(Assets.STONE_SKY, TileType.VOID);
        Tile tileBG4 = new Tile(Assets.SUNNY_CLOUD, TileType.VOID);
        Tile tileBG5 = new Tile(Assets.SUNNY_FOGGY_SKY, TileType.VOID);
        Tile tileBG6 = new Tile(Assets.SUNNY_SKY, TileType.VOID);
        Tile tileBG7 = new Tile(Assets.GREY_CLOUD, TileType.VOID);
        Tile tileBG8 = new Tile(Assets.GREY_FOGGY_SKY, TileType.VOID);
        Tile tileBG9 = new Tile(Assets.GREY_SKY, TileType.VOID);

        this.tileSetBackground = new Tile[] { tileBG0, tileBG1, tileBG2, tileBG3, tileBG4, tileBG5, tileBG6, tileBG7, tileBG8, tileBG9};
    }

    /**
     * This method fill the matrix
     * @param mapPath is the mapPath file
     */
    private void fillGameMap(String mapPath){
        try(final InputStream mapFile = ClassLoader.getSystemResourceAsStream(mapPath)) {
            BufferedReader mapReader = new BufferedReader(new InputStreamReader(mapFile));

            int row = 0;
            int col = 0;

            while (row < ROWS && col < COLS) {
                String line = mapReader.readLine();

                while(col < COLS) {
                    String values[] = line.split(" ");
                    int n = Integer.parseInt(values[col]);
                    this.gameMap[row][col] = n;
                    col++;
                }
                if(col == COLS) {
                    col = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method fill the matrix
     * @param backgroundPath is the background map file
     */
    private void fillGameBackground(String backgroundPath){
        try(final InputStream backgroundFile = ClassLoader.getSystemResourceAsStream(backgroundPath)) {
            BufferedReader mapReader = new BufferedReader(new InputStreamReader(backgroundFile));

            int row = 0;
            int col = 0;

            while (row < ROWS && col < COLS) {
                String line = mapReader.readLine();

                while(col < COLS) {
                    String values[] = line.split(" ");
                    int n = Integer.parseInt(values[col]);
                    this.backgroundMap[row][col] = n;
                    col++;
                }
                if(col == COLS) {
                    col = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the tile by the player's X and Y
     * @param pos Position player. Usefully to check collisions
     * @return tileSet[index] is the kind of tile at position X and Y
     */
    public Tile getTileAtPosition(PositionImpl pos) {
        int row = (int) pos.getY() / TILE_SIZE;
        int col = (int) pos.getX() / TILE_SIZE;

        if(row < 0 || row >= ROWS
                || col < 0 || col >= COLS) { return this.tileSet[0]; }

        int index = this.gameMap[row][col];
        return tileSet[index];
    }
}
