package model.command;

/**
 * A class that implements the movement of the tank in left direction.
 *
 */
public class MoveLeftCommand extends AbstractMovementCommand implements Command {

    public MoveLeftCommand() {
        super(Direction.LEFT);
    }

}
