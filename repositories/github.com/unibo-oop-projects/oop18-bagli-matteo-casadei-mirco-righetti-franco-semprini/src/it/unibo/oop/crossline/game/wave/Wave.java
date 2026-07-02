package it.unibo.oop.crossline.game.wave;

import java.util.List;
import it.unibo.oop.crossline.game.actor.robot.Robot;

/**
 * This interface represent a wave.
 */
public interface Wave {

    /**
     * Get the wave difficulty.
     * @return the wave difficulty
     */
    float getDifficulty();

    /**
     * Get the list of robots that represent the wave.
     * @return the list of robots
     */
    List<Robot> getRobots();

    /**
     * Returns a boolean that tells if wave has ended.
     * @return the wave state
     */
    boolean hasEnded();

}
