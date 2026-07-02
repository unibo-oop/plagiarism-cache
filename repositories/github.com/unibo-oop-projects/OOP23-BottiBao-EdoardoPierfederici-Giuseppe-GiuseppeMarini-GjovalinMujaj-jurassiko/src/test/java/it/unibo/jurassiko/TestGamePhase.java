package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.core.api.GamePhase;
import it.unibo.jurassiko.core.impl.GamePhaseImpl;

class TestGamePhase {

    private GamePhase gPhase;

    @BeforeEach
    void init() {
        gPhase = new GamePhaseImpl();
    }

    @Test
    void testPhases() {
        assertEquals(gPhase.getPhase(), GamePhase.Phase.PLACEMENT);
        gPhase.goNext();
        assertEquals(gPhase.getPhase(), GamePhase.Phase.ATTACK_FIRST_PART);
        gPhase.goNext();
        assertEquals(gPhase.getPhase(), GamePhase.Phase.ATTACK_SECOND_PART);
        gPhase.goNext();
        assertEquals(gPhase.getPhase(), GamePhase.Phase.MOVEMENT_FIRST_PART);
        gPhase.goNext();
        assertEquals(gPhase.getPhase(), GamePhase.Phase.MOVEMENT_SECOND_PART);
        gPhase.goNext();
        assertEquals(gPhase.getPhase(), GamePhase.Phase.PLACEMENT);
    }
}
