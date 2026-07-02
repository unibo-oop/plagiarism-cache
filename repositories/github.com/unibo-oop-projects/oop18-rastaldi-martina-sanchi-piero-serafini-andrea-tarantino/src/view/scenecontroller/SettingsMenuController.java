package view.scenecontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import view.alert.AlertWindow;
import view.alert.AlertWindowImpl;
import view.settingsobserver.CharactersForWinObserver;
import view.settingsobserver.HedgeJumpingObserver;
import view.settingsobserver.MinotaurusHedgeJumpingObserver;
import view.settingsobserver.MinotaurusStepsObserver;
import view.settingsobserver.Observer;
import view.utilities.Scenes;

/**
 * Chiara Tarantino.
 * Controller for settings menu scene.
 *
 */
public class SettingsMenuController extends SceneControllerImpl {

    private final ObservableList<Integer> stepsValue = FXCollections.observableArrayList(8, 10, 12);
    private final ObservableList<Integer> numberCharacters = FXCollections.observableArrayList(1, 2, 3);
    private final List<Observer> observers = new ArrayList<>();

    @FXML
    private ComboBox<Integer> minotaurusSteps;
    @FXML
    private ToggleButton hedgeJumping;
    @FXML
    private ComboBox<Integer> charactersForWin;
    @FXML
    private ToggleButton minotaurusHedgeJumping;

    @FXML
    private void initialize() {
        this.minotaurusSteps.setItems(this.stepsValue);
        this.minotaurusSteps.setValue(this.getController().getMinotaurusSteps());
        this.minotaurusSteps.getStylesheets().add("/style/stylesheet.css");

        this.charactersForWin.setItems(this.numberCharacters);
        this.charactersForWin.setValue(this.getController().getCharactersForWin());
        this.charactersForWin.getStylesheets().add("/style/stylesheet.css");

        this.minotaurusHedgeJumping.setSelected(this.getController().isMinotaurusHedgeJumpingMode());
        this.hedgeJumping.setSelected(this.getController().isHedgeJumpingMode());

        // add observers
        this.observers.addAll(Arrays.asList(new MinotaurusStepsObserver(this.minotaurusSteps, this.getController()),
                new HedgeJumpingObserver(this.hedgeJumping, this.getController()),
                new CharactersForWinObserver(this.charactersForWin, this.getController()),
                new MinotaurusHedgeJumpingObserver(this.minotaurusHedgeJumping, this.getController())));

    }

    @FXML
    private void openPlayersMenu() throws IOException {
        // call the controller to update model fields
        this.observers.forEach(observer -> observer.updateSettings());
        this.getSceneLoader().load(Scenes.PLAYERSMENU);
    }

    @FXML
    private void openSettingsMenu() throws IOException {
        this.getSceneLoader().load(Scenes.SETTINGSMENU);
    }

    @FXML
    private void returnModalityMenu() throws IOException {
        final AlertWindow alert = new AlertWindowImpl("Back To Modality Menù",
                "Sei sicuro di voler tornare indietro?\nLe modifiche non salvate andranno perse!", "yesNo");
        if (alert.showAndWait().get().getButtonData() == ButtonData.YES) {
            this.getSceneLoader().load(Scenes.MODALITYMENU);
        }
    }

}
