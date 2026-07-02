package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import it.unibo.controller.PauseController;
import it.unibo.model.PauseModel;
import it.unibo.model.Point2DI;
import it.unibo.model.Rectangle;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.ButtonInterface;
import it.unibo.view.interfaces.ClickInterface;
import it.unibo.view.interfaces.ViewInterface;

import java.awt.Color;

/**
 * The {@code PauseView} class represents the view for the pause button in the
 * game.
 * It displays a pause button when the game is running and a resume button when
 * the game is paused.
 * 
 * This view allows the player to click on the pause/resume button to toggle the
 * pause state of the game.
 * It implements the {@link ClickInterface}, {@link ViewInterface}, and
 * {@link ButtonInterface} interfaces
 * to provide functionality for clicking and displaying the button.
 */
public class PauseView implements ClickInterface, ViewInterface, ButtonInterface {

    /** Array of images for the pause and resume buttons. */
    private Image[] pause;

    /** The model that stores the current pause state of the game. */
    private PauseModel model;

    /** The scale factor for scaling the view elements. */
    Scale scale;

    /** The width of the pause/resume button image. */
    private int imageWidth;

    /** The height of the pause/resume button image. */
    private int imageHeight;

    /** The controller that handles the pause state logic. */
    private PauseController controller;

    /**
     * Constructs a {@code PauseView} instance with the given scale, model, and
     * controller.
     * Initializes the pause and resume button images and their dimensions.
     * 
     * @param scale      the scale factor for the view
     * @param model      the {@code PauseModel} instance that tracks the pause state
     * @param controller the {@code PauseController} instance that handles pause
     *                   logic
     */
    public PauseView(Scale scale, PauseModel model, PauseController controller) {
        this.scale = scale;
        this.pause = new Image[2];

        /* Load pause and resume button images*/
        URL pause_path = getClass().getClassLoader().getResource("images/pause_button.png");
        URL resume_path = getClass().getClassLoader().getResource("images/resume_button.png");

        this.pause[0] = new ImageIcon(pause_path).getImage();
        this.pause[1] = new ImageIcon(resume_path).getImage();

        this.imageWidth = this.pause[0].getWidth(null);
        this.imageHeight = this.pause[0].getHeight(null);

        this.model = model;
        this.controller = controller;
    }

    /**
     * Draws the pause or resume button on the screen depending on the current pause
     * state.
     * If the game is paused, the resume button is displayed; otherwise, the pause
     * button is shown.
     * It also applies a semi-transparent overlay when the game is paused.
     * 
     * @param g the {@code Graphics} object used for drawing the button
     */
    @Override
    final public void draw(Graphics g) {
        Rectangle button = getArea();

        /* If the game is not paused, draw the pause button*/
        if (!this.model.getPause()) {
            g.drawImage(
                    this.pause[0],
                    button.upleft.x(),
                    button.upleft.y(),
                    button.lowright.x(),
                    button.lowright.y(),
                    0,
                    0,
                    imageWidth,
                    imageHeight,
                    null);
        } else {
            /* Draw a semi-transparent overlay and the resume button when the game is paused*/
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, this.scale.getScale(), this.scale.getScale());
            g.drawImage(
                    this.pause[1],
                    button.upleft.x(),
                    button.upleft.y(),
                    button.lowright.x(),
                    button.lowright.y(),
                    0,
                    0,
                    imageWidth,
                    imageHeight,
                    null);
        }
    }

    /**
     * Checks if the pause/resume button was clicked.
     * 
     * @param pos the position of the click as a {@code Point2DI} object
     * @return {@code true} if the button was clicked, {@code false} otherwise
     */
    @Override
    public boolean isClicked(Point2DI pos) {
        Rectangle button = getArea();
        return button.isInside(pos);
    }

    /**
     * Performs the action of toggling the pause state when the button is clicked.
     * This method is called when the player clicks on the pause/resume button.
     */
    @Override
    public void doAction() {
        this.controller.togglePause();
    }

    /**
     * Calculates and returns the area (bounding box) of the pause/resume button.
     * This area is used to detect clicks on the button.
     * 
     * @return a {@code Rectangle} representing the area of the button
     */
    @Override
    public Rectangle getArea() {
        int newWidth = this.scale.getScale() / 7;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        int x = this.scale.getScale() - newWidth - this.scale.getScale() / 28;
        int y = this.scale.getScale() / 16;

        Point2DI upleft = new Point2DI(x, y);
        Point2DI lowright = new Point2DI(x + newWidth, y + newHeight);

        return new Rectangle(upleft, lowright);
    }
}
