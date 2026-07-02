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

/**
 * Class of level five.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class LevelFive extends LevelImpl {

    private static final LevelFive SINGLETON = new LevelFive();
    private final PlayerGraphic playerGraphic;
    private final Score score;
    private final Obstacle obstacle;
    private final String obstacleTwisterTilePath;

    /**
     * Constructor.
     */
    private LevelFive() {
        super();

        this.playerGraphic = PlayerGraphicImpl.get();
        this.score = ScoreImpl.get();
        this.obstacle = ObstacleImpl.get();

        final ImageView imageTV1 = new ImageView(new Image("/images/toTheMoon.gif"));

        imageTV1.fitWidthProperty().setValue((Utilities.W / 21) * 1.7);
        imageTV1.fitHeightProperty().setValue((Utilities.H / 21) * 1.4);
        imageTV1.preserveRatioProperty();
        imageTV1.setTranslateX((Utilities.W / 21) * 4.5);
        imageTV1.setTranslateY((Utilities.H / 21) * 5.3);

        final ImageView imageTV2 = new ImageView(new Image("/images/evangelion.gif"));

        imageTV2.fitWidthProperty().setValue((Utilities.W / 21) * 1.05);
        imageTV2.fitHeightProperty().setValue((Utilities.H / 21) * 0.85);
        imageTV2.preserveRatioProperty();
        imageTV2.setTranslateX((Utilities.W / 21) * 4.05);
        imageTV2.setTranslateY((Utilities.H / 21) * 2.7);

        final ImageView imageTV3 = new ImageView(new Image("/images/gameBoy.gif"));

        imageTV3.fitWidthProperty().setValue((Utilities.W / 21) * 1.5);
        imageTV3.fitHeightProperty().setValue((Utilities.H / 21) * 1.7);
        imageTV3.preserveRatioProperty();
        imageTV3.setTranslateX((Utilities.W / 21) * 1.95);
        imageTV3.setTranslateY((Utilities.H / 21) * 1.9);

        this.obstacleTwisterTilePath = "/obstacles/obstacleTwisterTile.png";

        this.setMapBounds(4, 16, 9, 14);
        this.setCharStartFinish(10, 15, 10, 7);

        this.setBackground("/levelFive/levelFive.png");
        this.setTilePath("/images/touchTile5.png");

        this.playerGraphic.playerSetup(this.getCharStartCol(), this.getCharStartRow(), this.getGrid());

        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 4, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 5, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 9, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 11, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 16, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 8, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 10, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 10, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 13, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 14, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 13, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 14, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 5, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 6, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 5, 10, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 6, 10, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 16, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 13, 9, this.getGrid());

        this.setPlayableMap();

        this.getRoot().getChildren().add(imageTV1);
        this.getRoot().getChildren().add(imageTV2);
        this.getRoot().getChildren().add(imageTV3);
        this.getRoot().getChildren().add(this.getBackground());
        this.getRoot().getChildren().add(this.getGrid());
        this.score.addScoreToPane(this.getRoot());

        this.inputEvent();
    }

    @Override
    public void resetCurrentLevel() {
        this.playerGraphic.playerSetup(this.getCharStartCol(), this.getCharStartRow(), this.getGrid());

        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 4, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 5, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 9, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 11, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 16, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 8, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 10, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 10, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 13, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 14, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 13, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 14, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 5, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 6, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 5, 10, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 6, 10, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 16, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleTwisterTilePath, 21, 21, 13, 9, this.getGrid());

        this.score.addScoreToPane(this.getRoot());
        this.getRoot().getChildren().add(this.getGrid());
    }

    /**
     * Returns an instance of LevelFive.
     * 
     * @return an instance of LevelFive
     */
    public static LevelFive get() {
        return SINGLETON;
    }
}
