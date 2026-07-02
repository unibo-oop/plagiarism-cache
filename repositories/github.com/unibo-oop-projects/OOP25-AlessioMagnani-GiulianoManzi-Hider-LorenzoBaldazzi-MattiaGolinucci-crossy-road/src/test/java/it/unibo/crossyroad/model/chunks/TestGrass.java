package it.unibo.crossyroad.model.chunks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.crossyroad.model.api.Dimension;
import it.unibo.crossyroad.model.api.EntityType;
import it.unibo.crossyroad.model.api.Position;
import it.unibo.crossyroad.model.impl.chunks.Grass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestGrass {
    private static final int LOOPS = 100_000;
    private static final Position CHUNK_INITIAL_POSITION = new Position(0, 0);
    private static final Position PLAYER_START_POSITON = new Position(5, 8);
    private static final int MAX_OBSTACLES_EXPECTED = 16;
    private static final int MAX_PICKABLES_EXPECTED = 5;
    private Grass grass;

    @BeforeEach
    void setUp() {
        this.grass = new Grass(CHUNK_INITIAL_POSITION, new Dimension(10, 3));
    }

    @Test
    void testEntity() {
        assertEquals(EntityType.GRASS, grass.getEntityType());
    }

    @Test
    void testRandomGenerations() {
        for (int i = 0; i < LOOPS; i++) {
            this.grass = new Grass(CHUNK_INITIAL_POSITION, new Dimension(10, 3));
            final int nOfObstacles = this.grass.getObstacles().size();
            final int nOfPickables = this.grass.getPickables().size();

            assertFalse(this.grass.getPickables().stream().anyMatch(p -> p.getPosition().equals(PLAYER_START_POSITON)));
            assertFalse(this.grass.getObstacles().stream().anyMatch(o -> o.getPosition().equals(PLAYER_START_POSITON)));
            assertTrue(nOfObstacles > 0);
            assertTrue(nOfObstacles < MAX_OBSTACLES_EXPECTED);
            assertTrue(nOfPickables < MAX_PICKABLES_EXPECTED);
        }
    }

    @Test
    void testPositionablesCount() {
        final int positionables = this.grass.getObstacles().size() + this.grass.getPickables().size();
        assertEquals(positionables, this.grass.getPositionables().size());
    }
}
