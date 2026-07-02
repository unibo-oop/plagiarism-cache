package view;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.game.GameScene;
import view.menu.EndMenu;
import view.menu.LeaderboardMenu;
import view.menu.MainMenu;
import view.menu.OptionMenu;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class of the app, it contains the main stage.
 */
public class ViewImpl implements View {

    private static Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    private static final double HEIGHT_MULTIPLIER = 1.2;
    private static final double WIDTH_MULTIPLIER = 3;

    private Stage mainStage;
    private AbstractScene currentScene;
    private Map<GameScreen, AbstractScene> scenes;
    /**
     * constructor.
     * @param s main stage
     */
    public ViewImpl(final Stage s) {
        mainStage = s;
        scenes = new HashMap<>();
        scenes.put(GameScreen.GAME, new GameScene(this.getExecutionWidth(), this.getExecutionHeight()));
        scenes.put(GameScreen.MAINMENU, new MainMenu(this.getExecutionWidth(), this.getExecutionHeight(), this));
        scenes.put(GameScreen.OPTIONS, new OptionMenu(this.getExecutionWidth(), this.getExecutionHeight(), this));
        scenes.put(GameScreen.LEADERBOARD, new LeaderboardMenu(this.getExecutionWidth(), this.getExecutionHeight(), this));
        scenes.put(GameScreen.ENDMENU, new EndMenu(this.getExecutionWidth(), this.getExecutionHeight(), this));
    }
    /**
     * start the view and showing main menu.
     */
    public void startMenu() {
        mainStage.setScene(scenes.get(GameScreen.MAINMENU));
        mainStage.centerOnScreen();
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.show();
    }
    /**
     * change the scene to be displayed on mainStage.
     * @param s GameScreen selection.
     * @return currentScene displayed scene
     */
    public AbstractScene changeScene(final GameScreen s) {
        if (scenes.containsKey(s)) {
            currentScene = scenes.get(s);
            mainStage.setScene(currentScene);
        } else {
            throw new IllegalArgumentException();
        }
        return currentScene;
    }
    /**
     * Calculates the execution height depending on
     * the screen resolution.
     * @return The height of the game.
     */
    private double getExecutionHeight() {
        return screenBounds.getHeight() / HEIGHT_MULTIPLIER;
    }

    /**
     * Calculates the execution width depending on
     * the screen resolution.
     * @return The width of the game.
     */
    private double getExecutionWidth() {
        return screenBounds.getWidth() / WIDTH_MULTIPLIER;
    }


    /**
     * screens of the app.
     */
    public enum GameScreen {
        GAME, MAINMENU, OPTIONS, LEADERBOARD, ENDMENU
    }
}
