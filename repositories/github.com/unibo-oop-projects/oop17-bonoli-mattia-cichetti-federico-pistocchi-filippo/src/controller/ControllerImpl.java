package controller;

import java.util.List;
import java.util.Optional;

import model.world.World;
import utilities.Pair;
import view.View;
import view.ViewImpl;

/**
 * controller implementation.
 */
public class ControllerImpl implements Controller {

    private LeaderboardManager lm;
    private View view;
    private GameLoop gameLoop;
    private int fps;
    private World world;
    private GameDifficult diff = GameDifficult.EASY;

    /**
     * @param v view
     * @param fps to be displayed
     */
    public ControllerImpl(final View v, final int fps) {
        view = v;
        lm = new LeaderboardManagerImpl();
        //JUST PLACEHOLDER FOR NOW
        gameLoop = new GameLoop(fps, this, view, diff);
    }
    /**
     * start the game loop.
     */
    public void startGame() {
        gameLoop.startGameLoop();
    }
    /**
     * stop the game.
     */
    public void stopGame() {
        gameLoop.stopGameLoop();
    }
    /**
     * pause the game.
     */
    public void pauseGame() {
        gameLoop.pauseGameLoop();
    }
    /**
     * retrieve the score.
     * @return score list
     */
    public List<Pair<String, Integer>> getScoreList() {
        return lm.getScoreList();
    }
    /**
     * set the difficult.
     * @param d difficult
     */
    public void setDifficult(final GameDifficult d) {
        diff = d;
    }

}
