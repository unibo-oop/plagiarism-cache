package javagotchi.utility;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * 
 * @author marica
 *
 */
public final class Utility {

    private Utility() {
    }

    /**
     * 
     * Debugging. Prints passed object.
     * 
     * @param obj
     *            object to print
     * 
     */
    public static void log(final Object obj) {
        System.out.println(obj);
    }

    /**
     * Creates an icon with a given path and a given dimension.
     * 
     * @param path
     *            path of image
     * @param width
     *            width of image
     * @param height
     *            height of image
     * @return {@link Icon}
     */
    public static Icon createIcon(final String path, final int width, final int height) {
        return new ImageIcon(createImage(path, width, height));
    }

    /**
     * Creates an image with a given path and a given dimension.
     * 
     * @param path
     *            path of image
     * @param width
     *            width of image
     * @param height
     *            height of image
     * @return {@link Image}
     */
    public static Image createImage(final String path, final int width, final int height) {
        return new ImageIcon(Utility.class.getResource(path)).getImage().getScaledInstance(width, height,
                Image.SCALE_DEFAULT);
    }

    /**
     * Create a random brighter color.
     * 
     * @return random color
     */
    public static Color generateRandomColor() {
        final float red = generateRandom(null).floatValue();
        final float green = generateRandom(null).floatValue();
        final float blue = generateRandom(null).floatValue();
        Color colour = new Color(red, green, blue).brighter();
        while (colour.equals(Color.WHITE)) {
            colour = generateRandomColor();
        }
        return colour;
    }

    /**
     * Create a random float if max is null , otherwise a random integer.
     * 
     * @param max
     *            if it is not null, it is the max number to generate random.
     * @return random number
     */
    public static Number generateRandom(final Integer max) {
        return max == null ? new Random().nextFloat() : new Random().nextInt(max.intValue());
    }

}
