package talisman.controller.battle;


import talisman.model.battle.BattleModel;
import talisman.model.battle.BattleState;
import talisman.model.character.CharacterModel;
import talisman.util.Pair;

/**
 * A MVC controller for the battle.
 * 
 * @author Alice Girolomini
 */
public interface BattleController {
    /**
     * Gets the model of the battle.
     * 
     * @return the model of the battle
     */
    BattleModel getBattle();

    /**
     * Initializes the scores of the opponents in the view.
     * 
     * @return the model
     */
    Pair<Integer, Integer> initializeScores();

    /**
     * Gets the current turn.
     * 
     * @return the turn
     */
    int getTurn();

    /**
     * Checks whether the opponent can roll the dice or not.
     * 
     * @return true if it's possible to roll the dice
     */
    boolean canRoll();

    /**
     * Gets the first opponent's model.
     * 
     * @return the model of the opponent
     */
    CharacterModel getFirstCharacter();

    /**
     * Gets the second opponent's model.
     * 
     * @return the model of the opponent
     */
    CharacterModel getSecondCharacter();

    /**
     * Gets the outcome of the battle.
     * 
     * @return the  associated to the outcome of the battle
     */
    BattleState getResult();

    /**
     * Checks whether the opponents can use a fate token.
     * 
     * @return true if the character can use a fate token
     */
    boolean requestedFate();

    /**
     * Uses a fate token.
     */
    void updateFate();

    /**
     * Rolls the dice for one of the opponents.
     * 
     * @return the result
     */
    int updateRoll();

    /**
     * Sums the score to the dice result for one of the opponents.
     * 
     * @return the final score
     */
    int updateScore();

    /**
     * Updates the score and passes the turn.
     * 
     * @return the final score
     */
    int requestedAttack();

    /**
     * Calls the death controller on the defeated character.
     * 
     * @param character - the character that has been defeated
     * @return true if the character died
     */
    boolean checkDeath(CharacterModel character);

}
