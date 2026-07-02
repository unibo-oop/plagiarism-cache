package view.controller;

import controller.event.ButtonEventImpl;
import controller.utility.ButtonType;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import view.ViewImpl;
import view.ViewManagerImpl;
import view.utility.SceneFactory;

/**
 * Controller class for the MainMenuView file.
 *
 */
public class MainMenuViewController extends AbstractControllerFXML {

    @FXML private BorderPane contentPane;

    /**
     * Get root.
     */
    @Override
    public final Region getRoot() {
        return contentPane;
    }

    /**
     * Set text.
     */
    @Override
    public void setText() { }

    /**
     * Event method to open the NewGame view.
     */
    @FXML
    public void playButtonClick() {
        ViewManagerImpl.get().push(SceneFactory.createNewGameScene());
    }

    /**
     * Event method to open the Options view.
     */
    @FXML
    public void optionsButtonClick() {
        ViewManagerImpl.get().push(SceneFactory.createOptionScene());
    }

    /**
     * Event method to close the game.
     */
    @FXML
    public void exitButtonClick() {
        ViewManagerImpl.get().pop();
        ViewImpl.get().notifyEvent(new ButtonEventImpl(ButtonType.QUIT_GAME, ""));
    }

    /**
     * Event method to open the Help view.
     */
    @FXML
    public void helpButtonClick() {
        ViewManagerImpl.get().push(SceneFactory.createHelpScene());
    }

    /**
     * Event method to open the Credits view.
     */
    @FXML
    public void creditsButtonClick() {
        ViewManagerImpl.get().push(SceneFactory.createCreditScene());
    }

    /**
     * Event method to open the Leader board view.
     */
    @FXML
    public void btnLeaderboardClick() {
        ViewManagerImpl.get().push(SceneFactory.createLeaderboardScene());
    }
}
