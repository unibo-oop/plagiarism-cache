package giocoscudetto.model.match;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import giocoscudetto.model.impl.match.PawnImpl;

/**
 * Tests for PawnImpl.
 */
class TestPawn {

    private static final int DEFAULT_COLOR = 255;
    private static final int STEP = 5;
    private static final int POSITION_VALUE = 12;
    private static final int COLOR_CUSTOM = 123;

    @Test
    void testInitialPosition() {

        final PawnImpl pawn = new PawnImpl(DEFAULT_COLOR);

        assertEquals(0, pawn.getPosition());
    }

    @Test
    void testChangePosition() {

        final PawnImpl pawn = new PawnImpl(DEFAULT_COLOR);

        pawn.changePosition(STEP);

        assertEquals(STEP, pawn.getPosition());
    }

    @Test
    void testSetPosition() {

        final PawnImpl pawn = new PawnImpl(DEFAULT_COLOR);

        pawn.setPosition(POSITION_VALUE);

        assertEquals(POSITION_VALUE, pawn.getPosition());
    }

    @Test
    void testPawnRGB() {

        final PawnImpl pawn = new PawnImpl(COLOR_CUSTOM);

        assertEquals(COLOR_CUSTOM, pawn.getPawnRGB());
    }
}
