package it.tbt.view.api;

/**
 * This interface represents the conceptual contract
 * that each view should implement in order to be accepted by the rest of the architecture.
 * The only method needed by every view is the render method.
 */

public interface GameView {

    /**
     * The view render logic.
     */
    void render();
}
