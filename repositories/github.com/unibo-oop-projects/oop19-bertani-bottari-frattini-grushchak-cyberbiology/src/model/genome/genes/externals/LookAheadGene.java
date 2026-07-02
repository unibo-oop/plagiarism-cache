package model.genome.genes.externals;

import java.util.Optional;

import model.entity.Entity;
import model.entity.EntityTypeNameEnum;
import model.entity.cell.CellTypeNameEnum;
import model.entity.cell.standard.CellStandard;
import model.genome.genes.steps.GenomeStep;
import model.genome.genes.steps.GenomeStepEnum;
import model.world.World;

/**
 * 
 * This class makes cell look ahead, and in base what it sees effectuate a
 * genome jump.
 *
 */
public class LookAheadGene extends AbstractExternalGene implements CellFamily {
    /**
     * @param world the current world of simulation.
     */
    public LookAheadGene(final World world) {
        super(world);
    }

    @Override
    public final void launch(final CellStandard cell) {
        final Optional<Entity> something = getEntityInDirection(cell.getX(), cell.getY(), cell.getDirection());

        if (something.isPresent()) {
            GenomeStep step;
            final EntityTypeNameEnum type = something.get().getEntityType();
            if (type.equals(EntityTypeNameEnum.CELL)) {
                @SuppressWarnings ("unchecked")
                final Optional<CellStandard> cellToLook = (Optional<CellStandard>) tryCastToSpecificCell(something.get(), CellTypeNameEnum.CELL_STANDARD_ALIVE);
                if (cellToLook.isEmpty()) {
                    step = GenomeStepEnum.ONE;
                } else {
                    if (areRelative(cell, cellToLook.get())) {
                        step = GenomeStepEnum.TWO;
                    } else {
                        step = GenomeStepEnum.THREE;
                    }
                }
            } else if (type.equals(EntityTypeNameEnum.STONE)) {
                step = GenomeStepEnum.FOUR;
            } else {
                step = GenomeStepEnum.FIVE;
            }
            cell.getGeneValueWithOffsetAndJump(step.getStep());
        }
    }

    @Override
    public final String getDescription() {
        return "Look ahead";
    }
}
