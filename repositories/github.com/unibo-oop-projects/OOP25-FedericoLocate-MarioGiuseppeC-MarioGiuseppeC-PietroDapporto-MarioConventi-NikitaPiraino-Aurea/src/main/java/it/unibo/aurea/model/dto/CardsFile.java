package it.unibo.aurea.model.dto;

import java.util.List;

/** 
 * Immutable item used as a wrapper for the Jackson Yaml. 
 * It allows to convert the .yaml file into a {@code List<CardDTO>}.
 * 
 * @param cards the {@code List<CardDTO>} stored
 */
public record CardsFile(List<CardDTO> cards) {

    /**
     * Constructor to store an internal representation of the list.
     */
    public CardsFile {
        cards = List.copyOf(cards);
    }
}
