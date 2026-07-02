package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import it.unibo.model.BulletModel;
import it.unibo.model.Point2D;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.ViewInterface;

/**
 * The BulletView class is responsible for rendering the bullet's graphical
 * representation on the screen, and the update of its position.
 */
public class BulletView implements ViewInterface {
    private BulletModel model;
    private Image sprites;
    Scale scale;

    public BulletView(BulletModel model, Scale scale) {
        this.scale = scale;
        this.model = model;
        URL image_path = getClass().getClassLoader().getResource("images/puyosprites.png");
        this.sprites = new ImageIcon(image_path).getImage();
    }

    /**
     * The bullet is only drawn if the model is active.
     * Its position is calculated in the model using linear interpolation.
     * The bullet image is retrieved from the spritesheet and represents a black
     * Puyo.
     * While the cannon moves horizontally, the bullet is always fired vertically.
     * However, if the cannon's position were fixed, the bullet could also move
     * diagonally.
     */
    @Override
    public void draw(Graphics g) {
        if (!this.model.isActive()) {
            return;
        }
        int cellsize = this.scale.getScale() / 16;
        Point2D curPos = this.model.getCurrentPosition();
        int x = (int) curPos.x();
        int y = (int) curPos.y();
        g.drawImage(
                sprites,
                x - cellsize / 2,
                y - cellsize / 2,
                x + cellsize / 2,
                y + cellsize / 2,
                18 * 32, 3 * 32,
                19 * 32, 4 * 32,
                null);
    }
}