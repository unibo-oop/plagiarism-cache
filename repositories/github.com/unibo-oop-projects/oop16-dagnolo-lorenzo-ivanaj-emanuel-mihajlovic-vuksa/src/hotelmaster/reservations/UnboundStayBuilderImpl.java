package hotelmaster.reservations;

import java.util.Map;

import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.structure.Room;
import hotelmaster.utility.time.FixedPeriod;

/**
 * An implementation of UnboundStayBuilder which relies on
 * {@link StayBuilderImpl}'s protected methods.
 */
public class UnboundStayBuilderImpl extends StayBuilderImpl implements UnboundStayBuilder {

    UnboundStayBuilderImpl() {
        super();
    }

    @Override
    public UnboundStayBuilder addRoom(final Room room, final Map<PersonPriceDescriber, Integer> people) {
        super.innerAddRoom(room, people);
        return this;
    }

    @Override
    public UnboundStayBuilder setClient(final Client client) {
        super.innerSetClient(client);
        return this;
    }

    @Override
    public UnboundStayBuilder setDates(final FixedPeriod dates) {
        super.innerSetDates(dates);
        return this;
    }

    @Override
    public UnboundStayBuilder setType(final StayTypePriceDescriber stayType) {
        super.innerSetType(stayType);
        return this;
    }

    @Override
    public UnboundStayBuilder addExtra(final StayExtraPriceDescriber stayExtra) {
        super.innerAddExtra(stayExtra);
        return this;
    }

    @Override
    public UnboundStayBuilder setState(final StayState state) {
        return this;
    }

}
