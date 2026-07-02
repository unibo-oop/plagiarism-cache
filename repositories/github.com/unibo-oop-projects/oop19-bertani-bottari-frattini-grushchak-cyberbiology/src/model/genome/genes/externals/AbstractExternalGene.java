package model.genome.genes.externals;

import java.util.Optional;

import model.caster.CastClass;
import model.caster.CastClassImp;
import model.direction.Direction;
import model.entity.Entity;
import model.entity.cell.Cell;
import model.entity.cell.CellTypeNameEnum;
import model.entity.cell.standard.CellStandard;
import model.genome.genes.AbstractGeneDescription;
import model.world.NoSquareException;
import model.world.World;

/**
 * 
 * The abstract class that implements some common methods of genes that can communicate with the world.
 *
 */
public abstract class AbstractExternalGene extends AbstractGeneDescription {
    private final World world;
    private final CastClass typeCast;

    /**
     * @param world  the current world of simulation.
     */
    protected AbstractExternalGene(final World world) {
        super();
        this.world = world;
        this.typeCast = new CastClassImp();
    }

    @Override
    public abstract void launch(CellStandard cell);

    /**
     * @return the current world of simulation.
     */
    protected World getWorld() {
        return this.world;
    }

    /**
     * Takes an entity in direction of the cell if it is present there.
     * @param x the coordinate of the x-axis
     * @param y the coordinate of the y-axis
     * @param direction in which the cell looks for an entity.
     * @return optional of entity that is in direction of the cell.
     */
    protected Optional<Entity> getEntityInDirection(final int x, final int y, final Direction direction) {
        try {
            return getWorld().getSquare(x + direction.movementAlongX(), y + direction.movementAlongY()).getEntity();
        } catch (NoSquareException e) {
            return Optional.empty();
        }
    }

    /**
     * Tries cast an entity to cell of a type specified by second parameter.
     * @param entity that will be tried casted.
     * @param type of the cell that would be returned if the cast will be successful.
     * @return an cell of the specified type if the cast was successful, otherwise returns Optional.empty().
     */
    protected Optional<? extends Cell> tryCastToSpecificCell(final Entity entity, final CellTypeNameEnum type) {
        final Cell cell = castToCell(entity);
        if (cell.getCellTypeName().equals(type)) {
            switch (type) {
            case CELL_DEAD:
                return Optional.of(this.typeCast.cellDeadCast(cell));
            case CELL_STANDARD_ALIVE:
                return Optional.of(this.typeCast.cellStandardCast(cell));
            default: // return Optional.empty();
            }
        }
        return Optional.empty();
    }

    /**
     * Tries cast an entity to {@link Cell}.
     * @param entity that will be tried casted.
     * @return Optional of {@link Cell} if cast was successful, otherwise returns Optional.empty(). 
     */
    protected Cell castToCell(final Entity entity) {
            return this.typeCast.cellCast(entity);
    }
}
