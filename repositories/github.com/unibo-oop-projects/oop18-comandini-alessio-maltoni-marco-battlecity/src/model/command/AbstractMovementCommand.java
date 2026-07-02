package model.command;

import model.entities.Tank;

public class AbstractMovementCommand implements Command {

    private final  Direction direction;

    public AbstractMovementCommand(final Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute(final Tank tank) {
        tank.getActualMovement().setMovement(direction.getX(), direction.getY());
        tank.setDirection(direction);
    }

}
