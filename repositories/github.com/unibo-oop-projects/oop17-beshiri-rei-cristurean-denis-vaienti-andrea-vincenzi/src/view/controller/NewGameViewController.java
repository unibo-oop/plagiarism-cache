package view.controller;

import controller.event.ButtonEventImpl;
import controller.utility.ButtonType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import view.ViewImpl;
import view.ViewManagerImpl;
import view.utility.SceneFactory;

/**
 * Controller class for the NewGameView file.
 *
 */
public class NewGameViewController extends AbstractControllerFXML {

    @FXML private BorderPane contentPane;
    @FXML private TextField txtNickname;

    /**
     * Get root.
     */
    @Override
    public final Region getRoot() {
        return this.contentPane;
    }

    /**
     * Set text.
     */
    @Override
    public void setText() { }

    /**
     * Event method to open the Canvas view in which the game will start.
     */
    @FXML
    public void btnPlayClick() {
        if (!txtNickname.getText().isEmpty()) {
            ViewManagerImpl.get().pop();
            ViewManagerImpl.get().push(SceneFactory.createGameScene());
            ViewImpl.get().notifyEvent(new ButtonEventImpl(ButtonType.START_GAME, txtNickname.getText()));
        }
    }

    /**
     * Event method for the back button.
     */
    @FXML
    public void btnBackClick() {
        ViewManagerImpl.get().pop();
    }
}
