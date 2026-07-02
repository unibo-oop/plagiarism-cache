package model.entities;

import java.util.Objects;

import model.Model;
import model.entitiesutil.Entity;
import model.entitiesutil.EntityDirection;
import model.entitiesutil.EntityMovable;
import model.entitiesutil.PowerUpState;
import model.physics.CharacterMovement;
import model.physics.CollisionDirection;
import model.physics.MovementManager;

/**
 * Special object that spawns in a random position and gives point if picked by
 * the {@link Hero}.
 */
public class PowerUp extends EntityMovable {
    private static final int SCORE = 100;
    private final MovementManager movementManager;
    private PowerUpState state;
    private boolean isFalling;

    /**
     * Special object that spawns in a random position and gives point if picked by
     * the {@link Hero}.
     * 
     * @param model is the {@link Model}
     * @param x is the left x coordinate
     * @param y is the top y coordinate
     * @param width of the entity
     * @param height if the entity
     * @param direction of the entity
     */
    public PowerUp(final Model model, final int x, final int y, final int width, final int height,
            final EntityDirection direction) {
        super(Objects.requireNonNull(model), x, y, width, height, Objects.requireNonNull(direction));
        this.movementManager = new CharacterMovement(this.body);
        this.isFalling = true;
        this.state = PowerUpState.getRandom();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isTouchedBy(final Entity entity, final CollisionDirection collisionDirection) {
        if (entity.getType().equals("Hero")) {
            // Touch the hero: disappear and give points
            this.hide();
            this.getModel().addPoints(SCORE);
            this.getModel().getLevel().addToRecheckEntity(this);
        } else if (entity.getType().equals("Wall")) {
            // Touch the wall while going down: stop falling
            this.movementManager.fallUndo(entity.getY());
            this.isFalling = false;
            this.getModel().getLevel().addToRecheckEntity(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doStep() {
        if (this.isFalling) {
            this.movementManager.fall();
            this.getModel().getLevel().addToRecheckEntity(this);
        }
    }

    /**
     * @return the {@link PowerUpState} of the {@link PowerUp}
     */
    public PowerUpState getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAfterCollisionStep() {
    }
}
