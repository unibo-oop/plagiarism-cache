package javagotchi.controller.minigame;

import javagotchi.controller.minigame.audio.Music;
import javagotchi.controller.minigame.audio.MusicImpl;

/**
 * 
 * @author marica
 *
 */
public class FactoryControllerImpl implements FactoryController {

    @Override
    public final ControllerMiniGame getControllerMiniGame() {
        return ControllerMiniGameImpl.getInstance();
    }

    @Override
    public final Music getMusic() {
        return MusicImpl.getInstance();
    }
}
