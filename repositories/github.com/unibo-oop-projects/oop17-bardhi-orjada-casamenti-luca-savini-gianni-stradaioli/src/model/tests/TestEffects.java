package model.tests;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import model.deck.SimpleEffect;
import model.player.Player;
import model.player.PlayerImpl;
/**
 * 
 */
public class TestEffects {
/**
 * 
 */
    @Test
    public void testLifeEffect() {
        final Player p1 = new PlayerImpl("Gianni", 10, 0);
        final Player p2 = new PlayerImpl("Nico", 10, 0);
        final Player p3 = new PlayerImpl("Dada", 10, 0);

        SimpleEffect.REFULL_LIFE_MEDIUM_ME.active(p1, Arrays.asList(p2, p3));

        assertEquals(p1.getLife(), 10); // il primo guadagna 3 punti vita
        assertEquals(p2.getLife(), 10); // gli altri non vengono toccati
        assertEquals(p3.getLife(), 10);
        // i soldi non vengono toccati
        assertEquals(p1.getMoney(), 0);
        assertEquals(p2.getMoney(), 0);
        assertEquals(p3.getMoney(), 0);

        SimpleEffect.TAKE_DAMAGES_HIGH_OTHER.active(p2, Arrays.asList(p1, p3));

        assertEquals(p1.getLife(), 6); // il primo subisce 4 danni
        assertEquals(p2.getLife(), 10); // lui ha attivato l'effetto quindi non
                                        // viene danneggiato
        assertEquals(p3.getLife(), 6); // il terzo subisce 4 danni

        SimpleEffect.TAKE_DAMAGES_MEDIUM_ALL.active(p3, Arrays.asList(p1, p2));
        // Tutti subiscono 3 danni
        assertEquals(p1.getLife(), 3);
        assertEquals(p2.getLife(), 7);
        assertEquals(p3.getLife(), 3);

        SimpleEffect.REFULL_LIFE_SMALL_ME.active(p1, Arrays.asList(p2, p3));

        assertEquals(p1.getLife(), 5); // p1 recupera 2 punnti (SMALL) vita.
        assertEquals(p2.getLife(), 7); // Gli altri rimangono inviariati
        assertEquals(p3.getLife(), 3);
    }
/**
 * 
 */
    @Test
    public void testPointsEffect() {
        final Player p1 = new PlayerImpl("Gianni", 10, 0);
        final Player p2 = new PlayerImpl("Nico", 10, 0);
        final Player p3 = new PlayerImpl("Dada", 10, 0);

        SimpleEffect.INCREASE_POINTS_MEDIUM_ME.active(p2, Arrays.asList(p1, p3));

        assertEquals(p2.getPoints(), 3); // p2 incrementa i suoi punti
        assertEquals(p3.getPoints(), 0); // gli altri no
        assertEquals(p1.getPoints(), 0);

        SimpleEffect.INCREASE_POINTS_SMALL_ME.active(p3, Arrays.asList(p1, p2));

        assertEquals(p3.getPoints(), 2); // p3 incrementa i suoi punti
        assertEquals(p2.getPoints(), 3); // gli altri no
        assertEquals(p1.getPoints(), 0);

        SimpleEffect.INCREASE_POINTS_SMALL_ME.active(p3, Arrays.asList(p1, p2));
        SimpleEffect.DECREASE_POINTS_SMALL_OTHER.active(p3, Arrays.asList(p1, p2));

        assertEquals(p3.getPoints(), 4); // p3 incrementa i suoi punti
        assertEquals(p2.getPoints(), 1); // gli altri no
        assertEquals(p1.getPoints(), 0);

        SimpleEffect.DECREASE_POINTS_MAX_ALL.active(p1, Arrays.asList(p2, p3));
        // In teoria non p1 non perde punti in quanto non ne ha, e gli altri si
        // fermano a 0.
        assertEquals(p3.getPoints(), 0); // p3 incrementa i suoi punti
        assertEquals(p2.getPoints(), 0); // gli altri no
        assertEquals(p1.getPoints(), 0);
        // Gli altri parametri non vengono toccati..
        assertEquals(p1.getLife(), 10);
        assertEquals(p2.getLife(), 10);
        assertEquals(p3.getLife(), 10);
    }
    /**
     * 
     */
    @Test
    public void testMoneyEffect() {
        final Player p1 = new PlayerImpl("Gianni", 10, 0);
        final Player p2 = new PlayerImpl("Nico", 10, 0);
        final Player p3 = new PlayerImpl("Dada", 10, 0);

        SimpleEffect.INCREASE_MONEY_MEDIUM_ME.active(p1, Arrays.asList(p2, p3));

        assertEquals(p1.getMoney(), 3); // p1 guadagna tre soldi
        assertEquals(p2.getMoney(), 0);
        assertEquals(p3.getMoney(), 0);

        SimpleEffect.INCREASE_MONEY_MEDIUM_ME.active(p2, Arrays.asList(p3, p1));

        assertEquals(p1.getMoney(), 3);
        assertEquals(p2.getMoney(), 3); // p2 guadagna tre soldi
        assertEquals(p3.getMoney(), 0);

        SimpleEffect.INCREASE_MONEY_SMALL_ME.active(p3, Arrays.asList(p2, p1));

        assertEquals(p1.getMoney(), 3);
        assertEquals(p2.getMoney(), 3);
        assertEquals(p3.getMoney(), 2); // p1 guadagna due soldi

        SimpleEffect.INCREASE_MONEY_MEDIUM_ME.active(p1, Arrays.asList(p3, p2));
        SimpleEffect.DECREASE_MONEY_SMALL_OTHER.active(p1, Arrays.asList(p3, p2));

        assertEquals(p1.getMoney(), 6); // p1 guadagna 3 soldi
        assertEquals(p2.getMoney(), 1); // p2 rimane un soldo
        assertEquals(p3.getMoney(), 0); // p3 rimane al verde

        SimpleEffect.DECREASE_MONEY_SMALL_ALL.active(p2, Arrays.asList(p3, p1));

        assertEquals(p1.getMoney(), 4); // p1 rimane un soldo
        assertEquals(p2.getMoney(), 0); // gli altri rimangono al verde
        assertEquals(p3.getMoney(), 0);

    }
}
