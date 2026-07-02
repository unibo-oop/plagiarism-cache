package controller;

import model.ImmutableModel;

/**
 * Defines all the methods that a specific controller must have.
 */
public interface SpecificController {

    /**
     * Getter for the model.
     * @return the instance of {@link ModelImpl} with the correct interface
     */
    ImmutableModel getModel();

    /**
     * Getter for the {@link MasterController}.
     * @return the instance of {@link MasterController}.
     */
    MasterController getMasterController();
}
