package breakout.view;

import breakout.view.graphics.AdvancedStyle;
import breakout.view.graphics.ClassicStyle;
import breakout.view.graphics.Fonts;
import breakout.view.levels.LevelEditor;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Main Menù of the game.
 */
public class MainMenu extends Scene {

    private static final String BUTTON_STYLE = "menu_button";
    private static final double BUTTON_HEIGHT = 170;
    private static final double BUTTON_WIDTH = 530;
    private static final double INSETS = 35;
    private static final double SPACING = 8;
    private static final Font BUTTON_FONT = Fonts.PIXEL_FONT.get(22);
    private static final Font LOGO_FONT = Fonts.PIXEL_FONT.get(70);

    private final BorderPane panel = new BorderPane();
    private final Text breakout = new Text("\nBREAKOUT");
    private final Button classic = new Button("Classic Mode");
    private final Button advanced = new Button("Advanced Mode");
    private final Button levels = new Button("Levels");
    private final Button score = new Button("Scores");
    private final Button exit = new Button("Exit");
    private final VBox vbox = new VBox(classic, advanced, levels, score, exit);

    /**
     * Constructor for the menu.
     * 
     * @param mainStage
     *            The Stage where the scene is called
     */
    public MainMenu(final Stage mainStage) {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());
        this.panel.setId("menu_image");

        this.breakout.setFont(LOGO_FONT);
        this.breakout.setId("logo");

        this.classic.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.classic.setId(BUTTON_STYLE);
        this.classic.setFont(BUTTON_FONT);
        this.classic.setAlignment(Pos.CENTER_LEFT);
        this.classic.setOnMouseClicked(e -> {
            mainStage.setScene(new ClassicGameScene(mainStage, new ClassicStyle()));
        });
        this.classic.setOnMouseEntered(e -> {
            this.panel.setId("menu_image");
        });
        this.advanced.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.advanced.setId(BUTTON_STYLE);
        this.advanced.setFont(BUTTON_FONT);
        this.advanced.setAlignment(Pos.CENTER_LEFT);
        this.advanced.setOnMouseClicked(e -> {
            mainStage.setScene(new GameConfiguration(mainStage, new AdvancedStyle()));
        });
        this.advanced.setOnMouseEntered(event -> {
            this.panel.setId("menu_image_advanced");
        });

        this.levels.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.levels.setId(BUTTON_STYLE);
        this.levels.setFont(BUTTON_FONT);
        this.levels.setAlignment(Pos.CENTER_LEFT);
        this.levels.setOnMouseClicked(e -> {
            mainStage.setScene(new LevelEditor(mainStage, new AdvancedStyle()));
        });
        this.levels.setOnMouseEntered(event -> {
            this.panel.setId("menu_image_level");
        });

        this.score.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.score.setId(BUTTON_STYLE);
        this.score.setFont(BUTTON_FONT);
        this.score.setAlignment(Pos.CENTER_LEFT);
        this.score.setOnMouseClicked(e -> {
            mainStage.setScene(new ScoreScene(mainStage, new AdvancedStyle()));
        });
        this.score.setOnMouseEntered(event -> {
            this.panel.setId("menu_image_score");
        });

        this.exit.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.exit.setId(BUTTON_STYLE);
        this.exit.setFont(BUTTON_FONT);
        this.exit.setAlignment(Pos.CENTER_LEFT);
        this.exit.setOnMouseEntered(event -> {
            this.panel.setId("menu_image_exit");
        });
        this.exit.setOnMouseClicked(event -> {
            Platform.exit();
        });

        this.vbox.setAlignment(Pos.CENTER);
        this.vbox.setSpacing(SPACING);
        this.vbox.setPadding(new Insets(INSETS));

        this.panel.setTop(breakout);
        this.panel.setCenter(vbox);
        BorderPane.setAlignment(this.breakout, Pos.TOP_CENTER);

        this.setRoot(this.panel);
        this.getStylesheets().add("stylesheet.css");
    }
}