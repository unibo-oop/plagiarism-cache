package model.genome.genes.externals;

import java.util.Optional;

import model.entity.Entity;
import model.entity.EntityTypeNameEnum;
import model.entity.cell.CellTypeNameEnum;
import model.entity.cell.standard.CellStandard;
import model.world.World;

/**
 * 
 * If in direction of the cell is an another cell with less mineral, the cell
 * share mineral with another for make their energies equal.
 * 
 */
public class ShareMineralsGene extends AbstractExternalGene {
    /**
     * @param world       the current world of simulation.
     */
    public ShareMineralsGene(final World world) {
        super(world);
    }

    @Override
    public final void launch(final CellStandard cell) {
        final Optional<Entity> something = getEntityInDirection(cell.getX(), cell.getY(), cell.getDirection());

        if (something.isPresent() && something.get().getEntityType().equals(EntityTypeNameEnum.CELL)) {
            
            final Optional<CellStandard> receiverCell = (Optional<CellStandard>) tryCastToSpecificCell(something.get(), CellTypeNameEnum.CELL_STANDARD_ALIVE);

            if (receiverCell.isPresent() && cell.getMineral() > receiverCell.get().getMineral()) {
                final int mineral = (cell.getMineral() - receiverCell.get().getMineral()) / 2;
                cell.decrementMineral(mineral);
                receiverCell.get().incrementMineral(mineral);
            }
        }

    }

    @Override
    public final String getDescription() {
        return "Share minerals";
    }

}
