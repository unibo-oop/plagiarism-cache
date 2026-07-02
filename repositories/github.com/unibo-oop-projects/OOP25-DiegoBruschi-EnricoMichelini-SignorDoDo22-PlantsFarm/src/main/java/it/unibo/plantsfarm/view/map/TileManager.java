package it.unibo.plantsfarm.view.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.utility.SpriteLoader;

/**
 * Used to load game map from a file by assigning an index
 * to every image then used to represent a tile.
 */
public final class TileManager {

    private static final int TILE_ARRAY_SIZE = 2000;

    private static final int ASSET_ORIGINAL_TILE_SIZE = 16;
    private static final int ASSET_SCALE = 3;
    private static final int ASSET_ACTUAL_SIZE = ASSET_ORIGINAL_TILE_SIZE * ASSET_SCALE;

    private static final int FIRST_SHOP_TILE_INDEX = 20;
    private static final Logger LOGGER = Logger.getLogger(TileManager.class.getName());

    private ImplViewGamePanel gp;
    private final Tile[] tile;
    private final int[][] mapTileNum;
    private int tileIndex;

    /**
     * Creates a new TileManager.
     * 
     * @param gp GamePanel passed to then set dimensions and draw tiles.
     */
    public TileManager(final ImplViewGamePanel gp) {
        setGp(gp);
        this.tile = new Tile[TILE_ARRAY_SIZE];
        this.mapTileNum = new int[ImplViewGamePanel.MAX_WORLD_COL][ImplViewGamePanel.MAX_WORLD_ROW];

        setupTileImage();
        loadMap("/maps/map.txt");
    }

    /**
     * Assigns every texture in the resources path to and index.
     */
    public void setupTileImage() {
        setupTile(tileIndex, "grass.png");
        tileIndex++;
        setupTile(tileIndex, "pathVer.png");
        tileIndex++;
        setupTile(tileIndex, "dirtOrnamental.png");
        tileIndex++;
        setupTile(tileIndex, "wall.png");
        tileIndex++;
        setupTile(tileIndex, "tree.png");
        tileIndex++;
        setupTile(tileIndex, "spawn.png");
        tileIndex++;
        setupTile(tileIndex, "flowers.png");
        tileIndex++;
        setupTile(tileIndex, "floor.png");
        tileIndex++;
        setupTile(tileIndex, "lessFlowers.png");
        tileIndex++;
        setupTile(tileIndex, "pathOri.png");
        tileIndex++;
        setupTile(tileIndex, "path.png");
        tileIndex++;
        setupTile(tileIndex, "dirtContained.png");
        tileIndex = FIRST_SHOP_TILE_INDEX;

        // Shop slicing and loading
        final int numColonne = 9;
        final int numRighe = 5;

        final int shopSourceSliceSize = ASSET_ACTUAL_SIZE * 3;

        final BufferedImage bigSheet = new SpriteLoader("/icons/tiles/shop.png").getImage();

        for (int row = 0; row < numRighe; row++) {
            for (int col = 0; col < numColonne; col++) {
                tile[tileIndex] = new Tile();
                tile[tileIndex].setImage(bigSheet.getSubimage(
                    col * shopSourceSliceSize,
                    row * shopSourceSliceSize,
                    shopSourceSliceSize,
                    shopSourceSliceSize
                ));
                tileIndex++;
            }
        }

        // Well slicing and loading
        final int numColonnePozzo = 3;
        final int numRighePozzo = 3;
        final int wellSourceSliceSize = ASSET_ACTUAL_SIZE;

        final BufferedImage bigSheetPozzo = new SpriteLoader("/icons/tiles/well.png").getImage();

        for (int row = 0; row < numRighePozzo; row++) {
            for (int col = 0; col < numColonnePozzo; col++) {
                tile[tileIndex] = new Tile();
                tile[tileIndex].setImage(bigSheetPozzo.getSubimage(
                    col * wellSourceSliceSize,
                    row * wellSourceSliceSize,
                    wellSourceSliceSize,
                    wellSourceSliceSize
                ));
                tileIndex++;
            }
        }
    }

    private void setupTile(final int index, final String fileName) {
        tile[index] = new Tile();
        tile[index].setImage(new SpriteLoader("/icons/tiles/" + fileName).getImage());
    }

    /**
     * Used to read a file containing the map (made by numbers) and load it by splitting
     * every tile.
     * 
     * @param filePath File passed in input containing the map.
     */
    public void loadMap(final String filePath) {
        try {
            final InputStream is = getClass().getResourceAsStream(filePath);
            final BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            for (int row = 0; row < ImplViewGamePanel.MAX_WORLD_ROW; row++) {
                final String line = br.readLine();
                if (line == null) {
                    break;
                }

                final String[] numbers = line.split(" ");
                for (int column = 0; column < ImplViewGamePanel.MAX_WORLD_COL; column++) {
                    if (column < numbers.length) {
                        final int num = Integer.parseInt(numbers[column]);
                        mapTileNum[column][row] = num;
                    }
                }
            }
            br.close();
        } catch (final IOException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Errore durante il caricamento della mappa", e);
        }
    }

    /**
     * Used to draw the map by drawing every tile in the right position.
     * 
     * @param g2 Graphics2D passed in input to draw the tiles.
     * @param cameraX X coordinate of the camera.
     * @param cameraY Y coordinate of the camera.
     */
    public void drawTile(final Graphics2D g2, final int cameraX, final int cameraY) {
        for (int row = 0; row < ImplViewGamePanel.MAX_WORLD_ROW; row++) {
            for (int col = 0; col < ImplViewGamePanel.MAX_WORLD_COL; col++) {

                final int tileNum = mapTileNum[col][row];

                if (tileNum < 0 || tileNum >= tile.length || tile[tileNum] == null) {
                    continue;
                }

                final int worldX = col * ImplViewGamePanel.TILE_SIZE;
                final int worldY = row * ImplViewGamePanel.TILE_SIZE;
                final int screenX = worldX - cameraX;
                final int screenY = worldY - cameraY;

                if (screenX + ImplViewGamePanel.TILE_SIZE > 0
                    && screenX < gp.getWidth()
                    && screenY + ImplViewGamePanel.TILE_SIZE > 0
                    && screenY < gp.getHeight()) {

                    g2.drawImage(tile[tileNum].getImage(),
                        screenX,
                        screenY,
                        ImplViewGamePanel.TILE_SIZE,
                        ImplViewGamePanel.TILE_SIZE,
                        null
                    );
                }
            }
        }
    }

    private void setGp(final ImplViewGamePanel gamePanel) {
        this.gp = gamePanel;
    }
}
