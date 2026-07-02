package model.genome.genes.externals;


import java.util.Random;

import model.direction.DirectionDecryptor;
import model.entity.cell.standard.CellStandard;
import model.genome.genes.Gene;
import model.world.World;

/**
 * 
 * Create new cell and put it in world.
 *
 */
public class ReproductionGene extends AbstractExternalGene {
    private final Gene mutationGene;
    private final int reproductionCost;

    /**
     * @param world of current simulation.
     * @param mutationGene a gene that can mutate a cell.
     * @param reproductionCost amount of energy that cell spend trying reproduce.
     */
    public ReproductionGene(final World world, final Gene mutationGene, final int reproductionCost) {
        super(world);
        this.mutationGene = mutationGene;
        this.reproductionCost = reproductionCost;
    }

    @Override
    public final void launch(final CellStandard cell) {
        if (cell.getEnergy() > this.reproductionCost * 2) {
            cell.decrementEnergy(this.reproductionCost);
            if (getWorld().isThereFreeSpaceAround(cell.getX(), cell.getY())) {
                final CellStandard child = cell.makeChild();
                this.mutationGene.launch(child);
                child.setDirection(DirectionDecryptor.getDirection(new Random().nextInt(100)));
                getWorld().putChild(cell.getX(), cell.getY(), child, cell.getDirection());
            }
//            else{
//                getWorld().makeCellDeath(cell.getX(), cell.getY());
//                cell.deactive();
//            }
        }
    }

    @Override
    public final String getDescription() {
        return "Reproduction";
    }

}
