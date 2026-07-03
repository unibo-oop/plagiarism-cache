package controller.file;

import java.io.BufferedReader;

import controller.CircuitPlayable;

/**
 * Interface for classes that need to provide files for view and model.
 */
public interface F1Files {

    /**
     * Method to get the file needed by the model.
     * @param cir the circuit chosen
     * @return the file's bufferedReader that the model has to use for loading the circuit
     */
    BufferedReader getFileForModel(CircuitPlayable cir);

    /**
     * Method to get the files needed by the view.
     * @param cir the circuit chosen
     * @return a class that contains all the files that the view needs 
     */
    ViewFile getFileForView(CircuitPlayable cir);

}
