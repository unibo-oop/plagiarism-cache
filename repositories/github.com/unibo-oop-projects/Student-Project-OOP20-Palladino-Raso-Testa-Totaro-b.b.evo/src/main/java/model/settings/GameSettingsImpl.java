package model.settings;

import model.utilities.Difficulty;

public class GameSettingsImpl implements Settings {

    private static final int HASH_FIRST_OPERAND = 1231;
    private static final int HASH_SECOND_OPERAND = 1237;
    private static final long serialVersionUID = 5553383064816848320L;
    private final boolean isEnableSoundFx;
    private final boolean isEnableMusic;
    private final boolean useLeftAndRight;
    private final boolean useUpAndDown;
    private final Difficulty difficulty;

    /**
     * 
     * Create a new Game's Settings whit specifics parameters.
     * @param isEnableSoundFx - used to understand if the game's sound fx is enabled.
     * @param isEnableMusic - used to understand if the game's music is enabled.
     * @param useLeftAndRight - used to understand if the player want to play with left and right buttons.
     * @param useUpAndDown - used to understand if the player want to play with up and down buttons.
     * @param difficulty - used to understand the game's difficulty.
     */
    public GameSettingsImpl(final boolean isEnableSoundFx, final boolean isEnableMusic, final boolean useLeftAndRight,
                            final boolean useUpAndDown, final Difficulty difficulty) {
        this.isEnableSoundFx = isEnableSoundFx;
        this.isEnableMusic = isEnableMusic;
        this.useLeftAndRight = useLeftAndRight;
        this.useUpAndDown = useUpAndDown;
        this.difficulty = difficulty;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isEnableSoundFx() {
        return this.isEnableSoundFx;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isEnableMusic() {
        return this.isEnableMusic;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean useLeftAndRight() {
        return this.useLeftAndRight;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean useUpAndDown() {
        return this.useUpAndDown;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     *  Return a String that represents the settings object.
     *  @return a String that represents the settings object.
     *
     */
    @Override
    public String toString() {
        return "[isEnableSoundFx=" + isEnableSoundFx + ", isEnableMusic=" + isEnableMusic
                + ", useLeftAndRight=" + useLeftAndRight + ", useUpAndDown=" + useUpAndDown + ", difficulty="
                + difficulty + "]";
    }

    /**
    *
    *  Returns the hash code value for this settings.
    *  @return the hash code value for this settings.
    */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((difficulty == null) ? 0 : difficulty.hashCode());
        result = prime * result + (isEnableMusic ? HASH_FIRST_OPERAND : HASH_SECOND_OPERAND);
        result = prime * result + (isEnableSoundFx ? HASH_FIRST_OPERAND : HASH_SECOND_OPERAND);
        result = prime * result + (useLeftAndRight ? HASH_FIRST_OPERAND : HASH_SECOND_OPERAND);
        result = prime * result + (useUpAndDown ? HASH_FIRST_OPERAND : HASH_SECOND_OPERAND);
        return result;
    }

    /**
     *  Method that compare the specified object whit the current settings.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameSettingsImpl other = (GameSettingsImpl) obj;

        return other.isEnableSoundFx() == this.isEnableSoundFx 
               && other.isEnableMusic() == this.isEnableMusic
               && other.useLeftAndRight() == this.useLeftAndRight
               && other.useUpAndDown() == this.useUpAndDown
               && other.getDifficulty() == this.difficulty;
    }


}
