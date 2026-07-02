package it.unibo.model.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.Pair;
import it.unibo.common.Rectangle;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.utilities.Constants;
import it.unibo.utilities.EntityType;
import it.unibo.utilities.Constants.Bird;
import it.unibo.utilities.Constants.Brick;
import it.unibo.utilities.Constants.Cake;
import it.unibo.utilities.Constants.Felix;
import it.unibo.utilities.Constants.Ralph;
import it.unibo.utilities.Constants.Window;

/**
 * HitboxComponent, it represents the hitbox of the entity.
 */
public class HitboxComponent extends AbstractComponent {

    private Rectangle hitbox;
    private boolean removeEntity;

    /**
     * Constructs a new HitboxComponent with the specified x and y coordinates and
     * entity type.
     * Depending on the entity type, the dimensions of the hitbox are set
     * differently.
     *
     * @param x    the x-coordinate of the entity initial position
     * @param y    the y-coordinate of the entity initial position
     * @param type the type of the entity, which determines the dimensions of the
     *             hitbox.
     */
    public HitboxComponent(final double x, final double y, final EntityType type) {
        switch (type) {
            case FELIX:
                this.hitbox = new Rectangle(x, y, Felix.FELIX_WIDTH, Felix.FELIX_HEIGHT);
                break;
            case RALPH:
                this.hitbox = new Rectangle(x, y, Ralph.RALPH_WIDTH, Ralph.RALPH_HEIGHT);
                break;
            case BRICK:
                this.hitbox = new Rectangle(x, y, Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT);
                break;
            case WINDOW:
                this.hitbox = new Rectangle(x, y, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
                break;
            case CAKE:
                this.hitbox = new Rectangle(x, y, Cake.CAKE_WIDTH, Cake.CAKE_HEIGHT);
                break;
            case BIRD:
                this.hitbox = new Rectangle(x, y, Bird.BIRD_WIDTH, Bird.BIRD_HEIGHT);
                break;
            default:
                break;
        }
    }

    /**
     * Updates the hitbox of the entity.
     */
    public void update() {
        final Entity entity = this.getEntity();
        final EntityType type = entity.getEntityType();
        this.checkEdgesCollisions();
        if (type == EntityType.FELIX) {
            this.checkEdgesCollisions();
            this.checkOtherEntitiesCollisions();
        } else if (type == EntityType.BRICK) {
            this.checkEdgesCollisions();
            this.checkOtherEntitiesCollisions();
        } else if (type == EntityType.CAKE) {
            this.checkOtherEntitiesCollisions();
        } else if (type == EntityType.BIRD) {
            this.checkOtherEntitiesCollisions();
        }
    }

    /**
     * Checks for collisions with the edges of the game area and handles them
     * accordingly.
     * If the hitbox of the entity goes beyond the left or right walls, it is
     * repositioned to the nearest valid position.
     * If the hitbox of the entity goes beyond the bottom wall and the entity is of
     * type BRICK, it is removed from the game.
     */
    public void checkEdgesCollisions() {
        if (this.hitbox.getX() < Constants.GameEdges.LEFT_WALL) {
            this.hitbox.setX(Constants.GameEdges.LEFT_WALL);
            this.getEntity().setPosition(new Pair<Double, Double>(Constants.GameEdges.LEFT_WALL, this.hitbox.getY()));
        }
        if (this.hitbox.getX() > Constants.GameEdges.RIGHT_WALL - this.hitbox.getWidth()) {
            this.hitbox.setX(Constants.GameEdges.RIGHT_WALL - this.hitbox.getWidth());
            this.getEntity()
                    .setPosition(
                            new Pair<Double, Double>(Constants.GameEdges.RIGHT_WALL - this.hitbox.getWidth(),
                                    this.hitbox.getY()));
        }
        if (this.hitbox.getY() <= Constants.GameEdges.UP_WALL && this.getEntity().getEntityType() == EntityType.BRICK) {
            this.removeEntity = true;
        }
    }

    /**
     * Checks for collisions with other entities.
     */
    public void checkOtherEntitiesCollisions() {
        this.getEntity().getGamePerformance().getEntity().stream()
                .filter(e -> !e.equals(this.getEntity()))
                .filter(e -> this.collidesWith((HitboxComponent) e.getTheComponent(ComponentType.HITBOX).get()))
                .forEach(e -> {
                    if (this.getEntity().getEntityType().equals(EntityType.FELIX)
                            && e.getEntityType().equals(EntityType.BRICK)
                            && !(((HitboxComponent) e.getTheComponent(ComponentType.HITBOX).get()).removeEntity)
                            && !(((LivesComponent) this.getEntity().getTheComponent(ComponentType.LIFE).get())
                                    .isImmortality())) {
                        ((HitboxComponent) e.getTheComponent(ComponentType.HITBOX).get()).removeEntity = true;
                        ((LivesComponent) this.getEntity().getTheComponent(ComponentType.LIFE).get()).stealLives();
                    }
                    if (this.getEntity().getEntityType().equals(EntityType.FELIX)
                            && e.getEntityType().equals(EntityType.CAKE)
                            && !(((HitboxComponent) e.getTheComponent(ComponentType.HITBOX).get()).removeEntity)) {
                        ((HitboxComponent) e.getTheComponent(ComponentType.HITBOX).get()).removeEntity = true;
                        ((ImmortalityComponent) this.getEntity().getTheComponent(ComponentType.IMMORTALITY).get())
                                .setImmortality(
                                        (LivesComponent) this.getEntity().getTheComponent(ComponentType.LIFE).get());
                    }
                    if (this.getEntity().getEntityType().equals(EntityType.FELIX)
                            && e.getEntityType().equals(EntityType.BIRD)
                            && !(((HitboxComponent) e.getTheComponent(ComponentType.HITBOX).get()).removeEntity)) {
                        ((HitboxComponent) e.getTheComponent(ComponentType.HITBOX).get()).removeEntity = true;
                        final Entity ralph = this.getEntity().getGamePerformance().getEntity().stream()
                                .filter(r -> r.getEntityType().equals(EntityType.RALPH)).findFirst().get();
                        ((StopRalphComponent) ralph.getTheComponent(ComponentType.STOPRALPH).get())
                                .setStopRalph(
                                        (ThrowBrickComponent) ralph.getTheComponent(ComponentType.THROWBRICK).get());
                    }
                });
    }

    /**
     * Checks for collisions with windows in the game and returns the position of
     * the first window collided with.
     * If the entity is Felix and collides with a window, the position of the window
     * is returned.
     * If no collisions occur, an empty Optional is returned.
     * 
     * @return An Optional containing the position of the first window collided
     *         with,
     *         or an empty Optional if no collisions occurred.
     */
    public Optional<Pair<Double, Double>> checkWindowsCollisions() {
        for (final Entity e : this.getEntity().getGamePerformance().getWindows()) {
            if (this.collidesWith((HitboxComponent) e.getTheComponent(ComponentType.HITBOX).get())
                    && this.getEntity().getEntityType().equals(EntityType.FELIX)
                    && e.getEntityType().equals(EntityType.WINDOW)) {
                return Optional.of(e.getPosition());
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the hitbox of the entity.
     *
     * @return the hitbox of the entity.
     */
    public Rectangle getHitbox() {
        return new Rectangle(this.hitbox.getX(), this.hitbox.getY(), this.hitbox.getWidth(), this.hitbox.getHeight());
    }

    /**
     * Sets the hitbox of the entity.
     *
     * @param hitbox the new hitbox of the entity.
     */
     @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public void setHitbox(final Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    /**
     * Checks if this hitbox collides with another hitbox.
     *
     * @param other the other hitbox to check collision with
     * @return true if this hitbox collides with the other hitbox, false otherwise.
     */
    public boolean collidesWith(final HitboxComponent other) {
        return this.hitbox.intersects(other.getHitbox());
    }

    /**
     * Checks if the entity should be removed from the game.
     *
     * @return true if the entity should be removed from the game, false otherwise.
     */
    public boolean shouldRemoveEntity() {
        return this.removeEntity;
    }

    /**
     * getter of the type of the class.
     * 
     * @return the type of the class.
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.HITBOX;
    }
}
