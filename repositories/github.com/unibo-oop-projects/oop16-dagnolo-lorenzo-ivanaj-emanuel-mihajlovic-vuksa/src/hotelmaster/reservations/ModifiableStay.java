package hotelmaster.reservations;

import java.util.Set;

import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.utility.time.FixedPeriod;

/**
 * A stay whose parameters can be modified if it has not yet begun. If the stay
 * is already active, only the end date may be modified. In that case, all
 * functions (except for setDates in some situations) will throw an
 * IllegalArgumentException.
 */
public interface ModifiableStay extends Stay {

    /**
     * Sets given client as this stay's client.
     * 
     * @param client
     *            the client
     */
    void setClient(Client client);

    /**
     * Sets new dates for the stay. The new end date must be after or on today's
     * date, and there must be no overlaps with the reserved rooms' future
     * stays.
     * 
     * @param dates
     *            the new dates
     */
    void setDates(FixedPeriod dates);

    /**
     * Sets the type of the stay to a new value.
     * 
     * @param type
     *            the new type
     */
    void setType(StayTypePriceDescriber type);

    /**
     * Returns the occupations of this stay.
     * 
     * @return the occupations
     */
    Set<ModifiableOccupation> getOccupations();

    /**
     * Returns the extras of this stay.
     * 
     * @return the extras
     */
    Set<StayExtraPriceDescriber> getExtras();

    /**
     * Sets the state of the stay.
     * 
     * @param state
     *            the new state
     */
    void setState(StayState state);

    /**
     * Instances a new {@link ModifiableStay}.
     * 
     * @return the new instance
     */
    static ModifiableStay create() {
        return new StayImpl();
    }
}
