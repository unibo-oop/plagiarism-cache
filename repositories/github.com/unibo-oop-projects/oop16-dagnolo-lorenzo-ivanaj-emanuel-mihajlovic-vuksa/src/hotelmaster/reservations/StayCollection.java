package hotelmaster.reservations;

import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.structure.HotelCollection;
import hotelmaster.utility.time.FixedPeriod;

/**
 * A collection of stays.
 */
public interface StayCollection extends HotelCollection<Stay> {

    /**
     * Activates the given stay.
     * 
     * @param stay
     *            the stay
     */
    void activate(Stay stay);

    /**
     * Closes the given stay.
     * 
     * @param stay
     *            the stay
     */
    void close(Stay stay);

    /**
     * Adds the given extra to the given stay.
     * 
     * @param stay
     *            the stay
     * @param extra
     *            the extra to be added
     * @return true if the stay has been modified
     */
    boolean addExtra(Stay stay, StayExtraPriceDescriber extra);

    /**
     * Removes the given extra from the stay.
     * 
     * @param stay
     *            the stay
     * @param extra
     *            the extra to be removed
     * @return true if the stay has been modified
     */
    boolean removeExtra(Stay stay, StayExtraPriceDescriber extra);

    /**
     * Set the stay's type to a given type.
     * 
     * @param stay
     *            the stay
     * @param type
     *            the type to be set
     */
    void setType(Stay stay, StayTypePriceDescriber type);

    /**
     * Sets the stay's dates to a new value.
     * 
     * @param stay
     *            the stay
     * @param dates
     *            the dates
     */
    void setDates(Stay stay, FixedPeriod dates);

    /**
     * Adds an occupation to the stay.
     * 
     * @param stay
     *            the stay
     * @param occupation
     *            the occupation
     * @return true if the stay has been modified
     */
    boolean addOccupation(Stay stay, Occupation occupation);

    /**
     * Removes an occupation from the stay.
     * 
     * @param stay
     *            the stay
     * @param occupation
     *            the occupation
     * @return true if the stay has been modified
     */
    boolean removeOccupation(Stay stay, Occupation occupation);

    /**
     * Sets an existing occupation of the stay to new values.
     * 
     * @param stay
     *            the stay
     * @param occupation
     *            the occupation
     * @return true if the stay has been modified
     */
    boolean setOccupation(Stay stay, Occupation occupation);
}
