package it.dpg.minigames.ballgame.view;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import it.dpg.minigames.ballgame.controller.BallObserver;
import it.dpg.minigames.base.view.AbstractMinigameView;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class BallViewImpl extends AbstractMinigameView implements BallView {
    private final double viewSize;
    private final BallObserver observer;
    private final Group panel = new Group();
    private final NodesFactory factory = new NodesFactory(this::mapCoordinate);
    private Circle ball;
    private Text score;
    private Text readyText;
    private Text goText;
    private Text victoryText;

    public BallViewImpl(final double viewSize, BallObserver observer) {
        super();
        this.viewSize = viewSize;
        this.observer = observer;
        this.panel.setId("group_node");
    }

    /**
     * maps the coordinate in a range [0, 100] to a coordinate in a range [0, viewSize]
     *
     * @param coordinate coordinate in range [0, 100]
     * @return mapped coordinate to match screen size
     */
    private double mapCoordinate(final double coordinate) {
        if (coordinate < 0 || coordinate > 100) {
            throw new IllegalArgumentException();
        }
        return (coordinate / 100) * this.viewSize;
    }

    @Override
    public Scene createScene() {
        Scene scene = new Scene(panel, viewSize, viewSize);
        scene.setOnKeyPressed(ke -> {
            switch (ke.getCode()) {
                case UP:
                    observer.signalGoingUp(true);
                    break;
                case DOWN:
                    observer.signalGoingDown(true);
                    break;
                case LEFT:
                    observer.signalGoingleft(true);
                    break;
                case RIGHT:
                    observer.signalGoingRight(true);
                    break;
            }
        });
        scene.setOnKeyReleased(ke -> {
            switch (ke.getCode()) {
                case UP:
                    observer.signalGoingUp(false);
                    break;
                case DOWN:
                    observer.signalGoingDown(false);
                    break;
                case LEFT:
                    observer.signalGoingleft(false);
                    break;
                case RIGHT:
                    observer.signalGoingRight(false);
                    break;
            }
        });
        return scene;
    }

    @Override
    public void setupLevel(BallMinigameLevel level) {
        Platform.runLater(() -> {
            panel.getChildren().clear();
            ball = factory.createBall(level);
            score = factory.createScore();
            readyText = factory.createReady();
            goText = factory.createGo();
            victoryText = factory.createVictoryMessage();

            factory.createNodes(level)
                    .forEach(node -> panel.getChildren().add(node));
            panel.getChildren().add(ball);
            panel.getChildren().add(score);
        });
    }

    @Override
    public void positionBall(double xPos, double yPos) {
        Platform.runLater(() -> {
            ball.setCenterX(mapCoordinate(xPos));
            ball.setCenterY(mapCoordinate(yPos));
        });
    }

    @Override
    public void setScore(int score) {
        Platform.runLater(() -> this.score.setText("Score: " + score));
    }

    @Override
    public void setVictory() {
        Platform.runLater(() -> panel.getChildren().add(this.victoryText));
    }

    @Override
    public void setReady() {
        Platform.runLater(() -> panel.getChildren().add(this.readyText));
    }

    @Override
    public void removeReady() {
        Platform.runLater(() -> panel.getChildren().remove(this.readyText));
    }

    @Override
    public void setGo() {
        Platform.runLater(() -> panel.getChildren().add(this.goText));
    }

    @Override
    public void removeGo() {
        Platform.runLater(() -> panel.getChildren().remove(this.goText));
    }
}
