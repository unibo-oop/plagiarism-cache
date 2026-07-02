package view.board.sideview;

import controller.audio.AudioManager;
import controller.audio.AudioManagerImpl;

/**
 *
 * Piero Sanchi. Represents a state of AudioState.
 *
 */
public final class AudioOn implements AudioState {

    private static final String AUDIOOFF = "AUDIO: OFF";
    private final AudioManager audioManager = new AudioManagerImpl();

    @Override
    public void execute(final GameView gameview) {
        this.audioManager.setAudio(false);
        gameview.setAudioText(AUDIOOFF);
        gameview.setState(new AudioOff());
    }

}
