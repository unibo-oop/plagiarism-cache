package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.battle.api.Battle;
import it.unibo.jurassiko.model.battle.impl.BattleImpl;

/**
 * Test for the Battle Class.
 */
class TestBattle {

    private static final int MAXNUMBER_DICE = 3;
    private static final int TROOPS_ATTACK = 2;
    private static final int TROOPS_DEFENCE = 2;
    private Battle battle;
    private Pair<Integer, Integer> dinoDeaths;

    /**
     * before each test.
     */
    @BeforeEach
    void init() {
        this.battle = new BattleImpl();
    }

    @Test
    void testBattle() {
        for (int i = 1; i < MAXNUMBER_DICE; i++) {
            for (int j = 1; j < MAXNUMBER_DICE; j++) {
                if (i < TROOPS_ATTACK && j < TROOPS_DEFENCE) {
                    this.dinoDeaths = this.battle.attack(TROOPS_ATTACK, TROOPS_DEFENCE, i, j);
                    assertTrue(this.dinoDeaths.x() >= 0 && this.dinoDeaths.x() < i + 1 && this.dinoDeaths.y() >= 0
                            && this.dinoDeaths.y() < j + 1);
                    assertTrue(this.dinoDeaths.x() > 0 || this.dinoDeaths.y() > 0);
                }
            }
        }
    }

    @Test
    void testCalculateDino() {
        assertEquals(3, battle.calculateDino(4, true));
        assertEquals(3, battle.calculateDino(4, false));
        assertEquals(2, battle.calculateDino(3, true));
        assertEquals(3, battle.calculateDino(3, false));
        assertEquals(1, battle.calculateDino(2, true));
        assertEquals(2, battle.calculateDino(2, false));
        assertEquals(0, battle.calculateDino(1, true));
        assertEquals(1, battle.calculateDino(1, false));
    }
}
