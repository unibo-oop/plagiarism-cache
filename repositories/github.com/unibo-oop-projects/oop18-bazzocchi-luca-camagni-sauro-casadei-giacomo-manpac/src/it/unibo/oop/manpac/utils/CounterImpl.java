package it.unibo.oop.manpac.utils;

/**
 * Implementation of Counter interface.
 */
public class CounterImpl implements Counter {

    private final int total;
    private int remaining;

    /**
     * Constructor of CounterImpl.
     * @param total the starting number of the counter
     */
    public CounterImpl(final int total) {
        this.total = total;
        this.remaining = total;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRemaining() {
        return this.remaining;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotal() {
        return this.total;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean decreaseCount() {
        this.remaining--;
        return this.remaining > 0 ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseCount() {
        this.remaining++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetCount() {
        this.remaining = this.total;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (prime + remaining) + total;
    }

    /**
     * {@inheritDoc}
     */
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
        final CounterImpl other = (CounterImpl) obj;
        if (remaining != other.remaining) {
            return false;
        }
        return (total != other.total);
    }

}
