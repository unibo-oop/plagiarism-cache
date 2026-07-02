package controller;

/**
 * Interface that manage all the data necessary for charts.
 */
public interface ChartsManager {

    /**
     *Addition of infectivity rate.
     * @param people
     *          total number of people
     * @param time
     *          quantum of time
     */
    void addRateInfectivity(int people, int time);

    /**
     * Addition of healthy people.
     * 
     * @param time
     *          quantum of time
     */
    void addHealthy(int time);

    /**
     * Addition of infected people.
     * 
     * @param infect
     *          number of infected
     * @param time
     *          quantum of time
     */
    void addInfect(int infect, int time);

    /**
     * Addition of the number of natural deaths.
     * @param death
     *         number of deaths
     * @param time
     *         quantum of time
     * 
     */
    void addDeath(int death, int time);

    /**
     * Addition the number of new people.
     * @param birth
     *         number of births
     * @param time
     *         quantum of time
     */
    void addBirth(int birth, int time);

    /**
     * Addition of the number of people killed by the virus.
     * @param virusDeath
     *         number of virus deaths
     * @param time
     *         quantum of time
     * 
     */
    void addVirusDeath(int virusDeath, int time);

    /**
     * Addition of the number of people treated.
     * @param recovered
     *        number of recovered
     * @param time
     *        quantum of time
     */
    void addRecovered(int recovered, int time);

    /**
     * Returns all data ready to be added to the chart.
     * @return  Datagetter
     *            object containing all the data
     */
    DataGetter getData();

}
