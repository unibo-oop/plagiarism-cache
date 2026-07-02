package it.unibo.arkanoid.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import it.unibo.arkanoid.engine.GameLoop;
import it.unibo.arkanoid.engine.GameLoopImpl;
import it.unibo.arkanoid.model.Model;
import it.unibo.arkanoid.model.ModelImpl;
import it.unibo.arkanoid.view.View;
import it.unibo.arkanoid.view.ViewImpl;

/**
 * Class to test the {@link GameLoop}.
 *
 */
public class TestGameLoop {

    private volatile int valueTest; //NOPMD
    private final Model modelTest = new ModelImpl();
    private final View viewTest = new ViewImpl();

    /**
     * Test start and stop of GameLoop engine.
     */
    @Test
    public void test() {
        this.valueTest = 0;
        final GameLoop gameLoopTest = new GameLoopImpl(viewTest, modelTest);
        gameLoopTest.startGameLoop();

        try {
            Thread.sleep(1000);
        } catch (final InterruptedException e) {
            Assert.assertTrue("Interrupted", false);
        }

        Assert.assertTrue("Render method is not called", this.valueTest != 0);
        gameLoopTest.stopGame();

        final int lastValueTest = this.valueTest;
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException e) {
            Assert.assertTrue("Interrupeted", false);
        }

        System.out.println("At the end, when the GameLoop is stopped, the lastValueTest is: " + lastValueTest
                + " and now is " + this.valueTest);
        Assert.assertTrue("GameLoop not stopped", this.valueTest <= lastValueTest);

    }

}
