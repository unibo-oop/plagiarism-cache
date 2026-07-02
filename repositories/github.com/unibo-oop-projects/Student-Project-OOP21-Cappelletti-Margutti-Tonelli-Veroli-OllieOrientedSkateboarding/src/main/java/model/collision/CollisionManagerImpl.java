package model.collision;

import java.util.List;

import javafx.geometry.Rectangle2D;
import model.Model;
import model.entity.DynamicEntity;
import model.entity.EntityType;
import model.player.Player;
import model.player.PlayerImpl;
/**
 * 
 * Implementation of {@link CollisionManager}.
 *
 */
public final class CollisionManagerImpl implements CollisionManager {

    private static final double COLLISION_BOUND = 15.0f;
    private double platformY;

    /**
     * {@inheritDoc}
     */
    @Override
    public void playerCollidesWidth(final Player pl, final List<DynamicEntity> objects, final Model model) {
        this.platformY = PlayerImpl.LAND;
        objects.forEach(e -> {
            if (e.getType() == EntityType.PLATFORM) {
                if (isPlayerAbove(pl, e)) {
                    platformY = e.getBounds().getMinY();
                }
            } else {
                final Rectangle2D playerBounds = shrinkBounds(pl.getBounds(), COLLISION_BOUND);
                if (pl.isShieldActive() && e.getType() == EntityType.OBSTACLE) {
                    e.hit(false);
                } else if (e.getBounds().intersects(playerBounds) && !e.wasHit()) {
                    e.onCollision(model);
                }
            }
        });
        pl.setLandHeight(platformY);
    }

    /**
     * 
     * @param player the player that has to be above
     * @param e the object that has to be below
     * @return true if player is above e 
     */
    private boolean isPlayerAbove(final Player player, final DynamicEntity e) {
        return player.getBounds().getMaxY() <= e.getBounds().getMinY() 
                && player.getBounds().getMaxX() >= e.getBounds().getMinX()
                && player.getBounds().getMinX() <= e.getBounds().getMaxX();
    }

    /**
     * 
     * @param bounds initial bounds
     * @param amount the amount to shrink each side
     * @return the modified bounds
     */
    private Rectangle2D shrinkBounds(final Rectangle2D bounds, final double amount) {
        return new Rectangle2D(bounds.getMinX() + amount, bounds.getMinY() + amount, 
                bounds.getWidth() - amount * 2, bounds.getHeight() - amount * 2);
    }

}
