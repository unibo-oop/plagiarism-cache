package model.properties.defaultdata;

/**
 * 
 * Every enum contains a container with limits and default values of different utilities utilized by cells.
 *
 */
public enum CellsDefaultDataEnum implements DefaultDataEnum {
    /**
     *  Contains integer values of the minimum, maximum and default number of maximum cell energy.
     */
    MAX_CELL_ENERGY(new DefaultDataContainerImpl<Integer>(100, 2000, 1000)),
    /**
     *  Contains integer values of the minimum, maximum and default number of maximum minerals that can be contained into cell.
     */
    MAX_CELL_MINERALS(new DefaultDataContainerImpl<Integer>(100, 5000, 2000)),
    /**
     * Contains integer values of the minimum, maximum and default number of energy that must be spend by cell every turn.
     */
    TURN_COST(new DefaultDataContainerImpl<Integer>(0, 5, 1)),
    /**
     * Contains integer values of the minimum, maximum and default number of maximum age of a cell.
     */
    MAX_AGE(new DefaultDataContainerImpl<Integer>(500, 10000, 3000)),
    /**
     * Contains integer values of the minimum, maximum and default number of initial cell genome size.
     */
    GENOME_SIZE(new DefaultDataContainerImpl<Integer>(5, 100, 30)),
    /**
     * Contains integer values of the minimum, maximum and default number of different types of genes.
     */
    NUMBER_GENE_TYPES(new DefaultDataContainerImpl<Integer>(CellsDefaultDataEnum.GENE_TYPES_MULTIPLE * 4, 
                                                    CellsDefaultDataEnum.GENE_TYPES_MULTIPLE * 8,
                                                    CellsDefaultDataEnum.GENE_TYPES_MULTIPLE * 5));

    /**
     * The number of types of genes must be multiple of this value.
     */
    public static final int GENE_TYPES_MULTIPLE = 8;

    private final DefaultDataContainer<? extends Number> data;

    CellsDefaultDataEnum(final DefaultDataContainer<? extends Number> data) {
        this.data = data;
    }

    @Override
    public DefaultDataContainer<? extends Number> getData() {
        return this.data;
    }
}
