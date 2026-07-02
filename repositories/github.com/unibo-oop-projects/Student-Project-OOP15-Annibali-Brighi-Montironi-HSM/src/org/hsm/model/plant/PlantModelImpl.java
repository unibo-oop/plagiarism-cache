package org.hsm.model.plant;

import java.io.Serializable;

/**
 *implementation of PlantModel Interface
 */

public class PlantModelImpl implements PlantModel, Serializable {

    private static final long serialVersionUID = -4471371902186918643L;

    private final String name;
    private final String botanicalName;
    private final double ph;
    private final double brightness;
    private final int optimalGrowthTime;
    private final int life;
    private final int size;
    private final int cost;
    private final double conductivity;
    private final double optimalTemperature;

    /**
     * Constructor used by a Builder.
     *
     * @param name
     *            name plant
     * @param botanicalName
     *            botanical name plant
     * @param ph
     *            optimal ph value for the plant
     * @param brightness
     *            light needed by the plant
     * @param optimalGrowthTime
     *            time growth
     * @param life
     *            time life
     * @param size
     *            max size for the plant expressed in cm²
     * @param cost
     *            cost
     * @param conductivity
     *            conductivity
     * @param optimalTemperature
     *            optimal temperature for the plant
     *
     */
    protected PlantModelImpl(final String name, final String botanicalName, final double ph, final double brightness,
            final int optimalGrowthTime, final int life, final int size, final int cost, final double conductivity,
            final double optimalTemperature) {
        super();
        this.name = name;
        this.botanicalName = botanicalName;
        this.ph = ph;
        this.brightness = brightness;
        this.optimalGrowthTime = optimalGrowthTime;
        this.life = life;
        this.size = size;
        this.cost = cost;
        this.conductivity = conductivity;
        this.optimalTemperature = optimalTemperature;
    }

    /**
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return botanicalName
     */
    @Override
    public String getBotanicalName() {
        return botanicalName;
    }

    /**
     * @return ph
     */
    @Override
    public double getPH() {
        return ph;
    }

    /**
     * @return brightness
     */
    @Override
    public double getBrightness() {
        return brightness;
    }

    /**
     * @return optimalGrowthTime
     */
    @Override
    public int getOptimalGrowthTime() {
        return optimalGrowthTime;
    }

    /**
     * @return life
     */
    @Override
    public int getLife() {
        return life;
    }

    /**
     * @return size
     */
    @Override
    public double getSize() {
        return size;
    }

    /**
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return conductivity
     */
    @Override
    public double getConductivity() {
        return conductivity;
    }

    /**
     * @return optimalTemperature
     */
    @Override
    public double getOptimalTemperature() {
        return optimalTemperature;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((botanicalName == null) ? 0 : botanicalName.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlantModelImpl other = (PlantModelImpl) obj;
        if (botanicalName == null) {
            if (other.botanicalName != null) {
                return false;
            }
        } else if (!botanicalName.equals(other.botanicalName)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
