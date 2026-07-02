package talisman.model.action;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Action that lets the user choose between sub-actions.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanActionChoiceAction extends TalismanChoiceAction<TalismanAction> {
    private static final long serialVersionUID = 4757285566100287578L;
    private static final String DESCRIPTION_FORMAT = "Choose between:";
    private static final String SINGLE_ACTION_DESCRIPTION_FORMAT = "- %s";

    private final List<TalismanAction> actions;

    /**
     * Creates a new choice actions.
     * 
     * @param actions the list of possible actions
     */
    public TalismanActionChoiceAction(final List<TalismanAction> actions) {
        this.actions = List.copyOf(actions);
        this.actions.stream().forEach(a -> a.setActionEndedListener(this::actionEnded));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanAction getChoice(final int index) {
        return this.actions.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getChoiceDescription(final int index) {
        return String.format(TalismanActionChoiceAction.SINGLE_ACTION_DESCRIPTION_FORMAT,
                this.getChoice(index).getDescription());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isChoiceEnabled(final int index) {
        return this.getChoice(index).canBeApplied();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getDescriptionFormat() {
        return TalismanActionChoiceAction.DESCRIPTION_FORMAT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean applyChoice(final int choice) {
        if (!this.getChoice(choice).canBeApplied()) {
            return false;
        }
        this.getChoice(choice).apply();
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getChoicesCount() {
        return this.actions.size();
    }

    private void readObject(final ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        this.actions.stream().forEach(a -> a.setActionEndedListener(this::actionEnded));
    }
}
