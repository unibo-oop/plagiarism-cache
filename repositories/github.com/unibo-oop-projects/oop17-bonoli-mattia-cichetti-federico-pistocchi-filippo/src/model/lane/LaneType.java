package model.lane;

/**
 * LaneType is the type of the lane.
 */
public enum LaneType {

    /**
     * A lane with no obstacles where the frog can land safely.
     */
    SAFE,

    /**
     * A lane where vehicles are spawned.
     */
    STREET,

    /**
     * A lane where logs are spawned.
     */
    RIVER;

}
