package model.gamerules;

import javafx.util.Pair;
import model.objectives.Objective;

/**
 * Establish the Rules for the Game in order to set up the map.
 */
public interface GameRules {
    /**
     * 
     * @return the selected size of the map for this game
     */
    Pair<Integer, Integer> getMapSize();

    /**
     * 
     * @return an objective, can be random depending on the game mode
     */
    Objective generateObjective();

    /**
     * 
     * @return the initial values for the resources that depends on the game mode
     */
    int getInitialValues();

    /**
     * @return the description of this GameRule
     */
    String getDescription();

}
