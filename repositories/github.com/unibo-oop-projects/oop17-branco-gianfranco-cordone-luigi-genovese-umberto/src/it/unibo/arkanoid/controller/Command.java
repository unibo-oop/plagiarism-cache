package it.unibo.arkanoid.controller;

import it.unibo.arkanoid.model.Model;

/**
 * 
 * Interface must be implemented from entity input.
 *
 */
public interface Command {

    /**
     * Notify input.
     * 
     * @param model 
     *          The model of the application.
     */
    void execute(Model model);

}
