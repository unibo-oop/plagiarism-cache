package hotelmaster.reservations;

import java.time.LocalDate;
import java.util.Map;

import hotelmaster.exceptions.MissingEntityException;
import hotelmaster.exceptions.OccupiedRoomException;
import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.structure.Hotel;
import hotelmaster.structure.ModifiableHotel;
import hotelmaster.structure.Room;
import hotelmaster.utility.time.FixedPeriod;

/**
 * A {@link StayManager} which allows modifying an inactive {@link Stay}'s state
 * and parameters.
 */
public class InactiveStayManager extends StayManager {

    InactiveStayManager(final Stay stay) throws IllegalArgumentException {
        super(stay, StayState.INACTIVE);
    }

    /**
     * Activates the {@link Stay}. If the beginning date is not today's date, it
     * is set to today's date. This operation invalidates this object, since the
     * {@link Stay} becomes active.
     * 
     * @throws IllegalStateException
     *             the {@link Stay} relevant to this {@link InactiveStayManager}
     *             is no longer inactive
     */
    public void checkin() throws IllegalStateException {
        this.checkState();
        ModifiableHotel.instance().getStays().activate(this.getStay());
    }

    /**
     * Cancels the {@link Stay}, removing all its relations to the
     * {@link Hotel}.
     * 
     * @throws IllegalStateException
     *             the stay relevant to this {@link InactiveStayManager} is no
     *             longer inactive
     */
    public void cancel() throws IllegalStateException {
        this.checkState();
        this.closeStay();
    }

    /**
     * Modifies the end date of a {@link Stay}. If delayed, there must be no
     * conflicts with already-existing {@link Stay}s for rooms.
     * 
     * @param beginningDate
     *            the new beginning-date for the stay
     * @throws IllegalArgumentException
     *             the date is before LocalDate.now()
     * @throws OccupiedRoomException
     *             one or more rooms are occupied by another stay in the period
     *             of time between the old end date and the new end date of this
     *             stay
     * @throws IllegalStateException
     *             the stay relevant to this StayManager is no longer inactive
     */
    public void setBeginning(final LocalDate beginningDate)
            throws IllegalArgumentException, OccupiedRoomException, IllegalStateException {
        this.checkState();
        if (beginningDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The beginning date can't be before today");
        }
        this.checkConflicts(FixedPeriod.of(beginningDate, this.getStay().getDates().getEnd()));
        ModifiableHotel.instance().getStays().setDates(this.getStay(),
                FixedPeriod.of(beginningDate, this.getStay().getDates().getEnd()));
    }

    /**
     * Adds a {@link Room} to the {@link Stay}'s {@link Room}s. If the
     * {@link Room} was already in this {@link Stay}'s rooms, the amount of
     * people is set to the given amount.
     * 
     * @param room
     *            the room to be added
     * @param people
     *            the people in the room
     * @return whether the room has been added or not
     * @throws MissingEntityException
     *             the room or a type of person is not in the hotel
     * @throws OccupiedRoomException
     *             the room is occupied during the stay's dates
     * @throws IllegalStateException
     *             the stay relevant to this StayManager is no longer inactive
     * @throws IllegalArgumentException
     *             the given map of people is invalid
     */
    public boolean addRoom(final Room room, final Map<PersonPriceDescriber, Integer> people)
            throws MissingEntityException, OccupiedRoomException, IllegalStateException, IllegalArgumentException {
        this.checkState();
        // TODO: review
        return false;
    }

    /**
     * Removes a {@link Room} from the {@link Stay}'s {@link Room}.
     * 
     * @param room
     *            the room to be removed
     * @return whether the room has been removed or not
     * @throws MissingEntityException
     *             the room is not in the hotel
     * @throws IllegalStateException
     *             the stay relevant to this StayManager is no longer inactive
     */
    public boolean removeRoom(final Room room) throws MissingEntityException, IllegalStateException {
        this.checkState();
        if (!Hotel.instance().getRoomView().contains(room)) {
            throw new MissingEntityException(Room.class);
        }
        return this.getStay().getOccupationsView().removeIf(occupation -> occupation.getRoom().equals(room));
    }

    /**
     * Adds a {@link StayExtraPriceDescriber} to this {@link Stay}.
     * 
     * @param stayExtra
     *            the extra to be added
     * @return whether the extra has been added or not
     * @throws MissingEntityException
     *             the extra is not in the hotel
     * @throws IllegalStateException
     *             the stay relevant to this StayManager is no longer inactive
     */
    public boolean addExtra(final StayExtraPriceDescriber stayExtra)
            throws MissingEntityException, IllegalStateException {
        this.checkState();
        if (!Hotel.instance().getPriceView(RoomExtraPriceDescriber.class).contains(stayExtra)) {
            throw new MissingEntityException(RoomExtraPriceDescriber.class);
        }
        return ModifiableHotel.instance().getStays().addExtra(this.getStay(), stayExtra);
    }

    /**
     * Removes an extra from this stay.
     * 
     * @param stayExtra
     *            the extra to be removed
     * @return whether the extra has been removed or not
     * @throws MissingEntityException
     *             the extra is not in the hotel
     * @throws IllegalStateException
     *             the stay relevant to this StayManager is no longer inactive
     */
    public boolean removeExtra(final StayExtraPriceDescriber stayExtra)
            throws MissingEntityException, IllegalStateException {
        this.checkState();
        if (!Hotel.instance().getPriceView(RoomExtraPriceDescriber.class).contains(stayExtra)) {
            throw new MissingEntityException(RoomExtraPriceDescriber.class);
        }
        return ModifiableHotel.instance().getStays().removeExtra(this.getStay(), stayExtra);
    }

    /**
     * Sets this stay to a certain type.
     * 
     * @param stayType
     *            the type to be set
     * @throws MissingEntityException
     *             the type is not in the hotel
     * @throws IllegalStateException
     *             the stay relevant to this StayManager is no longer inactive
     */
    public void setType(final StayTypePriceDescriber stayType) throws MissingEntityException, IllegalStateException {
        this.checkState();
        if (!Hotel.instance().getPriceView(RoomExtraPriceDescriber.class).contains(stayType)) {
            throw new MissingEntityException(RoomExtraPriceDescriber.class);
        }
        ModifiableHotel.instance().getStays().setType(this.getStay(), stayType);
    }
}
