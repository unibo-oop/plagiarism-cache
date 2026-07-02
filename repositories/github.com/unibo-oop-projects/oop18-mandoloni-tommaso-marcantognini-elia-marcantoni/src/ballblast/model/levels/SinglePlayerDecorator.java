package ballblast.model.levels;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.Model;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.helpers.GameObjectHelper;
import ballblast.model.inputs.InputManager.PlayerTag;

/**
 * Represents a decorator for levels which add the player object and ends when
 * the player is dead.
 */
public class SinglePlayerDecorator extends LevelDecorator {
    private static final Coordinate PLAYER_POSITION = initPlayerPosition();
    private final GameObject player;

    /**
     * Class constructor.
     * 
     * @param level the {@link Level} used like decoration.
     */
    public SinglePlayerDecorator(final Level level) {
        super(level);
        this.player = this.createPlayer();
        this.addPlayer(this.player);
    }

    @Override
    public final void update(final double elapsed) {
        if (this.getGameStatus() == GameStatus.RUNNING) {
            super.update(elapsed);
            this.checkGameOver();
        }
    }

    private void addPlayer(final GameObject player) {
        this.getGameObjectManager().addGameObjects(ImmutableList.of(player));
    }

    private GameObject createPlayer() {
        return GameObjectHelper.createPlayer(
                this.getGameObjectManager(), this.getInputManager(), PlayerTag.FIRST, 
                this.getCollisionManager(), Vector2D.create(0, 0), PLAYER_POSITION, 
                this.getGameDataManager(), this.getGameEventManager());
    }

    private void checkGameOver() {
        if (this.player.isDestroyed()) {
            this.setGameStatus(GameStatus.OVER);
        }
    }

    private static Coordinate initPlayerPosition() {
        final double bottomOffset = 17.1;
        return new Coordinate(Model.WORLD_WIDTH / 2, Model.WORLD_HEIGHT - bottomOffset);
    }
}
