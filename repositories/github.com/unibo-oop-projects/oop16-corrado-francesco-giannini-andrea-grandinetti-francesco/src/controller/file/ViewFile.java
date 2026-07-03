package controller.file;

import java.io.BufferedReader;

import controller.CircuitPlayable;
import javafx.scene.image.Image;

/**
 * Interface for classes that need to contain all the files needed by the view.
 */
public interface ViewFile {

    /**
     * Getter.
     * @return the file's bufferedReader that contains all the circuit's tracks
     */
    BufferedReader getTracks();

    /**
     * Getter.
     * @return the file's bufferedReader that contains all the coordinates of the cells
     */
    BufferedReader getCoordinates();

    /**
     * Getter.
     * @return the circuit's image
     */
    Image getCircuitImage();

    /**
     * Getter.
     * @return the circuit where the game is set.
     */
    CircuitPlayable getCircuit();

}