package breakout.view;

import java.util.List;
import java.util.stream.Collectors;

import breakout.controller.Controller;
import breakout.view.graphics.AdvancedStyle;
import breakout.view.graphics.Colors;
import breakout.view.graphics.Fonts;
import breakout.view.graphics.GraphicStyle;
import breakout.view.graphics.Images;
import breakout.view.graphics.Images.ColoredEntities;
import breakout.view.utils.CustomButton;
import breakout.view.utils.SwitchBox;
import breakout.view.utils.Utils;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * This scene is shown to the user right before the game starts.<br/>
 * It has two main panel:<br/>
 * <li/>The first one is for changing colors of the ball and the paddle<br/>
 * <li/>The second one is for choosing levels
 */
public final class GameConfiguration extends Scene {

    private static final double BUTTON_WIDTH = 170;
    private static final double BUTTON_HEIGHT = 80;
    private static final Shape BUTTON_SHAPE_PLAY = new Rectangle(BUTTON_WIDTH, BUTTON_HEIGHT);
    private static final double LINE_SCREEN_OFFSET = 30;
    private static final double PLAY_BUTTON_Y = 565;
    private static final double PLAY_BUTTON_X = 656;
    private static final Shape BUTTON_SHAPE_BACK = new Rectangle(BUTTON_WIDTH, BUTTON_HEIGHT);
    private static final double BACK_BUTTON_Y = 565;
    private static final double BACK_BUTTON_X = 836;
    private static final double TEXT_SIZE = 58;
    private static final double TEXT_BOX_SPACING = 15;
    private static final double LINE_LENGTH = 213;
    private static final double MESSAGE_DIALOG_WIDTH = 475;
    private static final double MESSAGE_DIALOG_HEIGHT = 250;

    private final Text playerLabel = new Text("Player");
    private final Line playerLineLeft = new Line();
    private final Line playerLineRight = new Line();
    private final HBox player = new HBox(playerLineLeft, playerLabel, playerLineRight);

    private final Text levelLabel = new Text("Levels");
    private final Line levelLineLeft = new Line();
    private final Line levelLineRight = new Line();
    private final HBox levels = new HBox(levelLineLeft, levelLabel, levelLineRight);

    private final PlayerPane editPlayer = new PlayerPane(0, 0);
    private final LevelsPane editLevel = new LevelsPane(0, 0);
    private final CustomButton play = new CustomButton(BUTTON_SHAPE_PLAY, "Play");
    private final CustomButton back = new CustomButton(BUTTON_SHAPE_BACK, "Back");
    private final AnchorPane mainPane = new AnchorPane(player, levels, editPlayer, editLevel, play, back);

    /**
     * @param style
     *            the style of the scene
     * @param mainStage
     *            The stage form where the scene is called
     */
    public GameConfiguration(final Stage mainStage, final GraphicStyle style) {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());
        this.mainPane.setId(style.mainPaneStyle());
        this.playerLabel.setId(style.getTextStyle());
        this.playerLabel.setFont(style.getFont(TEXT_SIZE));
        this.player.setSpacing(TEXT_BOX_SPACING);
        this.levels.relocate(0, mainStage.getHeight() / 4);
        this.editLevel.relocate(0, mainStage.getHeight() / 4);
        this.levels.setSpacing(TEXT_BOX_SPACING);
        this.levelLabel.setId(style.getTextStyle());
        this.levelLabel.setFont(style.getFont(TEXT_SIZE));

        this.play.setTextFont(style.getFont(TEXT_SIZE));
        this.play.setTextColor(Color.WHITE);
        this.play.setShapeStroke(StrokeType.CENTERED, 4, Color.WHITE);
        this.play.relocate(PLAY_BUTTON_X, PLAY_BUTTON_Y);
        this.play.setId("white_shadow");
        this.play.setOnMouseClicked(event -> {
            Controller.get().clearToPlayList();
            Controller.get().addLevelsToPlay(this.editLevel.getCustomList());
            Controller.get().setPlayer(this.editPlayer.getPlayer().getKey(), this.editPlayer.getPlayer().getValue());
            // You must choose at least 1 level
            if (!Controller.get().getLevelsToPlay().isEmpty()) {
                mainStage.setScene(new AdvancedGameScene(mainStage, new AdvancedStyle()));
            } else {
                final MessageDialog chooseLevel = new MessageDialog("You must choose at least one level to start",
                        MESSAGE_DIALOG_WIDTH, MESSAGE_DIALOG_HEIGHT, ButtonType.OK);
                chooseLevel.showAndWait();
            }

        });

        this.back.setTextFont(style.getFont(TEXT_SIZE));
        this.back.setTextColor(Color.WHITE);
        this.back.setShapeStroke(StrokeType.CENTERED, 4, Color.WHITE);
        this.back.relocate(BACK_BUTTON_X, BACK_BUTTON_Y);
        this.back.setId("white_shadow");
        this.back.setOnMouseClicked(event -> {
            mainStage.setScene(new MainMenu(mainStage));
        });

        final double translateLine = this.playerLabel.getBoundsInParent().getHeight() / 2;
        this.playerLineLeft.setId("black_white_gradient");
        this.playerLineLeft.setEndX(-LINE_LENGTH);
        this.playerLineLeft.setTranslateY(translateLine);
        this.playerLineRight.setId("white_black_gradient");
        this.playerLineRight.setStartX(this.playerLabel.getBoundsInParent().getMaxX());
        this.playerLineRight.setEndX(mainStage.getWidth() - LINE_SCREEN_OFFSET - LINE_LENGTH);
        this.playerLineRight.setTranslateY(translateLine);
        this.levelLineLeft.setId("black_white_gradient");
        this.levelLineLeft.setEndX(-LINE_LENGTH);
        this.levelLineLeft.setTranslateY(translateLine);
        this.levelLineRight.setId("white_black_gradient");
        this.levelLineRight.setStartX(this.levelLabel.getBoundsInParent().getMaxX());
        this.levelLineRight.setEndX(mainStage.getWidth() - LINE_SCREEN_OFFSET - LINE_LENGTH);
        this.levelLineRight.setTranslateY(translateLine);
        this.setRoot(this.mainPane);
        this.getStylesheets().add("stylesheet.css");
        this.getStylesheets().add(style.getCSS());
    }

    /**
     * A custom pane that allow the user to change colors of his ball and
     * paddle.
     */
    private static final class PlayerPane extends Pane {

        private static final String ARROW_STYLE = "arrow";
        private static final Image LEFT_ARROW = new Image(Utils.getPath("/Images/Green_Arrow_LEFT.png"));
        private static final Image RIGHT_ARROW = new Image(Utils.getPath("/Images/Green_Arrow_RIGHT.png"));
        private static final double BOX_INSETS = 26;
        private static final double BALL_CHOOSER_X = 254;
        private static final double BALL_CHOOSER_Y = 100;
        private static final double BALL_PREVIEW_SIZE = 32;
        private static final double PADDLE_CHOOSER_X = 635;
        private static final double PADDLE_CHOOSER_Y = 100;
        private static final double PADDLE_PREVIEW_SIZE = 205;

        private final List<ImageView> balls = Colors.ADVANCED_SIMPLE.stream().map(
                c -> new ImageView(Images.getImages().getColoredImage(ColoredEntities.BALLS, c))).peek(imageView -> {
                    imageView.setFitHeight(BALL_PREVIEW_SIZE);
                    imageView.setFitWidth(BALL_PREVIEW_SIZE);
                }).collect(Collectors.toList());
        private final SwitchBox<ImageView> ballChooser = new SwitchBox<>(LEFT_ARROW, balls, RIGHT_ARROW);
        private final List<ImageView> paddles = Colors.ADVANCED_SIMPLE.stream().map(
                c -> new ImageView(Images.getImages().getColoredImage(ColoredEntities.PADDLES, c))).peek(imageView -> {
                    imageView.setFitHeight(BALL_PREVIEW_SIZE);
                    imageView.setFitWidth(PADDLE_PREVIEW_SIZE);
                }).collect(Collectors.toList());
        private final SwitchBox<ImageView> paddleChooser = new SwitchBox<>(LEFT_ARROW, paddles, RIGHT_ARROW);

        /**
         * Constructor for this pane.
         */
        PlayerPane(final double posX, final double posY) {
            super();
            this.relocate(posX, posY);
            this.getChildren().addAll(ballChooser, paddleChooser);
            this.ballChooser.relocate(BALL_CHOOSER_X, BALL_CHOOSER_Y);
            this.paddleChooser.relocate(PADDLE_CHOOSER_X, PADDLE_CHOOSER_Y);
            this.ballChooser.setSpacing(BOX_INSETS);
            this.paddleChooser.setSpacing(BOX_INSETS);
            this.ballChooser.getLeftArrow().setId(ARROW_STYLE);
            this.ballChooser.getRightArrow().setId(ARROW_STYLE);
            this.paddleChooser.getLeftArrow().setId(ARROW_STYLE);
            this.paddleChooser.getRightArrow().setId(ARROW_STYLE);
        }

        public Pair<Image, Image> getPlayer() {
            return new Pair<>(this.ballChooser.getCurrent().getImage(), this.paddleChooser.getCurrent().getImage());
        }
    }

    /**
     * A custom pane that allow the user to change the level list to play.
     */
    private static final class LevelsPane extends Pane {

        private static final Font LIST_FONT = Fonts.ADVANCED_FONT.get(35);
        private static final double LIST_HEIGHT = 300;
        private static final double LIST_WIDTH = 350;
        private static final double LEVEL_LIST_X = 176;
        private static final double LEVEL_LIST_Y = 80;
        private static final double CUSTOM_LIST_X = 660;
        private static final double LEVELS_PREVIEW_HEIGHT = 85;
        private static final double LEVELS_PREVIEW_WIDTH = LIST_WIDTH / 2.5;

        private final ListView<HBox> levelList = new ListView<>();
        private final ListView<HBox> customList = new ListView<>();

        LevelsPane(final double posX, final double posY) {
            this.relocate(posX, posY);
            this.getChildren().addAll(levelList, customList);
            this.customList.relocate(CUSTOM_LIST_X, LEVEL_LIST_Y);
            this.customList.setPrefHeight(LIST_HEIGHT);
            this.customList.setPrefWidth(LIST_WIDTH);
            this.levelList.relocate(LEVEL_LIST_X, LEVEL_LIST_Y);
            this.levelList.setPrefWidth(LIST_WIDTH);
            this.levelList.setPrefHeight(LIST_HEIGHT + BUTTON_HEIGHT);
            this.levelList.setFocusTraversable(false);
            this.customList.setFocusTraversable(false);

            Controller.get().getAvailableLevels().forEach(level -> {
                final ImageView preview = new ImageView(level.getBackgroundImage());
                preview.setFitWidth(LEVELS_PREVIEW_WIDTH);
                preview.setFitHeight(LEVELS_PREVIEW_HEIGHT);
                final Text name = new Text(level.getName());
                final HBox h = new HBox(preview, name);
                name.setFont(LIST_FONT);
                name.setFill(Color.WHITE);
                name.setManaged(false);
                name.setLayoutX(this.levelList.getPrefWidth() / 2);
                name.setLayoutY(h.getBoundsInParent().getHeight() / 2);
                h.setStyle("-fx-background-color: #060606");
                h.setId("white_shadow");
                h.setOnMouseClicked(event -> {
                    if (this.levelList.getItems().contains(h)) {
                        this.customList.getItems().add(0, h);
                        this.levelList.getItems().remove(h);
                    } else {
                        this.customList.getItems().remove(h);
                        this.levelList.getItems().add(0, h);
                    }
                });
                this.levelList.getItems().add(h);

            });

        }

        public List<String> getCustomList() {
            return this.customList.getItems().stream().map(hbox -> ((Text) hbox.getChildren().get(1)).getText())
                                  .collect(Collectors.toList());
        }
    }
}