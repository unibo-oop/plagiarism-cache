package talisman.controller.battle;

import java.util.ArrayList;
import talisman.model.battle.BattleModel;
import talisman.model.battle.BattleState;
import talisman.model.character.CharacterModel;
import talisman.util.Pair;

/**
 * The implementation of a MVC controller for the battle.
 * 
 * @author Alice Girolomini
 *
 */
public class BattleControllerImpl implements BattleController {
    private static final int FIRSTTURN = 1;
    private static final int SECONDTURN = 2;
    private final BattleModel model;
    private final CharacterModel firstCharacter;
    private final CharacterModel secondCharacter;
    private final ArrayList<Integer> countFate;
    private int turn;
    private boolean roll;

    /**
     * Initializes the controller of the battle.
     * 
     * @param firstCharacter - one of the battle's opponents
     * @param secondCharacter - the other opponent
     * @param model - the model of the battle
     */
    public BattleControllerImpl(final CharacterModel firstCharacter, final CharacterModel secondCharacter, final BattleModel model) {
        this.firstCharacter = firstCharacter;
        this.secondCharacter = secondCharacter;
        this.model = model;
        this.turn = 1;
        this.roll = true;
        countFate = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            countFate.add(0);
        }
    }

    /**
     * Checks which character is fighting.
     */
    private CharacterModel checkTurn() {
        if (this.turn == FIRSTTURN) {
            return this.firstCharacter;
        }
        return this.secondCharacter;
    }

    /**
     * Changes the current turn of the battle.
     */
    private void changeTurn() {
        if (turn == FIRSTTURN) {
            this.turn = SECONDTURN;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BattleModel getBattle() {
        return this.model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> initializeScores() {
        Pair<Integer, Integer> values = new Pair<>(this.model.getScore(1), this.model.getScore(2));
        return values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTurn() {
        return this.turn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canRoll() {
        return this.roll;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharacterModel getFirstCharacter() {
        return this.firstCharacter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharacterModel getSecondCharacter() {
        return this.secondCharacter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean requestedFate() {
        CharacterModel character = checkTurn();
        if (character.getFate() > 0 && this.countFate.get(getTurn() - 1) == 0) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFate() {
        if (this.countFate.get(this.turn - 1) == 0) {
            this.roll = true;
            CharacterModel character = checkTurn();
            character.setFate(character.getFate() - 1);
            this.countFate.set((this.turn - 1), this.countFate.get(this.turn - 1) - 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateRoll() {
        this.roll = false;
        this.model.diceRoll(this.turn);
        return this.model.getDiceRoll(this.turn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateScore() {
        this.model.setScore(this.turn, this.model.getScore(this.turn) + this.model.getDiceRoll(this.turn));
        return this.model.getScore(this.turn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int requestedAttack() {
        int score = updateScore();
        this.roll = true;
        if (this.turn == SECONDTURN) {
            this.model.compareScore();
        }
        changeTurn();
        return score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BattleState getResult() {
        if (this.model.isEnded()) {
            if (this.model.getState() == BattleState.FIRST) {
                this.secondCharacter.setHealth(this.secondCharacter.getHealth() - 1);
                checkDeath(this.secondCharacter);
            } else if (this.model.getState() == BattleState.SECOND) {
                this.firstCharacter.setHealth(this.firstCharacter.getHealth() - 1);
                checkDeath(this.firstCharacter);
            }
        }
        return this.model.getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkDeath(final CharacterModel character) {
        DeathController deathController = new DeathControllerImpl(character);
        return deathController.death();
    }

}
