package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import maingame.level.Difficulty;
import maingame.menu.menu.Option;
import maingame.menu.model.ModelMenu;
import maingame.menu.model.ModelMenuImpl;

/**
 *Classe test per il menu.
 */
public class TestModelMenu {
    private final ModelMenu model = new ModelMenuImpl();

    /**
     * Testa la parte relativa alla gestione dei comandi e il loro indice.
     */
    @Test
    public void testModelMenuOption() {
        // imposto comandi a model
        model.addOption(Option.EDITOR);
        model.addOption(Option.START);
        model.addOption(Option.STATS);
        model.addOption(Option.DIFF);
        // Verifica che l'indice sia inizializzato a 0
        assertEquals(model.getIndex(), 0);
        model.setIndex(3);
        // Verifica che l'indice del model sia giuto
        assertEquals(model.getIndex(), 3);
        assertEquals(model.getOption(2), Option.STATS);
        assertEquals(model.getOption(0), Option.EDITOR);
        //Verifica che il numero di comandi sia corretto
        assertEquals(model.getNumOption(), 4);
    }

    /**
     * Testa la parte relativa al settaggio della difficoltà.
     */
    @Test
    public void testDiff() {
        // verifico che la difficoltà sia inizializzata ad easy
        assertEquals(model.getDifficulty(), Difficulty.EASY);

        model.setDifficulty(Difficulty.HARD);
        assertEquals(model.getDifficulty(), Difficulty.HARD);
    }

    /**
     * Testa la parte relativa alla gestione della visualizzazione dei panel secondari.
     */
    @Test
    public void testShow() {
        /*
         * Verifico che lo stato di visualizzazione dei ocmnadi e delle
         * statistiche sia impostato a false
         */
        assertFalse(model.isShowCommand());
        assertFalse(model.isShowStats());
        model.setShowCommand(true);
        // Verifico che lo stato di visualizzazione comandi sia true
        assertTrue(model.isShowCommand());
        model.setShowStats(true);
        // Verifico che lo stato di visualizzazione comandi sia true
        assertTrue(model.isShowStats());
    }

    /**
     * Test per eccezione.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testException() {
        model.getOption(-1);
    }
}
