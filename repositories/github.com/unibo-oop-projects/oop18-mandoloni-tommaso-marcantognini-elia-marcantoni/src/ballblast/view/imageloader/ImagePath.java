package ballblast.view.imageloader;

/**
 * Identifies an image stored on the disk and its path.
 */
public enum ImagePath {
    /**
     * The image for Ball. String empty to use randomColor in the
     * {@link ImageLoader}.
     */
    BALL(""),
    /**
     * The image for Player.
     */
    PLAYER("/view/players/cannon.png"),
    /**
     * The image for Bullet.
     */
    BULLET("/view/bullets/bullet.png"),
    /**
     * The image for speed Power.
     */
    POWERUP_SPEED("/view/powers/speed.png"),
    /**
     * The image for shield Power.
     */
    POWERUP_SHIELD("/view/powers/shield.png"),
    /**
     * The image for double fire Power.
     */
    POWERUP_DOUBLEFIRE("/view/powers/doublefire.png"),
    /**
     * The image for Wall horizontal.
     */
    WALL_FLOOR("/view/walls/wall_floor.png"),
    /**
     * The image for Wall roof.
     */
    WALL_ROOF("/view/walls/wall_roof.png"),
    /**
     * The image for Wall vertical.
     */
    WALL_VERTICAL("/view/walls/wall_vertical.png"),
    /**
     * The image for background.
     */
    BACKGROUND("/view/walls/background.png"),
    /**
     * The image for explosion.
     */
    FIREWORKS("/view/balls/explosion.png");
    private final String path;

    ImagePath(final String path) {
        this.path = path;
    }

    /**
     * 
     * @return the path of the image.
     */
    public String getPath() {
        return this.path;
    }
}
