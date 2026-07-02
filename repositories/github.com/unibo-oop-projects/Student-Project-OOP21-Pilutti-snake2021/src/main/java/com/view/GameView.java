package main.java.com.view;

import java.awt.BorderLayout;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class represents the view's entry point.
 * Here are created and initialized the main frame for the game and all its components.
 *
 */
public class GameView implements View {

    private static final String FRAME_NAME = "Snake Game";
    private static final String SCORE = "Score: 0";
    private static final String HI_SCORE = "Highscore: ";
    private static final String PAUSE = "Pause";
    private static final String RESET = "Reset";
    private static final String QUIT = "Quit";
    private static final Font FONT = new Font("Tahoma", Font.BOLD, 21);
    private static final Dimension WINDOW_SIZE = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());

    private GameObserver observer;
    private final JFrame frame;
    private JLabel lScore, lHiScore;
    private JButton bPause, bReset, bQuit;
    private final MapViewImpl mapView;
    private final BasicWindow gameOver, gameStart;

    /**
     * Constructor that creates and initializes all the components of the game's main window.
     * 
     * @param xMapSize the map's width
     * @param yMapSize the map's height
     */
    public GameView(final int xMapSize, final int yMapSize) {
        frame = new JFrame(FRAME_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(WINDOW_SIZE);
        frame.getContentPane().setLayout(new BorderLayout());
        gameOver = new GameOver();
        gameStart = new GameStart();
        mapView = new MapViewImpl(xMapSize, yMapSize);
        frame.getContentPane().add(createScorePanel(), BorderLayout.NORTH);
        frame.getContentPane().add(createButtonsPanel(), BorderLayout.SOUTH);
        frame.getContentPane().add(mapView, BorderLayout.CENTER);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null); // Centers the frame on the screen.
        frame.pack();
        mapView.setFocusable(false);
        gameStart.getFrame().setLocationRelativeTo(frame);
        gameOver.getFrame().setLocationRelativeTo(frame);
        gameStart.show();
    }

    /** {@inheritDoc} */
    public MapViewImpl getMapView() {
        return mapView;
    }

    /** {@inheritDoc} */
    public JFrame getFrame() {
        return frame;
    }

    /** {@inheritDoc} */
    public JLabel getScoreLabel() {
        return lScore;
    }

    /** {@inheritDoc} */
    public JLabel getHiScoreLabel() {
        return lHiScore;
    }

    /** {@inheritDoc} */
    @Override
    public void setObserver(final GameObserver obs) {
        observer = obs;
        gameOver.setObserver(obs);
        gameStart.setObserver(obs);
    }

    /** {@inheritDoc} */
    @Override
    public void show() {
        this.frame.setVisible(true);
    }

    /** {@inheritDoc} */
    public void showGameOver() {
        gameOver.show();
    }

    /** {@inheritDoc} */
    @Override
    public void updateView() {
        frame.repaint();
    }

    /** {@inheritDoc} */
    public void enableButtons() {
        bPause.setEnabled(true);
        bReset.setEnabled(true);
        bQuit.setEnabled(true);
    }

    private boolean confirmDialog(final String question, final String name) {
        return JOptionPane.showConfirmDialog(frame, question, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private JPanel createScorePanel() {
        final JPanel pTop = new JPanel(new FlowLayout());
        lScore = new JLabel(SCORE);
        lHiScore = new JLabel(HI_SCORE);
        lScore.setForeground(Color.WHITE);
        lHiScore.setForeground(Color.WHITE);
        lScore.setFont(FONT);
        lHiScore.setFont(FONT);
        pTop.setBackground(Color.BLACK);
        pTop.add(lScore);
        pTop.add(lHiScore);
        return pTop;
    }

    private JPanel createButtonsPanel() {
        final JPanel pBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bPause = new MyButton(PAUSE);
        bReset = new MyButton(RESET);
        bQuit = new MyButton(QUIT);
        bPause.addActionListener(e -> {
            bReset.setEnabled(false);
            bQuit.setEnabled(false);
            observer.pauseGame();
        });
        bPause.setMnemonic(KeyEvent.VK_Z); // ALT + Z to pause the game
        bReset.addActionListener(e -> {
            observer.pauseGame();
            if (confirmDialog("Confirm resetting?", "Reset")) {
                observer.resetGame();
            } else {
                mapView.requestFocusInWindow();
                observer.pauseGame();
            }
        });
        bReset.setMnemonic(KeyEvent.VK_X); // ALT + X to reset the game
        bQuit.addActionListener(e -> {
            observer.pauseGame();
            if (confirmDialog("Confirm quitting?", "Quit")) {
                observer.quit();
            } else {
                mapView.requestFocusInWindow();
                observer.pauseGame();
            }
        });
        bQuit.setMnemonic(KeyEvent.VK_C); // ALT + C to quit the game
        bPause.setEnabled(false);
        bReset.setEnabled(false);
        bQuit.setEnabled(false);
        pBottom.add(bPause);
        pBottom.add(bReset);
        pBottom.add(bQuit);
        pBottom.setBackground(Color.BLACK);
        return pBottom;
    }
}
