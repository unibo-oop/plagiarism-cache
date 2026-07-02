package sound;

import javafx.scene.media.AudioClip;

/**
 * A factory of {@link Sound}.
 * An implementation of {@link SoundFactory} interface.
 *
 */
public class SoundFactoryImpl implements SoundFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Sound createGameSoundtrack() {
        return this.createSound(SoundType.GAME_SOUNDTRACK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sound createJumpSound() {
        return this.createSound(SoundType.JUMP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sound createCoinCollectedSound() {
        return this.createSound(SoundType.COIN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sound createGameOverSound() {
        return this.createSound(SoundType.GAME_OVER);
    }

    private Sound createSound(final SoundType soundType) {
        final AudioClip audioClip = new AudioClip(this.getClass().getResource(soundType.toString()).toExternalForm());
        return new Sound() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void play() {
                audioClip.play();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void stop() {
                audioClip.stop();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean isPlaying() {
                return audioClip.isPlaying();
            }

        };
    }

}
