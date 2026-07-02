package view.controller;

import controller.Controller;
import controller.levels.Levels;

import java.io.IOException;
import java.util.Arrays;

import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import view.scene.ViewScenes;
import view.utility.ViewUtils;

/**
 * Controller class for the settings of the game.
 */
public class SettingController extends ViewController {

    private Controller controller;

    @FXML
    private JFXComboBox<String> difficultBox;

    @FXML
    private JFXComboBox<String> levelsBox;

    /**
     * Setter of the game difficult according to the selected item of the
     * difficultBox.
     * 
     * @param event
     *            the event of the difficultBox.
     */
    @FXML
    public void difficultAction(final ActionEvent event) {
        if (this.difficultBox.getValue().equals(Difficult.EASY.getName())) {
            this.controller.setTimeToShot(Difficult.EASY.getTimeShot());
        } else if (this.difficultBox.getValue().equals(Difficult.MEDIUM.getName())) {
            this.controller.setTimeToShot(Difficult.MEDIUM.getTimeShot());
        } else if (this.difficultBox.getValue().equals(Difficult.HARD.getName())) {
            this.controller.setTimeToShot(Difficult.HARD.getTimeShot());
        }
    }

    /**
     * Setter of the game level according to the selected item of the levelBox.
     * 
     * @param event
     *            the event of the levelBox.
     */
    @FXML
    public void levelAction(final ActionEvent event) {
        if (this.levelsBox.getValue().equals(Levels.LEVEL_1.getName())) {
            this.controller.getLevel().setCurrentLevel(Levels.LEVEL_1);
        } else if (this.levelsBox.getValue().equals(Levels.LEVEL_2.getName())) {
            this.controller.getLevel().setCurrentLevel(Levels.LEVEL_2);
        } else if (this.levelsBox.getValue().equals(Levels.LEVEL_3.getName())) {
            this.controller.getLevel().setCurrentLevel(Levels.LEVEL_3);
        } else if (this.levelsBox.getValue().equals(Levels.LEVEL_4.getName())) {
            this.controller.getLevel().setCurrentLevel(Levels.LEVEL_4);
        }
    }

    /**
     * Allows to return to the menu.
     * 
     * @param event
     *            the {@link MouseEvent} of the exit image.
     */
    @FXML
    public void exitAction(final MouseEvent event) {
        try {
            ViewScenes.MENU.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(), controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Allows to open the instruction stage.
     * 
     * @param event
     *            the {@link ActionEvent} to pass to the instruction stage.
     */
    @FXML
    public void instructionAction(final ActionEvent event) {
        try {
            ViewScenes.ISTRUCTION.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                    controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void init(final Controller controller) {
        this.controller = controller;
        Arrays.asList(Levels.values()).forEach(l -> this.levelsBox.getItems().add(l.getName()));
        Arrays.asList(Difficult.values()).forEach(d -> this.difficultBox.getItems().add(d.getName()));

    }

}
