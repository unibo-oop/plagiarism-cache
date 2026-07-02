package view.levels;

import controller.Score;
import controller.ScoreImpl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.level.LevelImpl;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleImpl;
import utilities.Utilities;
import view.PlayerGraphic;
import view.PlayerGraphicImpl;
import view.animations.CatAnimation;
import view.animations.MovingUpAnimation;

/**
 * Class of level four.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class LevelFour extends LevelImpl {

    private static final LevelFour SINGLETON = new LevelFour();
    private final PlayerGraphic playerGraphic;
    private final Score score;
    private final Obstacle obstacle;
    private final String obstacleOrangeBlueBallsPath;
    private final String obstacleRedYellowBallsPath;
    private final String obstacleWhiteBlackBallsPath;

    /**
     * Constructor.
     */
    private LevelFour() {
        super();

        this.playerGraphic = PlayerGraphicImpl.get();
        this.score = ScoreImpl.get();
        this.obstacle = ObstacleImpl.get();

        final MovingUpAnimation smoke1 = new MovingUpAnimation(35, 35, (Utilities.W / 21) * 6.25, Utilities.H / 4, 2500,
                0, "/images/smoke.png");
        final MovingUpAnimation smoke2 = new MovingUpAnimation(35, 35, (Utilities.W / 21) * 15.9,
                (Utilities.H / 21) * 5.6, 3000, 0, "/images/smoke.png");
        final CatAnimation cat = new CatAnimation(21, 21, (Utilities.W / 21) * 3, (Utilities.H / 21) * 15,
                "/catAnimationFrame/spriteCat.png");
        final ImageView imageTV = new ImageView(new Image("/images/imageTV.gif"));

        imageTV.fitWidthProperty().setValue((Utilities.W / 21) * 2.5);
        imageTV.fitHeightProperty().setValue((Utilities.H / 21) * 2.5);
        imageTV.preserveRatioProperty();
        imageTV.setTranslateX((Utilities.W / 21) * 11);
        imageTV.setTranslateY((Utilities.H / 21) * 1);

        this.obstacleOrangeBlueBallsPath = "/obstacles/obstacleOrangeBlueBalls.png";
        this.obstacleRedYellowBallsPath = "/obstacles/obstacleRedYellowBalls.png";
        this.obstacleWhiteBlackBallsPath = "/obstacles/obstacleWhiteBlackBalls.png";

        this.setMapBounds(5, 15, 11, 15);
        this.setCharStartFinish(10, 15, 10, 9);

        this.setBackground("/levelFour/levelFour.png");
        this.setTilePath("/images/touchTile4.png");

        this.playerGraphic.playerSetup(this.getCharStartCol(), this.getCharStartRow(), this.getGrid());

        this.obstacle.addObstacleToGrid(this.obstacleOrangeBlueBallsPath, 21, 21, 7, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleRedYellowBallsPath, 21, 21, 14, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleWhiteBlackBallsPath, 21, 21, 9, 13, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleRedYellowBallsPath, 21, 21, 12, 13, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleOrangeBlueBallsPath, 21, 21, 6, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleRedYellowBallsPath, 21, 21, 15, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleWhiteBlackBallsPath, 21, 21, 11, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleOrangeBlueBallsPath, 21, 21, 15, 11, this.getGrid());

        this.setPlayableMap();

        this.getRoot().getChildren().add(imageTV);
        this.getRoot().getChildren().add(this.getBackground());
        smoke1.addImageToPane(this.getRoot());
        smoke2.addImageToPane(this.getRoot());
        cat.addImageToPane(this.getRoot());
        this.getRoot().getChildren().add(this.getGrid());
        this.score.addScoreToPane(this.getRoot());

        this.inputEvent();
    }

    @Override
    public void resetCurrentLevel() {
        this.playerGraphic.playerSetup(this.getCharStartCol(), this.getCharStartRow(), this.getGrid());

        this.obstacle.addObstacleToGrid(this.obstacleOrangeBlueBallsPath, 21, 21, 7, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleRedYellowBallsPath, 21, 21, 14, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleWhiteBlackBallsPath, 21, 21, 9, 13, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleRedYellowBallsPath, 21, 21, 12, 13, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleOrangeBlueBallsPath, 21, 21, 6, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleRedYellowBallsPath, 21, 21, 15, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleWhiteBlackBallsPath, 21, 21, 11, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleOrangeBlueBallsPath, 21, 21, 15, 11, this.getGrid());

        this.score.addScoreToPane(this.getRoot());

        this.getRoot().getChildren().add(this.getGrid());
    }

    /**
     * Returns an instance of LevelFour.
     * 
     * @return an instance of LevelFour
     */
    public static LevelFour get() {
        return SINGLETON;
    }
}
