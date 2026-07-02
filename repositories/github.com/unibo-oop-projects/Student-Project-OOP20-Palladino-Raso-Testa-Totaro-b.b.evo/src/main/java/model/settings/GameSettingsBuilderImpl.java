package model.settings;

import model.utilities.Difficulty;

public final class GameSettingsBuilderImpl implements GameSettingsBuilder {

    private boolean isEnableSoundFx;
    private boolean isEnableMusic;
    private boolean useLeftAndRight;
    private boolean useUpAndDown;
    private Difficulty difficulty;

    /**
     * 
     * Method used for create a default builder whit default parameter.
     * @return a builder whit default settings.
     *
     */
    public GameSettingsBuilderImpl defaultSettings() {
        this.isEnableSoundFx = true;
        this.isEnableMusic = true;
        this.useLeftAndRight = true;
        this.useUpAndDown = false;
        this.difficulty = Difficulty.NORMAL;
        return this;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public GameSettingsBuilder enableSoundFx(final boolean isEnableSoundFX) {
        this.isEnableSoundFx = isEnableSoundFX;
        return this;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public GameSettingsBuilder enableMusic(final boolean isEnableMusic) {
        this.isEnableMusic = isEnableMusic;
        return this;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public GameSettingsBuilder leftAndRight(final boolean useLeftAndRight) {
        this.useLeftAndRight = useLeftAndRight;
        return this;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public GameSettingsBuilder upAndDown(final boolean useUpAndDown) {
        this.useUpAndDown = useUpAndDown;
        return this;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public GameSettingsBuilder difficulty(final Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public Settings build() {
        if (this.useLeftAndRight && this.useUpAndDown
            || this.difficulty == null) {
            throw new IllegalStateException();
        }
        return new GameSettingsImpl(this.isEnableSoundFx, 
                                    this.isEnableMusic, 
                                    this.useLeftAndRight, 
                                    this.useUpAndDown, 
                                    this.difficulty);
    }

}
