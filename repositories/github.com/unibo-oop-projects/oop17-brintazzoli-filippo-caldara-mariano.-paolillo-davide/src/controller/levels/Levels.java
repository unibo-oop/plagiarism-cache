package controller.levels;

/**
 * Enumeration for the game levels.
 */
public enum Levels {

    /**
     * The list of levels of the game.
     */
    LEVEL_1("Level1"), LEVEL_2("Level2"), LEVEL_3("Level3"), LEVEL_4("Level4");

    private String levelName;

    /**
     * Private constructor.
     * 
     * @param levelName
     *            the name of the level.
     */

    Levels(final String levelName) {
        this.levelName = levelName;
    }

    /**
     * Getter of the levels' name.
     * 
     * @return the level's name.
     */
    public String getName() {
        return this.levelName;
    }

}
