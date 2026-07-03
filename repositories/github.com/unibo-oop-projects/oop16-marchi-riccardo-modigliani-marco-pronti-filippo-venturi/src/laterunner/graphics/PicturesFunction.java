package laterunner.graphics;

import java.awt.Image;
import java.util.EnumMap;

import javax.swing.ImageIcon;

/**
 * Pictures Implementation.
 * 
 */
public class PicturesFunction implements Pictures {

    private EnumMap<Icons, ImageIcon> icons = new EnumMap<Icons, ImageIcon>(Icons.class);
    private EnumMap<Icons, Image> images = new EnumMap<Icons, Image>(Icons.class);

    /**
     * Common IconsFunction constructor. Initializes the maps containing all the images.
     */
    public PicturesFunction() {
        this.setMaps();
    }

    private static String getPath(final Icons icon) {

        String path;

        switch(icon) {
        case BACK:
            path = "/back.png";
            break;
        case BACKGROUND:
            path = "/background.jpg";
            break;
        case BUS:
            path = "/bus.png";
            break;
        case BUY_LIFE:
            path = "/buy_life.png";
            break;
        case BUY_SPEED:
            path = "/buy_speed.png";
            break;
        case CAR:
            path = "/car.png";
            break;
        case COIN:
            path = "/coin.png";
            break;
        case CROSS:
            path = "/cross.png";
            break;
        case HEART:
            path = "/heart.png";
            break;
        case JEEP:
            path = "/jeep.png";
            break;
        case MENU:
            path = "/menu.png";
            break;
        case MOTORBIKE:
            path = "/motorbike.png";
            break;
        case PLAY:
            path = "/play.png";
            break;
        case QUIT:
            path = "/quit.png";
            break;
        case ROAD:
            path = "/road.png";
            break;
        case SHOP:
            path = "/shop.png";
            break;
        case STATS:
            path = "/stats.png";
            break;
        default:
            throw new IllegalStateException();
        }

        return path;

    }

    private static ImageIcon getEIcon(final Icons icon) {
        return new ImageIcon(Icons.class.getResource(getPath(icon)));
    }

    private static Image getEImage(final Icons icon) {
        return new ImageIcon(Icons.class.getResource(getPath(icon))).getImage();
    }

    private void setMaps() {
        for (Icons i:Icons.values()) {
            icons.put(i, getEIcon(i));
            images.put(i, getEImage(i));
        }
    }

    @Override
    public ImageIcon getIcon(final Icons icon) {
        return icons.get(icon);
    }

    @Override
    public Image getImage(final Icons icon) {
        return images.get(icon);
    }

}