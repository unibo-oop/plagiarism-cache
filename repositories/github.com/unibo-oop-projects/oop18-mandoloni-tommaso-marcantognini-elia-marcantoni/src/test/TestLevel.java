package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectType;
import ballblast.model.levels.BasicLevel;
import ballblast.model.levels.GameStatus;
import ballblast.model.levels.Level;
import ballblast.model.levels.SinglePlayerDecorator;
import ballblast.model.levels.SurvivalLevelDecorator;

/**
 * Junit test for {@link Level}.
 */
class TestLevel {
    private static final double SAMPLE_ELAPSED = 0.1;

    /**
     * Test the basic logic inside the {@link BasicLevel}.
     */
    @Test
    public void testBasicLevel() {
        final Level level = new BasicLevel();
        assertTrue(level.getGameObjectManager().getGameObjects().isEmpty());
        level.start();
        level.update(SAMPLE_ELAPSED);
        assertFalse(level.getGameObjectManager().getGameObjects().isEmpty());
    }

    /**
     * Test the {@link SinglePlayerDecorator}.
     */
    @Test
    public void testSinglePlayerLevel() {
        final Level level = new SinglePlayerDecorator(new BasicLevel());
        assertSame(level.getGameStatus(), GameStatus.PAUSE);
        assertTrue(level.getGameObjectManager().getGameObjects().isEmpty());
        level.start();
        level.update(SAMPLE_ELAPSED);
        assertFalse(level.getGameObjectManager().getGameObjects().isEmpty());
        assertTrue(this.findPlayer(level).isPresent());
        assertSame(level.getGameStatus(), GameStatus.RUNNING);
        this.findPlayer(level).get().destroy();
        level.update(SAMPLE_ELAPSED);
        assertSame(level.getGameStatus(), GameStatus.OVER);
    }

    /**
     * Test the {@link SurvivalDecorator}.
     */
    @Test
    public void testSurvivalLevel() {
        final Level level = new SurvivalLevelDecorator(new BasicLevel());
        level.start();
        final int enableTime = 20;
        final long oldCount = this.getCollidablesCount(level);
        level.update(SAMPLE_ELAPSED); //Spawn the Ball.
        assertFalse(this.getCollidablesCount(level) > oldCount);
        level.update(SAMPLE_ELAPSED); //Add the Ball to the GameObjectManager's list.
        assertTrue(this.findBall(level).isPresent());
        level.update(SAMPLE_ELAPSED * enableTime);
        assertTrue(this.findBall(level).isPresent());
        assertTrue(this.getCollidablesCount(level) > oldCount); //Now the Ball is moving.
    }

    /**
     * Test if the game ends correctly after the destruction of the {@link Player}.
     */
    @Test
    public void testGameOver() {
        final Level level = new SinglePlayerDecorator(new BasicLevel());
        assertSame(level.getGameStatus(), GameStatus.PAUSE);
        assertTrue(level.getGameObjectManager().getGameObjects().isEmpty());
        level.start();
        assertFalse(level.getGameObjectManager().getGameObjects().isEmpty());
        assertTrue(this.findPlayer(level).isPresent());
        this.findPlayer(level).get().destroy();
        level.update(SAMPLE_ELAPSED);
        assertSame(level.getGameStatus(), GameStatus.OVER);
    }

    private Optional<GameObject> findPlayer(final Level level) {
        return level.getGameObjectManager().getGameObjects().stream()
                .filter(g -> g.getType() == GameObjectType.PLAYER).findFirst();
    }

    private Optional<GameObject> findBall(final Level level) {
        return level.getGameObjectManager().getGameObjects().stream()
            .filter(g -> g.getType() == GameObjectType.BALL).findFirst();
    }

    private long getCollidablesCount(final Level level) {
        return level.getCollisionManager().getCollidables().stream().count();
    }
}

