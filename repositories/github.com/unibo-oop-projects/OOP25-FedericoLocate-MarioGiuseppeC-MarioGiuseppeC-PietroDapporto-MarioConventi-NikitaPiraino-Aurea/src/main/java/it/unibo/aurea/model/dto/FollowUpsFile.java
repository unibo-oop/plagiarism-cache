package it.unibo.aurea.model.dto;

import java.util.List;

/**
 * DTO representing the collection of follow-up rules loaded from configuration.
 * Encapsulates an immutable list of {@link FollowUpDTO}, each describing
 * a relationship between a parent card and a child card, including
 * triggering conditions and timing.
 *
 * @param followups the list of follow-up definitions
 */
public record FollowUpsFile(List<FollowUpDTO> followups) {
    /**
     * Constructor of this class.
     * 
     * @param followups the {@code List} of all the elements follow-up
     */
    public FollowUpsFile {
        followups = List.copyOf(followups);
    }
}
