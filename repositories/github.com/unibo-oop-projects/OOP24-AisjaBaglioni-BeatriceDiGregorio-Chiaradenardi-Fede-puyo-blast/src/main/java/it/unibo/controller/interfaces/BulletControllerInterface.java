package it.unibo.controller.interfaces;

import it.unibo.controller.ControllerStorage;
import it.unibo.model.Point2DI;
import it.unibo.view.CannonView;

/**
 * An interface representing the controller responsible for handling the bullet
 * mechanics.
 */
public interface BulletControllerInterface {

    /**
     * A method that implements a Breadth-First Search (BFS) to find adjacent Puyos
     * and trigger their explosion.
     */
    void explodePuyos(Point2DI target);

    /**
     * A method to set the {@link CannonView}, shortly after the
     * {@link ControllerStorage}
     * is created.
     */
    void setCannonView(CannonView cannonView);
}