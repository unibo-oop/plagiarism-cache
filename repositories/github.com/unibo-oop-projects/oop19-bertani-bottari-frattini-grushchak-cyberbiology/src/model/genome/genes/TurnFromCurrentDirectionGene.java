package model.genome.genes;

import model.direction.Direction;
import model.direction.DirectionDecryptor;
import model.entity.cell.standard.CellStandard;
import model.genome.genes.steps.GenomeStepEnum;

/**
 * 
 * Turn cell direction in right or left respect its current direction.
 *
 */
public class TurnFromCurrentDirectionGene extends AbstractGeneDescription {

    @Override
    public final void launch(final CellStandard cell) {
         final int parameter = cell.getGeneValueWithOffsetAndJump(GenomeStepEnum.ONE.getStep());
         // if the parameter is an odd the cell turns to right respect its current direction,
         // otherwise the cell turns to left.
         final Direction direction = parameter % 2 == 0 
                  ? DirectionDecryptor.getDirection(cell.getDirection().getIndex() + 1) 
                          : DirectionDecryptor.getDirection((cell.getDirection().getIndex() - 1));
         cell.setDirection(direction);
    }

    @Override
    public final String getDescription() {
        return "Turn from current direction";
    }

}
