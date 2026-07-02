package view.controller;

import java.io.IOException;
import java.util.Optional;


import controller.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.scene.ViewScenes;
import view.utility.ViewUtils;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

/**
 * Implementation of the view controller for the menu stage.
 */
public class MenuController extends ViewController {

    private Controller controller;

    @Override
    public final void init(final Controller controller) {
        this.controller = controller;

    }

    /**
     * This method allow to switch to the game world stage.
     * 
     * @param event
     *            the action event.
     * @throws IOException
     *            throw a new {@link IOException}.
     */
    public void playAction(final ActionEvent event) throws IOException {
        ViewScenes.LOADING.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                this.controller);
    }

    /**
     * This method allow to switch to the settings stage.
     * 
     * @param event
     *            the action event.
     * @throws IOException
     *          throw a new {@link IOException}.
     */
    public void settingsAction(final ActionEvent event) throws IOException {
        ViewScenes.SETTING.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                this.controller);

    }

    /**
     * This method allow to exit the GUI.
     * 
     * @param event
     *            the action event.
     * @throws IOException
     *          throw a new {@link IOException}.
     */
    public void exitAction(final ActionEvent event) throws IOException {
        final Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Requested");
        alert.setHeaderText("Are you really sure you want to quit ?");
        final ButtonType yes = new ButtonType("Yes", ButtonData.YES);
        final ButtonType no = new ButtonType("No", ButtonData.NO);
        alert.getButtonTypes().setAll(yes, no);
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes) {
            System.exit(0);
        } else if (result.get() == no) {
            alert.close();
        }

    }

}
