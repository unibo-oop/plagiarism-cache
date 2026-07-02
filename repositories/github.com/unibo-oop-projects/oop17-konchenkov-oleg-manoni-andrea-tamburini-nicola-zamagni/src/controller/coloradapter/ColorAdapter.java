package controller.coloradapter;

import javafx.scene.paint.Color;
import model.collidable.tank.color.TankColor;
import model.collidable.tank.color.TankColorImpl;

/**
 * this class permit to convert {@link TankColor} type to
 * {@link javafx.scene.paint.Color}.
 * 
 * @author Nicola Tamburini
 *
 */
public class ColorAdapter extends TankColorImpl {

    private static final int MAXRGB = 255;

    private final Color fxColor;
    private final TankColor tankColor;

    /**
     * Constructor from RGB component.
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
    public ColorAdapter(final int red, final int green, final int blue, final double opacity) {
        super(red, green, blue, opacity);
        fxColor = Color.rgb(red, green, blue, opacity);
        tankColor = new TankColorImpl(red, green, blue, opacity);
    }

    /**
     * Constructor from {@link Color}.
     * 
     * @param color
     *            type {@link Color} color
     */
    public ColorAdapter(final Color color) {
        this(getRGB(color.getRed()), getRGB(color.getGreen()), getRGB(color.getBlue()), color.getOpacity());
    }

    /**
     * Constructor from {@link TankColor}.
     * 
     * @param color
     *            type {@link TankColor} color
     */
    public ColorAdapter(final TankColor color) {
        this(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());
    }

    /**
     * 
     * @return the color in type {@link Color}
     */
    public Color getFxColor() {
        return fxColor;
    }

    /**
     * 
     * @return the color in type {@link TankColor}
     */
    public TankColor getTankColor() {
        return tankColor;
    }

    private static int getRGB(final double d) {
        return (int) Math.round(d * MAXRGB);
    }
}
