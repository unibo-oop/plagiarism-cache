package it.unibo.michelito.controller.gamecontroller.movecommandbuilder.api;

import it.unibo.michelito.controller.playercommand.impl.MoveCommand;
import it.unibo.michelito.util.Direction;
/**
 * Interface that models a builder for a {@link MoveCommand}.
 */
public interface MoveCommandBuilder {
    /**
     * Adds a {@link Direction} to the {@link MoveCommandBuilder}.
     * @param direction the direction added.
     * @return the same instance of the {@link MoveCommandBuilder} but modified.
     */
    MoveCommandBuilder addDirection(Direction direction);

    /**
     * Builds and returns a {@link MoveCommand} with the combined {@link Direction}s.
     * @return a {@link MoveCommand} with the combined directions.
     */
    MoveCommand build();
}
