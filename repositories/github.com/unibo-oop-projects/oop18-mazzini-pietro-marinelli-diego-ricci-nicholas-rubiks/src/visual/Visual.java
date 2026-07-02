package visual;

import cubecontroller.CubeController;
import cubecontroller.CubeDimensions;
import cubecontroller.CubeFactory;
import cubecontroller.GameMode;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

/**
 * This class is intended to be a scene builder that creates the scene
 * using the cube dimension and the game mode.
 */
public class Visual {

    private final Scene scene;
    private static final double SCENE_DIMENSION = Screen.getPrimary().getVisualBounds().getHeight()
            <= Screen.getPrimary().getVisualBounds().getWidth() ? Screen.getPrimary().getVisualBounds().getHeight() / 2
                    : Screen.getPrimary().getVisualBounds().getWidth() / 2;

    /**
     * This constructor builds the scene using parameters to set the scene behavior.
     * @param dim - The cube dimension.
     * @param mode - The game mode.
     */
    public Visual(final CubeDimensions dim, final GameMode mode) {
        final CubeFactory cubefactory = new CubeFactory();
        final CubeController cube = cubefactory.getCube(dim, mode);
        final Group root = new Group();
        final Group cubeGroup = cube.getVisualCube().getCubeGroup();
        final Pane pane = new BorderPane(cubeGroup);
        root.getChildren().add(pane);
        turnOffPickOnBoundsFor(root);
        scene = new Scene(root, SCENE_DIMENSION, SCENE_DIMENSION, true);
        pane.prefWidthProperty().bind(scene.widthProperty());
        pane.prefHeightProperty().bind(scene.heightProperty());
        scene.setFill(Color.SILVER);
        scene.setCamera(new PerspectiveCamera());
        final MouseControl mouse = new MouseControl(cubeGroup, scene);
        mouse.initMouseControl();
        final KeyBoardControl keyboard = new KeyBoardControl(mode, scene, cube);
        keyboard.initKeyBoardControl();
    }

    //This method is a bit strange but is a little hack to achieve a click on the cube.
    private void turnOffPickOnBoundsFor(final Node n) {
        n.setPickOnBounds(false);
        if (n instanceof Parent) {
            for (final Node c : ((Parent) n).getChildrenUnmodifiable()) {
                turnOffPickOnBoundsFor(c);
            }
        }
    }

    /**
     * Scene getter.
     * @return The crafted scene.
     */
    public Scene getScene() {
        return this.scene;
    }

}
