package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;

import model.bonus.Bonus;
import model.bonus.BonusCard;
import model.bonus.BonusFactory;
import model.bonus.BonusFactoryImpl;
import model.player.Player;
import model.player.PlayerImpl;
import model.region.Region;
import model.region.RegionImpl;
import model.state.State;
import model.state.StateImpl;
import utils.enumerations.Color;
import utils.enumerations.ControlType;
/**
 * Test for class for those methods related to BonusCards in playerImpl.
 */
public class TestBonusInPlayer {

    private static final int NUM_JOLLY = 1;
    private static final int NUM_STATEBONUSCARD = 3;
    private final Region reg = new RegionImpl("TestRegion", 0);
    private final State state0 = new StateImpl("State0", reg);
    private final State state1 = new StateImpl("State1", reg);
    private final State state2 = new StateImpl("State2", reg);
    private final State state3 = new StateImpl("State3", reg);
    private final State state4 = new StateImpl("State4", reg);
    private final State state5 = new StateImpl("State5", reg);
    private final Player tay = new PlayerImpl("Taylor Swift", Color.RED, ControlType.HUMAN, 100);

    private final BonusFactory bf = new BonusFactoryImpl();
    private final BonusCard j = bf.getJolly();
    private final BonusCard art1 = bf.getArtillery(state0);
    private final BonusCard art2 = bf.getArtillery(state1);
    private final BonusCard art3 = bf.getArtillery(state2);
    private final BonusCard inf1 = bf.getInfantry(state2);
    private final BonusCard inf2 = bf.getInfantry(state3);
    private final BonusCard inf3 = bf.getInfantry(state4);
    private final BonusCard cav1 = bf.getCavalry(state3);
    private final BonusCard cav2 = bf.getCavalry(state4);
    private final BonusCard cav3 = bf.getCavalry(state5);

    /**
     * Test the correctness of addBonusCard & getBonusCards methods in
     * PlayerImpl class.
     */
    @Test
    public void testAddAndgetBonusCards() {
        Arrays.asList(j, art1, art2, art3, inf1, inf2, inf3, cav1, cav2, cav3).stream()
                .forEach(bc -> tay.addBonusCard(bc));
        assertTrue(this.tay.getBonusCards().get(Bonus.JOLLY).contains(j));
        assertEquals(this.tay.getBonusCards().get(Bonus.JOLLY).size(), NUM_JOLLY);

        assertTrue(this.tay.getBonusCards().get(Bonus.ARTILLERY).containsAll(Arrays.asList(art1, art2, art3)));
        assertEquals(this.tay.getBonusCards().get(Bonus.ARTILLERY).size(), NUM_STATEBONUSCARD);

        assertTrue(this.tay.getBonusCards().get(Bonus.INFANTRY).containsAll(Arrays.asList(inf1, inf2, inf3)));
        assertEquals(this.tay.getBonusCards().get(Bonus.INFANTRY).size(), NUM_STATEBONUSCARD);

        assertTrue(this.tay.getBonusCards().get(Bonus.CAVALRY).containsAll(Arrays.asList(cav1, cav2, cav3)));
        assertEquals(this.tay.getBonusCards().get(Bonus.CAVALRY).size(), NUM_STATEBONUSCARD);
    }

    /**
     * Test the correctness of getBonusCardsList methods in PlayerImpl class.
     */
    @Test
    public void testGetBonusCardsList() {
        this.tay.removeAllBonusCard();
        Arrays.asList(j, art1, art2, art3, inf1, inf2, inf3, cav1, cav2, cav3).stream()
                .forEach(bc -> tay.addBonusCard(bc));
        assertTrue(this.tay.getBonusCardsList()
                .containsAll(Arrays.asList(j, art1, art2, art3, inf1, inf2, inf3, cav1, cav2, cav3)));
        assertEquals(this.tay.getBonusCardsList().size(),
                Arrays.asList(j, art1, art2, art3, inf1, inf2, inf3, cav1, cav2, cav3).size());
    }

    /**
     * Test the correctness of removeCombo method in PlayerImpl class.
     */
    @Test
    public void testRemoveCombo() {
        this.tay.removeAllBonusCard();
        Arrays.asList(j, art1, art2, art3).stream().forEach(bc -> tay.addBonusCard(bc));
        // testing removeCombo method passing a null combo as argument
        try {
            this.tay.removeCombo(null);
            fail("call removeCombo(final List<BonusCard> combo) passing a null combo should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("wrong exception thrown");
        }

        // testing removeCombo method passing an empty combo as argument
        try {
            this.tay.removeCombo(new LinkedList<>());
            fail("call removeCombo(final List<BonusCard> combo) passing an empty combo should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("wrong exception thrown");
        }

        assertTrue(this.tay.getBonusCardsList().containsAll(Arrays.asList(j, art1, art2)));
        this.tay.removeCombo(new LinkedList<BonusCard>(Arrays.asList(j, art1, art2)));
        assertFalse(this.tay.getBonusCardsList().contains(j));
        assertFalse(this.tay.getBonusCardsList().contains(art1));
        assertFalse(this.tay.getBonusCardsList().contains(art2));
        assertTrue(this.tay.getBonusCardsList().contains(art3));
    }

    /**
     * Test the correctness of removeAllBonusCard method in PlayerImpl class.
     */
    @Test
    public void testRemoveAllBonusCard() {
        this.tay.addState(state1);
        Arrays.asList(j, art1, art2, art3).stream().forEach(bc -> tay.addBonusCard(bc));
        // testing removeAllBonusCard on a player who hasn't lost yet (a player
        // who still have a state)
        try {
            this.tay.removeAllBonusCard();
            fail("call removeCombo() on tay should raise an exception!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("wrong exception thrown");
        }
        assertTrue(this.tay.getBonusCardsList().containsAll(Arrays.asList(j, art1, art2, art3)));

        this.tay.removeState(state1);
        assertTrue(this.tay.getStates().isEmpty());
        try {
            this.tay.removeAllBonusCard();
        } catch (Exception e) {
            fail("this call should raise no exception");
        }
        assertTrue(this.tay.getBonusCardsList().isEmpty());
    }

    /**
     * Test the correctness of addBonusCardCollection method in PlayerImpl
     * class.
     */
    @Test
    public void testAddBonusCardCollection() {
        this.tay.addBonusCardCollection(Arrays.asList(j, art1, art2, art3, inf1, inf2, inf3, cav1, cav2, cav3));

        assertTrue(this.tay.getBonusCardsList()
                .containsAll(Arrays.asList(j, art1, art2, art3, inf1, inf2, inf3, cav1, cav2, cav3)));
        assertEquals(this.tay.getBonusCardsList().size(),
                Arrays.asList(j, art1, art2, art3, inf1, inf2, inf3, cav1, cav2, cav3).size());

    }
}
