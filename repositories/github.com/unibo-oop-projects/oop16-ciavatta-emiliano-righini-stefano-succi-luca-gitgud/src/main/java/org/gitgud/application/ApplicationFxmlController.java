package org.gitgud.application;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;

/**
 *
 */
public class ApplicationFxmlController implements ApplicationView {

    private ApplicationController ctrl;

    @FXML
    private MenuItem miOpen;
    @FXML
    private MenuItem miClone;
    @FXML
    private MenuItem miInit;
    @FXML
    private ProgressBar pbTaskProgress;

    private final Map<MenuItem, String> mapper = new HashMap<>();

    @Override
    public void attachController(final ApplicationController ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * The documentation for this method is not necessary.
     */
    @FXML
    public void initialize() {
        mapper.put(miOpen, "open");
        mapper.put(miClone, "clone");
        mapper.put(miInit, "init");
    }

    @Override
    public void resetTaskProgress() {
        pbTaskProgress.setProgress(0.0);
    }

    @Override
    public void setTaskProgress(final double progress) {
        pbTaskProgress.setProgress(progress);
    }

    @FXML
    private void miAboutOnAction(final ActionEvent event) { // NOPMD
        ctrl.openAboutBox();
    }

    @FXML
    private void miQuitOnAction(final ActionEvent event) { // NOPMD
        ctrl.quit();
    }

    @FXML
    private void openRepositoryBox(final ActionEvent event) { // NOPMD
        ctrl.openRepositoryBox(mapper.get(event.getSource()));
    }

}
