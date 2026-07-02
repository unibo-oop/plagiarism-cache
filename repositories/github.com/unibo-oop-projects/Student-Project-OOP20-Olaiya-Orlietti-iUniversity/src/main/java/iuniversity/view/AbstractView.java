package iuniversity.view;

import iuniversity.controller.Controller;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public abstract class AbstractView implements FXView {

    private Stage stage;
    private Controller controller;

    /**
     * {@inheritDoc}
     */
    @Override
    public Stage getStage() {
        return this.stage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStage(final Stage stage) {
        this.stage = stage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getController() {
        return this.controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
    }

    private void showAlert(final Alert.AlertType type, final String message) {
        new Alert(type, message).showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showErrorMessage(final String message) {
        this.showAlert(Alert.AlertType.ERROR, message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showInfoMessage(final String message) {
        this.showAlert(Alert.AlertType.INFORMATION, message);
    }

}
