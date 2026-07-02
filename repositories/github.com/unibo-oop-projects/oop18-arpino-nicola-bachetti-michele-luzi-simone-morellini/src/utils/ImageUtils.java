package utils;

import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;

/**
 * This class is used to get the {@link Image} relative to an object of the
 * game.
 *
 */

public final class ImageUtils {

    private static final String PATH_TEXTURE = "texture/";
    private static final String PATH_BACKGROUND = "background/";

    private static final List<Image> MILK_IMAGES = Arrays.asList(new Image(PATH_TEXTURE + "milk_down.png"),
            new Image(PATH_TEXTURE + "milk_left.png"), new Image(PATH_TEXTURE + "milk_up.png"), new Image(PATH_TEXTURE + "milk_right.png"));

    private static final List<Image> ABBRACCIO_IMAGES = Arrays.asList(new Image(PATH_TEXTURE + "abbraccio_down.png"),
            new Image(PATH_TEXTURE + "abbraccio_left.png"), new Image(PATH_TEXTURE + "abbraccio_up.png"),
            new Image(PATH_TEXTURE + "abbraccio_right.png"));

    private static final List<Image> STELLA_IMAGES = Arrays.asList(new Image(PATH_TEXTURE + "pandistelle_down.png"),
            new Image(PATH_TEXTURE + "pandistelle_left.png"), new Image(PATH_TEXTURE + "pandistelle_up.png"),
            new Image(PATH_TEXTURE + "pandistelle_right.png"));

    private static final List<Image> GOCCIOLA_IMAGES = Arrays.asList(new Image(PATH_TEXTURE + "gocciola_down.png"),
            new Image(PATH_TEXTURE + "gocciola_left.png"), new Image(PATH_TEXTURE + "gocciola_up.png"), new Image(PATH_TEXTURE + "gocciola_right.png"));

    /**
     * Items textures.
     */
    private static final Image SUGAR = new Image(PATH_TEXTURE + "zolletta.png");

    private static final Image SUGARCANE = new Image(PATH_TEXTURE + "sugarCane.png");

    private static final Image COFFE = new Image(PATH_TEXTURE + "coffe.png");

    private static final Image ENEMY_BULLET = new Image(PATH_TEXTURE + "enemy_bullet.png");

    private static final Image PLAYER_BULLET = new Image(PATH_TEXTURE + "player_bullet.png");

    private static final Image LACTOSE_FREE_MILK = new Image(PATH_TEXTURE + "lactose_free_milk.png");

    private static final Image WHOLE_MILK = new Image(PATH_TEXTURE + "whole_milk.png");

    private static final Image SKIMMED_MILK = new Image(PATH_TEXTURE + "skimmed_milk.png");

    private static final Image CHOCOLATE = new Image(PATH_TEXTURE + "chocolate.png");

    /**
     * Rooms background.
     */

    private static final Image TUTORIAL = new Image(PATH_BACKGROUND + "simple.jpg");

    private static final Image ROOM1 = new Image(PATH_BACKGROUND + "room1.jpg");

    private static final Image ROOM2 = new Image(PATH_BACKGROUND + "room2.jpg");

    private static final Image ROOM3 = new Image(PATH_BACKGROUND + "room3.jpg");

    private static final Image BOSS_ROOM = new Image(PATH_BACKGROUND + "boss_back.png");


    /**
     * Obstacles textures.
     */

    private static final Image OBSTACLE = new Image(PATH_TEXTURE + "obstacle_wood.png");

    private static final Image DOOR_CLOSED = new Image(PATH_TEXTURE + "door_closed.png");

    private static final Image DOOR_OPENED = new Image(PATH_TEXTURE + "door_opened.png");

    private ImageUtils() {
    }

    /**
     * Get chocolate image.
     * 
     * @return the Image
     */
    public static Image getChocolate() {
        return CHOCOLATE;
    }

    /**
     * Get image of lactose free milk.
     * 
     * @return the Image
     */
    public static Image getLactoseFreeMilk() {
        return LACTOSE_FREE_MILK;
    }

    /**
     * Get image for whole milk.
     * 
     * @return the Image.
     */
    public static Image getWholeMilk() {
        return WHOLE_MILK;
    }

    /**
     * Get image for skimmed milk.
     * 
     * @return the Image.
     */
    public static Image getSkimmedMilk() {
        return SKIMMED_MILK;
    }

    /**
     * Get the Image of the sugar.
     * 
     * @return the Image of the sugar
     */
    public static Image getSugar() {
        return SUGAR;
    }

    /**
     * Get the Image of the sugarcane.
     * 
     * @return the Image of the sugarcane
     */
    public static Image getSugarCane() {
        return SUGARCANE;
    }

    /**
     * Get enemy bullet image.
     * 
     * @return enemy bullet image
     */
    public static Image getEnemyBullet() {
        return ENEMY_BULLET;
    }

    /**
     * Get player bullet image.
     * 
     * @return player bullet image
     */
    public static Image getPlayerBullet() {
        return PLAYER_BULLET;
    }

    /**
     * Get the image for coffee.
     * 
     * @return the coffee image
     */
    public static Image getCoffe() {
        return COFFE;
    }

    /**
     * Get the Images for the Milk.
     * 
     * @return the List of Image for the Milk
     */
    public static List<Image> getMilkImages() {
        return MILK_IMAGES;
    }

    /**
     * Get the Images for the Abbraccio.
     * 
     * @return the List of Image for the Abbraccio
     */
    public static List<Image> getAbbraccioImages() {
        return ABBRACCIO_IMAGES;
    }

    /**
     * Get the Images for the Stelle.
     * 
     * @return the List of Image for the Stelle
     */
    public static List<Image> getStelleImages() {
        return STELLA_IMAGES;
    }

    /**
     * Get the Images for the Gocciole.
     * 
     * @return the List of Image for the Gocciole
     */
    public static List<Image> getGoccioleImages() {
        return GOCCIOLA_IMAGES;
    }

    /**
     * Get the right image of background based on which room is requested.
     * 
     * @return the image
     */

    /**
     * Get the obstacle image.
     * 
     * @return the image
     */
    public static Image getObstacle() {
        return OBSTACLE;
    }

    /**
     * Get opened door image.
     * 
     * @return the image
     */
    public static Image getDoorOpened() {
        return DOOR_OPENED;
    }

    /**
     * Get closed door image.
     * 
     * @return the image
     */
    public static Image getDoorClosed() {
        return DOOR_CLOSED;
    }

    /**
     * Method to get right background based on room received as parameter.
     * 
     * @param room {@link Rooms} room parameter
     * @return {@link Image} right background
     */
    public static Image getRoomBackground(final Rooms room) {

        switch (room) {
        case ROOM1:
            return ROOM1;
        case ROOM2:
            return ROOM2;
        case ROOM3:
            return ROOM3;
        case BOSS_ROOM:
            return BOSS_ROOM;
        case TUTORIAL:
            return TUTORIAL;
        default:
            return TUTORIAL;
        }
    }

}
