package tests.model;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import javafx.util.Pair;
import model.AIManager;
import model.AimManager;
import model.AttackManager;
import model.BonusCardManager;
import model.aim.StateAim;

import model.player.Player;
import model.player.PlayerImpl;
import model.player.PlayerInfo;
import model.region.Region;
import model.region.RegionImpl;
import model.state.State;
import model.state.StateImpl;
import model.state.StateInfo;
import utils.CircularList;
import utils.enumerations.Color;
import utils.enumerations.ControlType;
import utils.enumerations.MapType;
import view.View;

import utils.CircularListImpl;
import utils.HistoryLog;

/**
 * 
 * Test class for those methods of class AIManager modeling the attack logic.
 *
 */
public class TestAIAttack {

    private static final int STATES_TO_CONQUER = 24;
    private static final int LOWEST_TANK_NUM = 1;
    private static final int HIGHEST_TANK_NUM = 22;
    private final Region north = new RegionImpl("North", 0);
    private final Region south = new RegionImpl("South", 0);
    private final Region center = new RegionImpl("Center", 0);
    private final Region east = new RegionImpl("East", 0);
    private final State state0 = new StateImpl("State0", center);
    private final State state1 = new StateImpl("State1", center);
    private final State state2 = new StateImpl("State2", center);
    private final State state3 = new StateImpl("State3", center);
    private final State state4 = new StateImpl("State4", east);
    private final State state5 = new StateImpl("State5", east);
    private final State state6 = new StateImpl("State6", south);
    private final State state7 = new StateImpl("State7", south);
    private final State state8 = new StateImpl("State8", south);
    private final State state9 = new StateImpl("State9", north);
    private final State state10 = new StateImpl("State10", north);

    private final List<State> states = new ArrayList<>(
            Arrays.asList(state0, state1, state2, state3, state4, state5, state6, state7, state8, state9, state10));
    private final Player tay = new PlayerImpl("Taylor Swift", Color.RED, ControlType.AI, 100);
    private final Player gg = new PlayerImpl("GossipGirl", Color.YELLOW, ControlType.HUMAN, 100);

    private void basicInit() {
        Arrays.asList(state0, state1, state2, state3, state4, state6).forEach(s -> tay.addState(s));
        Arrays.asList(state5, state7, state8, state9, state10).forEach(s -> gg.addState(s));

        Arrays.asList(state0, state1, state2, state3).forEach(s -> center.addAssociatedState(s));
        Arrays.asList(state4, state5).forEach(s -> east.addAssociatedState(s));
        Arrays.asList(state9, state10).forEach(s -> north.addAssociatedState(s));
        Arrays.asList(state6, state7, state8).forEach(s -> south.addAssociatedState(s));

        state0.addNeighbouringState(Arrays.asList(state1, state2, state3));
        state1.addNeighbouringState(Arrays.asList(state0, state3, state2, state10, state5, state7));
        state2.addNeighbouringState(Arrays.asList(state0, state1, state3, state9, state8, state7));
        state3.addNeighbouringState(Arrays.asList(state0, state1, state2, state9, state10));
        state4.addNeighbouringState(Arrays.asList(state10, state5));
        state5.addNeighbouringState(Arrays.asList(state1, state6, state4));
        state6.addNeighbouringState(Arrays.asList(state7, state5));
        state7.addNeighbouringState(Arrays.asList(state1, state2, state6, state8));
        state8.addNeighbouringState(Arrays.asList(state7, state2, state9));
        state9.addNeighbouringState(Arrays.asList(state8, state2, state3, state10));
        state10.addNeighbouringState(Arrays.asList(state1, state9, state3, state4));

        final CircularList<Player> players = new CircularListImpl<>(Arrays.asList(tay, gg));
        tay.setAim(new StateAim(STATES_TO_CONQUER, tay));
        gg.setAim(new StateAim(STATES_TO_CONQUER, gg));

        AttackManager.getInstance().init(players);
        AimManager.getInstance().recovery(players);
        BonusCardManager.getInstance().init(this.states);
        AIManager.getInstance().init(players, new HashSet<>());
        HistoryLog.getLog().registerView(this.dummyView);
    }

    /**
     * Test those cases when the ai should not attack.
     */
    @Test
    public void testNoAttack() {

        final int numTank = 3;
        basicInit();

        this.tay.getStates().forEach(s -> s.addTanks(LOWEST_TANK_NUM));
        this.gg.getStates().forEach(s -> s.addTanks(HIGHEST_TANK_NUM));

        // external states of the current player controlled by ai don't contain
        // enough tanks to attack
        assertTrue(AIManager.getInstance().createBattleMap());

        // state0 contains enough tanks to attack but it's surrounded by
        // friendly states
        this.state0.addTanks(HIGHEST_TANK_NUM);
        assertTrue(AIManager.getInstance().createBattleMap());

        // tay's state have enough tanks to attack but their number is too low
        // if compared to the tanks amount on enemy's States,
        // the ai prefers to not attack
        this.tay.getStates().forEach(s -> s.addTanks(numTank));
        assertTrue(AIManager.getInstance().createBattleMap());

        AttackManager.getInstance().clear();
        AimManager.getInstance().resetInitCalled();
        BonusCardManager.getInstance().resetInitCalled();
        AIManager.getInstance().reset();
        this.states.forEach(s -> s.destroyTanks(s.getTanks()));
    }

    /**
     * Test those cases when the ai should attack.
     */
    @Test
    public void testAttack() {
        final int numTank = 3;
        basicInit();
        this.states.forEach(s -> s.addTanks(LOWEST_TANK_NUM));
        this.state6.addTanks(HIGHEST_TANK_NUM);
        Arrays.asList(state7, state8, state9, state10).forEach(s -> s.addTanks(HIGHEST_TANK_NUM));

        assertFalse(AIManager.getInstance().createBattleMap());
        AIManager.getInstance().createBattleMap();
        AIManager.getInstance().attack();
        assertFalse(this.state6.getTanks() == (HIGHEST_TANK_NUM + LOWEST_TANK_NUM)
                && this.state5.getTanks() == LOWEST_TANK_NUM);
        if (this.state5.getPlayer().equals(this.tay)) {
            assertTrue(AIManager.getInstance().createBattleMap());
            this.state5.setPlayer(gg);
            this.gg.addState(this.state5);
            this.tay.removeState(this.state5);
            if (this.tay.getRegions().contains(this.state5.getRegion())) {
                this.tay.removeRegion(this.state5.getRegion());
            }

        }

        this.states.forEach(
                s -> System.out.printf(s.getName() + " " + s.getTanks() + " " + s.getPlayer().getName() + "\n"));
        System.out.println("--------------------------");

        Arrays.asList(state5, state7, state6).forEach(s -> s.destroyTanks(s.getTanks()));
        Arrays.asList(state5, state7).forEach(s -> s.addTanks(numTank));

        this.state6.addTanks(HIGHEST_TANK_NUM);

        this.states.forEach(
                s -> System.out.printf(s.getName() + " " + s.getTanks() + " " + s.getPlayer().getName() + "\n"));

        // try doing multiple attack
        assertFalse(AIManager.getInstance().createBattleMap());
        AIManager.getInstance().createBattleMap();
        AIManager.getInstance().attack();
        assertFalse(this.state6.getTanks() == (HIGHEST_TANK_NUM) && this.state5.getTanks() == numTank
                && this.state7.getTanks() == numTank);

        this.state6.destroyTanks(this.state6.getTanks());
        this.state6.addTanks(HIGHEST_TANK_NUM);

        System.out.println("--------------------------");
        this.states.forEach(
                s -> System.out.printf(s.getName() + " " + s.getTanks() + " " + s.getPlayer().getName() + "\n"));

        assertFalse(AIManager.getInstance().createBattleMap());
        AIManager.getInstance().createBattleMap();
        AIManager.getInstance().attack();
        assertFalse(this.state6.getTanks() == (HIGHEST_TANK_NUM) && this.state5.getTanks() == numTank
                && this.state7.getTanks() == numTank);

        AttackManager.getInstance().clear();
        AimManager.getInstance().resetInitCalled();
        BonusCardManager.getInstance().resetInitCalled();
        AIManager.getInstance().reset();
        this.states.forEach(s -> s.destroyTanks(s.getTanks()));

    }

    private final View dummyView = new View() {

        @Override
        public void startView() { }

        @Override
        public void setGameList(final CircularList<? extends PlayerInfo> circularList, final Set<? extends StateInfo> set, final Optional<MapType> map) { }

        @Override
        public void updateStates() { }

        @Override
        public void updateInfoPlayer() { }

        @Override
        public void deployTanks() { }

        @Override
        public void revertAction() {
            System.out.println("Exception catched from TanksAnalyst.");
        }

        @Override
        public void updateStates(final List<StateInfo> assignment) { }

        @Override
        public void updateDice(final List<Pair<Integer, Integer>> diceValue) { }

        @Override
        public void printError(final String error) {
            System.out.println(error);
        }

        @Override
        public void showVictory() { }

        @Override
        public void deployPhaseFinished() { }

        @Override
        public void aIAttackPhaseFinished() { }

        @Override
        public void updateLog(final String msg) {
            System.out.println(msg);
        }

        @Override
        public void drawMap() { }

        @Override
        public void disableAllComponents(final boolean check) { }

        @Override
        public void showMovementDialog() { }

    };
}
