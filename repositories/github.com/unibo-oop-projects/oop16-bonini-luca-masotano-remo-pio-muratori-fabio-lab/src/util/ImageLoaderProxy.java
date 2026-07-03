package util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javafx.scene.image.Image;

/**
 * Singleton class used to provide images and internaly keep/manage them for
 * future uses. This class is meant to be a proxy between the image user and
 * image loader.
 */
public final class ImageLoaderProxy implements ImageLoader {

    private static ImageLoaderProxy singleton;
    private Map<ImageID, Image> loadedImages;
    private RealImageLoader realLoader;

    private ImageLoaderProxy() {
        this.realLoader = new RealImageLoader();
        this.loadedImages = new HashMap<>();
    }

    @Override
    public Image getImage(final ImageID identifier) {
        if (!loadedImages.containsKey(identifier)) {
            loadedImages.put(identifier, this.realLoader.getImage(identifier));
        }
        return loadedImages.get(identifier);
    }

    /**
     * Getter of all the loaded images.
     * 
     * @return a map of all the images requested
     */
    public Map<ImageID, Image> getLoadedImages() {
        return this.loadedImages;
    }

    /**
     * Getter of the singleton of this class.
     * 
     * @return a ProxyImageLoader object
     */
    public static ImageLoaderProxy get() {
        if (Objects.isNull(singleton)) {
            singleton = new ImageLoaderProxy();
        }
        return singleton;
    }

    /**
     * Class used to load external images; part of proxy pattern.
     */
    private class RealImageLoader implements ImageLoader {

        private static final String FILE_PATH = "/images/";

        @Override
        public Image getImage(final ImageID identifier) {
            return new Image(
                    this.getClass().getResourceAsStream(RealImageLoader.FILE_PATH + identifier.getImageName()));
        }
    }

    /**
     * Identifier of a single image, used to hide image real name from it's
     * utilization in "external" code. Since this class is both an inner class and
     * final it is accessible only by {@link ImageLoaderProxy}.
     */
    public enum ImageID {
        /**
         * Horizontal wall image identifier. Used to draw both upper and lower room
         * walls.
         */
        HORIZONTAL_WALL("wallhorizontal.png"),
        /**
         * Vertical wall image identifier. Used to draw both left and right room walls.
         */
        VERTICAL_WALL("wallvertical.png"),
        /**
         * Upper closed door image identifier.
         */
        NORTH_DOOR("doorup.png"),
        /**
         * Lower closed door image identifier.
         */
        SOUTH_DOOR("doordown.png"),
        /**
         * Right closed door image identifier.
         */
        EAST_DOOR("doorright.png"),
        /**
         * Left closed door image identifier.
         */
        WEST_DOOR("doorleft.png"),
        /**
         * Upper open door image identifier.
         */
        NORTH_DOOR_OPEN("doorupopen.png"),
        /**
         * Lower open door image identifier.
         */
        SOUTH_DOOR_OPEN("doordownopen.png"),
        /**
         * Right open door image identifier.
         */
        EAST_DOOR_OPEN("doorrightopen.png"),
        /**
         * Left open door image identifier.
         */
        WEST_DOOR_OPEN("doorleftopen.png"),
        /**
         * Player image identifier.
         */
        PLAYER("player.png"),
        /**
         * Enemy soldier image identifier.
         */
        ENEMY_SOLDIER("enemy.png"),
        /**
         * Enemy tank image identifier.
         */
        ENEMY_TANK("enemy.png"),
        /**
         * Enemy boss image identifier.
         */
        BOSS("boss.png"),
        /**
         * Player shooted bullet image identifier.
         */
        BULLET("bullet.png"),
        /**
         * Enemy shooted bullet image identifier.
         */
        BULLET_ENEMY("bulletenemy.png"),
        /**
         * Life point representation image identifier.
         */
        FULL_HEART("fullheart.png"),
        /**
         * Half life point representation image identifier.
         */
        HALF_HEART("halfheart.png"),
        /**
         * Map background image identifier.
         */
        MAP_BACKGROUND("mapborder.png"),
        /**
         * Unexplored rooms image identifier.
         */
        UNCHECKED_ROOM("room.png"),
        /**
         * Explored rooms image identifier.
         */
        CHECKED_ROOM("visitedroom.png"),
        /**
         * Current room image identifier.
         */
        CURRENT_ROOM("selectedroom.png"),
        /**
         * Room background image identifier.
         */
        FLOOR("floor.png"),
        /**
         * Pick-up object image identifier.
         */
        PICKUP("pickup.png");

        private String path;

        ImageID(final String path) {
            this.path = path;
        }

        private String getImageName() {
            return path;
        }
    }
}
