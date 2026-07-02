package it.dpg.minigames.jumpgame.view;

import it.dpg.minigames.base.view.AbstractMinigameView;
import it.dpg.minigames.jumpgame.controller.input.InputObserver;
import it.dpg.minigames.jumpgame.controller.input.MoveLeft;
import it.dpg.minigames.jumpgame.controller.input.MoveRight;
import it.dpg.minigames.jumpgame.controller.input.StopMovement;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Basic implementation of JumpMinigameView
 * @author Davide Picchiotti
 * @see JumpMinigameView
 * */

public class JumpMinigameViewImpl extends AbstractMinigameView implements JumpMinigameView {

    private static final Color PLAYER_COLOR = Color.BLUEVIOLET;
    private static final Color PLATFORM_COLOR = Color.BLACK;

    private static final String SCORE_TEXT = "SCORE: ";

    private Pane pane;
    private Rectangle player;
    private Text scoreText;
    private Map<Integer, Rectangle> platforms = new HashMap<>();
    private Scene scene;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private InputObserver observer;

    @Override
    protected Scene createScene() {
        pane = new Pane();
        scoreText = new Text(0, 20, SCORE_TEXT.concat("0"));
        scoreText.setFont(new Font(20));
        pane.getChildren().add(scoreText);

        scene = new Scene(pane);

        scene.setOnKeyPressed(k -> {
            if(k.getCode() == KeyCode.LEFT && !leftPressed) {
                observer.notifyInput(new MoveLeft());
                leftPressed = true;
            } else if(k.getCode() == KeyCode.RIGHT && !rightPressed) {
                observer.notifyInput(new MoveRight());
                rightPressed = true;
            }
        });

        scene.setOnKeyReleased(k -> {
            if(k.getCode() == KeyCode.LEFT || k.getCode() == KeyCode.RIGHT) {
                observer.notifyInput(new StopMovement());
                leftPressed = false;
                rightPressed = false;
            }
        });

        return scene;
    }

    @Override
    public void setGameSize(int width, int height) {
        pane.setPrefSize(width, height);
        scene.getRoot().resize(pane.getPrefWidth(), pane.getHeight());

    }

    @Override
    public void createPlayer(final int x, final int y, final int size) {
        Platform.runLater(() -> {
            player = new Rectangle(size, size, PLAYER_COLOR);
            player.setX(x);
            player.setY(mapY(y));
            player.setArcWidth(40);
            player.setArcHeight(40);
            pane.getChildren().add(player);
        });
    }

    @Override
    public void updatePlayer(final int x, final int y) {
        Platform.runLater(() -> {
            if(player != null) {
                player.setX(x);
                player.setY(mapY(y));
            } else {
                throw new UnsupportedOperationException("You must create a player before updating it");
            }
        });
    }

    @Override
    public void updatePlatform(final int x, final int y, final int width, final int height, final int id, final boolean exist) {
        Platform.runLater(() -> {
            if (platforms.get(id) == null && exist) {
                Rectangle r = new Rectangle(width, height, PLATFORM_COLOR);
                r.setX(x);
                r.setY(mapY(y));
                platforms.put(id, r);
                pane.getChildren().add(r);
            } else if(exist) {
                Rectangle r = platforms.get(id);
                if (r != null) {
                    r.setX(x);
                    r.setY(mapY(y));
                }
            } else {
                pane.getChildren().remove(platforms.get(id));
                platforms.remove(id);
            }
        });
    }

    @Override
    public void updateScore(int score) {
        Platform.runLater(() -> scoreText.setText(SCORE_TEXT.concat(String.valueOf(score))));
    }

    @Override
    public void setInputObserver(final InputObserver observer) {
        this.observer = observer;
    }

    private double mapY(final int y) {
        return pane.getHeight() - y;
    }
}
