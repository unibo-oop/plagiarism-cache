package model.admin.products;

/**
 * Enumeration with all available skipass.
 */
public enum Skipass {

    /**
     * Half day skipass.
     */
    HALF_DAY,
    /**
     * One day skipass.
     */
    ONE_DAY,
    /**
     * Two days skipass.
     */
    TWO_DAYS,
    /**
     * Three days skipass.
     */
    THREE_DAYS,
    /**
     * Five days skipass.
     */
    FIVE_DAYS,
    /**
     * One week skipass.
     */
    ONE_WEEK,
    /**
     * Ten days skipass.
     */
    TEN_DAYS,
    /**
     * Two weeks skipass.
     */
    TWO_WEEKS;

    private static final double HALF_DAY_DURATION = 0.50;
    private static final double ONE_DAY_DURATION = 1.00;
    private static final double TWO_DAYS_DURATION = 2.00;
    private static final double THREE_DAYS_DURATION = 3.00;
    private static final double FIVE_DAYS_DURATION = 5.00;
    private static final double ONE_WEEK_DURATION = 7.00;
    private static final double TEN_DAYS_DURATION = 10.00;
    private static final double TWO_WEEKS_DURATION = 14.00;

    private static final double HALF_DAY_PRICE = 28.00;
    private static final double ONE_DAY_PRICE = 40.00;
    private static final double TWO_DAYS_PRICE = 75.00;
    private static final double THREE_DAYS_PRICE = 106.00;
    private static final double FIVE_DAYS_PRICE = 165.00;
    private static final double ONE_WEEK_PRICE = 200.00;
    private static final double TEN_DAYS_PRICE = 280.00;
    private static final double TWO_WEEKS_PRICE = 370.00;

    /**
     * Get description.
     * 
     * @return a string which represents the description of the skipass duration
     */
    public String getDescription() {
        switch (this) {
            case HALF_DAY:          return "Mezzagiornata";
            case ONE_DAY:           return "Un Giorno";
            case TWO_DAYS:          return "Due Giorni";
            case THREE_DAYS:        return "Tre Giorni";
            case FIVE_DAYS:         return "Cinque Giorni";
            case ONE_WEEK:          return "Una Settimana";
            case TEN_DAYS:          return "Dieci Giorni";
            default:                return "Due Settimane";
        }
    }

    /**
     * Get price.
     * 
     * @return a double wich represents the single price of the skipass in mid season
     */
    public double getPrice() {
        switch (this) {
            case HALF_DAY:          return HALF_DAY_PRICE;
            case ONE_DAY:           return ONE_DAY_PRICE;
            case TWO_DAYS:          return TWO_DAYS_PRICE;
            case THREE_DAYS:        return THREE_DAYS_PRICE;
            case FIVE_DAYS:         return FIVE_DAYS_PRICE;
            case ONE_WEEK:          return ONE_WEEK_PRICE;
            case TEN_DAYS:          return TEN_DAYS_PRICE;
            default:                return TWO_WEEKS_PRICE;
        }
    }

    /**
     * Get duration.
     * 
     * @return a double which represents the duration of the skipass
     */
    public double getDuration() {
        switch (this) {
            case HALF_DAY:          return HALF_DAY_DURATION;
            case ONE_DAY:           return ONE_DAY_DURATION;
            case TWO_DAYS:          return TWO_DAYS_DURATION;
            case THREE_DAYS:        return THREE_DAYS_DURATION;
            case FIVE_DAYS:         return FIVE_DAYS_DURATION;
            case ONE_WEEK:          return ONE_WEEK_DURATION;
            case TEN_DAYS:          return TEN_DAYS_DURATION;
            default:                return TWO_WEEKS_DURATION;
        }
    }

}
