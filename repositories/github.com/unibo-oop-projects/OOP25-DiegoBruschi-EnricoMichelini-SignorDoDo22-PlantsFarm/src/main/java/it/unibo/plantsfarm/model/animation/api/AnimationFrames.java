package it.unibo.plantsfarm.model.animation.api;

import java.awt.image.BufferedImage;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.plantsfarm.view.utility.SpriteLoader;

/**
 * Contains sprite frames used for player animations.
 */
@SuppressFBWarnings(value = "MS_EXPOSE_REP", justification = "Animation frames are treated as immutable assets"
)
public final class AnimationFrames {

    private static final BufferedImage BASE =
        new SpriteLoader("/Player/tile001.png").getImage();

    private static final List<BufferedImage> AXE = List.of(
        new SpriteLoader("/Player/Actions/RemovePlant/axe1.png").getImage(),
        new SpriteLoader("/Player/Actions/RemovePlant/axe2.png").getImage(),
        new SpriteLoader("/Player/Actions/RemovePlant/axe1.png").getImage(),
        BASE
    );

    private static final List<BufferedImage> WALKRIGHT = List.of(
        new SpriteLoader("/Player/tile024.png").getImage(),
        new SpriteLoader("/Player/tile025.png").getImage(),
        new SpriteLoader("/Player/tile026.png").getImage(),
        new SpriteLoader("/Player/tile027.png").getImage(),
        new SpriteLoader("/Player/tile028.png").getImage(),
        new SpriteLoader("/Player/tile029.png").getImage()
    );

    private static final List<BufferedImage> WALKLEFT = List.of(
        new SpriteLoader("/Player/Walking/Left/tile024Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile025Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile026Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile027Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile028Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile029Left.png").getImage()
    );

    private static final List<BufferedImage> WALKDOWN = List.of(
        new SpriteLoader("/Player/Walking/Down/tile018.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile019.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile020.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile021.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile022.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile023.png").getImage()
    );

    private static final List<BufferedImage> WALKUP = List.of(
        new SpriteLoader("/Player/Walking/Up/tile030.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile031.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile032.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile033.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile034.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile035.png").getImage()
    );

    private static final List<BufferedImage> WATER = List.of(
        new SpriteLoader("/Player/Actions/to Water/tile023.png").getImage(),
        new SpriteLoader("/Player/Actions/to Water/tile024.png").getImage(),
        new SpriteLoader("/Player/Actions/to Water/tile023.png").getImage(),
        BASE
    );

    private static final List<BufferedImage> DIG = List.of(
        new SpriteLoader("/Player/Actions/Dig/tile012.png").getImage(),
        new SpriteLoader("/Player/Actions/Dig/tile013.png").getImage(),
        new SpriteLoader("/Player/Actions/Dig/tile012.png").getImage(),
        BASE
    );

    private AnimationFrames() { }

    /**
     * Returns the base image for the player.
     *
     * @return the base BufferedImage for the player
     */
    public static BufferedImage base() {
        return BASE;
    }

    /**
     * Returns the frames for the axe animation.
     *
     * @return a list of BufferedImages representing the axe animation
     */
    public static List<BufferedImage> axe() {
        return AXE;
    }

    /**
     * Returns the frames for the walking right animation.
     *
     * @return a list of BufferedImages representing the walking right animation
     */
    public static List<BufferedImage> walkRight() {
        return WALKRIGHT;
    }

    /**
     * Returns the frames for the walking left animation.
     *
     * @return a list of BufferedImages representing the walking left animation
     */
    public static List<BufferedImage> walkLeft() {
        return WALKLEFT;
    }

    /**
     * Returns the frames for the walking down animation.
     *
     * @return a list of BufferedImages representing the walking down animation
     */
    public static List<BufferedImage> walkDown() {
        return WALKDOWN;
    }

    /**
     * Returns the frames for the walking up animation.
     *
     * @return a list of BufferedImages representing the walking up animation
     */
    public static List<BufferedImage> walkUp() {
        return WALKUP;
    }

    /**
     * Returns the frames for the watering animation.
     *
     * @return a list of BufferedImages representing the watering animation
     */
    public static List<BufferedImage> water() {
        return WATER;
    }

    /**
     * Returns the frames for the digging animation.
     *
     * @return a list of BufferedImages representing the digging animation
     */
    public static List<BufferedImage> dig() {
        return DIG;
    }
}
