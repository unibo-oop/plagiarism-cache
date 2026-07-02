package javagotchi.controller.minigame.main;

import javagotchi.controller.home.HomeController;

/**
 * 
 * @author marica
 *
 */
public class MiniGameImpl implements MiniGame {
    /**
     * 
     * Constructor for the MiniGameImpl.
     * 
     * @param hc
     *            Home Controller
     */
    public MiniGameImpl(final HomeController hc) {
        MiniGame.getFactoryController().getControllerMiniGame().setHomeController(hc);
        MiniGame.getFactoryController().getControllerMiniGame().getModel().setGotchi(hc.getJavagotchi());
    }

    @Override
    public final void run() {
        startGame();
    }

    private void startGame() {
        MiniGame.getFactoryController().getControllerMiniGame().getView().getMainMenu().display();
        MiniGame.getFactoryController().getMusic().startAudio();
    }

}
