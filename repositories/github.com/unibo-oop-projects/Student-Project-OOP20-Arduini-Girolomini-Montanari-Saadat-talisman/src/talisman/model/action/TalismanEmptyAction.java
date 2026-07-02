package talisman.model.action;

/**
 * Models an empty action.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanEmptyAction  extends TalismanActionImpl {
    private static final long serialVersionUID = -2549899951117195372L;
    private static final String DESCRIPTION = "Do nothing";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return TalismanEmptyAction.DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply() {
        this.actionEnded();
    }
}
