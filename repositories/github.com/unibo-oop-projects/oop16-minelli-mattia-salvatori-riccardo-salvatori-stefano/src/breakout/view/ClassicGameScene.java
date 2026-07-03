package breakout.view;

import java.io.IOException;

import breakout.controller.ClassicGameLoop;
import breakout.controller.ScoreManager;
import breakout.view.graphics.ClassicStyle;
import breakout.view.graphics.GraphicStyle;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.CacheHint;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A class for the classic mode scene.
 */
public final class ClassicGameScene extends AbstractGameScene {

    private static final double TEXT_SIZE = 90;
    private static final double SCORE_COLUMN_WIDTH = 41.7;
    private static final double LIFE_COLUMN_WIDTH = 16.6;
    private static final double LEVEL_COLUMN_WIDTH = 41.7;
    private static final double RESUME_POSITION_X = 200;
    private static final double RESUME_POSITION_Y = 300;
    private static final double EXIT_POSITION_X = 771;
    private static final double EXIT_POSITION_Y = RESUME_POSITION_Y;

    private final Stage mainStage;
    private final ClassicGameLoop gameLoop;
    private final Text score = new Text();
    private final Text life = new Text();
    private final Text level = new Text();
    private final Text resume = new Text("RESUME");
    private final Text exit = new Text("EXIT");

    /**
     * @param style
     *            the style of the scene
     * @param mainStage
     *            The stage form where the scene is called
     */
    public ClassicGameScene(final Stage mainStage, final GraphicStyle style) {
        super(mainStage, style);
        this.mainStage = mainStage;
        this.getStatsContainer().setId(style.statsContainerStyle());
        this.getStatsContainer().setPrefWidth(mainStage.getWidth());
        this.getStatsContainer().add(score, 0, 0);
        this.getStatsContainer().add(life, 1, 0);
        this.getStatsContainer().add(level, 2, 0);
        final ColumnConstraints scoreWidth = new ColumnConstraints();
        scoreWidth.setPercentWidth(SCORE_COLUMN_WIDTH);
        scoreWidth.setHalignment(HPos.CENTER);
        final ColumnConstraints lifeWidth = new ColumnConstraints();
        lifeWidth.setPercentWidth(LIFE_COLUMN_WIDTH);
        lifeWidth.setHalignment(HPos.CENTER);
        final ColumnConstraints levelWidth = new ColumnConstraints();
        levelWidth.setPercentWidth(LEVEL_COLUMN_WIDTH);
        levelWidth.setHalignment(HPos.CENTER);
        this.getStatsContainer().getColumnConstraints().addAll(scoreWidth, lifeWidth, levelWidth);
        this.score.setFont(style.getFont(TEXT_SIZE));
        this.score.setId(style.getTextStyle());
        this.level.setFont(style.getFont(TEXT_SIZE));
        this.level.setId(style.getTextStyle());
        this.level.setCache(true);
        this.level.setCacheHint(CacheHint.SPEED);
        this.life.setFont(style.getFont(TEXT_SIZE));
        this.life.setId(style.getTextStyle());
        this.life.setCache(true);
        this.life.setCacheHint(CacheHint.SPEED);
        this.gameLoop = new ClassicGameLoop(this);
        this.exit.setOnMousePressed(event -> {
            this.gameLoop.finish();
            mainStage.setScene(new MainMenu(mainStage));
        });
        this.resume.setOnMousePressed(event -> {
            this.pause();
        });

        this.getCenterPane().getChildren().add(resume);
        this.getCenterPane().getChildren().add(exit);
        this.resume.setVisible(false);
        this.resume.setId(style.getTextStyle());
        this.resume.setFont(style.getFont(TEXT_SIZE));
        this.resume.relocate(RESUME_POSITION_X, RESUME_POSITION_Y);
        this.exit.setVisible(false);
        this.exit.setId(style.getTextStyle());
        this.exit.setFont(style.getFont(TEXT_SIZE));
        this.exit.relocate(EXIT_POSITION_X, EXIT_POSITION_Y);
        this.gameLoop.start();
    }

    /**
     * {@inheritDoc}
     */
    public void updateLife(final int newLife) {
        this.life.setText(String.valueOf(newLife));
    }

    /**
     * {@inheritDoc}
     */
    public void updateLevel(final String newLevel) {
        this.level.setText(newLevel);
    }

    /**
     * {@inheritDoc}<br/>
     * Shows the score always with 3 digits. <br/>
     * For example:<br/>
     * 1 is 001<br/>
     * 10 is 010
     */
    public void updateScore(final int newScore) {
        final String zeros = newScore >= 100 ? "" : newScore >= 10 ? "0" : "00";
        this.score.setText(zeros + newScore);
    }

    /**
     * {@inheritDoc}
     */
    public void pause() {
        if (!this.gameLoop.isPaused()) {
            this.gameLoop.pause();
            this.resume.setVisible(true);
            this.exit.setVisible(true);
        } else {
            this.unPause();
        }

    }

    /**
     * {@inheritDoc}
     */
    public void unPause() {
        this.gameLoop.unPause();
        this.resume.setVisible(false);
        this.exit.setVisible(false);
    }

    @Override
    protected double centerTextSize() {
        return TEXT_SIZE;
    }

    /**
     * Creates a new dialog box where the player can save his score.
     */
    public void gameFinished() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                final ScoreManager scoreSaver = new ScoreManager();
                final EndGameDialog endGame = new EndGameDialog(score.getText(), new ClassicStyle());
                endGame.showAndWait().ifPresent(name -> {
                    if (!name.equals("")) {
                        try {
                            scoreSaver.saveClassicScore(name, Integer.parseInt(score.getText()));
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
}
