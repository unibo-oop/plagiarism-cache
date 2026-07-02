package element;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Elements {

    /**
     * the arithmetic null vector
     */
    public static final Vector2D VECTOR_NULL = new Vector2DImpl(0, 0);

    /**
     * the origin point
     */
    public static final Point2D ORIGIN = new Point2DImpl(0, 0);

    private Elements() {
    }

    /**
     * @param value  the value to round
     * @param places the number of digit after the ,
     * @return the value rounded
     * @throws IllegalArgumentException if the place is minus of 0
     */
    public static double round(final double value, final int places) throws IllegalArgumentException {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
