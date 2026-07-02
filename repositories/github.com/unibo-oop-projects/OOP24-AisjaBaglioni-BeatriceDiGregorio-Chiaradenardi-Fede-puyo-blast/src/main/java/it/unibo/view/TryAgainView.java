package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import it.unibo.controller.TryAgainController;
import it.unibo.model.Point2DI;
import it.unibo.model.Rectangle;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.ButtonInterface;
import it.unibo.view.interfaces.ClickInterface;
import it.unibo.view.interfaces.ViewInterface;

/**
 * The TryAgainView class is responsible for displaying
 * the cyan button "Try Again",
 * whose controller allows to restart the level by stopping the {@link GameLoop}
 * and triggering a new
 * {@link GameEvent}.
 */
public class TryAgainView implements ClickInterface, ViewInterface, ButtonInterface {
    private Image tryAgainImage;
    private Scale scale;
    private int imageWidth;
    private int imageHeight;
    private TryAgainController controller;

    public TryAgainView(Scale scale, TryAgainController controller) {
        this.scale = scale;
        this.controller = controller;

        URL imagePath = getClass().getClassLoader().getResource("images/tryagain_button.png");
        tryAgainImage = new ImageIcon(imagePath).getImage();
        this.imageWidth = tryAgainImage.getWidth(null);
        this.imageHeight = tryAgainImage.getHeight(null);
    }

    @Override
    public void draw(Graphics g) {
        Rectangle buttonArea = getArea();
        g.drawImage(
                tryAgainImage,
                buttonArea.upleft.x(),
                buttonArea.upleft.y(),
                buttonArea.lowright.x(),
                buttonArea.lowright.y(),
                0,
                0,
                imageWidth,
                imageHeight,
                null);
    }

    @Override
    public Rectangle getArea() {
        int newWidth = this.scale.getScale() / 7;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        int puyoHeight = this.scale.getScale() / 8;
        int x = this.scale.getScale() / 28;
        int y = this.scale.getScale() - newHeight - puyoHeight;
        Point2DI upleft = new Point2DI(x, y);
        Point2DI lowright = new Point2DI(x + newWidth, y + newHeight);
        return new Rectangle(upleft, lowright);
    }

    @Override
    public boolean isClicked(Point2DI pos) {
        return getArea().isInside(pos);
    }

    @Override
    public void doAction() {
        if (controller != null) {
            controller.handleClick();
        }
    }
}