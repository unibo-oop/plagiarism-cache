package view;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import application.StealthNinja;

/**
 * Creates window game.
 * 
 *
 */
public class WindowGame {

    private static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() + 1;

    private final JFrame frame;

    /**
     * Constructor for windowGame.
     */
    public WindowGame() {
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
                new Point(0, 0), "null"));

    }

    /**
     * 
     * @return frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * 
     * method to add level into the frame.
     * 
     */
    public void addLevel() {

        frame.setVisible(true);
        frame.add(StealthNinja.MODEL_ACTION.getLevelController().getRendering());
        StealthNinja.MODEL_ACTION.getLevelController().start();

    }

    /**
     * 
     * method to remove level into the frame.
     * 
     */
    public void removeLevel() {
        frame.getContentPane().removeAll();
        frame.dispose();
    }

}
