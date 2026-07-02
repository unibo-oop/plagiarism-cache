package model.intelligence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.match.PlaygroundBattle;
import model.players.ArtificialPlayer;

/**
 * JUnit test for {@link IntelligenceComputation}.
 */
public class BasicIntelligenceComputationTest {

    private static final int ROWS = 10;
    private static final int COLS = 10;

    private static final int MAX_SHIPS_NUM = 5;

    private static final String BASIC_AI_NAME = "BasicAI";
    private static final String BASIC_AI_PASS = "basic";

    private IntelligenceComputation basicComput = new BasicIntelligenceComputation(ROWS, COLS);
    private ArtificialIntelligence basicAI = new BasicArtificialIntelligence(basicComput);

    /**
     * Test if returns a playground containing all ships.
     */
    @Test
    void basicIntelligenceInitShipsTest() {
        ArtificialPlayer playerAI = new ArtificialPlayer(BASIC_AI_NAME, BASIC_AI_PASS, basicAI);
        PlaygroundBattle grid = playerAI.getArtificialIntelligence().initShipsOnGrid();

        assertEquals(MAX_SHIPS_NUM, grid.getNumberOfAliveShip());
    }

}
