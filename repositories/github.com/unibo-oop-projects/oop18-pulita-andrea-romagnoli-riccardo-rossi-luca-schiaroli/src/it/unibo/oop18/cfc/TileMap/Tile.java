package it.unibo.oop18.cfc.TileMap;

import java.awt.image.BufferedImage;

/**
 * Tile class.
 */
public class Tile {

    private final BufferedImage image;
    private final int type;
    
    public static final int SPRITE_SIZE = 64;

    // tile types
    // tile movement
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;
    // tile food
    public static final int SALAD = 5;
    public static final int BREAD = 6;
    public static final int TOMATO = 7;
    public static final int MEAT = 8;
    // tile action
    public static final int DISH = 10;
    public static final int WASHBASIN = 11;
    public static final int COOKER = 12;
    public static final int CHOPPINGBOARD = 13;
    public static final int COUNTER = 14;

    /**
     * Constructor of the class.
     * 
     * @param image The image of the tile
     * @param type  The type of the tile
     */
    public Tile(final BufferedImage image, final int type) {
        this.image = image;
        this.type = type;
    }

    /**
     * Get the image of the tile.
     * 
     * @return image The image of the tile
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Get the type of the tile.
     * 
     * @return type The type of the tile
     */
    public int getType() {
        return type;
    }
}
