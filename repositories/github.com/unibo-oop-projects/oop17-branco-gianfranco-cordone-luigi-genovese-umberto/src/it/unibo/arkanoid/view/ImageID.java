package it.unibo.arkanoid.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enumeration for all the Image in the res folder.
 *
 */
public enum ImageID {

    /**
     * background type.
     */
    MASK("/GIF/BACKGROUND/MASK.jpg", true),

    /**
     * background type.
     */
    FIBER("/GIF/BACKGROUND/FIBER.jpg", true),

    /**
     * background type.
     */
    UNIVERSE("/GIF/BACKGROUND/UNIVERSE.jpg", true),

    /**
     * background type.
     */
    FOREST("/GIF/BACKGROUND/FOREST.jpg", true),

    /**
     * background type.
     */
    GREEN_UNIVERSE("/GIF/BACKGROUND/GREEN_UNIVERSE.jpg", true),

    /**
     * background type.
     */
    WOOD("/GIF/BACKGROUND/WOOD.jpg", true),

    /**
     * background type.
     */
    NEBULLA("/GIF/BACKGROUND/NEBULA.jpg", true),

    /**
     * background type.
     */
    FUTURE("/GIF/BACKGROUND/FUTURE.jpg", true),

    /**
     * background type.
     */
    BLUE("/GIF/BACKGROUND/BLUE.jpg", true),

    /**
     * type of subject.
     */
    PADDLE_BLUE("/GIF/SUBJECT/PADDLE.png", false),

    /**
     * type of subject.
     */
    BALL("/GIF/SUBJECT/BALL.png", false),

    /**
     * type of subject.
     */
    BRICK_SIMPLE("/GIF/SUBJECT/BRICK_SIM.png", false),

    /**
     * type of subject.
     */
    BRICK_MULTILIFE("/GIF/SUBJECT/BRICK_MUL.png", false),

    /**
     * type of subject.
     */
    BRICK_INDESTRUCTIBLE("/GIF/SUBJECT/IRON_SILVER_BRICK_2.png", false),

    /**
     * type of subject.
     */
    THREEBALL_POWERUP("/GIF/SUBJECT/THREE_BALL_POWERUP.png", false),

    /**
     * type of subject.
     */
    REDUCE_POWERUP("/GIF/SUBJECT/REDUCE_PADDLE_POWERUP.png", false),

    /**
     * type of subject.
     */
    ENLARGE_POWERUP("/GIF/SUBJECT/ENLARGE_PADDLE_POWERUP_3.png", false),

    /**
     * type of subject.
     */
    LIFE("/GIF/SUBJECT/ONE_LIFE.png", false),

    /**
     * type of subject.
     */
    VELOCITY_POWERUP("/GIF/SUBJECT/INCREASE_VEL_BALL.png", false);

    private String path;
    private boolean background;

    /**
     * True if Image is design for background use, false otherwise.
     * 
     * @return True if a background, false otherwise.
     */
    public boolean isBackground() {
        return background;
    }

    ImageID(final String path, final boolean background) {
        this.path = path;
        this.background = background;
    }

    /**
     * This method is useful to get all background image.
     * 
     * @return The image background.
     */
    public static List<ImageID> getBackgrounds() {
        return Arrays.stream(ImageID.values()).filter(i -> i.isBackground()).collect(Collectors.toList());
    }

    /**
     * Getter for path of ImageID.
     * 
     * @return The path of ImageID.
     */
    public String getPath() {
        return this.path;
    }

}
