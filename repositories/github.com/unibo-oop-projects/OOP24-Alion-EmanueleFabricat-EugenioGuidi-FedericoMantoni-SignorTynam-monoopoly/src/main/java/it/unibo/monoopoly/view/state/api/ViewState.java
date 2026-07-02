package it.unibo.monoopoly.view.state.api;

import it.unibo.monoopoly.controller.data.impl.DataInput;

/**
 * The model of the pattern State for the view.
 */
public interface ViewState {
    /**
     * Set the state ready to be execute.
     * 
     * @param setter to set the state if necessary
     */
    void setter(Boolean setter);

    /**
     * Display all the views necessary for the state.
     * 
     * @param data a pack of data to help the setup.
     */
    void visualize(DataInput data);

}
