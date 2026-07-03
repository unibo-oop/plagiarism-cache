package model.admin.products;

/**
 * Enumeration with all available instructor's lessons.
 */
public enum Instructor {

    /**
     * One hour long ski lesson.
     */
    SKI_1HOUR,
    /**
     * Two hours long ski lesson.
     */
    SKI_2HOURS,
    /**
     * Three hours long ski lesson.
     */
    SKI_3HOURS,
    /**
     * One hour long snowboard lesson. 
     */
    SNOWBOARD_1HOUR,
    /**
     * Two hours long snowboard lesson. 
     */
    SNOWBOARD_2HOURS,
    /**
     * Three hours long snowboard lesson.
     */
    SNOWBOARD_3HOURS;

    private static final double DURATION_1 = 1.00;
    private static final double DURATION_2 = 2.00;
    private static final double DURATION_3 = 3.00;

    private static final double PRICE_1 = 30.00;
    private static final double PRICE_2 = 55.00;
    private static final double PRICE_3 = 75.00;

    /**
     * Get description.
     * 
     * @return a string which represents the description of the lesson
     */
    public String getDescription() {
        switch (this) {
            case SKI_1HOUR:         return "Maestro Di Sci (1 Ora)";
            case SKI_2HOURS:        return "Maestro Di Sci (2 Ore)";
            case SKI_3HOURS:        return "Maestro Di Sci (3 Ore)";
            case SNOWBOARD_1HOUR:   return "Maestro Di Snowboard (1 Ora)";
            case SNOWBOARD_2HOURS:  return "Maestro Di Snowboard (2 Ore)";
            default:                return "Maestro Di Snowboard (3 Ore)";
        }
    }

    /**
     * Get price.
     * 
     * @return a double which represents the price of the lesson for one student in mid season
     */
    public double getPrice() {
        switch (this) {
            case SKI_1HOUR:         return PRICE_1;
            case SKI_2HOURS:        return PRICE_2;
            case SKI_3HOURS:        return PRICE_3;
            case SNOWBOARD_1HOUR:   return PRICE_1;
            case SNOWBOARD_2HOURS:  return PRICE_2;
            default:                return PRICE_3;
        }
    }

    /**
     * Get duration.
     * 
     * @return a double which represents the duration of the lesson
     */
    public double getDuration() {
        switch (this) {
            case SKI_1HOUR:         return DURATION_1;
            case SKI_2HOURS:        return DURATION_2;
            case SKI_3HOURS:        return DURATION_3;
            case SNOWBOARD_1HOUR:   return DURATION_1;
            case SNOWBOARD_2HOURS:  return DURATION_2;
            default:                return DURATION_3;
        }
    }

}
