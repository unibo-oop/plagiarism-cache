package hotelmaster.reservations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hotelmaster.pricing.SimplePriceCalculation;
import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.utility.time.FixedPeriod;

/**
 * The implementation of ModifiableStay.
 */
public class StayImpl implements ModifiableStay {

    private FixedPeriod dates;
    private Client client;
    private StayState state;
    private StayTypePriceDescriber type;
    private final Set<StayExtraPriceDescriber> extras;
    private final Set<ModifiableOccupation> occupations;

    StayImpl() {
        extras = new HashSet<>();
        occupations = new HashSet<>();
        state = StayState.INACTIVE;
    }

    @Override
    public StayState getState() {
        return this.state;
    }

    @Override
    public Client getClient() {
        return this.client;
    }

    @Override
    public FixedPeriod getDates() {
        return this.dates;
    }

    @Override
    public StayTypePriceDescriber getType() {
        return this.type;
    }

    @Override
    public Set<StayExtraPriceDescriber> getExtrasView() {
        return Collections.unmodifiableSet(this.extras);
    }

    @Override
    public Set<Occupation> getOccupationsView() {
        return Collections.unmodifiableSet(this.occupations);
    }

    @Override
    public double getTotalPrice() {
        return new SimplePriceCalculation().calculate(this);
    }

    @Override
    public StayManager getStayManager() {
        return StayManager.getStayManager(this);
    }

    @Override
    public int compareTo(final Stay o) {
        return this.dates.compareTo(o.getDates());
    }

    @Override
    public void setClient(final Client client) {
        this.client = client;
    }

    @Override
    public void setDates(final FixedPeriod dates) {
        this.dates = dates;
    }

    @Override
    public void setType(final StayTypePriceDescriber type) {
        this.type = type;
    }

    @Override
    public Set<StayExtraPriceDescriber> getExtras() {
        return this.extras;
    }

    @Override
    public Set<ModifiableOccupation> getOccupations() {
        return this.occupations;
    }

    @Override
    public void setState(final StayState state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        result = prime * result + ((dates == null) ? 0 : dates.hashCode());
        result = prime * result + ((extras == null) ? 0 : extras.hashCode());
        result = prime * result + ((occupations == null) ? 0 : occupations.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        if (!(obj instanceof StayImpl)) {
            return false;
        }
        final StayImpl other = (StayImpl) obj;
        if (client == null) {
            if (other.client != null) {
                return false;
            }
        } else if (!client.equals(other.client)) {
            return false;
        }
        if (dates == null) {
            if (other.dates != null) {
                return false;
            }
        } else if (!dates.equals(other.dates)) {
            return false;
        }
        if (extras == null) {
            if (other.extras != null) {
                return false;
            }
        } else if (!extras.equals(other.extras)) {
            return false;
        }
        if (occupations == null) {
            if (other.occupations != null) {
                return false;
            }
        } else if (!occupations.equals(other.occupations)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }
}
