package hotelmaster.reservations;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.structure.ModifiableRoom;

/**
 * An implementation of Occupation.
 */
public class OccupationImpl implements ModifiableOccupation {

    private final ModifiableStay stay;
    private final ModifiableRoom room;
    private Map<PersonPriceDescriber, Integer> people;

    OccupationImpl(final ModifiableStay stay, final ModifiableRoom room,
            final Map<PersonPriceDescriber, Integer> people) {
        if (stay == null || room == null || people == null || people.values().stream().anyMatch(val -> val < 1)) {
            throw new IllegalArgumentException("Invalid arguments for Occupation");
        }
        this.stay = stay;
        this.room = room;
        this.people = new HashMap<>(people);
    }

    @Override
    public ModifiableStay getStay() {
        return this.stay;
    }

    @Override
    public ModifiableRoom getRoom() {
        return this.room;
    }

    @Override
    public Map<PersonPriceDescriber, Integer> getPeopleView() {
        return ImmutableMap.copyOf(people);
    }

    @Override
    public int compareTo(final Occupation o) {
        if (this.stay.getDates() == null || o.getStay().getDates() == null) {
            return 0;
        }
        return this.stay.getDates().compareTo(o.getStay().getDates());
    }

    @Override
    public void setPeople(final Map<PersonPriceDescriber, Integer> people) {
        this.people = new HashMap<>(people);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((room == null) ? 0 : room.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof OccupationImpl)) {
            return false;
        }
        final OccupationImpl other = (OccupationImpl) obj;
        if (room == null) {
            if (other.room != null) {
                return false;
            }
        } else if (!room.equals(other.room)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Room ").append(this.room.getID().getFullID()).append(": {");
        for (final PersonPriceDescriber person : this.people.keySet()) {
            builder.append('[').append(person.getDescription()).append(" (").append(this.people.get(person))
                    .append(")] ");
        }
        return builder.append("}").toString();
    }

}
