package hotelmaster.structure;

import java.util.Collection;
import java.util.Iterator;

import hotelmaster.exceptions.RoomRemovalException;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.utility.collections.Trigger;
import hotelmaster.utility.collections.TriggerManager;
import hotelmaster.utility.collections.TriggeringOperation;
import hotelmaster.utility.collections.TriggeringSet;

/**
 * Basic implementation of {@link RoomCollection}.
 */
public class RoomCollectionImpl implements RoomCollection {

    private final TriggeringSet<Room> rooms;
    private final TriggerManager<Room> ownTriggers;

    // TODO: find a way to accept multiple parameters!

    RoomCollectionImpl() {
        this.rooms = TriggeringSet.from(ModifiableHotel.instance().getData().getRoomUtilities().getAll());
        this.ownTriggers = TriggerManager.create();
        this.rooms.addTrigger(Trigger.create(TriggeringOperation.ADD, room -> {
            try {
                ModifiableHotel.instance().getData().getRooms().createRoom(room);
            } catch (RoomRemovalException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }));
        this.rooms.addTrigger(Trigger.create(TriggeringOperation.REMOVE, room -> {
            try {
                ModifiableHotel.instance().getData().getRooms().removeRoom(room);
            } catch (RoomRemovalException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }));

    }

    @Override
    public boolean add(final Room e) {
        return this.rooms.add(e);
    }

    @Override
    public boolean addAll(final Collection<? extends Room> c) {
        return this.rooms.addAll(c);
    }

    @Override
    public void clear() {
        this.rooms.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return this.rooms.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return this.rooms.containsAll(c);
    }

    @Override
    public boolean isEmpty() {
        return this.rooms.isEmpty();
    }

    @Override
    public Iterator<Room> iterator() {
        return this.rooms.iterator();
    }

    @Override
    public boolean remove(final Object o) {
        return this.rooms.remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return this.rooms.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return this.rooms.retainAll(c);
    }

    @Override
    public int size() {
        return this.rooms.size();
    }

    @Override
    public Object[] toArray() {
        return this.rooms.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return this.rooms.toArray(a);
    }

    @Override
    public void addTrigger(final Trigger<Room> trigger) throws IllegalArgumentException {
        this.rooms.addTrigger(trigger);
    }

    @Override
    public void setType(final Room room, final RoomTypePriceDescriber type) {
        ((ModifiableRoom) room).setType(type);

    }

    @Override
    public boolean addExtra(final Room room, final RoomExtraPriceDescriber extra) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeExtra(final Room room, final RoomExtraPriceDescriber extra) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clearExtras(final Room room) {
        // TODO Auto-generated method stub

    }

}
