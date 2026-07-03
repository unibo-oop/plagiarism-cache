package model;

import java.util.List;
import model.exceptions.GameOverException;
import model.state.StateInfo;

/**
 * Interface for {@link AIManager} class.
 */
public interface AIManagerInterface {

    /**
     * Method that trade combo of BonusCards.
     */
    void tryTradingCombo();

    /**
     * Method that deploy the tanks for AI players.
     * 
     * @throws GameOverException
     *             if the player has won the game.
     * 
     * @return The combination of State-Amount of tanks that player made.
     */
    List<StateInfo> deployTanks() throws GameOverException;

    /**
     * Method used for AI players to attack.
     * 
     * @throws GameOverException
     *             if the player has won the game.
     */
    void attack() throws GameOverException;

    /**
     * Method used for AI players to move tanks.
     * 
     * @throws GameOverException
     *             if the player has won the game.
     * 
     * @return True if a movement is done.
     */
    boolean moveTanks() throws GameOverException;

    /**
     * Method that create the map of possible attacks and check if the AI player has finished to attack in that turn.
     * 
     * @return True if AI has finished to attack.
     * 
     */
    boolean createBattleMap();

}