package model.implementations;

import java.io.Serializable;

import model.interfaces.Luggage;

/**
 * 
 * Implements a luggage.
 */
public class LuggageImpl implements Luggage, Serializable {

    private static final long serialVersionUID = -7928802003897862031L;

    private final String luggageId;
    private final double weight;

    /**
     * Creates a new luggage.
     * 
     * @param wgt    the weight of luggage
     */
    public LuggageImpl(final double wgt) {
        super();
        this.luggageId = IdGeneratorImpl.getIdGenerator().generate();
        this.weight = wgt;
    }

    @Override
    public String getLuggageId() {
        return this.luggageId;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return "ID: " + this.luggageId + ", WEIGHT: " + this.weight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.luggageId == null) ? 0 : this.luggageId.hashCode());
        long temp;
        temp = Double.doubleToLongBits(this.weight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LuggageImpl other = (LuggageImpl) obj;
        if (this.luggageId == null) {
            if (other.luggageId != null) {
                return false;
            }
        } else if (!this.luggageId.equals(other.luggageId)) {
            return false;
        }
        return true;
    }

}