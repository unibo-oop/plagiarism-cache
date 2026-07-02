package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ballblast.controller.Controller;
import ballblast.controller.SimpleGameLoop;
import ballblast.model.Model;
import ballblast.model.ModelImpl;
import ballblast.view.View;
import ballblast.view.scenes.GameScenes;

/**
 * JUnit test for GameLoop.
 */
public class TestGameLoop {

    private static final int FPS = 60;

    private final Model testModel = new ModelImpl();
    private final TestView testView = new TestView();
    private final SimpleGameLoop gameLoop = new SimpleGameLoop(testModel, testView, FPS);

    /**
     * Starts the gameLoop.
     */
    @Before
    public void initializeEnv() {
        this.testModel.startSurvival();
        this.gameLoop.start();
    }

    /**
     * Tests GameLoop start.
     */
    @Test
    public void testStart() {
        assertTrue(this.gameLoop.isAlive());
    }

    /**
     * Tests GameLoop update.
     */
    @Test
    public void testLoop() {
        final int previousCounterValue = this.testView.getCounter();
        this.waitOneSecond();
        assertTrue(this.testView.getCounter() > previousCounterValue);
    }

    /**
     * Tests GameLoop pause feature.
     */
    @Test
    public void testPause() {
        this.waitOneSecond();
        final int previousValue = this.testView.getCounter();
        this.gameLoop.pause();
        this.waitOneSecond();
        assertEquals(previousValue, this.testView.getCounter());
    }

    /**
     * Tests GameLoop resume feature.
     */
    @Test
    public void testResume() {
        this.waitOneSecond();
        final int previousValue = this.testView.getCounter();
        this.gameLoop.pause();
        this.gameLoop.resumeLoop(); 
        this.waitOneSecond();
        assertTrue(previousValue < this.testView.getCounter());
    }

    private class TestView implements View {

        private int counter;

        @Override
        public void launch(final Controller controller) {
        }

        @Override
        public void render() {
            this.counter++;
        }

        private int getCounter() {
            return this.counter;
        }

        @Override
        public void loadScene(final GameScenes scene) {
        }

        @Override
        public void setGameOver(final boolean gameover) {
            // TODO Auto-generated method stub
        }

    }

    private void waitOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
