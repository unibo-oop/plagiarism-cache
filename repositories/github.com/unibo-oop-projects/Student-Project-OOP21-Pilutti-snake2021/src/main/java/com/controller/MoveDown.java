package main.java.com.controller;

import main.java.com.model.Model;
import main.java.com.utility.Direction;

/**
 * Implements the {@link Command} interface for move down action.
 */
public class MoveDown implements Command {

    /** {@inheritDoc} */
    @Override
    public void execute(final Model gameModel) {
        gameModel.getSnake().setDirection(Direction.DOWN);
    }

    /** {@inheritDoc} */
    public Direction getDir() {
        return Direction.DOWN;
    }

}
