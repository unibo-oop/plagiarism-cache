package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

import data.LandsData;
import lands.LandsImpl;
import lands.Lands.MyJButton;

class LandsImplTest {

    private LandsImpl lands = LandsImpl.getLandsImpl();

    @Test
    void testGetTERR() {
        int n = 0;
        Random m = new Random();
        int limit = LandsData.NUMTERR;
        assertFalse(lands == null);                     //controllo che sia istanziato TerritoriImpl.

        assertFalse(lands.getTERR().get(0).equals(null));        //controllo che siano istanziati
        assertTrue(lands.getTERR().size() == limit);             // e che siano 42.

        n = m.nextInt(limit);
        assertTrue(lands.getTERR().get(n).getNome()  //scelto una stato a caso il nome è
                .equals(LandsData.values()[n].getName())); // nella stessa posizione della enum.
       //errore: dato che sono array compara gli indirizzi di memoria.
        //assertTrue(lands.getTERR().get(n).getConfini().
                //equals(TerritoriData.values()[n].getConfini()));
        assertTrue(lands.getTERR().get(n).getOwner() == 0); //owner non settato.

        n = 0;
        for (int i = 0; i < limit; i++) {
            n += lands.getTERR().get(i).getArmies();
        }
        assertTrue(n == limit);       //assegnata un armata a territorio.
    }

    @Test
    void testMyJButtonMethods() {
        lands.getTERR().forEach(a -> a.setArmies(10));  //10 armate a territorio
        lands.getTERR().get(0).setOwner(2);             //1 territorio con proprietario
        MyJButton button = lands.getTERR().get(0);        //button è il primo territorio
        assertTrue(button.getOwner() == 2               //controllo che abbia i settaggi impostati poco prima.
                && button.getArmies() == 10);
    }

}
