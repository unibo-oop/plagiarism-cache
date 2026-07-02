package utilities;

import javafx.scene.image.Image;

/**
 *
 */
import java.util.Objects;

public enum EnumImages {

    /**
     *
     */
    BULLET("images/bullet.png"),

    /**
     *
     */
    BACKGROUND("images/skybox13.jpg"),

    /**
     *
     */
    PLAYER("images/playerShip.png"),

    /**
     *
     */
    ENEMY("images/enemy.png");

    private final Image value;

    /**
     * Constructor.
     *
     * @param path url to file
     */
    EnumImages(final String path) {
        this.value = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
    }

    /**
     * @return value.
     */
    public Image getImage() {
        return this.value;
    }

}
