package model;

public interface Tempo {
    public static final Integer MAXORE = 24;
    public static final Integer ORA = 1;
    public static final Integer MINORE = 0;
    public static final String STARTINGDAY = "2036-12-15";

    /**
     * Getter method for the Date
     * @return the String value of the Date
     */
    public String getDate();
    /**
     * Method to increase the hours in the game world
     * @param ore hours passed
     */
    public void passaOre(final Integer ore);
    /**
     * Fix the hour
     * @return the number of days passed
     */
    public Integer fixOra();
    /**
     * Fix the day
     * @param aumento amount of days of the increase
     */
    public void fixGiorno(final Integer aumento);
    /**
     * Getter method for the current hour
     * @return integer value of the current hour
     */
    public Integer getOra();
    
    /**
     * Method used to increase astronaut oxygen spending one hour
     */
    public void ristoro();
    
    public String toString();
}
