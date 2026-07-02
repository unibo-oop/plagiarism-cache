package it.unibo.view;

import it.unibo.model.Grid;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.ViewInterface;

import java.awt.*;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * The BorderView class is responsible for displaying the {@link Grid} border,
 * based on a specified scale.
 */
public class BorderView implements ViewInterface {
    private Image borderImage;
    private Scale scale;
    private int imageWidth;
    private int imageHeight;

    BorderView(Scale scale) {
        this.scale = scale;
        final URL imageURL = getClass().getClassLoader().getResource("images/" + "gridborder.png");
        this.borderImage = new ImageIcon(imageURL).getImage();
        this.imageHeight = borderImage.getHeight(null);
        this.imageWidth = borderImage.getWidth(null);
    }

    /**
     * Here, the shrink variable is used to make the image slightly smaller,
     * ensuring it fits well with the other graphics.
     */
    @Override
    public void draw(final Graphics g) {
        int cellsize = this.scale.getScale() / 16;
        int shrink = cellsize / 2;
        g.drawImage(
                borderImage,
                cellsize * 3 + shrink,
                0 + shrink,
                cellsize * 13 - shrink,
                cellsize * 10 - shrink,
                0, 0,
                imageWidth, imageHeight, null);
    }
}
