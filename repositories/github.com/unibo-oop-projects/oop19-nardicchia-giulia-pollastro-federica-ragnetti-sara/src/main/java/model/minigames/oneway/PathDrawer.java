package model.minigames.oneway;

import java.util.List;

import utility.Pair;

/**
 * Interface for drawing a particular path. 
 *
 */
public interface PathDrawer {

   /** 
     * Generate the path that links the initial to the final position.
     * 
     */
    void drawPath();

    /**
     * 
     * @return the initial position
     */
    Pair<Integer, Integer> getInitialPosition();

    /**
     * 
     * @return the complete drawn path
     */
    List<Direction> getSteps();

    /**
     * 
     * @return the final position
     */
    Pair<Integer, Integer> getFinalPosition();
}
