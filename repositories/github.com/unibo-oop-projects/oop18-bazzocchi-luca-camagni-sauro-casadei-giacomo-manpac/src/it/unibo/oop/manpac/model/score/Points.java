package it.unibo.oop.manpac.model.score;

/**
 * Represents the score of any type.
 *
 * @param <X> Type of points. The use of an immutable type (eg Integer, String,
 *            etc.) is highly recommended.<br>
 *            The type MUST extend java.io.Serializable.
 */
public interface Points<X extends java.io.Serializable> extends java.io.Serializable {

    /**
     * To get the points value (not a copy!).
     * 
     * @return the points value
     */
    X getValue();

    /**
     * To get a copy of {@link Points}.
     * 
     * @return Copy of Points
     */
    Points<X> getCopy();
}
