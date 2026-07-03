package audio;

import audio.utils.AudioPaths;

/**
 * 
 * Represents a factory of different game audio.
 *
 */
public interface AudioPlayerFactory {

    /**
     * Creates a background audio with the provided audio path.
     * 
     * @param backgroundAudio
     *            the required audio path.
     * @return a background audio with the provided audio path.
     */
    BackGroundAudio createBackgroundAudio(AudioPaths backgroundAudio);
}
