package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.SerializationUtils;

import javafx.util.Pair;
import model.bonus.BonusCard;
import model.exceptions.GameOverException;
import model.player.Player;

import model.state.State;
import model.state.StateInfo;
import utils.CircularList;

/**
 * 
 * Class used to make the AI players take moves.
 *
 */
public final class AIManager implements AIManagerInterface {

    private CircularList<Player> players;
    private Map<String, State> statesMap;
    private Set<State> states;
    private Set<State> statesCopy;
    private Set<State> currentPlayerStates;
    private boolean initialized;
    private Optional<Map<Pair<State, State>, Double>> possibleAttacks;
    private static final int MIN_EXTERNAL_TANKS = 3;
    private static final double ATTACK_RATIO = 2.75;
    private static final int MINIMUM_TANK_NUM = 1;
    private static final int MAXIMUM_TANK_NUM = 3;
    private static final AIManager SINGLETON = new AIManager();

    private AIManager() {
    }

    /**
     * 
     * @return The instance of the AIManager.
     * 
     */
    public static AIManager getInstance() {
        return SINGLETON;
    }

    /**
     * Method to initialize the AttackManager, must be called before using other
     * methods.
     * 
     * @param players
     *            The list of the players of the game.
     * @param states
     *            The list of all the states in this game.
     */
    public void init(final CircularList<Player> players, final Set<State> states) {
        if (this.initialized) {
            throw new IllegalStateException("AIManager yet initialized.");
        }
        this.players = players;
        this.states = states;
        this.statesMap = new HashMap<>();
        this.statesCopy = new HashSet<>();
        for (final State s : this.states) {
            this.statesCopy.add(SerializationUtils.clone(s));
        }
        this.currentPlayerStates = new HashSet<>();
        this.initialized = true;
        this.possibleAttacks = Optional.empty();
    }

    /**
     * Method that deploy the tanks for AI players.
     * 
     * @throws GameOverException
     *             if the player has won the game.
     * 
     * @return The combination of State-Amount of tanks that player made.
     */
    public List<StateInfo> deployTanks() throws GameOverException {
        if (!this.initialized) {
            throw new IllegalStateException("AIManager not initialized yet.");
        }
        updateGameboard();
        if (this.players.getHead().getBonusCardsList().size() >= 3) {
            tryTradingCombo();
        }
        final Map<StateInfo, Integer> choice = new HashMap<>();
        final List<StateInfo> changedStates = new ArrayList<>();
        for (int i = 0; i < this.players.getHead().getTanksToDeploy(); i++) {
            State designedState = null;
            for (final State s : this.currentPlayerStates) {
                if (!isInnerState(s)) {
                    if (s.getTanks() == 1) {
                        designedState = s;
                        break;
                    }
                    if (designedState == null) {
                        designedState = s;
                    } else if (designedState.getTanks() > s.getTanks()) {
                        designedState = s;
                    }
                }
            }
            if (choice.containsKey(statesMap.get(designedState.getName()))) {
                choice.put(statesMap.get(designedState.getName()), choice.get(statesMap.get(designedState.getName())) + 1);
                designedState.addTanks(1);
            } else {
                choice.put(statesMap.get(designedState.getName()), 1);
                designedState.addTanks(1);
                changedStates.add(statesMap.get(designedState.getName()));
            }
        }
        try {
            DeployManager.getInstance().assignmentCheck(choice);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return changedStates;
    }

    /**
     * Method used for AI players to attack.
     * 
     * @throws GameOverException
     *             if the player has won the game.
     */
    public void attack() throws GameOverException {
        if (!this.initialized) {
            throw new IllegalStateException("AIManager not initialized yet.");
        }
        final Pair<State, State> currentBattle = this.possibleAttacks.get().keySet().stream().findFirst().get();
        this.possibleAttacks.get().remove(currentBattle);
        AttackManager.getInstance().attackStateCheck(currentBattle.getKey(), currentBattle.getValue(), getMaximumAttackingTanks(currentBattle.getKey()));
        AttackManager.getInstance().evaluateResults();
        if (AttackManager.getInstance().hasConquered()) {
            this.evaluateTankReplacement(currentBattle);
        }
    }

    /**
     * Method used for AI players to move tanks.
     * 
     * @throws GameOverException
     *             if the player has won the game.
     * 
     * @return True if a movement is done.
     */
    public boolean moveTanks() throws GameOverException {
        if (!this.initialized) {
            throw new IllegalStateException("AIManager not initialized yet.");
        }
        Optional<State> sourceState = Optional.empty();
        Optional<State> destinationState = Optional.empty();
        List<State> candidateStates = new ArrayList<>();
        int tanksToMove = 0;

        // Gets the internal States of the current player
        candidateStates = this.players.getHead().getStates().stream()
                .filter(state -> this.isInnerState(state) && state.getTanks() > 1).collect(Collectors.toList());
        if (candidateStates.isEmpty()) {
            // Gets the external States of the current player if there aren't
            // any internal States with at least 2 tanks.
            candidateStates = this.players.getHead().getStates().stream().filter(state -> !this.isInnerState(state))
                    .collect(Collectors.toList());
        }
        // Gets the State with the major number of tanks inside.
        sourceState = candidateStates.stream().max((s1, s2) -> Integer.compare(s1.getTanks(), s2.getTanks()));
        if (sourceState.isPresent()) {
            // Gets the non-internal neighboring State with the minor number of
            // tanks inside.
            destinationState = sourceState.get().getNeighbouringStates().stream()
                    .filter(state -> !this.isInnerState(state) && this.players.getHead().getStates().contains(state))
                    .min((s1, s2) -> Integer.compare(s1.getTanks(), s2.getTanks()));
            if (destinationState.isPresent()) {
                if (this.isInnerState(sourceState.get())) {
                    switch (sourceState.get().getTanks()) {
                    case 3:
                        tanksToMove = 2;
                        break;
                    case 2:
                        tanksToMove = 1;
                        break;
                    default:
                        tanksToMove = sourceState.get().getTanks() - 1;
                        break;
                    }
                } else if (!this.isInnerState(sourceState.get()) && sourceState.get().getTanks() > 3) {
                    tanksToMove = sourceState.get().getTanks() - MIN_EXTERNAL_TANKS;
                }
                if (tanksToMove != 0) {
                    DeployManager.getInstance().movementStateCheck(sourceState.get(), destinationState.get(),
                            tanksToMove);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean createBattleMap() {
        final Map<Pair<State, State>, Double> tempMap = new HashMap<>();
        getAvailableStates()
                .forEach(s -> s.getNeighbouringStates().stream().filter(bs -> bs.getPlayer() != this.players.getHead())
                        .forEach(bs -> tempMap.put(new Pair<State, State>(s, bs), (double) (getRatio(s, bs)))));
        this.possibleAttacks = Optional.of(selectAllAttacks(tempMap));
        return this.possibleAttacks.get().isEmpty();
    }

    /**
     * Resets the manager to his initial state.
     */
    public void reset() {
        this.players = null;
        this.statesMap = null;
        this.statesCopy = null;
        this.currentPlayerStates = null;
        this.initialized = false;
    }

    private boolean isInnerState(final State state) {
        boolean isInner = true;
        for (final State s : state.getNeighbouringStates()) {
            if (!s.getPlayer().equals(state.getPlayer())) {
                isInner = false;
                break;
            }
        }
        return isInner;
    }

    private void updateGameboard() {
        this.statesMap.clear();
        this.statesCopy.clear();
        this.states.forEach(state -> {
            this.statesMap.put(state.getName(), state);
            this.statesCopy.add(SerializationUtils.clone(state));
        });
        this.currentPlayerStates.clear();
        for (final State s : this.statesCopy) {
            if (s.getPlayer().getName().equals(this.players.getHead().getName())) {
                this.currentPlayerStates.add(s);
            }
        }
    }

    private List<State> getAvailableStates() {
        final List<State> available = new LinkedList<>(this.players.getHead().getStates());
        final List<State> notAvailable = new LinkedList<>(available.stream().filter(s -> isInnerState(s) || !containsMoreTanks(s)).collect(Collectors.toList()));
        notAvailable.forEach(s->available.remove(s));
        return available;
    }

    private boolean containsMoreTanks(final State s) {
        return s.getTanks() > 1 ? true : false;
    }

    private Map<Pair<State, State>, Double> selectAllAttacks(final Map<Pair<State, State>, Double> possibleAttacks) {
        final List<Pair<State, State>> tmpList = new ArrayList<>();
        possibleAttacks.keySet().stream().filter(e -> possibleAttacks.get(e) < ATTACK_RATIO).forEach(e->tmpList.add(e));
        tmpList.forEach(p -> possibleAttacks.remove(p));
        return possibleAttacks;
    }

    private double getRatio(final State attacking, final State defending) {
        return (attacking.availableTanks() / defending.getTanks());
    }

    private void evaluateTankReplacement(final Pair<State, State> selectedKey) {
        int toMove = 0;
        if (isInnerState(selectedKey.getKey()) && selectedKey.getKey().availableTanks() > 0) {
            AttackManager.getInstance().moveTanks(selectedKey.getKey().availableTanks());
        } else {
            if (selectedKey.getKey().availableTanks() > MAXIMUM_TANK_NUM) {
                toMove = fairlyDivide(selectedKey.getKey().getTanks());
                if (toMove != 0) {
                    AttackManager.getInstance().moveTanks(toMove);
                }
            }
        }
    }

    private int getMaximumAttackingTanks(final State attacking) {
        return attacking.availableTanks() < MAXIMUM_TANK_NUM ? attacking.availableTanks() : MAXIMUM_TANK_NUM;
    }

    private int fairlyDivide(final int num) {
        if (num < MINIMUM_TANK_NUM) {
            throw new IllegalArgumentException("MAXIMUM_TANK_NUM is a lower bound for num");
        }
        return ((num - MAXIMUM_TANK_NUM) / 2);
    }

    @Override
    public void tryTradingCombo() {
        final List<BonusCard> combo = BonusCardManager.getInstance().getBestCombo(this.players.getHead());
        if (!combo.isEmpty()) {
            BonusCardManager.getInstance().tradeCombo(this.players.getHead(), combo);
        }
    }

}
