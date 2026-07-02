package it.unibo.arkanoid.controller;

import it.unibo.arkanoid.model.Model;

/**
 * A Command class for move {@link Paddle}.
 *
 */
public class MovePaddleCommand implements Command {

    private final double x;

    /**
     * Constructors for MovePaddleCommand.
     * 
     * @param x
     *            X coordinate.
     */
    public MovePaddleCommand(final Double x) {
        this.x = x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Model model) {
        model.setPaddlePosition(this.x);
    }

}
