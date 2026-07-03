package model.tests;

import static model.deck.SimpleEffect.DECREASE_POINTS_SMALL_OTHER;
import static model.deck.SimpleEffect.GLI_ALTRI_MOSTRI_PERDONO_2_VITE;
import static model.deck.SimpleEffect.TU_GUADAGNI_1_VITA;
import static model.deck.SimpleEffect.TU_GUADAGNI_5_SOLDI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import model.deck.Card;
import model.deck.EquipmentCard;
import model.deck.IstantCard;
import model.player.Player;
import model.player.PlayerImpl;
/**
 *
 */
public class TestPlayer {

    /**
     * 
     */
    @org.junit.Test
    public void kingTest() {
        final Player p1 = new PlayerImpl("Gianni", 10, 0);
        final Player p2 = new PlayerImpl("Nico", 10, 0);
        final Player p3 = new PlayerImpl("Dada", 10, 0);
        final Player p4 = new PlayerImpl("Malto", 10, 0);

        assertFalse(p1.isKing());
        assertFalse(p2.isKing());
        assertFalse(p3.isKing());
        assertFalse(p4.isKing());

        p1.changeRule();
        p2.changeRule();

        assertTrue(p1.isKing());
        assertTrue(p2.isKing());
        assertFalse(p3.isKing());
        assertFalse(p4.isKing());
    }
    /**
     * 
     */
    @org.junit.Test
    public void pickTest() {
        final Player p1 = new PlayerImpl("Gianni", 10, 0);
        final Player p2 = new PlayerImpl("Nico", 10, 0);
        final Player p3 = new PlayerImpl("Dada", 10, 0);
        final Player p4 = new PlayerImpl("Malto", 10, 0);

        // create 3 new Istant Card
        final Card c1 = new IstantCard("Birra e spada", 7, Arrays.asList(TU_GUADAGNI_1_VITA, GLI_ALTRI_MOSTRI_PERDONO_2_VITE, TU_GUADAGNI_5_SOLDI));
        final Card c2 = new IstantCard("Birra", 1, Arrays.asList(TU_GUADAGNI_1_VITA));
        final Card c3 = new IstantCard("Birra doppio leader", 3, Arrays.asList(TU_GUADAGNI_1_VITA, DECREASE_POINTS_SMALL_OTHER));

        // create 2 new Equipment Card
        final Card c4 = new EquipmentCard("Scudo del goblin", 7, Arrays.asList(TU_GUADAGNI_1_VITA, GLI_ALTRI_MOSTRI_PERDONO_2_VITE, TU_GUADAGNI_5_SOLDI), Collections.emptyList());
        final Card c5 = new EquipmentCard("Armatura del gargoyle", 4, Arrays.asList(TU_GUADAGNI_1_VITA), Collections.emptyList());

        assertEquals(p1.getPlayerCards().size(), 0);
        assertEquals(p2.getPlayerCards().size(), 0);
        assertEquals(p3.getPlayerCards().size(), 0);
        assertEquals(p4.getPlayerCards().size(), 0);

        p1.pickCard(c1, Arrays.asList(p2, p3, p4));
        p2.pickCard(c2, Arrays.asList(p1, p3, p4));
        p3.pickCard(c3, Arrays.asList(p4, p1, p2));

        // check that instant cards do not go into the player's hand

        assertEquals(p1.getPlayerCards().size(), 0);
        assertEquals(p2.getPlayerCards().size(), 0);
        assertEquals(p3.getPlayerCards().size(), 0);
        assertEquals(p4.getPlayerCards().size(), 0);

        p1.pickCard(c4, Arrays.asList(p2, p3, p4));
        p2.pickCard(c5, Arrays.asList(p1, p3, p4));

        //assertEquals(p1.getPlayerCards().size(), 1);
        //assertEquals(p2.getPlayerCards().size(), 1);
        assertEquals(p3.getPlayerCards().size(), 0);
        assertEquals(p4.getPlayerCards().size(), 0);

    }
    /**
     * 
     */
    @org.junit.Test
    public void valuesTest() {

        final Player p1 = new PlayerImpl("Gianni", 10, 0);
        final Player p2 = new PlayerImpl("Nico", 10, 0);
        final Player p3 = new PlayerImpl("Dada", 10, 0);
        final Player p4 = new PlayerImpl("Malto", 10, 0);

        assertEquals(p1.getMoney(), 0);
        assertEquals(p2.getMoney(), 0);
        assertEquals(p3.getMoney(), 0);
        assertEquals(p4.getMoney(), 0);

        assertEquals(p1.getLife(), 10);
        assertEquals(p2.getLife(), 10);
        assertEquals(p3.getLife(), 10);
        assertEquals(p4.getLife(), 10);

        assertEquals(p1.getPoints(), 0);
        assertEquals(p2.getPoints(), 0);
        assertEquals(p3.getPoints(), 0);
        assertEquals(p4.getPoints(), 0);

        // Test money

        p1.loseMoney(2);
        p2.earnMoney(3);

        assertEquals(p1.getMoney(), 0); // money can not go below zero
        assertEquals(p2.getMoney(), 3);
        assertEquals(p3.getMoney(), 0);
        assertEquals(p4.getMoney(), 0);

        // Test life

        assertTrue(p1.isAlive());
        assertTrue(p2.isAlive());
        assertTrue(p3.isAlive());
        assertTrue(p4.isAlive());

        p3.takeDamages(4);
        p4.rechargeLife(2);

        assertEquals(p1.getLife(), 10);
        assertEquals(p2.getLife(), 10);
        assertEquals(p3.getLife(), 6);
        assertEquals(p4.getLife(), 10); // life can not exceed 10

        p3.takeDamages(6);

        assertEquals(p1.getLife(), 10);
        assertEquals(p2.getLife(), 10);
        assertEquals(p3.getLife(), 0);
        assertEquals(p4.getLife(), 10);

        assertTrue(p1.isAlive());
        assertTrue(p2.isAlive());
        assertFalse(p3.isAlive());
        assertTrue(p4.isAlive());

        // test points

        p1.increasePoints(11);
        p1.decreasePoints(4);
        p2.increasePoints(5);

        assertEquals(p1.getPoints(), 7);
        assertEquals(p2.getPoints(), 5);
        assertEquals(p3.getPoints(), 0);
        assertEquals(p4.getPoints(), 0);
    }

}
