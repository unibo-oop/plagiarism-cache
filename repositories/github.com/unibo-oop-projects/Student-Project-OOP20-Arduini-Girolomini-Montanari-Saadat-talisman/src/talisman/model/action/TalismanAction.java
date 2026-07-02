package talisman.model.action;

import java.io.Serializable;

/**
 * Interface for actions that will be applied to a player when it reaches a
 * cell.
 * 
 * @author Alberto Arduini
 *
 */
public interface TalismanAction extends Serializable {
    /**
     * The flag value in case an optional field is serialized.
     */
    int SERIALIZED_PRESENT = 1;
    /**
     * The flag value in case an optional field is not serialized.
     */
    int SERIALIZED_MISSING = 0;

    /**
     * Obtains the action's description.
     * 
     * @return the description
     */
    String getDescription();

    /**
     * Applies the action to the current player.
     */
    void apply();

    /**
     * Sets the listener waiting for the action to end.
     * 
     * @param listener the listener
     */
    void setActionEndedListener(ActionEndedListener listener);
    
    /**
     * Checks if the action can be applied to the current player.
     * 
     * @return if the action can be applied
     */
    default boolean canBeApplied() {
        return true;
    }
}
