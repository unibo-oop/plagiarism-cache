package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.util.Pair;
import model.exceptions.GameOverException;
import model.player.Player;
import model.state.State;
import utils.CircularList;
import utils.HistoryLog;

/**
 * 
 * Class that manages the attack between two States. It's a thread-safe Singleton class.
 *
 */
public final class AttackManager implements AttackManagerInterface {

    private static final String NOT_INITIALIZED_ERROR_MESSAGE = "AttackManager not initialized.";
    private static final int DEFAULT_DEFENDING_TANKS = 3;
    private static final int MAX_NUMBER_OF_ROLLS = 3;
    private static final int DICE_FACES = 6;
    private State defender;
    private State attacker;
    private Player attackingPlayer;
    private Player defendingPlayer;
    private int attackerTanks;
    private int defenderTanks;
    private boolean conquered;
    private boolean initialized;
    private boolean getCard;
    private final List<Pair<Integer, Integer>> lastDiceRoll;
    private CircularList<Player> players;
    private static final AttackManager SINGLETON = new AttackManager();

    private AttackManager() {
        this.lastDiceRoll = new ArrayList<>();
        this.conquered = false;
        this.initialized = false;
        this.getCard = false;
    }

    /**
     * 
     * @return 
     *          The instance of the AttackManager.
     * 
     */
    public static AttackManager getInstance() {
        return SINGLETON;
    }

    /**
     * Method to initialize the AttackManager, must be called before using other methods.
     * 
     * @param players
     *          The list of the players.
     */
    public void init(final CircularList<Player> players) {
        if (!this.initialized) {
            this.players = players;
            this.initialized = true;
        }
    }

    @Override
    public void attackStateCheck(final State attacker, final State defender, final int nTanks)
            throws IllegalArgumentException {
        if (!this.initialized) {
            throw new IllegalStateException(NOT_INITIALIZED_ERROR_MESSAGE);
        }
        if  (!attacker.getPlayer().equals(getCurrentPlayer()) 
            || !attacker.getNeighbouringStates().contains(defender)
            || defender.getPlayer().equals(getCurrentPlayer())
            || (attacker.availableTanks() < nTanks)
            || (nTanks > 3 || nTanks < 1)
            || attacker.availableTanks() == 0) {
                throw new IllegalArgumentException("Cannot attack from " + attacker.getName() + " to " + defender.getName() + ".");
        } else {
            reset();
            this.attackingPlayer = attacker.getPlayer();
            this.defendingPlayer = defender.getPlayer();
            this.attacker = attacker;
            this.defender = defender;
            this.attackerTanks = nTanks;
            this.defenderTanks = getMaximumDefendingTanks();
        }
    }

    @Override
    public void evaluateResults() throws GameOverException {
        if (!this.initialized) {
            throw new IllegalStateException(NOT_INITIALIZED_ERROR_MESSAGE);
        }
        if (this.defender == null || this.attacker == null) {
            throw new IllegalArgumentException("States not declared.");
        }
        HistoryLog.getLog().send(this.attacker.getName() + "(" + getCurrentPlayer().getName() + ") attacks "
                          + this.defender.getName() + "(" + this.defendingPlayer.getName() + ")");
        final List<Integer> attackerDice = new ArrayList<>(), defenderDice = new ArrayList<>();
        for (int i = 0; i < MAX_NUMBER_OF_ROLLS; i++) {
            if (i < attackerTanks) {
                attackerDice.add(rollDice());
            } else {
                attackerDice.add(0);
            }
            if (i < defenderTanks) {
                defenderDice.add(rollDice());
            } else {
                defenderDice.add(0);
            }
        }
        Collections.sort(attackerDice);
        Collections.sort(defenderDice);
        for (int i = 0; i < MAX_NUMBER_OF_ROLLS; i++) {
            this.lastDiceRoll.add(new Pair<Integer, Integer>(attackerDice.get(i), defenderDice.get(i)));
        }
        diceComparison();
        if (this.defender.getTanks() == 0) {
            conquerState();
            HistoryLog.getLog().send(this.attackingPlayer.getName() + " conquered " + this.defender.getName());
        }
    }

    @Override
    public List<Pair<Integer, Integer>> getDice() {
        if (!this.initialized) {
            throw new IllegalStateException(NOT_INITIALIZED_ERROR_MESSAGE);
        }
        return this.lastDiceRoll;
    }

    @Override
    public boolean hasConquered() {
        if (!this.initialized) {
            throw new IllegalStateException(NOT_INITIALIZED_ERROR_MESSAGE);
        }
        return this.conquered;
    }

    @Override
    public void moveTanks(final int nTanks) throws IllegalArgumentException {
        if (!this.initialized) {
            throw new IllegalStateException(NOT_INITIALIZED_ERROR_MESSAGE);
        }
        if (nTanks >= attacker.getTanks()) {
            throw new IllegalArgumentException("Not enough tanks.");
        } else {
            this.attacker.moveTanks(this.defender, nTanks);
        }
        HistoryLog.getLog().send(getCurrentPlayer().getName() + " moved after the attack " + nTanks 
                          + " tanks from " + this.attacker.getName() + " to " + this.defender.getName());
    }

    /**
     * Method used to reset the Singleton when exit a game.
     */
    public void clear() {
        reset();
        this.attackingPlayer = null;
        this.getCard = false;
        this.initialized = false;
        this.players = null;
    }

    private int rollDice() {
        return (int) (DICE_FACES * Math.random()) + 1;
    }

    private int getMaximumDefendingTanks() {
        if (this.defender.getTanks() >= DEFAULT_DEFENDING_TANKS) {
            return DEFAULT_DEFENDING_TANKS;
        }
        return this.defender.getTanks();
    }

    private void diceComparison() {
        for (final Pair<Integer, Integer> p : this.lastDiceRoll) {
            if (p.getKey() <= p.getValue() && p.getKey() != 0 && p.getValue() != 0) {
                this.attacker.destroyTanks(1);
            } else if (p.getKey() > p.getValue() && p.getKey() != 0 && p.getValue() != 0) {
                this.defender.destroyTanks(1);
            }
        }
    }

    private void conquerState() throws GameOverException {
        if (this.defender.getPlayer().getRegions().contains(this.defender.getRegion())) {
            this.defender.getPlayer().removeRegion(this.defender.getRegion());
        }
        this.defender.getPlayer().removeState(this.defender);
        this.attackingPlayer.addState(this.defender);
        this.attacker.moveTanks(this.defender, this.attackerTanks);
        if (this.defender.getRegion().checkOwner(getCurrentPlayer())) {
            getCurrentPlayer().addRegion(this.defender.getRegion());
        }
        this.conquered = true;
        if (!this.getCard) {
            BonusCardManager.getInstance().addRandomBonusCardTo(getCurrentPlayer());
            this.getCard = true;
        }
        if (this.defendingPlayer.getStates().isEmpty()) {
            playerConquered();
        }
        if (AimManager.getInstance().checkWin(this.defendingPlayer)) {
            throw new GameOverException(getCurrentPlayer().getName());
        }
    }

    private void reset() {
        this.attackingPlayer = null;
        this.conquered = false;
        this.lastDiceRoll.clear();
        this.attacker = null;
        this.defender = null;
        this.defenderTanks = 0;
        this.attackerTanks = 0;
        this.defendingPlayer = null;
    }

    private void playerConquered() {
        this.players.remove(this.defendingPlayer);
        BonusCardManager.getInstance().acquireBonusCard(this.attackingPlayer, this.defendingPlayer);
        HistoryLog.getLog().send(this.attackingPlayer.getName() + " eliminated " + this.defendingPlayer.getName());
    }

    private Player getCurrentPlayer() {
        return this.players.getHead();
    }

    @Override
    public void turnShifted() {
        this.getCard = false;
    }

}