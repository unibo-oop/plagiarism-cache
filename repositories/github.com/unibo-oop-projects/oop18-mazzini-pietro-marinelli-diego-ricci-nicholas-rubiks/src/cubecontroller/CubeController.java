package cubecontroller;

import cubevisual.RubikCube;
import javafx.scene.Node;
import movestructure.Direction;

/**
 * This code models the interaction between logic cube and visual cube.
 * The CubeController can be intended as a Cube composed by a graphical part and its logic structure.
 */
public interface CubeController {

    /**
     * Method used by visual cube that catches user's input and convert it in a simple tuple of Face/Direction to be turned.
     * @param node - The central cube of a face that the user chose.
     * @param d - The direction (left/right).
     */
    void rotateCubeFromVisual(Node node, Direction d);

    /**
     * Visual cube getter.
     * @return A {@link RubikCube}, the visual cube.
     */
    RubikCube getVisualCube();

    /**
     * Check if the cube inserted by the user (choosing cubes colors) is valid or not and something is done.
     */
    void checkInsertedCube();

    /**
     * In solve mode this method triggers the next move to be done, else it does nothing.
     */
    void nextMove();

    /**
     * In solve mode this method triggers the previous move to be done, else it does nothing.
     */
    void previousMove();

}
