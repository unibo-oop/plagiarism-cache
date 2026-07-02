package model.genome.genes.externals;

import java.util.Optional;

import model.entity.Entity;
import model.entity.EntityTypeNameEnum;
import model.entity.cell.CellTypeNameEnum;
import model.entity.cell.standard.CellStandard;
import model.entity.cell.standard.obtainable.EnergyTypeEnum;
import model.world.World;

/**
 * 
 * If in direction of the cell is an another cell, the cell give mineral to the
 * another cell.
 * 
 */
public class GiveResourcesGene extends AbstractExternalGene {
    /**
     * @param world       the current world of simulation.
     */
    public GiveResourcesGene(final World world) {
        super(world);
    }

    @Override
    public final void launch(final CellStandard cell) {
        final Optional<Entity> something = getEntityInDirection(cell.getX(), cell.getY(), cell.getDirection());

        if (something.isPresent() && something.get().getEntityType().equals(EntityTypeNameEnum.CELL)) {
            @SuppressWarnings ("unchecked")
            final Optional<CellStandard> receiverCell = (Optional<CellStandard>) tryCastToSpecificCell(something.get(), CellTypeNameEnum.CELL_STANDARD_ALIVE);

            if (receiverCell.isPresent()) {
                final int minerals = cell.getMineral() / 4;
                cell.decrementMineral(minerals);
                receiverCell.get().incrementMineral(minerals);

                final int energy = cell.getEnergy() / 4;
                cell.decrementEnergy(energy);
                receiverCell.get().incrementEnergy(energy, EnergyTypeEnum.ALTRUISM);
            }
        }

    }

    @Override
    public final String getDescription() {
        return "Give resources";
    }

}
