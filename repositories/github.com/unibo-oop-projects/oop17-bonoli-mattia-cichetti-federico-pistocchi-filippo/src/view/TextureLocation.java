package view;

/**
 * Enumeration that contains usable names for the objects in the view and their corresponding
 * Image files paths so that it is easy to modify paths or add more textures to the game.
 */
public enum TextureLocation {
    /**
     * Request for the frog image.
     * This shall be added to the frog once it's available.
     */
    FROG("res/img/frog/frog_normal_state.png"),
    /**
     * Request for the den image.
     */
    DEN("res/img/textures/den.png"),
    /**
     * Request for the last lane image.
     */
    LANE_END("res/img/textures/endlane.png"),
    /**
     * Request for the starting safe lane image.
     */
    LANE_FIRST("res/img/textures/firstlane.png"),
    /**
     * Request for the middle safe image.
     */
    LANE_MID("res/img/textures/safemidlane.png"),
    /**
     * Request for the street image.
     */
    STREET("res/img/textures/streetlane.png"),
    /**
     * Request for the water image.
     */
    WATER("res/img/textures/waterlane.png");

    private final String path;

    TextureLocation(final String str) {
        this.path = str;
    }

    /**
     * Method that gets the actual URL of the file location.
     * @return the requested file URL.
     */
    public String getUrl() {
        return this.path;
    }

}
