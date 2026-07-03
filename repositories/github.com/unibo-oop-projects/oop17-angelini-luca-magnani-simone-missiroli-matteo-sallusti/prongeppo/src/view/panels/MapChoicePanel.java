package view.panels;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import utility.Background;
import utility.Utilities;

/**
 * This JPanel is used to select the background of the game.
 * @author Paolo&Missi
 *
 */
public class MapChoicePanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -2222002839635154931L;
    private final List<Background> maps;
    private int count;
    private BufferedImage bgImage;
    /**
     * 
     */
    public MapChoicePanel() {
        super(new BorderLayout());
        this.maps = Arrays.asList(Background.values());
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(final MouseEvent arg0) { }
            @Override
            public void mousePressed(final MouseEvent arg0) { }
            @Override
            public void mouseExited(final MouseEvent arg0) { }
            @Override
            public void mouseEntered(final MouseEvent arg0) { }
            @Override
            public void mouseClicked(final MouseEvent arg0) {
                count = Utilities.updateItToList(count, maps, true);
                final Background bg = maps.get(count);
                try {
                    MapChoicePanel.this.saveBG(ImageIO.read(getClass().getResource("/res/" + bg.name() + ".jpg")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setVisible(true);
        try {
            this.bgImage  = ImageIO.read(getClass().getResource("/res/BGTECNO.jpg")); //inizializazione sbagliata
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Saves a copy of the background image for faster access.
     * @param read **image to show on the panel**
     */
    private void saveBG(final BufferedImage read) {
        this.bgImage = read;
        this.repaint();
    }
    /**
     * It draws Image with same Dimension of the panel.
     */
    @Override
    public void paintComponent(final Graphics g) {
        g.drawImage(this.bgImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }
    /**
     * @return **the selected bg image**
     */
    public Image getImage() {
        return this.bgImage;
    }
}
