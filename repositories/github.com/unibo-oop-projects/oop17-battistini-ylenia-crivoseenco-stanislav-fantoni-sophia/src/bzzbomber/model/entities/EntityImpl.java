package bzzbomber.model.entities;

import java.awt.Point;
import java.awt.Rectangle;
import bzzbomber.game.Game;
import bzzbomber.view.gamefield.TileImpl;

/**
 * Implementation of @Entity.
 *
 */
public abstract class EntityImpl implements Entity {

    private static final int ENTITY_WIDTH = (int) (Game.TILE_WIDTH * 0.9);
    private static final int ENTITY_HEIGHT = (int) (Game.TILE_HEIGHT * 0.9);

    private Point currentPosition;
    private final Rectangle collisionBox;

    /**
     * It creates a EntityImpl, a game element.
     * 
     * @param pos
     *            the initial position
     */
    public EntityImpl(final Point pos) {
        this.currentPosition = pos;
        this.collisionBox = new Rectangle(pos.x, pos.y, ENTITY_WIDTH, ENTITY_HEIGHT);
    }

    @Override
    public final void setPosition(final Point position) {
        this.currentPosition = position;
    }

    @Override
    public final Point getPosition() {
        return new Point(this.currentPosition);
    }

    @Override
    public final Rectangle getCollisionBox() {
        return this.collisionBox;
    }

    @Override
    public abstract TileImpl getTile();

    @Override
    public final void setCollisionBox(final Point point) {
        this.collisionBox.setLocation(point);
    }

}
