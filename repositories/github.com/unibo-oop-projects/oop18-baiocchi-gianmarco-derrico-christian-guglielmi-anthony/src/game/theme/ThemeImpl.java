package game.theme;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * It represents a theme with any sound and sprite.
 */
public class ThemeImpl implements Theme {

    private final SoundsManager sounds;
    private final SpritesManager sprites;

    /**
     * Constructor that initialize sprites/sounds manager.
     * @param folder :
     * @throws UnsupportedAudioFileException : wrong audio file format
     * @throws IOException : problem during input/output
     * @throws LineUnavailableException : audio line can't be opened because it is unavailable
     */
    public ThemeImpl(final String folder) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.sounds = new SoundsManager(folder);
        this.sprites = new SpritesManager(folder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SoundsManager getSounds() {
        return this.sounds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpritesManager getSprites() {
        return this.sprites;
    }
}
