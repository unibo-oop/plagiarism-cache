package game.engine;

import game.theme.Theme;
import model.BalloomEnemy;
import model.Block;
import model.Door;
import model.PickableObject;
import model.Player;
import utilities.Position;

/**
 * This class manage a factory for all kinds of GameObjects.
 */
public class GameObjectFactoryImpl implements GameObjectFactory {

    private final Theme theme;

    /**
     * Constructor.
     * @param theme : the theme of objects' sprite 
     */
    public GameObjectFactoryImpl(final Theme theme) {
        this.theme = theme;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player createPlayer(final Position position) {
        return new Player(position, true, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BalloomEnemy createBalloomEnemy(final Position position) {
        return new BalloomEnemy(position, true, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Block createUnbreakableBlock(final Position position) {
        return new Block(false, position, this.theme.getSprites().getWallSprite());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Block createBreakableBlock(final Position position) {
        return new Block(true, position, this.theme.getSprites().getBreakableWallSprite());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Door createDoor(final Position position) {
        return new Door(position, this.theme.getSprites().getDoorSprite());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PickableObject createKey(final Position position) {
        //TODO p.getKey() in PowerUpAction input!
        return new PickableObject(position, player -> player.getPosition(), this.theme.getSprites().getKeySprite());
    }
}
