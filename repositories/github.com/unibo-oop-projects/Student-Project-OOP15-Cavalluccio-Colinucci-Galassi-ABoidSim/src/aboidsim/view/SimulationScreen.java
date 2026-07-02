package aboidsim.view;

import java.util.Set;

import aboidsim.util.Input;
import aboidsim.util.InputInfo;
import aboidsim.util.Pair;
import aboidsim.util.Vector;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * section of the interface containing the moving boids.
 */
class SimulationScreen extends Pane {

    static final int BOID_HEIGHT = 20;
    static final int BOID_WIDTH = 10;
    static final int HEIGHT = 600 + SimulationScreen.BOID_HEIGHT;
    static final int WIDTH = 600 + SimulationScreen.BOID_HEIGHT;
    private final DrawEntities drawEntities = new DrawEntities();
    private final GraphicsContext gc;
    private static final String PAUSE = "PAUSE";
    private static final String RESUME = "RESUME";

    /**
     * constructor.
     */
    SimulationScreen() {
        super();

        final Canvas canvas = new Canvas(SimulationScreen.WIDTH, SimulationScreen.HEIGHT);

        this.getChildren().add(canvas);
        this.setStyle("-fx-background-color: DarkSlateGrey ;");
        this.gc = canvas.getGraphicsContext2D();
        this.gc.setFill(Color.RED);
        this.gc.setStroke(Color.BLACK);
        this.gc.setLineWidth(2);

        // print the coordinates of the clicked point and calls addInputs method
        // passing scene's coordinates
        canvas.setOnMouseClicked(e -> {
            this.addInputs(new Vector(e.getSceneX(), e.getSceneY()));
        });

        final Button playPause = new Button(SimulationScreen.PAUSE);
        playPause.setId("pause-button");
        playPause.setOnAction(e -> {
            if (playPause.getText().equals(SimulationScreen.PAUSE)) {
                System.out.println("pause the simulation");
                InputHandler.getInputHandler().addInput(new InputInfo(Input.PAUSE));
                playPause.setText(SimulationScreen.RESUME);
            } else {
                System.out.println("resume the simulation");
                InputHandler.getInputHandler().addInput(new InputInfo(Input.RESUME));
                playPause.setText(SimulationScreen.PAUSE);
            }
        });
        this.getChildren().add(playPause);

    }

    /**
     * add input to InputHandler, with the pos and the type of the boid selected
     * in class BoidsSelection
     */
    private void addInputs(final Vector pos) {
        InputHandler.getInputHandler().addInput(BoidSelection.getInput(pos));

    }

    /**
     * used to draw all entities on the screen simulation
     *
     * @param entities
     *            set containing all the entities, with the rotation angle and
     *            the string representing the image
     */
    void drawOnScreen(final Set<Pair<Pair<Vector, Double>, Integer>> entities) {
        this.drawEntities.drawEntities(this.gc, entities);
    }

}
