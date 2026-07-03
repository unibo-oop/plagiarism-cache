package audio.utils;

/**
 * 
 * Enumeration that contains the path of the audio files.
 * 
 *
 */
public enum AudioPaths {

    /**
     * The main background audio file name.
     */
    BACKGROUND("background.mp3");

    private static final String AUDIO_PATH = "/audio/";
    private final String audioName;

    AudioPaths(final String audioName) {
        this.audioName = audioName;
    }

    /**
     * Provides the selected audio file path.
     * 
     * @return the selected audio file path as string.
     */
    public String getPath() {
        return AudioPaths.AUDIO_PATH + this.audioName;
    }

}
