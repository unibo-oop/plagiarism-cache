package model.genome.genes.externals;

import model.entity.cell.standard.CellStandard;
import model.world.CantMoveinDirectionException;
import model.world.World;

/**
 * 
 * The movement gene tries to move a cell in its direction.
 *
 */
public class MovementGene extends AbstractExternalGene {
    /**
     * @param world the current world of simulation.
     */
    public MovementGene(final World world) {
        super(world);
    }

    @Override
    public final void launch(final CellStandard cell) {
        getWorld().tryMoveCell(cell.getX(), cell.getY(), cell.getDirection());
    }

    @Override
    public final String getDescription() {
        return "Movement";
    }

}
