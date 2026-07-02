package it.tbt.controller.modelmanager;

import java.util.List;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Enemy;

/**
 * This class manages the communication between the view and the model of the
 * fight.
 */
public interface FightState extends ModelState {

    /**
     * Gets the index of the currently selected target.
     *
     * @return the index of the selected target
     */
    int getSelectedTargetIndex();

    /**
     * Handles the selection of the previous target.
     */
    void handlePreviousTarget();

    /**
     * Handles the selection of the next target.
     */
    void handleNextTarget();

    /**
     * Handles the action performed by the player.
     */
    void handlePerformAction();

    /**
     * Handles cycling through available actions.
     *
     * @param cycleUp true to cycle up, false to cycle down
     */
    void handleCycleAction(boolean cycleUp);

    /**
     * Returns a list of all allies participating in the fight.
     *
     * @return the list of allies
     */
    List<Ally> getAllies();

    /**
     * Returns a list of all enemies participating in the fight.
     *
     * @return the list of enemies
     */
    List<Enemy> getEnemies();

    /**
     * Checks if a skill is currently being used.
     *
     * @return true if a skill is being used, false otherwise
     */
    boolean isUsingSkill();

    /**
     * Checks if an antidote is currently being used.
     *
     * @return true if an antidote is being used, false otherwise
     */
    boolean isUsingAntidote();

    /**
     * Checks if a potion is currently being used.
     *
     * @return true if a potion is being used, false otherwise
     */
    boolean isUsingPotion();

    /**
     * Returns the current ally in control.
     *
     * @return the current ally
     */
    Ally getCurrentAlly();
}
