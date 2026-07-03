package oop.lit.model.actions;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import oop.lit.model.Action;
import oop.lit.util.InputRequest;
import oop.lit.util.InputRequestsFactory;

/**
 * A partial implementation of an Action.
 */
public abstract class AbstractAction implements Action {
    private final String label;

    /**
     * @param label
     *      a brief description of the action.
     */
    protected AbstractAction(final String label) {
        Objects.requireNonNull(label);
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean canBePerformed() {
        return true;
    }

    /**
     * Throws an IllegalStateException if this action can't be performed.
     */
    protected void checkPerformable() {
        if (!this.canBePerformed()) {
            throw new IllegalStateException("Can't perform the action right now");
        }
    }

    @Override
    public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
        this.checkPerformable();
        return Collections.emptyList();
    }
}
