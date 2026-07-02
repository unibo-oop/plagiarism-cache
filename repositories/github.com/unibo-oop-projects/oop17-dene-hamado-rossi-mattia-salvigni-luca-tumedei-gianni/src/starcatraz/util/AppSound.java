package starcatraz.util;

import javafx.scene.media.AudioClip;

/**
 * Sounds used in the project.
 */
public enum AppSound {

    GAME_MUSIC("/audio/OST.mp3", AudioClip.INDEFINITE),
    MENU_MUSIC("/audio/Menu.mp3", AudioClip.INDEFINITE),
    VICTORY_MUSIC("/audio/Victory.mp3", AudioClip.INDEFINITE),
    DEFEAT_MUSIC("/audio/Defeat.mp3", AudioClip.INDEFINITE),
    BUTTON_SOUND("/audio/Button.mp3", 1),
    DRAW_SOUND("/audio/Draw.mp3", 1),
    PLAY_CARD_SOUND("/audio/PlayCard.mp3", 1),
    ROBOT_SOUND("/audio/Robot.mp3", 1),
    ROCKET_SOUND("/audio/Rocket.mp3", 1),
    TERMINAL_SOUND("/audio/Terminal.mp3", 1);

    private final String path;
    private final AudioClip audio;

    /**
     * Constructor for AppSound.
     * @param path
     * @param cycleCount
     */
    AppSound(final String path, final int cycleCount) {
        this.path = path;
        this.audio =  new AudioClip(getClass().getResource(path).toString());
        this.audio.setCycleCount(cycleCount);
    }

    /**
     * @return the sound's relative path
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Start the sound.
     */
    public void play() {
        this.audio.stop();
        this.audio.play();
    }

    /**
     * Stop the sound.
     */
    public void stop() {
        this.audio.stop();
    }

    @Override
    public String toString() {
        return this.path;
    }

    /**
     * @return the volume of the sound
     */
    public double getVolume() {
        return this.audio.getVolume();
    }

    /**
     * Set the volume.
     * @param volume
     */
    public void setVolume(final double volume) {
        this.audio.setVolume(volume);
    }

    /**
     * @return true if the sound is a Music
     */
    public boolean isMusic() {
        return this.audio.getCycleCount() == AudioClip.INDEFINITE;
    }
}
