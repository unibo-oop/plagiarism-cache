package bubbleshooter.view.images;

/**
 * Identifies an image stored on the disk and its path.
 */
public enum ImagePath {
    /**
     * Blue bubble.
     */
    BLUE_BUBBLE("/view/bubbles/blue.png"),
    /**
     * Light-blue bubble.
     */
    LIGHT_BLUE_BUBBLE("/view/bubbles/lightBlue.png"),
    /**
     * Red bubble.
     */
    RED_BUBBLE("/view/bubbles/red.png"),
    /**
     * Green bubble.
     */
    GREEN_BUBBLE("/view/bubbles/green.png"),
    /**
     * Yellow bubble.
     */
    YELLOW_BUBBLE("/view/bubbles/yellow.png"),
    /**
     * Purple bubble.
     */
    PURPLE_BUBBLE("/view/bubbles/purple.png"),

    /**
     * The Cannon.
     */
    CANNON("/view/cannon/cannon.png");

    private final String path;

    ImagePath(final String path) {
        this.path = path;
    }

    /**
     * getter for the path of image.
     * 
     * @return the path of the image.
     */
    public String getPath() {
        return this.path;
    }
}
