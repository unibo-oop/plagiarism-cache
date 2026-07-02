package it.unibo.aurea.model.dto;

import it.unibo.aurea.model.api.CharacterType;

/** 
 * DTO (data transfer object) representing an immutable model of a card.
 * Used as a bridge between the .yaml file and the card implementation.
 * 
 * @param id the {@code String} univocal for this card
 * @param character the {@code CharacterType} assigned to the card
 * @param description the text of the card
 * @param refusal the negative {@code DecisionDTO}
 * @param approval the positive {@code DecisionDTO}
 */
public record CardDTO(
    String id,
    CharacterType character,
    String description,
    DecisionDTO refusal,
    DecisionDTO approval) {
}
