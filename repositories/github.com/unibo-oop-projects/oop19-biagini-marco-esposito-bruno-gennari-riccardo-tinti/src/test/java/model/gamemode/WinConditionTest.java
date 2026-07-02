package model.gamemode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class WinConditionTest {

    private WinCondition winCondition = new WinConditionImpl();
    private int hits = 0;
    private int opponentRemainingShips = 0;

    @Test
    void testClassic() {

        winCondition.setGameMode(GameMode.CLASSIC);

        opponentRemainingShips = 500;
        assertThrows(IllegalArgumentException.class, () -> winCondition.isMatchOver(hits, opponentRemainingShips));

        opponentRemainingShips = 5;
        assertEquals(winCondition.isMatchOver(hits, opponentRemainingShips), false);

        opponentRemainingShips = 0;
        assertEquals(winCondition.isMatchOver(hits, opponentRemainingShips), true);

        opponentRemainingShips = -300;
        assertThrows(IllegalArgumentException.class, () -> winCondition.isMatchOver(hits, opponentRemainingShips));

    }

    @Test
    void testThreeHits() {

        winCondition.setGameMode(GameMode.THREE_HITS);

        hits = -5000;
        assertThrows(IllegalArgumentException.class, () -> winCondition.isMatchOver(hits, opponentRemainingShips));

        hits = 0;
        assertEquals(winCondition.isMatchOver(hits, opponentRemainingShips), false);

        hits = 3;
        assertEquals(winCondition.isMatchOver(hits, opponentRemainingShips), true);

        hits = 5000;
        assertEquals(winCondition.isMatchOver(hits, opponentRemainingShips), true);
    }

}
