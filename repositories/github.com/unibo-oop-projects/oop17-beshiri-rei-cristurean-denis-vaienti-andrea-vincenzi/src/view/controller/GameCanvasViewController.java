package view.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import view.DrawerManager;
import view.DrawerManagerImpl;
import view.ViewImpl;

/**
 * Controller class for the HelpView file.
 */
public class GameCanvasViewController extends AbstractControllerFXML {

    @FXML private Pane rootGamePane;
    @FXML private Canvas roomCanvas;
    @FXML private Canvas lifesCanvas;
    @FXML private Canvas elapsedTimeCanvas; 

    /**
     * Initialize method that sets the canvas drawer.
     */
    @FXML
    public void initialize() {
        final DrawerManager drawer = new DrawerManagerImpl(roomCanvas, elapsedTimeCanvas, lifesCanvas);
        ViewImpl.get().setDrawer(drawer);
    }

    /**
     * Get root of scene.
     */
    @Override
    public final Region getRoot() {
        return rootGamePane;
    }

    /**
     * Set text.
     */
    @Override
    public void setText() { }
}
