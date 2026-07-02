package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zombieversity.view.GameView;
import zombieversity.view.SceneSwapper;
import zombieversity.view.SceneSwapperImpl;

/**
 * Test HAS TO BE tested one by one.
 * Tests for scene swapping.
 *
 */
public class TestSceneSwapper extends ApplicationTest {

    private SceneSwapper swap;
    private Scene scene;
    private final AnchorPane pane = new AnchorPane();
    private static final int SCENE_WIDTH = 300;
    private static final int SCENE_HEIGHT = 300;
    private static final String GAME_VIEW = "gameview";
    private Stage stage;

    @Override
    public final void start(final Stage stage) throws Exception {
        this.stage = stage;
    }

    /**
     * Tests adding and removing scenes.
     */
    @Test
    public final void testAddandRemoveScene() {
        swap = new SceneSwapperImpl(this.stage);
        scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);

        Platform.runLater(() -> {
            /**
             * New object added, if were no before this become the main.
             */
            swap.addScene("test", scene);
            assertEquals(scene, swap.getCurrent());
            /**
             * If all scenes are removed, no scenes are displayed.
             */
            swap.removeScene("test");
            assertNull(swap.getCurrent());
        });

    }

    /**
     * Tests loading and swapping of scenes.
     */
    @Test
    public final void testLoadAndSwap() {
        swap = new SceneSwapperImpl(stage);
        final Scene first = new Scene(new AnchorPane(), SCENE_WIDTH, SCENE_HEIGHT);
        final Scene second = new Scene(new AnchorPane(), SCENE_WIDTH, SCENE_HEIGHT);

        Platform.runLater(() -> {
            swap.addScene("first", first);
            swap.addScene("second", second);
            swap.swapTo("second");
            assertEquals(second, swap.getCurrent());
            assertTrue(swap.loadFromFile(GAME_VIEW));
            assertTrue(swap.swapTo(GAME_VIEW));
            assertNotNull(swap.getCurrent());
        });
    }

    /**
     * Tests controller.
     */
    @Test
    public final void testController() {
        swap = new SceneSwapperImpl(stage);
        Platform.runLater(() -> {
            assertTrue(swap.loadFromFile("testFXML"));
            assertTrue(swap.loadFromFile(GAME_VIEW));
            assertTrue(swap.swapTo(GAME_VIEW));

            assertEquals(GameView.class, swap.getFXMLController(GAME_VIEW).get().getClass());
        });
    }
}
