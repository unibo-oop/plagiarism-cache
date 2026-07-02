package model.genome.genes.externals;

import model.entity.cell.standard.CellStandard;
import model.world.World;

/**
 * 
 * Death gene orders to the world to replace the cell with a dead cell.
 *
 */
public class DeathGene extends AbstractExternalGene {
    /**
     * @param world  the current world of simulation.
     */
    public DeathGene(final  World world) {
        super(world);
    }

    @Override
    public final void launch(final CellStandard cell) {
        getWorld().makeCellDeath(cell.getX(), cell.getY());
        cell.deactive();
    }

    @Override
    public final String getDescription() {
        return "Death";
    }
}
