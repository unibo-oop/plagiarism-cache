package it.unibo.roguekong.view.impl;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.EnemyImpl;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.impl.Tile;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.view.RogueKongView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.*;

/**
 * 1. This implementation defines several layers (Panes) and a Scene where
 * all its contents are rendered
 * 2. The scene renders said root Pane and takes as input widthxheight
 * n pixels
 */
public class GameView implements RogueKongView {
    private final static int WIDTH = 960;
    private final static int HEIGTH = 640;
    private final static int TILE_SIZE = 32;
    private String lastSpritePath;
    private final Pane root;
    private final Pane background;
    private final VBox ui;
    private final Pane map;
    private final Pane playerRender;
    private final Pane enemyRender;
    private final Map<EnemyImpl, ImageView> enemyViews = new IdentityHashMap<>();
    private Pane powerUpLayer;
    private VBox powerUpBox;
    private Label livesLabel;
    private int lastLives = -1;
    private final Scene scene;

    private Runnable onKill;
    private Runnable onHit;
    private final Set<KeyCode> keysPressed = new HashSet<>();

    private ImageView playerSpriteView;
    private ImageView enemySpriteView;

    /**
     * GameView prepares the layout, several layers where map and player are loaded, event driven inputs
     */
    public GameView(){
        this.root = new Pane();
        this.map = new Pane();
        this.background = new Pane();
        this.ui = new VBox();
        this.playerRender = new Pane();
        this.enemyRender = new Pane();

        this.createLivesUI();

        this.root.getChildren().addAll(background, map, enemyRender, playerRender, ui);
        /*
         * setFocusTraversable makes the user input readable
         */
        this.root.setFocusTraversable(true);

        createPowerUpLayer();

        this.scene = new Scene(root, WIDTH, HEIGTH);

        /*
         * Events needed for user keys input.
         * All the registered input are inserted into a Queue (the hashset declared above) and removed when released.
         */
        this.scene.setOnKeyPressed(e -> keysPressed.add(e.getCode()));
        this.scene.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));

        this.scene.getStylesheets().add(
                getClass().getResource("/css/menu.css").toExternalForm()
        );
    }

    @Override
    public Scene getScene(){
        return scene;
    }

    public Pane getRoot(){
        return root;
    }

    /**
     * The loaders beneath load both the map and the background.
     * Background and map are loaded on different layers, but are both Tile types
     */
    public void loadMap(TileManager tileManager){
        int[][] mapMatrix = tileManager.getGameMap();
        int[][] backgroundMatrix = tileManager.getBackgroundMap();

        Tile[] tileSet = tileManager.getTileSet();
        Tile[] tileSetBackground = tileManager.getTileSetBackground();

        map.getChildren().clear();
        background.getChildren().clear();

        for(int i = 0; i < tileManager.getRows(); i++){
            for(int j = 0; j < tileManager.getCols(); j++){
                int mapTileIndex = mapMatrix[i][j];
                int backgroundTileIndex = backgroundMatrix[i][j];

                Tile mapTile = tileSet[mapTileIndex];
                Tile backgroundTile = tileSetBackground[backgroundTileIndex];

                Image mapTileImage = new Image(
                        getClass().getResourceAsStream(mapTile.getImage())
                );

                Image backgroundTileImage = new Image(
                        getClass().getResourceAsStream(backgroundTile.getImage())
                );

                ImageView mapTileView = new ImageView(mapTileImage);
                ImageView backgroundTileView = new ImageView(backgroundTileImage);

                mapTileView.setFitWidth(TILE_SIZE);
                mapTileView.setFitHeight(TILE_SIZE);
                backgroundTileView.setFitWidth(TILE_SIZE);
                backgroundTileView.setFitHeight(TILE_SIZE);

                mapTileView.setX(j * TILE_SIZE);
                mapTileView.setY(i * TILE_SIZE);
                backgroundTileView.setX(j * TILE_SIZE);
                backgroundTileView.setY(i * TILE_SIZE);

                background.getChildren().add(backgroundTileView);
                map.getChildren().add(mapTileView);
            }
        }
    }

    /**
     * Renders the player. It has to be called each frame in the main game loop.
     * @param player gets player as input in order to load its sprite. Size are set manually here.
     */
    public void renderPlayer(PlayerImpl player) {
        String currentSprite = player.getSprite();

        if (playerSpriteView == null) {
            playerSpriteView = new ImageView();
            playerSpriteView.setFitWidth(TILE_SIZE);
            playerSpriteView.setFitHeight(TILE_SIZE);
            playerRender.getChildren().add(playerSpriteView);
        }

        if (!currentSprite.equals(lastSpritePath)) {
            Image spriteImage = new Image(
                    getClass().getResourceAsStream(currentSprite)
            );
            playerSpriteView.setImage(spriteImage);
            lastSpritePath = currentSprite;
        }

        playerSpriteView.setX(player.getPosition().getX());
        playerSpriteView.setY(player.getPosition().getY());
    }

    public void renderEnemies(List<EnemyImpl> enemies) {
        enemyViews.entrySet().removeIf(entry -> {
            EnemyImpl e = entry.getKey();
            if (e.isDead()) {
                enemyRender.getChildren().remove(entry.getValue());
            }
            return e.isDead();
        });

        for (EnemyImpl enemy : enemies) {
            if (enemy.isDead()) {
                continue;
            }

            ImageView view = enemyViews.get(enemy);
            if (view == null) {
                Image sprite = new Image(getClass().getResourceAsStream(enemy.getSprite()));
                view = new ImageView(sprite);
                view.setFitWidth(TILE_SIZE);
                view.setFitHeight(TILE_SIZE);

                enemyViews.put(enemy, view);
                enemyRender.getChildren().add(view);
            }

            view.setX(enemy.getPosition().getX());
            view.setY(enemy.getPosition().getY());
        }
    }

    public void clearEnemies() {
        enemyRender.getChildren().clear(); // rimuove tutti i ImageView dei nemici
        enemyViews.clear(); // svuota la mappa
    }



    /**
     * Creates the powerup Overlay where the player can choose the random
     * powerups the game picks.
     */
    private void createPowerUpLayer(){
        powerUpLayer = new Pane();
        powerUpLayer.setPrefSize(WIDTH, HEIGTH);

        /*
         * Inline CSS style
         */
        powerUpLayer.setStyle("""
                -fx-background-color: rgba(0, 0, 0, 0.6);
                """);

        powerUpBox = new VBox(15);
        powerUpBox.setStyle("""
                -fx-padding: 20;
                """);

        /*
         * Centers the box
         */
        powerUpBox.setLayoutX(WIDTH / 2.0 - 150);
        powerUpBox.setLayoutY(HEIGTH / 2.0 - 120);

        powerUpLayer.getChildren().add(powerUpBox);
        powerUpLayer.setVisible(false);

        ui.getChildren().add(powerUpLayer);
    }

    /**
     * Renders the power up panel in order for the player to click and choose the
     * preferred Power Up. Clicking the button with said power up name applies the effect on the player.
     * @param player needed to apply the power up effect
     * @param powerUps is a list of powerups, shuffled
     * @param onChoice sets the action of the buttons
     */
    public void showPowerUpPanel(PlayerImpl player, List<PowerUp> powerUps, Runnable onChoice){
        powerUpBox.getChildren().clear();

        for(PowerUp p : powerUps){
            Button button = new Button(p.getName());
            button.setPrefWidth(260);

            button.setOnAction(e -> {
                p.applyEffect(player);
                hidePowerUpPanel();
                if(onChoice != null){
                    onChoice.run();
                }
            });

            powerUpBox.getChildren().add(button);
        }

        powerUpLayer.setVisible(true);
    }

    private void createLivesUI(){
        livesLabel = new Label("Lives: 0");
        livesLabel.setLayoutX(20);
        livesLabel.setLayoutY(20);

        livesLabel.setStyle("""
                -fx-font-size: 40;
                -fx-font-weight: bold;
                -fx-padding: 0 0 0 30;
                -fx-text-fill: red;
                """);

        ui.getChildren().add(livesLabel);
    }

    public void renderLives(PlayerImpl player){
        int currentLives = player.getLives().getLives();

        String heartsLives = "Lives: ";

        for(int i = 0; i < currentLives; i++) {
            heartsLives += "❤";
        }
        if(currentLives != lastLives){
            livesLabel.setText(heartsLives);
            lastLives = currentLives;
        }
    }

    /**
     * Simply hides the power up panel
     */
    public void hidePowerUpPanel(){
        powerUpLayer.setVisible(false);
    }

    /**
     * Returns true if key is in the input queue
     */
    public boolean isKeyPressed(KeyCode key){
        return keysPressed.contains(key);
    }

    public void clearKeyPressed(){
        keysPressed.clear();
    }

    public void setOnKill(Runnable r){
        this.onKill = r;
    }

    public void setOnHit(Runnable r) {
        this.onHit = r;
    }

    private void runIfNotNull(Runnable r) {
        if (r != null) r.run();
    }
}
