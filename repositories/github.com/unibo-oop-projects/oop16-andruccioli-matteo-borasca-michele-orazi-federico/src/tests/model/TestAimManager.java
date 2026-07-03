package tests.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.Test;

import javafx.util.Pair;
import model.AimManager;
import model.aim.DefinedConquerAim;
import model.aim.DestroyAim;
import model.aim.DoubleGarrisonAim;
import model.aim.StateAim;
import model.aim.UndefinedConquerAim;
import model.player.Player;
import model.player.PlayerImpl;
import model.player.PlayerInfo;
import model.region.Region;
import model.region.RegionImpl;
import model.state.State;
import model.state.StateImpl;
import model.state.StateInfo;
import utils.CircularList;
import utils.CircularListImpl;
import utils.HistoryLog;
import utils.enumerations.Color;
import utils.enumerations.ControlType;
import utils.enumerations.MapType;
import view.View;

/**
 * Test for class AimManager.
 */
public class TestAimManager {

    private static final int REQUIRED_TANKS = 2;
    private static final int DEFAULT_STATES = 3;
    private static final int DEFAULT_STATES_GARRISON = 2;
    private static final String WRONG_EX = "wrong exception thrown";
    private final Region reg0 = new RegionImpl("TestRegion0", 0);
    private final Region reg1 = new RegionImpl("TestRegion1", 0);
    private final Region reg2 = new RegionImpl("TestRegion2", 0);
    private final Region reg3 = new RegionImpl("TestRegion3", 0);

    private final Set<Region> definedSet0 = new HashSet<>();
    private final Set<Region> definedSet1 = new HashSet<>();
    private final Set<Region> definedSet2 = new HashSet<>();
    private final Set<Region> definedSet3 = new HashSet<>();
    private final Set<Region> undefinedSet0 = new HashSet<>();
    private final Set<Region> undefinedSet1 = new HashSet<>();

    private final Player p0 = new PlayerImpl("p0", Color.BLACK, ControlType.HUMAN, 1);
    private final Player p1 = new PlayerImpl("p1", Color.RED, ControlType.HUMAN, 1);
    private final Player p2 = new PlayerImpl("p2", Color.BLUE, ControlType.HUMAN, 1);
    private final Player p3 = new PlayerImpl("p3", Color.GREEN, ControlType.HUMAN, 1);
    private final Player p4 = new PlayerImpl("p4", Color.PURPLE, ControlType.HUMAN, 1);
    private final Player p5 = new PlayerImpl("p4", Color.YELLOW, ControlType.HUMAN, 1);

    private boolean present;

    private final List<Player> pList = new LinkedList<>(Arrays.asList(p0, p1, p2, p3, p4));

    /**
     * Test the correctness of AimManager initialization.
     */
    @Test
    public void testInitialization() {
        definedSet0.addAll(Arrays.asList(reg0, reg1));
        definedSet1.addAll(Arrays.asList(reg0, reg2));
        definedSet2.addAll(Arrays.asList(reg0, reg3));
        definedSet3.addAll(Arrays.asList(reg1, reg2));
        undefinedSet0.addAll(Arrays.asList(reg1, reg3));
        undefinedSet1.addAll(Arrays.asList(reg2, reg3));

        final List<Set<Region>> definedAvailableRegion = new LinkedList<>(
                Arrays.asList(definedSet0, definedSet1, definedSet2, definedSet3));
        final List<Set<Region>> undefinedAvailableRegion = new LinkedList<>(
                Arrays.asList(undefinedSet0, undefinedSet1));

        final CircularList<Player> cPList = new CircularListImpl<>(pList);
        HistoryLog.getLog().registerView(this.dummyView);
        AimManager.getInstance().init(definedAvailableRegion, undefinedAvailableRegion, DEFAULT_STATES,
                DEFAULT_STATES_GARRISON, cPList);
        cPList.toStream().forEach(p -> assertNotNull(p.getAim()));
        cPList.toStream().forEach(p -> {
            if (p.getAim() instanceof DestroyAim) {
                present = false;
                if (((DestroyAim) p.getAim()).getEnemy() == p.getColor()) {
                    assertTrue(((DestroyAim) p.getAim()).isSecondAimEnabled());
                } else {
                    cPList.toStream().forEach(p1 -> {
                        if (((DestroyAim) p.getAim()).getEnemy() == p1.getColor()) {
                            this.present = true;
                        }
                    });
                    if (!this.present) {
                        assertTrue(((DestroyAim) p.getAim()).isSecondAimEnabled());
                    } else {
                        assertFalse(((DestroyAim) p.getAim()).isSecondAimEnabled());
                    }
                }
            }
        });

    }

    /**
     * Test the correctness of exceptions related to AimManager initialization.
     */
    @Test
    public void testInitializationExceptions() {
        definedSet0.addAll(Arrays.asList(reg0, reg1));
        definedSet1.addAll(Arrays.asList(reg0, reg2));
        definedSet2.addAll(Arrays.asList(reg0, reg3));
        definedSet3.addAll(Arrays.asList(reg1, reg2));
        undefinedSet0.addAll(Arrays.asList(reg1, reg3));
        undefinedSet1.addAll(Arrays.asList(reg2, reg3));

        final List<Set<Region>> definedAvailableRegion = new LinkedList<>(
                Arrays.asList(definedSet0, definedSet1, definedSet2, definedSet3));
        final List<Set<Region>> undefinedAvailableRegion = new LinkedList<>(
                Arrays.asList(undefinedSet0, undefinedSet1));
        final CircularList<Player> cPList = new CircularListImpl<>(pList);
        HistoryLog.getLog().registerView(this.dummyView);
        try {
            AimManager.getInstance().init(null, undefinedAvailableRegion, DEFAULT_STATES, DEFAULT_STATES_GARRISON,
                    cPList);
            fail("call init passing a null definedAvailableRegion should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

        try {
            AimManager.getInstance().init(new LinkedList<>(), undefinedAvailableRegion, DEFAULT_STATES,
                    DEFAULT_STATES_GARRISON, cPList);
            fail("call init passing an empty definedAvailableRegion should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

        try {
            AimManager.getInstance().init(definedAvailableRegion, null, DEFAULT_STATES, DEFAULT_STATES_GARRISON,
                    cPList);
            fail("call init passing a null undefinedAvailableRegion should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

        try {
            AimManager.getInstance().init(definedAvailableRegion, new LinkedList<>(), DEFAULT_STATES,
                    DEFAULT_STATES_GARRISON, cPList);
            fail("call init passing an empty undefinedAvailableRegion should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

        try {
            AimManager.getInstance().init(definedAvailableRegion, undefinedAvailableRegion, DEFAULT_STATES,
                    DEFAULT_STATES_GARRISON, null);
            fail("call init passing a null cPList should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

        try {
            AimManager.getInstance().init(definedAvailableRegion, undefinedAvailableRegion, DEFAULT_STATES,
                    DEFAULT_STATES_GARRISON, new CircularListImpl<>(new LinkedList<>()));
            fail("call init passing an empty cPList should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

        try {
            AimManager.getInstance().init(definedAvailableRegion, undefinedAvailableRegion, 0, DEFAULT_STATES_GARRISON,
                    cPList);
            fail("call init passing defaultNumStates=0 should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

        try {
            AimManager.getInstance().init(definedAvailableRegion, undefinedAvailableRegion, DEFAULT_STATES, 0, cPList);
            fail("call init passing doubleGarrisonNumStates=0 should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

    }

    /**
     * Test the correctness of AimManager recovery.
     */
    @Test
    public void testRecovery() {
        final CircularList<Player> cPList = new CircularListImpl<>(pList);
        HistoryLog.getLog().registerView(this.dummyView);
        try {
            AimManager.getInstance().recovery(null);
            fail("call recovery passing null list should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

        try {
            AimManager.getInstance().recovery(new CircularListImpl<>(new LinkedList<>()));
            fail("call recovery passing an empty list should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

        try {
            AimManager.getInstance().recovery(cPList);
            fail("call recovery passing a circular list containing players who haven't still received an aim should raise an exception!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

        cPList.toStream().forEach(p -> p.setAim(new StateAim(DEFAULT_STATES, p)));
        AimManager.getInstance().recovery(cPList);
        assertTrue(AimManager.getInstance().isInitCalled());
        AimManager.getInstance().resetInitCalled();
    }

    /**
     * Test the correctness of AimManager checkWin().
     */
    @Test
    public void testCheckWin() {
        final CircularList<Player> cPList = new CircularListImpl<>(pList);
        final State state0 = new StateImpl("State0", this.reg0);
        final State state1 = new StateImpl("State1", this.reg0);
        HistoryLog.getLog().registerView(this.dummyView);
        try {
            AimManager.getInstance().checkWin();
            fail("call checkWin() without having initialized the istance of AimManager should raise an exception!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("wrong exception thrown");
        }
        cPList.toStream().forEach(p -> p.setAim(new DoubleGarrisonAim(DEFAULT_STATES_GARRISON, p)));
        AimManager.getInstance().recovery(cPList);
        assertFalse(AimManager.getInstance().checkWin());
        cPList.getHead().addState(state0);
        cPList.getHead().addState(state1);
        assertFalse(AimManager.getInstance().checkWin());
        state0.addTanks(REQUIRED_TANKS);
        state1.addTanks(REQUIRED_TANKS);
        assertTrue(AimManager.getInstance().checkWin());
        AimManager.getInstance().resetInitCalled();
    }

    /**
     * Test the correctness of AimManager checkWin(final Player p) .
     */
    @Test
    public void testCheckWinWithPlayer() {

        final State state0 = new StateImpl("State0", this.reg0);
        final State state1 = new StateImpl("State1", this.reg0);
        final State state2 = new StateImpl("State2", this.reg0);

        HistoryLog.getLog().registerView(this.dummyView);
        definedSet0.addAll(Arrays.asList(reg0, reg1));
        definedSet1.addAll(Arrays.asList(reg0, reg2));
        definedSet2.addAll(Arrays.asList(reg0, reg3));
        definedSet3.addAll(Arrays.asList(reg1, reg2));
        undefinedSet0.addAll(Arrays.asList(reg1, reg3));
        undefinedSet1.addAll(Arrays.asList(reg2, reg3));

        final Set<Region> defined = new HashSet<>();
        final Set<Region> undefined = new HashSet<>();
        defined.addAll(Arrays.asList(this.reg0, this.reg1));
        undefined.addAll(Arrays.asList(this.reg1, this.reg2));

        pList.add(p5);
        final CircularList<Player> cPList = new CircularListImpl<>(pList);

        try {
            AimManager.getInstance().checkWin(null);
            fail("call checkWin(Player p) passing a null p should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

        try {
            AimManager.getInstance().checkWin(p5);
            fail("call checkWin() without having initialized the istance of AimManager should raise an exception!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(WRONG_EX);
        }

        this.p0.setAim(new StateAim(DEFAULT_STATES, this.p0));
        this.p1.setAim(new DoubleGarrisonAim(DEFAULT_STATES_GARRISON, this.p1));
        this.p2.setAim(new DefinedConquerAim(defined, this.p2));
        this.p3.setAim(new UndefinedConquerAim(undefined, this.p3));
        this.p4.setAim(new DestroyAim(DEFAULT_STATES, this.p4, Color.YELLOW));
        this.p5.setAim(new DestroyAim(DEFAULT_STATES, this.p5, Color.PURPLE));

        AimManager.getInstance().recovery(cPList);

        assertFalse(AimManager.getInstance().checkWin(this.p4));
        assertTrue(((DestroyAim) this.p5.getAim()).isSecondAimEnabled());
        this.p0.addState(state0);
        this.p0.addState(state1);
        this.p0.addState(state2);
        assertTrue(AimManager.getInstance().checkWin(this.p4));

        cPList.shift();
        assertFalse(AimManager.getInstance().checkWin(this.p4));
        this.p1.addState(state0);
        this.p1.addState(state1);
        state0.addTanks(REQUIRED_TANKS);
        state1.addTanks(REQUIRED_TANKS);
        assertTrue(AimManager.getInstance().checkWin(this.p4));

        cPList.shift();
        assertFalse(AimManager.getInstance().checkWin(this.p4));
        this.p2.addRegion(reg0);
        this.p2.addRegion(reg1);
        assertTrue(AimManager.getInstance().checkWin(this.p4));

        cPList.shift();
        assertFalse(AimManager.getInstance().checkWin(this.p4));
        this.p3.addRegion(reg1);
        this.p3.addRegion(reg2);
        assertFalse(AimManager.getInstance().checkWin(this.p4));
        this.p3.addRegion(reg3);
        assertTrue(AimManager.getInstance().checkWin(this.p4));

        cPList.shift();
        assertFalse(((DestroyAim) p4.getAim()).isSecondAimEnabled());
        assertTrue(AimManager.getInstance().checkWin(this.p5));

        cPList.shift();
        assertFalse(AimManager.getInstance().checkWin(this.p1));
        this.p5.addState(state0);
        this.p5.addState(state1);
        this.p5.addState(state2);
        assertTrue(AimManager.getInstance().checkWin(this.p1));

        AimManager.getInstance().resetInitCalled();
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
