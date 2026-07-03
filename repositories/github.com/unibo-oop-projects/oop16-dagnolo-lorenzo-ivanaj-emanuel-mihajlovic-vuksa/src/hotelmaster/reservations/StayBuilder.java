package hotelmaster.reservations;

import java.util.Map;
import java.util.Optional;

import hotelmaster.exceptions.MissingEntityException;
import hotelmaster.exceptions.OccupiedRoomException;
import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.structure.Hotel;
import hotelmaster.structure.Room;
import hotelmaster.utility.time.FixedPeriod;

/**
 * Manages the creation of a stay through 2 steps. In the first step, the
 * client's information and the stay's duration are registerd. In the second
 * step, the rooms are added to the stay. After adding at least 1 room to the
 * stay, it may be finalized through the "complete" function. Once finalized,
 * the instance throws an exception for any method called upon it.
 */
public interface StayBuilder {

    /**
     * Adds a room to this stay. Must set the start and end dates and the
     * parameters related to the client before calling. Once this function is
     * successfully called, the parameters that have been previously set are
     * locked. Only "complete" and this function can be called from then on.
     * 
     * @param room
     *            the room to be added
     * @param people
     *            the people in the room
     * @return this StayBuilder instance
     * @throws MissingEntityException
     *             a given PersonPriceDescriber does not exist in the hotel
     * @throws IllegalArgumentException
     *             the room cannot hold the given amount of people
     * @throws IllegalStateException
     *             the dates or client-related parameters have not been all set
     * @throws OccupiedRoomException
     *             the room is occupied during the set dates
     */
    StayBuilder addRoom(Room room, Map<PersonPriceDescriber, Integer> people)
            throws MissingEntityException, IllegalArgumentException, IllegalStateException, OccupiedRoomException;

    /**
     * Sets the {@link Client} of this {@link Stay} and adds the given
     * {@link Client} to the {@link Hotel}.
     * 
     * @param client
     *            the client to be set
     * @return this StayBuilder instance
     * @throws IllegalStateException
     *             the StayBuilder is already past step 1
     * @throws IllegalArgumentException
     *             the Hotel already has a stay for the given client
     */
    StayBuilder setClient(Client client) throws IllegalStateException, IllegalArgumentException;

    /**
     * Sets the beginning and end dates of this Stay.
     * 
     * @param dates
     *            the dates to be set
     * @return this StayBuilder instance
     * @throws IllegalStateException
     *             the StayBuilder is already past step 1
     */
    StayBuilder setDates(FixedPeriod dates) throws IllegalStateException;

    /**
     * Returns the dates of this stay builder.
     * 
     * @return the dates
     */
    Optional<FixedPeriod> getDates();

    /**
     * Sets the type of this Stay.
     * 
     * @param stayType
     *            the stay type to be set.
     * @return this StayBuilder instance
     * @throws MissingEntityException
     *             the price describer does not exist in the hotel
     * @throws IllegalStateException
     *             the StayBuilder is already past step 1
     */
    StayBuilder setType(StayTypePriceDescriber stayType) throws MissingEntityException, IllegalStateException;

    /**
     * Adds a stay extra to the already added extras.
     * 
     * @param stayExtra
     *            the extra to be added
     * @return this StayBuilder instance
     * @throws MissingEntityException
     *             the price describer does not exist in the hotel
     * @throws IllegalStateException
     *             the StayBuilder is already past step 1
     */
    StayBuilder addExtra(StayExtraPriceDescriber stayExtra) throws MissingEntityException, IllegalStateException;

    /**
     * Completes the StayBuilder, disabling further function calls and writing
     * the stay to the hotel.
     * 
     * @throws IllegalStateException
     *             the functions haven't been properly called
     */
    void complete() throws IllegalStateException;

    /**
     * Return an instanced StayBuilder.
     * 
     * @return the instance
     */
    static StayBuilder create() {
        return new StayBuilderImpl();
    }
}
