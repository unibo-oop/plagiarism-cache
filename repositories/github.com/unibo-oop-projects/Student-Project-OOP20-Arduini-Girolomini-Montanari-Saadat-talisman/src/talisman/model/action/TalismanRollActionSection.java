package talisman.model.action;

import java.io.Serializable;

/**
 * Models one of the possible results of the roll.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanRollActionSection implements Serializable {
    private static final long serialVersionUID = 6263075682855988004L;

    /**
     * Used to return the result of trying to apply a section after a roll.
     * 
     * @author Alberto Arduini
     *
     */
    enum ApplyResult {
        APPLIED, VALUE_NOT_ENOUGH, CANNOT_BE_APPLIED,
    }

    private final int fromValue;
    private final TalismanAction action;

    /**
     * Creates a new result.
     * 
     * @param fromValue the first valid roll result that applies this action
     * @param action    the action to apply
     */
    public TalismanRollActionSection(final int fromValue, final TalismanAction action) {

        super();
        this.fromValue = fromValue;
        this.action = action;
    }

    /**
     * Gets the action that will get applied at this section.
     * 
     * @return the action
     */
    public TalismanAction getAction() {
        return action;
    }

    /**
     * Gets the first valid roll result from which this section if applied.
     * 
     * @return the value
     */
    public int getFromValue() {
        return fromValue;
    }

    /**
     * Applies the underlying action if the result is in range.
     * 
     * @param rollResult the result of the roll
     * @return the operation result as a {@code ApplyResult}
     */
    public ApplyResult apply(final int rollResult) {
        if (rollResult < this.getFromValue()) {
            return ApplyResult.VALUE_NOT_ENOUGH;
        }
        if (!this.getAction().canBeApplied()) {
            return ApplyResult.CANNOT_BE_APPLIED;
        }
        this.getAction().apply();
        return ApplyResult.APPLIED;
    }
}
