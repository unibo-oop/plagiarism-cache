package model.command;

public class MoveUpCommand extends AbstractMovementCommand implements Command {

    public MoveUpCommand() {
        super(Direction.UP);
    }
}
