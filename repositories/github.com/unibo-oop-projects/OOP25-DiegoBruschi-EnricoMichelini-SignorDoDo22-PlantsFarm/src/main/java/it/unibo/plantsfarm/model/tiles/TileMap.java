package it.unibo.plantsfarm.model.tiles;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.plantsfarm.model.garden.SoilSaving;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;

/**
 * Used to load the map from a file and to create the logic map used to manage the game.
 */
public final class TileMap {

    private static final int ORNAMENTAL_SOIL = 2;
    private static final int NORMAL_SOIL = 11;

    private static final int WALL = 3;
    private static final int TREE = 4;

    private static final int BEGIN_SHOP_FIRST_ROW = 22;
    private static final int END_SHOP_FIRST_ROW = 26;
    private static final int BEGIN_SHOP_SECOND_ROW = 31;
    private static final int END_SHOP_SECOND_ROW = 35;
    private static final int BEGIN_SHOP_THIRD_ROW = 40;
    private static final int END_SHOP_THIRD_ROW = 45;
    private static final int BEGIN_SHOP_FOURTH_ROW = 48;
    private static final int END_SHOP_FOURTH_ROW = 54;
    private static final int BEGIN_SHOP_FIFTH_ROW = 58;
    private static final int END_SHOP_FIFTH_ROW = 60;

    private static final int WELL_FIRST_ROW = 66;
    private static final int BEGIN_WELL_SECOND_ROW = 68;
    private static final int END_WELL_SECOND_ROW = 70;
    private static final int WELL_THIRD_ROW = 72;

    private static final Logger LOGGER = Logger.getLogger(TileMap.class.getName());

    private final List<SoilImpl> soilList = new LinkedList<>();
    private final List<SolidBlock> solidBlocks = new LinkedList<>();

    private final int[][] logicMap1;
    private final SoilSaving saveController = new SoilSaving();

    /**
     * Creates a new TileMap.
     */
    public TileMap() {
        this.logicMap1 = new int[ImplViewGamePanel.MAX_WORLD_ROW][ImplViewGamePanel.MAX_WORLD_COL];
    }

    /**
     * Loads the map from a file and initializes the logic map.
     * 
     * @param filePath The path to the map file.
     */
    public void loadMap(final String filePath) {
        this.soilList.clear();
        this.solidBlocks.clear();

        try (InputStream is = getClass().getResourceAsStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            for (int row = 0; row < ImplViewGamePanel.MAX_WORLD_ROW; row++) {
                final String line = br.readLine();
                if (line == null) {
                    break;
                }

                final String[] numbers = line.split(" ");

                for (int col = 0; col < ImplViewGamePanel.MAX_WORLD_COL; col++) {
                    if (col >= numbers.length) {
                        break;
                    }

                    final int num = Integer.parseInt(numbers[col]);
                    this.logicMap1[row][col] = num;
                    final int worldX = col * ImplViewGamePanel.TILE_SIZE;
                    final int worldY = row * ImplViewGamePanel.TILE_SIZE;
                    final int size = ImplViewGamePanel.TILE_SIZE;

                    if (isSoil(num)) {
                        this.soilList.add(new SoilImpl(new Rectangle(worldX, worldY, size, size), num));
                    }

                    if (isSolid(num)) {
                        this.solidBlocks.add(new SolidBlock(new Rectangle(worldX, worldY, size, size)));
                    }
                }
            }
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Errore durante il caricamento della mappa", e);
        }

        applySavedData();
    }

    /**
     * Applies the saved data to the current map, updating the soil list with the saved progress.
     */
    private void applySavedData() {
        final List<SoilImpl> savedProgress = saveController.loadGame();
        if (savedProgress == null) {
            return;
        }

        for (final SoilImpl saved : savedProgress) {
            for (int i = 0; i < soilList.size(); i++) {
                final SoilImpl current = soilList.get(i);
                if (current.getCoordinate().x == saved.getCoordinate().x
                    && current.getCoordinate().y == saved.getCoordinate().y
                ) {
                    this.soilList.set(i, saved);
                    break;
                }
            }
        }
    }

    /**
     * Checks if the given tile number corresponds to a soil tile.
     * 
     * @param num The tile number to check.
     * @return true if the tile number corresponds to a soil tile, false otherwise.
     */
    public boolean isSoil(final int num) {
        return num == ORNAMENTAL_SOIL || num == NORMAL_SOIL;
    }

    /**
     * Checks if the given tile number corresponds to a solid tile.
     * 
     * @param num The tile number to check.
     * @return true if the tile number corresponds to a solid tile, false otherwise.
     */
    public boolean isSolid(final int num) {
        return num == WALL || num == TREE
            || num >= BEGIN_SHOP_FIRST_ROW && num <= END_SHOP_FIRST_ROW 
            || num >= BEGIN_SHOP_SECOND_ROW && num <= END_SHOP_SECOND_ROW
            || num >= BEGIN_SHOP_THIRD_ROW && num <= END_SHOP_THIRD_ROW
            || num >= BEGIN_SHOP_FOURTH_ROW && num <= END_SHOP_FOURTH_ROW
            || num >= BEGIN_SHOP_FIFTH_ROW && num <= END_SHOP_FIFTH_ROW
            || num == WELL_FIRST_ROW
            || num >= BEGIN_WELL_SECOND_ROW && num <= END_WELL_SECOND_ROW
            || num == WELL_THIRD_ROW;
    }

    /**
     * Gets the list of soil tiles.
     * 
     * @return The list of soil tiles.
     */
    public List<SoilImpl> getSoilList() {
        return List.copyOf(this.soilList);
    }

    /**
     * Gets the list of solid blocks.
     * 
     * @return The list of solid blocks.
     */
    public List<SolidBlock> getSolidBlocks() {
        return List.copyOf(this.solidBlocks);
    }

    /**
     * Gets the tile ID at the specified row and column in the logic map.
     * 
     * @param row The row index of the tile.
     * @param col The column index of the tile.
     * @return The tile ID at the specified position, or 0 if the position is out of bounds.
     */
    public int getTileId(final int row, final int col) {
        if (row >= 0 && row < ImplViewGamePanel.MAX_WORLD_ROW && col >= 0 && col < ImplViewGamePanel.MAX_WORLD_COL) {
            return logicMap1[row][col];
        }
        return 0;
    }
}
