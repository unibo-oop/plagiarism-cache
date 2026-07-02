package javagotchi.controller.minigame.audio;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * @author marica
 *
 */
public final class MusicImpl implements Music {

    private static final String PATHSOUND = "/minigame/audio/musicMiniGame.wav";
    private static Clip aClip;

    private static class LazyMusic {
        private static final Music MUSIC = new MusicImpl();
    }

    private MusicImpl() {
        this.loadAudio();
    }

    /**
     * Gets Music instance.
     * 
     * @return the Music instance
     */
    public static Music getInstance() {
        return LazyMusic.MUSIC;
    }

    private void loadAudio() {
        try (AudioInputStream aStream = AudioSystem
                .getAudioInputStream(new BufferedInputStream(MusicImpl.class.getResourceAsStream(PATHSOUND)))) {

            aClip = AudioSystem.getClip();
            aClip.open(aStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startAudio() {
        aClip.start();
        aClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void stopAudio() {
        aClip.stop();
    }

    @Override
    public boolean isOn() {
        return aClip.isRunning();
    }

}
