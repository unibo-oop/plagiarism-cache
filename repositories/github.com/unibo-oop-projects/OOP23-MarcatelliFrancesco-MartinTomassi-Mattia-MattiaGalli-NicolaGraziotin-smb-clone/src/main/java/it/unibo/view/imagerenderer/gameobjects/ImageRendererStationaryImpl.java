package it.unibo.view.imagerenderer.gameobjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

import it.unibo.commons.Constants;
import it.unibo.model.tiles.Tile;

/**
 * An implementation of ImageRendererStationary that generates a BufferedImage representing
 * the stationary tiles in a game.
 */
public class ImageRendererStationaryImpl implements ImageRendererStationary {

    private final int numRows;
    private final int numColumns;

    /**
     * Constructs an ImageRendererStationaryImpl object with the specified number of rows and columns.
     *
     * @param numRows    The number of rows in the grid.
     * @param numColumns The number of columns in the grid.
     */
    public ImageRendererStationaryImpl(final int numRows, final int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
    }

    /**
     * Renders the stationary tiles represented by the provided 2D List of Optional Tile objects
     * into a BufferedImage.
     *
     * @param stationary A 2D List of Optional Tile objects representing the stationary tiles.
     * @return A BufferedImage representing the stationary tiles.
     */
    @Override
    public BufferedImage render(final List<List<Optional<Tile>>> stationary) {
        return getStationary(stationary);
    }

    /**
     * Generates a BufferedImage representing the stationary tiles in the game.
     *
     * @param stationary A 2D List of Tile objects representing the stationary tiles.
     * @return A BufferedImage representing the stationary tiles.
     */
    private BufferedImage getStationary(final List<List<Optional<Tile>>> stationary) {
        final BufferedImage image = new BufferedImage(
                this.numColumns * Constants.TILE_SIZE,
                this.numRows * Constants.TILE_SIZE,
                BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = (Graphics2D) image.getGraphics();

        IntStream.range(0, numRows).forEach(row ->
                IntStream.range(0, this.numColumns).forEach(column -> {
                    final Optional<Tile> tile = stationary.get(row).get(column);
                    tile.ifPresent(t -> {
                        try {
                            g.drawImage(
                                    getSubImage(ImageIO.read(getClass()
                                        .getClassLoader()
                                        .getResourceAsStream(t.getSrcImage())), t),
                                    column * Constants.TILE_SIZE,
                                    row * Constants.TILE_SIZE,
                                    Constants.TILE_SIZE,
                                    Constants.TILE_SIZE,
                                    null);
                        } catch (IOException e) {
                            Logger.getLogger(ImageRendererStationaryImpl.class.getName())
                                    .severe("An error occurred: " + e.getMessage());
                        }
                    });
                }));
        g.dispose();
        return image;
    }

    /**
     * Returns a subimage from the provided base image based on the specified tile.
     *
     * @param baseImage The base image from which to extract the subimage.
     * @param tile      The Tile object specifying the subimage's position.
     * @return The subimage corresponding to the specified Tile.
     */
    private BufferedImage getSubImage(final BufferedImage baseImage, final Tile tile) {
        return baseImage.getSubimage(
                tile.getX(),
                tile.getY(),
                Constants.TILE_SIZE,
                Constants.TILE_SIZE);
    }

}
