package model.genome.genes;

import model.direction.DirectionEnum;
import model.entity.cell.standard.CellStandard;
import model.genome.genes.steps.GenomeStepEnum;

/**
 * 
 * Change direction of cell in a horizontal direction.
 *
 */
public class HorizontallyAligningGene extends AbstractGeneDescription {

    @Override
    public final void launch(final CellStandard cell) {
        final int parameter = cell.getGeneValueWithOffsetAndJump(GenomeStepEnum.ONE.getStep());
        if (cell.getNumberOfGenes() / 2 > parameter) {
            cell.setDirection(DirectionEnum.EAST);
        } else {
            cell.setDirection(DirectionEnum.WEST);
        }
    }

    @Override
    public final String getDescription() {
        return "Horizontally aligning";
    }

}
