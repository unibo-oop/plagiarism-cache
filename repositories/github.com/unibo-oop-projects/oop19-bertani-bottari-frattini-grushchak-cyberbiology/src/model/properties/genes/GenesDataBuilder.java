package model.properties.genes;
// The imported class utilized by the documentation references
import model.properties.defaultdata.GenesDefaultDataEnum;

/**
 * 
 * An interface of builder that create {@link GenesData}.
 *
 */
public interface GenesDataBuilder {
    /**
     * 
     * @param cost of reproduction.
     * @return this builder.
     * @throws IlleagalArgumentException if the passed param is out of limits
     *                                   defined by
     *                                   {@link GenesDefaultDataEnum#REPRODUCTION_COST}
     */
    GenesDataBuilder setReproductionCost(int cost);

    /**
     * At what depth cells can perform photosynthesis.
     * 
     * @param penetration of sun light into water.
     * @return this builder.
     * @throws IlleagalArgumentException if the passed param is out of limits
     *                                   defined by
     *                                   {@link GenesDefaultDataEnum#SUN_PENETRATION}
     */
    GenesDataBuilder setSunPenetration(float penetration);

    /**
     * From this depth cells can absorb minerals.
     * 
     * @param depth of the minerals zone start.
     * @return this builder.
     * @throws IlleagalArgumentException if the passed param is out of limits
     *                                   defined by
     *                                   {@link GenesDefaultDataEnum#MINERALS_DEPTH}
     */
    GenesDataBuilder setMineralsDepth(float depth);

    /**
     * How effective is photosynthesis.
     * 
     * @param energy of sun.
     * @return this builder.
     * @throws IlleagalArgumentExceptionif the passed param is out of limits defined
     *                                     by
     *                                     {@link GenesDefaultDataEnum#SUN_ENERGY}
     */
    GenesDataBuilder setSunEnergy(int energy);

    /**
     * How effective is absorbing of minerals.
     * 
     * @param absorption is maximum of minerals that cell can receive in one turn.
     * @return this builder.
     * @throws IlleagalArgumentException if the passed param is out of limits
     *                                   defined by
     *                                   {@link GenesDefaultDataEnum#MINERALS_ABSORPTION}
     */
    GenesDataBuilder setMineralsAbsorption(int absorption);

    /**
     * A mutation rate during reproduction.
     * 
     * @param rate of mutation.
     * @return this builder.
     * @throws IlleagalArgumentException if the passed param is out of limits
     *                                   defined by
     *                                   {@link GenesDefaultDataEnum#MUTATION_RATE}
     */
    GenesDataBuilder setMutationRate(float rate);

    /**
     * How much energy a cell receives if it ate a dead cell.
     * 
     * @param nutrition of a dead cell.
     * @return this builder.
     * @throws IlleagalArgumentException if the passed param is out of limits
     *                                   defined by
     *                                   {@link GenesDefaultDataEnum#NUTRITION_OF_DEAD_CELL}
     */
    GenesDataBuilder setNutritionOfDeadCell(int nutrition);

    /**
     * Build a GenesData class. All uninitialized fields will be set by default. The
     * default values will be taken from
     * {@link GenesDefaultDataEnum}
     * 
     * @return completed {@link GenesData} class.
     */
    GenesData build();

}
