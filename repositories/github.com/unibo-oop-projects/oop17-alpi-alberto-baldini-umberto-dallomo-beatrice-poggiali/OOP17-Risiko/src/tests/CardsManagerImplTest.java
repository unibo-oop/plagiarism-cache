package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import bonuscards.CardsCheck;
import data.BonusCards;

class CardsManagerImplTest extends CardsCheck {

    // sublist fatta a metà della lista
    private static final int FIRSTINDEX = 19;
    private static final int LASTINDEX = 24;
    private static final int TESTSLIMIT = 10000;
    private static List<String> carte  = new LinkedList<String>();

    CardsManagerImplTest() {
        for (int i = 0; i < BonusCards.TYPEOFCARDS; i++) {
            for (int j = 0; j < BonusCards.values()[i].getNcards(); j++) {
                carte.add(BonusCards.values()[i].getName());
            }
        }
    }

    @Test //usando solo CardsCheck, combo standard
    void testBonusArmies() {

        LinkedList<String> lista = new LinkedList<>();

        //3 uguali
        for (int i = 0; i < 3; i++) {
            lista.add(BonusCards.INFANTRYMAN.getName()); //3 fanti
        }
        assertEquals(controlForExchange(lista), BonusCards.BONUSSAME);

        for (int i = 0; i < 3; i++) {
            lista.add(BonusCards.CANNON.getName());  //3 cannoni
        }
        assertEquals(controlForExchange(lista), BonusCards.BONUSSAME);

        for (int i = 0; i < 3; i++) {
            lista.add(BonusCards.KNIGHT.getName());  //3 cavalieri
        }
        assertEquals(controlForExchange(lista), BonusCards.BONUSSAME);

        //3 diverse
        lista.add(BonusCards.KNIGHT.getName());
        lista.add(BonusCards.CANNON.getName());
        lista.add(BonusCards.INFANTRYMAN.getName());
        assertEquals(controlForExchange(lista), BonusCards.BONUSDIFF);

        // jolly e due uguali
        lista.add(BonusCards.JOLLY.getName());
        lista.add(BonusCards.KNIGHT.getName());
        lista.add(BonusCards.KNIGHT.getName());
        assertEquals(controlForExchange(lista), BonusCards.BONUSJOLLY);

    }

    @Test
    void test8RandomCombo() {
        List<String> tmp = new LinkedList<>();

        for (int i = 0; i < TESTSLIMIT; i++) {
            Collections.shuffle(carte);
            tmp.addAll(carte.subList(FIRSTINDEX, LASTINDEX));
            System.out.println(tmp.toString());
            assertTrue(controlForExchange(tmp) != 0);
            System.out.println(tmp.toString());
            assertEquals(tmp.size(), 2);
            tmp.clear();
            System.out.println(tmp.toString());
        }
    }
}
