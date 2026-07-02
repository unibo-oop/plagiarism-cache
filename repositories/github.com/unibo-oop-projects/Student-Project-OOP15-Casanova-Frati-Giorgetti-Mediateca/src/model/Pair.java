package model;

import java.io.Serializable;

/**
 * This class is taken from the lessons' slide. It manages a pair of generic
 * type.
 *
 * @author Edoardo
 *
 * @param <X>
 * @param <Y>
 */
public class Pair<X, Y> implements Serializable {

    private static final long serialVersionUID = -6161750573464309304L;
    private X first;
    private Y second;

    /**
     * Pair's constructor.
     *
     * @param initFirst
     *            generic.
     * @param initSecond
     *            generic.
     */
    public Pair(final X initFirst, final Y initSecond) {
        this.first = initFirst;
        this.second = initSecond;
    }

    /**
     * First element getter.
     *
     * @return the first element.
     */
    public X getFirst() {
        return this.first;
    }

    /**
     * Second element getter.
     *
     * @return the second element.
     */
    public Y getSecond() {
        return this.second;
    }

    /**
     * First element setter.
     *
     * @param initFirst
     *            the first element.
     */
    public void setFirst(final X initFirst) {
        this.first = initFirst;
    }

    /**
     * Secodn element setter.
     *
     * @param initSecond
     *            the second element.
     */
    public void setSecond(final Y initSecond) {
        this.second = initSecond;
    }

    @Override
    public String toString() {
        return "<" + this.first + "," + this.second + ">";
    }
}
