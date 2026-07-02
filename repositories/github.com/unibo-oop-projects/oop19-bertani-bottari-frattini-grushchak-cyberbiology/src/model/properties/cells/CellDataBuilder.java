package model.properties.cells;

//The imported class utilized by the documentation references
import model.properties.defaultdata.CellsDefaultDataEnum;

/**
 * 
 * An interface of builder that create {@link CellData}.
 *
 */
public interface CellDataBuilder {

    /**
     * @param cost is the amount of energy that cell spends making its turn.
     * @return this builder.
     * @throws IllegalStateException if the object has already being built.
     * @throws IllegalArgumentException if the passed param is out of limits
     *                                   defined by {@link CellsDefaultDataEnum#TURN_COST}
     */
    CellDataBuilder setTurnCost(int cost);

    /**
     * @param size is the number of genes in genome of cell.
     * @return this builder.
     * @throws IllegalStateException  if the object has already being built.
     * @throws IllegalArgumentException if the passed param is out of limits
     *                                   defined by {@link CellsDefaultDataEnum#GENOME_SIZE}
     */
    CellDataBuilder setGenomeSize(int size);

    /**
     * @param maxEnergy is the maximum number of energy that can have a cell.
     * @return this builder.
     * @throws IllegalStateException  if the object has already being built. 
     * @throws IllegalArgumentException if the passed param is out of limits
     *                                   defined by {@link CellsDefaultDataEnum#MAX_CELL_ENERGY}
     */
    CellDataBuilder setMaxEnergy(int maxEnergy);

    /**
     * @param maxMinerals is the maximum number of minerals that can have a cell.
     * @return this builder.
     * @throws IllegalStateException  if the object has already being built. 
     * @throws IllegalArgumentException if the passed param is out of limits
     *                                   defined by {@link CellsDefaultDataEnum#MAX_CELL_MINERALS}
     */
    CellDataBuilder setMaxMinerals(int maxMinerals);

    /**
     * When a cell reaches this age it dies.
     * @param maxAge maximum age of cell.
     * @return this builder.
     * @throws IllegalStateException  if the object has already being built.
     * @throws IllegalArgumentException if the passed param is out of limits
     *                                   defined by {@link CellsDefaultDataEnum#MAX_AGE}
     */
    CellDataBuilder setMaxAge(int maxAge);

    /**
     * The number of possible genes.
     * 
     * @param number of genes.
     * @return builder.
     * @throws IlleagalArgumentException if the passed param is out of limits
     *                                   defined by
     *                                   {@link CellsDefaultDataEnum#NUMBER_GENE_TYPES}
     *                                   or if is not multiple of
     *                                   {@value CellsDefaultDataEnum#GENE_TYPES_MULTIPLE}.
     */
    CellDataBuilder setNumberOfGenes(int number);

    /**
     * Build a CellData class. All uninitialized fields will be set by default.
     * The default values will be taken from
     * {@link model.properties.defaultdata.CellsDefaultDataEnum}
     * 
     * @return new CellData class
     * @throws IllegalStateException  if the object has already being built.
     */
    CellData build();
}
