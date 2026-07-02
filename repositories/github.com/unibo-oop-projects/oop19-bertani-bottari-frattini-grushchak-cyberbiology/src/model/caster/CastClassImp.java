package model.caster;

import model.entity.Entity;
import model.entity.EntityTypeNameEnum;
import model.entity.cell.Cell;
import model.entity.cell.cellDead.CellDead;
import model.entity.cell.standard.CellStandard;
import model.entity.stone.Stone;

public class CastClassImp implements CastClass {

    @Override
    public final Cell cellCast(final Entity entity) {
        if (entity instanceof Cell) {
                return (Cell) entity;
            }
        throw new IllegalStateException("not a cell");
    }

    @Override
    public final Stone stoneCast(final Entity entity) {
        if (entity.getEntityType() == EntityTypeNameEnum.STONE) {
            return (Stone) entity;
        }
        throw new IllegalStateException("not a stone");
    }

    @Override
    public final CellDead cellDeadCast(final Entity entity) {
        if (entity instanceof CellDead) {
            return (CellDead) entity;
        }
        throw new IllegalStateException("not a dead cell");
    }

    @Override
    public final CellStandard cellStandardCast(final Entity entity) {
        if (entity instanceof CellStandard) {
            return (CellStandard) entity;
        }
        throw new IllegalStateException("not an alive cell");
    }
}
