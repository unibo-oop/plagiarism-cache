package talisman.view;

/**
 * Used to show the result of a roll given by a RollAction.
 * 
 * @author Alberto Arduini
 *
 */
public interface TalismanRollActionResultWindow {
    /**
     * Shows a result window.
     * 
     * @param result            the roll result
     * @param actionDescription the action that gets applied
     */
    static void show(final int result, final String actionDescription) {
        new TalismanRollActionResultWindowImpl(result, actionDescription);
    }
}
