package it.unibo.michelito.model.enemy.api.ai;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;

/**
 *The base interface of movement for enemy.
 */
public interface Movement {
    /**
     * Apply the move for {@link it.unibo.michelito.model.enemy.api.Enemy},
     * based on collision in the maze and time between one movement and another.
     * @param maze the maze where the enemy is.
     * @param time a delta time between a move and another.
     */
    void move(Maze maze, long time);

    /**
     * @return the movement type.
     */
    MovementType getMovementType();

    /**
     * @return the position that the movement is currently on.
     */
    Position getPosition();

    /**
     * @param position set the position where the movement is currently on.
     */
    void setPosition(Position position);
}
