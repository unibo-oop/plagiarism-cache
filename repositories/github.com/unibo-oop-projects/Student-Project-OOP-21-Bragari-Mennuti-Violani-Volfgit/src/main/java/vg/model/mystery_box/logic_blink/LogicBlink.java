package vg.model.mystery_box.logic_blink;

/**
 * This interface is used to create a new LogicBlink.
 */
public interface LogicBlink {
    /**
     * Set true if the object is blinking.
     * @param blinking defines if the object is blinking.
     */
    void setBlinking(boolean blinking);
    /**
     * This method is used to verify the visibility of the object.
     * @return defines if the object is visible.
     */
    boolean isShow();
    /**
     * This method is used to update the blinking.
     * @param elapsedTime defines the time.
     */
    void updateBlinking(long elapsedTime);
}
