package application;

import controller.ControllerImpl;

/**
 * 
 **/
public final class Game {

    private Game() {
    }

    /**
     * @param args
     *            . .
     */
    public static void main(final String[] args) {
        ControllerImpl.getController().startGame();

    }

}
