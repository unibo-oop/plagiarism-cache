package model;

import java.util.Set;

import model.player.PlayerInfo;
import model.state.StateInfo;
import utils.CircularList;
import utils.enumerations.MapType;

/**
 * Interface for a Model. It manages the logic of the game.
 */

public interface Model {

    /**
     * Restructures the players' list in order to place the players at their initial positions.
     */
    void reorganize();

    /**
     * Shifts all players left of one position in players' list.
     */
    void shiftPlayer();

    /**
     * @return the players' list.
     */
    CircularList<? extends PlayerInfo> getPlayers();

    /**
     * @return the set of states.
     */
    Set<? extends StateInfo> getStates();

    /**
     * @return the singleton {@link AttackManager}.
     */
    AttackManagerInterface getAttackManager();

    /**
     * @return the singleton {@link AIManager}.
     */
    AIManagerInterface getAIManager();

    /**
     * @return the singleton {@link DeployManager}.
     */
    DeployManagerInterface getDeployManager();

    /**
     * @return the singleton {@link BonusCardManagerInterface}.
     */
    BonusCardManagerInterface getBonusCardManager();

    /**
     * @return the player who is currently playing .
     */
    PlayerInfo getActualPlayer();

    /**
     * Called at the end of each turn. It takes the actual basic structures of the game and saves them
     * in a {@link ModelMemento} object. It saves that object in {@link CareMementoTaker}.
     */
    void autoSave();

    /**
     * It takes the most recent {@link ModelMemento} in {@link CareMementoTaker} and save it on file.
     * @param fileName
     *                the file name. If null or empty, the name will be composed by the current date.
     */
    void saveOnFile(String fileName);

    /**
     * Called when the player want to close the current game, resets all managers to their initial conditions.
     */
    void resetGame();

    /**
     * @return the current {@link utils.enumerations.MapType}.
     */
    MapType getMap();

    /**
     * Checks if all players are AI controller.
     * @return true if all players are AI controller, false otherwise.
     */
    boolean allAIPlayers();

}
