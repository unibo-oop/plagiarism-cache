package main.java.com.controller;

import main.java.com.model.Model;
import main.java.com.utility.Direction;

/**
 * Implements the {@link Command} interface for move right action.
 */
public class MoveRight implements Command {

    /** {@inheritDoc} */
    @Override
    public void execute(final Model gameModel) {
        gameModel.getSnake().setDirection(Direction.RIGHT);
    }

    /** {@inheritDoc} */
    @Override
    public Direction getDir() {
        return Direction.RIGHT;
    }

}
