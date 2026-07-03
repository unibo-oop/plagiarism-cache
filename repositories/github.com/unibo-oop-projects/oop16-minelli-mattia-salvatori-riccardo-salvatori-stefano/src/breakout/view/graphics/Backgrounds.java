package breakout.view.graphics;

/**
 * Enum for available backgrounds.
 */
public enum Backgrounds {

    /**
     * 
     */
    EARTH("earth"),
    /**
     * 
     */
    GALAXY("galaxy"),
    /**
     * 
     */
    APOCALYPSE("apocalypse"),
    /**
     * 
     */
    FUTURE("future");

    private final String fileName;

    Backgrounds(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the name of the file
     */
    public String getName() {
        return this.fileName;
    }

}
