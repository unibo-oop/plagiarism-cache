package it.unibo.michelito.view.gameview.frame.impl;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.panel.impl.GamePanel;
import it.unibo.michelito.view.gameview.frame.api.GameView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Represents the view of the game, implements {@link GameView}.
 * Entry point for the View.
 */
public final class GameViewImpl extends JFrame implements GameView {
    @Serial
    private static final long serialVersionUID = 1L;
    private boolean showing = true;
    private final double aspectRatio;
    private final GamePanel gamePanel = new GamePanel();

    /**
     * Constructor for the GameViewImpl.
     */
    public GameViewImpl() {
        super();
        setTitle("Michelito");

        add(gamePanel, BorderLayout.CENTER);

        //set frame spawn
        setLocationRelativeTo(null);

        //set custom resizing
        pack();
        final Dimension size = getSize();
        aspectRatio = (double) size.width / size.height;
        setMinimumSize(size);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                resizeDiagonally();
            }
        });

        //set custom quit closing action
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                final int response = JOptionPane.showConfirmDialog(
                        GameViewImpl.this,
                        "Are you sure about that?",
                        "Quit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    setShowingFalse();
                }
            }
        });
    }

    @Override
    public void setViewVisibility(final boolean show) {
        SwingUtilities.invokeLater(() -> {
            this.setVisible(show);
            if (!show) {
                this.dispose();
            }
        });
    }

    @Override
    public synchronized boolean isViewShowing() {
        return this.showing;
    }

    @Override
    public void display(final Set<GameObject> gameObjects, final int lives, final int levelNumber) {
        SwingUtilities.invokeLater(() -> gamePanel.display(gameObjects, lives, levelNumber));
    }

    @Override
    public Set<Integer> getPressedKeys() {
        return gamePanel.getKeysPressed();
    }

    private synchronized void setShowingFalse() {
        this.showing = false;
    }

    private void resizeDiagonally() {
        final Dimension size = getSize();
        final int newWidth = size.width;
        final int newHeight = (int) (newWidth / aspectRatio);
        if (newHeight != size.height) {
            setSize(newWidth, newHeight);
        }
    }
}
