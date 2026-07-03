package controller;

import util.Logger;

/**
 * The main class where the game is launched.
 */
public final class GameInit {

    private GameInit() {

    }

    /**
     * Launches the application.
     * 
     * @param args
     *            not used
     */
    public static void main(final String[] args) {

        try {
            ControllerImpl.get().initView();
        } catch (Exception e) {
            Logger.getLog().write(e.getStackTrace());
        }
    }

}
