package bzzbomber.view.gamefield;

import bzzbomber.view.TileImg;

/**
 * Represents an extended version of Tile with the ability to manipulate it's
 * dimensions.
 */
public class TileForGameField extends TileImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int width;
    private int height;

    /**
     * @param tileImage
     *            Image of this Tile.
     * @param w
     *            Width of this Tile.
     * @param h
     *            Height of this Tile.
     */
    public TileForGameField(final TileImg tileImage, final int w, final int h) {
        super(tileImage);
        this.width = w;
        this.height = h;
    }

    /**
     * @param w
     *            Width of this Tile.
     */
    public void setTileWidth(final int w) {
        this.width = w;
    }

    /**
     * @param h
     *            Height of this Tile.
     */
    public void setTileHeight(final int h) {
        this.height = h;
    }

    /**
     * @return Width of this Tile.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return Height of this Tile.
     */
    public int getHeight() {
        return height;
    }

}
