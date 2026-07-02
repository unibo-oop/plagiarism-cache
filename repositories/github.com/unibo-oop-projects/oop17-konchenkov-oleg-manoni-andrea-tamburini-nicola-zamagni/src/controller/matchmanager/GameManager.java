package controller.matchmanager;

import model.Model;

/**
 *
 * @author Oleg
 *
 */
public interface GameManager {

    /**
     * Starts turn management that in each turn calls turn loop.
     */
    void startNewTurn();

    /**
     * tanks deploying at the beginning of a match.
     */
    void tankDeploy();

    /**
     * sets turret angle and render it.
     */
    void setTurrenAngle();

    /**
     * checks if the current angle of tanks turret is prohibited and sets a random
     * one.
     */
    void randomizeAngleIfProhibited();

    /**
     * If there are no alive players the game end with no winner, if there is one
     * player alive he's the winner, otherwise next player's turn.
     */
    void isGameEnded();

    /**
     *
     * @return current turn
     */
    int getTurn();

    /**
     *
     * @return model
     */
    Model getModel();

    /**
     *
     * @return turn manager
     */
    TurnManager getTurnManager();

    /**
     * Stop game.
     */
    void stopTurnManager();
}
