package org.hsm.model.plant;

/**
 * Builder pattern.
 */

public class BuilderPlant {

    private String name;
    private String botanicalName;
    private int ph;
    private int brightness;
    private int optimalGrowthTime;
    private int life;
    private int size;
    private int cost;
    private int conductivity;
    private int optimalTemperature;

    /**
     * @param s
     *            is the name of the plant.
     * @return obj.
     */
    public BuilderPlant name(final String s) {
        this.name = s;
        return this;
    }

    /**
     * @param s
     *            is the botanical name of the plant
     * @return obj
     */
    public BuilderPlant botanicalName(final String s) {
        this.botanicalName = s;
        return this;
    }

    /**
     * @param p
     *            is the optimal ph value of the plant
     * @return obj
     */
    public BuilderPlant ph(final int p) {
        this.ph = p;
        return this;
    }

    /**
     * @param b
     *            is
     * @return obj
     */
    public BuilderPlant brightness(final int b) {
        this.brightness = b;
        return this;
    }

    /**
     * @param ogt
     *            is the time-growth of the plant
     * @return obj
     */
    public BuilderPlant optimalGrowthTime(final int ogt) {
        this.optimalGrowthTime = ogt;
        return this;
    }

    /**
     * @param l
     *            represent time-life of the plant
     * @return obj
     */
    public BuilderPlant life(final int l) {
        this.life = l;
        return this;
    }

    /**
     * @param s
     *            is the maximum size of the plant expressed in cm³
     * @return obj
     */
    public BuilderPlant size(final int s) {
        this.size = s;
        return this;
    }

    /**
     * @param c
     *            is
     * @return obj
     */
    public BuilderPlant cost(final int c) {
        this.cost = c;
        return this;
    }

    /**
     * @param c
     *            is the conductivity
     * @return obj
     */
    public BuilderPlant conductivity(final int c) {
        this.conductivity = c;
        return this;
    }

    /**
     * @param ot
     *            is the optimal temperature for the plant
     * @return obj
     */
    public BuilderPlant optimalTemperature(final int ot) {
        this.optimalTemperature = ot;
        return this;
    }

    /**
     * @return a Plant
     * @throws IllegalArgumentException .
     * @throws IllegalStateException .
     */
    public PlantModel build() throws IllegalArgumentException, IllegalStateException {
        if (this.name.equals("") || this.botanicalName.equals("") || (this.size + this.ph + this.brightness
                + this.optimalGrowthTime + this.life + this.conductivity + this.optimalTemperature) == 0) {
            throw new IllegalArgumentException();
        }
        return new PlantModelImpl(this.name, this.botanicalName, this.ph, this.brightness, this.optimalGrowthTime,
                this.life, this.size, this.cost, this.conductivity, this.optimalTemperature);
    }

}
