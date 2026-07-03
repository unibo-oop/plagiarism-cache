package model.tests;

import java.util.Arrays;

import model.deck.Card;
import model.deck.IstantCard;
import model.player.Player;
import model.player.PlayerImpl;
import static model.deck.SimpleEffect.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 *
 */
public class TestIstantCards {
    /**
     * 
     */
    @org.junit.Test
    public void simpleTest() {
        final Player p1 = new PlayerImpl("Gianni", 10, 0);
        final Player p2 = new PlayerImpl("Nico", 10, 0);
        final Player p3 = new PlayerImpl("Dada", 10, 0);
        final Player p4 = new PlayerImpl("Malto", 10, 0);

        final Card c1 = new IstantCard("Birra e spada", 7, Arrays.asList(TU_GUADAGNI_1_VITA, GLI_ALTRI_MOSTRI_PERDONO_2_VITE, TU_GUADAGNI_5_SOLDI));
        final Card c2 = new IstantCard("Succo", 1, Arrays.asList(TU_GUADAGNI_1_VITA));
        final Card c3 = new IstantCard("Birra doppio leader", 3, Arrays.asList(TU_GUADAGNI_1_VITA, DECREASE_POINTS_SMALL_OTHER));

        assertEquals(c1.getPrice(), 7);
        assertEquals(c2.getPrice(), 1);
        assertEquals(c3.getPrice(), 3);

        assertTrue(c1.isSingleUse());
        assertTrue(c2.isSingleUse());
        assertTrue(c3.isSingleUse());

        assertEquals(p1.getLife(), 10);
        assertEquals(p2.getLife(), 10);
        assertEquals(p3.getLife(), 10);
        assertEquals(p4.getLife(), 10);

        assertEquals(p1.getMoney(), 0);
        assertEquals(p2.getMoney(), 0);
        assertEquals(p3.getMoney(), 0);
        assertEquals(p4.getMoney(), 0);

        assertEquals(p1.getPoints(), 0);
        assertEquals(p2.getPoints(), 0);
        assertEquals(p3.getPoints(), 0);
        assertEquals(p4.getPoints(), 0);

        c1.activeCard(p1, Arrays.asList(p2, p3, p4));

        assertEquals(p1.getLife(), 10);
        assertEquals(p1.getMoney(), 5);
        assertEquals(p2.getLife(), 8);
        assertEquals(p3.getLife(), 8);
        assertEquals(p4.getLife(), 8);
        assertEquals(p2.getMoney(), 0);
        assertEquals(p3.getMoney(), 0);
        assertEquals(p4.getMoney(), 0);

        c2.activeCard(p2, Arrays.asList(p1, p3, p4));

        assertEquals(p1.getLife(), 10);
        assertEquals(p2.getLife(), 9);
        assertEquals(p3.getLife(), 8);
        assertEquals(p4.getLife(), 8);

        c3.activeCard(p3, Arrays.asList(p1, p2, p4));

        assertEquals(p1.getPoints(), 0);
        assertEquals(p2.getPoints(), 0);
        assertEquals(p3.getLife(), 9);
        assertEquals(p4.getPoints(), 0);

    }
    /**
     * 
     */
    @org.junit.Test
    public void effectsNameTest() {
        //final Card c1 = new IstantCard("Birra e spada", 7, Arrays.asList(TU_GUADAGNI_1_VITA, GLI_ALTRI_MOSTRI_PERDONO_2_VITE, TU_GUADAGNI_5_SOLDI));
        final Card c2 = new IstantCard("Succo", 1, Arrays.asList(TU_GUADAGNI_1_VITA));
        //final Card c3 = new IstantCard("Birra doppio leader", 3, Arrays.asList(TU_GUADAGNI_1_VITA, DECREASE_POINTS_SMALL_OTHER));

        assertEquals(c2.getName(), "Birra");
        assertEquals(TU_GUADAGNI_1_VITA.toString(), "Tu guadagni 1 vita");

    }
    /**
     * 
     */
    @org.junit.Test
    public void effectsDescrioptionTest() {
        final Card c1 = new IstantCard("Birra e spada", 7, Arrays.asList(TU_GUADAGNI_1_VITA, GLI_ALTRI_MOSTRI_PERDONO_2_VITE, TU_GUADAGNI_5_SOLDI));
        //final Card c2 = new IstantCard("Succo", 1, Arrays.asList(TU_GUADAGNI_1_VITA));
        //final Card c3 = new IstantCard("Birra doppio leader", 3, Arrays.asList(TU_GUADAGNI_1_VITA, DECREASE_POINTS_SMALL_OTHER));

        System.out.println(c1.getDescription());
        assertEquals(c1.getDescription(), TU_GUADAGNI_1_VITA.toString()
                + " " + GLI_ALTRI_MOSTRI_PERDONO_2_VITE.toString()
                + " " + TU_GUADAGNI_5_SOLDI.toString());

        System.out.println(TU_GUADAGNI_1_VITA.toString()
                + " " + GLI_ALTRI_MOSTRI_PERDONO_2_VITE.toString()
                + " " + TU_GUADAGNI_5_SOLDI.toString());

    }

}
