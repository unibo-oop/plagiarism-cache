package it.unibo.aurea.model;

import it.unibo.aurea.model.api.FollowUp;
import it.unibo.aurea.model.api.OutcomeType;

/**
 * {@inheritDoc}.
 */
public class FollowUpImpl implements FollowUp {
    private final String parentId;
    private final String childId;
    private final OutcomeType trigger;
    private final int delayTurn;

    /**
     * Constructor for a new follow-up.
     * 
     * @param parentId the identifier of the parent card
     * @param childId the identifier of the child card to be triggered
     * @param trigger the required outcome
     * @param delayTurn the number of turns to wait before triggering the child card
     */
    public FollowUpImpl(final String parentId, 
        final String childId, 
        final OutcomeType trigger, 
        final int delayTurn) {
        this.parentId = parentId;
        this.childId = childId;
        this.trigger = trigger;
        this.delayTurn = delayTurn;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String getParentId() {
        return this.parentId;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String getChildId() {
        return this.childId;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public OutcomeType getTrigger() {
        return this.trigger;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int getDelayTurn() {
        return this.delayTurn;
    }

}
