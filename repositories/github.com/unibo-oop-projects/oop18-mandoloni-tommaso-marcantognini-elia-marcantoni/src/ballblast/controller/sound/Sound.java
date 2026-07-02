package ballblast.controller.sound;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Map;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.google.common.collect.ImmutableMap;

import ballblast.commons.events.EventHandler;
import ballblast.commons.events.EventType;

/**
 * Enumeration representing the game sounds.
 */
public enum Sound {

    /**
     * The background music.
     */
    THEME("theme.wav"),

    /**
     * The shot sound.
     */
    SHOT("shot.wav"),

    /**
     * The split sound.
     */
    SPLIT("split.wav"),

    /**
     * The destroy sound.
     */
    DESTROY("destroy.wav"),

    /**
     * The game over sound.
     */
    GAMEOVER("gameover.wav");

    private static final Map<EventType, Sound> SOUND_MAP;
    private Clip clip;

    static {
        SOUND_MAP = ImmutableMap.of(
                EventType.SPLIT,        Sound.SPLIT,
                EventType.DESTROY,      Sound.DESTROY,
                EventType.SHOT,         Sound.SHOT
        );
    }

    /**
     * Handler of {@link EventType}.
     */
    public static final EventHandler<EventType> HANDLER = e -> SOUND_MAP.get(e).playSound();

    Sound(final String fileName) {
        try {
            this.clip = AudioSystem.getClip();
            this.clip.open(AudioSystem.getAudioInputStream(
                    new BufferedInputStream(Sound.class.getResourceAsStream("/sound/" + fileName))));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the sound.
     */
    public void playSound() {
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * Stops the sound.
     */
    public void stopSound() {
        clip.stop();
    }

    /**
     * Loops the sound.
     */
    public void loopSound() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Pre-loads the sounds.
     */
    public static void loadSounds() {
        values();
    }

}
