package model.statistic.statistics;
/**
 * 
 * enumeration about some parameter of statistics.
 *
 */
public enum EnumStatistic {
    /**
     * today.
     */
    TODAY("today"),
    /**
     * this month.
     */
    MONTH("month"),
    /**
     * this year.
     */
    YEAR("year");
/**
 * description of enumeration type.
 */
    private final String name;
/**
 * 
 * @param name the name of type of the enum.
 */
    EnumStatistic(final String name) {
        this.name = name;
    }
 /**
  * @return the name of enumeration type
  */
    public String getName() {
        return this.name;
    }
}
