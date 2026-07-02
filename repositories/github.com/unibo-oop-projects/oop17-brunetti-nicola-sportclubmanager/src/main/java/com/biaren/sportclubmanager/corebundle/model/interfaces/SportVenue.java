package com.biaren.sportclubmanager.corebundle.model.interfaces;

/**
 * Defines a Sport Venue.
 * 
 * @author nbrunetti
 *
 */
public interface SportVenue extends Facility{
    /**
     * Get the total seats of the venue.
     * 
     * @return total seats
     */
    int getTotalSeats();

    /**
     * Get the approximate field length as an integer number.
     * 
     * @return field length
     */
    double getFieldLength();

    /**
     * Get the approximate field width as an integer number.
     * 
     * @return field width
     */
    double getFieldWidth();

    /**
     * Return true if venue in indoor, false if is outdoor.
     * 
     * @return if is indoor venue
     */
    boolean isIndoor();
}
