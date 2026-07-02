package main.java.com.view;

import java.awt.BorderLayout;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This class implements the {@link JFrame} shown at the start of the game that
 * consists only of a {@link JButton} that runs the game.
 *
 */
public class GameStart implements BasicWindow {

    private static final String IMG_NAME = "/start.png";

    private GameObserver observer;
    private final JFrame frame;

    /**
     * Constructor that instantiates the frame and the start button.
     */
    public GameStart() {
        frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(createStartButton(), BorderLayout.CENTER);
        frame.setUndecorated(true);
        frame.pack();
        frame.setAlwaysOnTop(true);
    }

    /** {@inheritDoc} */
    @Override
    public void show() {
        frame.setVisible(true);
    }

    /** {@inheritDoc} */
    @Override
    public void setObserver(final GameObserver obs) {
        observer = obs;
    }

    /** {@inheritDoc} */
    @Override
    public JFrame getFrame() {
        return frame;
    }

    private JButton createStartButton() {
        final ImageIcon imgStart = new ImageIcon(getClass().getResource(IMG_NAME));
        final JButton start = new JButton(imgStart);
        start.setPreferredSize(new Dimension(imgStart.getIconWidth(), imgStart.getIconHeight()));
        start.addActionListener(e -> {
            frame.setVisible(false);
            observer.start();
        });
        return start;
    }
}
