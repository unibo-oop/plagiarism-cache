package hotelmaster.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import hotelmaster.exceptions.MissingEntityException;
import hotelmaster.exceptions.RoomRemovalException;
import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.pricing.PriceDescriber;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.pricing.SeasonPriceDescriber;
import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;

/**
 * The implementation of HotelManager.
 */
public class HotelManagerImpl implements HotelManager {

    // map which contains the logic for whether a price can be removed
    private static final Map<Class<? extends PriceDescriber>, Function<PriceDescriber, Boolean>> PRICE_IN_USE = new HashMap<>();
    // map which contains the logic for removing a price
    private static final Map<Class<? extends PriceDescriber>, Consumer<PriceDescriber>> PRICE_REMOVAL = new HashMap<>();

    static {
        // loading is done in a static block in order to avoid race conditions
        // and synchronized blocks
        PRICE_IN_USE.put(RoomExtraPriceDescriber.class, roomExtra -> Hotel.instance().getRoomView().stream()
                .anyMatch(room -> (room.getExtrasView().contains(roomExtra) && !room.hasOccupations())));
        PRICE_IN_USE.put(RoomTypePriceDescriber.class,
                roomType -> Hotel.instance().getRoomView().stream().anyMatch(room -> room.getType().equals(roomType)));
        PRICE_IN_USE.put(StayExtraPriceDescriber.class, stayExtra -> Hotel.instance().getStayView().stream()
                .anyMatch(stay -> stay.getExtrasView().contains(stayExtra)));
        PRICE_IN_USE.put(StayTypePriceDescriber.class,
                stayType -> Hotel.instance().getStayView().stream().anyMatch(stay -> stay.getType().equals(stayType)));
        PRICE_IN_USE.put(PersonPriceDescriber.class, person -> Hotel.instance().getStayView().stream().anyMatch(
                stay -> stay.getOccupationsView().stream().anyMatch(occ -> occ.getPeopleView().containsKey(person))));
        PRICE_IN_USE.put(SeasonPriceDescriber.class, season -> Hotel.instance().getStayView().stream()
                .anyMatch(stay -> stay.getDates().overlaps(((SeasonPriceDescriber) season).getPeriod())));

        PRICE_REMOVAL.put(RoomExtraPriceDescriber.class, (roomExtra) -> {
            for (final Room room : Hotel.instance().getRoomView().stream()
                    .filter(room -> room.getExtrasView().contains(roomExtra)).collect(Collectors.toList())) {
                ModifiableHotel.instance().getRooms().removeExtra(room, (RoomExtraPriceDescriber) roomExtra);
            }
            ModifiableHotel.instance().getPriceManager().remove(roomExtra);
        });
        PRICE_REMOVAL.put(RoomTypePriceDescriber.class,
                roomType -> ModifiableHotel.instance().getPriceManager().remove(roomType));
        PRICE_REMOVAL.put(StayExtraPriceDescriber.class,
                stayExtra -> ModifiableHotel.instance().getPriceManager().remove(stayExtra));
        PRICE_REMOVAL.put(StayTypePriceDescriber.class,
                stayType -> ModifiableHotel.instance().getPriceManager().remove(stayType));
        PRICE_REMOVAL.put(PersonPriceDescriber.class,
                person -> ModifiableHotel.instance().getPriceManager().remove(person));
        PRICE_REMOVAL.put(SeasonPriceDescriber.class,
                season -> ModifiableHotel.instance().getPriceManager().remove(season));
    }

    HotelManagerImpl() {
        /*
         * Nothing to do here
         */
    }

    @Override
    public <T extends PriceDescriber> boolean addPriceDescriber(final T priceDescriber) {
        // TODO: create price describer in data
        return ModifiableHotel.instance().getPriceManager().add(priceDescriber);
    }

    @Override
    public <T extends PriceDescriber> boolean setPriceDescriber(final T priceDescriber, final double value) {
        final Optional<T> foundPrice = ModifiableHotel.instance().getPriceManager().getOfInstance(priceDescriber)
                .stream().filter(price -> price.getClass().equals(priceDescriber.getClass())
                        && price.getDescription().equals(priceDescriber.getDescription()))
                .findAny();
        if (foundPrice.isPresent()) {
            ModifiableHotel.instance().getPriceManager().setPrice(priceDescriber, value);
        }
        return true;
    }

    @Override
    public <T extends PriceDescriber> boolean removePriceDescriber(final T priceDescriber) {
        if (PRICE_IN_USE.get(instanceToType(priceDescriber)).apply(priceDescriber)) {
            PRICE_REMOVAL.get(instanceToType(priceDescriber)).accept(priceDescriber);
            return true;
        }
        return false;
    }

    @Override
    public int addFloors(final int amount) {
        return ModifiableHotel.instance().addFloors(amount);
    }

    @Override
    public int removeFloor(final int floorToRemove) throws RoomRemovalException, IllegalArgumentException {
        if (floorToRemove < 0) {
            throw new IllegalArgumentException("The floor must be a positive integer or 0");
        }
        if (Hotel.instance().getRoomView().stream().filter(room -> room.getID().getFloor() == floorToRemove)
                .anyMatch(room -> room.hasOccupations())) {
            throw new RoomRemovalException("Cannot remove floor " + floorToRemove);
        }
        ModifiableHotel.instance().getRooms().removeIf(room -> room.getID().getFloor() == floorToRemove);
        return 0;
    }

    @Override
    public int removeFloors(final int amount) throws RoomRemovalException {
        int toRemove = amount;
        // gets the rooms on the last floor and checks if they have any stays
        while (toRemove > 0) {
            final int floorToRemove = Hotel.instance().getFloorView().size() - toRemove;
            if (Hotel.instance().getRoomView().stream()
                    .filter(room -> room.getID().getFloor() == floorToRemove && room.hasOccupations()).count() > 0) {
                throw new RoomRemovalException("Cannot remove floor " + floorToRemove + ": it has occupied rooms");
            }
            toRemove--;
        }
        return ModifiableHotel.instance().removeFloors(amount);
    }

    @Override
    public void addRooms(final int floor, final int amount, final RoomTemplate template)
            throws MissingEntityException, IllegalArgumentException {
        if (amount < 0 || floor < 0 || !template.getRoomType().isPresent()
                || Hotel.instance().getFloorView().size() < floor) {
            throw new IllegalArgumentException("Invalid argument for function HotelManager#addRooms");
        }
        if (template.getRoomExtras().stream().anyMatch(extra -> !Hotel.instance().hasPriceDescriber(extra))) {
            throw new MissingEntityException(RoomExtraPriceDescriber.class);
        }
        if (!Hotel.instance().hasPriceDescriber(template.getRoomType().get())) {
            throw new MissingEntityException(RoomTypePriceDescriber.class);
        }
        int amountOfRooms = amount;
        final Iterator<Integer> gaps = ModifiableHotel.instance().getFloorGaps(floor);
        /*
         * While there are rooms to add:
         * 1.   Checks if there are gaps between the rooms for each room to add.
         * 
         * 2.   a.  No gaps: the new room's number is equal to the current amount of rooms.
         *      b.  Gaps: the new room's number is retrieved from the iterator of gaps.
         * 
         * 3.   Creates the room from the RoomTemplate.
         * 
         * 4.   Adds the room to the hotel.
         */
        final List<ModifiableRoom> roomsToAdd = new ArrayList<>(amount);
        while (amountOfRooms > 0) {
            amountOfRooms--;
            final int roomNumber = gaps.hasNext() ? gaps.next()
                    : (int) Hotel.instance().getRoomView().stream().filter(room -> room.getID().getFloor() == floor)
                            .count();
            final ModifiableRoom newRoom = ModifiableRoom.create(floor, roomNumber);
            for (final RoomExtraPriceDescriber extra : template.getRoomExtras()) {
                newRoom.getExtras().add(extra);
            }
            newRoom.setType(template.getRoomType().get());
            roomsToAdd.add(newRoom);
        }
        ModifiableHotel.instance().getRooms().addAll(roomsToAdd);
    }

    @Override
    public void updateRoom(final Room room, final RoomTemplate template) throws MissingEntityException {
        if (template.getRoomType().isPresent() && !Hotel.instance().getPriceView(RoomTypePriceDescriber.class)
                .contains(template.getRoomType().get())) {
            throw new MissingEntityException(RoomTypePriceDescriber.class);
        }
        if (!template.getRoomExtras().stream()
                .allMatch(extra -> Hotel.instance().getPriceView(RoomTypePriceDescriber.class).contains(extra))) {
            throw new MissingEntityException(RoomExtraPriceDescriber.class);
        }
        if (!Hotel.instance().getRoomView().contains(room)) {
            throw new MissingEntityException(Room.class);
        }
        if (template.getRoomType().isPresent()) {
            ModifiableHotel.instance().getRooms().setType(room, template.getRoomType().get());
        }
        ModifiableHotel.instance().getRooms().clearExtras(room);
        for (final RoomExtraPriceDescriber extra : template.getRoomExtras()) {
            ModifiableHotel.instance().getRooms().addExtra(room, extra);
        }
    }

    @Override
    public void removeRoom(final Room room) throws RoomRemovalException, IllegalArgumentException {
        if (!Hotel.instance().getRoomView().contains(room)) {
            throw new IllegalArgumentException("The room does not exist in the hotel");
        }
        if (room.hasOccupations()) {
            throw new RoomRemovalException("The room has stays associated to it");
        }
        ModifiableHotel.instance().getRooms().remove(room);
    }

    @SuppressWarnings("unchecked")
    private <T extends PriceDescriber> Class<T> instanceToType(final T priceDescriber) {
        return (Class<T>) priceDescriber.getClass();
    }
}
