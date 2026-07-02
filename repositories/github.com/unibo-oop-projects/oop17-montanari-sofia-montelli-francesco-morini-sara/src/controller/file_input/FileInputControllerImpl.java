package controller.file_input;

import controller.AbstractController;
import javafx.stage.Window;

/**
 * Basic implementation of {@link FileInputController}.
 */
public class FileInputControllerImpl extends AbstractController implements FileInputController {

    /**
     * The provided path for the player file.
     */
    private String playerPath = "";

    /**
     * The provided path for the statistics.
     */
    private String statisticsPath = "";

    @Override
    public final String getPromptedPlayerPath() {
        return playerPath;
    }

    @Override
    public final String getPromptedStatisticsFile() {
       return statisticsPath;
    }

    @Override
    public final void setPlayerPath(final String path) throws IllegalArgumentException {
        playerPath = path;
    }

    @Override
    public final void setStatisticsFile(final String path) throws IllegalArgumentException {
        statisticsPath = path;
    }

    @Override
    public final void setDefaultPath() {
        setPlayerPath(System.getProperty("user.home") + System.getProperty("file.separator") + "player.db");
        setStatisticsFile(System.getProperty("user.home") + System.getProperty("file.separator") + "stats.db");
    }

    @Override
    public final Window getStage() {
        return getMasterController().getPrimaryStage();
    }

    @Override
    public final void confirm() {
        getMasterController().setFilePath(playerPath, statisticsPath);
    }
}
