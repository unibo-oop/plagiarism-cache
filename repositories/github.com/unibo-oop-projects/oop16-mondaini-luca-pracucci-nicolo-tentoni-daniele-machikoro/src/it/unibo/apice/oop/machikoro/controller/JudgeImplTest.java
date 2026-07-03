package it.unibo.apice.oop.machikoro.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import it.unibo.apice.oop.machikoro.model.AimCard;
import it.unibo.apice.oop.machikoro.model.AlreadyBoughtCardException;
import it.unibo.apice.oop.machikoro.model.Card;
import it.unibo.apice.oop.machikoro.model.NotEnoughMoneyException;
import it.unibo.apice.oop.machikoro.model.Player;

/**
 * Classe di Test per il JudgeImpl.
 */
public class JudgeImplTest {

    /**
     * Metodo di test delle funzioni base di un JudgeImpl.
     * 
     * @throws IOException
     *             intercetta le eccezioni riguardanti i file creati e letti nel
     *             costruttore.
     */
    @Test
    public void testBaseJudge() throws IOException {
        final Judge j = new JudgeImpl();
        j.addPlayer("Io", false);
        j.addPlayer("tu", false);
        j.addPlayer("Computer 1", true);

        final Player p1 = j.getPlayers().get(0);
        final Player p2 = j.getPlayers().get(1);
        final Player p3 = j.getPlayers().get(2);

        assertEquals(p1.getName(), "Io");
        assertEquals(p2.getName(), "tu");
        assertEquals(p3.getName(), "Computer 1");

        p2.receiveMoney(1);
        p3.giveMoney(p1, 1);

        assertEquals(p1.getMoney(), 4);
        assertEquals(p2.getMoney(), 4);
        assertEquals(p3.getMoney(), 2);

        assertEquals(p1.getAimCards().size(), 4);
        assertEquals(p2.getAimCards().size(), 4);
        assertEquals(p3.getAimCards().size(), 4);

        assertEquals(p1.getBoardCards().size(), 2);
        assertEquals(p2.getBoardCards().size(), 2);
        assertEquals(p3.getBoardCards().size(), 2);

        final Card c = j.getBoardCards().get(3);
        try {
            p2.buyCard(c);
            assertTrue(p2.getBoardCards().contains(c));
        } catch (NotEnoughMoneyException e) {
            System.out.println(e);
        } catch (AlreadyBoughtCardException e) {
            System.out.println(e);
        }

        p1.getAimCards().stream().filter(f -> f.getName().equals("TrainStation")).forEach(a -> {
            assertFalse(((AimCard) a).isEnabled());
            try {
                p1.buyCard(a);
            } catch (NotEnoughMoneyException e) {
                System.out.println(e);
            } catch (AlreadyBoughtCardException e) {
                System.out.println(e);
            }
            assertEquals(p1.getMoney(), 0);
            assertTrue(((AimCard) a).isEnabled());
        });
    }
}
