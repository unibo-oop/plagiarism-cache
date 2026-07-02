package it.unibo.michelito.model.enemy.api.ai;

import it.unibo.michelito.model.maze.api.Maze;

/**
 *An AI that change the Movement of the enemy depending on various parameter.
 */
public interface MoodAI {

    /**
     * @return the current Movement.
     */
    Movement getMovement();

    /**
     * @param currentTime currentTime update the AI so he can change base of the actual condition.
     * @param maze the current maze for get data.
     */
    void update(long currentTime, Maze maze);

}
