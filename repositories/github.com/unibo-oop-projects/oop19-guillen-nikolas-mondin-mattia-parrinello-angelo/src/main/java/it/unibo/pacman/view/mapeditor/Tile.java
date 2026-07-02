package it.unibo.pacman.view.mapeditor;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.EnumMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.view.utilities.BufferedImageLoader;

/**
 * Model the properties for a tile of the editor.
 */
public class Tile {

    private static final int TILE_SIZE = 30;

    private final EnumMap<EntityType, String> entityPath = new EnumMap<>(EntityType.class);

    {
        entityPath.put(EntityType.PACMAN, "Images/Pacman/Pacman_Left.png");
        entityPath.put(EntityType.WALL, "Images/Map/Wall.png");
        entityPath.put(EntityType.PILL, "Images/Map/pill.png");
        entityPath.put(EntityType.POWERPILL, "Images/Map/PowerPill.png");
        entityPath.put(EntityType.BLINKY, "Images/Ghost/Blinky_Up.png");
        entityPath.put(EntityType.INKY, "Images/Ghost/Inky_Up.png");
        entityPath.put(EntityType.CLYDE, "Images/Ghost/Clyde_Up.png");
        entityPath.put(EntityType.PINKY, "Images/Ghost/Pinky_Up.png");
        entityPath.put(EntityType.EMPTY, "Images/Map/blackBlock2.png");
    }

    private final EntityType type;
    private final BufferedImage image;

    /**
     * Create a tile.
     * @param type of the tile
     */
    public Tile(final EntityType type) {
        this.type = type;
        this.image = BufferedImageLoader.loadImage(this.getPath());
    }

    /**
     * 
     * @return the type of tile
     */
    public EntityType getType() {
        return type;
    }

    /**
     * 
     * @return the right path for the type.
     */
    private String getPath() {
        return this.entityPath.get(type);
    }

    /**
     * 
     * @return an image for the tile
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * 
     * @return an icon for the tile
     */
    public Icon getIcon() {
        return new ImageIcon(image);
    }

    /**
     * 
     * @return the size of the tile
     */
    public static int getSize() {
        return TILE_SIZE;
    }
}
