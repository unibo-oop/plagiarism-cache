package controllers;

import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.mario.MarioType;

import java.io.IOException;

public class GameLoopImpl extends Application implements GameLoop {

    private static final double MOVEMENT = 6;
    private static final double SPEED = 3.8;
    private final SuperMarioRunController controller;
    private final MarioController cont;
    private final ObstacleController obstacleController;
    private AnimationTimer gravity;
    private boolean marioGravity = true;
    private final Timeline timer = new Timeline(new KeyFrame(Duration.millis(420), (ActionEvent ev) -> marioGravity = true));
    private boolean marioOnFloor = false;
    private boolean hasMarioJumped = false;

    public GameLoopImpl(final SuperMarioRunControllerImpl controller, final ObstacleController obstacleController, final Stage primaryStage, final MarioController cont) {
        this.controller = controller;
        this.start(primaryStage);
        this.cont = cont;
        this.obstacleController = obstacleController;
    }

    @Override
    public void checkCollision() throws IOException {
        controller.checkCollision();
    }

    /**
     * This function start the game loop, it uses an AnimationTimer.
     * @param primaryStage
     */
    public final void start(final Stage primaryStage) {
        gravity = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                moveObstacle();
                try {
                    checkCollision();
                    controller.updateScore();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (marioGravity) {
                    marioFall();
                } else if (hasMarioJumped) {
                    marioJump();
                } 
            }
        };
        gravity.start();
    }

    @Override
    public void spacebarEvent() {
        timer.stop();
        if (marioOnFloor) {
            this.marioGravity = false;
            this.marioOnFloor = false;
            this.hasMarioJumped = true;
            if (this.controller.getMarioController().getMarioModel().getType() == MarioType.POWER) {
                this.controller.marioChangeSkin(MarioType.POWER_JUMP);
                this.controller.getMarioController().getMarioModel().setType(MarioType.POWER_JUMP);
            } else {
                this.controller.marioChangeSkin(MarioType.JUMP);
                this.controller.getMarioController().getMarioModel().setType(MarioType.JUMP);
            }
            timer.play();
        } else if (hasMarioJumped) {
            this.marioOnFloor = true;
            this.hasMarioJumped = false;
            marioGravity = true;
            timer.play();
        }
    }

    @Override
    public void marioFall() {
        this.controller.getMarioController().marioMovement(MOVEMENT);
    }

    @Override
    public void marioJump() {
        this.controller.getMarioController().marioMovement(-MOVEMENT);
    }

    @Override
    public void collisioned() {
        marioGravity = false;
        marioOnFloor = true;
        hasMarioJumped = false;
        if (this.controller.getMarioController().getMarioModel().getType() == MarioType.POWER_JUMP) {
            this.controller.marioChangeSkin(MarioType.POWER);
            this.controller.getMarioController().getMarioModel().setType(MarioType.POWER);
        } else {
            this.controller.marioChangeSkin(MarioType.RUN);
            this.controller.getMarioController().getMarioModel().setType(MarioType.RUN);
        }
        timer.stop();
    }

    @Override
    public void moveObstacle() {
        this.obstacleController.moveObstacle(SPEED);
    }

    @Override
    public void endGame() {
        gravity.stop();
    }
}
