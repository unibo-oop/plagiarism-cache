package tests.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import model.aim.DefinedConquerAim;
import model.aim.DestroyAim;
import model.aim.DoubleGarrisonAim;
import model.aim.StateAim;
import model.aim.UndefinedConquerAim;
import model.player.Player;
import model.player.PlayerImpl;
import model.region.Region;
import model.region.RegionImpl;
import model.state.State;
import model.state.StateImpl;
import utils.enumerations.Color;
import utils.enumerations.ControlType;

/**
 * Test methods aimAchieved of each aimClass.
 */
public class TestAimAchievedMethods {

    private static final int DEFAULT_STATES = 5;
    private static final int DEFAULT_STATES_GARRISON = 4;
    private final Region reg0 = new RegionImpl("TestRegion0", 0);
    private final Region reg1 = new RegionImpl("TestRegion1", 0);
    private final Region reg2 = new RegionImpl("TestRegion2", 0);
    private final Region reg3 = new RegionImpl("TestRegion3", 0);

    private final State state0 = new StateImpl("State0", reg0);
    private final State state1 = new StateImpl("State1", reg0);
    private final State state2 = new StateImpl("State2", reg0);
    private final State state3 = new StateImpl("State3", reg0);
    private final State state4 = new StateImpl("State4", reg0);
    private final State state5 = new StateImpl("State5", reg0);
    private final Player tay = new PlayerImpl("Taylor Swift", Color.RED, ControlType.HUMAN, 1);

    /**
     * Test the correctness of method aimAchieved in UndefinedConquerAim.
     */
    @Test
    public void testUndefinedConquerAim() {
        final Set<Region> regions = new HashSet<>();
        regions.addAll(Arrays.asList(reg0, reg1));
        this.tay.setAim(new UndefinedConquerAim(regions, this.tay));
        assertFalse(this.tay.getAim().aimAchieved());
        this.tay.addRegion(reg0);
        this.tay.addRegion(reg2);
        this.tay.addRegion(reg3);
        assertFalse(this.tay.getAim().aimAchieved());
        this.tay.addRegion(reg1);
        assertTrue(this.tay.getAim().aimAchieved());
        this.tay.removeRegion(reg3);
        assertTrue(this.tay.getAim().aimAchieved());
        this.tay.removeRegion(reg2);
        assertFalse(this.tay.getAim().aimAchieved());
    }

    /**
     * Test the correctness of method aimAchieved in DefinedConquerAim.
     */
    @Test
    public void testDefinedConquerAim() {
        final Set<Region> regions = new HashSet<>();
        regions.addAll(Arrays.asList(reg0, reg1));
        this.tay.setAim(new DefinedConquerAim(regions, this.tay));
        assertFalse(this.tay.getAim().aimAchieved());
        this.tay.addRegion(reg0);
        this.tay.addRegion(reg2);
        this.tay.addRegion(reg3);
        assertFalse(this.tay.getAim().aimAchieved());
        this.tay.addRegion(reg1);
        assertTrue(this.tay.getAim().aimAchieved());
        this.tay.removeRegion(reg3);
        assertTrue(this.tay.getAim().aimAchieved());
        this.tay.removeRegion(reg2);
        assertTrue(this.tay.getAim().aimAchieved());
    }

    /**
     * Test the correctness of method aimAchieved in DoubleGarrisonAim.
     */
    @Test
    public void testDoubleGarrisonAim() {
        this.tay.setAim(new DoubleGarrisonAim(DEFAULT_STATES_GARRISON, this.tay));
        assertFalse(this.tay.getAim().aimAchieved());
        this.tay.addState(state0);
        this.tay.addState(state2);
        this.tay.addState(state3);
        assertFalse(this.tay.getAim().aimAchieved());
        Arrays.asList(state0, state2, state3).stream().forEach(s -> s.addTanks(2));
        assertFalse(this.tay.getAim().aimAchieved());
        this.tay.addState(state4);
        this.tay.addState(state5);
        Arrays.asList(state4, state5).stream().forEach(s -> s.addTanks(1));
        assertFalse(this.tay.getAim().aimAchieved());
        Arrays.asList(state4, state5).stream().forEach(s -> s.addTanks(1));
        assertTrue(this.tay.getAim().aimAchieved());
        this.tay.removeState(state0);
        assertTrue(this.tay.getAim().aimAchieved());
    }

    /**
     * Test the correctness of method aimAchieved in StateAim.
     */
    @Test
    public void testStateAim() {
        this.tay.setAim(new StateAim(DEFAULT_STATES, this.tay));
        assertFalse(this.tay.getAim().aimAchieved());
        this.tay.addState(state0);
        this.tay.addState(state1);
        this.tay.addState(state2);
        this.tay.addState(state3);
        assertFalse(this.tay.getAim().aimAchieved());
        this.tay.addState(state4);
        assertTrue(this.tay.getAim().aimAchieved());
    }

    /**
     * Test the correctness of method aimAchieved in DestroyAim.
     */
    @Test
    public void testDestroyAim() {
        final Player gg = new PlayerImpl("Gossip Girl", Color.BLACK, ControlType.HUMAN, 1);
        this.tay.setAim(new DestroyAim(DEFAULT_STATES, this.tay, Color.BLACK));
        this.tay.addState(state0);
        this.tay.addState(state1);
        assertFalse(this.tay.getAim().aimAchieved());
        ((DestroyAim) this.tay.getAim()).setLastDefeated(gg);
        assertTrue(this.tay.getAim().aimAchieved());
        ((DestroyAim) this.tay.getAim()).switchAim();
        assertFalse(this.tay.getAim().aimAchieved());
        this.tay.addState(state3);
        this.tay.addState(state4);
        this.tay.addState(state5);
        assertTrue(this.tay.getAim().aimAchieved());
    }
}
