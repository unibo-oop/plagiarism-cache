package zombietsunami.model.zombiemodel.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import zombietsunami.model.EntityImpl;
import zombietsunami.model.zombiemodel.api.JumpZombie;

/**
 * Implementation of the JumpZombie interface representing the behavior of a
 * jumping zombie in the game.
 */
public class JumpZombieImpl implements JumpZombie {
    private boolean jumping;
    private boolean jumpingDown;
    private boolean jumpUp;
    private int initialY;
    private int maxY;
    private static final int MAX_Y_VALUE = 90;
    private final EntityImpl entity;

    /**
     * Constructs a jumpZombieImpl with the specified EntityImpl as the underlying
     * entity.
     * 
     * @param entity the underlying entity for the jumpZombie behavior.
     */
    @SuppressFBWarnings(justification = "entity must be"
            + "passed an object to another class , otherwise the code won't work.")
    public JumpZombieImpl(final EntityImpl entity) {
        this.entity = entity;
    }

    private void decreaseZombieMapY() {
        this.entity.setY(this.entity.getY() - this.entity.getSpeed());
    }

    private void increaseZombieMapY() {
        this.entity.setY(this.entity.getY() + this.entity.getSpeed());
    }

    /**
     * Initiates the jump action for the zombie.
     */
    @Override
    public void jumpPress() {
        jumping = true;
        jumpUp = true;
        initialY = this.entity.getY();
        maxY = initialY - MAX_Y_VALUE;
    }

    /**
     * Updates the jump action for the zombie.
     */
    @Override
    public void updateJumpZombie() {
        if (jumping) {
            if (jumpUp) {
                jumpUp();
            } else if (jumpingDown) {
                jumpDown();
            }
        }
    }

    private void jumpDown() {
        if (this.entity.getY() < initialY) {
            increaseZombieMapY();
        } else {
            jumping = false;
            jumpingDown = false;
        }
    }

    private void jumpUp() {
        if (this.entity.getY() > maxY) {
            decreaseZombieMapY();
        } else {
            jumpingDown = true;
            jumpUp = false;
        }
    }

    /**
     * Gets the current jumping state of the zombie.
     * 
     * @return true if the zombie is currently jumping.
     */
    @Override
    public boolean isJumping() {
        return jumping;
    }
}
