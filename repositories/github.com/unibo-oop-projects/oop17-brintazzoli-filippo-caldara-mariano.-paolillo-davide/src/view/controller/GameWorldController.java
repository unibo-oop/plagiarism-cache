package view.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.scene.ViewScenes;
import view.utility.ViewUtils;

/**
 * Controller class for the game world. It draws the objects according to the
 * controller updating.
 */
public class GameWorldController extends ViewController {

    private static final double LIFE_OPACITY = 0.6;
    private static final double WIDTH_RAPPORT = 30; 
    private static final double HEIGHT_RAPPORT = 20; 
    private static final double LIFE_DIMENSION = 40;
    private static final double PROJECTILE_DIMENSION = 10;
    private final Image life = new Image("res/images/life.png", LIFE_DIMENSION, LIFE_DIMENSION, false, false); 
    private final Image projectile = new Image("res/images/ball.png", PROJECTILE_DIMENSION, PROJECTILE_DIMENSION, false, false); 
    private final List<ImageView> projectiles = new ArrayList<>();
    private final List<ImageView> playerLives = new ArrayList<>();
    private final List<ImageView> enemyLives = new ArrayList<>();
    private Controller controller;

    @FXML
    private ImageView backGround;

    @FXML
    private ImageView playerTank;

    @FXML
    private ImageView playerCannon;

    @FXML
    private Group worldGroup;

    @FXML
    private Canvas worldCanvas;

    @FXML
    private ImageView enemyTank;

    @FXML
    private ImageView enemyCannon;

    @Override
    public final void init(final Controller controller) {
        this.controller = controller;
        this.controller.getLevel().setLevelStarted();
    }

    /**
     * Manage the {@link KeyEvent} to move the player {@link Tank}.
     */
    public void moveTank() {
        ViewUtils.getScene().setOnKeyPressed(e -> this.controller.getControllerInput().setKeyInput(e, true));
        ViewUtils.getScene().setOnKeyReleased(e -> this.controller.getControllerInput().setKeyInput(e, false));
    }

    /**
     * Manage the {@link MouseEvent} to move the player cannon.
     */
    public void moveCannon() {
        ViewUtils.getScene().setOnMouseMoved(e -> this.controller.getControllerInput().setMouseMovement(e));
    }

    /**
     * Manage the {@link MouseEvent} to allow the player {@link Tank} shot.
     */
    public void shot() {
        ViewUtils.getScene().setOnMouseClicked(e -> this.controller.getControllerInput().setMouseClicked(e));
    }

    /**
     * Update the {@link View} and the game loop according to the {@link LevelImpl}.
     */
    public void updateView() {
        if (this.controller.getLevel().isGameEnded()) {
            try {
                ViewScenes.END_GAME.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                        this.controller);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (this.controller.getLevel().isLevelEnded()) {
            try {
                ViewScenes.END_LEVEL.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                        this.controller);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (this.controller.getLevel().isGameOver()) {
            try {
                ViewScenes.LOSE.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                        this.controller);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.repaint();
        }
    }

    /**
     * Repaint the objects of the {@link View}.
     */
    private void repaint() {
        this.drawBackGround();
        this.drawTank();
        this.drawCannon();
        this.drawProjectiles();
        this.drawLives();

    }

    /**
     * Draw the lives of the two {@link Tank}.
     */
    private void drawLives() {
        this.playerLives.forEach(p -> this.worldGroup.getChildren().removeAll(this.playerLives));
        this.playerLives.removeAll(this.playerLives);
        for (int i = 0; i < this.controller.getControllerOutput().getPlayerLifes(); i++) {
            final ImageView lifeImage = new ImageView(this.life);
            lifeImage.setLayoutX(i * ViewUtils.getScene().getWidth() / WIDTH_RAPPORT);
            lifeImage.setLayoutY(0);
            lifeImage.setFitWidth(ViewUtils.getScene().getWidth() / WIDTH_RAPPORT);
            lifeImage.setFitHeight(ViewUtils.getScene().getHeight() / HEIGHT_RAPPORT);
            lifeImage.setOpacity(LIFE_OPACITY);
            this.playerLives.add(lifeImage);
        }
        this.enemyLives.forEach(p -> this.worldGroup.getChildren().removeAll(this.enemyLives));
        this.enemyLives.removeAll(this.enemyLives);
        for (int i = 0; i < this.controller.getControllerOutput().getEnemyLifes(); i++) {
            final ImageView lifeImage = new ImageView(this.life);
            lifeImage.setFitWidth(ViewUtils.getScene().getWidth() / WIDTH_RAPPORT);
            lifeImage.setFitHeight(ViewUtils.getScene().getHeight() / HEIGHT_RAPPORT);
            lifeImage.setLayoutX(ViewUtils.getScene().getWidth() - lifeImage.getFitWidth()
                    - (i * ViewUtils.getScene().getWidth() / WIDTH_RAPPORT));
            lifeImage.setLayoutY(0);
            lifeImage.setOpacity(LIFE_OPACITY);
            this.enemyLives.add(lifeImage);
        }
        this.worldGroup.getChildren().addAll(this.playerLives);
        this.worldGroup.getChildren().addAll(this.enemyLives);
    }

    /**
     * Draw the background of the scene.
     */
    private void drawBackGround() {
        this.worldCanvas.setWidth(ViewUtils.getScene().getWidth());
        this.worldCanvas.setHeight(ViewUtils.getScene().getHeight());
        this.backGround.setFitWidth(this.worldCanvas.getWidth());
        this.backGround.setFitHeight(this.worldCanvas.getHeight());
    }

    /**
     * Draw the list of {@link Projectile}.
     */
    private void drawProjectiles() {
        this.worldGroup.getChildren().removeAll(this.projectiles);
        this.projectiles.removeAll(this.projectiles);
        this.controller.getControllerOutput().getProjectiles().forEach(p -> {
            final ImageView projectileImage = new ImageView(projectile);
            projectileImage.setLayoutX(p.getFirst());
            projectileImage.setLayoutY(p.getSecond());
            projectileImage.setFitWidth(this.controller.getControllerOutput().getProjectileDimension().getFirst());
            projectileImage.setFitHeight(this.controller.getControllerOutput().getProjectileDimension().getSecond());
            this.projectiles.add(projectileImage);
        });
        this.worldGroup.getChildren().addAll(this.projectiles);
    }

    /**
     * Draw the two {@link Tank}.
     */
    private void drawTank() {
        this.playerTank.setLayoutX(this.controller.getControllerOutput().getPlayerPosition().getFirst());
        this.playerTank.setLayoutY(this.controller.getControllerOutput().getPlayerPosition().getSecond());
        this.enemyTank.setLayoutX(this.controller.getControllerOutput().getEnemyPosition().getFirst());
        this.enemyTank.setLayoutY(this.controller.getControllerOutput().getEnemyPosition().getSecond());
        this.playerTank.setFitWidth(this.controller.getControllerOutput().getTankDimension().getFirst());
        this.playerTank.setFitHeight(this.controller.getControllerOutput().getTankDimension().getSecond());
        this.enemyTank.setFitWidth(this.controller.getControllerOutput().getTankDimension().getFirst());
        this.enemyTank.setFitHeight(this.controller.getControllerOutput().getTankDimension().getSecond());
    }

    /**
     * Draw the two cannon.
     */
    private void drawCannon() {
        this.playerCannon.setLayoutX(this.controller.getControllerOutput().getPlayerCannonPosition().getFirst());
        this.playerCannon.setLayoutY(this.controller.getControllerOutput().getPlayerCannonPosition().getSecond());
        this.enemyCannon.setLayoutX(this.controller.getControllerOutput().getEnemyCannonPosition().getFirst());
        this.enemyCannon.setLayoutY(this.controller.getControllerOutput().getEnemyCannonPosition().getSecond());
        this.playerCannon.setFitWidth(this.controller.getControllerOutput().getCannonDimension().getFirst());
        this.playerCannon.setFitHeight(this.controller.getControllerOutput().getCannonDimension().getSecond());
        this.enemyCannon.setFitWidth(this.controller.getControllerOutput().getCannonDimension().getFirst());
        this.enemyCannon.setFitHeight(this.controller.getControllerOutput().getCannonDimension().getSecond());
        this.playerCannon.setRotate(this.controller.getControllerOutput().getPlayerAngle());
        this.enemyCannon.setRotate(this.controller.getControllerOutput().getEnemyAngle());
        if (this.enemyCannon.getLayoutX() < 0) {
            this.enemyCannon.setLayoutX(0);
        }
    }

}
