package breakout.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import breakout.controller.ScoreManager;
import breakout.view.graphics.GraphicStyle;
import breakout.view.utils.CustomButton;
import breakout.view.utils.SwitchBox;
import breakout.view.utils.Utils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * A scene for user's score.
 *
 */
public class ScoreScene extends Scene {
    private static final double SCROLLPANE_HEIGHT = 450;
    private static final double EXIT_BUTTON_WIDTH = 200;
    private static final double EXIT_BUTTON_HEIGHT = 85;
    private static final Shape BUTTON_SHAPE = new Rectangle(EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
    private static final double EXIT_BUTTON_X = 900;
    private static final double EXIT_BUTTON_Y = 555;
    private static final double BUTTON_FONT_SIZE = 60;
    private static final Image LEFT_ARROW = new Image(Utils.getPath("/Images/Green_Arrow_LEFT.png"));
    private static final Image RIGHT_ARROW = new Image(Utils.getPath("/Images/Green_Arrow_RIGHT.png"));
    private static final double ARROW_TOP_MARGIN = 25;
    private static final double ARROW_LEFT_MARGIN = 35;
    private static final double SCORE_POSITION_X = 100;
    private static final double FONT_SIZE = 90;

    private final List<Text> mode = Arrays.asList(new Text("Advanced"), new Text("Classic"));
    private final SwitchBox<Text> modeChooser = new SwitchBox<>(LEFT_ARROW, mode, RIGHT_ARROW);
    private final GridPane scores = new GridPane();
    private final CustomButton exit = new CustomButton(BUTTON_SHAPE, "Back");
    private final ScrollPane scrollPane = new ScrollPane(scores);
    private final AnchorPane mainPane = new AnchorPane(modeChooser, scrollPane, exit);
    private final GraphicStyle style;

    /**
     * Constructor for the scene.
     * 
     * @param mainStage
     *            The Stage where the scene is called
     * @param style
     *            The style of the scene
     */
    public ScoreScene(final Stage mainStage, final GraphicStyle style) {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());
        this.scrollPane.relocate(100, 100);
        this.scrollPane.setMaxHeight(SCROLLPANE_HEIGHT);
        this.scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.scrollPane.setId(style.mainPaneStyle());
        final List<Pair<Text, Text>> advancedScore = new ArrayList<>();
        final List<Pair<Text, Text>> classicScore = new ArrayList<>();
        final ScoreManager scoreManager = new ScoreManager();
        try {
            advancedScore.addAll(scoreManager.loadAdvancedScore().getOrderedScores().stream().map(
                    pair -> new Pair<Text, Text>(new Text(pair.getKey()), new Text(pair.getValue().toString())))
                                             .collect(Collectors.toList()));
            classicScore.addAll(scoreManager.loadClassicScore().getOrderedScores().stream().map(
                    pair -> new Pair<Text, Text>(new Text(pair.getKey()), new Text(pair.getValue().toString())))
                                            .collect(Collectors.toList()));
        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        }
        this.style = style;
        this.changeScoreGrid(advancedScore);
        this.modeChooser.getLeftArrow().setId("arrow");
        this.modeChooser.getLeftArrow().setOnMousePressed(e -> {
            this.modeChooser.showPreviousItem();
            if (this.modeChooser.getCurrent().equals(this.mode.get(0))) {
                this.changeScoreGrid(advancedScore);
            } else {
                this.changeScoreGrid(classicScore);
            }

        });
        this.modeChooser.getRightArrow().setId("arrow");
        this.modeChooser.getRightArrow().setOnMousePressed(e -> {
            this.modeChooser.showNextItem();
            if (this.modeChooser.getCurrent().equals(this.mode.get(0))) {
                this.changeScoreGrid(advancedScore);
            } else {
                this.changeScoreGrid(classicScore);
            }
        });

        this.modeChooser.getToSwitch().stream().forEach(n -> {
            n.setFont(style.getFont(FONT_SIZE));
            n.setId(style.getTextStyle());
        });
        HBox.setMargin(this.modeChooser.getLeftArrow(), new Insets(ARROW_TOP_MARGIN, 0, 0, ARROW_LEFT_MARGIN));
        HBox.setMargin(this.modeChooser.getRightArrow(), new Insets(ARROW_TOP_MARGIN, 0, 0, ARROW_LEFT_MARGIN));

        this.exit.setTextFont(style.getFont(BUTTON_FONT_SIZE));
        this.exit.setTextColor(Color.WHITE);
        this.exit.setShapeStroke(StrokeType.CENTERED, 4, Color.WHITE);
        this.exit.relocate(EXIT_BUTTON_X, EXIT_BUTTON_Y);
        this.exit.setStyleOnMousePressed("-fx-effect:innershadow( gaussian  , white , 25 , 0.1 , 0 , 0 );");
        this.exit.setId("white_shadow");
        this.exit.setOnMouseClicked(event -> {
            mainStage.setScene(new MainMenu(mainStage));
        });
        this.scores.getColumnConstraints().addAll(new ColumnConstraints(mainStage.getWidth() * 2 / 3),
                new ColumnConstraints(mainStage.getWidth() / 3));
        this.scores.relocate(SCORE_POSITION_X, this.modeChooser.getBoundsInLocal().getHeight());
        this.mainPane.getChildren().add(scores);
        this.mainPane.setId(style.mainPaneStyle());
        this.setRoot(mainPane);
        this.getStylesheets().add("stylesheet.css");
        this.getStylesheets().add(style.getCSS());
    }

    private void changeScoreGrid(final List<Pair<Text, Text>> toShow) {
        this.scores.getChildren().clear();
        toShow.stream().forEach(p -> {
            p.getKey().setFont(style.getFont(FONT_SIZE));
            p.getValue().setFont(style.getFont(FONT_SIZE));
            p.getKey().setId(style.getTextStyle());
            p.getValue().setId(style.getTextStyle());
            this.scores.add(p.getKey(), 0, toShow.indexOf(p));
            this.scores.add(p.getValue(), 1, toShow.indexOf(p));
        });
    }
}
