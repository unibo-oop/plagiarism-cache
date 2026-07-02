package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.enemy.AiMAnagerImpl;
import model.enemy.DummyCommmandGenerator;
import model.enemy.EnemyAi;

class TestAiManager {
    private static final int DEFAULT_COMMAND_NUMBER = 10;
    private final AiMAnagerImpl aiManager = new AiMAnagerImpl();
    private final List<EnemyAi> enemiesAi = new ArrayList<>();

    @Test
    void testCommandsGenerator() {
        enemiesAi.add(aiManager.getNewEnemyAI(new DummyCommmandGenerator()));
        aiManager.generateAiCommands();
        assertEquals(DEFAULT_COMMAND_NUMBER, enemiesAi.get(0).getCommandQueue().size());
        enemiesAi.get(0).getCommandQueue().poll();
        assertNotEquals(DEFAULT_COMMAND_NUMBER, enemiesAi.get(0).getCommandQueue().size());
        aiManager.generateAiCommands();
        assertEquals(DEFAULT_COMMAND_NUMBER - 1, enemiesAi.get(0).getCommandQueue().size());
        enemiesAi.forEach(e -> {
            while (!e.getCommandQueue().isEmpty()) {
                e.getCommandQueue().poll();
            }
        });
        assertEquals(0, enemiesAi.get(0).getCommandQueue().size());
        aiManager.generateAiCommands();
        assertEquals(DEFAULT_COMMAND_NUMBER, enemiesAi.get(0).getCommandQueue().size());
    }

    @Test
    void testStartStop() {
        enemiesAi.add(aiManager.getNewEnemyAI(new DummyCommmandGenerator()));
        enemiesAi.add(aiManager.getNewEnemyAI(new DummyCommmandGenerator()));
        enemiesAi.add(aiManager.getNewEnemyAI(new DummyCommmandGenerator()));
        aiManager.stopAll();
        aiManager.generateAiCommands();
        enemiesAi.forEach(ai -> {
            assertNotEquals(DEFAULT_COMMAND_NUMBER, ai.getCommandQueue().size());
        });
        aiManager.startAll();
        aiManager.generateAiCommands();
        enemiesAi.forEach(ai -> {
            assertEquals(DEFAULT_COMMAND_NUMBER, ai.getCommandQueue().size());
        });
    }

    @Test
    void testResetAll() {
        enemiesAi.add(aiManager.getNewEnemyAI(new DummyCommmandGenerator()));
        enemiesAi.add(aiManager.getNewEnemyAI(new DummyCommmandGenerator()));
        enemiesAi.add(aiManager.getNewEnemyAI(new DummyCommmandGenerator()));
        aiManager.resetAll();
        aiManager.generateAiCommands();
        enemiesAi.forEach(ai -> {
            assertNotEquals(DEFAULT_COMMAND_NUMBER, ai.getCommandQueue().size());
        });
    }
}
