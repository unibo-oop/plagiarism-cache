package starcatraz.application;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import starcatraz.controller.SettingsController;
import starcatraz.controller.StarcatrazController;
import starcatraz.controller.game.GameController;
import starcatraz.model.Achievements;
import starcatraz.model.AchievementsImpl;
import starcatraz.model.Settings;
import starcatraz.model.SettingsImpl;
import starcatraz.model.Statistics;
import starcatraz.model.StatisticsImpl;
import starcatraz.util.AppFXML;
import starcatraz.util.AppImage;
import starcatraz.util.FileReadWrite;

/**
 * Implementation of StarcatrazApp interface.
 */
public class StarcatrazAppImpl extends Application implements StarcatrazApp {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Settings settings;
    private Statistics statistics;
    private Achievements achievement;

    @Override
    public void start(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.settings = new SettingsImpl();
        this.statistics = new StatisticsImpl();
        this.achievement = new AchievementsImpl(statistics);
        FileReadWrite.readStatistics(statistics);
        loadSettings();
        setupPrimaryStage(this.settings.getResolutionWidth(), this.settings.getResolutionHeight());
        initRootLayout();
        showView(AppFXML.MENU_VIEW);
    }

    @Override
    public void setupPrimaryStage(final int width, final int height) {
        this.primaryStage.setTitle(APP_TITLE);
        this.primaryStage.getIcons().add(AppImage.ICON_APP.getImage());
        this.primaryStage.setWidth(width);
        this.primaryStage.setHeight(height);
        this.primaryStage.setMaxWidth(width);
        this.primaryStage.setMaxHeight(height);
        this.primaryStage.setFullScreen(true);
        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    }

    /**
     * Load and apply settings from file.
     */
    private void loadSettings() {
        FileReadWrite.readSettings(settings);
        SettingsController.setSound(this.settings.getSoundEffectsVolume());
        SettingsController.setMusic(this.settings.getMusicVolume());
    }

    @Override
    public void initRootLayout() {
        try {
            // Load root layout from fxml file
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(AppFXML.ROOT_LAYOUT));
            rootLayout = (BorderPane) loader.load();
            // Show the scene containing the root layout
            final Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showView(final String path) {
        try {
            // Load View
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            final AnchorPane view = (AnchorPane) loader.load();
            final StarcatrazController controller = loader.getController();
            controller.setStarcatrazApp(this);
            // Set View into the center of RootLayout
            rootLayout.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showGameView() {
        try {
            // Load GameView
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(AppFXML.GAME_VIEW));
            final AnchorPane gameView = (AnchorPane) loader.load();
            final GameController gameController = loader.getController();
            ((StarcatrazController) gameController).setStarcatrazApp(this);
            gameController.setStatistics(statistics);
            // Set GameView into the center of RootLayout
            rootLayout.setCenter(gameView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load game fonts.
     */
    private static void loadFonts() {
        Font.loadFont(StarcatrazAppImpl.class.getResourceAsStream("/fonts/SF Distant Galaxy.ttf"), 20.0);
        Font.loadFont(StarcatrazAppImpl.class.getResourceAsStream("/fonts/Xolonium.ttf"), 10.0);
    }

    @Override
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public Settings getSettings() {
        return this.settings;
    }

    @Override
    public Statistics getStatistics() {
        return this.statistics;
    }

    @Override
    public Achievements getAchievements() {
        return this.achievement;
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void main(final String[] args) throws IOException, URISyntaxException {
        loadFonts();
        launch(args);
    }
}
