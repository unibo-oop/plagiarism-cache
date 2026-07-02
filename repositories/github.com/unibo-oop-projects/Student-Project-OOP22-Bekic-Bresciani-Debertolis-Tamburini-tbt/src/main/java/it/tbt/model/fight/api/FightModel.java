package it.tbt.model.fight.api;

import java.util.List;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Enemy;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.StateTrigger;

/**
 * Interface representing the fight model in a game.
 * It provides methods to access and manipulate the fight state.
 */
public interface FightModel extends StateTrigger {

    /**
     * Get the current ally in the fight.
     *
     * @return the current {@link Ally}
     */
    Ally getCurrentAlly();

    /**
     * Get the list of all allies participating in the fight.
     *
     * @return a {@link List} of allies
     */
    List<Ally> getAllies();

    /**
     * Get the list of all enemies in the fight.
     *
     * @return a {@link List} of enemies
     */
    List<Enemy> getEnemies();

    /**
     * Get the index of the currently selected target.
     *
     * @return the index of the selected target
     */
    int getSelectedTargetIndex();

    /**
     * Select the previous target in the list of targets.
     */
    void selectPreviousTarget();

    /**
     * Select the next target in the list of targets.
     */
    void selectNextTarget();

    /**
     * Perform the selected action in the fight.
     */
    void performSelectedAction();

    /**
     * Check if the current action is a skill.
     *
     * @return true if using a skill, false otherwise
     */
    boolean isUsingSkill();

    /**
     * Select the action based on the given parameters.
     *
     * @param b the first selection parameter
     * @param c the second selection parameter
     * @param d the third selection parameter
     */
    void selectAction(boolean b, boolean c, boolean d);

    /**
     * Check if the current action is using a potion.
     *
     * @return true if using a potion, false otherwise
     */
    boolean isUsingPotion();

    /**
     * Check if the current action is using an antidote.
     *
     * @return true if using an antidote, false otherwise
     */
    boolean isUsingAntidote();

    /**
     * Initialize the party for the fight.
     *
     * @param party the {@link IParty} representing the party in the fight
     */
    void initializeParty(IParty party);

}
