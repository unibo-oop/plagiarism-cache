package model.gravity;

import java.util.Optional;

import model.direction.Direction;
import model.direction.DirectionEnum;
import model.entity.cell.cellDead.CellDead;
import model.world.World;

public class GravityImp implements Gravity {

    private final World screen;

    private final DirectionEnum direction = DirectionEnum.SOUTH;

    public GravityImp(final World world) {
        this.screen = world;
    }

    @Override
    public final void cellFallingDown(final CellDead cell) {
       tryMoveCell(cell.getX(), cell.getY(), direction, cell);
    }

    private void tryMoveCell(final int x, final int y, final Direction direction, final CellDead cell) {
        int w = x + direction.movementAlongX();
        int h = y + direction.movementAlongY();
        if (!screen.isThereAnything(w, h)) {
            screen.getSquare(w, h).setEntity(Optional.of(cell));
            screen.getSquare(x, y).setEntity(Optional.empty());
            cell.setX(w);
            cell.setY(h);
        }
    }
}
