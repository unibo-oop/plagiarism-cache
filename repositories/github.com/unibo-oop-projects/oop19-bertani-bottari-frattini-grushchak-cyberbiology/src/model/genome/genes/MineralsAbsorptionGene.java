package model.genome.genes;


import model.entity.cell.standard.CellStandard;
import model.entity.cell.standard.obtainable.EnergyTypeEnum;

/**
 * The minerals absorption gene give to a cell energy if it is in the minerals depth zone.
 */
public class MineralsAbsorptionGene extends AbstractGeneDescription {
    private final int mineralsAbsorption;
    private final int worldHeight;
    private final float mineralsDepth;

    /**
     * @param worldHeight  How effective is absorbing of minerals.
     * @param mineralsAbsorption how effective is absorbing of minerals.
     * @param mineralsDepth at what depth cells can start absorb minerals.
     */
    public MineralsAbsorptionGene(final int worldHeight,
                                  final int mineralsAbsorption,
                                  final float mineralsDepth) {
        super();
        this.mineralsAbsorption = mineralsAbsorption;
        this.worldHeight = worldHeight;
        this.mineralsDepth = mineralsDepth;
    }

    @Override
    public final void launch(final CellStandard cell) {
        final float depth = (float)cell.getY() / (float)this.worldHeight;
        if (depth >= this.mineralsDepth) {
            final float relativeDepth = (1 - depth) / (1 - this.mineralsDepth);
            final int minerals = Math.round(relativeDepth * this.mineralsAbsorption);
            cell.incrementMineral(minerals);
        }
    }

    @Override
    public final String getDescription() {
        return "Mineral absorption";
    }

}
