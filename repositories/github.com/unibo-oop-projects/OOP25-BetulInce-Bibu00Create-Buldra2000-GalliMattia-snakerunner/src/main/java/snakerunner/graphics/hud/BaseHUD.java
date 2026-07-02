package snakerunner.graphics.hud;

/**
 * Represents a basic component of the Heads-Up Display (HUD) that manages a single integer value.
 */
@FunctionalInterface
public interface BaseHUD {

    /**
     * Updates the current value displayed by the HUD component.
     * 
     * @param value value the new integer value to be set and displayed.
     */
    void setValue(int value);
}
