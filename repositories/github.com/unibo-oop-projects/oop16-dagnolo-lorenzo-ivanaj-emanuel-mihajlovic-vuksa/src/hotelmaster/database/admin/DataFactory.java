package hotelmaster.database.admin;

import hotelmaster.database.secretary.ModifiableReservation;
import hotelmaster.database.secretary.Reservation;
import hotelmaster.database.utilities.BasicReservationUtilities;
import hotelmaster.database.utilities.DocumentUtilities;
import hotelmaster.database.utilities.PersonPriceUtilities;
import hotelmaster.database.utilities.RoomExtraUtilities;
import hotelmaster.database.utilities.RoomTypeUtilities;
import hotelmaster.database.utilities.RoomUtilities;
import hotelmaster.database.utilities.SeasonUtilities;
import hotelmaster.database.utilities.StayExtraUtilities;
import hotelmaster.database.utilities.StayTypeUtilities;
/**
 * Factory interface that get the methods to manage the data stored in a source.
 */
public interface DataFactory {
    /**
     * Basic operations on price describers (insert/modify price/delete).
     * @return the {@link PriceUtilities} 
     */
    PriceUtilities getPrices();
    /**
     * Basic operations on room structure (Insert/modify properties).
     * @return The {@link Rooms}
     */
    Rooms getRooms();
    /**
     * Get the utilities for reservations.
     * @return The {@link BasicReservationUtilities} that allow to read all the reservations.
     */
    BasicReservationUtilities getReservations();
    /**
     * Get the utilities for the room extras.
     * @return the {@link RoomExtraUtilities} that allow to read all the available room extras
     */
    RoomExtraUtilities getRoomExtraUtilities();
    /**
     * Get the utilities for the room types.
     * @return the {@link RoomTypeUtilities} that allow to read all the available room types.
     */
    RoomTypeUtilities getRoomTypeUtilities();
    /**
     * Get the utilities for the stay types.
     * @return the {@link StayTypeUtilities} that allow to read all the available stay types.
     */
    StayTypeUtilities getStayTypeUtilities();
    /**
     * Get the utilities for the stay extras.
     * @return The {@link StayExtraUtilities} that allow to read all the available stay extras
     */
    StayExtraUtilities getStayExtraUtilities();
    /**
     * Get the utilities for the seasons.
     * @return The {@link SeasonUtilities} that allow to read all the available seasons
     */
    SeasonUtilities getSeasonUtilities();
    /**
     * Get the utilities for the documents.
     * @return The {@link DocumentUtilities} that allow to read all the available document types
     */
    DocumentUtilities getDocumentUtilities();
    /**
     * Get the utilities for person prices.
     * @return The {@link PersonPriceUtilities} that allow to read all the available person prices
     */
    PersonPriceUtilities getPersonPriceUtilities();
    /**
     * Get all the utilities for rooms.
     * @return The {@link RoomUtilities} that allow to read all the available rooms.
     */
    RoomUtilities getRoomUtilities();
    /**
     * Modifiable reservation management.
     * @return The {@link ModifiableReservation} that allow to modify a reservation.
     */
    ModifiableReservation getModifiableReservation();
    /**
     * Unmodifiable reservation management (insert/confirm/close).
     * @return The {@link BasicReservationUtilities}
     */
    Reservation getReservation();

}