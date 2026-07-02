package model.properties.cells;

import model.genome.decryptor.GeneDecryptor;
import model.genome.factory.GenesTable;
import model.properties.defaultdata.CellsDefaultDataEnum;
import model.properties.defaultdata.DefaultDataEnum;
import model.properties.utilities.NumbersComparator;

/**
 * 
 * A builder for an {@link CellData}. Can create only a single instance.
 *
 */
public class CellDataBuilderImpl implements CellDataBuilder {
    private final GeneDecryptor geneDecryptor;
    private final GenesTable genesTable; 
    private int turnCost;
    private int genomeSize;
    private int maxEnergy;
    private int maxMinerals;
    private int maxAge;
    private int numberOfGenes;

    private boolean isBuild;

    /**
     * @param decryptor used for read the cell's genome.
     * @param genesTable contains created genes.
     */
    public CellDataBuilderImpl(final GeneDecryptor decryptor, final GenesTable genesTable) {
        this.geneDecryptor = decryptor;
        this.genesTable = genesTable;

        this.turnCost = CellsDefaultDataEnum.TURN_COST.getData().getDafaultValue().intValue();
        this.genomeSize = CellsDefaultDataEnum.GENOME_SIZE.getData().getDafaultValue().intValue();
        this.maxEnergy = CellsDefaultDataEnum.MAX_CELL_ENERGY.getData().getDafaultValue().intValue();
        this.maxMinerals = CellsDefaultDataEnum.MAX_CELL_MINERALS.getData().getDafaultValue().intValue();
        this.maxAge = CellsDefaultDataEnum.MAX_AGE.getData().getDafaultValue().intValue();
        this.numberOfGenes = CellsDefaultDataEnum.NUMBER_GENE_TYPES.getData().getDafaultValue().intValue();
    }

    private void controlIsBuilt() {
        if (this.isBuild) {
            throw new IllegalStateException("This builder has already built");
        }
    }

    private void checkLimits(final int value, final DefaultDataEnum defaultData) {
        check(NumbersComparator.isBiggerThan(defaultData.getData().getMinimumValue(), value)
                || NumbersComparator.isBiggerThan(value, defaultData.getData().getMaximumValue()));
    }

    private void check(final boolean bool) {
        if (bool) {
            throw new IllegalArgumentException("The parameter is out of limit");
        }
    }

    @Override
    public final CellDataBuilder setTurnCost(final int cost) {
        controlIsBuilt();
        checkLimits(cost, CellsDefaultDataEnum.TURN_COST);
        this.turnCost = cost;
        return this;
    }

    @Override
    public final CellDataBuilder setGenomeSize(final int size) {
        controlIsBuilt();
        checkLimits(size, CellsDefaultDataEnum.GENOME_SIZE);
        this.genomeSize = size;
        return this;
    }

    @Override
    public final CellDataBuilder setMaxEnergy(final int maxEnergy) {
        controlIsBuilt();
        checkLimits(maxEnergy, CellsDefaultDataEnum.MAX_CELL_ENERGY);
        this.maxEnergy = maxEnergy;
        return this;
    }

    @Override
    public final CellDataBuilder setMaxMinerals(final int maxMinerals) {
        controlIsBuilt();
        checkLimits(maxMinerals, CellsDefaultDataEnum.MAX_CELL_MINERALS);
        this.maxMinerals = maxMinerals;
        return this;
    }

    @Override
    public final CellDataBuilder setMaxAge(final int maxAge) {
        controlIsBuilt();
        checkLimits(maxAge, CellsDefaultDataEnum.MAX_AGE);
        this.maxAge = maxAge;
        return this;
    }

    @Override
    public final CellDataBuilder setNumberOfGenes(final int number) {
        controlIsBuilt();
        checkLimits(number, CellsDefaultDataEnum.NUMBER_GENE_TYPES);
        if((number % CellsDefaultDataEnum.GENE_TYPES_MULTIPLE != 0)) {
            throw new IllegalArgumentException("The number of genes must be multple of " + CellsDefaultDataEnum.GENE_TYPES_MULTIPLE);
        }
        this.numberOfGenes = number;
        return this;
    }

    @Override
    public final CellData build() {
        controlIsBuilt();
        this.isBuild = true;

        return new CellData() {

            @Override
            public GeneDecryptor getGeneDecryptor() {
                return geneDecryptor;
            }

            @Override
            public int getTurnCost() {
                return turnCost;
            }

            @Override
            public int getGenomeSize() {
                return genomeSize;
            }

            @Override
            public int getMaxEnergy() {
                return maxEnergy;
            }

            @Override
            public int getMaxMinerals() {
                return maxMinerals;
            }

            @Override
            public int getMaxAge() {
                return maxAge;
            }

            @Override
            public int getNumberOfGenes() {
                return numberOfGenes;
            }

            @Override
            public GenesTable getGenesTable() {
                return genesTable;
            }

        };
    }

}
