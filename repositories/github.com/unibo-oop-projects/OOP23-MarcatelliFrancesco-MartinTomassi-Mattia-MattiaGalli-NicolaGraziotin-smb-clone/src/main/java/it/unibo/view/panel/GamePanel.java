package it.unibo.view.panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.GameController;
import it.unibo.model.entity.player.MeatBoy;

/**
 * The GamePanel class represents the main panel for rendering game elements.
 * It extends JPanel and provides methods for painting and handling keyboard input.
 */
@SuppressFBWarnings("SE_TRANSIENT_FIELD_NOT_RESTORED")
public class GamePanel extends JPanel implements KeyListener {

    public static final long serialVersionUID = 1;
    private final transient List<BufferedImage> images;
    private final transient GameController controller;
    private final transient MeatBoy meatBoy;

    /**
     * Constructs a new GamePanel with the specified GameController.
     *
     * @param controller The GameController associated with the panel.
     */
    @SuppressFBWarnings("MC_OVERRIDABLE_METHOD_CALL_IN_CONSTRUCTOR")
    public GamePanel(final GameController controller) {
        this.images = new ArrayList<>();
        this.controller = controller;
        this.meatBoy = this.controller.getMeatBoy();
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setLayout(null);
    }

    /**
     * Sets the list of Images to be displayed on the panel and triggers a repaint.
     * In the Images, there are Background, Stationary, Saws, and MeatBoy Images (in order).
     *
     * @param images The list of BufferedImages representing game elements.
     */
    public void setImages(final List<BufferedImage> images) {
        this.images.addAll(images);
        repaint();
    }

    /**
     * Overrides the paintComponent method of the JPanel class to paint the game elements.
     * 
     * @param g The Graphics context used for painting.
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (g instanceof Graphics2D) {
            final Graphics2D g2d = (Graphics2D) g;
            for (int i = 0; i < this.images.size(); i++) {
                if (i == this.images.size() - 1) {
                    g2d.drawImage(Objects.requireNonNull(this.images.get(i)), (int) meatBoy.getX(), (int) meatBoy.getY(), this);
                } else {
                    g2d.drawImage(Objects.requireNonNull(this.images.get(i)), 0, 0, this);
                }
            }
        } else {
            return;
        }
    }

    /**
     * Handles key pressed events.
     *
     * @param e The KeyEvent representing the key pressed event.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE
            && JOptionPane.showConfirmDialog(null, "Sei sicuro di volere uscire?", "Torna al menu", JOptionPane.YES_NO_OPTION)
            == JOptionPane.YES_OPTION) {
            this.controller.esc();
        }
        this.controller.moveMeatBoy(e.getKeyCode());
    }

    /**
     * Handles key released events.
     *
     * @param e The KeyEvent representing the key released event.
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        this.controller.stopMovingMeatBoy(e.getKeyCode());
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }
}

