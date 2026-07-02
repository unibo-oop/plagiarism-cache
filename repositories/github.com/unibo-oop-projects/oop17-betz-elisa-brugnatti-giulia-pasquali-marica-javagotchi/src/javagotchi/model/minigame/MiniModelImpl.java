package javagotchi.model.minigame;

import java.util.Random;

import javagotchi.model.Javagotchi;

/**
 * 
 * @author marica
 *
 */
public class MiniModelImpl implements MiniModel {

    private Javagotchi javagotchi;
    private GameGrid gameGrid;
    private Time time;
    private Score score;
    private GameState gameState;

    /**
     * Constructor for the ModelImpl.
     */
    public MiniModelImpl() {
        initModel();
        gameState = GameState.NULL;
    }

    @Override
    public final Javagotchi getGotchi() {
        return javagotchi;
    }

    @Override
    public final void setGotchi(final Javagotchi java) {
        javagotchi = java;
    }

    @Override
    public final GameGrid getGameGrid() {
        return gameGrid;
    }

    @Override
    public final Time getTime() {
        return time;
    }

    @Override
    public final void setTime(final Time time) {
        this.time = time;
    }

    @Override
    public final Score getScore() {
        return score;
    }

    @Override
    public final void setScore(final Score score) {
        this.score = score;
    }

    @Override
    public final void setGameState(final GameState state) {
        gameState = state;
    }

    @Override
    public final boolean isGameState(final GameState state) {
        return gameState.equals(state);
    }

    @Override
    public final void initModel() {
        this.setScore(new ScoreImpl());
        this.setGameGrid(new GameGridImpl());
        this.setTime(new TimeImpl());
    }

    private void setGameGrid(final GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    @Override
    public final boolean isMomentToAddTime() {
        return new Random().nextInt(this.time.getStartTime() - 1) + 1 == this.time.getSeconds();
    }

    @Override
    public final String toString() {
        return "GameState: " + gameState + ", Time: " + time.getSeconds() + ", Score : " + score.getCurrentScore();
    }

}
