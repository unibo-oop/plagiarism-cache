package bubbleshooter.controller.input;

import java.util.List;
import bubbleshooter.model.bubble.Bubble;
import bubbleshooter.model.game.BubbleSwitcher;

/**
 * Class that checks whether the {@link SwitchBubble} and the {@link ShootingBubble} can swapped.
 * Used to communicate with {@link GameController} and {@link BubbleSwitcher}.
 */
public class SwitcherController {

    private static final double LIMITS_SWITCH = 3;
    private double numSwitch;
    private BubbleSwitcher bubbleSwitcher;
    private List<Bubble> bubbles;

    /**
     * Constructor for a new SwitcherController.
     * 
     * @param bubbles , the list of all {@link Bubble}s.
     */
    public SwitcherController(final List<Bubble> bubbles) {
        this.bubbles = bubbles;
        this.bubbleSwitcher = new BubbleSwitcher(this.bubbles);
        this.setInitialNumSwitch();
    }

    /**
     * Method to recall the switch of the {@link Bubble}s if the switch limit has not been exceeded.
     */
    public final void switchControl() {
        if (!isSwitchEnd()) {
            this.increasesNumSwitch();
            this.bubbleSwitcher.switchBall();
        }
    }

    /**
     * Method to set the number of switches already made.
     * @param numSwitch , the number of switches already made.
     */
    public final void setNumSwitch(final double numSwitch) {
        this.numSwitch = numSwitch;
    }

    /**
     * Method to set the initial value of the number of switches already made.
     */
    public final void setInitialNumSwitch() {
        this.numSwitch = 0;
    }

    /**
     * Method to increase the number of switches already made.
     */
    public final void increasesNumSwitch() {
        this.numSwitch++;
    }

    /**
     * Method that checks whether the number of switches already made is greater than the possible switches.
     * @return the number of switches already made is greater than the possible switches.
     */
    public final boolean isSwitchEnd() {
        return this.numSwitch >= LIMITS_SWITCH;
    }

    /**
     * Method to set the list of all {@link Bubble}s.
     * @param bubbles , the list of all {@link Bubble}s.
     */
    public final void setBubbles(final List<Bubble> bubbles) {
        this.bubbles = bubbles;
        this.bubbleSwitcher = new BubbleSwitcher(this.bubbles);
    }

    /**
     * Method to get the number of switches already made.
     * @return the number of switches already made.
     */
    public final double getNumSwitch() {
       return this.numSwitch;
    }
}
