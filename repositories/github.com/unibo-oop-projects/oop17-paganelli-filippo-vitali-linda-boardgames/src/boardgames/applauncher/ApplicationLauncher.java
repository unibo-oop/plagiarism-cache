
package boardgames.applauncher;

import boardgames.controller.GameControllerImpl;
import boardgames.view.MenuView;
import boardgames.view.MenuViewImpl;
import boardgames.view.game.GameViewImpl;

/**
 * Class that starts the game.
 *
 */

public final class ApplicationLauncher {

    private ApplicationLauncher() {

    }

    /**
     * Main class.
     * 
     * @param args
     *            unused
     */
    public static void main(final String[] args) {
        final GameControllerImpl controller = GameControllerImpl.getGameControllerImpl();
        final MenuView v = new MenuViewImpl(controller);
        v.showMenu();
    }

}
