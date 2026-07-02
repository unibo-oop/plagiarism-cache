package bubbleshooter.model;

import java.util.Collections;
import java.util.List;
import bubbleshooter.model.bubble.Bubble;
import bubbleshooter.model.bubble.BubblesManager;
import bubbleshooter.model.game.GameStatus;
import bubbleshooter.model.game.level.BasicLevel;
import bubbleshooter.model.game.level.Level;
import bubbleshooter.model.game.level.SurvivalLevel;

/**
 * The class which manage the logic of the game. Implements the {@link Model}
 * interface.
 */
public class ModelImpl implements Model {

    private Level level;

    @Override
    public final void startBasicGame() {
        this.level = new BasicLevel();
        this.level.start();
    }

    @Override
    public final void startSurvivalGame() {
        this.level = new SurvivalLevel();
        this.level.start();
    }

    @Override
    public final void update(final double elapsed) {
        this.level.update(elapsed);
    }

    @Override
    public final GameStatus getGameStatus() {
        return this.level.getGameStatus();
    }

    @Override
    public final void setGameStatus(final GameStatus gameStatus) {
        this.level.setGameStatus(gameStatus);
    }

    @Override
    public final BubblesManager getBubblesManager() {
        return this.level.getBubblesManager();
    }

    @Override
    public final List<Bubble> getBubbles() {
        return Collections.unmodifiableList(this.level.getBubblesManager().getAllBubbles());
    }

    @Override
    public final Level getLevel() {
        return this.level;
    }
}
