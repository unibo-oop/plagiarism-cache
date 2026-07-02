package it.unibo.scat.view.util;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Implementation of the Audio interface using the Java Sound API.
 * Manages playback, volume control, and looping for AudioTracks.
 */
public final class AudioManager implements Audio {
    private static final float DEFAULT_VOLUME = 0.3f;

    private Clip clip;

   @Override
    public void play(final AudioTrack music, final boolean loop) {
        try (AudioInputStream audioIn = AudioSystem
                .getAudioInputStream(ClassLoader.getSystemResource(music.getPath()))) {

            final AudioFormat baseFormat = audioIn.getFormat();
            final AudioFormat targetFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(),
                16,
                baseFormat.getChannels(),
                baseFormat.getChannels() * 2,
                baseFormat.getSampleRate(),
                false
            );
            try (AudioInputStream decodedAudioIn = AudioSystem.getAudioInputStream(targetFormat, audioIn)) {
                this.clip = AudioSystem.getClip();
                this.clip.open(decodedAudioIn);
                if (loop) {
                    setVolume(DEFAULT_VOLUME);
                    this.clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                this.clip.start();
            }
        } catch (final UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new IllegalStateException("Unable to play audio track: " + music.getPath(), e);
        }
    }

    @Override
    public void setVolume(final float volume) {
        if (clip == null) {
            return;
        }
        final float normalizedVolume = Math.max(0.0f, Math.min(1.0f, volume));
        try {
            final FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            final float dB = (float) (Math.log10(normalizedVolume <= 0 ? 0.0001 : normalizedVolume) * 20.0);
            gainControl.setValue(dB);

        } catch (final IllegalArgumentException e) {
            throw new IllegalStateException("Volume control not supported", e);
        }
    }

    @Override
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

}
