package talisman.model.action;

/**
 * A generic action that uses an amount (could be a max, min, ...).
 * 
 * @author Alberto Arduini
 *
 */
public abstract class TalismanAmountAction extends TalismanActionImpl {
    private static final long serialVersionUID = 834721071654192282L;

    private final int amount;

    /**
     * Creates a new generic amount action.
     * 
     * @param amount the action amount
     */
    public TalismanAmountAction(final int amount) {
        this.amount = amount;
    }

    /**
     * Gets the amount associated with this action.
     * 
     * @return the amount
     */
    public int getAmount() {
        return this.amount;
    }

}
