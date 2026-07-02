package model.minigames.perilouspath;

import java.util.LinkedList;

import utility.Pair;

/**
 * Represents the game's path.
 */
public interface PathGame {

    /**
     * Gets the game's path.
     * 
     * @return the game's path
     */
    LinkedList<Pair<Integer, Integer>> getPathList(); //NOPMD
}
