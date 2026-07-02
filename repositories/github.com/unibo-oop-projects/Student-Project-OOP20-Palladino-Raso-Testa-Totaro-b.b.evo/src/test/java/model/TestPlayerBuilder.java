package model;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.leaderboard.PlayerBuilderImpl;
import model.leaderboard.PlayerImpl;

class TestPlayerBuilder {

    private static final String ALIAS = "Alex00";
    private static final int SCORE = 1234;
    private static final int UNDER_SCORE = -1500;
    private static final int LIFE = 3;
    private static final int COFFICENT = 10;
    private PlayerBuilderImpl builder;

    @BeforeEach
    void initBuild() {
        this.builder = new PlayerBuilderImpl();
    }

    @Test
    void testCorrectBuild() {
        assertDoesNotThrow(() -> {
            this.builder.alias(ALIAS);
            this.builder.score(SCORE);
            this.builder.life(LIFE);
            this.builder.maxLife(LIFE);
            final var playerBuilder = this.builder.build();
            final PlayerImpl player = new PlayerImpl(ALIAS, SCORE, LIFE, LIFE);
            assertEquals(player, playerBuilder);
        });
    }

    @Test
    void testUncorretBuildOverFlowLife() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.builder.score(SCORE);
            this.builder.life(LIFE + COFFICENT);
            this.builder.build();
        });
    }

    @Test
    void testUncorretBuildUnderFlowLife() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.builder.score(SCORE);
            this.builder.life(LIFE - COFFICENT);
            this.builder.build();
        });
    }

    @Test
    void testUncorretBuildUnderFlowScore() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.builder.score(UNDER_SCORE);
            this.builder.life(LIFE);
            this.builder.build();
        });
    }

    @Test
    void testNullAlias() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.builder.score(UNDER_SCORE);
            this.builder.life(LIFE);
            this.builder.build();
        });
    }

}
