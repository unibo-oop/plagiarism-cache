package model.properties.defaultdata;

/**
 * 
 * Every enum contains a container with limits and default value of different utilities utilized by genes.
 *
 */
public enum GenesDefaultDataEnum implements DefaultDataEnum {
    /**
     * Contains integer values of the minimum, maximum and default cost of cellular reproduction.
     */
    REPRODUCTION_COST(new DefaultDataContainerImpl<Integer>(0, 500, 100)),
    /**
     * Contains integer values of the minimum, maximum and default sun energy in simulation.
     */
    SUN_ENERGY(new DefaultDataContainerImpl<Integer>(0, 25, 15)),
    /**
     * Contains integer values of the minimum, maximum and default number of minerals that can be
     * absorbed by cell in one turn.
     */
    MINERALS_ABSORPTION(new DefaultDataContainerImpl<Integer>(0, 50, 50)),
    /**
     * Contains integer values of the minimum, maximum and default number of energy that a cell
     * receives if it ate a dead cell.
     */
    NUTRITION_OF_DEAD_CELL(new DefaultDataContainerImpl<Integer>(0, 1000, 100)),
    /**
     * Contains floats values of the minimum, maximum and default sun depth that define the first (100*n)%
     * of lines of world with possibility to photosynthesis.
     */
    SUN_PENETRATION(new DefaultDataContainerImpl<Float>(0F, 1F, 1F)),
    /**
     * Contains floats values of the minimum, maximum and default minerals depth that define the last
     * (100*(1-n))% of lines of the world with possibility to receive minerals.
     */
    MINERALS_DEPTH(new DefaultDataContainerImpl<Float>(0F, 1F, 0.4F)),
    /**
     * Contains floats values of the minimum, maximum and float default mutation rate.
     */
    MUTATION_RATE(new DefaultDataContainerImpl<Float>(0.05F, 1F, 0.25F));

    private final DefaultDataContainer<? extends Number> data;

    GenesDefaultDataEnum(final DefaultDataContainer<? extends Number> data) {
        this.data = data;
    }

    @Override
    public DefaultDataContainer<? extends Number> getData() {
        return this.data;
    }

}
