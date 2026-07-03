package hotelmaster.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.structure.ModifiableHotel;
import hotelmaster.structure.ModifiableRoom;
import hotelmaster.structure.Room;
import hotelmaster.structure.RoomID;

/**
 * Tests for {@link Room}, {@link ModifiableRoom} and {@link RoomID}.
 */
public class RoomTest {

    /**
     * Instancing tests.
     */
    @org.junit.Test
    public void id() {
        final ModifiableRoom room = ModifiableRoom.create(0, 0);
        room.setType(new RoomTypePriceDescriber("some name", 0, 0, 1));
        assertEquals("2 rooms with same ID should be equal", room, ModifiableRoom.create(0, 0));
        assertEquals("2 rooms with same ID should be equal", room.getID(), ModifiableRoom.create(0, 0).getID());
        assertEquals("Room and ModifiableRoom with same ID should be equal", ModifiableRoom.create(0, 1),
                (Room) ModifiableRoom.create(0, 1));
        ModifiableHotel.instance().getRooms().add(room);
    }

    /**
     * Tests on {@link RoomExtraPriceDescriber} and
     * {@link RoomTypePriceDescriber}.
     */
    @org.junit.Test
    public void properties() {
        final ModifiableRoom room = ModifiableRoom.create(0, 0);
        final RoomTypePriceDescriber type = new RoomTypePriceDescriber("name", 0, 0, 1);
        final Set<RoomExtraPriceDescriber> extras = new HashSet<>();
        final RoomExtraPriceDescriber first = new RoomExtraPriceDescriber("first", 1);
        extras.add(first);
        extras.add(new RoomExtraPriceDescriber("second", 2));
        extras.add(new RoomExtraPriceDescriber("third", 3));
        room.setType(type);
        room.getExtras().addAll(extras);
        assertEquals("The room should have the given type", type, room.getType());
        assertTrue("The room should have all the given extras", room.getExtras().containsAll(extras));
        assertFalse("Extras should all be distinct", extras.add(first));
    }
}
