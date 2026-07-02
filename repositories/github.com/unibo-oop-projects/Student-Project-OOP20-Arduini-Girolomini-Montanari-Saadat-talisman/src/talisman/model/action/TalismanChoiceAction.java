package talisman.model.action;

import java.util.ArrayList;
import java.util.List;

import talisman.view.TalismanChoiceActionWindow;

/**
 * Action that lets the user choose between sub-actions. It has the option to
 * have an empty action as the first one.
 * 
 * @author Alberto Arduini
 * 
 * @param <X> the type of item to be chosen
 *
 */
public abstract class TalismanChoiceAction<X>  extends TalismanActionImpl {
    private static final long serialVersionUID = 6981329627204017530L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply() {
        this.showChoicePanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        String description = this.getDescriptionFormat();
        for (int i = 0; i < this.getChoicesCount(); i++) {
            description = description.concat(System.lineSeparator() + this.getChoiceDescription(i));
        }
        return description;
    }

    /**
     * Gets the choice at the given index.
     * 
     * @param index the choice index
     * 
     * @return the choice object
     */
    public abstract X getChoice(int index);

    /**
     * Gets the description of the choice at the given index.
     * 
     * @param index the choice index
     * 
     * @return the choice object
     */
    public abstract String getChoiceDescription(int index);

    /**
     * Gets if the specified choice can be selected.
     * 
     * @param index the choice index
     * 
     * @return {@code true} if it's selectable, {@code false} otherwise
     */
    public abstract Boolean isChoiceEnabled(int index);

    /**
     * Gets the actions count, excluding the empty one.
     * 
     * @return the actions count
     */
    public abstract int getChoicesCount();

    /**
     * Gets the format of the description.
     * 
     * @return the description format
     */
    protected abstract String getDescriptionFormat();

    /**
     * Applies the given choice on the current player.
     * 
     * @param choice the choice index
     * 
     * @return if the choice has been applied or not
     */
    protected abstract boolean applyChoice(int choice);

    private void showChoicePanel() {
        final List<String> choices = new ArrayList<>();
        final List<Boolean> statuses = new ArrayList<>();
        for (int i = 0; i < this.getChoicesCount(); i++) {
            choices.add(this.getChoiceDescription(i));
            statuses.add(this.isChoiceEnabled(i));
        }
        TalismanChoiceActionWindow.show(choices, statuses, this::choiceChoosen);
    }

    private void choiceChoosen(final int choice) {
        if (choice < 0 || choice >= this.getChoicesCount() || !this.applyChoice(choice)) {
            this.showChoicePanel();
        }
    }
}
