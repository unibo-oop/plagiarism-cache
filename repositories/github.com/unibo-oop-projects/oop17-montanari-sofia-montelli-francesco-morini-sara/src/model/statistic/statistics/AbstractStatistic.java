package model.statistic.statistics;

import model.statistic.Statistic;
/**
 *
 * @param <T> is the statistic type, that need to be comparable
 */
public abstract class AbstractStatistic<T extends Comparable<T>> implements Statistic<T> {

   private final String name;

    /**
     * 
     * @param name the name of the statistic
     */
    public AbstractStatistic(final String name) {
        this.name = name;
    }

    @Override
    public final int compareTo(final Statistic<T> that) {
        if (!this.getName().equals(that.getName())) {
            return 0;
        }
        return this.getValue().compareTo(that.getValue());
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public abstract T getValue(); 

    @SuppressWarnings("unchecked")
    @Override
    public final boolean equals(final Object that) {
        if (that instanceof Statistic) {
            return (this.compareTo((Statistic<T>) that) == 0);
        }
        return false;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
        }

    @Override
    public final String toString() {
        return getName().toString() + ": " + getValue().toString();
    }

}
