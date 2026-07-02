package controllers;


import models.Entity;
import models.MOVEMENT;
import models.Pair;
import models.Player;
import models.Point2D;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;
import models.WorldMapImpl;
import view.BoardView;
import view.BoardViewJavaFX;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.Pane;

import java.util.List;

/**
 * BoardController is a presenter class (we called it a controller to make its role more clear)
 * that connects the logical game map with its graphical counterpart. It takes elements from the WorldMap model
 * so that they can be used by the BoardView to properly show each event and scenario to the user
 */
public final class BoardController {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int ENEMIES = 8;
    private static final int COLLECTABLES = 10;

    private final SpawnStrategy spawnStrat;
    private final WorldMap gameWorldMap;
    private BoardView worldMapView;
    private Point2D previousPlayerPos;
    @FXML
    private Pane mainPane;

    /**
     * This is the constructor of the controller.
     */
    public BoardController() {
        this.spawnStrat  = new RandomSpawnStrategy();
        this.gameWorldMap = new WorldMapImpl(WIDTH, HEIGHT, ENEMIES, COLLECTABLES, spawnStrat);
    }

    /**
     * Get the key pressed by the user and call for the update the map according to the type of movement key
     * pressed by user (up,down,left,right).
     * 
     * @param keyEvent Key pressed by user
     */
    @FXML
    private void onDirectionalKeyPress(final KeyEvent keyEvent) {
        KeyCode key = keyEvent.getCode();
        switch (key) {
            case W: 
            case UP: this.updateMap(MOVEMENT.DOWN);
                break;
            case S:
            case DOWN: this.updateMap(MOVEMENT.UP);
                break;
            case A:
            case LEFT: this.updateMap(MOVEMENT.LEFT);
                break;
            case D:
            case RIGHT: this.updateMap(MOVEMENT.RIGHT);
                break;
        default:
            break;
        }
    }

    /**
     * Call for the initialization of the map view.
     */
    @FXML
    private void initialize() {
        Platform.runLater(() -> this.initalizeView());
    }

    /**
     * Properly initialize the view of the map, instantiating necessary elements.
     */
    private void initalizeView() {
        this.worldMapView = new BoardViewJavaFX(this.mainPane, HEIGHT, WIDTH);
        this.previousPlayerPos = this.gameWorldMap.getPlayerPos();
        List<Pair<Point2D, Class<? extends Entity>>> allEntities = this.gameWorldMap.getEntitiesPos();
        this.worldMapView.initializeView(this.previousPlayerPos, allEntities);
    }

    /**
     * Update the game map both logically and graphically, depending on the key pressed by the user.
     * 
     * @param movement Movement depending on the key pressed by user
     */
    private void updateMap(final MOVEMENT movement) {
        this.gameWorldMap.movePlayer(movement);
        Point2D playerPos = this.gameWorldMap.getPlayerPos();
        if (playerPos.equals(this.previousPlayerPos)) {
            Player player = (Player) this.gameWorldMap.getBoard().get(playerPos).get();
            player.setHealth(-1);
            System.out.println("Damage Taken");
            System.out.println(player.getHealth());
            if (player.getHealth() <= 0) {
                this.worldMapView.gameOver();
            }
        } else {
            this.worldMapView.updateWorldMap(playerPos, this.gameWorldMap.getEntitiesPos().size(), this.gameWorldMap.getEntitiesPos());
        }
        this.previousPlayerPos = playerPos;
    }
}
