package ala.views;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ala.controllers.BossHellLevelGeneratorController;
import ala.controllers.BossParadiseLevelGeneratorController;
import ala.controllers.BossPurgatoryLevelGeneratorController;
import ala.controllers.NormalHellLevelGeneratorController;
import ala.controllers.NormalParadiseLevelGeneratorController;
import ala.controllers.NormalPurgatoryLevelGeneratorController;
import ala.models.CurrentLevelModel;
import ala.models.LevelGeneratorPatternModel.LVLNUMBER;
import ala.models.NormalHellLevelGeneratorModel;
import ala.models.NormalParadiseLevelGeneratorModel;
import ala.models.NormalPurgatoryLevelGeneratorModel;
import ala.models.BossHellLevelGeneratorModel;
import ala.models.BossParadiseLevelGeneratorModel;
import ala.models.BossPurgatoryLevelGeneratorModel;
import ala.models.SaveGameModel;

/**
 * Generate the game menu GUI.
 */
public class DisplayManagerView {
    //Attributes:
    private static final double SCENE_WIDTH = 1280;
    private static final double SCENE_HEIGHT = 720;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 40;
    private static final int BOX_PADDING = 150;
    private static final int BOX_SPACING = 40;

    //GameLevel root node and scene:
    private Pane gameDemoLevelRoot = new Pane();
    private Scene gameDemoLevelScene;

    //Scene background 
    private static final Image MAIN_MENU_BACKGROUND = new Image(DisplayManagerView.class.getResource("/Menu_Background.jpg").toExternalForm());
    private BackgroundImage mainMenuImage = new BackgroundImage(MAIN_MENU_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    private Background mainMenuBackground = new Background(mainMenuImage);

    //Menu Button background
    private static final Image MENU_BUTTONS_BACKGROUND = new Image(DisplayManagerView.class.getResource("/Menu_Button.jpg").toExternalForm());
    private BackgroundImage menuButtonImage = new BackgroundImage(MENU_BUTTONS_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    private Background menuButtonBackground = new Background(menuButtonImage);

    //Hell Button background
    private static final Image HELL_BUTTONS_BACKGROUND = new Image(DisplayManagerView.class.getResource("/Hell_Button.jpg").toExternalForm());
    private BackgroundImage hellButtonImage = new BackgroundImage(HELL_BUTTONS_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    private Background hellButtonBackground = new Background(hellButtonImage);

    //Earthly Button background
    private static final Image EARTHLY_BUTTONS_BACKGROUND = new Image(DisplayManagerView.class.getResource("/Earthly_Button.jpg").toExternalForm());
    private BackgroundImage earthlyButtonImage = new BackgroundImage(EARTHLY_BUTTONS_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    private Background earthlyButtonBackground = new Background(earthlyButtonImage);

    //Heaven Button background
    private static final Image HEAVEN_BUTTONS_BACKGROUND = new Image(DisplayManagerView.class.getResource("/Heaven_Button.jpg").toExternalForm());
    private BackgroundImage heavenButtonImage = new BackgroundImage(HEAVEN_BUTTONS_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    private Background heavenButtonBackground = new Background(heavenButtonImage);

    //Save and load game
    private SaveGameModel game = new SaveGameModel();

    //Current level
    private List<Button> levelButtonList = new ArrayList<Button>();
    private CurrentLevelModel level = new CurrentLevelModel(this.levelButtonList);

    //Main Menu Contents:
    private Button newGameButton;
    private Button loadGameButton;
    private Button settingsButton;
    private Button exitGameButton;
    private VBox mainMenuBox;
    //Create GameMenu scene:
    private Scene mainMenuScene;

    //Settings Contents:
    private Button soundOffButton;
    private Button soundOnButton;
    private Button mainMenuButton0;
    private VBox mainSettingBox;
    private Scene settingsScene;

    //Choose Level Menu contents:
    private Button saveGameButton;
    private Button mainMenuButton1;
    private Button commandsButton;
    private Button level1;
    private Button level2;
    private Button level3;
    private Button level4;
    private Button level5;
    private Button level6;
    private VBox chooseLevelMenuBox;
    private VBox chooseLevelLevelsBox;
    private HBox chooseLevelMainBox;
    //choose level Menu scene:
    private Scene chooseLevelMenuScene;

    private Label commandsLabel;
    private Button backToLevelButton;
    private VBox commandsMainBox;
    private Scene commandsScene;

    //Exit Game Menu contents:
    private Label exitGameLabel;
    private Button exitGameYes;
    private Button exitGameNo;
    private HBox exitGameHBox;
    private VBox exitGameMainBox;
    //Exit Game Menu scene:
    private Scene exitGameMenuScene;

    //Exit Level contents:
    private static Label message;
    private Button ok;
    private VBox mainEvixLevelBox;
    //Exit Level scene:
    private static Scene exitLevelScene;
    //----------------------------------------------

        //Constructor
        /**
         * Create all game menu scenes:
         * mainMenuScene, exitGameMenuScene, settingsScene, chooseLevelMenuScene, commandsScene.
         * 
         * @param currentDisplayed
         */
        public DisplayManagerView(final Stage currentDisplayed) {
            this.gameDemoLevelScene = new Scene(this.gameDemoLevelRoot, SCENE_WIDTH, SCENE_HEIGHT);

            //Create Main Menu:
            //New game button: start a new game.
            this.newGameButton = new Button("New Game");
            DisplayManagerView.buttonStyle(this.newGameButton);
            this.newGameButton.setBackground(this.menuButtonBackground);
            this.newGameButton.setOnAction(e -> {
                currentDisplayed.setScene(this.chooseLevelMenuScene);
                CurrentLevelModel.forceSetCurrentLevel0();
                CurrentLevelModel.unlock();
            });

            //Load game button: load the last save you did.
            this.loadGameButton = new Button("Load Game");
            DisplayManagerView.buttonStyle(this.loadGameButton);
            this.loadGameButton.setBackground(this.menuButtonBackground);
            this.loadGameButton.setOnAction(e -> {
                try {
                    SaveGameModel.loadGame();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                CurrentLevelModel.unlock();
                currentDisplayed.setScene(this.chooseLevelMenuScene);
            });

            //Option button: enter game settings
            this.settingsButton = new Button("Options");
            DisplayManagerView.buttonStyle(this.settingsButton);
            this.settingsButton.setBackground(this.menuButtonBackground);
            this.settingsButton.setOnAction(e -> currentDisplayed.setScene(this.settingsScene));

            //Exit game button: to click if u want to go out to the application without clicking the red X.
            this.exitGameButton = new Button("Exit Game");
            DisplayManagerView.buttonStyle(this.exitGameButton);
            this.exitGameButton.setBackground(this.menuButtonBackground);
            this.exitGameButton.setOnAction(e -> currentDisplayed.setScene(this.exitGameMenuScene));

            //The box which contains all the preview buttons.
            this.mainMenuBox = new VBox(BUTTON_HEIGHT);
            this.mainMenuBox.getChildren().addAll(this.newGameButton, this.loadGameButton, this.settingsButton, this.exitGameButton);
            this.mainMenuBox.setAlignment(Pos.CENTER);
            this.mainMenuBox.setBackground(mainMenuBackground);

            //Main Menu Scene
            this.mainMenuScene = new Scene(this.mainMenuBox, SCENE_WIDTH,  SCENE_HEIGHT);

            //----------------------------------------------
            //Create Settings Menu:

            //Sound On button: disable music & sounds
            this.soundOnButton = new Button("Sound On");
            DisplayManagerView.buttonStyle(this.soundOnButton);
            this.soundOnButton.setBackground(this.menuButtonBackground);
            this.soundOnButton.setDisable(true);
            this.soundOnButton.setOnAction(e -> {
                this.soundOnButton.setDisable(true);
                this.soundOffButton.setDisable(false);
            });

            //Sound Off button: enable music & sounds
            this.soundOffButton = new Button("Sound Off");
            DisplayManagerView.buttonStyle(this.soundOffButton);
            this.soundOffButton.setBackground(this.menuButtonBackground);
            this.soundOffButton.setOnAction(e -> {
                this.soundOffButton.setDisable(true);
                this.soundOnButton.setDisable(false);
            });

            //Return to main menu Button
            this.mainMenuButton0 = new Button("Main Menu");
            DisplayManagerView.buttonStyle(this.mainMenuButton0);
            this.mainMenuButton0.setBackground(this.menuButtonBackground);
            this.mainMenuButton0.setOnAction(e -> currentDisplayed.setScene(this.mainMenuScene));

            //The box which contains all the preview buttons.
            this.mainSettingBox = new VBox(BOX_SPACING);
            this.mainSettingBox.getChildren().addAll(this.soundOnButton, this.soundOffButton, this.mainMenuButton0);
            this.mainSettingBox.setAlignment(Pos.CENTER);
            this.mainSettingBox.setBackground(mainMenuBackground);

            //Settings Scene
            this.settingsScene = new Scene(this.mainSettingBox, SCENE_WIDTH, SCENE_HEIGHT);

            //----------------------------------------------

            //Create choose level menu:
            //Save game button
            this.saveGameButton = new Button("Save Game");
            DisplayManagerView.buttonStyle(this.saveGameButton);
            this.saveGameButton.setBackground(this.menuButtonBackground);
            this.saveGameButton.setOnAction(e -> game.saveGame());

            //Commands Button
            this.commandsButton = new Button("Commands");
            DisplayManagerView.buttonStyle(this.commandsButton);
            this.commandsButton.setBackground(this.menuButtonBackground);
            this.commandsButton.setOnAction(e -> currentDisplayed.setScene(this.commandsScene));

            //Return to main menu Button
            this.mainMenuButton1 = new Button("Main Menu");
            DisplayManagerView.buttonStyle(this.mainMenuButton1);
            this.mainMenuButton1.setBackground(this.menuButtonBackground);
            this.mainMenuButton1.setOnAction(e -> currentDisplayed.setScene(this.mainMenuScene));

            //Menu box (left)
            this.chooseLevelMenuBox = new VBox(BOX_SPACING);
            this.chooseLevelMenuBox.getChildren().addAll(this.saveGameButton, this.commandsButton, this.mainMenuButton1);
            this.chooseLevelMenuBox.setPadding(new Insets(BOX_PADDING));
            this.chooseLevelMenuBox.setAlignment(Pos.BASELINE_LEFT);

            //List of levels
            //level1
            this.level1 = new Button("Level 1");
            DisplayManagerView.buttonStyle(this.level1);
            this.level1.setBackground(this.hellButtonBackground);
            levelButtonList.add(level1);
            this.level1.setOnAction(e -> {
                NormalHellLevelGeneratorModel level1Model = new NormalHellLevelGeneratorModel(LVLNUMBER.UNO);
                NormalHellLevelGeneratorView level1View = new NormalHellLevelGeneratorView(this.gameDemoLevelRoot, this.gameDemoLevelScene, currentDisplayed, this.chooseLevelMenuScene, level1Model);
                NormalHellLevelGeneratorController level1Controller = new NormalHellLevelGeneratorController(level1Model, level1View);
                currentDisplayed.setScene(gameDemoLevelScene);
                level1Controller.createGameLoop();
            });

            //level2
            this.level2 = new Button("Level 2");
            DisplayManagerView.buttonStyle(this.level2);
            this.level2.setBackground(this.hellButtonBackground);
            this.levelButtonList.add(level2);
            this.level2.setOnAction(e -> {
                BossHellLevelGeneratorModel level2Model = new BossHellLevelGeneratorModel(LVLNUMBER.DUE);
                BossHellLevelGeneratorView level2View = new BossHellLevelGeneratorView(this.gameDemoLevelRoot, this.gameDemoLevelScene, currentDisplayed, this.chooseLevelMenuScene, level2Model);
                BossHellLevelGeneratorController level2Controller = new BossHellLevelGeneratorController(level2Model, level2View);
                currentDisplayed.setScene(gameDemoLevelScene);
                level2Controller.createGameLoop();

            });

            //level3
            this.level3 = new Button("Level 3");
            DisplayManagerView.buttonStyle(this.level3);
            this.level3.setBackground(this.earthlyButtonBackground);
            this.levelButtonList.add(level3);
            this.level3.setOnAction(e -> {
                NormalPurgatoryLevelGeneratorModel level3Model = new NormalPurgatoryLevelGeneratorModel(LVLNUMBER.TRE);
                NormalPurgatoryLevelGeneratorView level3View = new NormalPurgatoryLevelGeneratorView(this.gameDemoLevelRoot, this.gameDemoLevelScene, currentDisplayed, this.chooseLevelMenuScene, level3Model);
                NormalPurgatoryLevelGeneratorController level3Controller = new NormalPurgatoryLevelGeneratorController(level3Model, level3View);
                currentDisplayed.setScene(gameDemoLevelScene);
                level3Controller.createGameLoop();
            });

            //level4
            this.level4 = new Button("Level 4");
            DisplayManagerView.buttonStyle(this.level4);
            this.level4.setBackground(this.earthlyButtonBackground);
            this.levelButtonList.add(level4);
            this.level4.setOnAction(e -> {
                BossPurgatoryLevelGeneratorModel level4Model = new BossPurgatoryLevelGeneratorModel(LVLNUMBER.QUATTRO);
                BossPurgatoryLevelGeneratorView level4View = new BossPurgatoryLevelGeneratorView(this.gameDemoLevelRoot, this.gameDemoLevelScene, currentDisplayed, this.chooseLevelMenuScene, level4Model);
                BossPurgatoryLevelGeneratorController level4Controller = new BossPurgatoryLevelGeneratorController(level4Model, level4View);
                currentDisplayed.setScene(gameDemoLevelScene);
                level4Controller.createGameLoop();
            });

            //level5
            this.level5 = new Button("Level 5");
            DisplayManagerView.buttonStyle(this.level5);
            this.level5.setBackground(this.heavenButtonBackground);
            this.levelButtonList.add(level5);
            this.level5.setOnAction(e -> {
                NormalParadiseLevelGeneratorModel level5Model = new NormalParadiseLevelGeneratorModel(LVLNUMBER.CINQUE);
                NormalParadiseLevelGeneratorView level5View = new NormalParadiseLevelGeneratorView(this.gameDemoLevelRoot, this.gameDemoLevelScene, currentDisplayed, this.chooseLevelMenuScene, level5Model);
                NormalParadiseLevelGeneratorController level5Controller = new NormalParadiseLevelGeneratorController(level5Model, level5View);
                currentDisplayed.setScene(gameDemoLevelScene);
                level5Controller.createGameLoop();
            });

            //level6
            this.level6 = new Button("Level 6");
            DisplayManagerView.buttonStyle(this.level6);
            this.level6.setBackground(this.heavenButtonBackground);
            this.levelButtonList.add(level6);
            this.level6.setOnAction(e -> {
                BossParadiseLevelGeneratorModel level6Model = new BossParadiseLevelGeneratorModel(LVLNUMBER.SEI);
                BossParadiseLevelGeneratorView level6View = new BossParadiseLevelGeneratorView(this.gameDemoLevelRoot, this.gameDemoLevelScene, currentDisplayed, this.chooseLevelMenuScene, level6Model);
                BossParadiseLevelGeneratorController level6Controller = new BossParadiseLevelGeneratorController(level6Model, level6View);
                currentDisplayed.setScene(gameDemoLevelScene);
                level6Controller.createGameLoop();
            });

            //Level box (right)
            this.chooseLevelLevelsBox = new VBox(BOX_SPACING);
            this.chooseLevelLevelsBox.getChildren().addAll(this.level6, this.level5, this.level4, this.level3, this.level2, this.level1);
            this.chooseLevelLevelsBox.setPadding(new Insets(BOX_PADDING));
            this.chooseLevelLevelsBox.setAlignment(Pos.TOP_LEFT);

            //Level Main Box
            this.chooseLevelMainBox = new HBox(BOX_SPACING);
            this.chooseLevelMainBox.getChildren().addAll(this.chooseLevelMenuBox, this.chooseLevelLevelsBox);
            this.chooseLevelMainBox.setPadding(new Insets(BOX_PADDING));
            this.chooseLevelMainBox.setAlignment(Pos.CENTER);
            this.chooseLevelMainBox.setBackground(mainMenuBackground);

            //Choose level Scene
            this.chooseLevelMenuScene  = new Scene(this.chooseLevelMainBox, SCENE_WIDTH, SCENE_HEIGHT);
            //----------------------------------------------

            //Create Commands menu:
            //Commands label
            this.commandsLabel = new Label();
            this.commandsLabel.setText(
                    "Right arrow    = Move right\n"
                     + "Left arrow = Move left\n" 
                     + "Space      = Jump\n" 
                     + "Q          = Fisical attack\n" 
                     + "W          = Range attack\n"
                     + "E          = Final attack\n"
                     + "ESC            = Exit current level"
            );
            DisplayManagerView.labelStyle(this.commandsLabel);

            //Back to level menu Button
            this.backToLevelButton = new Button("Levels Menu");
            DisplayManagerView.buttonStyle(this.backToLevelButton);
            this.backToLevelButton.setBackground(this.menuButtonBackground);
            this.backToLevelButton.setOnAction(e -> currentDisplayed.setScene(this.chooseLevelMenuScene));

            //Main command Box
            this.commandsMainBox = new VBox(BOX_SPACING);
            this.commandsMainBox.getChildren().addAll(this.commandsLabel, this.backToLevelButton);
            this.commandsMainBox.setAlignment(Pos.CENTER);
            this.commandsMainBox.setBackground(mainMenuBackground);

            //Commands Scene
            this.commandsScene = new Scene(this.commandsMainBox, SCENE_WIDTH, SCENE_HEIGHT);

            //----------------------------------------------

            //Create exit game menu:
            //Exit Label
            this.exitGameLabel = new Label();
            this.exitGameLabel.setText("Are you sure you want to close the game?");
            DisplayManagerView.labelStyle(this.exitGameLabel);

            //Exit game button
            this.exitGameYes = new Button("Yes");
            DisplayManagerView.buttonStyle(this.exitGameYes);
            this.exitGameYes.setBackground(this.menuButtonBackground);
            this.exitGameYes.setOnAction(e -> currentDisplayed.close());

            //Return to menu button
            this.exitGameNo = new Button("No");
            DisplayManagerView.buttonStyle(this.exitGameNo);
            this.exitGameNo.setBackground(this.menuButtonBackground);
            this.exitGameNo.setOnAction(e -> currentDisplayed.setScene(this.mainMenuScene));

            //yes && no box
            this.exitGameHBox = new HBox(BOX_SPACING);
            this.exitGameHBox.getChildren().addAll(this.exitGameNo, this.exitGameYes);
            this.exitGameHBox.setAlignment(Pos.CENTER);

            //The main box which contains all the preview buttons.
            this.exitGameMainBox = new VBox(BOX_SPACING);
            this.exitGameMainBox.getChildren().addAll(this.exitGameLabel, this.exitGameHBox);
            this.exitGameMainBox.setAlignment(Pos.CENTER);
            this.exitGameMainBox.setBackground(mainMenuBackground);

            //Exit scene
            this.exitGameMenuScene = new Scene(this.exitGameMainBox, SCENE_WIDTH, SCENE_HEIGHT);

            //----------------------------------------------

            //Back to levels menu label
            DisplayManagerView.message = new Label();
            DisplayManagerView.labelStyle(message);

            //Back to menu button
            this.ok = new Button("OK");
            DisplayManagerView.buttonStyle(ok);
            this.ok.setBackground(this.menuButtonBackground);
            this.ok.setOnAction(e -> currentDisplayed.setScene(this.chooseLevelMenuScene));

            //The main box which contains all the preview buttons.
            this.mainEvixLevelBox = new VBox(BOX_SPACING);
            this.mainEvixLevelBox.getChildren().addAll(DisplayManagerView.message, this.ok);
            this.mainEvixLevelBox.setPadding(new Insets(BOX_PADDING));
            this.mainEvixLevelBox.setAlignment(Pos.CENTER);
            this.mainEvixLevelBox.setBackground(mainMenuBackground);

            //Exit scene
            DisplayManagerView.exitLevelScene = new Scene(this.mainEvixLevelBox, SCENE_WIDTH, SCENE_HEIGHT);
        }
        //---------------------------------------------

        //Methods
        //Buttons style
        /**
         * Gives to a Button a default style.
         * 
         * @param button
         */
        static void buttonStyle(final Button button) {
            button.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            button.setTextFill(Color.BLACK);
            button.setFont(Font.font("Helvetica", FontWeight.BOLD, 10 * 2));

            //Button Shadow
            button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(final MouseEvent e) {
                        button.setEffect(new DropShadow());
                      }
                    });
                button.addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(final MouseEvent e) {
                        button.setEffect(null);
                      }
                    });
        }

        //Label style
        /**
         * Gives to a Label a default style.
         * 
         * @param label
         */
        static void labelStyle(final Label label) {
            label.setFont(Font.font("Helvetica", FontWeight.BOLD, 10 * 3));
            label.setStyle("-fx-background-color: rgba(100,100,100,0.7);"
                          + "-fx-background-radius: 10;"
                          + "-fx-opacity: 20;");
            label.setTextFill(Color.BLACK);
        }

        /**
         * Set the end level message in the exit level scene.
         * 
         * @param isCompleted
         */
        public static void setMessage(final boolean isCompleted) {
            if (isCompleted) {
                message.setText("Level completed!");
            } else {
                message.setText("Level failed!");
              }
        }
        //----------------------------------------------

        //Getter & Setter
        public final Scene getMainMenuScene() {
            return mainMenuScene;
        }

        public final CurrentLevelModel getLevel() {
            return level;
        }

        public final SaveGameModel getGame() {
            return game;
        }

        public static final Scene getEndLevelScene() {
            return exitLevelScene;
        }
}
