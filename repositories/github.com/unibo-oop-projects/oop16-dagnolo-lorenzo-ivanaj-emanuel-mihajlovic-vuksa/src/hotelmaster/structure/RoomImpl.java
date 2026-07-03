package hotelmaster.structure;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.utility.time.FixedPeriod;

/**
 * An implementation of the Room interface that extends OccupationBased in order
 * to reuse the code for Occupation management, which is shared by Stay and Room
 * implementations.
 */
public class RoomImpl implements ModifiableRoom {

    private final RoomID id;
    private RoomTypePriceDescriber type;
    private final Set<RoomExtraPriceDescriber> extras;

    RoomImpl(final int floor, final int numberOnFloor) {
        this.id = RoomID.create(floor, numberOnFloor);
        this.extras = new HashSet<>();
    }

    @Override
    public RoomID getID() {
        return id;
    }

    @Override
    public RoomTypePriceDescriber getType() {
        return type;
    }

    @Override
    public Set<RoomExtraPriceDescriber> getExtrasView() {
        return ImmutableSet.copyOf(extras);
    }

    @Override
    public void setType(final RoomTypePriceDescriber type) {
        this.type = type;
    }

    @Override
    public Set<RoomExtraPriceDescriber> getExtras() {
        return this.extras;
    }

    @Override
    public boolean hasOccupations() {
        return Hotel.instance().getStayView().stream().anyMatch(
                stay -> stay.getOccupationsView().stream().anyMatch(occupation -> occupation.getRoom().equals(this)));
    }

    @Override
    public boolean isOccupiedDuring(final FixedPeriod dates) {
        return Hotel.instance().getStayView().stream().filter(stay -> stay.getDates().overlaps(dates))
                .map(stay -> stay.getOccupationsView().stream())
                .anyMatch(innerStream -> innerStream.anyMatch(occupation -> occupation.getRoom().equals(this)));
    }

    @Override
    public int compareTo(final Room o) {
        return this.id.compareTo(o.getID());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (!(obj instanceof RoomImpl)) {
            return false;
        }
        final RoomImpl other = (RoomImpl) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
