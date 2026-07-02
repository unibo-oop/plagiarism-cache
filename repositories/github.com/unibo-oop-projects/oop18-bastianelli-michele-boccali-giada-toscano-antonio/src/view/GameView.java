package view;

import common.CommonStrings;
import controller.GameController;
import controller.menu.Controller;
import enumerators.Level;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;

/**
 * View of the game.
 */
public final class GameView extends GenericView {

    public static final long ONE_SECOND_NS = 1000000000;
    private long fps;
    private int maxUpdates = 8;
    private long lastTime = System.nanoTime();

    private boolean endGame;
    private Camera camera;

    private final AnimationTimer animationTimer = new AnimationTimer() {

        @Override
        public void handle(final long time) {
            render(time);
        }
    };

    /**
     * View constructor. Create camera
     * 
     * @param c the controller
     */
    public GameView(final long fps, final Controller c) {
        super(c);
        this.fps = fps;
    }
    
    public void init(final Level level) {

        super.getAnchorPane().setStyle(
                " -fx-background-image : url(" + level.getBackgroundPath() + ");");

        Scale scale = new Scale(CommonStrings.SCALING_FACTOR, CommonStrings.SCALING_FACTOR);
        scale.setPivotX(0);
        scale.setPivotY(0);
        getScene().getRoot().getTransforms().setAll(scale);
        this.camera = new Camera();
    }

    /**
     * Returns the children of the root of the scene.
     * 
     * @return the ObservableList of the root nodes.
     */
    public ObservableList<Node> getViewNodes() {
        return super.getAnchorPane().getChildren();
    }

    /**
     * Returns the view Dimension2D.
     * 
     * @return view Dimension2D
     */
    public Dimension2D getViewDimension() {
        return super.getDimension();
    }

    /**
     * Returns the game camera.
     * 
     * @return the game camera.
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Returns the game JavaFX Scene.
     * 
     * @return Scene
     */
    public Scene getScene() {
        return super.getScene();
    }

    /**
     * Starts the animationTimer.
     */
    public void timerStart() {
        endGame = false;
        animationTimer.start();
    }

    /**
     * Stops the animationTimer.
     */
    public void timerStop() {
        endGame = true;
        animationTimer.stop();
    }

    /**
     * Returns the camera position.
     * 
     * @return the camera position
     */
    public Point2D getCameraPosition() {
        return camera.getCameraPosition();
    }

    public void render(final long time) {
        long timeDelta = time - lastTime;
//	float timeDeltaSeconds = timeDelta / (float) ONE_SECOND_NS;
        lastTime = time;

        GameController.getInstance().tick();
        int updateCount = 0;
        while (timeDelta > 0 && (maxUpdates <= 0 || updateCount < maxUpdates && !endGame)) {
            long updateTimeStep = Math.min(timeDelta, ONE_SECOND_NS / fps);
//	    float updateTimeStepSeconds = updateTimeStep / (float) ONE_SECOND_NS;

            GameController.getInstance().tick();
            timeDelta -= updateTimeStep;
            updateCount++;
        }
        long sleepTime = (ONE_SECOND_NS / fps) - (System.nanoTime() - lastTime);
        if (sleepTime <= 0) {
            return;
        }
        long prevTime = System.nanoTime();
        while (System.nanoTime() - prevTime <= sleepTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
