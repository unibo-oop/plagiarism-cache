
package zombietsunami;

import org.junit.jupiter.api.Test;

import zombietsunami.model.ModelImpl;
import zombietsunami.model.zombiemodel.impl.ZombieImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for ZombieImpl.
 * 
 * This class contains unit tests for various functionalities of the ZombieImpl
 * class.
 */
class TestZombie {
    private final ModelImpl model = new ModelImpl();
    private final ZombieImpl zombie = new ZombieImpl();
    private final int initialX = zombie.getX();
    private final int initialY = zombie.getY();
    private final int screenX = zombie.getScreenX();
    private final int screenY = zombie.getScreenY();
    private final int initialSpeed = zombie.getSpeed();
    private static final int RANGEY = 90;
    private final int maxY = initialY - RANGEY;

    /**
     * Test method to verify the default position of the zombie.
     * 
     * This test ensures that the zombie's initial position is correctly set to
     * (initialX, initialY)
     * and its speed is set to initialSpeed.
     */

    @Test
    void checkDefaultPosition() {
        assertEquals(zombie.getX(), initialX);
        assertEquals(zombie.getY(), initialY);
        assertEquals(zombie.getSpeed(), initialSpeed);
        assertEquals(zombie.getScreenX(), screenX);
        assertEquals(zombie.getScreenY(), screenY);

    }

    /**
     * Test method to verify the update of the zombie's position.
     * 
     * This test checks if the zombie's position is correctly updated after
     * calling the update method twice. It verifies that the zombie moves two units
     * to the right along the X-axis while maintaining its Y position.
     */

    @Test
    void checkUpdatePosition() {
        zombie.update();
        zombie.update();
        assertEquals(zombie.getX(), initialX + 2);
        assertEquals(zombie.getY(), initialY);
    }

    /**
     * Test method to verify the jumping behavior of the zombie.
     * 
     * This test validates the jumping functionality of the zombie by:
     * 1. Triggering the jump action and ensuring the zombie enters the jumping
     * state.
     * 2. Simulating the zombie's jump to its maximum height (Y = maxY), checking
     * its
     * X position remains unchanged.
     * 3. Verifying that the zombie's Y position is not greater than maxY after
     * reaching the maximum height.
     * 4. Confirming that the zombie returns to its original position after
     * completing the jump.
     * 5. Ensuring the zombie exits the jumping state after completing the jump.
     */

    @Test
    void checkJumpPosition() {
        zombie.jumpPress();
        assertTrue(zombie.isJumping());

        for (int i = initialY; i >= maxY; i--) {
            assertEquals(zombie.getX(), initialX);
            zombie.updateJumpZombie();
        }

        assertFalse(zombie.getY() > maxY);
        assertEquals(zombie.getY(), maxY);
        assertEquals(zombie.getX(), initialX);

        for (int i = maxY; i <= initialY; i++) {
            assertEquals(zombie.getX(), initialX);
            zombie.updateJumpZombie();
        }
        assertEquals(zombie.getY(), initialY);
        assertFalse(zombie.isJumping());
    }

    /**
     * Test method to verify that a zombie cannot start another jump while already
     * in mid-air.
     * 
     * This test ensures that when a zombie is already in the jumping state,
     * attempting to initiate
     * another jump does not change its jumping state.
     */
    @Test
    void checkCannotJumpWhileAlreadyJumping() {
        zombie.jumpPress();
        assertTrue(zombie.isJumping());

        zombie.jumpPress();
        assertTrue(zombie.isJumping());
    }

    /**
     * This method checks the strength of the zombie.
     * It verifies that the zombie's initial strength is 1,
     * then increases its strength and verifies it has been incremented to 2.
     */
    @Test
    void checkStregth() {
        zombie.getStrength();
        assertEquals(zombie.getStrength(), 1);
        zombie.increaseStrength();
        assertEquals(zombie.getStrength(), 2);
        zombie.decreaseStrength();
        assertEquals(zombie.getStrength(), 1);
    }

    /**
     * This method checks the model with the zombie.
     * It compares various attributes between the zombie and the model,
     * ensuring they match appropriately.
     * It also tests the update and jump functionality of both the zombie and the
     * model.
     */
    @Test
    void checkModelWithZombie() {
        assertEquals(zombie.getStrength(), model.getStrength());
        assertEquals(zombie.getScreenX(), model.getZombieScreenX());
        assertEquals(zombie.getScreenY(), model.getZombieScreenY());
        assertEquals(zombie.getSpeed(), model.getSpeed());
        assertEquals(zombie.getX(), model.getZombieMapX());
        assertEquals(zombie.getY(), model.getZombieMapY());
        assertEquals(zombie.isJumping(), zombie.isJumping());

        model.updateZombie();
        zombie.update();
        assertEquals(zombie.getX(), model.getZombieMapX());

        model.jumpPress();
        zombie.jumpPress();
        assertTrue(model.isJumping());
        assertTrue(zombie.isJumping());
        assertEquals(zombie.isJumping(), model.isJumping());

        model.updateJumpZombie();
        zombie.updateJumpZombie();
        assertEquals(zombie.getY(), model.getZombieMapY());
    }
}
