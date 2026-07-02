package tmw.controller.entities;

import java.util.Optional;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.controller.world.WorldController;
import tmw.model.entities.BulletEntity;
import tmw.model.objects.BaseGameObject;

/**
 * This abstract Class represents a controller for the bullets. It's abstract
 * because the method checkCollisions depends by the type of the bullet handled
 * by this controller, so has to be implemented by the those classes that extend
 * this one.
 */
public abstract class AbstarctBulletController implements EntityController<BulletEntity> {

    private final WorldController worldController;
    private final BulletEntity bullet;

    /**
     * Construct a new bullet controller.
     * 
     * @param worldController - The WorldController that is used by the controller
     *                        to communicate with the rest of the game
     * @param bullet          - The bullet that this controller has to handle
     */
    public AbstarctBulletController(final WorldController worldController, final BulletEntity bullet) {
        this.worldController = worldController;
        this.bullet = bullet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        final P2d newPosition = this.bullet.getCurrentPos()
                .sum(this.bullet.getCurrentVel().getNormalized().mul(this.bullet.getSpeed()));

        if (this.checkPosition(newPosition)) {
            this.bullet.update(Optional.of(newPosition));
        } else {
            this.delete();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete() {
        this.worldController.removeBullet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        this.worldController.getView().render(this.bullet, this.bullet.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BulletEntity getEntity() {
        return this.bullet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resizeEntity(final Dim2D dimension) {
        this.getEntity().resetDefaultDimension(dimension);
    }

    /**
     * Returns the world controller stored in this class. It's protected because it
     * can used by those classes that extend this one to implement the abstract
     * method.
     * 
     * @return the world controller stored in this class
     */
    protected WorldController getWorldController() {
        return worldController;
    }

    private boolean checkPosition(final P2d newPosition) {
        if ((newPosition.getX() + this.getEntity().getBoundary().getWidth()) > this.getWorldController().getGameWorld()
                .getWorldArea().getWidth()
                || (newPosition.getY() + this.getEntity().getBoundary().getHeight()) > this.getWorldController()
                        .getGameWorld().getWorldArea().getHeight()
                || newPosition.getX() < 0 || newPosition.getY() < 0) {
            return false;
        }

        for (final BaseGameObject o : this.getWorldController().getObstacleLoaded()) {
            if (o.intersect(this.getEntity())) {
                return false;
            }
        }

        return !this.checkCollisions(newPosition);
    }

    /**
     * This method check the collision with those entity that the bullet should hit.
     * 
     * @param newPosition - the position where to check the collisions
     * @return true if the bullet is colliding, false otherwise
     */
    protected abstract boolean checkCollisions(P2d newPosition);

}
