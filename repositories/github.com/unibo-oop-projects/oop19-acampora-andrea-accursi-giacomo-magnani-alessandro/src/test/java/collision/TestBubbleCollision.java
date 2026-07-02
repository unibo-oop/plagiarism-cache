package collision;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import bubbleshooter.model.bubble.Bubble;
import bubbleshooter.model.bubble.GridBubble;
import bubbleshooter.model.bubble.ShootingBubble;
import bubbleshooter.model.bubble.BubbleColor;
import bubbleshooter.model.collision.CollisionController;
import bubbleshooter.model.component.CollisionComponent;
import bubbleshooter.model.component.ComponentType;
import bubbleshooter.model.game.level.BasicLevel;
import javafx.geometry.Point2D;

/**
 * Test class used to check if {@link CollisionController} and {@link CollisionComponent} work correctly.
 */
public class TestBubbleCollision {

    private final  CollisionController collisionController = new CollisionController(new BasicLevel());

    /**
     * Method to test if a {@link GridBubble} contains the {@link CollisionComponent}.
     */
    @Test
    public final void testComponentOnGridBubble() {
        final Bubble gridBubble = new GridBubble(new Point2D(0, 0), BubbleColor.BLUE);
        assertTrue(gridBubble.getComponent(ComponentType.COLLISIONCOMPONENT).isPresent());
     }

    /**
     * Method to test if a {@link ShootingBubble} contains the {@link CollisionComponent}.
     */
    @Test
    public final void testComponentOnShootingBubble() {
        final Bubble shootingBubble = new ShootingBubble(new Point2D(0, 0), BubbleColor.BLUE);
        assertTrue(shootingBubble.getComponent(ComponentType.COLLISIONCOMPONENT).isPresent());

     }

    /**
     * Method to test if a {@link Components} is linked with its {@link Bubble}.
     */
    @Test
    public final void testAllComponents() {
        final Bubble gridBubble = new GridBubble(new Point2D(0, 0), BubbleColor.BLUE);
        final Bubble shootingBubble = new ShootingBubble(new Point2D(0, 0), BubbleColor.BLUE);
        final CollisionComponent gridCollComponent = (CollisionComponent) gridBubble.getComponent(ComponentType.COLLISIONCOMPONENT).get();
        final CollisionComponent shootCollComponent = (CollisionComponent) shootingBubble.getComponent(ComponentType.COLLISIONCOMPONENT).get();
        assertTrue(gridCollComponent.getContainer().equals(gridBubble));
        assertTrue(shootCollComponent.getContainer().equals(shootingBubble));
    }
 
    /**
     * Method to test if the {@link CollisionController} can detect a {@link Collision}.
     */
    @Test
    public final void testFalseCollisions() {
        final Bubble gridBubble = new GridBubble(new Point2D(100, 100), BubbleColor.BLUE);
        final Bubble shootingBubble = new ShootingBubble(new Point2D(0, 0), BubbleColor.BLUE);
        assertFalse(this.collisionController.hasCollided(gridBubble, shootingBubble));
    }

    /**
     * Method to test if the {@link CollisionController} can detect a {@link Collision}.
     */
    @Test
    public final void testRightCollisions() {
        final Bubble gridBubble = new GridBubble(new Point2D(100, 100), BubbleColor.BLUE);
        final Bubble shootingBubble = new ShootingBubble(new Point2D(95, 95), BubbleColor.BLUE);
        assertTrue(this.collisionController.hasCollided(gridBubble, shootingBubble));
    }
}
