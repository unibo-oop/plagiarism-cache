package controller.interfaces;

/**
 * Add Plane Controller interface.
 */
public interface AddPlaneController {

    /**
     * Adds a plane to the list of planes.
     * 
     * @throws NumberFormatException if seats fields do not contain an integer
     */
    void addPlane() throws NumberFormatException;

}