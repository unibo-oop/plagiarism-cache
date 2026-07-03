package breakout.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import breakout.controller.AdvancedGameLoop;
import breakout.controller.ScoreManager;
import breakout.view.graphics.AdvancedStyle;
import breakout.view.graphics.GraphicStyle;
import breakout.view.utils.CustomButton;
import breakout.view.utils.Utils;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.CacheHint;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The main Scene for the advance mode game.
 */
public final class AdvancedGameScene extends AbstractGameScene {

    private static final double FONT_SIZE = 80;
    private static final double LIFE_SIZE = 23;
    private static final Image LIFE = new Image(Utils.getPath("/Images/Balls/Ball_RED.png"), LIFE_SIZE, LIFE_SIZE,
            false, false);
    private static final double POWERUP_SIZE = 18;

    private static final double MENU_BUTTON_SIZE = 46;
    private static final double SCORE_COLUMN_WIDTH = 30;
    private static final double LIFE_COLUMN_WIDTH = 15;
    private static final double LEVEL_COLUMN_WIDTH = 0;
    private static final double MENU_COLUMN_WIDTH = 40;
    private static final int PAUSE_TEXT_TIME = 1500;
    private static final double MESSAGE_DIALOG_WIDTH = 600;
    private static final double MESSAGE_DIALOG_HEIGHT = 250;

    private static final int LIFE_PER_ROW = 8;
    private static final int MAX_LIFE_ROWS = 2;

    private final Stage mainStage;
    private final AdvancedGameLoop gameLoop;
    private final Text score = new Text();
    private final List<ImageView> life = new ArrayList<>();
    private final List<ImageView> powerUps = new ArrayList<>();
    private final AnchorPane lifePane = new AnchorPane();
    private final Text levelName = new Text();
    private final Rectangle c = new Rectangle(MENU_BUTTON_SIZE, MENU_BUTTON_SIZE);
    private final CustomButton backMenu = new CustomButton(c, "");

    /**
     * @param style
     *            the style of the scene
     * @param mainStage
     *            The stage form where the scene is called
     */
    public AdvancedGameScene(final Stage mainStage, final GraphicStyle style) {
        super(mainStage, style);
        this.mainStage = mainStage;
        this.generateInvisibleLives();
        this.generateInvisiblePowerUps();
        this.score.setFont(style.getFont(FONT_SIZE));
        this.score.setId(style.getTextStyle());
        this.score.setCache(true);
        this.score.setCacheHint(CacheHint.SPEED);

        this.levelName.setFont(style.getFont(FONT_SIZE));
        this.levelName.setId(style.getTextStyle());
        this.levelName.setCache(true);
        this.levelName.setCacheHint(CacheHint.SPEED);
        this.backMenu.setImage(
                new Image(Utils.getPath("/Images/Menu_Button.png"), c.getWidth(), c.getHeight(), true, true));
        this.backMenu.setShapeStroke(StrokeType.OUTSIDE, 2, Color.WHITE);
        this.gameLoop = new AdvancedGameLoop(this);
        this.backMenu.setOnMouseClicked(e -> {
            if (!this.gameLoop.isPaused()) {
                this.gameLoop.pause();
            }
            final MessageDialog dialog = new MessageDialog(
                    "If you press Exit you will go back to main menu.\nAre you sure you want to lose your progress?",
                    MESSAGE_DIALOG_WIDTH, MESSAGE_DIALOG_HEIGHT, new ButtonType("Exit", ButtonData.OK_DONE),
                    new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));
            if (dialog.showAndWait().get().getButtonData() == ButtonData.OK_DONE) {
                this.gameLoop.finish();
                mainStage.setScene(new MainMenu(mainStage));
            } else {
                this.unPause();
            }
        });
        this.backMenu.setCache(true);
        this.backMenu.setCacheHint(CacheHint.SPEED);
        this.getStatsContainer().setPrefWidth(mainStage.getWidth());
        this.getStatsContainer().add(score, 0, 0);
        this.getStatsContainer().add(lifePane, 1, 0);
        this.getStatsContainer().add(levelName, 2, 0);
        this.getStatsContainer().add(backMenu, 3, 0);
        final ColumnConstraints scoreWidth = new ColumnConstraints();
        scoreWidth.setPercentWidth(SCORE_COLUMN_WIDTH);
        scoreWidth.setHalignment(HPos.CENTER);
        final ColumnConstraints lifeWidth = new ColumnConstraints();
        lifeWidth.setPercentWidth(LIFE_COLUMN_WIDTH);
        lifeWidth.setHalignment(HPos.CENTER);
        final ColumnConstraints levelWidth = new ColumnConstraints();
        levelWidth.setPercentWidth(LEVEL_COLUMN_WIDTH);
        levelWidth.setHalignment(HPos.LEFT);
        final ColumnConstraints backMenuWidth = new ColumnConstraints();
        backMenuWidth.setPercentWidth(MENU_COLUMN_WIDTH);
        backMenuWidth.setHalignment(HPos.RIGHT);
        this.getStatsContainer().getColumnConstraints().addAll(scoreWidth, lifeWidth, levelWidth, backMenuWidth);
        this.getStatsContainer()
            .setBackground(new Background(new BackgroundImage(new Image(Utils.getPath("/Images/statsBackground.png")),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));

        this.gameLoop.start();
    }

    /**
     * Shows the image in input in the list of active power ups.
     * 
     * @param image
     *            The image of the power up to show
     */
    public void updatePowerUp(final List<Image> image) {
        this.powerUps.forEach(i -> i.setImage(null));
        final Iterator<Image> iterator = image.iterator();
        this.powerUps.forEach(p -> {
            if (iterator.hasNext()) {
                p.setImage(iterator.next());
            }
        });
    }

    /**
     * {@inheritDoc}:<br/>
     * Shows player's life as a sequence of red balls(each ball is a life).<br/>
     * When {@link #LIFE_PER_ROW} count is reached additional balls are added in
     * a row below
     */
    public void updateLife(final int newLife) {
        this.life.stream().forEach(life -> life.setVisible(false));
        this.life.stream().limit(newLife).forEach(life -> life.setVisible(true));
    }

    /**
     * {@inheritDoc}
     */
    public void updateScore(final int newScore) {
        this.score.setText(String.valueOf(newScore));
    }

    /**
     * {@inheritDoc}
     */
    public void updateLevel(final String newLevel) {
        this.levelName.setText(newLevel);
    }

    /**
     * {@inheritDoc}
     */
    public void pause() {
        if (!this.gameLoop.isPaused()) {
            this.showText("PAUSE", PAUSE_TEXT_TIME);
            this.gameLoop.pause();
        } else {
            this.unPause();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void unPause() {
        this.showText("RESUMED", PAUSE_TEXT_TIME / 2);
        this.gameLoop.unPause();
    }

    /**
     * Creates a new dialog box where the player can save his score.
     */
    public void gameFinished() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                final ScoreManager s = new ScoreManager();
                final EndGameDialog endGame = new EndGameDialog(score.getText(), new AdvancedStyle());
                endGame.showAndWait().ifPresent(name -> {
                    if (!name.equals("")) {
                        try {
                            s.saveAdvancedScore(name, Integer.parseInt(score.getText()));
                        } catch (NumberFormatException | IOException e) {
                            e.printStackTrace();
                        }
                        mainStage.setScene(new MainMenu(mainStage));
                    } else {
                        endGame.close();
                        gameFinished();
                    }
                });
                mainStage.setScene(new MainMenu(mainStage));
            }
        });

    }

    private void generateInvisibleLives() {
        for (int row = 0; row < MAX_LIFE_ROWS; row++) {
            for (int column = 0; column < LIFE_PER_ROW; column++) {
                final ImageView img = new ImageView(LIFE);
                img.setFitHeight(LIFE_SIZE - 2);
                img.setFitWidth(LIFE_SIZE - 2);
                img.setVisible(false);
                this.lifePane.getChildren().add(img);
                img.relocate(column * LIFE_SIZE, (row + 0.5) * LIFE_SIZE);
                this.life.add(img);
            }
        }
    }

    private void generateInvisiblePowerUps() {
        for (int i = 0; i < LIFE_PER_ROW; i++) {
            final ImageView img = new ImageView();
            img.setFitHeight(POWERUP_SIZE);
            img.setFitWidth(POWERUP_SIZE);
            img.setPreserveRatio(true);
            this.lifePane.getChildren().add(img);
            img.relocate(i * LIFE_SIZE, (MAX_LIFE_ROWS + 0.5) * LIFE_SIZE);
            this.powerUps.add(img);
        }
    }

    @Override
    protected double centerTextSize() {
        return FONT_SIZE;
    }
}
