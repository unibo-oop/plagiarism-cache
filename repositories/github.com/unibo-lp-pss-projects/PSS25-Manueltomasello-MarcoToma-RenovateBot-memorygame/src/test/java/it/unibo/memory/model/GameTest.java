package it.unibo.memory.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void testGameLogic() {
        // coppie della difficoltà media invece di un numero fisso
        Game game = new Game(Difficulty.MEDIUM.totalPairs()); 
        
        //Aggiungo una mossa
        game.addMove(); 
        assertEquals(1, game.getMoves()); 
        
        //Trovo una coppia
        game.addMatchedPair();
        assertEquals(1, game.getMatchedPairs()); 
        
        //La partita non deve essere finita
        assertFalse(game.isGameOver()); 
    }

    @Test
    void testWinCondition() {
        //Per il test di vittoria possiamo anche tenere un numero piccolo, 
        // ma è più elegante usare la difficoltà EASY
        Game smallGame = new Game(Difficulty.EASY.totalPairs());
        int pairsToWin = Difficulty.EASY.totalPairs();
        
        //Troviamo tutte le coppie tranne l'ultima
        for (int i = 0; i < pairsToWin - 1; i++) {
            smallGame.addMatchedPair();
            assertFalse(smallGame.isGameOver(), "La partita non dovrebbe finire alla coppia " + (i+1));
        }
        
        //Troviamo l'ultima coppia
        smallGame.addMatchedPair();
        
        //Verifica finale 
        assertTrue(smallGame.isGameOver(), "La partita dovrebbe essere finita ora!");
    }
}