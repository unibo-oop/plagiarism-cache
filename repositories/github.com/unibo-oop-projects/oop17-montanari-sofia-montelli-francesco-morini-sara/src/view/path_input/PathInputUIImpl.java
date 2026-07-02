package view.path_input;

import controller.file_input.FileInputController;
import controller.file_input.FileInputControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

/**
 * Implementation of {@link PathInputUI} using JavaFX.
 */
public final class PathInputUIImpl implements PathInputUI {
    /**
     * a label for showing the chosen path.
     */
    @FXML private Label playerPath;

    /**
     * A button for showing a {@link FileChooser}.
     */
    @FXML private Button choosePlayerPath;

    /**
     *  a label for showing the chosen path.
     */
    @FXML private Label statisticsPath;

    /**
     * A  button for showing a {@link FileChooser}.
     */
    @FXML private Button chooseStatisticsPath;

    /**
     * Confirm the data with some default value.
     */
    @FXML private Button buttonDefault;

    /**
     * Confirms the prompted data.
     */
    @FXML private Button buttonConfirm;

    private FileInputController controller;

    @Override
    public void initialize() {
        controller = new FileInputControllerImpl();
        update();
    }

    @Override
    public void reset() {
        controller.setPlayerPath("");
        controller.setStatisticsFile("");
        update();
    }

    @Override
    public void update() {
        if (controller.getPromptedPlayerPath().equals("") || controller.getPromptedStatisticsFile().equals("")) {
            disableConfirm();
        } else {
            enableConfirm();
        }
        playerPath.setText(controller.getPromptedPlayerPath().length() > 0 ? controller.getPromptedPlayerPath() : "Please, provide a path for the players file");
        statisticsPath.setText(controller.getPromptedStatisticsFile().length() > 0 ? controller.getPromptedStatisticsFile() : "Please, provide a path the statistics file");
    }

    @FXML
    @Override
    public void confirm() {
        controller.confirm();
    }

    @Override
    public void enableConfirm() {
        buttonConfirm.setDisable(false);
    }

    @Override
    public void disableConfirm() {
       buttonConfirm.setDisable(true);
    }

    @FXML
    @Override
    public  void choosePlayerFile() {
        controller.setPlayerPath(getFileWithChoser("Open player file"));
        update();
    }

    @FXML
    @Override
    public void chooseStatisticsFile() {
        controller.setStatisticsFile(getFileWithChoser("Open statistics file"));
        update();
    }

    @FXML
    @Override
    public void useDefault() {
        controller.setDefaultPath();
        update();
    }
    /**
     * Prompts a {@link FileChooser} for select the desired file.
     * @param choserTitle the title to display
     * @return the absolute path of the chosen file
     */
    private String getFileWithChoser(final String choserTitle) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(choserTitle);
        return fileChooser.showOpenDialog(controller.getStage()).getAbsolutePath();
    }

}
