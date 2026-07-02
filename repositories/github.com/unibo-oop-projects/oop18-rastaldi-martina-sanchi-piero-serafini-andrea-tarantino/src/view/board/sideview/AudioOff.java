package view.board.sideview;

import controller.audio.AudioManager;
import controller.audio.AudioManagerImpl;

/**
 *
 * Piero Sanchi. Represents a state of AudioState.
 *
 */
public final class AudioOff implements AudioState {

    private static final String AUDIOON = "AUDIO: ON";
    private final AudioManager audioManager = new AudioManagerImpl();

    @Override
    public void execute(final GameView gameview) {
        this.audioManager.setAudio(true);
        gameview.setAudioText(AUDIOON);
        gameview.setState(new AudioOn());
    }

}
