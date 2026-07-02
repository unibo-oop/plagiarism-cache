package model.collidable.tank.color;

/**
 * Represents color of Tank. Its based of a RGB composition.
 * 
 * @author Nicola Tamburini
 *
 */
public interface TankColor {

    /**
     * 
     * @return red composition
     */
    int getRed();

    /**
     * 
     * @return green composition
     */
    int getGreen();

    /**
     * 
     * @return blue composition
     */
    int getBlue();

    /**
     * 
     * @return opacity
     */
    double getOpacity();
}
