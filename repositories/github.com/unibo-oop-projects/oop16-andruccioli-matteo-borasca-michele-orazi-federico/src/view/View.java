package view;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.util.Pair;
import model.player.PlayerInfo;
import model.state.StateInfo;
import utils.CircularList;
import utils.enumerations.MapType;

/**
 * 
 * Interface for a generic View.
 *
 */
public interface View {

    /**
     * 
     * Start the GUI and show mainmenu.
     * 
     */
    void startView();

    /**
     * 
     * Set the reference to playerList and stateList.
     * 
     * @param circularList
     *                      List of players of the current game.
     *
     * @param set
     *                      List of all states.
     * @param map
     *                      the map type.
     * 
     */
    void setGameList(CircularList<? extends PlayerInfo> circularList, Set<? extends StateInfo> set, Optional<MapType> map);

    /**
     * 
     * Draw game map and all gameplay components.
     * 
     */
    void drawMap();

    /**
     * 
     * Checks for changes in states and update the current view.
     * 
     */
    void updateStates();

    /**
     * 
     * Show the info of current player.
     * 
     */
    void updateInfoPlayer();

    /**
     * 
     * Predispose GUI to tank's "manual insertion" by user interaction.
     * 
     */
    void deployTanks();

    /**
     * 
     * Show an error message.
     * Due to an error in the selection of the states it make repeat the selection phase.
     * 
     */
    void revertAction();

    /**
     * 
     * Update the view. Show the action made by AI.
     * 
     * @param assignment
     *                   List of pair with State selected by AI linked with the number of tanks to add in it.
     * 
     */
    void updateStates(List<StateInfo> assignment);

    /**
     * 
     * Update the view. Show value of dice after a battle.
     * 
     * @param diceValue
     *                  List of pair containing two integer that are the value of dice thrown by players or AI.
     * 
     */
    void updateDice(List<Pair<Integer, Integer>> diceValue);

    /**
     * 
     * Print an error message on screen.
     * 
     * @param error
     *               An error message to be displayed.
     * 
     */
    void printError(String error);

    /**
     * 
     * If a player completed his objective close the Gameplay View and show another to celebrate the winner.
     * 
     */
    void showVictory();

    /**
     * 
     * Set a flag inside view when (user) deploy phase is finished. 
     * 
     */
    void deployPhaseFinished();

    /**
     * 
     * Set a flag inside view when (AI) attack pahse is finished.
     * 
     */
    void aIAttackPhaseFinished();

    /**
     * 
     * Update the log section.
     * 
     * @param msg
     *              String to be added to the log.
     * 
     */
    void updateLog(String msg);

    /**
     * Disables or enable all components.
     * @param check
     *             true to disable, false to enable.
     */
    void disableAllComponents(boolean check);

    /**
     * Shows a dialog message.
     */
    void showMovementDialog();
}