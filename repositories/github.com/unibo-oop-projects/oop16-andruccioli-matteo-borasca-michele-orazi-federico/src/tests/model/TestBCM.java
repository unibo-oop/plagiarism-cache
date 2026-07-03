package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import javafx.util.Pair;
import model.BonusCardManager;
import model.bonus.Bonus;
import model.bonus.BonusCard;
import model.bonus.BonusFactory;
import model.bonus.BonusFactoryImpl;
import model.bonus.StateBonusCard;
import model.player.Player;
import model.player.PlayerImpl;
import model.player.PlayerInfo;
import model.region.Region;
import model.region.RegionImpl;
import model.state.State;
import model.state.StateImpl;
import model.state.StateInfo;
import utils.CircularList;
import utils.HistoryLog;
import utils.enumerations.Color;
import utils.enumerations.ControlType;
import utils.enumerations.MapType;
import view.View;

/**
 * Test for class BonusCardManager.
 */
public class TestBCM {

    private static final int COMBO_SIZE = 3;
    private static final int BONUS_STATE = 2;
    private static final int JOLLY_COMBO_TANK = 12;
    private static final int ALL_TYPES_COMBO_TANK = 10;
    private static final int CAVALRY_COMBO_TANK = 8;
    private static final int INFANTRY_COMBO_TANK = 6;
    private static final int ARTILLERY_COMBO_TANK = 4;
    private static final int NUM_BONUSCARDS = 8;
    private static final int NUM_BONUSCARD_PER_TYPE = 2;
    private static final String ERR = "wrong exception thrown";
    private final Region reg = new RegionImpl("TestRegion", 0);
    private final State state0 = new StateImpl("State0", reg);
    private final State state1 = new StateImpl("State1", reg);
    private final State state2 = new StateImpl("State2", reg);
    private final State state3 = new StateImpl("State3", reg);
    private final State state4 = new StateImpl("State4", reg);
    private final State state5 = new StateImpl("State5", reg);

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

    private final State state6 = new StateImpl("State6", reg);
    private final BonusCard inf4 = bf.getInfantry(state6);
    private final BonusCard inf5 = bf.getInfantry(state6);
    private final BonusCard cav4 = bf.getCavalry(state6);
    private final BonusCard cav5 = bf.getCavalry(state6);
    private final BonusCard art4 = bf.getArtillery(state6);
    private final BonusCard art5 = bf.getArtillery(state6);
    private final BonusCard art6 = bf.getArtillery(state6);

    private final List<State> states = new ArrayList<>(Arrays.asList(state0, state1, state2, state3, state4, state5));

    /**
     * Test the correctness of BonusCardManager initialization.
     */
    @Test
    public void testInitialization() {
        final BonusFactory bf = new BonusFactoryImpl();
        final State state6 = new StateImpl("State6", reg);
        final BonusCard inf4 = bf.getInfantry(state6);
        final BonusCard inf5 = bf.getInfantry(state6);
        final BonusCard cav4 = bf.getCavalry(state6);
        HistoryLog.getLog().registerView(this.dummyView);
        BonusCardManager.getInstance().init(states);

        // at this point the deck should contain 8 elements:
        // 2Jolly+6StateBonusCard
        assertEquals(BonusCardManager.getInstance().getDeck().size(), NUM_BONUSCARDS);
        // the deck should contain 2 BonusCard object foreach type of bonus:

        Arrays.asList(Bonus.values()).forEach(b -> assertEquals(
                BonusCardManager.getInstance().getDeck().stream().filter(p -> p.getBonusType().equals(b)).count(),
                NUM_BONUSCARD_PER_TYPE));

        for (final State s : this.states) {
            boolean found = false;
            for (final BonusCard bc : BonusCardManager.getInstance().getDeck()) {
                if (bc instanceof StateBonusCard && ((StateBonusCard) bc).getState() == s) {
                    found = true;
                }
            }
            assertTrue(found);
        }

        // If someone tries to call "init" method after having called "init"
        // method nothing should happen:
        BonusCardManager.getInstance().init(states);
        assertEquals(BonusCardManager.getInstance().getDeck().size(), NUM_BONUSCARDS);
        Arrays.asList(Bonus.values()).forEach(b -> assertEquals(
                BonusCardManager.getInstance().getDeck().stream().filter(p -> p.getBonusType().equals(b)).count(),
                NUM_BONUSCARD_PER_TYPE));

        // If someone tries to call "recovery" method after having called "init"
        // method nothing should happen:
        BonusCardManager.getInstance().recovery(Arrays.asList(inf4, inf5, cav4));
        assertEquals(BonusCardManager.getInstance().getDeck().size(), NUM_BONUSCARDS);
        Arrays.asList(Bonus.values()).forEach(b -> assertEquals(
                BonusCardManager.getInstance().getDeck().stream().filter(p -> p.getBonusType().equals(b)).count(),
                NUM_BONUSCARD_PER_TYPE));
        Arrays.asList(inf4, inf5, cav4).stream()
                .forEach(bc -> assertFalse(BonusCardManager.getInstance().getDeck().contains(bc)));
        BonusCardManager.getInstance().resetInitCalled();
    }

    /**
     * Test the correctness of BonusCardManager assignments.
     */
    @Test
    public void testCardAssignment() {
        final Player tay1 = new PlayerImpl("Taylor Swift1", Color.RED, ControlType.HUMAN, 100);
        final Player gg = new PlayerImpl("Gossip Girl", Color.PURPLE, ControlType.HUMAN, 1);
        int numCardInDeck = NUM_BONUSCARDS;
        int numTayCard = 0;
        int numGgCard = 0;
        HistoryLog.getLog().registerView(this.dummyView);
        this.states.stream().forEach(s -> tay1.addState(s));
        BonusCardManager.getInstance().init(this.states);

        BonusCardManager.getInstance().addRandomBonusCardTo(tay1);
        numCardInDeck--;
        numTayCard++;


        assertEquals(BonusCardManager.getInstance().getDeck().size(), numCardInDeck);
        assertEquals(tay1.getBonusCardsList().size(), numTayCard);
        BonusCardManager.getInstance().addRandomBonusCardTo(gg);
        numCardInDeck--;
        numGgCard++;

        System.out.println("gg: " + gg.getBonusCardsList().size());
        System.out.println("deck: " + BonusCardManager.getInstance().getDeck().size() + "\n");

        assertEquals(BonusCardManager.getInstance().getDeck().size(), numCardInDeck);
        assertEquals(gg.getBonusCardsList().size(), numGgCard);

        gg.getBonusCardsList().stream()
                .forEach(bc -> assertFalse(BonusCardManager.getInstance().getDeck().contains(bc)));
        while (!BonusCardManager.getInstance().getDeck().isEmpty()) {
            BonusCardManager.getInstance().addRandomBonusCardTo(tay1);
            numCardInDeck--;
            numTayCard++;
        }
        assertEquals(tay1.getBonusCardsList().size(), numTayCard);

        BonusCardManager.getInstance().resetInitCalled();
    }

    /**
     * Test the correctness of BonusCardManager method acquireBonusCard.
     */
    @Test
    public void testacquireBonusCardMethod() {
        final Player tay = new PlayerImpl("Taylor Swift2", Color.RED, ControlType.HUMAN, 100);
        final Player gg = new PlayerImpl("Gossip Girl", Color.PURPLE, ControlType.HUMAN, 1);
        HistoryLog.getLog().registerView(this.dummyView);
        BonusCardManager.getInstance().init(this.states);

        BonusCardManager.getInstance().addRandomBonusCardTo(tay);
        BonusCardManager.getInstance().addRandomBonusCardTo(tay);
        BonusCardManager.getInstance().addRandomBonusCardTo(tay);
        BonusCardManager.getInstance().addRandomBonusCardTo(gg);
        BonusCardManager.getInstance().addRandomBonusCardTo(gg);


        int numTayCard = tay.getBonusCardsList().size();

        numTayCard += gg.getBonusCardsList().size();
        BonusCardManager.getInstance().acquireBonusCard(tay, gg);

        assertEquals(tay.getBonusCardsList().size(), numTayCard);
        assertEquals(gg.getBonusCardsList().size(), 0);

        BonusCardManager.getInstance().resetInitCalled();

    }

    /**
     * Test the correctness of BonusCardManager method tradeCombo.
     */
    @Test
    public void testTrade() {
        final Player tay = new PlayerImpl("Taylor Swift", Color.RED, ControlType.HUMAN, 100);
        HistoryLog.getLog().registerView(this.dummyView);
        BonusCardManager.getInstance().init(this.states);


        int supposedTanksToDeploy = 0;

        // ------------------------------------- testing artillery
        Arrays.asList(art1, art2, art3).forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += ARTILLERY_COMBO_TANK;

        BonusCardManager.getInstance().tradeCombo(tay, new LinkedList<>(Arrays.asList(art3, art1, art2)));
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertFalse(tay.getBonusCardsList().contains(art3));
        assertFalse(tay.getBonusCardsList().contains(art1));
        assertFalse(tay.getBonusCardsList().contains(art2));

        // ------------------------------------- testing infantry

        Arrays.asList(inf1, inf2, inf3).forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += INFANTRY_COMBO_TANK;

        BonusCardManager.getInstance().tradeCombo(tay, new LinkedList<>(Arrays.asList(inf1, inf3, inf2)));
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertFalse(tay.getBonusCardsList().contains(inf2));
        assertFalse(tay.getBonusCardsList().contains(inf1));
        assertFalse(tay.getBonusCardsList().contains(inf3));

        // ------------------------------------- testing cavalry

        Arrays.asList(cav1, cav2, cav3).forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += CAVALRY_COMBO_TANK;

        BonusCardManager.getInstance().tradeCombo(tay, new LinkedList<>(Arrays.asList(cav3, cav2, cav1)));
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertFalse(tay.getBonusCardsList().contains(cav1));
        assertFalse(tay.getBonusCardsList().contains(cav2));
        assertFalse(tay.getBonusCardsList().contains(cav3));

        // ------------------------------------- testing
        // cavalry-infantry-artillery

        Arrays.asList(cav1, art2, inf3).forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += ALL_TYPES_COMBO_TANK;

        BonusCardManager.getInstance().tradeCombo(tay, new LinkedList<>(Arrays.asList(inf3, art2, cav1)));
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertFalse(tay.getBonusCardsList().contains(cav1));
        assertFalse(tay.getBonusCardsList().contains(art2));
        assertFalse(tay.getBonusCardsList().contains(inf3));

        // ------------------------------------- testing jolly-artillery

        Arrays.asList(art1, j, art2).stream().forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += JOLLY_COMBO_TANK;

        BonusCardManager.getInstance().tradeCombo(tay, new LinkedList<>(Arrays.asList(j, art1, art2)));
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertFalse(tay.getBonusCardsList().contains(j));
        assertFalse(tay.getBonusCardsList().contains(art1));
        assertFalse(tay.getBonusCardsList().contains(art2));

        // ------------------------------------- testing jolly-infantry

        Arrays.asList(inf2, j, inf1).stream().forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += JOLLY_COMBO_TANK;

        BonusCardManager.getInstance().tradeCombo(tay, new LinkedList<>(Arrays.asList(inf1, j, inf2)));
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertFalse(tay.getBonusCardsList().contains(j));
        assertFalse(tay.getBonusCardsList().contains(inf1));
        assertFalse(tay.getBonusCardsList().contains(inf2));

        // ------------------------------------- testing jolly-cavalry

        Arrays.asList(cav1, cav2, j).stream().forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += JOLLY_COMBO_TANK;

        BonusCardManager.getInstance().tradeCombo(tay, new LinkedList<>(Arrays.asList(cav1, j, cav2)));
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertFalse(tay.getBonusCardsList().contains(j));
        assertFalse(tay.getBonusCardsList().contains(cav1));
        assertFalse(tay.getBonusCardsList().contains(cav2));

        // ------------------------------------- testing artillery with stuff
        // and states
        Arrays.asList(art1, art2, art3, inf1, inf2).forEach(bc -> tay.addBonusCard(bc));
        Arrays.asList(state0, state1, state2).forEach(s -> tay.addState(s));

        supposedTanksToDeploy += ARTILLERY_COMBO_TANK;
        supposedTanksToDeploy += BONUS_STATE;
        supposedTanksToDeploy += BONUS_STATE;
        supposedTanksToDeploy += BONUS_STATE;

        BonusCardManager.getInstance().tradeCombo(tay, new LinkedList<>(Arrays.asList(art3, art1, art2)));
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertFalse(tay.getBonusCardsList().contains(art3));
        assertFalse(tay.getBonusCardsList().contains(art1));
        assertFalse(tay.getBonusCardsList().contains(art2));

        // ------------------------------------- testing
        // cavalry-infantry-artillery with stuff and states

        Arrays.asList(cav1, art2, inf3).forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += ALL_TYPES_COMBO_TANK;
        supposedTanksToDeploy += BONUS_STATE;

        BonusCardManager.getInstance().tradeCombo(tay, new LinkedList<>(Arrays.asList(inf3, art2, cav1)));
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertFalse(tay.getBonusCardsList().contains(cav1));
        assertFalse(tay.getBonusCardsList().contains(art2));
        assertFalse(tay.getBonusCardsList().contains(inf3));

        // ------------------------------------- testing jolly-infantry with
        // stuff and states

        Arrays.asList(j, cav2, art2).stream().forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += JOLLY_COMBO_TANK;
        supposedTanksToDeploy += BONUS_STATE;

        BonusCardManager.getInstance().tradeCombo(tay, new LinkedList<>(Arrays.asList(inf1, j, inf2)));
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertFalse(tay.getBonusCardsList().contains(j));
        assertFalse(tay.getBonusCardsList().contains(inf1));
        assertFalse(tay.getBonusCardsList().contains(inf2));

        BonusCardManager.getInstance().resetInitCalled();

    }

    /**
     * Test the correctness of BonusCardManager method getBestCombo.
     */
    @Test
    public void testBestCombo() {
        HistoryLog.getLog().registerView(this.dummyView);
        BonusCardManager.getInstance().init(this.states);

        System.out.println("\n***testBestCombo()");

        int supposedTanksToDeploy = 0;
        int size = 0;
        List<BonusCard> chosen = new LinkedList<>();

        // ------------------------------------- testing jolly-combo
        final Player tay = new PlayerImpl("Taylor Swift", Color.RED, ControlType.HUMAN, 100);

        Arrays.asList(j, art1, art2, art3, inf1, inf2, inf3, cav1, cav2, cav3).stream()
                .forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += JOLLY_COMBO_TANK;
        size += (Arrays.asList(j, art1, art2, art3, inf1, inf2, inf3, cav1, cav2, cav3).size() - COMBO_SIZE);

        chosen = BonusCardManager.getInstance().getBestCombo(tay);

        BonusCardManager.getInstance().tradeCombo(tay, chosen);
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertFalse(tay.getBonusCardsList().contains(j));
        assertEquals(tay.getBonusCardsList().size(), size);

        // ------------------------------------- testing All types combo

        tay.removeAllBonusCard();

        Arrays.asList(art3, inf1, inf2, inf3, cav1, cav2, cav3).stream().forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += ALL_TYPES_COMBO_TANK;
        size = (Arrays.asList(art3, inf1, inf2, inf3, cav1, cav2, cav3).size() - COMBO_SIZE);

        chosen = BonusCardManager.getInstance().getBestCombo(tay);

        BonusCardManager.getInstance().tradeCombo(tay, chosen);
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertEquals(tay.getBonusCardsList().size(), size);

        // ------------------------------------- testing Cavalry combo

        Arrays.asList(inf1, cav1).stream().forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += CAVALRY_COMBO_TANK;
        size += (Arrays.asList(inf1, cav1).size() - COMBO_SIZE);

        chosen = BonusCardManager.getInstance().getBestCombo(tay);

        BonusCardManager.getInstance().tradeCombo(tay, chosen);
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertEquals(tay.getBonusCardsList().size(), size);

        // ------------------------------------- testing Infantry combo

        Arrays.asList(art1, art2, art3).stream().forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += INFANTRY_COMBO_TANK;
        size += (Arrays.asList(art1, art2, art3).size() - COMBO_SIZE);

        chosen = BonusCardManager.getInstance().getBestCombo(tay);

        BonusCardManager.getInstance().tradeCombo(tay, chosen);
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertEquals(tay.getBonusCardsList().size(), size);


        // ------------------------------------- testing Artillery combo

        Arrays.asList(inf1).stream().forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += ARTILLERY_COMBO_TANK;
        size += (Arrays.asList(inf1).size() - COMBO_SIZE);

        chosen = BonusCardManager.getInstance().getBestCombo(tay);

        BonusCardManager.getInstance().tradeCombo(tay, chosen);
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertEquals(tay.getBonusCardsList().size(), size);

        // ------------------------------------- testing jolly-combo with states
        // vs other jolly combos without states

        Arrays.asList(j, art1, art2, art3, inf2, inf3, cav1, cav2, cav3, inf4, inf5, art4).stream()
                .forEach(bc -> tay.addBonusCard(bc));
        tay.addState(state6);

        supposedTanksToDeploy += JOLLY_COMBO_TANK;
        supposedTanksToDeploy += BONUS_STATE;
        supposedTanksToDeploy += BONUS_STATE;
        size += (Arrays.asList(j, art1, art2, art3, inf2, inf3, cav1, cav2, cav3, inf4, inf5, art4).size()
                - COMBO_SIZE);

        chosen = BonusCardManager.getInstance().getBestCombo(tay);

        BonusCardManager.getInstance().tradeCombo(tay, chosen);
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertFalse(tay.getBonusCardsList().contains(j));
        assertFalse(tay.getBonusCardsList().contains(inf4));
        assertFalse(tay.getBonusCardsList().contains(inf5));
        assertEquals(tay.getBonusCardsList().size(), size);

        // ------------------------------------- testing All types combo with
        // all states vs other All types combos without states

        Arrays.asList(cav4, cav5, inf4).stream().forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += ALL_TYPES_COMBO_TANK;
        supposedTanksToDeploy += BONUS_STATE;
        supposedTanksToDeploy += BONUS_STATE;
        supposedTanksToDeploy += BONUS_STATE;
        size += (Arrays.asList(cav4, cav5, inf4).size() - COMBO_SIZE);

        chosen = BonusCardManager.getInstance().getBestCombo(tay);

        BonusCardManager.getInstance().tradeCombo(tay, chosen);
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertEquals(tay.getBonusCardsList().size(), size);

        // ------------------------------------- testing Artillery combo with
        // all states vs Cavalry combo without states
        tay.removeState(state6);
        tay.removeAllBonusCard();
        tay.addState(state6);

        Arrays.asList(art4, art5, art6, cav1, cav2, cav3).stream().forEach(bc -> tay.addBonusCard(bc));

        supposedTanksToDeploy += ARTILLERY_COMBO_TANK;
        supposedTanksToDeploy += BONUS_STATE;
        supposedTanksToDeploy += BONUS_STATE;
        supposedTanksToDeploy += BONUS_STATE;
        size = (Arrays.asList(art4, art5, art6, cav1, cav2, cav3).size() - COMBO_SIZE);

        chosen = BonusCardManager.getInstance().getBestCombo(tay);

        BonusCardManager.getInstance().tradeCombo(tay, chosen);
        assertEquals(tay.getTanksToDeploy(), supposedTanksToDeploy);
        assertEquals(tay.getBonusCardsList().size(), size);
        assertFalse(tay.getBonusCardsList().contains(art4));
        assertFalse(tay.getBonusCardsList().contains(art5));
        assertFalse(tay.getBonusCardsList().contains(art6));

        BonusCardManager.getInstance().resetInitCalled();

    }

    /**
     * Test the correctness of BonusCardManager Recovery method.
     */
    @Test
    public void testRecovery() {

        final int numJollyCards = 1;
        final int numArtilleryCards = 3;
        final int numInfantryCards = 3;
        final int numCavalryCards = 1;
        HistoryLog.getLog().registerView(this.dummyView);
        BonusCardManager.getInstance().recovery(Arrays.asList(j, art1, art2, art3, inf1, inf2, inf3, cav1));

        // at this point the deck should contain 8 elements:
        // 2Jolly+6StateBonusCard
        assertEquals(BonusCardManager.getInstance().getDeck().size(), NUM_BONUSCARDS);
        // the deck should contain: 1 BonusCard object of type Jolly and
        // Cavalry; 3 bonusCard objects of type Artillery and Infantry
        assertEquals(BonusCardManager.getInstance().getDeck().stream().filter(p -> p.getBonusType().equals(Bonus.JOLLY))
                .count(), numJollyCards);
        assertEquals(BonusCardManager.getInstance().getDeck().stream()
                .filter(p -> p.getBonusType().equals(Bonus.ARTILLERY)).count(), numArtilleryCards);
        assertEquals(BonusCardManager.getInstance().getDeck().stream()
                .filter(p -> p.getBonusType().equals(Bonus.INFANTRY)).count(), numInfantryCards);
        assertEquals(BonusCardManager.getInstance().getDeck().stream()
                .filter(p -> p.getBonusType().equals(Bonus.CAVALRY)).count(), numCavalryCards);

        // If someone tries to call "init" method after having called "recovery"
        // method nothing should happen:
        BonusCardManager.getInstance().init(states);
        assertEquals(BonusCardManager.getInstance().getDeck().size(), NUM_BONUSCARDS);
        assertEquals(BonusCardManager.getInstance().getDeck(),
                Arrays.asList(j, art1, art2, art3, inf1, inf2, inf3, cav1));

        // If someone tries to call "recovery" method after having called
        // "recovery" method nothing should happen:
        BonusCardManager.getInstance().recovery(Arrays.asList(inf4, inf5, cav4));
        assertEquals(BonusCardManager.getInstance().getDeck().size(), NUM_BONUSCARDS);
        assertEquals(BonusCardManager.getInstance().getDeck(),
                Arrays.asList(j, art1, art2, art3, inf1, inf2, inf3, cav1));
        Arrays.asList(inf4, inf5, cav4).stream()
                .forEach(bc -> assertFalse(BonusCardManager.getInstance().getDeck().contains(bc)));

        BonusCardManager.getInstance().resetInitCalled();

    }

    /**
     * Test the correctness of BonusCardManager methods when these throw
     * exceptions.
     */
    @Test
    public void testExceptions() {

        final Player tay = new PlayerImpl("Taylor Swift", Color.RED, ControlType.HUMAN, 100);
        final Player gg = new PlayerImpl("Gossip Girl", Color.PURPLE, ControlType.HUMAN, 1);
        HistoryLog.getLog().registerView(this.dummyView);

        // testing init method passing a null list as argument
        try {
            BonusCardManager.getInstance().init(null);
            fail("call init(final List<State> states) passing a null list should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing init method passing an empty list as argument
        try {
            BonusCardManager.getInstance().init(new LinkedList<>());
            fail("call init(final List<State> states) passing an empty list should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing recovery method passing a null list as argument
        try {
            BonusCardManager.getInstance().recovery(null);
            fail("call recovery(final Collection<BonusCard> bonusCardDeck) passing a null list should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing addRandomBonusCardTo method passing a null player as argument
        try {
            BonusCardManager.getInstance().addRandomBonusCardTo(null);
            fail("call addRandomBonusCardTo(final Player player) passing a null player should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing addRandomBonusCardTo method without having called init or
        // recovery method before
        try {
            BonusCardManager.getInstance().addRandomBonusCardTo(tay);
            fail("call addRandomBonusCardTo(final Player player) without having called init or recovery method before should raise an exception!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing tradeCombo method passing a null player as argument
        try {
            BonusCardManager.getInstance().tradeCombo(null, Arrays.asList(j, inf1, inf2));
            fail("call tradeCombo(final Player player, final List<BonusCard> combo) passing a null player should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing tradeCombo method passing a null combo as argument
        try {
            BonusCardManager.getInstance().tradeCombo(tay, null);
            fail("call tradeCombo(final Player player, final List<BonusCard> combo) passing a null combo should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing tradeCombo method passing an invalid combo (wrong num of
        // BonusCard contained ) as argument
        try {
            BonusCardManager.getInstance().tradeCombo(tay, Arrays.asList(j, inf1));
            fail("call tradeCombo(final Player player, final List<BonusCard> combo) passing an invalid combo should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing tradeCombo method without having called init or recovery
        // method before
        try {
            BonusCardManager.getInstance().tradeCombo(tay, Arrays.asList(j, inf1, inf2));
            fail("call tradeCombo(final Player player, final List<BonusCard> combo) without having called init or recovery method before should raise an exception!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing getBestCombo method passing a null player as argument
        try {
            BonusCardManager.getInstance().getBestCombo(null);
            fail("call getBestCombo(final Player player) passing a null player should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing getBestCombo method without having called init or recovery
        // method before
        try {
            BonusCardManager.getInstance().getBestCombo(tay);
            fail("call getBestCombo(final Player player) without having called init or recovery method before should raise an exception!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing tradeCombo method passing a null conqueror player as argument
        try {
            BonusCardManager.getInstance().acquireBonusCard(null, gg);
            fail("call acquireBonusCard(final Player conqueror, final Player defeated) passing a null conqueror player should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing acquireBonusCard method passing a null defeated player as
        // argument
        try {
            BonusCardManager.getInstance().acquireBonusCard(tay, null);
            fail("call acquireBonusCard(final Player conqueror, final Player defeated) passing a null defeated player should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        // testing acquireBonusCard method without having called init or
        // recovery method before
        try {
            BonusCardManager.getInstance().acquireBonusCard(tay, gg);
            fail("call acquireBonusCard(final Player conqueror, final Player defeated) without having called init or recovery method before should raise an exception!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail(ERR);
        }

        BonusCardManager.getInstance().resetInitCalled();

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
