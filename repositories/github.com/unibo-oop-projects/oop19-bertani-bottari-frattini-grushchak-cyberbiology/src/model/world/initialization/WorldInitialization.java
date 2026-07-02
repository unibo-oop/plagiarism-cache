package model.world.initialization;


import java.util.Random;
import java.util.stream.Stream;

import model.entity.cell.Cell;
import model.entity.cell.standard.prefabCell.CellCreator;
import model.world.World;
import utilities.Pair;

/**
 * 
 * Initialize world of simulation.
 *
 */
public class WorldInitialization {
    /**
     * @param world of current simulation.
     * @param creator of new cells.
     * @param modality modality of initialization.
     */
    public WorldInitialization(final World world, final CellCreator creator, final Modality modality) {
        System.out.println("start intilization");
        switch (modality) {
        case SINGLE_PHOTOSYNTHESIS_CELL:
            final Cell cell = creator.newAllPhotosyntesisCell(world.getWorldWidth() / 2, world.getWorldHeight() / 4);
            world.putCell(cell.getX(), cell.getY(), cell);
            break;
        case RANDOMS_GENOME_CELLS:
            final int numCells = (world.getWorldHeight() * world.getWorldWidth()) / 20;
            final Random ran = new Random();
            System.out.println(numCells);
            Stream.generate(() -> new Pair<Integer, Integer>(ran.nextInt(world.getWorldWidth()), ran.nextInt(world.getWorldHeight())))
                    .filter(f -> world.getSquare(f.getX(), f.getY()).getEntity().isEmpty())
                    .limit(numCells)
                    .forEach(e -> world.putCell(e.getX(), e.getY(), creator.newAllRandomCell(e.getX(), e.getY())));
            break;
        default:
            throw new IllegalStateException("modality is not implemented");
        }
        System.out.println("finish intilization");
    }

}
