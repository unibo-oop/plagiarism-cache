package model.command;

public class MoveRightCommand extends AbstractMovementCommand implements Command {

    public MoveRightCommand() {
        super(Direction.RIGHT);
    }

}
