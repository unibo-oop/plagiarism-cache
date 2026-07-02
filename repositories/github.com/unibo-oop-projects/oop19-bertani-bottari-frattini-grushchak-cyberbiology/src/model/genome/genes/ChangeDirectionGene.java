package model.genome.genes;


import model.direction.Direction;
import model.direction.DirectionDecryptor;

import model.entity.cell.standard.CellStandard;
import model.genome.genes.steps.GenomeStepEnum;

/**
 * 
 * Change the direction of a cell.
 *
 */
public class ChangeDirectionGene extends AbstractGeneDescription {

    @Override
    public final void launch(final CellStandard cell) {
         final int parameter = cell.getGeneValueWithOffsetAndJump(GenomeStepEnum.ONE.getStep());
         final Direction direction = DirectionDecryptor.getDirection(parameter);
         cell.setDirection(direction);
    }

    @Override
    public final String getDescription() {
        return "Change direction";
    }

}
