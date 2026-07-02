package it.unibo.biscia.view.managers;

/**
 * Utility class for Sound files.
 *
 */
public final class SoundManager {

    /**
     * Sound for when the snake dies.
     */
    // TODO: Implement sounds.
    public static final Asset<Sound> DIE = new AssetImpl<>("path", "name", new Sound());

    /**
     * A Sound asset info.
     *
     */
    public static final class Sound {
        private Sound() {
        }
    }

    private SoundManager() {
    }
}
