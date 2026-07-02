package javagotchi.view.minigame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.lang3.math.Fraction;

import javafx.application.Platform;
import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.utility.Utility;

/**
 *
 * @author marica
 *
 */
public abstract class AbstractFrameDefault extends JFrame implements FrameDefault {

    private static final long serialVersionUID = 8481707000166074215L;
    private static final Dimension DIMENSION_DEFAULT_SCREEN = new Dimension(1920, 1080);
    private static final Dimension SCREENDIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Fraction FRACTIONWIDTH = Fraction.getFraction(SCREENDIMENSION.width,
            DIMENSION_DEFAULT_SCREEN.width);
    private static final Fraction FRACTIONHEIGHT = Fraction.getFraction(SCREENDIMENSION.height,
            DIMENSION_DEFAULT_SCREEN.height);
    private static final String EXIT_MESSAGE = "Do you really want to exit?";

    /**
     * Constructor for FrameDefault.
     * 
     * @param width
     *            width of windows.
     * @param height
     *            height of windows
     */
    public AbstractFrameDefault(final int width, final int height) {
        super();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setMinimumSize(new Dimension((int) (FRACTIONWIDTH.doubleValue() * width),
                (int) (FRACTIONHEIGHT.doubleValue() * height)));
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                if (JOptionPane.showConfirmDialog(AbstractFrameDefault.this, EXIT_MESSAGE) == JOptionPane.YES_OPTION) {
                    Platform.runLater(() -> {
                        MiniGame.getFactoryController().getControllerMiniGame().getHomeController().save();
                        System.exit(0);
                    });
                }
            }
        });
        this.pack();
    }

    @Override
    public final void display() {
        this.setVisible(true);
    }

    @Override
    public final void hideWindow() {
        this.setVisible(false);
    }

    @Override
    public final void disposeWindow() {
        this.dispose();
    }

    /**
     * Sets event of buttons.
     */
    protected abstract void setEvent();

    /**
     * Creates the background image.
     * @author marica
     *
     */
    protected static class BackgroundImage extends JPanel {
        private static final long serialVersionUID = -6817719321353816406L;
        private final Image img;

        /**
         * 
         * @param path
         *            path of image
         * @param frame
         *            window in which to insert the background image
         */
        protected BackgroundImage(final String path, final JFrame frame) {
            super();
            img = Utility.createImage(path, AbstractFrameDefault.DIMENSION_DEFAULT_SCREEN.width,
                    AbstractFrameDefault.DIMENSION_DEFAULT_SCREEN.height);
            this.setPreferredSize(frame.getSize());
            this.setLayout(new BorderLayout());
            loadImage(this, img);
        }

        @Override
        protected final void paintComponent(final Graphics g) {
            g.drawImage(img, 0, 0, null);
        }

        private static void loadImage(final JComponent comp, final Image img) {
            try {
                final MediaTracker track = new MediaTracker(comp);
                track.addImage(img, 0);
                track.waitForID(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
