package it.unibo.isaccoop.test.model.enemy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import it.unibo.isaccoop.core.GameEngine;
import it.unibo.isaccoop.core.GameEngineImpl;
import it.unibo.isaccoop.model.creator.ConcreteCreatorFactory;
import it.unibo.isaccoop.model.room.LevelController;
import it.unibo.isaccoop.model.room.LevelControllerImpl;

/**
 * TestEnemy class to test enemy behavior.
 * */
class TestEnemy {

    private static final int MAX_ROOMS = 5;

    private final GameEngine gameEngine = new GameEngineImpl();
    private final LevelController gameState = new LevelControllerImpl(MAX_ROOMS, gameEngine);

    /**
     * TestEnemy Constructor.
     * */
    TestEnemy() {
        this.moveToStandardRoom();
    }

    /**
     * Test enemy remove.
     * */
    @Test
    void testEnemyRemove() {
        final var ai = gameState.getCurrentLevel().getCurrentRoom().getRoomAI().get();
        gameState.getCurrentLevel().getCurrentRoom().getEnemies().ifPresent(l -> l.forEach(e -> ai.remove(e)));
        gameState.getCurrentLevel().getCurrentRoom().getEnemies().ifPresent(l -> assertEquals(0, l.size()));
    }

    /**
     * Test enemy creation.
     * */
    @Test
    void testEnemyCreation() {
        assertNotEquals(0, new  ConcreteCreatorFactory().createEnemies().create());
    }

    private void moveToStandardRoom() {
        gameState.getCurrentLevel().moveToNextRoom();
        gameState.getCurrentLevel().moveToNextRoom();
        gameState.getCurrentLevel().moveToNextRoom();
    }
}
