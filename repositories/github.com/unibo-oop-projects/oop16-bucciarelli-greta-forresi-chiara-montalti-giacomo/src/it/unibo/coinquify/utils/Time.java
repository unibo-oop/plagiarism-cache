package it.unibo.coinquify.utils;

/**
 * Time interface.
 */
public interface Time {

    /**
     * 
     * @return hour
     */
    int getHour();

    /**
     * 
     * @return minutes
     */
    int getMinutes();

    /**
     * 
     * @param hour
     *            to set
     */
    void setHour(int hour);

    /**
     * 
     * @param minutes
     *            to set
     */
    void setMinutes(int minutes);

    /**
     * 
     * @param time
     *            to compare
     * @return true if the current time is before this
     */
    boolean before(Time time);

    /**
     * 
     * @param time
     *            to compare
     * @return true if the current time is after this
     */
    boolean after(Time time);

    /**
     * 
     * @return all minutes, 60 * hours + minutes
     */
    int getAllMinutes();
}