package model;

/**
 * interface for the warehouse.
 */
public interface Warehouse {

    //water
    /**
     * increments the liters user for flowers.
     * @param liters the number used
     */
    void addWaterUsed(int liters);

    /**
     * gets the water used.
     * @return the total liters used
     */
    long getWaterUsed();

    /**
     * resets the water used.
     * @return if the action is OK.
     */
    boolean resetWaterUsed();

    //fertilizer
    /**
     * increments the Kg of fertilizer used.
     * @param kg the Kg of fertilized used for the plants.
     */
    void addFertilizerUsed(int kg);

    /**
     * returns the fertilizer used in total.
     * @return the kg of fertilizer totally used.
     */
    long getFertilizerUsed();

    /**
     * resets the amount of fertilizer used.
     * @return if the action is OK.
     */
    boolean resetFertilizerUsed();

    /**
     * 
     * @return the total plants picked.
     */
    long getTotalPlantsPicked();

    //ADD plants picked
    /**
     * @param num the number of flower of this type picked.
     */
    void addRosePicked(int num);

    /**
     * @param num the number of flower of this type picked.
     */
    void addTulipPicked(int num);

    /**
     * @param num the number of flower of this type picked.
     */
    void addSunflowerPicked(int num);

    /**
     * @param num the number of flower of this type picked.
     */
    void addLilyPicked(int num);

    /**
     * @param num the number of flower of this type picked.
     */
    void addGenericPicked(int num);


    //GETTER plants picked
    /**
     * gets the total number of this flower picked.
     * @return number of flowers picked
     */
    long getRosePicked();

    /**
     * gets the total number of this flower picked.
     * @return number of flowers picked
     */
    long getTulipPicked();

    /**
     * gets the total number of this flower picked.
     * @return number of flowers picked
     */
    long getSunflowerPicked();

    /**
     * gets the total number of this flower picked.
     * @return number of flowers picked
     */
    long getLilyPicked();

    /**
     * gets the total number of this flower picked.
     * @return number of flowers picked
     */
    long getGenericPicked();

}
