package util;

/**
 * Interfaccia Vector2.
 * 
 * @param <X>
 *            Tipo del vettore.
 */

public interface Vector2<X extends Number> {

    /**
     * Setta il campo x e y del vettore.
     * 
     * @param x
     *            Coordinata x del vettore.
     * @param y
     *            Coordinata y del vettore.
     */
    void set(X x, X y);

    /**
     * Setta il campo x e y del vettore a partire da un altro vettore.
     * 
     * @param v
     *            Vettore di cui fare la copia.
     */
    void set(Vector2<X> v);

    /**
     * Ritorna il campo x del vettore.
     * 
     * @return Coordinata x del vettore.
     */

    X getX();

    /**
     * Ritorna il campo y del vettore.
     * 
     * @return Coordinata y del vettore.
     */

    X getY();

    /**
     * Setta il campo x del vettore.
     * 
     * @param x
     *            Coordinata x.
     */

    void setX(X x);

    /**
     * Setta il campo y del vettore.
     * 
     * @param y
     *            Coordinata y.
     */

    void setY(X y);

    @Override
    boolean equals(Object object);

    @Override
    int hashCode();

}