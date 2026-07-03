package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.GamePlatform;

/**
 * This class represents the single tile implementation
 */
public class Tile implements GamePlatform {
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;

    private TileType tileType;
    private final String  image;
    private boolean isCollidable;
    private final boolean canDealDamage;

    /**
     * Create a new tile
     * @param image is the sprite path
     * @param isCollidable says if the tile is a collidable object
     * @param canDealDamage says if the tile can deal damage
     * @param tileType says what kind of tile is
     */
    public Tile(String image, boolean isCollidable, boolean canDealDamage, TileType tileType) {
        this.image = image;
        this.isCollidable = isCollidable;
        this.tileType = tileType;
        this.canDealDamage = canDealDamage;
    }

    /**
     * Create a new tile, that cannot deals damage and is not collidable. Mostly used for decorative tiles
     * @param image is the sprite path
     * @param tileType says what kind of tile is
     */
    public Tile(String image, TileType tileType) {
        this.image = image;
        this.tileType = tileType;
        this.canDealDamage = false;
        this.isCollidable = false;
    }

    @Override
    public int getWidth() { return WIDTH; }

    @Override
    public int getHeight() { return HEIGHT; }

    public String getImage() { return image; }

    public boolean isCollidable() { return isCollidable; }

    public boolean isCanDealDamage() { return canDealDamage; }

    public TileType getTileType() { return tileType; }
}
