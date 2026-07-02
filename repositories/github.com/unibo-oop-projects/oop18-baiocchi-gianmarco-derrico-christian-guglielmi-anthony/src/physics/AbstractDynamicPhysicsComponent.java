package physics;

import java.awt.geom.Rectangle2D;
import java.util.Set;

import graphics.SpriteSheet;
import model.AbstractStillObject;
import model.GameObject;

/**
 * Class to manage any dynamic entity physics.
 */
public abstract class AbstractDynamicPhysicsComponent extends AbstractPhysicsComponent {

    private static final int POSITION_ADJUSTMENT = 10;
    private static final int HEIGHT_ADJUSTMENT = 5;
    private static final int WIDTH_ADJUSTMENT = 20;

    /**
     * Constructor.
     * @param entity : to manage its physics
     */
    public AbstractDynamicPhysicsComponent(final GameObject entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkWallsCollisions(final Set<AbstractStillObject> walls) {
        walls.forEach(wall -> {
//            HO MESSO I PRINTLN SOLO PER TOGLIERE I WARNINGS!
            if (wall.getBounds().intersects(this.getTopBound())) {
                System.out.println();
                //TODO
            } else if (wall.getBounds().intersects(this.getLowerBound())) {
                System.out.println();
                //TODO
            } else if (wall.getBounds().intersects(this.getRightBound())) {
                System.out.println();
                //TODO
            } else if (wall.getBounds().intersects(this.getLeftBound())) {
                System.out.println();
                //TODO
            }
        });
    }

    /**
     * Get a rectangle.
     * @return a rectangle to check a higher bound collision
     */
    public Rectangle2D getTopBound() {
        return new Rectangle2D.Double(super.getObject().getPosition().getX() + POSITION_ADJUSTMENT,
                                      super.getObject().getPosition().getY(),
                                      SpriteSheet.SPRITE_SIZE_IN_GAME - WIDTH_ADJUSTMENT,
                                      HEIGHT_ADJUSTMENT);
    }

    /**
     * Get a rectangle.
     * @return a rectangle to check a lower bound collision
     */
    public Rectangle2D getLowerBound() {
        return new Rectangle2D.Double(super.getObject().getPosition().getX() + POSITION_ADJUSTMENT,
                                      super.getObject().getPosition().getY() + SpriteSheet.SPRITE_SIZE_IN_GAME
                                                                             - HEIGHT_ADJUSTMENT,
                                      SpriteSheet.SPRITE_SIZE_IN_GAME - WIDTH_ADJUSTMENT,
                                      HEIGHT_ADJUSTMENT);
    }

    /**
     * Get a rectangle.
     * @return a rectangle to check a left bound collision
     */
    public Rectangle2D getLeftBound() {
        return new Rectangle2D.Double(super.getObject().getPosition().getX(),
                                      super.getObject().getPosition().getY() + POSITION_ADJUSTMENT,
                                      HEIGHT_ADJUSTMENT,
                                      SpriteSheet.SPRITE_SIZE_IN_GAME - WIDTH_ADJUSTMENT);
    }

    /**
     * Get a rectangle.
     * @return a rectangle to check a right bound collision
     */
    public Rectangle2D getRightBound() {
        return new Rectangle2D.Double(super.getObject().getPosition().getX() + SpriteSheet.SPRITE_SIZE_IN_GAME
                                                                             - HEIGHT_ADJUSTMENT,
                                      super.getObject().getPosition().getY() + POSITION_ADJUSTMENT,
                                      HEIGHT_ADJUSTMENT,
                                      SpriteSheet.SPRITE_SIZE_IN_GAME - WIDTH_ADJUSTMENT);
    }
}
