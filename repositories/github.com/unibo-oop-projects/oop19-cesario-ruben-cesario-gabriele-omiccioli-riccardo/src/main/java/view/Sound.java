package view;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Sound class is used to store sounds in cache and to provide methods to play sounds and control the audio volume.
 */
public final class Sound {

    // Initial volume value of 0.5 = 50% max volume
    private static final double INITIAL_VOLUME = 0.5;

    // Step of the volume to be increased or decreased
    private static final double VOLUME_STEP = 0.1;

    // Variable used for volume control
    private static double volume = INITIAL_VOLUME;

    // Variable used to activate or deactivate music
    private static Boolean music = true;

    private static final Map<String, MediaPlayer> SOUND_CACHE = new HashMap<>();;
    private static MediaPlayer musicPlayer;

    private Sound() {
    }

    static {
        initialize();
    }

    private static void initialize() {
        SOUND_CACHE.put("menuMusic", new MediaPlayer(new Media(ClassLoader.getSystemResource("sounds/menu_music.wav").toString())));
        SOUND_CACHE.put("arenaMusic1", new MediaPlayer(new Media(ClassLoader.getSystemResource("sounds/arena_music_1.wav").toString())));
        SOUND_CACHE.put("arenaMusic2", new MediaPlayer(new Media(ClassLoader.getSystemResource("sounds/arena_music_2.wav").toString())));
        SOUND_CACHE.put("elevatorMusic", new MediaPlayer(new Media(ClassLoader.getSystemResource("sounds/elevator_music.wav").toString())));

        SOUND_CACHE.put("shoot", new MediaPlayer(new Media(ClassLoader.getSystemResource("sounds/shoot.wav").toString())));
        SOUND_CACHE.put("pew", new MediaPlayer(new Media(ClassLoader.getSystemResource("sounds/pew.wav").toString())));

        SOUND_CACHE.put("collision", new MediaPlayer(new Media(ClassLoader.getSystemResource("sounds/collision.wav").toString())));

        SOUND_CACHE.put("explosion", new MediaPlayer(new Media(ClassLoader.getSystemResource("sounds/explosion.wav").toString())));

        SOUND_CACHE.put("alarm", new MediaPlayer(new Media(ClassLoader.getSystemResource("sounds/alarm.wav").toString())));

        SOUND_CACHE.put("yay", new MediaPlayer(new Media(ClassLoader.getSystemResource("sounds/yay.wav").toString())));

        SOUND_CACHE.put("mouseOver", new MediaPlayer(new Media(ClassLoader.getSystemResource("sounds/mouse_over.wav").toString())));
    }

    /**
     * Plays a sound from the MediaPlayer associated to the key with the current volume and then resets the MediaPlayer to the start position.
     * @param key MediaPlayer key
     */
    public static void play(final String key) {
        SOUND_CACHE.get(key).setVolume(volume);
        SOUND_CACHE.get(key).play();
        SOUND_CACHE.get(key).seek(Duration.ZERO);
    }

    /**
     * Loads the music associated to the key and starts playing it at the current volume or at volume 0 if the music is disabled.
     * @param key Music key
     */
    public static void playMusic(final String key) {
        musicPlayer = SOUND_CACHE.get(key);
        musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicPlayer.setOnEndOfMedia(() -> nextMusic());
        if (music) {
            musicPlayer.setVolume(volume);
        } else {
            musicPlayer.setVolume(0);
        }
        musicPlayer.play();
    }

    /**
     * Starts or changes the current music playing with the music associated to the key.
     * @param key Music key
     */
    public static void changeMusic(final String key) {
        // If the musicPlayer exists
        if (musicPlayer != null) {
            // If the musicPlayer is playing and the music to be started is not the one currently being played
            if (musicPlayer.getStatus() == MediaPlayer.Status.PLAYING && musicPlayer.getMedia() != SOUND_CACHE.get(key).getMedia()) {
                musicPlayer.stop();
                playMusic(key);
            }
        } else {
            playMusic(key);
        }
    }

    /**
     * Changes the arena music to the next track.
     */
    public static void nextMusic() {
        if (musicPlayer.getMedia() == SOUND_CACHE.get("arenaMusic1").getMedia()) {
            changeMusic("arenaMusic2");
        }  else if (musicPlayer.getMedia() == SOUND_CACHE.get("arenaMusic2").getMedia()) {
            changeMusic("arenaMusic1");
        }
    }

    /**
     * Sets the volume of the media player (0 min, 1 max).
     * @param value Audio volume value
     */
    public static void setVolume(final double value) {
        if (value >= 0 && value <= 1) {
            volume = value;
            refreshMusicVolume();
        }
    }

    /**
     * Returns the volume of the media player (0 min, 1 max).
     * @return volume The audio volume
     */
    public static double getVolume() {
        return volume;
    }

    /**
     * Decrease volume by VOLUME_STEP.
     */
    public static void decreaseVolume() {
        if (volume - VOLUME_STEP >= 0) {
            volume = volume - VOLUME_STEP;
            refreshMusicVolume();
        }
    }

    /**
     * Increase volume by VOLUME_STEP.
     */
    public static void increaseVolume() {
        if (volume + VOLUME_STEP <= 1) {
            volume = volume + VOLUME_STEP;
            refreshMusicVolume();
        }
    }

    /**
     * Refresh the volume of the music player.
     */
    private static void refreshMusicVolume() {
        if (musicPlayer != null && music) {
            musicPlayer.setVolume(volume);
        }
    }

    /**
     * Returns the value of the music variable, used to decide if music should be playing.
     * @return music Boolean value indicating if the music is active
     */
    public static Boolean isMusicActive() {
        return music;
    }

    /**
     * Sets the value of music variable, used to decide if music should be playing.
     * @param value Boolean value to activate or deactivate music
     */
    public static void setMusicActive(final Boolean value) {
        music = value;
        if (music) {
            musicPlayer.setVolume(volume);
        } else {
            musicPlayer.setVolume(0);
        }
    }

}
