package hotelmaster.reservations;

import java.util.Set;

import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.structure.HotelEntity;
import hotelmaster.utility.time.FixedPeriod;

/**
 * A stay defined during a certain {@link FixedPeriod}. Contains the
 * {@link Room}s, {@link StayExtraPriceDescriber}s and
 * {@link StayTypericeDescriber} linked to it. Comparison between stays should
 * be done through the {@link FixedPeriod}s.
 */
public interface Stay extends Comparable<Stay>, HotelEntity {

    /**
     * Returns whether the stay is in effect or not at today's date.
     * 
     * @return the state of the stay
     */
    StayState getState();

    /**
     * Get the nominative associated to this stay.
     * 
     * @return the nominative
     */
    Client getClient();

    /**
     * Returns the period of time linked to this stay.
     * 
     * @return the FixedPeriod dates
     */
    FixedPeriod getDates();

    /**
     * Returns the type of the stay.
     * 
     * @return the type
     */
    StayTypePriceDescriber getType();

    /**
     * Returns the extras of the stay, if there are any.
     * 
     * @return the extras
     */
    Set<StayExtraPriceDescriber> getExtrasView();

    /**
     * Returns the rooms linked to the stay.
     * 
     * @return the rooms
     */
    Set<Occupation> getOccupationsView();

    /**
     * Returns the total price of the stay.
     * 
     * @return the price of the stay
     */
    double getTotalPrice();

    /**
     * Returns the stay manager relevant to this stay. If {@link Stay#getState}
     * returns {@link StayState#ACTIVE} a {@link ActiveStayManager} will be
     * returned. If {@link Stay#getState} returns {@link StayState#INACTIVE} a
     * {@link InactiveStayManager} will be returned. If {@link Stay#getState()}
     * returns {@link StayState#CLOSED} a null will be returned, to denote the
     * incorrect usage of this function.
     * 
     * 
     * @return the stay manager
     */
    StayManager getStayManager();
}
