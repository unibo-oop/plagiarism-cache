package hotelmaster.reservations;

import java.util.Map;

import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.structure.Hotel;
import hotelmaster.structure.Room;
import hotelmaster.utility.time.FixedPeriod;

/**
 * Builds a stay without performing any checks on the integrity of the stay,
 * except for the finalizing {@link UnboundStayBuilder#complete()} method, and
 * without writing to the data source. To be used when loading the Hotel date
 * from a reliable data source. Use {@link StayBuilder} for user-side adding.
 */
public interface UnboundStayBuilder extends StayBuilder {
    /**
     * Adds a room to this stay.
     * 
     * @param room
     *            the room to be added
     * @param people
     *            the people in the room
     * @return this UnboundStayBuilder instance
     */
    UnboundStayBuilder addRoom(Room room, Map<PersonPriceDescriber, Integer> people);

    /**
     * Sets the {@link Client} of this {@link Stay} and adds the given
     * {@link Client} to the {@link Hotel}.
     * 
     * @param client
     *            the client to be set
     * @return this UnboundStayBuilder instance
     */
    UnboundStayBuilder setClient(Client client);

    /**
     * Sets the beginning and end dates of this Stay.
     * 
     * @param dates
     *            the dates to be set
     * @return this UnboundStayBuilder instance
     */
    UnboundStayBuilder setDates(FixedPeriod dates);

    /**
     * Sets the type of this Stay.
     * 
     * @param stayType
     *            the stay type to be set.
     * @return this StayBuilder instance
     */
    UnboundStayBuilder setType(StayTypePriceDescriber stayType);

    /**
     * Adds a stay extra to the already added extras.
     * 
     * @param stayExtra
     *            the extra to be added
     * @return this StayBuilder instance
     */
    UnboundStayBuilder addExtra(StayExtraPriceDescriber stayExtra);

    /**
     * Sets the stay to the given state.
     * 
     * @param state
     *            the state of the stay
     * @return this StayBuilder instance
     */
    UnboundStayBuilder setState(StayState state);

    /**
     * Completes the StayBuilder, disabling further function calls and writing
     * the stay to the hotel.
     * 
     * @throws IllegalStateException
     *             the functions haven't been properly called
     */
    void complete() throws IllegalStateException;

    /**
     * Return an instanced UnboundStayBuilder.
     * 
     * @return the instance
     */
    static UnboundStayBuilder create() {
        return new UnboundStayBuilderImpl();
    }
}
