package vg.controller;

import vg.controller.leaderboard.ScoreManager;
import vg.controller.leaderboard.ScoreManagerImpl;
import vg.model.score.ScoreImpl;
import vg.view.ViewManager;
import vg.view.gameOver.GameOverView;
import vg.view.utils.KeyAction;
import java.util.Optional;

public class GameOverController extends Controller<GameOverView> {

    private final ScoreManager scoreManager;
    private int score;
    private int round;

    public GameOverController(final GameOverView view, final ViewManager viewManager) {
        super(view, viewManager);
        this.scoreManager = ScoreManagerImpl.newScoreManager();
    }

    /**
     * Set info on gameover screen.
     * @param score Player score
     * @param round Game round
     */
    public void set(final int score, final int round) {
        this.score = score;
        this.round = round;
        this.getView().getViewController().setRound(round);
        this.getView().getViewController().setScore(score);
    }

    @Override
    public void keyTapped(final KeyAction k) {
    }

    @Override
    public void keyPressed(final KeyAction k) {
        if (k == KeyAction.ENTER) {
            Optional<String> name = this.getView().getViewController().getTypedName();
            name.ifPresent(s -> {
                this.scoreManager.saveScore(new ScoreImpl(s, this.score, this.round));
                this.scoreManager.saveOnFile();
                this.getViewManager().backHome();
            });
        }
    }

    @Override
    public void keyReleased(final KeyAction k) {

    }
}
