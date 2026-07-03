package hotelmaster.reservations;

/**
 * The state of a hotel.
 */
public enum StayState {
    /**
     * The stay is active. (today is between the stay's beginning and end)
     */
    ACTIVE,
    /**
     * The stay is inactive. (today is before the stay's beginning)
     */
    INACTIVE
}
