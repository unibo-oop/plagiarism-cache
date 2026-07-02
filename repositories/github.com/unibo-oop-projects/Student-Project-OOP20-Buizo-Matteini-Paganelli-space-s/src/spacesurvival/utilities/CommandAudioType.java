package spacesurvival.utilities;

/**
 * 
 * Enum for multiple commands audio.
 *
 */
public enum CommandAudioType {
    /** 
     * Type of command to pass to the CallerAudio to start sound.
     */
    AUDIO_ON("AUDIO_ON"),
    /**
     * Type of command to pass to the CallerAudio to stop sound.
     */
    AUDIO_OFF("AUDIO_OFF"), 
    /**
     * Type of command to pass to the CallerAudio to reset the timing of the sound.
     */
    RESET_TIMING("RESET_TIMING");

    private final String string;

    CommandAudioType(final String string) {
        this.string = string;
    }

    /**
     * Type of command to pass to the CallerAudio to reset the timing of the sound.
     * 
     * @return the string representing the command.
     */
    public String getValue() {
        return this.string;
    }
}
