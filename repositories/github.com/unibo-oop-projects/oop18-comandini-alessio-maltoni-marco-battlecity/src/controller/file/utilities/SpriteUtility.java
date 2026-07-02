package controller.file.utilities;

import enums.Sprite;
import javafx.scene.image.Image;

/**
 * Class utility for load the Sprite. The class uses the Singleton Pattern.
 */
public final class SpriteUtility {

    // Instance of the class.
    private static final SpriteUtility SINGLETON = new SpriteUtility();

    /**
     * Method that implements the Singleton Pattern. It returns only one instance of
     * the class, created only one time.
     * 
     * @return the instance of the class.
     */
    public static SpriteUtility getInstance() {
        return SINGLETON;
    }

    /*
     * Empty constructor of the class that load the sprite.
     */
    private SpriteUtility() {
    }

    /**
     * Method that return the requested sprite image. Given a Sprite enumeration, it
     * open the specified file and return it back.
     * 
     * @param spriteEnum the enumeration of the requested sprite.
     * @return the requested sprite.
     */
    public Image getSprite(final Sprite spriteEnum) {
        Image sprite = null;
        final String path = spriteEnum.getPath();
        // Try to load the image from file.
        try {
            sprite = new Image(getClass().getResourceAsStream(path));
        } catch (final Exception e) {
            System.out.println("SPRITE " + spriteEnum + " NOT FOUND");
        }
        return sprite;
    }

}
