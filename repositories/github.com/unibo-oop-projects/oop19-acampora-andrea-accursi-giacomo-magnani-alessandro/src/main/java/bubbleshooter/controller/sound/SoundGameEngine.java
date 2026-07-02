package bubbleshooter.controller.sound;

import java.io.IOException;
import java.util.Optional;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import bubbleshooter.controller.engine.GameEngineDecorator;
import bubbleshooter.controller.engine.GameLoop;

/**
 * Class which Decorate the {@link BasicGameLoop} through the {@link GameEngineDecorator}
 * in order to add the Sound feature.
 */
public class SoundGameEngine extends GameEngineDecorator {

    /**
     * The {@link SoundManager} which manage the Sound feature.
     */
    private Optional<SoundManager> soundManager;

    /**
     * @param gameLoop The {@link BasicGameLoop} to decorate.
     */
    public SoundGameEngine(final GameLoop gameLoop) {
        super(gameLoop);
        try {
            this.soundManager = Optional.of(new SoundManager());
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException  e) {
            this.soundManager = Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     * It also starts the Background music.
     */
    @Override
    public final void startLoop() {
        super.startLoop();
        if (this.soundManager.isPresent()) {
            this.soundManager.get().startBackgroundSound();
        }
    }

    /**
     * {@inheritDoc}
     * It also stop the Background music.
     */
    @Override
    public final void stopLoop() {
        super.stopLoop();
        if (this.soundManager.isPresent()) {
            this.soundManager.get().stopBackgroundSound();
        }
    }

    /**
     * {@inheritDoc}
     * It also pause the Background music.
     */
    @Override
    public final void pauseLoop() {
        super.pauseLoop();
        if (this.soundManager.isPresent()) {
        this.soundManager.get().pauseBackgroundSound();
        }
   }

    /**
     * {@inheritDoc}
     * It also resume the Background music.
     */
    @Override
    public final void resumeLoop() {
        super.resumeLoop();
        if (this.soundManager.isPresent()) {
            this.soundManager.get().resumeSound();

        }
    }

    /**
     * 
     * @return the {@link SoundManager2} which manages the Music feature.
     */
    public final Optional<SoundManager> getSoundManager() {
        return this.soundManager;
    }
}
