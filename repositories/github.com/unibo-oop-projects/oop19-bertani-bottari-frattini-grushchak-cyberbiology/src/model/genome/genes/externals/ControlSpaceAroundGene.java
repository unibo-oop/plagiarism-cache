package model.genome.genes.externals;


import model.entity.cell.standard.CellStandard;
import model.genome.genes.steps.GenomeStepEnum;
import model.world.World;

/**
 * 
 * If around of cell are not free space, the cell effectuates a genome jump.
 *
 */
public class ControlSpaceAroundGene extends AbstractExternalGene {
    /**
     * @param world the current world of simulation.
     */
    public ControlSpaceAroundGene(final World world) {
        super(world);
    }

    @Override
    public final void launch(final CellStandard cell) {
        if (!getWorld().isThereFreeSpaceAround(cell.getX(), cell.getY())) {
            cell.getGeneValueWithOffsetAndJump(GenomeStepEnum.ONE.getStep());
        }
    }

    @Override
    public final String getDescription() {
        return "Control space around";
    }

}
