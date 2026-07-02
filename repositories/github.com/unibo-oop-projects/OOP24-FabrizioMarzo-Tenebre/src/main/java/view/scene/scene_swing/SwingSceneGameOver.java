package view.scene.scene_swing;

import javax.swing.*;
import view.scene.Scene;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A Swing-based implementation of the Game Over scene.
 * <p>
 * This class creates a non-resizable JFrame window that displays a "GAME OVER"
 * message
 * along with a custom finish level message centered on a black background.
 * It listens for keyboard input and exits the application when the ESC key is
 * pressed.
 * </p>
 */
public class SwingSceneGameOver implements Scene {

    private JFrame frame;
    private GameOverPanel panel;
    private int width;
    private int height;
    private String finishLevel;

    /**
     * Constructs the Game Over scene with specified dimensions and a finish
     * message.
     *
     * @param width       the width of the window in pixels
     * @param height      the height of the window in pixels
     * @param finishLevel the message to display under the "GAME OVER" text
     */
    public SwingSceneGameOver(final int width, final int height, final String finishLevel) {
        this.width = width;
        this.height = height;
        this.finishLevel = finishLevel;

        frame = new JFrame("Game Over");
        frame.setMinimumSize(new Dimension(width, height));
        frame.setResizable(false);

        panel = new GameOverPanel();
        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window on screen
        frame.setVisible(true);
    }

    /**
     * Renders the scene by repainting the frame on the Swing Event Dispatch Thread.
     */
    @Override
    public void render() {
        try {
            SwingUtilities.invokeAndWait(() -> frame.repaint());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inner JPanel class responsible for rendering the Game Over message
     * and handling key input to exit the application.
     */
    private class GameOverPanel extends JPanel implements KeyListener {

        private final JButton exitButton;

        /**
         * Initializes the panel with size, background, keyboard listener, and exit
         * button.
         */
        public GameOverPanel() {
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.BLACK);
            setFocusable(true);
            addKeyListener(this);
            setFocusTraversalKeysEnabled(false);
            setLayout(null); // Absolute positioning

            // Create the Exit button
            exitButton = new JButton("Exit Game");
            exitButton.setFocusPainted(false);
            exitButton.setBackground(Color.DARK_GRAY);
            exitButton.setForeground(Color.WHITE);
            exitButton.setFont(new Font("Arial", Font.PLAIN, 16));
            exitButton.setBounds((width - 150) / 2, height - 120, 150, 40); // Centered button

            // Add action listener
            exitButton.addActionListener(e -> System.exit(0));

            add(exitButton);
        }

        /**
         * Paints the Game Over message and finish message.
         *
         * @param g the Graphics context used for drawing
         */
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 48));

            String message = "GAME OVER";
            FontMetrics fm = g2d.getFontMetrics();
            int msgWidth = fm.stringWidth(message);
            int msgHeight = fm.getHeight();

            int x = (getWidth() - msgWidth) / 2;
            int y = (getHeight() / 2) + (msgHeight / 4);

            g2d.drawString(message, x, y);

            g2d.setFont(new Font("Arial", Font.PLAIN, 20));
            int instrWidth = g2d.getFontMetrics().stringWidth(finishLevel);
            g2d.drawString(finishLevel, (getWidth() - instrWidth) / 2, y + 50);
        }

        /**
         * Exits the application if ESC key is pressed.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // No action needed
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // No action needed
        }
    }

}
