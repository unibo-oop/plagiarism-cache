package model;

import view.TrainingArea;

/**
 * The implementation of the {@link Model}.
 * 
 */
public class ModelImpl implements Model { // NOPMD

    private UserPlayer player;
    private TrainingArea selectedArea;
    private DifficultyLevel difficultyLevel;
    private String minigame;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserPlayer(final UserPlayer player) {
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArea(final TrainingArea area) {
        this.selectedArea = area;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMiniGame(final String minigame, final DifficultyLevel difficultyLevel) {
        this.minigame = minigame;
        this.difficultyLevel = difficultyLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DifficultyLevel getDifficultyLevel() {
        return this.difficultyLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addScore(final Integer score) {
        this.player.getProgress().addMiniGameScore(selectedArea, minigame, score);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserPlayer getUserPlayer() {
        return this.player;
    }
}
