package view.controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.stage.Stage;
import view.loader.stagefactory.StageManager;
import view.mediator.ControllerMediator;
import view.rendering.UpdateViewStrategyImpl;

public class MenuBarController {

    @FXML
    private Stage primaryStage;
    @FXML
    private Menu recent;
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateViewStrategyImpl.class);

    /**
     * @throws IOException if controlPanelController does not load wizard.
     * sets the configuration file selected by the user
     */
    public void openFile() throws IOException {
        ControllerMediator.getInstance().controlPanelControllerOperateOn();
    }

    /**
     * exit the simulation.
     */
    @FXML
    public void quit() {
        Platform.exit();
        System.exit(0);
    }

    public void doSomething() {

    }

    /**
     * to open the application manual.
     * @throws IOException 
     */
    public void openManual() throws IOException {
        final StageManager manager = new StageManager();
        manager.getScenefactory().manualLoader().load();
    }

    /**
     * @throws IOException if does not load.
     * to open about documents.
     *
     * @throws IOException
     */
    public void openAbout() throws IOException {
        final StageManager manager = new StageManager();
        manager.getScenefactory().aboutLoader().load();
    }

}
