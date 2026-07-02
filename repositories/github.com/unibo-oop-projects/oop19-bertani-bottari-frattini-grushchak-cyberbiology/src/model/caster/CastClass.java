package model.caster;

import model.entity.Entity;
import model.entity.cell.Cell;
import model.entity.cell.cellDead.CellDead;
import model.entity.cell.standard.CellStandard;
import model.entity.stone.Stone;

/** 
 * class used for castings.
 *
 */
public interface CastClass {

    /**
     * @param entity
     * @return Entity casted as StandardCell, or throw an IllegalStateException if it isn't
     */
    Cell cellCast(Entity entity);
    /**
     * 
     * @param entity
     * @return Entity casted as a dead cell, or throw an IllegalStateException if it isn't
     */
    CellDead cellDeadCast(Entity entity);
    /**
     * 
     * @param entity
     * @return Entity casted as Stone, or throw an IllegalStateException if it isn't
     */
    Stone stoneCast(Entity entity);
    /**
     * 
     * @param entity
     * @return Entity or Cell casted as a StandardCell, or throw an IllegalStateException if it isn't
     */
    CellStandard cellStandardCast(Entity entity);

}
