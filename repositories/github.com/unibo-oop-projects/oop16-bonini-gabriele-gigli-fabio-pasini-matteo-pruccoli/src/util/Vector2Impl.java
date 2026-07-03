package util;

/**
 * Classe Vector2.
 * 
 * @param <X>
 *            Tipo del vettore.
 */
public final class Vector2Impl<X extends Number> implements Vector2<X> {

    private X x, y;

    /**
     * Costruttore utilizzato per creare un Vector2 a partire da un'altro
     * Vector2.
     * 
     * @param vector
     *            Vettore di cui fare una copia.
     */
    public Vector2Impl(final Vector2<X> vector) {
        set(vector.getX(), vector.getY());
    }

    /**
     * Costruttore utilizzato per creare un Vector2 a partire dalle coordinate x
     * e y.
     * 
     * @param x
     *            Coordinata x del vettore.
     * @param y
     *            Coordinata y del vettore.
     */
    public Vector2Impl(final X x, final X y) {
        set(x, y);
    }

    @Override
    public void set(final X x, final X y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void set(final Vector2<X> v) {
        this.x = v.getX();
        this.y = v.getY();
    }

    @Override
    public X getX() {
        return x;
    }

    @Override
    public X getY() {
        return y;
    }

    @Override
    public void setX(final X x) {
        this.x = x;
    }

    @Override
    public void setY(final X y) {
        this.y = y;
    }

    /**
     * Calcola la distanza euclidea tra due vettori.
     * 
     * @param v
     *            Primo vettore.
     * @param v1
     *            Secondo vettore.
     * @return Distanza euclidea tra i due vettori.
     */

    public static double getDistance(final Vector2<?> v, final Vector2<?> v1) {
        final double x = v.getX().doubleValue() - v1.getX().doubleValue();
        final double y = v.getY().doubleValue() - v1.getY().doubleValue();
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Vector2<?> && ((Vector2<?>) object).getX().equals(this.getX())
                && ((Vector2<?>) object).getY().equals(this.getY());
    }
}