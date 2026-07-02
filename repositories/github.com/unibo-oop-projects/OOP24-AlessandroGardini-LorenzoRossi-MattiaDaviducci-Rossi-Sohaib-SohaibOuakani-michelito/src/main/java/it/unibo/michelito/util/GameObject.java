package it.unibo.michelito.util;

import it.unibo.michelito.model.maze.api.Maze;

import java.io.Serial;
import java.io.Serializable;

/**
 * Generic Object of the game, used outside the {@link Maze}.
 *
 * @param objectType the {@link ObjectType}.
 * @param position the {@link Position}.
 */
public record GameObject(ObjectType objectType, Position position) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
