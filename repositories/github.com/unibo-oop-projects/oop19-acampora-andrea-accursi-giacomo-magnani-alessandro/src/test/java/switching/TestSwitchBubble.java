package switching;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;


import bubbleshooter.controller.input.SwitcherController;
import bubbleshooter.model.bubble.Bubble;
import bubbleshooter.model.bubble.BubbleColor;
import bubbleshooter.model.bubble.ShootingBubble;
import bubbleshooter.model.bubble.SwitchBubble;
import bubbleshooter.model.game.level.BasicLevel;
import bubbleshooter.model.game.level.Level;
import bubbleshooter.model.game.level.SurvivalLevel;
import javafx.geometry.Point2D;

/**
 * Class used to Test the {@link ShootingComponent} and the {@link ShootingBubble}.
 *
 */
public class TestSwitchBubble {

    private static final double LIMITS = 3;

    private final Bubble switchBubble = new SwitchBubble(new Point2D(0, 0), BubbleColor.GREEN);
    private final Bubble testShootingBubble = new ShootingBubble(new Point2D(0, 0), BubbleColor.BLUE);
    private final List<Bubble> bubblesManager = new LinkedList<>();
    private final SwitcherController switcherController = new SwitcherController(bubblesManager);
    private final Level basicLevel = new BasicLevel();
    private final Level survivalLevel = new SurvivalLevel();


    /**
     * Method to test if the {@link ShootingBubble} at the switch takes on the color of the {@link SwitchBubble}.
     */
    @Test
    public final void testSwitch() {
        this.bubblesManager.add(this.testShootingBubble);
        this.bubblesManager.add(this.switchBubble);
        this.switcherController.setBubbles(bubblesManager);
        this.switcherController.switchControl();

        System.out.println(this.switchBubble.getColor());

        assertTrue(this.switchBubble.getColor().equals(BubbleColor.BLUE));
        assertTrue(this.testShootingBubble.getColor().equals(BubbleColor.GREEN));
    }

    /**
     * Method to test if the {@link ShootingBubble} doesn't take on the 
     * {@link SwitchBubble}'s color when the Switch Limit is exceeded.
     */
    @Test
    public final void testLimitsExceeded() {
        this.bubblesManager.add(this.testShootingBubble);
        this.bubblesManager.add(this.switchBubble);
        this.switcherController.setBubbles(bubblesManager);
        this.switcherController.setNumSwitch(LIMITS);
        this.switcherController.switchControl();

        assertFalse(this.switchBubble.getColor().equals(BubbleColor.BLUE));
        assertFalse(this.testShootingBubble.getColor().equals(BubbleColor.GREEN));
    }

    /**
     * Method to test if the {@link ShootingBubble} takes on the color 
     * of the {@link SwitchBubble} after Shooting in {@link BasicMode}.
     */
    @Test
    public final void testBasicSwitchAfterShot() {
        this.bubblesManager.add(testShootingBubble);
        this.bubblesManager.add(switchBubble);
        this.basicLevel.getBubblesManager().addBubbles(bubblesManager);
        this.basicLevel.loadShootingBubble();

        assertTrue(this.basicLevel.getBubblesManager().getShootingBubble().get().getColor().equals(BubbleColor.GREEN));
    }

    /**
     * Method to test if the {@link ShootingBubble} takes on the color 
     * of the {@link SwitchBubble} after Shooting in {@link SurvivalMode}.
     */
    @Test
    public final void testSurvivalSwitchAfterShot() {
        this.bubblesManager.add(testShootingBubble);
        this.bubblesManager.add(switchBubble);
        this.survivalLevel.getBubblesManager().addBubbles(bubblesManager);
        this.survivalLevel.loadShootingBubble();

        assertTrue(this.survivalLevel.getBubblesManager().getShootingBubble().get().getColor().equals(BubbleColor.GREEN));
    }
}
