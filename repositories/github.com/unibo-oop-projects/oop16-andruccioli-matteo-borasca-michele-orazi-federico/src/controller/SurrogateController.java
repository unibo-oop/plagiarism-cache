package controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Triple;

import model.bonus.BonusCard;
import model.state.StateInfo;
import utils.enumerations.Color;
import utils.enumerations.ControlType;
import utils.enumerations.MapType;
import utils.enumerations.Status;

/**
 * Interface for a generic Controller. Exposes methods usable by the View.
 *
 */
public interface SurrogateController {

    /**
     * Starts the {@link TankAnalyst} thread that checks correctness of the choice.
     * 
     * @param choice
     *        specifies the choice made by the user: it consists of the States, where the deployment is done, 
     *        associated with their own number of tanks deployed.
     */
    void checkDeployment(Map<StateInfo, Integer> choice);

    /**
     * Starts the {@link StateAnalyst} thread that checks correctness of the choice.
     * 
     * @param striker
     *               the {@link model.State} owned by the striker.
     * @param defender
     *               the {@link model.State} owned by the defender.
     * @param tanksNumber
     *               the number of tanks used for the attack.
     */
    void checkStates(StateInfo striker, StateInfo defender, int tanksNumber);

    /**
     * Manages the end of the turn.
     */
    void endTurn();

    /**
     * Gets the current {@link utils.Status} of the game.
     * @return the current {@link utils.Status} of the game.
     * 
     */
    Status getGameStatus();

    /**
     * Initializes the game by saying to {@link model.GameLoader#initGame(List, MapType)} to build the basic structures of the game.
     * The Model returned by that method is registered as the current model. 
     * @param playersInfo
     *               the players to be inserted.
     * @param mapType
     *               the chosen map type.
     */
    void initialization(List<Triple<String, Color, ControlType>> playersInfo, MapType mapType);

    /**
     * Initializes the map in the view. This method must be called after a call to {@link #initialization(List, MapType)} method,
     * because it uses the basic structures built by it.
     */
    void initializeMap();

    /**
     * Starts the {@link BattleManager} thread that starts the battle.
     */
    void initBattle();

    /**
     * Moves the number of tanks specified.
     * 
     * @param tanksNumber
     *                  represents the number of tanks to be moved.
     */
    void tanksToMove(int tanksNumber);

    /**
     * Moves the number of tanks specified form a source to a destination.
     * 
     * @param from
     *            represents the source.
     * @param where
     *            represents the destination.
     * @param tanksNumber
     *            represents the number of tanks to be moved.
     */
    void tanksToMove(StateInfo from, StateInfo where, int tanksNumber);

    /**
     * Saves the current game info on a file with the specified name.
     * @param fileName
     *                the name of the file. If null or empty the system will give it an automatic name.
     */
    void saveOnFile(String fileName);

    /**
     * Loads a game previously saved.
     * @param fileName
     *                the file to read.
     */
    void loadGameFromFile(File fileName);

    /**
     * Called when the player want to close the current game, it warns the model of that choice in order to reset 
     * managers and to permit the return to the initial menu.
     */
    void resetGame();

    /**
     * Gets the best combo for the current player.
     * @return the best combo.
     */
    List<BonusCard> getBestCombo();

    /**
     * Sends to {@link model.BonusCardManager} the bonus cards combo chosen by the player.
     * @param choice
     *          the choice selected by the player.
     */
    void tradeCombo(List<BonusCard> choice);

}
