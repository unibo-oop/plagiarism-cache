package view.gamegui.subscene;

import java.util.List;

import controller.file.FileController;
import controller.file.FileControllerImpl;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.command.Direction;
import model.entities.GameEntity;
import view.GameSceneInterface;
import view.JavaFXView;

/**
 * Class implemented in JavaFX that make the sub-scene of the game where the
 * tank moves. The class extends the JavaFX's class SubScene.
 */
public final class GameSubScene extends SubScene implements GameSceneInterface {

    // Scene Magic Numbers.
    private static final double SCENE_WIDTH = JavaFXView.STAGE_DIMESNION / 1.27;
    private static final double SCENE_HEIGHT = JavaFXView.STAGE_DIMESNION / 1.27;
    private static final double UNITY_WIDTH = SCENE_WIDTH / 26;
    private static final double UNITY_HEIGHT = SCENE_HEIGHT / 26;
    private static final double ROTATE_UP = 0.0;
    private static final double ROTATE_RIGHT = 90.0;
    private static final double ROTATE_DOWN = 180.0;
    private static final double ROTATE_LEFT = 270.0;

    // The file controller.
    private final FileController fc;
    // The root of the scene;
    private final StackPane root;
    // The context of the canvas;
    private final GraphicsContext gc;

    /**
     * The constructor of the sub-scene.
     */
    public GameSubScene() {
        super(new StackPane(), SCENE_WIDTH, SCENE_HEIGHT);
        this.fc = new FileControllerImpl();
        this.root = (StackPane) getRoot();
        final Canvas myCanvas = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);
        this.root.getChildren().add(myCanvas);
        gc = myCanvas.getGraphicsContext2D();
        init();
    }

    /*
     * Initialize the sub-scene.
     */
    private void init() {
        setFill(Color.BLACK);
        setRootBackground();
    }

    /*
     * This method set the root background.
     */
    private void setRootBackground() {
        final BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        final Background background = new Background(backgroundFill);
        root.setBackground(background);
    }

    @Override
    public void render(final List<GameEntity> gameEntities) {
        gc.clearRect(0, 0, SCENE_HEIGHT, SCENE_HEIGHT);
        gameEntities.forEach((e) -> {
            final double eWidth = e.getDimension().getWidth() * UNITY_WIDTH;
            final double eHeight = e.getDimension().getHeight() * UNITY_HEIGHT;
            Image sprite = fc.getSprite(e.getSprite());
            if (e.getDirection().isPresent()) {
                if (e.getDirection().get().equals(Direction.UP)) {
                    sprite = rotateSprite(sprite, ROTATE_UP);
                } else if (e.getDirection().get().equals(Direction.RIGHT)) {
                    sprite = rotateSprite(sprite, ROTATE_RIGHT);
                } else if (e.getDirection().get().equals(Direction.DOWN)) {
                    sprite = rotateSprite(sprite, ROTATE_DOWN);
                } else if (e.getDirection().get().equals(Direction.LEFT)) {
                    sprite = rotateSprite(sprite, ROTATE_LEFT);
                }
            }
            gc.drawImage(sprite, e.getActualPosition().getX() * UNITY_WIDTH,
                    e.getActualPosition().getY() * UNITY_HEIGHT, eWidth, eHeight);
        });
    }

    /*
     * The method that rotates the sprite according to the rotation grades.
     * 
     * @param sprite the sprite to rotate.
     * 
     * @param rotation the rotation in grades.
     * 
     * @return the sprite rotate.
     */
    private Image rotateSprite(final Image sprite, final double rotation) {
        final ImageView iv = new ImageView(sprite);
        iv.setRotate(rotation);
        final SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        return iv.snapshot(params, null);
    }

}
