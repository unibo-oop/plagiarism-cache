package main.java.com.controller;

import main.java.com.model.Model;
import main.java.com.utility.Direction;

/**
 * Implements the {@link Command} interface for move left action.
 */
public class MoveLeft implements Command {

    /** {@inheritDoc} */
    @Override
    public void execute(final Model gameModel) {
        gameModel.getSnake().setDirection(Direction.LEFT);
    }

    /** {@inheritDoc} */
    @Override
    public Direction getDir() {
        return Direction.LEFT;
    }

}
