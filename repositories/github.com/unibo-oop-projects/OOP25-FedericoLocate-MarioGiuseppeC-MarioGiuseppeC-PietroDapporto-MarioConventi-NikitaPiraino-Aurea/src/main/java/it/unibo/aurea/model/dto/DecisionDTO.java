package it.unibo.aurea.model.dto;

import java.util.List;

/**
 * Immutable model of the consequence of a card.
 * 
 * @param text the related answer
 * @param effects a {@code List<EffectDTO>} with 2 element
 */
public record DecisionDTO(String text, List<EffectDTO> effects) {

    /**
     * Constructor to store an internal representation of the list.
     */
    public DecisionDTO {
        effects = List.copyOf(effects);
    }
}
