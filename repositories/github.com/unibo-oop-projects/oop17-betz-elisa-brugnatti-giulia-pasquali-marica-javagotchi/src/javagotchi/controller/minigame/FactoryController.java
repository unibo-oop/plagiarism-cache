package javagotchi.controller.minigame;

import javagotchi.controller.minigame.audio.Music;

/**
 * 
 * @author marica
 *
 */
public interface FactoryController {
    /**
     * Gets ControllerMiniGame.
     * 
     * @return Factory ControllerMiniGame
     */
    ControllerMiniGame getControllerMiniGame();

    /**
     * Gets Music instance.
     * 
     * @return the Music instance
     */
    Music getMusic();
}
