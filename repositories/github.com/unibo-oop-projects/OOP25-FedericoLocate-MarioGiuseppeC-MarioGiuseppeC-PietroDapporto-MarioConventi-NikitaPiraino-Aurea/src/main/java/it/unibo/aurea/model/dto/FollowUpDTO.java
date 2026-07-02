package it.unibo.aurea.model.dto;

import it.unibo.aurea.model.api.OutcomeType;

/**
 * DTO representing a follow-up rule between two cards.
 * Defines how and when a child card should be triggered after a parent card,
 * optionally depending on the outcome of the player's decision.
 *
 * @param parentId the identifier of the parent card
 * @param childId the identifier of the child card to be triggered
 * @param trigger the required outcome of a decision
 * @param delayTurn the number of turns to wait before triggering the child card
 */
public record FollowUpDTO(
    String parentId,
    String childId,
    OutcomeType trigger,
    int delayTurn) {
}
