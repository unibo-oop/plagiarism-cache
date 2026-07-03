package model.admin.products;

/**
 * Enumeration with all seasons.
 */
public enum Season {

    /**
     * High season.
     */
    HIGH_SEASON,
    /**
     * Mid season.
     */
    MID_SEASON,
    /**
     * Off season.
     */
    OFF_SEASON;

    private static final double HIGH_SEASON_RATE = 1.25;
    private static final double MID_SEASON_RATE = 1.00;
    private static final double OFF_SEASON_RATE = 0.75;

    /**
     * Get description.
     * 
     * @return a string which represents the description of the season
     */
    public String getDescription() {
        switch (this) {
            case HIGH_SEASON:       return "Alta Stagione";
            case MID_SEASON:        return "Media Stagione";
            default:                return "Bassa Stagione";
        }
    }

    /**
     * Get period.
     * 
     * @return a string which represents the description of the season's period
     */
    public String getPeriod() {
        switch (this) {
            case HIGH_SEASON:       return "Dicembre-Gennaio";
            case MID_SEASON:        return "Febbraio-Marzo";
            default:                return "Altro Periodo";
        }
    }

    /**
     * Get rate.
     * 
     * @return a double which represents the rate of the season
     */
    public double getRate() {
        switch (this) {
            case HIGH_SEASON:       return HIGH_SEASON_RATE;
            case MID_SEASON:        return MID_SEASON_RATE;
            default:                return OFF_SEASON_RATE;
        }
    }

}
