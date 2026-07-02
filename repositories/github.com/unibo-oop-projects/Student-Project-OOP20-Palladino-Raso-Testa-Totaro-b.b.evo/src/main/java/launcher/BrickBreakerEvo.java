package launcher;

import java.io.File;
import java.io.IOException;

import controller.utilities.IOSettings;
import javafx.application.Application;
import javafx.stage.Stage;
import model.settings.GameSettingsBuilderImpl;
import model.settings.SettingLevelManager;
import model.utilities.ScreenUtilities;
import resource.routing.PersonalStyle;
import view.PersonalViews;
import view.SceneLoader;

/**
 * BRICK-BREAKER-EVO MAIN.
 * Stage creation.
 *
 */
public class BrickBreakerEvo extends Application {

    /**
     * User's home folder.
     */
    public static final String HOME = System.getProperty("user.home");

    /**
    * identifies how the operating system separates files.
    */
    public static final String SEP = System.getProperty("file.separator");

    /**
     * Game folder destination.
     */
    public static final String MAIN_FOLDER = HOME + SEP + ".BrickBreakerEvo";

    /**
     * Folder where levels are saved.
     */
    public static final String LEVEL_FOLDER = MAIN_FOLDER + SEP + "Levels";

    /**
     * Folder where settings are saved.
     */
    public static final String SETTINGS_FOLDER = MAIN_FOLDER + SEP + "Settings" + SEP;

    /**
     * 
     */
    public static final String SETTING_LEVEL = SETTINGS_FOLDER + SEP + "loadLevel";

    /**
     * Folder where maps are saved.
     */
    private static final String MAPS_FOLDER = MAIN_FOLDER + SEP + "Maps";

    /**
     * Folder where Leaderboards are saved.
     */
    private static final String LEADERBOARDS_FOLDER = MAIN_FOLDER + SEP + "Leaderboards" + SEP;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {

        SceneLoader.switchScene(primaryStage, 
                PersonalViews.SCENE_MAIN_MENU.getURL(), 
                PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                ScreenUtilities.SCREEN_WIDTH, 
                ScreenUtilities.SCREEN_HEIGHT,
                PersonalStyle.DEFAULT_STYLE.getStylePath());

        primaryStage.setMinWidth(ScreenUtilities.MIN_RESIZE_WIDTH);
        primaryStage.setMinHeight(ScreenUtilities.MIN_RESIZE_HEIGHT);

    }

    /**
     * initializes the game.
     * @param args unused.
     * @throws IOException - common input/output exception.
     */
    public static void main(final String[] args) throws IOException {
        initialization();
        launch();
    }

    /**
     * 
     * if not present, create the folder to keep the game files.
     */
    private static void initialization() throws IOException {

        if (new File(BrickBreakerEvo.MAIN_FOLDER).mkdirs()) {
            System.out.println("Main Folder successfully created");
        }
        if (new File(BrickBreakerEvo.LEVEL_FOLDER).mkdirs()) {
            System.out.println("Level Folder successfully created");
        }
        if (!new File(BrickBreakerEvo.SETTINGS_FOLDER + "settings.json").exists()
            && new File(BrickBreakerEvo.SETTINGS_FOLDER).mkdirs()
            && new File(BrickBreakerEvo.SETTINGS_FOLDER + "settings.json").createNewFile()
            && new File(BrickBreakerEvo.SETTINGS_FOLDER + "loadLevel").createNewFile()) {
                System.out.println("Settings Folder e json successfully created");
                IOSettings.printInJsonFormat(SETTINGS_FOLDER + "settings.json", 
                                             new GameSettingsBuilderImpl().defaultSettings().build());
                SettingLevelManager.init();
        }

        if (new File(BrickBreakerEvo.MAPS_FOLDER).mkdirs()) {
            System.out.println("Maps Folder successfully created");
        }

        if (new File(BrickBreakerEvo.LEADERBOARDS_FOLDER).mkdirs()
                && new File(BrickBreakerEvo.LEADERBOARDS_FOLDER + "ranking.json").createNewFile()) {
            System.out.println("LeaderBoards Folder e json successfully created");
        //new MainMenuView(TITLE).show();
        }
    }
}
