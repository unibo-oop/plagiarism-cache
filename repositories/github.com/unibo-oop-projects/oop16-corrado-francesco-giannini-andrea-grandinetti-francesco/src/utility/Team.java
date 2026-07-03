package utility;

/**
 * F1 Team that will be playable in the game.
 */
public enum Team {

    /**
     * Mercedes.
     */
    MER("Mercedes"),

    /**
     * Ferrari.
     */
    FER("Ferrari"),

    /**
     * Red Bull.
     */
    RDB("Red Bull"),

    /**
     * Mclaren.
     */
    MCL("Mclaren"),

    /**
     * Renault.
     */
    REN("Renault");

    private final String name;

    Team(final String name) {
        this.name = name;
    }

    /**
     * @return team's name
     */
    public String toString() {
        return this.name;
    }
}
