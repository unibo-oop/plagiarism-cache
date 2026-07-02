package controllers;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

/**
 * This class implement a GameLoop that moves all the view's objects.
 */
public class GameLoopImpl extends Application implements GameLoop {

    private static final double PIXEL_MOVEMENT = 2.75;
    private static final double SPAWN_FLAG = 125; 
    private final FlappyBirdController controller;
    private AnimationTimer gravityTimer;
    private int cont;
    private final Timeline timer = new Timeline(new KeyFrame(Duration.millis(200), (ActionEvent event) -> gravity = true));
    private boolean gravity = true;

    /**
     * This is constructor method that initialized the controller and give to the method {@link GameLoopImpl#start(Stage)} the primaryStage.
     * @param controller FlappyBirdController
     * @param primaryStage primaryStage is the window
     */
    public GameLoopImpl(final FlappyBirdControllerImpl controller, final Stage primaryStage) {
        this.controller = controller;
        this.start(primaryStage);
    }
    @Override
    public final void start(final Stage primaryStage) {
        gravityTimer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                try {
                    checkCollision();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (gravity) {
                    birdUpdateDown();
                } else {
                    birdUpdateUp();
                }
                controller.getTubeController().scrollTubes();
                cont++;
                if (cont == SPAWN_FLAG) {
                    cont = 0;
                    try {
                        controller.getTubeController().addTube();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        gravityTimer.start();
    }

    @Override
    public final void userAction() {
        timer.stop();
        gravity = false;
        timer.play();
    }

    @Override
    public final void birdUpdateDown() {
        this.controller.getBirdController().birdMovement(PIXEL_MOVEMENT);
    }

    @Override
    public final void birdUpdateUp() {
        this.controller.getBirdController().birdMovement(-PIXEL_MOVEMENT);
    }

    @Override
    public final void checkCollision() throws IOException {
        controller.checkCollision();
    }

    @Override
    public final void findCollision() {
            gravityTimer.stop();
    }
}



