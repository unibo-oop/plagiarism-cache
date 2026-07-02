package model;

import java.io.Serializable;

/**
 * 
 * abstract class for stats and item.
 *
 */
public abstract class AbstractValue implements Serializable {

    private static final long serialVersionUID = 8418635479839137969L;
    private String name;
    private volatile double value;

    /**
     * 
     * @param newName
     *            constructor for name
     * @param newValue
     *            constructor for value
     */
    public AbstractValue(final String newName, final double newValue) {
        this.name = newName;
        this.value = newValue;
    }

    /**
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return quantity of this item
     */

    @Override
    public String toString() {
        return name + "[ value=" + value + "]";
    }

    /**
     * 
     * @param newName
     *            is the new name
     */
    public void setName(final String newName) {
        this.name = newName;
    }

    /**
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * 
     * @param newValue
     *            is the new value
     */
    public void setValue(final double newValue) {
        this.value = newValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long temp;
        temp = Double.doubleToLongBits(value);
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
        AbstractValue other = (AbstractValue) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value)) {
            return false;
        }
        return true;
    }

}
