package laterunner.core;

import laterunner.graphics.Icons;
import laterunner.graphics.PicturesFunction;
import laterunner.model.vehicle.Vehicles;

/**
 * Dimensions class.
 */
public final class Dimensions {

    private static final int WIDTH_JEEP = 21;
    private static final int WIDTH_BUS = 20;
    private static final int WIDTH_MOTORBIKE = 15;
    private static final int WIDTH_CAR = 12;
    private static final int HEIGHT_JEEP = 21;
    private static final int HEIGHT_BUS = 26;
    private static final int HEIGHT_MOTORBIKE = 47;
    private static PicturesFunction icons = new PicturesFunction();

    private static class LazyDimensions {
        private static final Dimensions SINGLETON = new Dimensions();
    }

    private Dimensions() { }

    /**
     * Instantiates dimensions with a lazy initialization.
     * @return
     *          The only one instance
     */
    public static Dimensions getDimensions() {
        return LazyDimensions.SINGLETON;
    }

    /**
     * Calculate the right vehicle's width.
     * 
     * @param vehicle
     *          vehicle used to select the right one obstacle
     * @return 
     *          width
     */

    public double getVehicleWidth(final Vehicles vehicle) {
        double width = 0;
        switch (vehicle) {
        case OBSTACLE_CAR : width = icons.getIcon(Icons.JEEP).getIconWidth() - WIDTH_JEEP; break;
        case BUS : width =  icons.getIcon(Icons.BUS).getIconWidth() - WIDTH_BUS; break;
        case MOTORBIKE : width = icons.getIcon(Icons.MOTORBIKE).getIconWidth() - WIDTH_MOTORBIKE; break;
        case USER_CAR : width = icons.getIcon(Icons.CAR).getIconWidth() - WIDTH_CAR; break;
        default : throw new IllegalStateException();
        }
        return width;
}

    /**
     * Calculate the right vehicle's height.
     * 
     * @param vehicle
     *          vehicle used to select the right one obstacle
     * @return 
     *          height
     */
    public double getVehicleHeight(final Vehicles vehicle) {
        double height = 0;
        switch (vehicle) {
        case OBSTACLE_CAR : height = icons.getIcon(Icons.JEEP).getIconHeight() - HEIGHT_JEEP; break;
        case BUS : height =  icons.getIcon(Icons.BUS).getIconHeight() - HEIGHT_BUS; break;
        case MOTORBIKE : height = icons.getIcon(Icons.MOTORBIKE).getIconHeight() - HEIGHT_MOTORBIKE; break;
        case USER_CAR : height = icons.getIcon(Icons.CAR).getIconHeight(); break;
        default : throw new IllegalStateException();
        }
        return height;
    }
}