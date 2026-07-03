package it.oop.project;

import it.oop.project.controller.GameControllerImpl;

/**
 * This is the 2048 game launcher.
 *
 */
public class Launcher {
    /**
     * This is the main method that launches the 2048 game.
     * 
     * @param args
     *            no expected arguments.
     */
    public static void main(String[] args) {
        new GameControllerImpl();
    }

}
