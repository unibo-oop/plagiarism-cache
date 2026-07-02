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
 * If in direction of the cell is an another cell with less
 * energy, the cell share energy with another for make their energies equal.
 *
 */
public class ShareEnergyGene extends AbstractExternalGene {
    /**
     * @param world       the current world of simulation.
     */
    public ShareEnergyGene(final World world) {
        super(world);
    }

    @Override
    public void launch(final CellStandard cell) {
        final Optional<Entity> something = getEntityInDirection(cell.getX(), cell.getY(), cell.getDirection());
        
        if(something.isPresent() && something.get().getEntityType().equals(EntityTypeNameEnum.CELL)) {
            final Optional<CellStandard> receiverCell  = (Optional<CellStandard>)tryCastToSpecificCell(something.get(), CellTypeNameEnum.CELL_STANDARD_ALIVE);
            
            if(receiverCell.isPresent() && receiverCell.get().isActive() && cell.getEnergy() > receiverCell.get().getEnergy()) {
                final int energy = (cell.getEnergy() - receiverCell.get().getEnergy()) / 2;
                cell.decrementEnergy(energy); 
                receiverCell.get().incrementEnergy(energy, EnergyTypeEnum.ALTRUISM); // aggiungere tipo
            }
        }
    }

    @Override
    public String getDescription() {
        return "Share energy";
    }
}
