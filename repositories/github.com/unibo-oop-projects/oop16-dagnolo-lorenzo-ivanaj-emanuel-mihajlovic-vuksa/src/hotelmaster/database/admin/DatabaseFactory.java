package hotelmaster.database.admin;

import hotelmaster.database.secretary.ModifiableReservation;
import hotelmaster.database.secretary.ModifiableReservationImpl;
import hotelmaster.database.secretary.Reservation;
import hotelmaster.database.secretary.ReservationImpl;
import hotelmaster.database.utilities.BasicReservationUtilities;
import hotelmaster.database.utilities.DocumentUtilities;
import hotelmaster.database.utilities.PersonPriceUtilities;
import hotelmaster.database.utilities.ReservationUtilities;
import hotelmaster.database.utilities.RoomExtraUtilities;
import hotelmaster.database.utilities.RoomTypeUtilities;
import hotelmaster.database.utilities.RoomUtilities;
import hotelmaster.database.utilities.SeasonUtilities;
import hotelmaster.database.utilities.StayExtraUtilities;
import hotelmaster.database.utilities.StayTypeUtilities;
/**
 * Factory for classes that interact with the database to store the data.
 */
public class DatabaseFactory implements DataFactory {

    private final PriceUtilities prices;
    private final Rooms rooms;
    private final BasicReservationUtilities reservations;
    private final RoomExtraUtilities roomExtraUtilities;
    private final RoomTypeUtilities roomTypeUtilities;
    private final StayTypeUtilities stayTypeUtilities;
    private final StayExtraUtilities stayExtraUtilities;
    private final SeasonUtilities seasonUtilities;
    private final DocumentUtilities documentUtilities;
    private final PersonPriceUtilities personPriceUtilities;
    private final RoomUtilities roomUtilities;
    private final ModifiableReservation modifiableReservation;
    private final Reservation reservation;
    /**
     * 
     */
    public DatabaseFactory() {
        this.prices = new PriceUtilitiesImpl();
        this.rooms = new RoomsImpl();
        this.reservations = new ReservationUtilities();
        this.roomExtraUtilities = new RoomExtraUtilities();
        this.roomTypeUtilities = new RoomTypeUtilities();
        this.stayTypeUtilities = new StayTypeUtilities();
        this.stayExtraUtilities = new StayExtraUtilities();
        this.seasonUtilities = new SeasonUtilities();
        this.documentUtilities = new DocumentUtilities();
        this.personPriceUtilities = new PersonPriceUtilities();
        this.roomUtilities = new RoomUtilities();
        this.modifiableReservation = new ModifiableReservationImpl();
        this.reservation = new ReservationImpl();
    }

    @Override
    public PriceUtilities getPrices() {
        return prices;
    }

    @Override
    public Rooms getRooms() {
        return rooms;
    }

    @Override
    public BasicReservationUtilities getReservations() {
        return reservations;
    }

    @Override
    public RoomExtraUtilities getRoomExtraUtilities() {
        return roomExtraUtilities;
    }

    @Override
    public RoomTypeUtilities getRoomTypeUtilities() {
        return roomTypeUtilities;
    }

    @Override
    public StayTypeUtilities getStayTypeUtilities() {
        return stayTypeUtilities;
    }

    @Override
    public StayExtraUtilities getStayExtraUtilities() {
        return stayExtraUtilities;
    }

    @Override
    public SeasonUtilities getSeasonUtilities() {
        return seasonUtilities;
    }

    @Override
    public DocumentUtilities getDocumentUtilities() {
        return documentUtilities;
    }

    @Override
    public PersonPriceUtilities getPersonPriceUtilities() {
        return personPriceUtilities;
    }

    @Override
    public RoomUtilities getRoomUtilities() {
        return roomUtilities;
    }

    @Override
    public ModifiableReservation getModifiableReservation() {
        return modifiableReservation;
    }

    @Override
    public Reservation getReservation() {
        return reservation;
    }


}
