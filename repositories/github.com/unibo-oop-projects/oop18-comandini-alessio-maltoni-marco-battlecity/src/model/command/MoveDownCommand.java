package model.command;

public class MoveDownCommand extends AbstractMovementCommand implements Command {

    public MoveDownCommand() {
        super(Direction.DOWN);

    }

}
