package it.unibo.model.camera.api;

/**
 * Camera that coordinates player tracking and world generation.
 */
public interface Camera {

    /**
     * @return the width of visible game area.
     */
    double getViewportWidth();

    /**
     * @return the height of visible game area.
     */
    double getViewportHeight();

}
