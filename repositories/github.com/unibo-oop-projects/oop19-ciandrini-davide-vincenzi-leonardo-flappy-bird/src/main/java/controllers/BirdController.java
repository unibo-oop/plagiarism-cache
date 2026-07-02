package controllers;

import javafx.scene.shape.Rectangle;
import model.bird.Bird;
import view.BirdView;

/**
 * This interfaces initializes and controls the Rectangle of the bird's view.
 */
public interface BirdController {

    /**
     * Call {@link BirdView} method and set the view of bird.
     */
    void initBirdView();

    /**
     * Call {@link Bird#birdUpdate(double)} and {@link BirdView#updatePosition(double)} for define bird movement.
     * @param n Constant defined in {@link controllers.GameLoopImpl}, set the increase of pixel at each loop.
     */
    void birdMovement(double n);

    /**
     * Boolean method that return true if the bird touch the floor.
     * @param r Rectangle initialize in {@link BirdView} constructor
     * @return true
     */
    boolean floorCollision(Rectangle r);

    /**
     * Get the View of bird.
     * @return BirdView Object type
     */
    BirdView getBirdView();

    /**
     * Get the Model of bird.
     * @return BirdModel Object type
     */
    Bird getBirdModel();
}

