package it.unibo.aurea.model.api;

/** 
 * Represents a relations between two cards.
 * If the parent card is picked the the child card must appear.
 */
public interface FollowUp {
    /**
     * @return the univocal {@code String} of the parent card.
     */
    String getParentId();

    /**
     * @return the univocal {@code String} of the child card.
     */
    String getChildId();

    /**
     * @return the required {@code OutcomeType} for the card to apply.
     */
    OutcomeType getTrigger();

    /**
     * @return the number of turns the child must wait after the parent.
     */
    int getDelayTurn();
}
