package controller;
import java.util.stream.Stream;

import model.caster.CastClass;
import model.caster.CastClassImp;
import model.entity.EntityTypeNameEnum;
import model.entity.cell.Cell;
import model.entity.cell.CellTypeNameEnum;
import model.gravity.Gravity;
import model.gravity.GravityImp;
import model.world.World;
import model.world.WorldImp;

public class RunnerImp implements Runner {

    private final CastClass cast = new CastClassImp();

    private final World screen;

    private final Gravity gravity;

    RunnerImp(final WorldImp world) {
        this.screen = world;
        this.gravity = new GravityImp(this.screen);
    }

    private Stream<Cell> initialPart() {
        return screen.getMap().values().stream()
                .map(m -> m.getEntity())
                .filter(f -> f.isPresent())
                .map(m -> m.get())
                .filter(f -> f.getEntityType().equals(EntityTypeNameEnum.CELL))
                .map(m -> cast.cellCast(m));
    }

    @Override
    public final void aliveCells() {
        this.initialPart()
        .filter(f -> f.getCellTypeName().equals(CellTypeNameEnum.CELL_STANDARD_ALIVE))
        .map(m -> cast.cellStandardCast(m))
        .filter(f -> f.isActive())
        .forEach(c -> c.run());
    }

    @Override
    public final void deadCells() {
        this.initialPart()
        .filter(f -> f.getCellTypeName()
        .equals(CellTypeNameEnum.CELL_DEAD))
        .map(m -> cast.cellDeadCast(m))
        .forEach(c -> gravity.cellFallingDown(c));

    }
}
