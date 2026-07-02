package it.unibo.isaccoop.graphics;

import javax.swing.ImageIcon;

import it.unibo.isaccoop.model.boundingbox.CircleBoundingBox;
import it.unibo.isaccoop.model.common.MapElement;
import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.room.Room;

/**
 * Represents the graphics implemented using Swing.
 */
public final class SwingGraphics implements Graphics {

    private static final String RES_PATH = "it/unibo/isaccoop/images/";
    private final java.awt.Graphics g2;

    private final double ratioX;
    private final double ratioY;

    /**
     * SwingGraphics Constructor.
     * @param g
     * @param ratioX
     * @param ratioY
     */
    public SwingGraphics(final java.awt.Graphics g, final double ratioX, final double ratioY) {
        this.g2 = g.create();
        this.ratioX = ratioX;
        this.ratioY = ratioY;
    }

    @Override
    public void drawElementAsImage(final MapElement element, final String imageName) {
       final var imageIcon = new ImageIcon(ClassLoader.getSystemResource(RES_PATH + imageName));

       if (element instanceof Room) {
           final var room = (Room) element;
           this.g2.drawImage(imageIcon.getImage(), 0, 0, (int) Math.round(room.getWidth() * this.ratioX),
                   (int) Math.round(room.getHeight() * this.ratioY), null);
       } else {
           final var sy2 = imageIcon.getImage().getHeight(null);
           final var sx2 = imageIcon.getImage().getWidth(null);
           final var delta = this.getDeltaXinPixel(((CircleBoundingBox) element.getBox()).getRadius());

           final var dx1 = this.getXinPixel(element.getCoords()) - delta;
           final var dy1 = this.getYinPixel(element.getCoords()) - delta;
           final var dx2 = this.getXinPixel(element.getCoords()) + delta;
           final var dy2 = this.getYinPixel(element.getCoords()) + delta;

           this.g2.drawImage(imageIcon.getImage(), dx1, dy1, dx2, dy2, 0, 0, sx2, sy2, null);
       }
    }

    /**
     * Method to convert x coord in pixel.
     * @param p element coords
     * @return x coord in pixel
     * */
    private int getXinPixel(final Point2D p) {
        return (int) Math.round(p.getX() * ratioX);
    }

    /**
     * Method to convert y coord in pixel.
     * @param p element coords
     * @return y coord in pixel
     * */
    private int getYinPixel(final Point2D p) {
        return (int)  Math.round(p.getY() * ratioY);
    }

    /**
     * Method to convert element radius in pixel.
     * @param dx element radius
     * @return element radius in pixel
     * */
    private int getDeltaXinPixel(final double dx) {
        return (int)  Math.round(dx * ratioX);
    }
}
