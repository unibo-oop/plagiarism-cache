package it.unibo.jtrs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jtrs.model.api.ScoreModel;
import it.unibo.jtrs.model.impl.ScoreModelImpl;

class ScoreModelTest {

    private ScoreModel model;

    // CHECKSTYLE: MagicNumber OFF
    /**
     * Initialize necessary field for the tests.
     */
    @BeforeEach
    void init() {
        this.model = new ScoreModelImpl();
    }

    /**
     * Tests score evaluation.
     */
    @Test
    void testNextPiece() {
        this.model.evaluate(4);
        assertEquals(1200, this.model.getScore());
        assertEquals(0, this.model.getLevel());

        this.model.evaluate(4);
        this.model.evaluate(4);
        this.model.evaluate(4);
        this.model.evaluate(4); // set to level 2

        this.model.evaluate(3);
        assertEquals(11_700, this.model.getScore());
        assertEquals(2, this.model.getLevel());
    }
    // CHECKSTYLE: MagicNumber ON
}
