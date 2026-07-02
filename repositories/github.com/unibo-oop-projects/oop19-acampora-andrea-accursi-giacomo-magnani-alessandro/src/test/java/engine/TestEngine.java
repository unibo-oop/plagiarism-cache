package engine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import bubbleshooter.controller.Controller;
import bubbleshooter.controller.engine.BasicGameLoop;
import bubbleshooter.controller.engine.GameLoop;
import bubbleshooter.model.Model;
import bubbleshooter.model.ModelImpl;
import bubbleshooter.view.View;
import bubbleshooter.view.scene.FXMLPath;

/**
 * JUnit Test class to test the {@link GameLoop} of the Game.
 */
public class TestEngine {

    private final Model testModel = new ModelImpl();
    private final View testView = new TestView();
    private final GameLoop engine = new BasicGameLoop(testView, testModel);

    /**
     * Testing the starting of the main loop of the Engine.
     */
    @Test
    public final void testStartingLoop() {
        this.engine.startLoop();
        this.testModel.startBasicGame();
        assertFalse(this.engine.isStopped());
    }

   /**
     * Testing the pause of the engine.
     */
    @Test
    public final void testPause() {
        this.engine.startLoop();
        this.testModel.startBasicGame();
        assertFalse(this.engine.isStopped());
        this.engine.pauseLoop();
        assertTrue(this.engine.isPaused());
    }

    /**
     * Testing if the engine wakes up from the pause and restart the Loop.
     */
    @Test
    public final void testResumeLoop() {
        this.engine.startLoop();
        this.testModel.startBasicGame();
        assertFalse(this.engine.isStopped());
        this.engine.pauseLoop();
        assertTrue(this.engine.isPaused()); 
        this.engine.resumeLoop();
        assertFalse(this.engine.isPaused());
    }

    /**
     * Anonymous class used only to Test the GameEngine and avoid all the initializations.
     */
    class TestView implements View {

        @Override
        public void launch(final Controller controller) {
        }

        @Override
        public void loadScene(final FXMLPath scene) {
        }

        @Override
        public void showGameOver() {
        }

        @Override
        public void update() {
        }
    }
}
