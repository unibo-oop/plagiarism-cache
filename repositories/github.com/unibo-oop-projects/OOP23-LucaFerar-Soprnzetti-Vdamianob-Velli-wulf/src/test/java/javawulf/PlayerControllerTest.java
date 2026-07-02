package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.map.Map;
import javawulf.model.map.factory.MapFactoryImpl;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;
import javawulf.controller.PlayerController;
import javawulf.controller.PlayerControllerImpl;

/**
 * Test class for PlayerController.
 */
final class PlayerControllerTest {

    private static final int HEALTH = 3;
    private static final int STARTING_X = 12;
    private static final int STARTING_Y = 12;
    private static final int STARTING_POINTS = 0;
    private Player player;
    private PlayerController controller;
    private boolean attacking;

    @BeforeEach
    void createPlayer() {
        this.player = new PlayerImpl(STARTING_X, STARTING_Y, HEALTH, STARTING_POINTS);
        this.controller = new PlayerControllerImpl();
    }

    @Test
    void testMovement() {
        this.controller.updatePlayerStatus(true, false, false, false);
        updatePlayer();
        assertEquals(this.controller.getDirection().get(), this.player.getDirection());
    }

    @Test
    void testAttack() {
        this.controller.updateSwordStatus(true);
        updatePlayer();
        assertEquals(CollisionType.SWORD, this.player.getSword().getBounds().getCollisionType());
        this.controller.updatePlayerStatus(true, false, false, false);
        updatePlayer();
        assertNotEquals(this.controller.getDirection().get(), this.player.getDirection());
        this.controller.updateSwordStatus(false);
        updatePlayer();
        assertNotEquals(CollisionType.SWORD, this.player.getSword().getBounds().getCollisionType());
    }

    private void updatePlayer() {
        final Map map = new MapFactoryImpl().getTestMap(player);
        if (this.controller.getDirection().isPresent() && !this.attacking) {
            this.player.move(this.controller.getDirection().get(), map);
        } else if (this.controller.isAttack() && !this.attacking) {
            this.player.attack();
            this.attacking = true;
        } else {
            this.player.getSword().deactivate();
            this.attacking = false;
        }
    }

}
