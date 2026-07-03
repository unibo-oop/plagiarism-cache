package hotelmaster.pricing;

import hotelmaster.structure.HotelEntity;

/**
 * The base class describing a price. This class should never be instanced,
 * instead it should be only used as superclass for specialized types of prices.
 * Once a price is created, only its numeric value can be modified.
 */
public class PriceDescriber implements Comparable<PriceDescriber>, HotelEntity {

    private final String description;
    private double price;

    PriceDescriber(final String description, final double price) throws IllegalArgumentException {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Cannot have empty description");
        }
        this.description = description;
        this.price = price;
    }

    /**
     * Gets the description of the {@link PriceDescriber}.
     * 
     * @return thet description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the price of the {@link PriceDescriber}.
     * 
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price to a new value.
     * 
     * @param value
     *            the value to be set
     */
    protected void setPrice(final double value) {
        this.price = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        if (!(obj instanceof PriceDescriber)) {
            return false;
        }
        final PriceDescriber other = (PriceDescriber) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(final PriceDescriber o) {
        return description.compareTo(o.description);
    }

    @Override
    public String toString() {
        return this.description;
    }
}
