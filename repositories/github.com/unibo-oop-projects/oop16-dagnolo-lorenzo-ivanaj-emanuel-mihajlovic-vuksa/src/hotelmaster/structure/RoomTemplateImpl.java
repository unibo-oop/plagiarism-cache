package hotelmaster.structure;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import hotelmaster.exceptions.MissingEntityException;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;

/**
 * A basic implementation of the RoomTemplate interface.
 */
public class RoomTemplateImpl implements RoomTemplate {

    private RoomTypePriceDescriber type;
    private final Set<RoomExtraPriceDescriber> extras;

    RoomTemplateImpl() {
        this.type = null;
        this.extras = new HashSet<>();
    }

    @Override
    public RoomTemplate setRoomType(final RoomTypePriceDescriber roomType) throws MissingEntityException {
        if (!Hotel.instance().hasPriceDescriber(roomType)) {
            throw new MissingEntityException(RoomTypePriceDescriber.class);
        }
        this.type = roomType;
        return this;
    }

    @Override
    public RoomTemplate addRoomExtra(final RoomExtraPriceDescriber roomExtra)
            throws MissingEntityException, IllegalArgumentException {
        if (!Hotel.instance().hasPriceDescriber(roomExtra)) {
            throw new MissingEntityException(RoomExtraPriceDescriber.class);
        }
        if (!this.extras.add(roomExtra)) {
            throw new IllegalArgumentException("The RoomExtra has already been added");
        }
        return this;
    }

    @Override
    public Optional<RoomTypePriceDescriber> getRoomType() {
        if (type == null) {
            return Optional.empty();
        } else {
            return Optional.of(type);
        }
    }

    @Override
    public Set<RoomExtraPriceDescriber> getRoomExtras() {
        return Collections.unmodifiableSet(extras);
    }
}
