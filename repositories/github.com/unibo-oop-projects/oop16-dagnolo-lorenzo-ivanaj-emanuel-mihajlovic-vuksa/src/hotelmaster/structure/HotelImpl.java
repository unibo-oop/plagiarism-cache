package hotelmaster.structure;

import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultimap;

import hotelmaster.database.admin.DataFactory;
import hotelmaster.database.admin.DatabaseFactory;
import hotelmaster.database.utilities.DocumentUtilities;
import hotelmaster.pricing.PriceCollection;
import hotelmaster.pricing.PriceDescriber;
import hotelmaster.reservations.Client;
import hotelmaster.reservations.DocumentType;
import hotelmaster.reservations.Stay;
import hotelmaster.reservations.StayCleanup;
import hotelmaster.reservations.StayCollection;
import hotelmaster.utility.collections.Trigger;
import hotelmaster.utility.collections.TriggeringOperation;

/**
 * The implementation of Hotel.
 */
public class HotelImpl implements ModifiableHotel {

    private static final ModifiableHotel SINGLETON = new HotelImpl();
    private PriceCollection priceManager;
    private final RoomCollection rooms;
    private final StayCollection stays;
    private Set<DocumentType> documents;

    private StayCleanup stayCleanup;
    private final Map<Integer, Integer> floors;
    private final TreeMultimap<Integer, Integer> floorGaps;

    private final DataFactory data;

    HotelImpl() {
        data = new DatabaseFactory();
        this.floors = new HashMap<>();
        this.floorGaps = TreeMultimap.create(Ordering.natural(), Ordering.natural());
        this.loadData();
    }

    private void loadData() {
        this.priceManager = PriceCollection.create();
        this.documents = new HashSet<>(new DocumentUtilities().getAll());
        this.data.getReservations().loadStays();
        this.triggerBinding();
    }

    private void triggerBinding() {
        this.rooms.addTrigger(Trigger.create(TriggeringOperation.REMOVE, new AddGaps()));
        this.rooms.addTrigger(Trigger.create(TriggeringOperation.ADD, new RemoveGaps()));
    }

    @Override
    public Map<Integer, Integer> getFloorView() {
        return Collections.unmodifiableMap(this.floors);
    }

    @Override
    public Set<DocumentType> getDocuments() {
        return ImmutableSet.copyOf(this.documents);
    }

    @Override
    public <T extends PriceDescriber> Set<T> getPriceView(final Class<T> priceType) {
        return Collections.unmodifiableSet(this.priceManager.get(priceType));
    }

    @Override
    public <T extends PriceDescriber> boolean hasPriceDescriber(final T price) {
        return this.priceManager.contains(price);
    }

    @Override
    public Set<Room> getRoomView() {
        return ImmutableSet.copyOf(this.rooms);
    }

    @Override
    public Set<Stay> getStayView() {
        return ImmutableSet.copyOf(this.stays);
    }

    @Override
    public Set<Client> getClientView() {
        return this.stays.stream().map(stay -> stay.getClient()).collect(Collectors.toSet());
    }

    @Override
    public int addFloors(final int amount) {
        int toRemove = amount;
        while (toRemove > 0) {
            this.floors.put(this.floors.size() + 1, 0);
            toRemove--;
        }
        return this.floors.size();
    }

    @Override
    public int removeFloors(final int amount) {
        int toRemove = amount;
        while (toRemove > 0) {
            final int floorToRemove = Collections.max(this.floors.keySet());
            this.floors.remove(floorToRemove);
            toRemove--;
        }
        return this.floors.size();
    }

    @Override
    public Map<Integer, Integer> getFloors() {
        return this.floors;
    }

    @Override
    public Iterator<Integer> getFloorGaps(final int floor) {
        return ImmutableSet.copyOf(floorGaps.get(floor)).iterator();
    }

    @Override
    public PriceCollection getPriceManager() {
        return this.priceManager;
    }

    @Override
    public RoomCollection getRooms() {
        return this.rooms;
    }

    @Override
    public StayCollection getStays() {
        return this.stays;
    }

    @Override
    public void activateStayCleanupTimer(final LocalTime callTime) {
        if (this.stayCleanup != null) {
            this.stayCleanup.abort();
        }
        this.stayCleanup = StayCleanup.create(callTime);
    }

    @Override
    public DataFactory getData() {
        return this.data;
    }

    /**
     * Returns the singleton instance of Hotel.
     * 
     * @return the singleton
     */
    protected static ModifiableHotel instance() {
        return SINGLETON;
    }

    private Optional<Room> highestRoomOnFloor(final int floor) {
        if (floor < 0) {
            throw new IllegalArgumentException("Floor cannot be negative");
        }
        if (this.rooms.stream().mapToInt(room -> room.getID().getFloor()).distinct().max().orElseGet(() -> 0) < floor) {
            return Optional.empty();
        }
        return rooms.stream().filter(room -> room.getID().getFloor() == floor)
                .max((room1, room2) -> room1.compareTo(room2));
    }

    private class AddGaps implements Consumer<Room> {
        /*
         * Adds a room to the gaps, if it was between rooms (if it
         * is the last room on the floor, there is no gap when it gets
         * removed)
         */
        private final TreeMultimap<Integer, Integer> innerFloorGaps = floorGaps;

        @Override
        public void accept(final Room t) {
            /*
             * 1. Finds the room "A" on t's floor with the highest
             * number-on-floor 2. Checks if t's number-on-floor is A's
             * number-on-floor 3. Adds the gap if A and t are not the same
             */
            final Optional<Room> highestRoom = highestRoomOnFloor(t.getID().getFloor());
            if (highestRoom.isPresent()
                    && highestRoom.get().getID().getNumberOnFloor() < t.getID().getNumberOnFloor()) {
                innerFloorGaps.get(t.getID().getFloor()).add(t.getID().getNumberOnFloor());
                floors.put(t.getID().getFloor(), highestRoom.get().getID().getNumberOnFloor());
            } else {
                floors.put(t.getID().getFloor(), 0);
            }
        }

    }

    private class RemoveGaps implements Consumer<Room> {
        /* Removes the room from the gaps, if it was between rooms */

        private final TreeMultimap<Integer, Integer> innerFloorGaps = floorGaps;

        @Override
        public void accept(final Room t) {
            innerFloorGaps.get(t.getID().getFloor()).remove(t.getID().getNumberOnFloor());
            final Optional<Room> highestRoom = highestRoomOnFloor(t.getID().getFloor());
            if (highestRoom.isPresent() && highestRoom.get().equals(t)) {
                floors.put(t.getID().getFloor(), t.getID().getNumberOnFloor());
            }
        }

    }

}
