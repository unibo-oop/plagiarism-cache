package view.audio;

/**
 * Game's audio tracks.
 */
public enum AudioTrack {

    /**
     * The soundtrack of the game.
     */
    SOUNDTRACK("orchestra_long.mp3", AudioType.MUSIC),

    /**
     * The music of each level.
     */
    MUSIC_LEVEL("piano.wav", AudioType.MUSIC),

    /**
     * The sound of the game over message.
     */
    GAME_OVER("game_over.mp3", AudioType.EFFECT),

    /**
     * The sound of the victory message.
     */
    VICTORY("victory.mp3", AudioType.EFFECT),

    /**
     * the sound of the error message.
     */
    ERROR("error.mp3", AudioType.ERROR);

    private static final String DIRECTORY = "audio/";
    private final String path;
    private final AudioType type;

    AudioTrack(final String path, final AudioType type) {
        this.path = path;
        this.type = type;
    }

    /**
     * @return the type of the track.
     */
    public AudioType getType() {
        return this.type;
    }

    /**
     * @return the path of the track.
     */
    public String getPath() {
        return DIRECTORY + this.path;
    }
}
