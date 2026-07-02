package model.collidable.tank.color;

/**
 * {@inheritDoc}.
 * 
 * @author Nicola Tamburini
 *
 */
public class TankColorImpl implements TankColor {

    private static final int MAXRGB = 255;
    private final int red;
    private final int green;
    private final int blue;
    private final double opacity;

    /**
     * TankColor constructor.
     * 
     * @param red
     *            red component, in range {@code 0-255}
     * @param green
     *            green component, in range {@code 0-255}
     * @param blue
     *            blue component, in range {@code 0-255}
     * @param opacity
     *            opacity, in range {@code 0.0-1.0}
     */
    public TankColorImpl(final int red, final int green, final int blue, final double opacity) {
        if (red < 0 || red > MAXRGB) {
            throw new IllegalArgumentException();
        }
        if (green < 0 || green > MAXRGB) {
            throw new IllegalArgumentException();
        }
        if (blue < 0 || blue > MAXRGB) {
            throw new IllegalArgumentException();
        }
        if (opacity < 0.0 || opacity > 1.0) {
            System.out.println(opacity);
            throw new IllegalArgumentException();
        }

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRed() {
        return this.red;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGreen() {
        return this.green;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBlue() {
        return this.blue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getOpacity() {
        return this.opacity;
    }

}
