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
import view.animations.MovingDownAnimation;

/**
 * Class of level three.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class LevelThree extends LevelImpl {

    private static final LevelThree SINGLETON = new LevelThree();
    private final PlayerGraphic playerGraphic;
    private final Score score;
    private final Obstacle obstacle;
    private final String obstacleBinPath;
    private final String obstacleBushPath;
    private final String obstacleBranchPath;

    /**
     * Constructor.
     */
    private LevelThree() {
        super();

        this.playerGraphic = PlayerGraphicImpl.get();
        this.score = ScoreImpl.get();
        this.obstacle = ObstacleImpl.get();

        final String snowBallPath = "/images/snowBall.png";

        final FishAnimation noMovingFish = new FishAnimation(30, 30, Utilities.W / 3.5, Utilities.H / 3.5);
        final MovingDownAnimation snowBall1 = new MovingDownAnimation(28, 28, 1000, 0, snowBallPath);
        final MovingDownAnimation snowBall2 = new MovingDownAnimation(28, 28, 1050, 0, snowBallPath);
        final MovingDownAnimation snowBall3 = new MovingDownAnimation(28, 28, 1100, 0, snowBallPath);
        final MovingDownAnimation snowBall4 = new MovingDownAnimation(28, 28, 1150, 0, snowBallPath);
        final MovingDownAnimation snowBall5 = new MovingDownAnimation(28, 28, 1200, 0, snowBallPath);
        final MovingDownAnimation snowBall6 = new MovingDownAnimation(28, 28, 1250, 0, snowBallPath);
        final MovingDownAnimation snowBall7 = new MovingDownAnimation(28, 28, 1300, 0, snowBallPath);
        final MovingDownAnimation snowBall8 = new MovingDownAnimation(28, 28, 1350, 0, snowBallPath);
        final MovingDownAnimation snowBall9 = new MovingDownAnimation(28, 28, 1400, 0, snowBallPath);
        final MovingDownAnimation snowBall10 = new MovingDownAnimation(28, 28, 1450, 0, snowBallPath);
        final MovingDownAnimation snowBall11 = new MovingDownAnimation(28, 28, 1500, 0, snowBallPath);
        final MovingDownAnimation snowBall12 = new MovingDownAnimation(28, 28, 1550, 0, snowBallPath);
        final MovingDownAnimation snowBall13 = new MovingDownAnimation(28, 28, 1600, 0, snowBallPath);
        final MovingDownAnimation snowBall14 = new MovingDownAnimation(28, 28, 1650, 0, snowBallPath);
        final MovingDownAnimation snowBall15 = new MovingDownAnimation(28, 28, 1700, 0, snowBallPath);
        final MovingDownAnimation snowBall16 = new MovingDownAnimation(28, 28, 1750, 0, snowBallPath);
        final MovingDownAnimation snowBall17 = new MovingDownAnimation(28, 28, 1800, 0, snowBallPath);
        final MovingDownAnimation snowBall18 = new MovingDownAnimation(28, 28, 1850, 0, snowBallPath);
        final MovingDownAnimation snowBall19 = new MovingDownAnimation(28, 28, 1900, 0, snowBallPath);
        final MovingDownAnimation snowBall20 = new MovingDownAnimation(28, 28, 1950, 0, snowBallPath);

        this.obstacleBinPath = "/obstacles/obstacleBinSnow.png";
        this.obstacleBushPath = "/obstacles/obstacleBushSnow.png";
        this.obstacleBranchPath = "/obstacles/obstacleBranchSnow.png";

        this.setMapBounds(5, 15, 8, 11);
        this.setCharStartFinish(10, 13, 10, 6);

        this.setBackground("/levelThree/levelThree.png");
        this.setTilePath("/images/touchTile3.png");

        this.playerGraphic.playerSetup(this.getCharStartCol(), this.getCharStartRow(), this.getGrid());

        this.obstacle.addObstacleToGrid(this.obstacleBushPath, 21, 21, 7, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBranchPath, 21, 21, 13, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBranchPath, 21, 21, 10, 9, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBushPath, 21, 21, 13, 9, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBranchPath, 21, 21, 14, 9, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBinPath, 21, 11.5, 9, 8, this.getGrid());

        this.setPlayableMap();

        this.getRoot().getChildren().add(this.getBackground());
        noMovingFish.addImageToPane(this.getRoot());
        this.getRoot().getChildren().add(this.getGrid());
        snowBall1.addImageToPane(this.getRoot());
        snowBall2.addImageToPane(this.getRoot());
        snowBall3.addImageToPane(this.getRoot());
        snowBall4.addImageToPane(this.getRoot());
        snowBall5.addImageToPane(this.getRoot());
        snowBall6.addImageToPane(this.getRoot());
        snowBall7.addImageToPane(this.getRoot());
        snowBall8.addImageToPane(this.getRoot());
        snowBall9.addImageToPane(this.getRoot());
        snowBall10.addImageToPane(this.getRoot());
        snowBall11.addImageToPane(this.getRoot());
        snowBall12.addImageToPane(this.getRoot());
        snowBall13.addImageToPane(this.getRoot());
        snowBall14.addImageToPane(this.getRoot());
        snowBall15.addImageToPane(this.getRoot());
        snowBall16.addImageToPane(this.getRoot());
        snowBall17.addImageToPane(this.getRoot());
        snowBall18.addImageToPane(this.getRoot());
        snowBall19.addImageToPane(this.getRoot());
        snowBall20.addImageToPane(this.getRoot());
        this.score.addScoreToPane(this.getRoot());

        this.inputEvent();
    }

    @Override
    public void resetCurrentLevel() {
        this.playerGraphic.playerSetup(this.getCharStartCol(), this.getCharStartRow(), this.getGrid());

        this.obstacle.addObstacleToGrid(this.obstacleBushPath, 21, 21, 7, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBranchPath, 21, 21, 13, 11, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBranchPath, 21, 21, 10, 9, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBushPath, 21, 21, 13, 9, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBranchPath, 21, 21, 14, 9, this.getGrid());
        this.obstacle.addObstacleToGrid(this.obstacleBinPath, 21, 11.5, 9, 8, this.getGrid());

        this.score.addScoreToPane(this.getRoot());

        this.getRoot().getChildren().add(this.getGrid());
    }

    /**
     * Returns an instance of LevelThree.
     * 
     * @return an instance of LevelThree
     */
    public static LevelThree get() {
        return SINGLETON;
    }
}
