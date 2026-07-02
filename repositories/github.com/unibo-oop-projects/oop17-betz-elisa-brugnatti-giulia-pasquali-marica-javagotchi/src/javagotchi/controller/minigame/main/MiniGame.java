package javagotchi.controller.minigame.main;

import javagotchi.controller.minigame.FactoryController;
import javagotchi.controller.minigame.FactoryControllerImpl;

/**
 * 
 * @author marica
 *
 */
public interface MiniGame extends Runnable {

    /**
     * Gets Factory Controller instance.
     * 
     * @return Factory Controller instance
     */
    static FactoryController getFactoryController() {
        return new FactoryControllerImpl();
    }
}
