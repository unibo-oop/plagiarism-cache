package main.java.com.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class implements the {@link JFrame} to be shown when the game is over.
 *
 */
public class GameOver implements BasicWindow {

    private static final String GAME_OVER = "Game Over";
    private static final String RESTART = "Restart";
    private static final String QUIT = "Quit";
    private static final String IMG_NAME = "/game_over.png";
    private static final Color TRANSPARENT = new Color(1.0f, 1.0f, 1.0f, 0.0f);

    private GameObserver observer;
    private final JFrame frame;

    /**
     * Constructor that instantiates the frame and its components.
     */
    public GameOver() {
        frame = new JFrame(GAME_OVER);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        final JLabel lImg = new JLabel(new ImageIcon(getClass().getResource(IMG_NAME)));
        frame.getContentPane().add(lImg, BorderLayout.CENTER);
        frame.getContentPane().add(createButtonsPanel(), BorderLayout.SOUTH);
        frame.setUndecorated(true);
        frame.setBackground(TRANSPARENT);
        frame.setAlwaysOnTop(true);
        frame.pack();
    }

    /** {@inheritDoc} */
    public void show() {
        frame.setVisible(true);
    }

    /** {@inheritDoc} */
    public void setObserver(final GameObserver obs) {
        observer = obs;
    }

    /** {@inheritDoc} */
    @Override
    public JFrame getFrame() {
        return frame;
    }

    private JPanel createButtonsPanel() {
        final JPanel pBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JButton bRestart = new MyButton(RESTART);
        final JButton bQuit = new MyButton(QUIT);
        bRestart.addActionListener(e -> {
            observer.resetGame();
            frame.setVisible(false);
        });
        bQuit.addActionListener(e -> {
            observer.quit();
        });
        pBottom.add(bRestart);
        pBottom.add(bQuit);
        pBottom.setBackground(TRANSPARENT);
        return pBottom;
    }

}
