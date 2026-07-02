package view.levels;

import controller.Score;
import controller.ScoreImpl;
import model.level.LevelImpl;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleImpl;
import utilities.Utilities;
import view.PlayerGraphic;
import view.PlayerGraphicImpl;
import view.animations.MovingDownAnimation;
import view.animations.OneDirectionAnimation;

/**
 * Class of level two.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class LevelTwo extends LevelImpl {

    private static final LevelTwo SINGLETON = new LevelTwo();
    private final PlayerGraphic playerGraphic;
    private final Score score;
    private final Obstacle obstacle;
    private final String obstacleFlowersPath;
    private final String obstacleBushPath;
    private final String obstacleBranchPath;

    /**
     * Constructor.
     */
    private LevelTwo() {
        super();

        this.playerGraphic = PlayerGraphicImpl.get();
        this.score = ScoreImpl.get();
        this.obstacle = ObstacleImpl.get();

        final OneDirectionAnimation movingShip = new OneDirectionAnimation("/images/ship.png", 3.5, 3.5,
                Utilities.W / 2.4, Utilities.H / 1.4, 0, 20000000);
        final OneDirectionAnimation movingWaterFlowers = new OneDirectionAnimation("/images/waterFlowers.png", 21, 21,
                Utilities.W / 3.5, Utilities.H / 1.15, 0, 80000000);
        final MovingDownAnimation movingLeafOrange1 = new MovingDownAnimation(21, 21, Utilities.W / 5.5,
                Utilities.H / 4, 3000, 120, "/images/leafOrange.png");
        final MovingDownAnimation movingLeafOrange2 = new MovingDownAnimation(21, 21, Utilities.W / 1.2,
                Utilities.H / 8, 4000, 120, "/images/leafOrange.png");
        final MovingDownAnimation movingLeafOrange3 = new MovingDownAnimation(21, 21, Utilities.W / 1.15,
                Utilities.H / 1.4, 4500, 120, "/images/leafOrange.png");

        this.obstacleFlowersPath = "/obstacles/obstacleFlowers.png";
        this.obstacleBushPath = "/obstacles/obstacleBush.png";
        this.obstacleBranchPath = "/obstacles/obstacleBranch.png";

        this.setMapBounds(6, 14, 8, 11);
        this.setCharStartFinish(10, 13, 10, 6);

        this.setBackground("/levelTwo/levelTwo.png");
        this.setTilePath("/images/touchTile2.png");

        this.playerGraphic.playerSetup(this.getCharStartCol(), this.getCharStartRow(), this.getGrid());

        this.obstacle.addObstacleToGrid(this.obstacleFlowersPath, 21, 21, 6, 8, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleFlowersPath, 21, 21, 7, 10, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBushPath, 21, 21, 9, 10, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBranchPath, 21, 21, 9, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBushPath, 21, 21, 12, 9, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBranchPath, 21, 21, 12, 10, this.getGrid());

        this.setPlayableMap();

        this.getRoot().getChildren().add(this.getBackground());
        movingShip.addImageToPane(this.getRoot());
        movingWaterFlowers.addImageToPane(this.getRoot());
        movingLeafOrange1.addImageToPane(this.getRoot());
        movingLeafOrange2.addImageToPane(this.getRoot());
        movingLeafOrange3.addImageToPane(this.getRoot());
        this.getRoot().getChildren().add(this.getGrid());
        this.score.addScoreToPane(this.getRoot());

        this.inputEvent();
    }

    @Override
    public void resetCurrentLevel() {
        this.playerGraphic.playerSetup(this.getCharStartCol(), this.getCharStartRow(), this.getGrid());

        this.obstacle.addObstacleToGrid(this.obstacleFlowersPath, 21, 21, 6, 8, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleFlowersPath, 21, 21, 7, 10, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBushPath, 21, 21, 9, 10, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBranchPath, 21, 21, 9, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBushPath, 21, 21, 12, 9, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBranchPath, 21, 21, 12, 10, this.getGrid());

        this.score.addScoreToPane(this.getRoot());

        this.getRoot().getChildren().add(this.getGrid());
    }

    /**
     * Returns an instance of LevelTwo.
     * 
     * @return an instance of LevelTwo
     */
    public static LevelTwo get() {
        return SINGLETON;
    }
}
