package controller.file_input;

import controller.SpecificController;
import javafx.stage.Window;

/**
 * Controller for {@link PathInputUI}.
 */
public interface FileInputController extends SpecificController {

    /**
     * Getter for the player file path.
     * @return the path
     */
    String getPromptedPlayerPath();

    /**
     * Getter for the statistics file path.
     * @return the path
     */
    String getPromptedStatisticsFile();

    /**
     * Set the player path.
     * @param path the path to set
     * @throws IllegalArgumentException if the provided string is not a path to a file
     */
    void setPlayerPath(String path) throws IllegalArgumentException;

    /**
     * Set the statics path.
     * @param path the path to set
     * @throws IllegalArgumentException if the provided string is not a path to a file
     */
    void setStatisticsFile(String path) throws IllegalArgumentException;

    /**
     * Sets both path to a default value.
     */
    void setDefaultPath();

    /**
     * @return the {@link Window} which contains the associated UI.
     */
    Window getStage();

    /**
     * Confirms the prompted data.
     */
    void confirm();
}
