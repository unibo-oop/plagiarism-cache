package zengine.core;

import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class models a sound effect that plays inside Zengine. It is basically a
 * wrapper for a javax.sound.sampled.Clip.
 */
final class GameSound {
    private final String name;
    private Clip clip;
    private boolean busy;
    private boolean valid;

    /**
     * 
     * builds a new Sound effect by loading the audio file called "name" (not
     * full path)
     * 
     * @param name
     *            name of the .wav file to be loaded
     */
    GameSound(final String name) {
        this.name = name;
        this.busy = false;

        // shoud this be already a full path?
        final String fullName = "/" + Zengine.USR_SOUND_PATH + name + Zengine.USR_SOUND_EXT;
        final BufferedInputStream in = new BufferedInputStream(ZengineAssets.getAssets().pathGetStream(fullName));

        if (in != null) {
            try {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(in));

                clip.addLineListener(new LineListener() {
                    @Override
                    public void update(final LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP) {
                            busy = false;
                            // clip.close();
                            ZengineAudio.getAudio().soundEnded(name);
                        }
                    }
                });
                ZengineLogger.getLogger().loggerMessage("allocated clip for sound " + name);
                this.valid = true;
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                valid = false;
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
                valid = false;
            } catch (IOException e) {
                e.printStackTrace();
                valid = false;
            } catch (IllegalArgumentException e) {
                ZengineLogger.getLogger().loggerWarning("Unsupported audio " + fullName);
                valid = false;
            }
        }
    }

    /**
     * returns the name associated with this sound
     * 
     * @return the name associated with this sound
     */
    public String getName() {
        return name;
    }

    /**
     * plays the sound effect. If it was already playing, it is stopped, reset
     * and then played again.
     */
    public void play() {
        if (clip != null && valid) {
            busy = true;

            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }

    /**
     * stops the sound effect, if possible.
     */
    public void stop() {
        if (clip != null && valid) {
            busy = false;
            clip.stop();
        }
    }

    /**
     * returns true if this clip is playing its sound effect.
     * 
     * @return true if this clip is playing its sound effect
     */
    public boolean isBusy() {
        return busy;
    }
}
