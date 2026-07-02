package it.unibo.aurea.model.api;

import java.util.List;

import it.unibo.aurea.model.Decision;

/**
 * Represents a question made to the player. 
 * The {@code Decision} created must be in this order: first the refusal, second the approval.
 * 
 */
public interface Card {
    /**
     * An univocal id for this object.
     * 
     * @return a {@code String} representing the card
     */
    String getId();

    /**
     * Get the text of a question that desribes its purpose.
     * 
     * @return the {@code String} of a question
     */
    String getDescription();

    /** 
     * Indicates if a card has been used or not.
     * 
     * @return true if it's been used. Di default is false
     */
    boolean isUsed();

    /** 
     * Idicates the character assigned to this card.
     * 
     * @return the {@code Characters} of this card
     */
    CharacterType getCharacter();

    /**
     *  Idicates the refusal of this card.
     * 
     * @return a {@code Decision} with the consequences of the refusal
     */
    Decision getRefusal();

    /**
     *  Idicates the approval of this card.
     * 
     * @return a {@code Decision} with the consequences of the approval
     */
    Decision getApproval();

    /**
     * Represents an overview on the consequences of this card on the parameters.
     * 
     * @return a {@code List} of the effect connected with this card
     */
    List<Effect> getAllEffects();

    /**
     * Sets the usage of this card to {@code true}.
     */
    void changeUsage();
}
