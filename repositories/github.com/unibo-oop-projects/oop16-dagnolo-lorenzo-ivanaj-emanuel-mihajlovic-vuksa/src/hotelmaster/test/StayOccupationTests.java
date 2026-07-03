package hotelmaster.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.reservations.ModifiableOccupation;
import hotelmaster.reservations.ModifiableStay;
import hotelmaster.reservations.StayState;
import hotelmaster.structure.Hotel;
import hotelmaster.structure.ModifiableHotel;
import hotelmaster.structure.ModifiableRoom;
import hotelmaster.structure.Room;
import hotelmaster.utility.time.FixedPeriod;

/**
 * Tests related to stays and occupations.
 */
public class StayOccupationTests {
    // CHECKSTYLE:OFF: checkstyle:magicnumber

    @org.junit.Test
    public void active() {
        final ModifiableStay stay = ModifiableStay.create();
        assertEquals(stay.getState(), StayState.INACTIVE);
        stay.setState(StayState.ACTIVE);
        assertEquals(stay.getState(), StayState.ACTIVE);
    }

    @org.junit.Test
    public void linkedToRoom() {
        /*
        putPriceDescribers();
        putRooms(2, 12);
        final ModifiableStay stay = ModifiableStay.create();
        stay.setDates(FixedPeriod.of(LocalDate.ofYearDay(1, 1), LocalDate.ofYearDay(1, 5)));
        assertTrue(stay.getOccupations().isEmpty());
        final Map<PersonPriceDescriber, Integer> people = new HashMap<>();
        people.put(Hotel.instance().getPriceView(PersonPriceDescriber.class).iterator().next(), 1);
        final Room room = ModifiableHotel.instance().getRooms().iterator().next();
        final ModifiableOccupation occ = ModifiableOccupation.create(stay, room, people);
        stay.getOccupations().add(occ);
        room.getOccupations().add(occ);
        assertEquals(stay, room.getOccupations().iterator().next().getStay());
        assertEquals(room, stay.getOccupations().iterator().next().getRoom());
        room.setType(Hotel.instance().getPriceView(RoomTypePriceDescriber.class).iterator().next());
        assertEquals(stay, room.getOccupations().iterator().next().getStay());
        assertEquals(room, stay.getOccupations().iterator().next().getRoom());
        assertEquals(room.getType(), stay.getOccupations().iterator().next().getRoom().getType());
        */
    }

    private void putPriceDescribers() {
        /*
        ModifiableHotel.instance().getPriceManager().add(new StayTypePriceDescriber("standard", 1.0));
        ModifiableHotel.instance().getPriceManager().add(new StayTypePriceDescriber("all-inclusive", 1.2));
        ModifiableHotel.instance().getPriceManager().add(new StayExtraPriceDescriber("pool", 20, true));
        ModifiableHotel.instance().getPriceManager().add(new StayExtraPriceDescriber("gym", 20, true));
        ModifiableHotel.instance().getPriceManager().add(new StayExtraPriceDescriber("parking", 15, false));
        */
        ModifiableHotel.instance().getPriceManager().add(new PersonPriceDescriber("adult", 1.0));
        ModifiableHotel.instance().getPriceManager().add(new PersonPriceDescriber("child", 0.7));
        ModifiableHotel.instance().getPriceManager().add(new RoomTypePriceDescriber("twinbed", 1.2, 0.5, 2));
        /*
        ModifiableHotel.instance().getPriceManager().add(new RoomTypePriceDescriber("single", 1.0, 0.5, 1));
        ModifiableHotel.instance().getPriceManager().add(new RoomTypePriceDescriber("4-people", 1.3, 0.5, 4));
        ModifiableHotel.instance().getPriceManager().add(new RoomExtraPriceDescriber("ac", 18));
        ModifiableHotel.instance().getPriceManager().add(new RoomExtraPriceDescriber("fridge", 15));
        ModifiableHotel.instance().getPriceManager().add(new RoomExtraPriceDescriber("balcony", 20));
        */
    }

    private void putRooms(final int floors, final int roomsPerFloor) {
        int i = 0;
        int j = 0;
        ModifiableHotel.instance().addFloors(floors);
        while (i < floors) {
            while (j < roomsPerFloor) {
                ModifiableHotel.instance().getRooms().add(ModifiableRoom.create(i, j));
                j++;
            }
            i++;
        }
    }
    // CHECKSTYLE:ON: checkstyle:magicnumber
}
