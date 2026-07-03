
package view.panels;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JPanel;

import controller.GraphicManager;
import utility.GameValues;
import view.PongElement;

/**
 * The panel representing the game world/elems.
 * @author Missi&Simo
 *
 */
public class PongPanel extends JPanel {

    private static final long serialVersionUID = -3665022203274529626L;
    private final Image image;
    private final GraphicManager gmanager;
    /**
     * @param img **The background image of this panel**
     * @param gmanager **the Graphic manager to take PongElement**
     * @param inputList **the list of keyListener**
     */
    public PongPanel(final Image img, final GraphicManager gmanager, final List<KeyListener> inputList) {
        super();
        this.gmanager = gmanager;
        this.setLayout(null);
        this.image = img;
        this.setBackground(Color.BLACK);
        this.setForeground(Color.BLACK);
        inputList.forEach(inputListener -> this.addKeyListener(inputListener));
    }

    @Override
    public final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.grabFocus();
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        this.gmanager.getList().forEach(elem -> {
            if (elem.isVisible()) {
                this.adaptImage(elem, g);
            }
        });
    }

    private void adaptImage(final PongElement elem, final Graphics g) {
        g.drawImage(elem.getImage(), 
                this.getWidth() * elem.getPosition().x / GameValues.WORLDWIDTH, 
                this.getHeight() * elem.getPosition().y / GameValues.WORLDHEIGHT, 
                this.getWidth() * elem.getWidth() / GameValues.WORLDWIDTH, 
                this.getHeight() * elem.getHeight() / GameValues.WORLDHEIGHT, this);
    }

}
