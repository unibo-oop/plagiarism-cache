package audio;

import audio.utils.AudioPaths;

/**
 * 
 * Implements a factory of different game audio.
 *
 */
public class AudioPlayerFactoryImpl implements AudioPlayerFactory {

    @Override
    public final BackGroundAudio createBackgroundAudio(final AudioPaths backgroundAudio) {
        return new BackGroundAudio(backgroundAudio);
    }

}
