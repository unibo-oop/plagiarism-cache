package controller.scene;

import controller.game.GameStateImpl;
import controller.utilities.GUILayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.mapeditor.Level;
import model.mapeditor.LevelManager;
import model.settings.SettingLevel.SettingLevelBuilder;
import model.settings.SettingLevelManager;
import model.utilities.CheckCustomAlert;
import model.utilities.ScreenUtilities;
import resource.routing.PersonalStyle;
import view.PersonalViews;

/**
 * Manage the CreativeMode scene.
 * Used to create custom level and play it.
 */
public class ControllerCreativeMode implements GUILayout {

    /**
     * Dimension of Editor mode width.
     */
    private static final double EDITOR_MODE_WIDTH = 1200;

    /**
     * Dimension of Editor mode height.
     */
    private static final double EDITOR_MODE_HEIGHT = 950;

    private Level currentLevel;

    @FXML
    private AnchorPane panel;

    @FXML
    private Button menuBtn;

    @FXML
    private Button builderBtn;

    @FXML
    private Button playBtn;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox levelContainer;

    @FXML
    private Label levelSelected;


    /**
     * 
     * Initialize the scene loading the button and method update to refresh the view.
     */
    @FXML
    public void initialize() {
        this.loadListener();
        this.update();
    }

    /**
     * Use to update the view of custom level.
     * Reads the new generated levels.
     */
    public void update() {
        this.levelContainer.getChildren().clear();
        for (final Level lvl : LevelManager.loadLevels()) {
            final Button b = new Button(lvl.getLevelName());
            this.setButtonStyle(b, menuBtn);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    levelSelected.setOpacity(100);
                    currentLevel = lvl;
                    levelSelected.setText("Level name : " + lvl.getLevelName() + "\n"
                                          + "Background : " + lvl.getBackground().getTheme() + "\n"
                                          + "Music : " + lvl.getMusic().getName() + "\n"
                                          + "PaddleTexture : " + lvl.getPaddleTexture().getTheme() + "\n"
                                          + "BallTexture : " + lvl.getBallTexture().getTheme());
                }
            });
            this.levelContainer.getChildren().add(b);
        }

    }

    /**
     * 
     * Load the button listener.
     */
    @FXML
    private void loadListener() {
        //MenuButton return to menu
        this.menuBtn.setOnAction(event -> FXMLMenuController.switchScene((Stage) this.panel.getScene().getWindow(), PersonalViews.SCENE_MAIN_MENU, PersonalStyle.DEFAULT_STYLE, 
                ScreenUtilities.SCREEN_WIDTH, ScreenUtilities.SCREEN_HEIGHT, true));
        //BuilderButton go to LevelBuilder
        this.builderBtn.setOnAction(event -> FXMLMenuController.switchScene((Stage) this.panel.getScene().getWindow(), PersonalViews.SCENE_EDITOR_MODE, PersonalStyle.DEFAULT_STYLE, 
                EDITOR_MODE_WIDTH, EDITOR_MODE_HEIGHT, true));
    }

    /**
     * 
     * Loads the currently selected level and starts the gameloop cycle.
     */
    @FXML
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private void playLevel() {
        if (!levelSelected.getText().isBlank()) {
            GameStateImpl.setCreativeMode(true);
            final SettingLevelBuilder levelBuilder = new SettingLevelBuilder();
            levelBuilder.fromSettings(SettingLevelManager.loadOption());
            levelBuilder.selectLevel(currentLevel);
            SettingLevelManager.saveOption(levelBuilder.build());

            this.playBtn.setOnAction(event -> FXMLMenuController.switchScene((Stage) this.panel.getScene().getWindow(), PersonalViews.SCENE_CHARACTER_MENU, PersonalStyle.DEFAULT_STYLE, 
                    ScreenUtilities.SCREEN_WIDTH, ScreenUtilities.SCREEN_HEIGHT, false));

        } else {
            CheckCustomAlert.checkLevelSelected();
        }
    }

    /**
     * Copy the style of a button to another button.
     * @param subject the button that takes the new style
     * @param reference the button that gives the new style
     */
    private void setButtonStyle(final Button subject, final Button reference) {
        subject.setStyle(reference.getStyle());
        subject.setEffect(reference.getEffect());
        subject.setFont(reference.getFont());
    }
}
