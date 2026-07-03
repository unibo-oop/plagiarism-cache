package view.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import view.ViewImpl;
import view.render.CanvasDrawer;
import view.render.CanvasDrawerImpl;

/**
 * Controller of the GameScreen FXML file.
 */
public class GameSceneControllerImpl extends AbstractControllerFXML implements GameSceneController {

    private static final int MAXIMIZED_MAP_WIDTH = 200;
    private static final int MINIMIZED_MAP_WIDTH = 100;
    @FXML private FlowPane gameRoot;
    @FXML private Canvas roomCanvas;
    @FXML private Canvas mapCanvas;
    private CanvasDrawer drawer;

    @FXML
    private void initialize() {
        drawer = new CanvasDrawerImpl(roomCanvas, mapCanvas);
        ViewImpl.get().setDrawerReference(drawer);
    }

    @Override
    public Region getRoot() {
        return gameRoot;
    }

    @Override
    public void resizeMap(final boolean magnify) {
        if (magnify) {
            mapCanvas.setWidth(MAXIMIZED_MAP_WIDTH);
            mapCanvas.setHeight(MAXIMIZED_MAP_WIDTH);
            mapCanvas.setOpacity(1);
        } else {
            mapCanvas.setWidth(MINIMIZED_MAP_WIDTH);
            mapCanvas.setHeight(MINIMIZED_MAP_WIDTH);
            mapCanvas.setOpacity(0.5);
        }
        drawer.draw();
    }
}
