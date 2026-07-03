package model;


import javafx.util.Pair;

/**
 * @author Simone
 * A world that can manage and update some model element.
 */
public interface World {

    /**
     * updates every object in world.
     */
    void updateState();

    /**
     * @return if the game is finished
     */
    boolean isItGameOver();

    /**
     * @return if world has reset
     */
    boolean isWorldReset();

    /**
     * @return a Pair(comboTeam1, comboTeam2)
     */
    Pair<Integer, Integer> getComboSum();

    /**
     * @return a Pair(scoreTeam1, scoreTeam2)
     */
    Pair<Integer, Integer> getPairScore();
}
