package hotelmaster.reservations;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import hotelmaster.exceptions.MissingEntityException;
import hotelmaster.exceptions.OccupiedRoomException;
import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.structure.Hotel;
import hotelmaster.structure.ModifiableHotel;
import hotelmaster.structure.ModifiableRoom;
import hotelmaster.structure.Room;
import hotelmaster.utility.time.FixedPeriod;

/**
 * An implementation of StayBuilder which creates the stay in the hotel while
 * checking whether entities such as Price Describers exist/are active in the
 * hotel or not. Exposes protected methods to set its fields for its subclasses.
 */
public class StayBuilderImpl implements StayBuilder {

    private StayTypePriceDescriber type;
    private final Set<StayExtraPriceDescriber> extras;
    private final Map<ModifiableRoom, Map<PersonPriceDescriber, Integer>> occupations;
    private Client client;
    private FixedPeriod dates;
    private StayState state;
    private boolean firstStep;
    private boolean finalized;

    StayBuilderImpl() {
        this.extras = new HashSet<>();
        this.occupations = new HashMap<>();
        this.firstStep = true;
        this.finalized = false;
    }

    @Override
    public StayBuilder addRoom(final Room room, final Map<PersonPriceDescriber, Integer> people)
            throws MissingEntityException, IllegalStateException, OccupiedRoomException {
        secondStepCheck();
        if (Hotel.instance().getStayView().stream().filter(stay -> stay.getDates().overlaps(this.dates)).anyMatch(
                stay -> stay.getOccupationsView().stream().anyMatch(occupation -> occupation.getRoom().equals(room)))) {
            throw new OccupiedRoomException("The room is occupied during this stay's dates");
        }
        for (

        final PersonPriceDescriber personPrice : people.keySet()) {
            if (!Hotel.instance().hasPriceDescriber(personPrice)) {
                throw new MissingEntityException(PersonPriceDescriber.class);
            }
        }
        if (room.getType().getMaxPeople() < people.values().stream().mapToInt((e) -> e).sum()) {
            throw new IllegalArgumentException("The room cannot hold the given amount of people");
        }
        this.innerAddRoom(room, people);
        return this;
    }

    @Override
    public StayBuilder setClient(final Client client) throws IllegalStateException, IllegalArgumentException {
        firstStepCheck();
        if (Hotel.instance().getClientView().contains(client)) {
            throw new IllegalArgumentException("The given client already has a stay");
        }
        this.innerSetClient(client);
        return this;
    }

    @Override
    public StayBuilder setDates(final FixedPeriod dates) throws IllegalStateException {
        firstStepCheck();
        this.innerSetDates(dates);
        return this;
    }

    @Override
    public Optional<FixedPeriod> getDates() {
        return this.dates == null ? Optional.empty() : Optional.of(this.dates);
    }

    @Override
    public StayBuilder setType(final StayTypePriceDescriber stayType)
            throws MissingEntityException, IllegalStateException {
        firstStepCheck();
        if (!Hotel.instance().hasPriceDescriber(stayType)) {
            throw new MissingEntityException(StayTypePriceDescriber.class);
        }
        this.innerSetType(stayType);
        return this;
    }

    @Override
    public StayBuilder addExtra(final StayExtraPriceDescriber stayExtra)
            throws MissingEntityException, IllegalStateException {
        firstStepCheck();
        if (!Hotel.instance().hasPriceDescriber(stayExtra)) {
            throw new MissingEntityException(StayExtraPriceDescriber.class);
        }
        this.innerAddExtra(stayExtra);
        return this;
    }

    @Override
    public void complete() throws IllegalStateException {
        finalizedCheck();
        final ModifiableStay stay = ModifiableStay.create();
        /*
         * Translates all of the key-value pairs in the map "occupations" to
         * actual occupations and adds them to the stay and to the relevant room
         */
        stay.setClient(this.client);
        stay.setDates(this.dates);
        stay.setType(this.type);
        for (final ModifiableRoom room : occupations.keySet()) {
            final ModifiableOccupation occupation = ModifiableOccupation.create(stay, room, occupations.get(room));
            stay.getOccupations().add(occupation);
        }
        for (final StayExtraPriceDescriber extra : extras) {
            stay.getExtras().add(extra);
        }
        if (this.state != null) {
            stay.setState(this.state);
        } else {
            stay.setState(this.dates.getBeginning().isAfter(LocalDate.now()) ? StayState.INACTIVE : StayState.ACTIVE);
        }
        synchronized (ModifiableHotel.instance().getStays()) {
            ModifiableHotel.instance().getStays().add(stay);
        }
        ModifiableHotel.instance().getClients().add(this.client);
        finalized = true;
    }

    /**
     * Throws an {@link IllegalStateException} if the builder isn't in the first
     * step.
     * 
     * @throws IllegalStateException
     */
    private void firstStepCheck() throws IllegalStateException {
        finalizedCheck();
        if (!this.firstStep) {
            throw new IllegalStateException("The builder is not in the first step anymore");
        }
    }

    /**
     * Throws an {@link IllegalStateException} if the builder isn't in the
     * second step.
     * 
     * @throws IllegalStateException
     *             the builder isn't in the second step
     */
    private void secondStepCheck() throws IllegalStateException {
        finalizedCheck();
        firstStep = occupations.isEmpty() || client == null || dates == null || type == null;
        if (this.firstStep) {
            throw new IllegalStateException("The builder is not in the second step yet");
        }
    }

    /**
     * Throws an {@link IllegalStateException} if the builder is finalized.
     * 
     * @throws IllegalStateException
     */
    private void finalizedCheck() throws IllegalStateException {
        if (this.finalized) {
            throw new IllegalStateException("The builder is finalized and may not be used anymore");
        }
    }

    /**
     * Adds the room, or sets the people to the given value if the room already
     * exists.
     * 
     * @param room
     *            the room
     * @param people
     *            the people
     */
    protected void innerAddRoom(final Room room, final Map<PersonPriceDescriber, Integer> people) {
        this.occupations.put((ModifiableRoom) room, people);
    }

    /**
     * Sets the client.
     * 
     * @param client
     *            the client
     */
    protected void innerSetClient(final Client client) {
        this.client = client;
    }

    /**
     * Sets the dates.
     * 
     * @param dates
     *            the dates
     */
    protected void innerSetDates(final FixedPeriod dates) {
        this.dates = dates;
    }

    /**
     * Sets the type of the stay.
     * 
     * @param stayType
     *            the type of the stay
     */
    protected void innerSetType(final StayTypePriceDescriber stayType) {
        this.type = stayType;
    }

    /**
     * Sets the state of the stay.
     * 
     * @param state
     *            the state of the stay
     */
    protected void innerSetStay(final StayState state) {
        this.state = state;
    }

    /**
     * Adds an extra to the stay.
     * 
     * @param stayExtra
     *            the extra
     */
    protected void innerAddExtra(final StayExtraPriceDescriber stayExtra) {
        this.extras.add(stayExtra);
    }
}
