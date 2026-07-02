package view.entity;

import javafx.scene.image.Image;

/**
 * 
 * Enumeration for entity images path.
 *
 */
public enum EntityImages {

    /**
     * Obstacle's image (Hydrant).
     */
    OBSTACLE_ONE("Hydrant.png"),

    /**
     * Obstacle's image (road cone).
     */
    OBSTACLE_TWO("RoadCone.png"),

    /**
     * Coin's image.
     */
    COIN("Coin.png"),

    /**
     * Platform's image.
     */
    PLATFORM("Platform.png"),

    /**
     * Extra Life powerup's image. 
     */
    EXTRALIFE("ExtraLife.png"), 

    /**
     * Mushroom powerup's image. 
     */
    MUSHROOM("Mushroom.png"), 

    /**
     * Shield powerup's image. 
     */
    SHIELD("Shield.png"), 

    /**
     * SprayBomb powerup's image. 
     */
    SPRAYBOMB("SprayBomb.png"), 

    /**
     * SuperJump powerup's image. 
     */
    SUPERJUMP("SuperJump.png"); 

    private final String path;

    /**
     * 
     * @param path the path corresponding to the image.
     */
    EntityImages(final String path) {
        this.path = path;
    }

    /**
     * Get a new instance of Image from the specified path.
     * @return new image identifying the entity.
     */
    public Image getImage() {
        return new Image(this.path);
    }

}
