package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * This class handles the game screen and everything in it.
 *
 */

public class GameScreenImpl extends JPanel implements GameScreen{

    private static final long serialVersionUID = -6769386766627070108L;
    private final Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Double HEIGHT_SCALE = 0.5;
    private static final Double WIDHT_SCALE = 0.25;
    private final Dimension gameDimension;
    private final DrawableCanvas canvas;
    private final InputHandler handler = new InputHandler();

    public GameScreenImpl(DrawableCanvas canvas) {
        super();
        this.setFocusable(true);
        this.gameDimension = new Dimension((int) (screenRes.getWidth() * WIDHT_SCALE),
                (int) (screenRes.getHeight() * HEIGHT_SCALE));
        this.setSize((int) (gameDimension.getWidth()), (int) (gameDimension.getHeight()));
        this.canvas = canvas;
        this.setBackground(Color.BLACK);
        this.addKeyListener(handler);
        this.setVisible(true);
        this.requestFocusInWindow();
    }

    /**
     * Method to update what is drawn on the screen.
     */
    public void updateScreen() {
        this.requestFocusInWindow();
        this.repaint();
    }

    /**
     * Method to paint a component
     * @param g the component to draw
     */
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(canvas.getBackGround(), 0, 0, gameDimension.width, gameDimension.height - 50, null);
        g2d.drawImage(canvas.getforeGround(), 0, 0, gameDimension.width, gameDimension.height - 50, null);
    }
    /**
     * Getter for the drawable canvas
     * @return the canvas on which to draw
     */
    public DrawableCanvas getCanvas() {
        return this.canvas;
    }
    /**
     * Getter for the Handler
     * @return handler of the game
     */
    public InputHandler getHandler() {
        return this.handler;
    }

}
