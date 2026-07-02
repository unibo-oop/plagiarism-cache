package tmw.test.audio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import javafx.scene.media.MediaPlayer.Status;
import tmw.model.audio.AudioMaster;
import tmw.model.audio.AudioMasterImpl;
import tmw.model.audio.AudioSfx;
import tmw.model.audio.AudioTracks;

/**
 * This is a Unit test for audio feature in this project, so tests
 * {@link AudioMaster}.
 */
public class TestAudio {

    /**
     * Basic test to check if main audio commands works.
     */
    @Test
    public void testAudioCommands() {

        final AudioMaster audio = new AudioMasterImpl();

        assertFalse(audio.isPlaying());
        audio.stopPlaying();
        assertTrue(!audio.isPlaying());
        audio.playBackMusic(AudioTracks.MAINMENU_TRACK);
        assertTrue(audio.getPlayer().getStatus() != Status.STOPPED);
        audio.playSFX(AudioSfx.BULLET_HIT);
        audio.mutePlayer();
        assertTrue(audio.getPlayer().isMute());
    }
}

