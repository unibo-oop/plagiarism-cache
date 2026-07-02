package model.enums;

/**
 * An enum representing all computed statistics informations.
 * 
 */
public enum StatsInfo {

    /**
     * Represents the number of total match played by a specific player.
     */
    TOTALS("Totals"),

    /**
     * Represents the number of total match won by a specific player.
     */
    WINS("Wins"),

    /**
     * Represents the number of total match lost by a specific player.
     */
    LOSS("Loss"),

    /**
     * Represents the record score value of a specific player.
     */
    RECORD("Record"),

    /**
     * Represents the won percentage of a specific player.
     */
    WINS_RATE("Wins_rate"),

    /**
     * Represents the percentage of defeats of a specific player.
     */
    LOSS_RATE("Loss_rate");

    private String name;

    StatsInfo(final String name) {
        this.name = name;
    }

    /**
     * Returns the name of a statistical information.
     * 
     * @return
     *      the name of the statistical information.
     */
    public String getName() {
        return this.name;
    }

}
