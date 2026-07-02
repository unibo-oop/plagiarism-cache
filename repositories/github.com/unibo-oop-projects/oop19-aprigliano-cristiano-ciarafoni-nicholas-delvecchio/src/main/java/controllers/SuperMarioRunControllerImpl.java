package controllers;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.mario.MarioType;
import model.score.Score;
import model.score.ScoreImpl;
import view.*;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import utility.InsertScores;

public class SuperMarioRunControllerImpl implements SuperMarioRunController, SuperMarioRunGameViewObserver {

    private static final double POWERUP_MUSHROOM_HEIGHT = 30.0;
    private static final int DISAPPEAR_OBSTACLE = 4000;

    private final ObstacleController obstacleController;
    private final MarioController marioController;
    private final FloorController floorController;
    private final SuperMarioRunView marioRunView;
    private final GameLoop gameLoop;
    private final Score score;
    private final EndGame endGame;
    private final InsertScores insertScores;
    private String username;
    private MarioType marioType;

    public SuperMarioRunControllerImpl(final Stage firstStage, String name) {
        username = name;
        insertScores = new InsertScores();
        score = new ScoreImpl();
        floorController = new FloorControllerImpl();
        obstacleController = new ObstacleControllerImpl();
        marioController = new MarioControllerImpl();
        marioRunView = new SuperMarioRunViewImpl(firstStage, this);
        marioRunView.start();
        endGame = new EndGameImpl(this.marioRunView, score.getScore());
        gameLoop = new GameLoopImpl(this, obstacleController, firstStage, marioController);
    }

    @Override
    public void newGame() {
        this.addNode(marioController.getMarioView().getMario());
        this.addNode(floorController.getFloorView().getFloor());
        for (Node n : obstacleController.getNodeList()) {
            this.addNode(n);
        }
        this.marioType = this.marioController.getMarioModel().getType();
    }

    private String checkNameNull() {
        if (username.isBlank() || username.isEmpty()) {
            username = "User" + new Date(System.currentTimeMillis());
        }
        return username;
    }

    @Override
    public void pressSpace() {
        gameLoop.spacebarEvent();
    }

    @Override
    public ObstacleController getObstacleController() {
        return this.obstacleController;
    }

    @Override
    public MarioController getMarioController() {
        return marioController;
    }

    @Override
    public FloorController getFloorController() {
        return floorController;
    }

    @Override
    public void addNode(final Node n) {
        this.marioRunView.addChildren(n);
    }

    @Override
    public void checkCollision() throws IOException {
        if (marioController.getMarioView().getMario().getY() + marioController.getMarioView().getMario().getHeight() > floorController.getFloorView().getFloor().getY()) {
            marioController.getMarioView().getMario().setY(floorController.getFloorView().getFloor().getY() - marioController.getMarioView().getMario().getHeight());
            gameLoop.collisioned();
        }
        if (this.checkObstacleCollision(marioController.getMarioView().getMario())) {
            if (this.marioController.getMarioModel().getType() == MarioType.POWER) {
                this.marioChangeSkin(MarioType.POWER);
            } else {
                gameLoop.endGame();
                endGame.setResult(this.checkNameNull(), score.getScore());
                this.marioRunView.endGame(score.getScore());
                this.insertScores.insertOrderedUserScore(this.checkNameNull(), ((Integer) (score.getScore())).toString());
            }
        }
    }

    @Override
    public final boolean checkObstacleCollision(final Rectangle mario) {
        final AtomicInteger flag = new AtomicInteger(0);
        List<Rectangle> list = new LinkedList<>();

        for (ObstacleView o : this.obstacleController.getObstacleList()) {
            list.add(o.getObstacle());
        }

        list.forEach((value) -> {
            final Shape intersect = Shape.intersect(mario, value);
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                flag.set(1);
                if (value.getHeight() == POWERUP_MUSHROOM_HEIGHT) {
                    if (this.marioController.getMarioModel().getType() == MarioType.RUN) {
                        this.marioController.getMarioModel().setType(MarioType.POWER);
                    }
                    if (this.marioController.getMarioModel().getType() == MarioType.POWER_JUMP) {
                        flag.set(0);
                    }
                    value.setX(value.getX() - DISAPPEAR_OBSTACLE);
                } else {
                    if (this.marioController.getMarioModel().getType() == MarioType.POWER 
                            || this.marioController.getMarioModel().getType() == MarioType.POWER_JUMP) {
                        value.setX(value.getX() - DISAPPEAR_OBSTACLE);
                        this.marioController.getMarioModel().setType(MarioType.RUN);
                        this.marioChangeSkin(this.marioController.getMarioModel().getType());
                        flag.set(0);
                    }
                }
            }
        });
        return flag.get() != 0;
    }

    @Override
    public void marioChangeSkin(final MarioType type) {
        this.marioController.changeMarioSkin(type);
    }

    @Override
    public void updateScore() {
        score.incrementScore();
        marioRunView.setScore(score.getScore());
    }
}
