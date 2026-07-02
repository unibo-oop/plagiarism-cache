package javagotchi.model.minigame;

import java.util.HashMap;
import java.util.Map;

import javagotchi.controller.minigame.main.MiniGame;

/**
 * 
 * @author marica
 *
 */
public class ScoreImpl implements Score {

    private static final long serialVersionUID = 7268372234257742898L;
    private static final String SCORELAB = "Score: ";
    private static final String BESTSCORELAB = "Best Score: ";
    private static final int SCORE_START = 0;
    private int currentScore;
    private static final int INC = 5;

    private final Map<String, Integer> bestScoreMap = new HashMap<>();

    /**
     * Constructor for ScoreImpl.
     */
    public ScoreImpl() {
        currentScore = SCORE_START;
    }

    /**
     * Constructor for ScoreImpl.
     * 
     * @param score
     *            previous score.
     */
    public ScoreImpl(final Integer score) {
        currentScore = score.intValue();
    }

    @Override
    public final int getCurrentScore() {
        return currentScore;
    }

    @Override
    public final void setCurrentScore() {
        currentScore += INC;
    }

    @Override
    public final int getBestScore() {
        this.readIfExistFile();
        if (this.isImprovedScore()) {
            MiniGame.getFactoryController().getControllerMiniGame().getSavedData().writeBestScore(bestScoreMap);
        }
        return bestScoreMap.get(MiniGame.getFactoryController().getControllerMiniGame().getModel().getGotchi()
                .getInformation().getName());
    }

    private void readIfExistFile() {
        if (MiniGame.getFactoryController().getControllerMiniGame().getSavedData().existFileBestScore()) {
            bestScoreMap.putAll(MiniGame.getFactoryController().getControllerMiniGame().getSavedData().readBestScore());
        }
    }

    private boolean isImprovedScore() {
        final String name = MiniGame.getFactoryController().getControllerMiniGame().getModel().getGotchi()
                .getInformation().getName();
        final boolean isRegistered = this.bestScoreMap.containsKey(name);
        if (!isRegistered || (isRegistered && this.bestScoreMap.get(name) < currentScore)) {
            this.bestScoreMap.put(name, currentScore);
            return true;
        }
        return false;
    }

    @Override
    public final String getStringScore() {
        return SCORELAB + this.currentScore;
    }

    @Override
    public final String getStringBestScore() {
        return BESTSCORELAB + this.getBestScore();
    }

}
