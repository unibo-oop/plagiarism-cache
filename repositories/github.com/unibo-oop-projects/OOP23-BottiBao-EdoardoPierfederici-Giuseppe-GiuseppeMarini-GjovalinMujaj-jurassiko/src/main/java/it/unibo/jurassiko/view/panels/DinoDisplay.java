package it.unibo.jurassiko.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import it.unibo.jurassiko.model.player.api.Player.GameColor;

/**
 * The DinoDisplay class represents a panel containing the sprite of a dino and
 * the amount of troops in a territory or ocean.
 */
public class DinoDisplay extends JPanel {

    private static final long serialVersionUID = -4807657096142808628L;
    private static final double NTROOPS_WIDTH_RATIO = 0.43;
    private static final double NTROOPS_HEIGHT_RATIO = 0.35;
    private static final int FONT_SIZE = 14;

    private final JLabel dinoSprite;
    private final JLabel nTroops;
    private final Map<GameColor, ImageIcon> sprites;

    /**
     * Create the panel where insert the dino and counter of troops.
     * 
     * @param spriteLoader the instance of class SpriteLoader
     * @param isOcean      true if it belongs to an ocean, false otherwise
     * @param x            the x-coordinate
     * @param y            the y-coordinate
     */
    public DinoDisplay(final SpriteLoader spriteLoader, final boolean isOcean, final int x, final int y) {
        this.sprites = isOcean ? spriteLoader.getLaprasSprites() : spriteLoader.getDinoSprites();

        final int width = sprites.get(GameColor.RED).getIconWidth();
        final int height = sprites.get(GameColor.RED).getIconHeight();
        this.dinoSprite = new JLabel();
        setSpriteLabel(width, height);

        this.nTroops = new JLabel();
        setNTroopsLabel(width, height);

        final JLayeredPane lpane = new JLayeredPane();
        lpane.add(dinoSprite, JLayeredPane.DEFAULT_LAYER);
        if (!isOcean) {
            lpane.add(nTroops, JLayeredPane.PALETTE_LAYER);
        }
        lpane.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.add(lpane);
        this.setOpaque(false);
        this.setBounds(x, y, width, height);
    }

    /**
     * 
     * @param color the color of dino to set
     */
    public final void setSpriteColor(final GameColor color) {
        this.dinoSprite.setIcon(this.sprites.get(color));
    }

    /**
     * 
     * @param dinoAmount the amount of dino to set
     */
    public final void setNumber(final int dinoAmount) {
        this.nTroops.setText(Integer.toString(dinoAmount));
    }

    private void setSpriteLabel(final int width, final int height) {
        setSpriteColor(GameColor.RED); // Red as default
        this.dinoSprite.setOpaque(false);
        this.dinoSprite.setBounds(0, 0, width, height);
    }

    private void setNTroopsLabel(final int totalWidth, final int totalHeight) {
        this.nTroops.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        this.nTroops.setBackground(Color.BLUE);
        this.nTroops.setForeground(Color.YELLOW);
        this.nTroops.setHorizontalAlignment(JLabel.CENTER);
        final int width = (int) (NTROOPS_WIDTH_RATIO * totalWidth);
        final int height = (int) (NTROOPS_HEIGHT_RATIO * totalHeight);
        final int x = totalWidth - width;
        final int y = totalHeight - height;
        this.nTroops.setBounds(x, y, width, height);
        this.nTroops.setOpaque(true);
        setNumber(1);
    }
}
