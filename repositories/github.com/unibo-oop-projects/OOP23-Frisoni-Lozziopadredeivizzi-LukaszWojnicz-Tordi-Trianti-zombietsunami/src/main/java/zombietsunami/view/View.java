package zombietsunami.view;

import java.awt.event.KeyListener;

import javax.swing.JFrame;

import zombietsunami.view.api.KeyHandler;
import zombietsunami.view.api.VController;
import zombietsunami.view.mapview.impl.MapImpl;

/**
 * This utility class is the main view of the game and sets the main JFrame.
 */
public final class View {

    private View() {
    }

    /**
     * This method sets the main frame and puts into it the various panels.
     * 
     * @param c      sets the view controller of the class
     * @param width  is the screen width
     * @param height is the screen height
     */
    public static void start(final VController c, final int width, final int height) {
        final JFrame window = new JFrame("Zombie Tsunami");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setFocusable(true);
        final KeyHandler keyH = new KeyHandlerImpl();
        window.addKeyListener((KeyListener) keyH);
        final StartMenu startMenu = new StartMenu(width, height);
        final MapImpl gamePanel = new MapImpl(c, keyH);

        startMenu.enableStartPanel();
        window.add(startMenu);

        startMenu.getStartButton().addActionListener(e -> {
            window.remove(startMenu);
            window.add(gamePanel);
            window.revalidate();
            window.repaint();
            gamePanel.startGameThread();
        });
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

}
