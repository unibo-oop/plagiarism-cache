package view.levels;

import controller.Score;
import controller.ScoreImpl;
import model.level.LevelImpl;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleImpl;
import utilities.Utilities;
import view.PlayerGraphic;
import view.PlayerGraphicImpl;
import view.animations.FishAnimation;
import view.animations.OneDirectionAnimation;

/**
 * Class of level one.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class LevelOne extends LevelImpl {

    private static final LevelOne SINGLETON = new LevelOne();
    private final PlayerGraphic playerGraphic;
    private final Score score;
    private final Obstacle obstacle;
    private final String obstacleBarrelPath;

    /**
     * Constructor.
     */
    private LevelOne() {
        super();

        this.playerGraphic = PlayerGraphicImpl.get();
        this.score = ScoreImpl.get();
        this.obstacle = ObstacleImpl.get();

        final OneDirectionAnimation movingShip = new OneDirectionAnimation("/images/ship.png", 3.5, 3.5,
                Utilities.W / 2 - Utilities.W / 7, +Utilities.H / 12, 0, 20000000);
        final FishAnimation movingFish = new FishAnimation(36, 36, Utilities.H / 1.5, Utilities.H / 6, Utilities.W / 4,
                Utilities.W / 6);
        final OneDirectionAnimation movingWaterFlowers = new OneDirectionAnimation("/images/waterFlowers.png", 21, 21,
                Utilities.W / 1.3, Utilities.H / 2, 0, 80000000);

        this.obstacleBarrelPath = "/obstacles/obstacleBarrel.png";

        this.setMapBounds(8, 12, 10, 14);
        this.setCharStartFinish(10, 16, 10, 8);

        this.setBackground("/levelOne/levelOne.png");
        this.setTilePath("/images/touchTile.png");

        this.playerGraphic.playerSetup(this.getCharStartCol(), this.getCharStartRow(), this.getGrid());

        this.obstacle.addObstacleToGrid(this.obstacleBarrelPath, 21, 14.61, 11, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBarrelPath, 21, 14.61, 12, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBarrelPath, 21, 14.61, 10, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBarrelPath, 21, 14.61, 11, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBarrelPath, 21, 14.61, 8, 10, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBarrelPath, 21, 14.61, 9, 10, this.getGrid());

        this.setPlayableMap();

        this.getRoot().getChildren().add(this.getBackground());
        movingShip.addImageToPane(this.getRoot());
        movingFish.addImageToPane(this.getRoot());
        movingWaterFlowers.addImageToPane(this.getRoot());
        this.getRoot().getChildren().add(this.getGrid());
        this.score.addScoreToPane(this.getRoot());

        this.inputEvent();
    }

    @Override
    public void resetCurrentLevel() {
        this.playerGraphic.playerSetup(this.getCharStartCol(), this.getCharStartRow(), this.getGrid());

        this.obstacle.addObstacleToGrid(this.obstacleBarrelPath, 21, 14.61, 11, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBarrelPath, 21, 14.61, 12, 14, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBarrelPath, 21, 14.61, 10, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBarrelPath, 21, 14.61, 11, 12, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBarrelPath, 21, 14.61, 8, 10, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBarrelPath, 21, 14.61, 9, 10, this.getGrid());

        this.score.addScoreToPane(this.getRoot());

        this.getRoot().getChildren().add(this.getGrid());
    }

    /**
     * Returns an instance of LevelOne.
     * 
     * @return an instance of LevelOne
     */
    public static LevelOne get() {
        return SINGLETON;
    }
}
